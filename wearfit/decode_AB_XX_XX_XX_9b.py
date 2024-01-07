def parse(state, data):
    i = data[5]
    j = data[6]
    state.context.update({
        "hasmianyi": state.checkbit(i, 1),
        "hastiwen": state.checkbit(i, 2),
        "hasnewtiwen": state.checkbit(i, 4),
        "hasviber": state.checkbit(j, 1),
        "hasvkclient": state.checkbit(j, 2),
        "hastelegram": state.checkbit(j, 4),
        "hasskype": state.checkbit(j, 8),
        "haswearfit": state.checkbit(j, 16),
        "hasdrinkwater": state.checkbit(j, 32),
    })
    return True
