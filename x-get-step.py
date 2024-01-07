#!/usr/bin/env vpython3
import sys
import datetime
import sqlalchemy as sa
from wearfit import decodestate

ADDRESS = "D5:60:92:D2:1D:CE"

state = decodestate.State(ADDRESS)

def main():
    if len(sys.argv) > 1:
        forday = datetime.datetime.strptime(sys.argv[1], '%Y-%m-%d')
    else:
        forday = datetime.datetime.now()
        forday = datetime.datetime(forday.year, forday.month, forday.day, 0)
    sstart = forday
    sstop = forday  + datetime.timedelta(hours=24)

    tbl = decodestate.model.Measure
    stmt = sa.select(
        tbl
    ).where(
        (tbl.when >= sstart)
        &(tbl.when < sstop)
        &(tbl.StepCount > 0)
    ).with_only_columns(
        tbl.when, tbl.StepCount, tbl.Calory
    ).order_by(
        tbl.when.desc()
    )
    if 0:
        cstmt = stmt.compile()
        print(cstmt.__str__(), cstmt.params)

    print(sstart, sstop)
    for row in state.session.execute(stmt).fetchall():
        print(row)

main()
