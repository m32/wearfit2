def parse(state, data):
    if data[5] in (0x09, 0x11, 0x21):
        # measure once
        if len(data) >= 7 and data[6] == 0:
            return False
        if data[5] == 0x09:
            s = [('HeartRate', data[6]),]
        elif data[5] == 0x11:
            s = [('BloodOxygen', data[6]),]
        elif data[5] == 0x21:
            s = [
                ('BloodPressure_high', data[6]),
                ('BloodPressure_low', data[7]),
            ]
        else:
            return False
        s.extend(('measure', 'once'))
    elif data[5] in (0x0a, 0x12, 0x22):
        # measure realtime
        if data[6] == 0:
            return False
        if data[5] == 0x10:
            s = [('HeartRate', data[6]),]
        elif data[5] == 0x12:
            s = [('BloodOxygen', data[6]),]
        elif data[5] == 0x22:
            s = [
                ('BloodPressure_high', data[6]),
                ('BloodPressure_low', data[7]),
            ]
        else:
            return False
        s.extend(('measure', 'realtime'))
    elif data[5] == 0x81:
        #Decimal('7.325').quantize(Decimal('.01'), rounding=ROUND_DOWN)
        s = [('BodyTemp', data[6] + data[7] / 10.0),]
    elif data[5] == 0x41:
        s = [('Mianyi', data[6]),]
    else:
        return False
    state.write('{}\n'.format(s))
    return False
