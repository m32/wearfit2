def parse(state, data):
    vmaj = data[6]
    vmin = data[7]
    band_type = data[8]
    state.context = {
        "app": ['2','1'][band_type <= 29 or band_type >= 100 and band_type <= 199],
        "major": vmaj,
        "minor": vmin,
        "ota_version": vmaj + vmin / 100.0,
        "band_type": band_type,
        "0x01": "",
        "0x02": "",
        "jizuo_setting": False, # przypomnienia o siedzącym trybie życia
        "power_saving": False,
        "has_hongwai_setting": False,
        "jichang_message_length": False,
        "has_weather": False,
        "has_contact": False,
        "hasContinueHr": False,
        "hasEcg": False,
        "hasContinueHrD": False,
        "hasContinueHrE": False,
        "hasContinueHrF": False,
        "ox10": False,
        "ox0a": False,
        "ox30": False,
    }
    if state.context['app'] == '1':
        i = 0
    else:
        if len(data) >= 18:
            i = data[17]
            state.context.update({
                "0x01": "0x01" if state.checkbit(i, 1) else "",
                "0x02": "0x02" if state.checkbit(i, 2) else "",
                "jizuo_setting": state.checkbit(i, 4),
                "power_saving": state.checkbit(i, 8),
                "has_hongwai_setting": state.checkbit(i, 16),
                "jichang_message_length": state.checkbit(i, 32),
                "has_weather": state.checkbit(i, 64),
                "has_contact": state.checkbit(i, 128),
            })
            if data[0] == 0xab:
                i = data[16]
                state.context.update({
                    'hasContinueHr': i == 11,
                    'hasEcg': i == 12,
                    'hasContinueHrD': i == 13,
                    'hasContinueHrE': i == 14,
                    'hasContinueHrF': i == 15,
                    'ox10': i == 16,
                    'ox0a': i == 10,
                    'ox30': i == 48,
                })

        i = data[15]
    state.context.update({
        'isShowStepLength': state.checkbit(i, 1),
        'isShowSleepInterval': state.checkbit(i, 2),
        'isShowHourly': state.checkbit(i, 4),
        'showWechat': not state.checkbit(i, 8),
        'showHeartRateWarn': state.checkbit(i, 16),
        'isHanTianXia': state.checkbit(i, 32),
        'support_jingqi': state.checkbit(i, 128),
    })
    state.context.update({
        "has_ecg": state.context['hasEcg'],
        "has_continue_hr": state.context['hasContinueHr'],
        "has_continue_hr_d": state.context['hasContinueHrD'],
        "has_continue_hr_e": state.context['hasContinueHrE'],
        "has_continue_hr_f": state.context['hasContinueHrF'],
        "0x10": state.context['ox10'],
        #"0x10": device_name == "Smart Fit",
        "0x0a": state.context['ox0a'],
        "0x30": state.context['ox30'],
        "do_step_length": state.context['isShowStepLength'],
        "do_sleep": state.context['isShowSleepInterval'],
        "do_time_12": state.context['isShowHourly'],
        "do_wechat": state.context['showWechat'],
        "do_show_heartrate_warn": state.context['showHeartRateWarn'],
        "set_watch_bg": 0,
        "show_ping_bao": False,
        "screen_width": 0,
        "screen_height": 0,
        "SHOW_PING_BAO_SWITCH": False,
        "is_fang_ping": False,
    })
    if len(data) > 18:
        config = {
            14: (True, 360, 360, True, False),
            13: (True, 360, 360, False, False),
            12: (True, 240, 283, True, True),
            11: (True, 240, 283, False, True),
            10: (True, 240, 288, True, True),
             9: (True, 240, 288, False, True),
             8: (True, 240, 240, True, False),
             7: (True, 240, 240, False, True),
             6: (True, 135, 240, True, True),
             5: (True, 135, 240, False, True),
             4: (True,  80, 160, True, True),
             3: (True, 240, 240, True, True),
             2: (True,  80, 160, False, True),
             1: (True, 240, 240, False, True),
        }
        i = data[18]
        state.context["set_watch_bg"] = i
        v = config.get(i, None)
        if v:
            state.context.update({
                "show_ping_bao": v[0],
                "screen_width": v[1],
                "screen_height": v[2],
                "SHOW_PING_BAO_SWITCH": v[3],
                "is_fang_ping": v[4],
            })
    i = data[19] if len(data) > 19 else 1|2
    state.context.update({
        "have_zh": state.checkbit(i, 1),
        "have_en": state.checkbit(i, 2),
        "have_it": state.checkbit(i, 4),
        "have_es": state.checkbit(i, 8),
        "have_pt": state.checkbit(i, 16),
        "have_ru": state.checkbit(i, 32),
        "have_ja": state.checkbit(i, 64),
        "new_language": state.checkbit(i, 128),
    })
    v = state.checkbit(data[9], 128)
    state.context.update({
        'haveSwitchWatch': v,
        'SwitchWatchTyp': ((data[9] - 128) * 256 + data[10]) if v else 0,
    })
    return True
