#!/usr/bin/env vpython3
import os
import io
import json
import datetime
import time
from . import model

class State:
    def __init__(self, devicemac='00:00:00:00:00:00', **kwargs):
        self.fname = State.fname(devicemac)
        engine = model.sa.create_engine("sqlite:///%s.sqlite3"%self.fname, **kwargs)

        self.devicemac = devicemac
        self.engine = engine
        self.session = model.saorm.Session(engine)
        self.time = time.time()
        self.sync = datetime.datetime.now()
        self.syncs = self.sync.strftime('%Y-%m-%d %H:%M')
        self.context = {}
        self.log = io.StringIO()
        self.line = ''
        self.cmdname = 'startup'
        self.responses = 0

        with open(self.fname+'.json', 'rt') as fp:
            data = fp.read()
            self.cfg = json.loads(data)
        try:
            self.lastsync = datetime.datetime.strptime(self.cfg[self.devicemac]['lastsync'], '%Y-%m-%d %H:%M')
        except KeyError:
            self.lastsync = datetime.datetime(2000, 1, 1)
        self.syncdata = None

    @staticmethod
    def fname(devicemac):
        return 'wearfit-'+''.join(devicemac.split(':'))

    def close(self, commit=True):
        if commit:
            self.cfg[self.devicemac]['lastsync'] = self.sync.strftime('%Y-%m-%d %H:%M')
            with open(self.fname+'.json', 'wt') as fp:
                data = json.dumps(self.cfg, indent=4)
                fp.write(data)

            self.session.commit()
        else:
            self.session.rollback()

        data = self.log.getvalue()
        if data:
            with open(self.fname+'.log', 'at') as fp:
                fp.write(data)

    def checkbit(self, i, j):
        return i&j == j

    def write(self, s):
        self.log.write(datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'))
        self.log.write(' ')
        self.log.write(s)

    def todo(self):
        self.write('todo {}: {}\n'.format(self.cmdname, self.line))


def createdatabase(devicemac='00:00:00:00:00:00', **kwargs):
    fname = State.fname(devicemac)
    if os.path.exists(fname+'.sqlite3'):
        return
    engine = model.sa.create_engine("sqlite:///%s.sqlite3"%fname, **kwargs)
    model.Base.metadata.create_all(engine)

    if os.path.exists(fname+'.json'):
        return
    data = {
        devicemac:{
            'lastsync': '1900-01-01 00:00',
        }
    }
    with open(fname+'.json', 'wt') as fp:
        data = json.dumps(data, indent=4)
        fp.write(data)

def main():
    createdatabase()

if __name__ == '__main__':
    main()

