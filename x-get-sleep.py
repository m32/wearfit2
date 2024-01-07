#!/usr/bin/env vpython3
import sys
import datetime
import sqlalchemy as sa
from wearfit import decodestate

ADDRESS = "D5:60:92:D2:1D:CE"

state = decodestate.State(ADDRESS)

class Sleep:
    def __init__(self):
        self.sleepData = []

    def addSleep(self, sstart, slength, stype):
        # stype: 1=shallow, 2=deep
        send = sstart + datetime.timedelta(minutes=slength)
        self.sleepData.append([sstart, send, stype])

    def finish(self):
        t = []
        for s in sorted(self.sleepData):
            t.append(s)
        i = 1
        while i < len(t):
            prev = t[i-1]
            this = t[i]
            if prev[1] > this[0]:
                newlength = (prev[1] - this[1]).seconds // 60
                prev[1] = this[0]
                if newlength > 0:
                    # split sleep period
                    t.insert(i+1, [this[1], this[1]+datetime.timedelta(minutes=newlength), prev[2]])
            elif prev[1] < this[0]:
                # awakening
                t.insert(i, [prev[1], this[0], 0])
                i += 1
            i += 1
        total = {
            0:datetime.timedelta(0),
            1:datetime.timedelta(0),
            2:datetime.timedelta(0),
        }
        for s in t:
            # when, duration, type (0=awakening, 1=shallow, 2=deep)
            print(s[0], s[1]-s[0], s[2])
            total[s[2]] += s[1]-s[0]
        print('0', total[0])
        print('1', total[1])
        print('2', total[2])
        print('=', total[0]+total[1]+total[2])

def main():
    if len(sys.argv) > 1:
        forday = datetime.datetime.strptime(sys.argv[1], '%Y-%m-%d')
    else:
        forday = datetime.datetime.now()
        forday = datetime.datetime(forday.year, forday.month, forday.day, 0)
    sstart = forday - datetime.timedelta(hours=12)
    sstop = forday + datetime.timedelta(hours=12)

    tbl = decodestate.model.Measure
    stmt = sa.select(
        tbl
    ).where(
        (tbl.when >= sstart)
        &(tbl.when < sstop)
        &(tbl.Sleep_id != None)
    ).with_only_columns(
        tbl.when, tbl.Sleep_time, tbl.Sleep_id
    )
    if 0:
        cstmt = stmt.compile()
        print(cstmt.__str__(), cstmt.params)

    sleep = Sleep()
    for row in state.session.execute(stmt).fetchall():
        #print(row)
        sleep.addSleep(row.when, row.Sleep_time, row.Sleep_id)
    sleep.finish()

main()
