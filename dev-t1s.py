#!/usr/bin/env vpython3
import sys
import time
import datetime
import asyncio
import platform

from bleak import BleakClient, BleakScanner
from wearfit import commands, decode, decodestate

CHARACTERISTIC_UUID = "6e400003-b5a3-f393-e0a9-e50e24dcca9e"
RX = '6e400002-b5a3-f393-e0a9-e50e24dcca9e'
ADDRESS = 'D5:60:92:D2:1D:CE'

if 1:
    # https://github.com/hbldh/bleak/issues/367
    import subprocess
    try:
        subprocess.call(['bluetoothctl','disconnect', ADDRESS], timeout=12)
        print(ADDRESS, 'was connected')
    except (subprocess.CalledProcessError, subprocess.TimeoutExpired) as why:
        # do what you need to do
        print(ADDRESS, 'was not connected', why)
    time.sleep(5)

decodestate.createdatabase(ADDRESS)
state = decodestate.State(ADDRESS)


def vnotification_handler(sender, data):
    state.responses += 1
    data = list(data)
    state.line = ' '.join(['%02x'%x for x in data])
    #state.write('recv: {}\n'.format(state.line))
    decode.notification_handler(sender, state, data)
    state.time = time.time()


async def main():
    print('last sync:', state.lastsync)
    device = await BleakScanner.find_device_by_address(
        ADDRESS, cb=dict(use_bdaddr=True)
    )
    if device is None:
        print("could not find device with address '{}'".format(ADDRESS))
        return

    async with BleakClient(device, timeout=15) as client:

        print(f"Connected: {client.is_connected}")
        if 0:
            paired = await client.pair(protection_level=0)
            print(f"Paired: {paired}")

        if 0:
            for service in client.services:
                print("[Service] {0}: {1}".format(service.uuid, service.description))
                for char in service.characteristics:
                    if "read" in char.properties:
                        try:
                            value = bytes(await client.read_gatt_char(char.uuid))
                        except Exception as e:
                            value = str(e).encode()
                    else:
                        value = None
                    print(
                        "  [Characteristic] {0}: (Handle: {1}) ({2}) | Name: {3}, Value: {4} ".format(
                            char.uuid,
                            char.handle,
                            ",".join(char.properties),
                            char.description,
                            value,
                        )
                    )
                    for descriptor in char.descriptors:
                        value = await client.read_gatt_descriptor(descriptor.handle)
                        print(
                            "    [Descriptor] {0}: (Handle: {1}) | Value: {2} ".format(
                                descriptor.uuid, descriptor.handle, bytes(value)
                            )
                        )

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

        print('start notify:', CHARACTERISTIC_UUID)
        await client.start_notify(CHARACTERISTIC_UUID, vnotification_handler)
        await asyncio.sleep(5.00)

        print('Commands:')
        cmds = commands.Commands()

        if 1:
            cmd = cmds.setUpHandLightScreen(1)
            await one('setUpHandLightScreen', cmd)

        if 1:
            sync = datetime.datetime.now()
            cmd = cmds.setTime(sync.year, sync.month, sync.day, sync.hour, sync.minute, sync.second)
            await one('setTime', cmd)

            sync = state.lastsync
            cmd = cmds.setTimeSync(sync.year, sync.month, sync.day, sync.hour, sync.minute, sync.second)
            await one('setTimeSync', cmd)

            cmd = cmds.getBattery()
            await one('getBattery', cmd)

            cmd = cmds.getBatteryInfo()
            await one('getBatteryInfo', cmd)

            cmd = cmds.getVersionInfo()
            await one('getVersionInfo', cmd)

        if 0:
            cmd = cmds.getClassicMac()
            await one('getClassicMac', cmd)

        if 0:
            data = [
                # 0 pochmurnie 0
                # 1 słońce
                # 2 pada 0
                # 3 pada 1
                # 4 pochmurnie 1
                # 5 wiatr
                # 6 zawieje
                # 7 półchmurki
                # 8 brak
            ]
            for i in range(9):
                data = [(i, i+10)]
                cmd = cmds.sendWeatherInfo(data)
                await one('sendWeatherInfo', cmd, 5.0)

        if 0:
            #await one('switchSpeed', cmds.switchSpeed(1))
            await one('switchSpeed', cmds.switchSpeed(0))

        if 1:
            cmd = cmds.setSyncData(state.lastsync.year, state.lastsync.month, state.lastsync.day, state.lastsync.hour, state.lastsync.minute)
            await one('setSyncData', cmd, 5.0)

        if 1:
            cmd = cmds.setSyncSleepData(state.lastsync.year, state.lastsync.month, state.lastsync.day, state.lastsync.hour, state.lastsync.minute)
            await one('setSyncSleepData', cmd, 5.0)

        if 0:
            await one('setBandLanguage', cmds.setBandLanguage(1))

        state.close()

        await client.stop_notify(CHARACTERISTIC_UUID)
        await client.disconnect()
        

if __name__ == "__main__":
    asyncio.run(main())
