#!/usr/bin/env vpython3
import sys
import glob
from wearfit import decode, decodestate

ADDRESS = 'x-bt-log-dump'
decodestate.createdatabase(ADDRESS)
state = decodestate.State(ADDRESS)

class Main:
    def __init__(self):
        self.commands = {}
        self.lastcommand = None

    def MainActivity(self, line):
        line = line.split('data receive:-->')
        if len(line) != 2:
            #print('bad MainActivity', line)
            return
        print(line)
        data = [int(x, 16) for x in line[1].split()]
        handled = decode.notification_handler(None, state, data)
        if not handled:
            print('unhandled', line)

    def CommandManager(self, line):
        line = line.split('CommandManager:')[1]
        n = line.find('ble send')
        if n < 0:
            self.lastcommand = line.strip().split(':')[0]
            return
        line = line[n+8:].strip()
        try:
            cmds = self.commands[self.lastcommand]
            if line not in cmds:
                cmds.append(line)
        except:
            self.commands[self.lastcommand] = [line]

    def BleUtil(self, line):
        return
        print(line)

    def main(self, fname):
        fp = open(fname, 'rb')
        while True:
            line = fp.readline()
            if not line:
                break
            #05-30 22:37:05.941 30158 30158 I MainActivity: data receive:--> ab 00 0f ff 92 c0 15 01 c8 00 00 00 00 00 00 20 0c 40
            #05-30 22:37:05.942 30158 30158 I CommandManager: ble send  ab 00 0b ff 93 80 00 07 e7 05 1e 16 25 05
            #if line.find(b'MainActivity:') > 0:
            if line.find(b'data receive:-->') > 0:
                self.MainActivity(line.strip().decode())
            elif line.find(b'CommandManager:') > 0:
                self.CommandManager(line.strip().decode())
            elif line.find(b'ble send') > 0:
                self.CommandManager(line.strip().decode())
            elif line.find(b'BleUtil:') > 0:
                self.BleUtil(line.strip().decode())
            elif line.find(b'BleUtil :') > 0:
                self.BleUtil(line.strip().decode())

def main():
    fnames = glob.glob('x-bt-log-*.log')
    fname = sys.argv[1] if len(sys.argv)>1 else fnames[-1]
    cls = Main()
    if fname == 'all':
        for fname in sorted(fnames):
            print('*'*10, fname)
            cls.main(fname)
    else:
        cls.main(fname)
    if 0:
        print(cls.commands)
        print(state.context)

if __name__ == '__main__':
    main()
