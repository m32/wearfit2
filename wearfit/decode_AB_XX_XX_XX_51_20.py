import datetime
from . import model

def parse(state, data):
    i6 = data[6] + 2000
    i7 = data[7]
    i8 = data[8]
    i9 = data[9]
    i10 = (data[10] << 16) + (data[11] << 8) + data[12]
    i11 = (data[13] << 16) + (data[14] << 8) + data[15]
    i12 = data[16]
    i13 = data[17]
    i14 = data[18]
    i15 = data[19]
    when = datetime.datetime(i6, i7, i8, i9, 0)
    if when > state.lastsync:
        rec = model.Measure(
            synced = state.sync,
            line = state.line,
            when = when,
            Mianyi = i9,
            StepCount = i10,
            Calory = i11,
            HeartRate = i12,
            BloodOxygen = i13,
            BloodPressure_high = i14,
            BloodPressure_low = i15,
        )
        if state.syncdata is not None:
            rec.setShallow_sleep_time = state.syncdata.setShallow_sleep_time
            rec.setDeep_sleep_time = state.syncdata.setDeep_sleep_time
            rec.setSleep_time = state.syncdata.setSleep_time
            rec.setWakeup_times = state.syncdata.setWakeup_times
            state.syncdata = None
        state.session.add(rec)
    return True
