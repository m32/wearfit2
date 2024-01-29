#!/usr/bin/env vpython3
import sys
import time
import datetime
import asyncio
import platform

from bleak import BleakClient
from wearfit import commands, decode, decodestate

CHARACTERISTIC_UUID = "6e400003-b5a3-f393-e0a9-e50e24dcca9e"
RX = '6e400002-b5a3-f393-e0a9-e50e24dcca9e'
ADDRESS = "D5:60:92:D2:1D:CE"

state = decodestate.State(ADDRESS)

def vnotification_handler(sender, data):
    state.responses += 1
    data = list(data)
    state.line = ' '.join(['%02x'%x for x in data])
    #state.write('recv: {}\n'.format(state.line))
    decode.notification_handler(sender, state, data)
    state.time = time.time()

async def main():
    async with BleakClient(ADDRESS) as client:
        async def one(cmdname, cmd, timeout=2.0):
            print('cmd: {}'.format(cmdname))
            state.cmdname = cmdname
            state.responses = 0
            #bdata = ' '.join(['%02x'%x for x in cmd])
            #state.write('send: {}: {}\n'.format(cmdname, bdata))
            await client.write_gatt_char(RX, cmd)
            state.time = time.time()
            while time.time() - state.time < timeout:
                await asyncio.sleep(0.5)
            print('cmd: {} resp:{}'.format(cmdname, state.responses))

        client._mtu_size = 500
        if 0:
            await client._acquire_mtu()
            state.write("MTU: {}".format(client.mtu_size))


        await client.start_notify(CHARACTERISTIC_UUID, vnotification_handler)

        state.write('startup\n')
        await asyncio.sleep(5.00)

        cmds = commands.Commands()

        if 0:
            sync = datetime.datetime.now()
            cmd = cmds.setTime(sync.year, sync.month, sync.day, sync.hour, sync.minute, sync.second)
            await one('setTime', cmd)

        if 0:
            cmd = cmds.setBandLanguage(1)
            await one('setBandLanguage', cmd)

        if 1:
            cmd = cmds.setUserInfo(1, 70, 57, 183, 83, 8000, 1, 0)
            await one('setUserInfo', cmd)

        if 0:
            cmd = cmds.set12HourSystem(0)
            await one('set12HourSystem', cmd)

        if 0:
            cmd = cmds.setOnTimeMeasure(1)
            await one('setOnTimeMeasure', cmd)

        if 0:
            cmd = cmds.setSleepTimeRange(1, 23, 0, 9, 0)
            await one('setSleepTimeRange', cmd)

        if 0:
            cmd = cmds.setFace(0)
            await one('setFace', cmd)

        if 0:
            url = 'http://www.iwhop.com/weatherapi/weather/weekforecasting'
            lat = '53.4372274'
            lon = '14.5097729'

            import urllib.parse
            import urllib.request

            values = {
                'lat': lat,
                'lon': lon,
            }

            data = urllib.parse.urlencode(values)
            url = url+'?'+data

            req = urllib.request.Request(url)
            with urllib.request.urlopen(req) as response:
                the_page = response.read()

                now = datetime.datetime.now()
                now = datetime.datetime(now.year, now.month, now.day)

                import json
                msg = json.loads(the_page)
                if msg['code'] == 200:
                    data = []
                    for e in msg['forecasts']:
                        when = datetime.datetime.strptime(e['date'], '%Y-%m-%d')
                        if (when - now).days == len(data):
                            data.append((e['code'], e['temperature']))
                    if data:
                        cmd = cmds.sendWeatherInfo(data)
                        await one('sendWeatherInfo', cmd)

        await client.stop_notify(CHARACTERISTIC_UUID)

        state.close(False)

if __name__ == "__main__":
    asyncio.run(main())

