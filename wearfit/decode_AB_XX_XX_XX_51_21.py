import datetime
import decimal
from . import model

def parse(state, data):
    i28 = data[6] + 2000
    i29 = data[7]
    i30 = data[8]
    i31 = data[9]
    i32 = data[10]
    rec = model.Measure(
        synced = state.sync,
        line = state.line,
        when = datetime.datetime(i28, i29, i30, i31, 0),
    )
    if i32 != 0:
        rec.Mianyi = i32
    i33 = data[11]
    i34 = data[12]
    if i33 != 0:
        rec.BodyTemp = decimal.Decimal(i33 + i34/10.0).quantize(decimal.Decimal('.01'), rounding=decimal.ROUND_DOWN)
    if i32 != 0 or i33 != 0:
        state.session.add(rec)
    return True
