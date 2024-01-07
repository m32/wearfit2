import decimal
import datetime
from . import model

def parse(state, data):
    # odczyt ciśnienia przez opaskę
    i48 = data[6] + 2000
    i49 = data[7]
    i50 = data[8]
    i51 = data[9]
    i52 = data[10]
    i53 = data[11]
    i54 = data[12]
    if i53 != 0 and i54 != 0:
        rec = model.Measure(
            synced = state.sync,
            line = state.line,
            when = datetime.datetime(i48, i49, i50, i51, i52),
            BloodPressure_high = i53,
            BloodPressure_low = i54,
            Type = 0,
        )
        state.session.add(rec)
    return True
