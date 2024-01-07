#!/usr/bin/env vpython3
from . import (
    decode_00,
    decode_01,
    decode_AB_XX_XX_XX_28_09,
    decode_AB_XX_XX_XX_28_80,
    decode_AB_XX_XX_XX_31,
    decode_AB_XX_XX_XX_51_08,
    decode_AB_XX_XX_XX_51_11,
    decode_AB_XX_XX_XX_51_12,
    decode_AB_XX_XX_XX_51_13,
    decode_AB_XX_XX_XX_51_14,
    decode_AB_XX_XX_XX_51_20,
    decode_AB_XX_XX_XX_51_21,
    decode_AB_XX_XX_XX_52,
    decode_AB_XX_XX_XX_91,
    decode_AB_XX_XX_XX_92,
    decode_AB_XX_XX_XX_95,
    decode_AB_XX_XX_XX_9b,
    decode_AC,
)

def notification_handler(sender, state, data):
    handled = True
    if data[0] == 0x00:
        handled = decode_00.parse(state, data)
    elif data[0] == 0x01:
        handled = decode_01.parse(state, data)
    elif data[0] == 0xab:
        if data[4] == 0x26:
            state.context['hasEcg'] = state.context['has_ecg'] = True
        elif data[4] == 0x28:
            if data[5] == 0x09:
                handled = decode_AB_XX_XX_XX_28_09.parse(state, data)
            elif data[5] == 0x80:
                handled = decode_AB_XX_XX_XX_28_80.parse(state, data)
            else:
                handled = False
        elif data[4] == 0x31:
                handled = decode_AB_XX_XX_XX_31.parse(state, data)
        elif data[4] == 0x32:
            state.todo()
        elif data[4] == 0x51:
            if data[5] == 0x08:
                handled = decode_AB_XX_XX_XX_51_08.parse(state, data)
            elif data[5] == 0x11:
                handled = decode_AB_XX_XX_XX_51_11.parse(state, data)
            elif data[5] == 0x12:
                handled = decode_AB_XX_XX_XX_51_12.parse(state, data)
            elif data[5] == 0x13:
                handled = decode_AB_XX_XX_XX_51_13.parse(state, data)
            elif data[5] == 0x14:
                handled = decode_AB_XX_XX_XX_51_14.parse(state, data)
            elif data[5] == 0x16:
                state.todo()
            elif data[5] == 0x18:
                state.todo()
            elif data[5] == 0x20:
                handled = decode_AB_XX_XX_XX_51_20.parse(state, data)
            elif data[5] == 0x21:
                handled = decode_AB_XX_XX_XX_51_21.parse(state, data)
            elif data[5] == 0x30:
                state.todo()
            else:
                state.todo()
        elif data[4] == 0x52:
            handled = decode_AB_XX_XX_XX_52.parse(state, data)
        elif data[4] == 0x7d:
            state.todo()
        elif data[4] == 0x81:
            state.todo()
        elif data[4] == 0x86:
            state.todo()
        elif data[4] == 0x91:
            handled = decode_AB_XX_XX_XX_91.parse(state, data)
        elif data[4] == 0x92:
            handled = decode_AB_XX_XX_XX_92.parse(state, data)
        elif data[4] == 0x95:
            handled = decode_AB_XX_XX_XX_95.parse(state, data)
        elif data[4] == 0x97:
            state.todo()
        elif data[4] == 0x9b:
            handled = decode_AB_XX_XX_XX_9b.parse(state, data)
        elif data[4] == 0xa6:
            state.todo()
        elif data[4] == 0xb2:
            state.todo()
        else:
            handled = False
    elif data[0] == 0xac:
        # EcgActivity$7.java
        # EcgActivity2$14.java
        handled = decode_AC.parse(state, data)
    if not handled:
        state.todo()
    return handled
