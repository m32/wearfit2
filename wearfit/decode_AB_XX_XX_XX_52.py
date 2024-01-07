import datetime
from . import model

def parse(state, data):
    i88 = data[6] + 2000
    i89 = data[7]
    i90 = data[8]
    i91 = data[9]
    i92 = data[10]
    i93 = data[11]
    i94 = (data[12] << 8) + data[13]
    rec = model.Measure(
        synced = state.sync,
        line = state.line,
        when = datetime.datetime(i88, i89, i90, i91, i92),
        Sleep_id = i93,
        Sleep_time = i94,
    )
    state.session.add(rec)
    return True
