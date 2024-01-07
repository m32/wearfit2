import datetime
from . import model

def parse(state, data):
    # odczyt tętna przez opaskę
    i61 = data[6] + 2000
    i62 = data[7]
    i63 = data[8]
    i64 = data[9]
    i65 = data[10]
    i66 = data[11]
    hrtype = 1 if state.context['hasContinueHr'] and (state.context['hasContinueHrD'] or state.context['hasContinueHrE'] or state.context['hasContinueHrF']) else 0
    if i66 != 0:
        rec = model.Measure(
            synced = state.sync,
            line = state.line,
            when = datetime.datetime(i61, i62, i63, i64, i65),
            HeartRate = i66,
            Type = hrtype
        )
        state.session.add(rec)
    return True
