#!/usr/bin/env vpython3
import sys
import asyncio
from bleak import BleakClient

ADDRESS = "D5:60:92:D2:1D:CE" # t1s
#ADDRESS = "F0:DE:C1:51:13:86" # galaxy watch b943 le

async def main():
    async with BleakClient(ADDRESS) as client:
        print("connected")

        for service in client.services:
            print("[Service] %s"% service)

            for char in service.characteristics:
                if "read" in char.properties:
                    try:
                        value = await client.read_gatt_char(char.uuid)
                        print(
                            "  [Characteristic] %s (%s), Value: %r"%(
                                char,
                                ",".join(char.properties),
                                value
                            )
                        )
                    except Exception as e:
                        print(
                            "  [Characteristic] %s (%s), Error: %s"%(
                                char,
                                ",".join(char.properties),
                                e
                            )
                        )

                else:
                    print(
                        "  [Characteristic] %s (%s)"%(char, ",".join(char.properties) )
                    )

                for descriptor in char.descriptors:
                    try:
                        value = await client.read_gatt_descriptor(descriptor.handle)
                        print("    [Descriptor] %s, Value: %r"%(descriptor, value))
                    except Exception as e:
                        print("    [Descriptor] %s, Error: %s"%(descriptor, e))

        print("disconnecting...")

    print("disconnected")

if __name__ == "__main__":
    asyncio.run(main())
