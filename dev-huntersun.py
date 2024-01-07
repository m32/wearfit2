#!/usr/bin/env vpython3
import sys
import asyncio
import platform

from bleak import BleakClient


# you can change these to match your device or override them from the command line
CHARACTERISTIC_UUID = "0000ff04-0000-1000-8000-00805f9b34fb"
ADDRESS = "DF:3D:0A:D5:F2:E6"

def notification_handler(sender, data):
    bdata = ','.join(['%02x'%x for x in bytes(data)])
    print("NOTIFY:", bdata)

async def main():
    async with BleakClient(ADDRESS) as client:
        print(f"Connected: {client.is_connected}")

        await client.start_notify(CHARACTERISTIC_UUID, notification_handler)

        await asyncio.sleep(5.0)


if __name__ == "__main__":
    asyncio.run(main())
