import datetime
from . import model

def parse(state, data):
    rec = model.Measure(
        synced = state.sync,
        line = state.line,
        when = datetime.datetime.now(),
        StepCount = (data[6] << 16) + (data[7] << 8) + data[8],
        Calory = (data[9] << 16) + (data[10] << 8) + data[11],
        Shallow_sleep_time = ((data[12] * 60 + data[13]) * 60 * 1000),
        Deep_sleep_time = (data[14] * 60 + data[15]) * 60 * 1000,
        Sleep_time = (data[12] * 60 + data[13] + data[14] * 60 + data[15]) * 1000 * 60,
        Wakeup_times = data[16],
    )
    state.session.add(rec)
    return True
