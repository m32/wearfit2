def parse(state, data):
    if len(data) > 8:
        i = data[8]
        state.context.update({"biaopan_num": i})
    if len(data) > 7:
        i = data[7]
        state.context.update({"xiangmubianhao": i})
    return True
