#!/usr/bin/env vpython3
import sys
import asyncio
import platform

from bleak import BleakClient


listenon = [
    '00001800-0000-1000-8000-00805f9b34fb', # Generic Access
    '00001801-0000-1000-8000-00805f9b34fb', # Generic Attribute
    '00001812-0000-1000-8000-00805f9b34fb', # Human Interface Device
    '0000fee7-0000-1000-8000-00805f9b34fb', # Niezidenyfikowany
    '6e400001-b5a3-f393-e0a9-e50e24dcca9e', # Własnościowy

    '0000fea2-0000-1000-8000-00805f9b34fb',
    '0000fea1-0000-1000-8000-00805f9b34fb',
    '0000fec9-0000-1000-8000-00805f9b34fb',
    #'6e400002-b5a3-f393-e0a9-e50e24dcca9e',
    '6e400003-b5a3-f393-e0a9-e50e24dcca9e',
    '00002a05-0000-1000-8000-00805f9b34fb',
]

ADDRESS = "D5:60:92:D2:1D:CE"

def notification_handler(c, sender, data):
    bdata = ','.join(['%02x'%x for x in bytes(data)])
    print(c, "NOTIFY:", bdata)

async def main():
    async with BleakClient(ADDRESS) as client:
        print(f"Connected: {client.is_connected}")

        for c in listenon:
            print('listen', c, end=' ')
            try:
                await client.start_notify(c, lambda s, d: notification_handler(c, s, d))
                print('ok')
            except:
                print('failed')

        for i in range(200):
            await asyncio.sleep(5.0)


if __name__ == "__main__":
    asyncio.run(main())
