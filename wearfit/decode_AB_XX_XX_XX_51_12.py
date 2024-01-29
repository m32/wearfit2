import datetime
from . import model

def parse(state, data):
    i55 = data[6] + 2000
    i56 = data[7]
    i57 = data[8]
    i58 = data[9]
    i59 = data[10]
    i60 = data[11]
    if i60 != 0:
        when = datetime.datetime(i55, i56, i57, i58, i59)
        if when > state.lastsync:
            rec = model.Measure(
                synced = state.sync,
                line = state.line,
                when = when,
                BloodOxygen = i60,
                Type = 0
            )
            state.session.add(rec)
    return True
