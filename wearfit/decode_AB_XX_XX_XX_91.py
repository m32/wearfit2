import datetime
from . import model

def parse(state, data):
    i1 = data[7]
    rec = model.Measure(
        synced = state.sync,
        line = state.line,
        when = datetime.datetime.now(),
        batteryPercent = i1,
    )
    state.session.add(rec)
    return True
