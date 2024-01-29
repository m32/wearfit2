import decimal
import datetime
from . import model

def parse(state, data):
    i41 = data[6] + 2000
    i42 = data[7]
    i43 = data[8]
    i44 = data[9]
    i45 = data[10]
    i46 = data[11]
    i47 = data[12]
    if i46 != 0 and i45 != 0:
        when = datetime.datetime(i41, i42, i43, i44, i45)
        if when > state.lastsync:
            rec = model.Measure(
                synced = state.sync,
                line = state.line,
                when = when,
                BodyTemp = decimal.Decimal(i46 + i47/10.0).quantize(decimal.Decimal('.01'), rounding=decimal.ROUND_DOWN)
            )
            state.session.add(rec)
    return True
