package com.wakeup.wearfit2.manager;

public class VersionManager {
    final private static String TAG = "VersionManager";
    private com.wakeup.wearfit2.manager.CommandManager commandManager;
    private android.content.Context mContext;
    private java.util.List mDatas;
    
    static {
    }
    
    public VersionManager(java.util.List a, android.content.Context a0) {
        this.mDatas = a;
        this.mContext = a0;
    }
    
    public void handleVersion() {
        String s = TAG;
        android.util.Log.i(s, ((Object)this.mDatas).toString());
        int i = ((Integer)this.mDatas.get(6)).intValue();
        int i0 = ((Integer)this.mDatas.get(7)).intValue();
        float f = (float)((Integer)this.mDatas.get(6)).intValue() + (float)((Integer)this.mDatas.get(7)).intValue() / 100f;
        int i1 = ((Integer)this.mDatas.get(8)).intValue();
        String s0 = com.wakeup.wearfit2.util.SPUtils.getString(this.mContext, com.wakeup.wearfit2.util.SPUtils.DEVICE_NAME, "");
        String s1 = com.wakeup.wearfit2.util.SPUtils.getString(this.mContext, "LIMIT_DEVICE");
        if (!android.text.TextUtils.isEmpty((CharSequence)(Object)s1)) {
            java.util.List a = ((com.wakeup.wearfit2.bean.LimitBeans)new com.google.gson.Gson().fromJson(s1, com.wakeup.wearfit2.bean.LimitBeans.class)).getData();
            this.commandManager = com.wakeup.wearfit2.manager.CommandManager.getInstance(this.mContext);
            if (this.isLimitBand(a, s0, Float.toString(f), i1).booleanValue()) {
                com.wakeup.wearfit2.util.SPUtils.putString(this.mContext, "address_for_peidui", "");
                com.wakeup.wearfit2.AppApplication.batteryPercent = 0;
                com.wakeup.wearfit2.util.SPUtils.putString(this.mContext, com.wakeup.wearfit2.util.SPUtils.DEVICE_ADDRESS, "");
                com.wakeup.wearfit2.util.SPUtils.putString(this.mContext, com.wakeup.wearfit2.util.SPUtils.DEVICE_NAME, "");
                com.wakeup.wearfit2.util.SPUtils.putString(this.mContext, "last_name", "");
                this.commandManager.unbind();
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "has_contact", false);
                android.content.Intent a0 = new android.content.Intent("USER_UNBIND_DEVICE");
                this.mContext.sendBroadcast(a0);
                return;
            }
            com.wakeup.wearfit2.AppApplication.main_version = i;
            com.wakeup.wearfit2.AppApplication.sub_version = i0;
            com.wakeup.wearfit2.AppApplication.ota_version = f;
            com.wakeup.wearfit2.AppApplication.band_type = i1;
        }
        com.wakeup.wearfit2.AppApplication.main_version = i;
        com.wakeup.wearfit2.AppApplication.sub_version = i0;
        com.wakeup.wearfit2.AppApplication.ota_version = f;
        com.wakeup.wearfit2.AppApplication.band_type = i1;
        int i2 = com.wakeup.wearfit2.AppApplication.band_type;
        label12: {
            label10: {
                label11: {
                    if (i2 <= 29) {
                        break label11;
                    }
                    if (com.wakeup.wearfit2.AppApplication.band_type < 100) {
                        break label10;
                    }
                }
                if (com.wakeup.wearfit2.AppApplication.band_type > 199) {
                    break label10;
                }
                android.util.Log.i(s, "1.0app \u624b\u73af");
                break label12;
            }
            android.util.Log.i(s, "2.0app \u624b\u73af");
            int i3 = ((Integer)this.mDatas.get(15)).intValue();
            label2: if (this.mDatas.size() < 17) {
                StringBuilder a1 = new StringBuilder();
                a1.append("\u957f\u5ea617\u4f4d\u4ee5\u4e0b \uff1a");
                a1.append(this.mDatas.size());
                android.util.Log.i(s, a1.toString());
                com.wakeup.wearfit2.AppApplication.hasContinueHr = false;
                com.wakeup.wearfit2.AppApplication.hasContinueHrD = false;
                com.wakeup.wearfit2.AppApplication.hasContinueHrE = false;
                com.wakeup.wearfit2.AppApplication.hasContinueHrF = false;
                com.wakeup.wearfit2.AppApplication.hasEcg = false;
                com.wakeup.wearfit2.AppApplication.ox10 = false;
                com.wakeup.wearfit2.AppApplication.ox0a = false;
                com.wakeup.wearfit2.AppApplication.ox30 = false;
            } else {
                StringBuilder a2 = new StringBuilder();
                a2.append("\u957f\u5ea617\u4f4d\u4ee5\u4e0a\u5305\u62ec17 \uff1a ");
                a2.append(this.mDatas.size());
                android.util.Log.i(s, a2.toString());
                if (this.mDatas.size() < 18) {
                    com.wakeup.wearfit2.util.SPUtils.putString(this.mContext, "0x02", "");
                    com.wakeup.wearfit2.util.SPUtils.putString(this.mContext, "0x01", "");
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, com.wakeup.wearfit2.util.SPUtils.HAS_JIUZUO_SETTING, false);
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "power_saving", false);
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, com.wakeup.wearfit2.util.SPUtils.HAS_HONGWAI_SETTING, false);
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, com.wakeup.wearfit2.util.SPUtils.JIACHANG_MESSAGE_LENGTH, false);
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "has_weather", false);
                } else {
                    int i4 = ((Integer)this.mDatas.get(17)).intValue();
                    if ((i4 & 1) != 1) {
                        com.wakeup.wearfit2.util.SPUtils.putString(this.mContext, "0x01", "");
                    } else {
                        com.wakeup.wearfit2.util.SPUtils.putString(this.mContext, "0x01", "0x01");
                    }
                    if ((i4 & 2) != 2) {
                        com.wakeup.wearfit2.util.SPUtils.putString(this.mContext, "0x02", "");
                    } else {
                        com.wakeup.wearfit2.util.SPUtils.putString(this.mContext, "0x02", "0x02");
                    }
                    if ((i4 & 4) != 4) {
                        android.util.Log.i(s, "\u6ca1\u6709\u4e45\u5750\u63d0\u9192\u8bbe\u7f6e");
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, com.wakeup.wearfit2.util.SPUtils.HAS_JIUZUO_SETTING, false);
                    } else {
                        android.util.Log.i(s, "\u6709\u4e45\u5750\u63d0\u9192\u8bbe\u7f6e");
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, com.wakeup.wearfit2.util.SPUtils.HAS_JIUZUO_SETTING, true);
                    }
                    if ((i4 & 8) != 8) {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "power_saving", false);
                    } else {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "power_saving", true);
                    }
                    if ((i4 & 16) != 16) {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, com.wakeup.wearfit2.util.SPUtils.HAS_HONGWAI_SETTING, false);
                    } else {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, com.wakeup.wearfit2.util.SPUtils.HAS_HONGWAI_SETTING, true);
                    }
                    if ((i4 & 32) != 32) {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, com.wakeup.wearfit2.util.SPUtils.JIACHANG_MESSAGE_LENGTH, false);
                    } else {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, com.wakeup.wearfit2.util.SPUtils.JIACHANG_MESSAGE_LENGTH, true);
                    }
                    if ((i4 & 64) != 64) {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "has_weather", false);
                    } else {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "has_weather", true);
                    }
                    if ((i4 & 128) != 128) {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "has_contact", false);
                    } else {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "has_contact", true);
                    }
                }
                int i5 = ((Integer)this.mDatas.get(0)).intValue();
                label9: {
                    if (i5 != 171) {
                        break label9;
                    }
                    if (((Integer)this.mDatas.get(16)).intValue() != 12) {
                        break label9;
                    }
                    com.wakeup.wearfit2.AppApplication.hasEcg = true;
                    com.wakeup.wearfit2.AppApplication.hasContinueHr = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrD = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrE = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrF = false;
                    com.wakeup.wearfit2.AppApplication.ox10 = false;
                    com.wakeup.wearfit2.AppApplication.ox0a = false;
                    com.wakeup.wearfit2.AppApplication.ox30 = false;
                    android.util.Log.i(s, "\u5fc3\u7535\u624b\u73af");
                    break label2;
                }
                int i6 = ((Integer)this.mDatas.get(0)).intValue();
                label8: {
                    if (i6 != 171) {
                        break label8;
                    }
                    if (((Integer)this.mDatas.get(16)).intValue() != 11) {
                        break label8;
                    }
                    com.wakeup.wearfit2.AppApplication.hasContinueHr = true;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrD = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrE = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrF = false;
                    com.wakeup.wearfit2.AppApplication.hasEcg = false;
                    com.wakeup.wearfit2.AppApplication.ox10 = false;
                    com.wakeup.wearfit2.AppApplication.ox0a = false;
                    com.wakeup.wearfit2.AppApplication.ox30 = false;
                    com.wakeup.wearfit2.AppApplication.showHeartRateWarn = (i3 & 16) == 16;
                    StringBuilder a3 = new StringBuilder();
                    a3.append("\u8fde\u7eed\u5fc3\u7387\u624b\u73af---");
                    a3.append(com.wakeup.wearfit2.AppApplication.showHeartRateWarn ? "\u6709\u5fc3\u7387\u62a5\u8b66" : "\u6ca1\u6709\u5fc3\u7387\u62a5\u8b66");
                    android.util.Log.i(s, a3.toString());
                    break label2;
                }
                int i7 = ((Integer)this.mDatas.get(0)).intValue();
                label7: {
                    if (i7 != 171) {
                        break label7;
                    }
                    if (((Integer)this.mDatas.get(16)).intValue() != 13) {
                        break label7;
                    }
                    com.wakeup.wearfit2.AppApplication.hasContinueHrD = true;
                    com.wakeup.wearfit2.AppApplication.hasContinueHr = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrE = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrF = false;
                    com.wakeup.wearfit2.AppApplication.hasEcg = false;
                    com.wakeup.wearfit2.AppApplication.ox10 = false;
                    com.wakeup.wearfit2.AppApplication.ox0a = false;
                    com.wakeup.wearfit2.AppApplication.ox30 = false;
                    break label2;
                }
                int i8 = ((Integer)this.mDatas.get(0)).intValue();
                label6: {
                    if (i8 != 171) {
                        break label6;
                    }
                    if (((Integer)this.mDatas.get(16)).intValue() != 14) {
                        break label6;
                    }
                    com.wakeup.wearfit2.AppApplication.hasContinueHrE = true;
                    com.wakeup.wearfit2.AppApplication.hasContinueHr = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrD = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrF = false;
                    com.wakeup.wearfit2.AppApplication.hasEcg = false;
                    com.wakeup.wearfit2.AppApplication.ox10 = false;
                    com.wakeup.wearfit2.AppApplication.ox0a = false;
                    com.wakeup.wearfit2.AppApplication.ox30 = false;
                    break label2;
                }
                int i9 = ((Integer)this.mDatas.get(0)).intValue();
                label5: {
                    if (i9 != 171) {
                        break label5;
                    }
                    if (((Integer)this.mDatas.get(16)).intValue() != 15) {
                        break label5;
                    }
                    com.wakeup.wearfit2.AppApplication.hasContinueHrF = true;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrE = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHr = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrD = false;
                    com.wakeup.wearfit2.AppApplication.hasEcg = false;
                    com.wakeup.wearfit2.AppApplication.ox10 = false;
                    com.wakeup.wearfit2.AppApplication.ox0a = false;
                    com.wakeup.wearfit2.AppApplication.ox30 = false;
                    break label2;
                }
                int i10 = ((Integer)this.mDatas.get(0)).intValue();
                label4: {
                    if (i10 != 171) {
                        break label4;
                    }
                    if (((Integer)this.mDatas.get(16)).intValue() != 16) {
                        break label4;
                    }
                    com.wakeup.wearfit2.AppApplication.hasContinueHr = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrD = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrE = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrF = false;
                    com.wakeup.wearfit2.AppApplication.hasEcg = false;
                    com.wakeup.wearfit2.AppApplication.ox10 = true;
                    com.wakeup.wearfit2.AppApplication.ox0a = false;
                    com.wakeup.wearfit2.AppApplication.ox30 = false;
                    break label2;
                }
                int i11 = ((Integer)this.mDatas.get(0)).intValue();
                label3: {
                    if (i11 != 171) {
                        break label3;
                    }
                    if (((Integer)this.mDatas.get(16)).intValue() != 10) {
                        break label3;
                    }
                    com.wakeup.wearfit2.AppApplication.hasContinueHr = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrD = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrE = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrF = false;
                    com.wakeup.wearfit2.AppApplication.hasEcg = false;
                    com.wakeup.wearfit2.AppApplication.ox10 = false;
                    com.wakeup.wearfit2.AppApplication.ox0a = true;
                    com.wakeup.wearfit2.AppApplication.ox30 = false;
                    break label2;
                }
                int i12 = ((Integer)this.mDatas.get(0)).intValue();
                label0: {
                    label1: {
                        if (i12 != 171) {
                            break label1;
                        }
                        if (((Integer)this.mDatas.get(16)).intValue() == 48) {
                            break label0;
                        }
                    }
                    com.wakeup.wearfit2.AppApplication.hasContinueHr = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrD = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrE = false;
                    com.wakeup.wearfit2.AppApplication.hasContinueHrF = false;
                    com.wakeup.wearfit2.AppApplication.hasEcg = false;
                    com.wakeup.wearfit2.AppApplication.ox10 = false;
                    com.wakeup.wearfit2.AppApplication.ox0a = false;
                    com.wakeup.wearfit2.AppApplication.ox30 = false;
                    break label2;
                }
                com.wakeup.wearfit2.AppApplication.hasContinueHr = false;
                com.wakeup.wearfit2.AppApplication.hasContinueHrD = false;
                com.wakeup.wearfit2.AppApplication.hasContinueHrE = false;
                com.wakeup.wearfit2.AppApplication.hasContinueHrF = false;
                com.wakeup.wearfit2.AppApplication.hasEcg = false;
                com.wakeup.wearfit2.AppApplication.ox10 = false;
                com.wakeup.wearfit2.AppApplication.ox0a = false;
                com.wakeup.wearfit2.AppApplication.ox30 = true;
            }
            int i13 = i3 & 1;
            StringBuilder a4 = new StringBuilder();
            a4.append("\u662f\u5426\u6709\u8bbe\u7f6e\u6b65\u957f");
            a4.append(i13);
            android.util.Log.i(s, a4.toString());
            int i14 = i3 >> 1 & 1;
            StringBuilder a5 = new StringBuilder();
            a5.append("\u662f\u5426\u6709\u7761\u7720\u533a\u95f4");
            a5.append(i14);
            android.util.Log.i(s, a5.toString());
            int i15 = i3 >> 2 & 1;
            StringBuilder a6 = new StringBuilder();
            a6.append("\u662f\u5426\u670912\u5c0f\u65f6\u5236");
            a6.append(i15);
            android.util.Log.i(s, a6.toString());
            int i16 = i3 >> 3 & 1;
            StringBuilder a7 = new StringBuilder();
            a7.append("\u662f\u5426\u6709\u5fae\u4fe1\u8fd0\u52a8");
            a7.append(i16);
            android.util.Log.i(s, a7.toString());
            int i17 = i3 >> 4 & 1;
            StringBuilder a8 = new StringBuilder();
            a8.append("\u662f\u5426\u6709\u5fc3\u7387\u62a5\u8b66");
            a8.append(i17);
            android.util.Log.i(s, a8.toString());
            if ((i3 & 32) != 32) {
                com.wakeup.wearfit2.AppApplication.isHanTianXia = false;
            } else {
                com.wakeup.wearfit2.AppApplication.isHanTianXia = true;
            }
            if ((i3 & 128) != 128) {
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "support_jingqi", false);
            } else {
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "support_jingqi", true);
            }
            StringBuilder a9 = new StringBuilder();
            a9.append("\u662f\u5426\u6c49\u5929\u4e0b");
            a9.append(com.wakeup.wearfit2.AppApplication.isHanTianXia);
            android.util.Log.i(s, a9.toString());
            com.wakeup.wearfit2.AppApplication.isShowSteplength = i13 == 1;
            com.wakeup.wearfit2.AppApplication.isShowSleepInterval = (i3 & 2) == 2;
            com.wakeup.wearfit2.AppApplication.isShowHourly = (i3 & 4) == 4;
            com.wakeup.wearfit2.AppApplication.showWechat = (i3 & 8) != 8;
            com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, com.wakeup.wearfit2.util.SPUtils.HAS_ECG, com.wakeup.wearfit2.AppApplication.hasEcg);
            com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "has_continue_hr", com.wakeup.wearfit2.AppApplication.hasContinueHr);
            com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "has_continue_hr_d", com.wakeup.wearfit2.AppApplication.hasContinueHrD);
            com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "has_continue_hr_e", com.wakeup.wearfit2.AppApplication.hasContinueHrE);
            com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "has_continue_hr_f", com.wakeup.wearfit2.AppApplication.hasContinueHrF);
            com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "0x10", com.wakeup.wearfit2.AppApplication.ox10);
            if ("Smart Fit".equals((Object)com.wakeup.wearfit2.util.SPUtils.getString(this.mContext, com.wakeup.wearfit2.util.SPUtils.DEVICE_NAME, ""))) {
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "0x10", true);
            } else {
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "0x10", false);
            }
            com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "OX0a", com.wakeup.wearfit2.AppApplication.ox0a);
            com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "OX30", com.wakeup.wearfit2.AppApplication.ox30);
            com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "do_step_length", com.wakeup.wearfit2.AppApplication.isShowSteplength);
            com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "do_sleep", com.wakeup.wearfit2.AppApplication.isShowSleepInterval);
            com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "do_time_12", com.wakeup.wearfit2.AppApplication.isShowHourly);
            com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "do_wechat", com.wakeup.wearfit2.AppApplication.showWechat);
            com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "do_show_heartrate_warn", com.wakeup.wearfit2.AppApplication.showHeartRateWarn);
            StringBuilder a10 = new StringBuilder();
            a10.append("hasEcg: ");
            a10.append(com.wakeup.wearfit2.AppApplication.hasEcg);
            a10.append("\nhasContinueHr: ");
            a10.append(com.wakeup.wearfit2.AppApplication.hasContinueHr);
            a10.append("\nhasContinueHrD: ");
            a10.append(com.wakeup.wearfit2.AppApplication.hasContinueHrD);
            a10.append("\nhasContinueHrE: ");
            a10.append(com.wakeup.wearfit2.AppApplication.hasContinueHrE);
            a10.append("\nhasContinueHrF: ");
            a10.append(com.wakeup.wearfit2.AppApplication.hasContinueHrF);
            a10.append("\n0x10: ");
            a10.append(com.wakeup.wearfit2.AppApplication.ox10);
            a10.append("\n0x0a: ");
            a10.append(com.wakeup.wearfit2.AppApplication.ox0a);
            a10.append("\n0x30: ");
            a10.append(com.wakeup.wearfit2.AppApplication.ox30);
            a10.append("\nisShowSteplength: ");
            a10.append(com.wakeup.wearfit2.AppApplication.isShowSteplength);
            a10.append("\nisShowSleepInterval: ");
            a10.append(com.wakeup.wearfit2.AppApplication.isShowSleepInterval);
            a10.append("\nisShowHourly: ");
            a10.append(com.wakeup.wearfit2.AppApplication.isShowHourly);
            a10.append("\nshowWechat: ");
            a10.append(com.wakeup.wearfit2.AppApplication.showWechat);
            a10.append("\nshowWechat: ");
            a10.append(com.wakeup.wearfit2.AppApplication.showWechat);
            a10.append("\nshowHeartRateWarn: ");
            a10.append(com.wakeup.wearfit2.AppApplication.showHeartRateWarn);
            android.util.Log.i(s, a10.toString());
            if (this.mDatas.size() <= 18) {
                com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "set_watch_bg", 0);
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "show_ping_bao", false);
                com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_width", 0);
                com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_height", 0);
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "SHOW_PING_BAO_SWITCH", false);
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "is_fang_ping", false);
            } else {
                int i18 = ((Integer)this.mDatas.get(18)).intValue();
                StringBuilder a11 = new StringBuilder();
                a11.append("I: ");
                a11.append(i18);
                android.util.Log.i(s, a11.toString());
                com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "set_watch_bg", i18);
                switch(i18) {
                    case 14: {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "show_ping_bao", true);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_width", 360);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_height", 360);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "SHOW_PING_BAO_SWITCH", true);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "is_fang_ping", false);
                        break;
                    }
                    case 13: {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "show_ping_bao", true);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_width", 360);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_height", 360);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "SHOW_PING_BAO_SWITCH", false);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "is_fang_ping", false);
                        break;
                    }
                    case 12: {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "show_ping_bao", true);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_width", 240);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_height", 283);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "SHOW_PING_BAO_SWITCH", true);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "is_fang_ping", true);
                        break;
                    }
                    case 11: {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "show_ping_bao", true);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_width", 240);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_height", 283);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "SHOW_PING_BAO_SWITCH", false);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "is_fang_ping", true);
                        break;
                    }
                    case 10: {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "show_ping_bao", true);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_width", 240);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_height", 288);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "SHOW_PING_BAO_SWITCH", true);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "is_fang_ping", true);
                        break;
                    }
                    case 9: {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "show_ping_bao", true);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_width", 240);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_height", 288);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "SHOW_PING_BAO_SWITCH", false);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "is_fang_ping", true);
                        break;
                    }
                    case 8: {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "show_ping_bao", true);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_width", 240);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_height", 240);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "SHOW_PING_BAO_SWITCH", true);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "is_fang_ping", false);
                        break;
                    }
                    case 7: {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "show_ping_bao", true);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_width", 240);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_height", 240);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "SHOW_PING_BAO_SWITCH", false);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "is_fang_ping", false);
                        break;
                    }
                    case 6: {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "show_ping_bao", true);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_width", 135);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_height", 240);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "SHOW_PING_BAO_SWITCH", true);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "is_fang_ping", true);
                        break;
                    }
                    case 5: {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "show_ping_bao", true);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_width", 135);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_height", 240);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "SHOW_PING_BAO_SWITCH", false);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "is_fang_ping", true);
                        break;
                    }
                    case 4: {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "show_ping_bao", true);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_width", 80);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_height", 160);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "SHOW_PING_BAO_SWITCH", true);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "is_fang_ping", true);
                        break;
                    }
                    case 3: {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "show_ping_bao", true);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_width", 240);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_height", 240);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "SHOW_PING_BAO_SWITCH", true);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "is_fang_ping", true);
                        break;
                    }
                    case 2: {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "show_ping_bao", true);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_width", 80);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_height", 160);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "SHOW_PING_BAO_SWITCH", false);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "is_fang_ping", true);
                        break;
                    }
                    case 1: {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "show_ping_bao", true);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_width", 240);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_height", 240);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "SHOW_PING_BAO_SWITCH", false);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "is_fang_ping", true);
                        break;
                    }
                    case 0: {
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "show_ping_bao", false);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_width", 0);
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.mContext, "screen_height", 0);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "SHOW_PING_BAO_SWITCH", false);
                        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "is_fang_ping", false);
                        break;
                    }
                }
            }
            if (this.mDatas.size() <= 19) {
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "have_zh", true);
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "have_en", true);
            } else {
                int i19 = ((Integer)this.mDatas.get(19)).intValue();
                StringBuilder a12 = new StringBuilder();
                a12.append("\u7b2c19\u4f4d\uff1a");
                a12.append(i19);
                android.util.Log.i(s, a12.toString());
                if ((i19 & 1) != 1) {
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "have_zh", false);
                } else {
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "have_zh", true);
                }
                if ((i19 & 2) != 2) {
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "have_en", false);
                } else {
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "have_en", true);
                }
                if ((i19 & 4) != 4) {
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "have_it", false);
                } else {
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "have_it", true);
                }
                if ((i19 & 8) != 8) {
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "have_es", false);
                } else {
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "have_es", true);
                }
                if ((i19 & 16) != 16) {
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "have_pt", false);
                } else {
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "have_pt", true);
                }
                if ((i19 & 32) != 32) {
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "have_ru", false);
                } else {
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "have_ru", true);
                }
                if ((i19 & 64) != 64) {
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "have_ja", false);
                } else {
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "have_ja", true);
                }
                if ((i19 & 128) != 128) {
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "new_language", false);
                } else {
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.mContext, "new_language", true);
                }
            }
        }
        org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.BAND_VERSION_GOT));
        StringBuilder a13 = new StringBuilder();
        a13.append("\u624b\u73af\u7248\u672c\u4fe1\u606f ota_version: ");
        a13.append(com.wakeup.wearfit2.AppApplication.ota_version);
        a13.append(" band_type: ");
        a13.append(com.wakeup.wearfit2.AppApplication.band_type);
        android.util.Log.d(s, a13.toString());
        com.wakeup.wearfit2.util.SPUtils.putFloat(this.mContext, "ota_version", com.wakeup.wearfit2.AppApplication.ota_version);
        com.wakeup.wearfit2.util.SPUtils.putFloat(this.mContext, "band_type", (float)com.wakeup.wearfit2.AppApplication.band_type);
        int i20 = ((Integer)this.mDatas.get(9)).intValue();
        int i21 = ((Integer)this.mDatas.get(10)).intValue();
        if ((i20 & 128) != 128) {
            com.wakeup.wearfit2.AppApplication.haveSwitchWatch = false;
        } else {
            com.wakeup.wearfit2.AppApplication.haveSwitchWatch = true;
            com.wakeup.wearfit2.AppApplication.SwitchWatchType = (i20 - 128) * 256 + i21;
        }
    }
    
    Boolean isLimitBand(java.util.List a, String s, String s0, int i) {
        Object a0 = a;
        int i0 = 0;
        boolean b = false;
        for(; i0 < ((java.util.List)a0).size(); i0 = i0 + 1) {
            if (((com.wakeup.wearfit2.bean.LimitDevice)((java.util.List)a0).get(i0)).getName().equals((Object)s) && ((com.wakeup.wearfit2.bean.LimitDevice)((java.util.List)a0).get(i0)).getVersion().equals((Object)s0) && ((com.wakeup.wearfit2.bean.LimitDevice)((java.util.List)a0).get(i0)).getType() == i) {
                b = true;
            }
        }
        return Boolean.valueOf(b);
    }
}
