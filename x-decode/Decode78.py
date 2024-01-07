#!/usr/bin/env vpython3
def notification_handler(sender, data):
    unknown = False
    if data[0] == 0xab: # and data[3] == 0xff
        if data[4] == 0x31:
            if data[5] == 0x0a:
                bp = data[6]
                if bp != 0:
                    print('heart:', bp, 'app')
            elif data[5] == 0x12:
                sp = data[6]
                if bp != 0:
                    print('oxygen:', sp, 'app')
            elif data[5] == 0x22:
                bph = data[6]
                bpl = data[7]
                if bph != 0:
                    print('pressure:', bph, bpl, 'app')
            else:
                unknown = True
        elif data[4] == 0x32:
            bp = data[6]
            sp = data[7]
            bph = data[8]
            bpl = data[9]
            if bp != 0:
                print('heart:', bp, 'timed')
            if sp != 0:
                print('oxygen:', sp, 'timed')
            if bph != 0:
                print('pressure:', bph, bpl, 'timed')
        elif data[4] == 0x51:
            if data[5] == 0x08:
                stepCount = data[6] << 16 + data[7] << 8 + data[8]
                calory = data[9] << 16 + data[10] << 8 + data[11]
                shallowSleepTime = (data[12] * 60 + data[13]) * 60 * 1000
                deepSleepTime = (data[14] * 60 + data[15]) * 60 * 1000
                sleepTime = (data[12] * 60 + data[13] + data[14] * 60 + data[15]) * 1000 * 60
                wakeupTimes = data[16]
                print(f'\t steps: {stepCount} calories: {calory} shallow sleep: {shallowSleepTime} deep sleep: {deepSleepTime} sleep: {sleepTime} wakeups: {wakeupTimes}')
            elif data[5] == 0x20:
                st = data[11] * 256 + data[12]
                cl = data[14] * 256 + data[15]
                if st != 0:
                    print('steps:', data[6], data[7], data[8], data[9], cl, st)
                bp = data[16]
                sp = data[17]
                bph = data[18]
                bpl = data[19]
                if bp != 0:
                    print('heart:', data[6], data[7], data[8], data[9], data[10], bp, 'hourly')
                if sp != 0:
                    print('oxygen:', data[6], data[7], data[8], data[9], data[10], sp, 'hourly')
                if bph != 0:
                    print('pressure:', data[6], data[7], data[8], data[9], data[10], bph, bpl, 'hourly')
            elif data[5] == 0x11:
                bp = data[11]
                if bp != 0:
                    print('heart:', data[6], data[7], data[8], data[9], data[10], bp, 'watch')
            elif data[5] == 0x12:
                sp = data[11]
                if sp != 0:
                    print('oxygen:', data[6], data[7], data[8], data[9], data[10], sp, 'watch')
            elif data[5] == 0x13:
                date = (data[6]+2000, data[7], data[8], data[9], data[10])
                temp = data[11] + data[12] / 10.0
                print('\tbody temp:', date, temp)
            elif data[5] == 0x14:
                bph = data[11]
                bpl = data[12]
                if bph != 0:
                    print('pressure:', data[6], data[7], data[8], data[9], data[10], bph, bpl, 'watch')
            else:
                unknown = True
        elif data[4] == 0x52:
            dur = data[12]*256 + data[13]
            if dur != 0:
                print('sleep:', data[6], data[7], data[8], data[9], data[10], dur)
        elif data[4] == 0x7d: # and len(data) ==7:
            print('\tfind phone: {} {}'.format(data[6]))
            if data[6] == 0:
                ringing = False
            else:
                ringing = True
        elif data[4] == 0x79:
            print('take picture')
            if data[6] == 1:
                cmd = 'su -c input keyevent 24'
                print('multimedia key:', cmd)
        elif data[4] == 0x91: #and len(data) == 8:
            print('\tBattery: {}% charge={}'.format(data[7], data[6]==1))
        elif data[4] == 0x92:
            vmaj = data[6]
            vmin = data[7]
            vota = vmaj + vmin / 100.0
            vtype = data[8]
            vapp = ['2','1'][vtype <= 29 or vtype >= 100 and vtype <= 199]
            if data[17]==0x60:
                model = 'DT78'
                if data[15]==0x08:
                    model = 'T03'
            elif data[17]==0xa2:
                model = 'DT92'
                if data[15]==0xff:
                    model = 'ESP32'
            elif data[17]==0x22:
                model = 'DT66'
                if data[15]==0x28:
                    model = 'DT78 v2'
            elif data[17]==0x62:
                model = 'DT66'
                if data[15]==0x28:
                    model = 'DT66 mibro air'
            elif data[17] == 0x40:
                model = 'T1S'
            else:
                model = 'unknown'
            print('\tversion: {}.{} ota={:.2f} type={} app={}.0 model={}'.format(vmaj, vmin, vota, vtype, vapp, model))
        elif data[4] == 0x99:
            # emulate keyboard
            cmd = data[6]
            cmds = {
                0x00: 'su -c input keyevent 85', # play
                0x01: 'su -c input keyevent 87', # prev
                0x02: 'su -c input keyevent 88', # next
                0x00: 'su -c input keyevent 85', # play
            }
            print('multimedia key:', cmd, cmds.get(cmd))
        elif data[4] == 0x9b:
            options = {
                "hasmianyi": (data[5] & 1) == 1,
                "hastiwen": (data[5] & 2) == 2,
                "hasnewtiwen": (data[5] & 4) == 4,
                "hasviber": (data[6] & 1) == 1,
                "hasvkclient": (data[6] & 2) == 2,
                "hastelegram": (data[6] & 4) == 4,
                "hasskype": (data[6] & 8) == 8,
                "haswearfit": (data[6] & 16) == 16,
                "hasdrinkwater": (data[6] & 32) == 32,
            }
            print(f'\toptions: {options}')
        else:
            unknown = True
    elif data[0] == 0xad:
        if len(data) == 5:
            pos = data[3]
            if pos <= 112:
                # sendImage(pos, context)
                print(f"Request Location = {pos}")
                ##createBitmap(pos, context)
                #uploadFile(pos, context)
            else:
                print("Transfer complete")
                #sendData(byteArrayOfInts(0xAB, 0x00, 0x04, 0xFF, 0x87, 0x80, 0x00))
        else:
            unknown = True
    else:
        unknown = True
    if unknown:
        bdata = ','.join(['%02x'%x for x in bytes(data)])
        print(f"NOTIFY: unknown={unknown} data={bdata}")

def main():
    lines = open('t1s.txt', 'rt').readlines()
    for line in lines:
        line = line.split()
        if not line or line[0] != 'NOTIFY:':
            continue
        #if line[1] == 'unknown=False':
        #    continue
        line = line[2].split('=')[1].split(',')
        data = bytes([int(x, 16) for x in line])
        notification_handler(None, data)
if __name__ == '__main__':
    main()
