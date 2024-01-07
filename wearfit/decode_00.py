def parse(state, data):
    if len(data) > 5:
        class setSyncData:
            setShallow_sleep_time = (data[1] * 60) + data[2]
            setDeep_sleep_time = (data[3] * 60) + data[4]
            setSleep_time = setShallow_sleep_time + setDeep_sleep_time
            setWakeup_times = data[5]
        state.syncdata = setSyncData()
    return True
