package com.wakeup.wearfit2;

class AppApplication$BLEStatusChangeBroadcastReceiver extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.AppApplication this$0;
    
    AppApplication$BLEStatusChangeBroadcastReceiver(com.wakeup.wearfit2.AppApplication a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        int i = 0;
        String s = a0.getAction();
        s.hashCode();
        switch(s.hashCode()) {
            case 2116862345: {
                i = (s.equals((Object)"android.bluetooth.device.action.BOND_STATE_CHANGED")) ? 3 : -1;
                break;
            }
            case 1752867454: {
                i = (s.equals((Object)"com.wakeup.wearfit2.BROADCAST_DATA_AVAILABLE")) ? 2 : -1;
                break;
            }
            case 918932346: {
                i = (s.equals((Object)"com.wakeup.wearfit2.BROADCAST_CONNECTION_STATE")) ? 1 : -1;
                break;
            }
            case -19011148: {
                i = (s.equals((Object)"android.intent.action.LOCALE_CHANGED")) ? 0 : -1;
                break;
            }
            default: {
                i = -1;
            }
        }
        switch(i) {
            case 3: {
                int i0 = a0.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1 << -1);
                int i1 = a0.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", -1 << -1);
                String s0 = com.wakeup.wearfit2.AppApplication.TAG;
                StringBuilder a1 = new StringBuilder();
                a1.append("\u7ecf\u5178\u84dd\u7259\u914d\u5bf9\u72b6\u6001\u6539\u53d8    state = ");
                a1.append(i0);
                a1.append("    prevState = ");
                a1.append(i1);
                android.util.Log.i(s0, a1.toString());
                android.bluetooth.BluetoothDevice a2 = (android.bluetooth.BluetoothDevice)(Object)a0.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                a2.getName();
                String s1 = a2.getAddress();
                String s2 = com.wakeup.wearfit2.util.SPUtils.getString(this.this$0.getApplicationContext(), "classic_mac_address", "");
                label45: {
                    label46: {
                        if (i0 != 12) {
                            break label46;
                        }
                        if (i1 == 11) {
                            break label45;
                        }
                    }
                    if (i0 != 10) {
                        break;
                    }
                    if (i1 != 12) {
                        break;
                    }
                    android.util.Log.i(com.wakeup.wearfit2.AppApplication.TAG, "\u89e3\u9664\u7ecf\u5178\u84dd\u7259\u914d\u5bf9");
                    if (!s1.equals((Object)s2)) {
                        break;
                    }
                    android.content.Intent a3 = new android.content.Intent("USER_UNBIND_DEVICE");
                    this.this$0.sendBroadcast(a3);
                    com.wakeup.wearfit2.AppApplication.batteryPercent = 0;
                    com.wakeup.wearfit2.util.SPUtils.putString(this.this$0.getApplicationContext(), com.wakeup.wearfit2.util.SPUtils.DEVICE_ADDRESS, "");
                    com.wakeup.wearfit2.util.SPUtils.putString(this.this$0.getApplicationContext(), com.wakeup.wearfit2.util.SPUtils.DEVICE_NAME, "");
                    com.wakeup.wearfit2.util.SPUtils.putString(this.this$0.getApplicationContext(), "classic_mac_address", "");
                    com.wakeup.wearfit2.util.SPUtils.putBoolean(this.this$0.getApplicationContext(), "has_contact", false);
                    break;
                }
                android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, "\u914d\u5bf9\u6210\u529f\uff0c\u5f00\u59cb\u8fde\u63a5a2dp");
                if (!s1.equals((Object)s2)) {
                    break;
                }
                com.wakeup.wearfit2.AppApplication.access$900(this.this$0, a2);
                break;
            }
            case 2: {
                Object a4 = null;
                byte[] a5 = a0.getByteArrayExtra("com.wakeup.wearfit2.EXTRA_DATA");
                if (a5 == null) {
                    return;
                }
                String s3 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a5);
                java.util.List a6 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToArrayList(a5);
                if (((Integer)a6.get(0)).intValue() != 171) {
                    a4 = a6;
                } else if (((Integer)a6.get(4)).intValue() != 166) {
                    a4 = a6;
                } else {
                    String[] a7 = s3.split("\\s+");
                    StringBuilder a8 = new StringBuilder();
                    int i2 = a7.length - 1;
                    a4 = a6;
                    for(; i2 >= 0; i2 = i2 + -1) {
                        if (i2 > 7) {
                            a8.append(a7[i2].toUpperCase());
                            if (i2 != 8) {
                                a8.append(":");
                            }
                        }
                    }
                    String s4 = a8.toString();
                    String s5 = com.wakeup.wearfit2.AppApplication.TAG;
                    StringBuilder a9 = new StringBuilder();
                    a9.append("\u7ecf\u5178\u84dd\u7259mac\u5730\u5740: ");
                    a9.append(s4);
                    android.util.Log.i(s5, a9.toString());
                    com.wakeup.wearfit2.util.SPUtils.putString(this.this$0.getApplicationContext(), "classic_mac_address", s4);
                    android.content.Intent a10 = new android.content.Intent("connect_classic_bluetooth");
                    this.this$0.sendBroadcast(a10);
                }
                if (((Integer)((java.util.List)a4).get(0)).intValue() == 171 && ((Integer)((java.util.List)a4).get(4)).intValue() == 146) {
                    new com.wakeup.wearfit2.manager.VersionManager((java.util.List)a4, this.this$0.getApplicationContext()).handleVersion();
                    com.wakeup.wearfit2.AppApplication.access$300(this.this$0).sendEmptyMessageDelayed(1, 1000L);
                }
                if (((Integer)((java.util.List)a4).get(0)).intValue() == 171 && ((Integer)((java.util.List)a4).get(4)).intValue() == 145) {
                    com.wakeup.wearfit2.AppApplication.batteryPercent = ((Integer)((java.util.List)a4).get(7)).intValue();
                    String s6 = com.wakeup.wearfit2.AppApplication.TAG;
                    StringBuilder a11 = new StringBuilder();
                    a11.append("\u7535\u91cf--");
                    a11.append(com.wakeup.wearfit2.AppApplication.batteryPercent);
                    android.util.Log.i(s6, a11.toString());
                    org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.BAND_BATTERY_INFO));
                }
                int i3 = ((Integer)((java.util.List)a4).get(0)).intValue();
                label44: {
                    label43: {
                        {
                            if (i3 != 171) {
                                break label44;
                            }
                            if (((Integer)((java.util.List)a4).get(4)).intValue() != 81) {
                                break label44;
                            }
                            if (((Integer)((java.util.List)a4).get(5)).intValue() == 8) {
                                break label43;
                            }
                        }
                        break label44;
                    }
                    com.wakeup.wearfit2.model.StepAndSleepModel a12 = new com.wakeup.wearfit2.model.StepAndSleepModel();
                    a12.setStepCount((((Integer)((java.util.List)a4).get(6)).intValue() << 16) + (((Integer)((java.util.List)a4).get(7)).intValue() << 8) + ((Integer)((java.util.List)a4).get(8)).intValue());
                    a12.setCalory((((Integer)((java.util.List)a4).get(9)).intValue() << 16) + (((Integer)((java.util.List)a4).get(10)).intValue() << 8) + ((Integer)((java.util.List)a4).get(11)).intValue());
                    a12.setShallow_sleep_time((long)((((Integer)((java.util.List)a4).get(12)).intValue() * 60 + ((Integer)((java.util.List)a4).get(13)).intValue()) * 60 * 1000));
                    a12.setDeep_sleep_time((long)((((Integer)((java.util.List)a4).get(14)).intValue() * 60 + ((Integer)((java.util.List)a4).get(15)).intValue()) * 60 * 1000));
                    a12.setSleep_time((long)((((Integer)((java.util.List)a4).get(12)).intValue() * 60 + ((Integer)((java.util.List)a4).get(13)).intValue() + ((Integer)((java.util.List)a4).get(14)).intValue() * 60 + ((Integer)((java.util.List)a4).get(15)).intValue()) * 1000 * 60));
                    a12.setWakeup_times(((Integer)((java.util.List)a4).get(16)).intValue());
                    a12.setTimeInMillis(System.currentTimeMillis());
                    a12.setMacAddress(com.wakeup.wearfit2.AppApplication.device_address);
                    if (a12.save()) {
                        android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, "stepAndSleepModel save success");
                        com.wakeup.wearfit2.model.event.BaseEvent a13 = new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.STEP_AND_SLEEP_CHANGE);
                        a13.setmObject((Object)a12);
                        org.greenrobot.eventbus.EventBus.getDefault().post((Object)a13);
                    } else {
                        android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, "stepAndSleepModel save fail");
                    }
                }
                int i4 = ((Integer)((java.util.List)a4).get(0)).intValue();
                label32: {
                    com.wakeup.wearfit2.model.DBModel a14 = null;
                    label30: {
                        {
                            int i5 = 0;
                            label42: {
                                {
                                    if (i4 != 171) {
                                        break label32;
                                    }
                                    if (((Integer)((java.util.List)a4).get(4)).intValue() != 81) {
                                        break label32;
                                    }
                                    if (((Integer)((java.util.List)a4).get(5)).intValue() == 32) {
                                        break label42;
                                    }
                                }
                                break label32;
                            }
                            com.wakeup.wearfit2.AppApplication.isEcg = false;
                            android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, "\u7b2c\u4e00\u4e2a\u5305");
                            android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, a4.toString());
                            a14 = new com.wakeup.wearfit2.model.DBModel();
                            com.wakeup.wearfit2.AppApplication.dbModel = a14;
                            int i6 = ((Integer)((java.util.List)a4).get(6)).intValue() + 2000;
                            int i7 = ((Integer)((java.util.List)a4).get(7)).intValue();
                            int i8 = ((Integer)((java.util.List)a4).get(8)).intValue();
                            int i9 = ((Integer)((java.util.List)a4).get(9)).intValue();
                            StringBuilder a15 = new StringBuilder();
                            a15.append(i6);
                            Object[] a16 = new Object[1];
                            a16[0] = Integer.valueOf(i7);
                            a15.append(String.format("%02d", a16));
                            Object[] a17 = new Object[1];
                            a17[0] = Integer.valueOf(i8);
                            a15.append(String.format("%02d", a17));
                            Object[] a18 = new Object[1];
                            a18[0] = Integer.valueOf(i9);
                            a15.append(String.format("%02d", a18));
                            a15.append(0);
                            long j = com.wakeup.wearfit2.util.DateUtils.getMilliSecondFromTime(a15.toString());
                            int i10 = (((Integer)((java.util.List)a4).get(10)).intValue() << 16) + (((Integer)((java.util.List)a4).get(11)).intValue() << 8) + ((Integer)((java.util.List)a4).get(12)).intValue();
                            int i11 = (((Integer)((java.util.List)a4).get(13)).intValue() << 16) + (((Integer)((java.util.List)a4).get(14)).intValue() << 8) + ((Integer)((java.util.List)a4).get(15)).intValue();
                            int i12 = ((Integer)((java.util.List)a4).get(16)).intValue();
                            int i13 = ((Integer)((java.util.List)a4).get(17)).intValue();
                            int i14 = ((Integer)((java.util.List)a4).get(18)).intValue();
                            int i15 = ((Integer)((java.util.List)a4).get(19)).intValue();
                            label40: if (i12 == 0 && i13 == 0) {
                                label41: {
                                    if (i14 == 0) {
                                        break label41;
                                    }
                                    if (i15 != 0) {
                                        break label40;
                                    }
                                }
                                return;
                            }
                            a14.setMacAddress(com.wakeup.wearfit2.AppApplication.device_address);
                            a14.setTimeInMillis(j + 3600000L);
                            a14.setStepCount(i10);
                            a14.setCalory(i11);
                            a14.setHeartRate(i12);
                            a14.setBloodOxygen(i13);
                            a14.setBloodPressure_high(i14);
                            a14.setBloodPressure_low(i15);
                            a14.setType(1);
                            label36: {
                                label39: {
                                    if (i9 < 6) {
                                        break label39;
                                    }
                                    if (i9 >= 11) {
                                        break label39;
                                    }
                                    i5 = 5;
                                    break label36;
                                }
                                label37: {
                                    label38: {
                                        if (i9 >= 11) {
                                            break label38;
                                        }
                                        break label37;
                                    }
                                    if (i9 >= 18) {
                                        break label37;
                                    }
                                    i5 = 10;
                                    break label36;
                                }
                                label34: {
                                    label35: {
                                        if (i9 < 18) {
                                            break label35;
                                        }
                                        if (i9 < 24) {
                                            break label34;
                                        }
                                    }
                                    i5 = 30;
                                    break label36;
                                }
                                i5 = 20;
                            }
                            int i16 = i5 + (int)Math.sqrt((double)i10) / 2;
                            a14.setTiredValue(i16);
                            label33: {
                                if (i16 >= 30) {
                                    break label33;
                                }
                                a14.setTiredType(1);
                                break label32;
                            }
                            label31: {
                                if (i16 < 30) {
                                    break label31;
                                }
                                if (i16 >= 60) {
                                    break label31;
                                }
                                a14.setTiredType(2);
                                break label32;
                            }
                            if (i16 < 60) {
                                break label30;
                            }
                            if (i16 >= 80) {
                                break label30;
                            }
                            a14.setTiredType(3);
                        }
                        break label32;
                    }
                    a14.setTiredType(4);
                }
                int i17 = ((Integer)((java.util.List)a4).get(0)).intValue();
                label28: {
                    label29: {
                        {
                            if (i17 != 171) {
                                break label28;
                            }
                            if (((Integer)((java.util.List)a4).get(4)).intValue() != 81) {
                                break label28;
                            }
                            if (((Integer)((java.util.List)a4).get(5)).intValue() == 22) {
                                break label29;
                            }
                        }
                        break label28;
                    }
                    android.util.Log.e("liu0916", "\u8fd0\u52a8\u6a21\u5f0f");
                    int i18 = ((Integer)((java.util.List)a4).get(6)).intValue();
                    int i19 = ((Integer)((java.util.List)a4).get(7)).intValue() + 2000;
                    int i20 = ((Integer)((java.util.List)a4).get(8)).intValue();
                    int i21 = ((Integer)((java.util.List)a4).get(9)).intValue();
                    int i22 = ((Integer)((java.util.List)a4).get(10)).intValue();
                    int i23 = ((Integer)((java.util.List)a4).get(11)).intValue();
                    StringBuilder a19 = new StringBuilder();
                    a19.append(i19);
                    Object[] a20 = new Object[1];
                    a20[0] = Integer.valueOf(i20);
                    a19.append(String.format("%02d", a20));
                    Object[] a21 = new Object[1];
                    a21[0] = Integer.valueOf(i21);
                    a19.append(String.format("%02d", a21));
                    Object[] a22 = new Object[1];
                    a22[0] = Integer.valueOf(i22);
                    a19.append(String.format("%02d", a22));
                    Object[] a23 = new Object[1];
                    a23[0] = Integer.valueOf(i23);
                    a19.append(String.format("%02d", a23));
                    long j0 = com.wakeup.wearfit2.util.DateUtils.getMilliSecondFromTime(a19.toString());
                    int i24 = ((Integer)((java.util.List)a4).get(12)).intValue() * 16 * 16 + ((Integer)((java.util.List)a4).get(13)).intValue();
                    int i25 = ((Integer)((java.util.List)a4).get(14)).intValue() * 16 * 16 + ((Integer)((java.util.List)a4).get(15)).intValue();
                    int i26 = ((Integer)((java.util.List)a4).get(16)).intValue() * 16 * 16 + ((Integer)((java.util.List)a4).get(17)).intValue();
                    int i27 = ((Integer)((java.util.List)a4).get(18)).intValue();
                    com.wakeup.wearfit2.bean.BandSportModel a24 = new com.wakeup.wearfit2.bean.BandSportModel();
                    a24.setSportType(i18);
                    a24.setTimestamp(j0);
                    a24.setSportDuration(i24);
                    a24.setStep(i25);
                    a24.setKcal(i26);
                    a24.setHr(i27);
                    a24.setUpToService("No");
                    String[] a25 = new String[3];
                    a25[0] = "sportType = ? and timestamp = ?";
                    a25[1] = String.valueOf(i18);
                    a25[2] = String.valueOf(j0);
                    java.util.List a26 = org.litepal.LitePal.where(a25).find(com.wakeup.wearfit2.bean.BandSportModel.class);
                    label27: {
                        if (a26 == null) {
                            break label27;
                        }
                        boolean b = a26.isEmpty();
                        label26: {
                            if (!b) {
                                break label26;
                            }
                            break label27;
                        }
                        android.util.Log.e("liu0916", "bandSportModel\u91cd\u590d\u6570\u636e\uff0c\u4e0d\u9700\u8981\u4fdd\u5b58");
                        break label28;
                    }
                    if (a24.save()) {
                        StringBuilder a27 = new StringBuilder();
                        a27.append("bandSportModel\u6570\u636e\u4fdd\u5b58\u6210\u529f: ");
                        a27.append(new com.google.gson.Gson().toJson((Object)a24));
                        android.util.Log.d("liu0916", a27.toString());
                    } else {
                        android.util.Log.e("liu0916", "bandSportModel\u6570\u636e\u4fdd\u5b58\u5931\u8d25");
                    }
                }
                label23: if (((Integer)((java.util.List)a4).get(0)).intValue() == 171 && ((Integer)((java.util.List)a4).get(4)).intValue() == 81) {
                    if (((Integer)((java.util.List)a4).get(5)).intValue() != 17) {
                        if (((Integer)((java.util.List)a4).get(5)).intValue() != 18) {
                            if (((Integer)((java.util.List)a4).get(5)).intValue() != 20) {
                                if (((Integer)((java.util.List)a4).get(5)).intValue() != 19) {
                                    if (((Integer)((java.util.List)a4).get(5)).intValue() != 24) {
                                        if (((Integer)((java.util.List)a4).get(5)).intValue() == 33) {
                                            int i28 = ((Integer)((java.util.List)a4).get(6)).intValue() + 2000;
                                            int i29 = ((Integer)((java.util.List)a4).get(7)).intValue();
                                            int i30 = ((Integer)((java.util.List)a4).get(8)).intValue();
                                            int i31 = ((Integer)((java.util.List)a4).get(9)).intValue();
                                            StringBuilder a28 = new StringBuilder();
                                            a28.append(i28);
                                            Object[] a29 = new Object[1];
                                            a29[0] = Integer.valueOf(i29);
                                            a28.append(String.format("%02d", a29));
                                            Object[] a30 = new Object[1];
                                            a30[0] = Integer.valueOf(i30);
                                            a28.append(String.format("%02d", a30));
                                            Object[] a31 = new Object[1];
                                            a31[0] = Integer.valueOf(i31);
                                            a28.append(String.format("%02d", a31));
                                            a28.append(0);
                                            long j1 = com.wakeup.wearfit2.util.DateUtils.getMilliSecondFromTime(a28.toString());
                                            int i32 = ((Integer)((java.util.List)a4).get(10)).intValue();
                                            if (i32 != 0) {
                                                com.wakeup.wearfit2.bean.MianyiBean a32 = new com.wakeup.wearfit2.bean.MianyiBean((float)i32, j1);
                                                String[] a33 = new String[2];
                                                a33[0] = "timeInMillis=?";
                                                a33[1] = String.valueOf(a32.getTimeInMillis());
                                                a32.saveOrUpdate(a33);
                                            }
                                            int i33 = ((Integer)((java.util.List)a4).get(11)).intValue();
                                            int i34 = ((Integer)((java.util.List)a4).get(12)).intValue();
                                            if (i33 != 0) {
                                                com.wakeup.wearfit2.bean.BodyTempBean a34 = new com.wakeup.wearfit2.bean.BodyTempBean((float)i33 + (float)i34 / 10f, j1, com.wakeup.wearfit2.AppApplication.device_address);
                                                String[] a35 = new String[2];
                                                a35[0] = "timeInMillis=?";
                                                a35[1] = String.valueOf(a34.getTimeInMillis());
                                                a34.saveOrUpdate(a35);
                                            }
                                        }
                                    } else {
                                        int i35 = ((Integer)((java.util.List)a4).get(6)).intValue() + 2000;
                                        int i36 = ((Integer)((java.util.List)a4).get(7)).intValue();
                                        int i37 = ((Integer)((java.util.List)a4).get(8)).intValue();
                                        int i38 = ((Integer)((java.util.List)a4).get(9)).intValue();
                                        int i39 = ((Integer)((java.util.List)a4).get(10)).intValue();
                                        StringBuilder a36 = new StringBuilder();
                                        a36.append(i35);
                                        Object[] a37 = new Object[1];
                                        a37[0] = Integer.valueOf(i36);
                                        a36.append(String.format("%02d", a37));
                                        Object[] a38 = new Object[1];
                                        a38[0] = Integer.valueOf(i37);
                                        a36.append(String.format("%02d", a38));
                                        Object[] a39 = new Object[1];
                                        a39[0] = Integer.valueOf(i38);
                                        a36.append(String.format("%02d", a39));
                                        Object[] a40 = new Object[1];
                                        a40[0] = Integer.valueOf(i39);
                                        a36.append(String.format("%02d", a40));
                                        long j2 = com.wakeup.wearfit2.util.DateUtils.getMilliSecondFromTime(a36.toString());
                                        int i40 = ((Integer)((java.util.List)a4).get(11)).intValue();
                                        if (i40 != 0) {
                                            com.wakeup.wearfit2.bean.MianyiBean a41 = new com.wakeup.wearfit2.bean.MianyiBean((float)i40, j2);
                                            String[] a42 = new String[2];
                                            a42[0] = "timeInMillis=?";
                                            a42[1] = String.valueOf(a41.getTimeInMillis());
                                            a41.saveOrUpdate(a42);
                                        }
                                    }
                                } else {
                                    int i41 = ((Integer)((java.util.List)a4).get(6)).intValue() + 2000;
                                    int i42 = ((Integer)((java.util.List)a4).get(7)).intValue();
                                    int i43 = ((Integer)((java.util.List)a4).get(8)).intValue();
                                    int i44 = ((Integer)((java.util.List)a4).get(9)).intValue();
                                    int i45 = ((Integer)((java.util.List)a4).get(10)).intValue();
                                    StringBuilder a43 = new StringBuilder();
                                    a43.append(i41);
                                    Object[] a44 = new Object[1];
                                    a44[0] = Integer.valueOf(i42);
                                    a43.append(String.format("%02d", a44));
                                    Object[] a45 = new Object[1];
                                    a45[0] = Integer.valueOf(i43);
                                    a43.append(String.format("%02d", a45));
                                    Object[] a46 = new Object[1];
                                    a46[0] = Integer.valueOf(i44);
                                    a43.append(String.format("%02d", a46));
                                    Object[] a47 = new Object[1];
                                    a47[0] = Integer.valueOf(i45);
                                    a43.append(String.format("%02d", a47));
                                    long j3 = com.wakeup.wearfit2.util.DateUtils.getMilliSecondFromTime(a43.toString());
                                    int i46 = ((Integer)((java.util.List)a4).get(11)).intValue();
                                    int i47 = ((Integer)((java.util.List)a4).get(12)).intValue();
                                    if (i46 != 0) {
                                        com.wakeup.wearfit2.bean.BodyTempBean a48 = new com.wakeup.wearfit2.bean.BodyTempBean((float)i46 + (float)i47 / 10f, j3, com.wakeup.wearfit2.AppApplication.device_address);
                                        String[] a49 = new String[2];
                                        a49[0] = "timeInMillis=?";
                                        a49[1] = String.valueOf(a48.getTimeInMillis());
                                        a48.saveOrUpdate(a49);
                                    }
                                }
                            } else {
                                android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, "\u8840\u538b");
                                int i48 = ((Integer)((java.util.List)a4).get(6)).intValue() + 2000;
                                int i49 = ((Integer)((java.util.List)a4).get(7)).intValue();
                                int i50 = ((Integer)((java.util.List)a4).get(8)).intValue();
                                int i51 = ((Integer)((java.util.List)a4).get(9)).intValue();
                                int i52 = ((Integer)((java.util.List)a4).get(10)).intValue();
                                StringBuilder a50 = new StringBuilder();
                                a50.append(i48);
                                Object[] a51 = new Object[1];
                                a51[0] = Integer.valueOf(i49);
                                a50.append(String.format("%02d", a51));
                                Object[] a52 = new Object[1];
                                a52[0] = Integer.valueOf(i50);
                                a50.append(String.format("%02d", a52));
                                Object[] a53 = new Object[1];
                                a53[0] = Integer.valueOf(i51);
                                a50.append(String.format("%02d", a53));
                                Object[] a54 = new Object[1];
                                a54[0] = Integer.valueOf(i52);
                                a50.append(String.format("%02d", a54));
                                long j4 = com.wakeup.wearfit2.util.DateUtils.getMilliSecondFromTime(a50.toString());
                                int i53 = ((Integer)((java.util.List)a4).get(11)).intValue();
                                int i54 = ((Integer)((java.util.List)a4).get(12)).intValue();
                                com.wakeup.wearfit2.model.DBModel a55 = new com.wakeup.wearfit2.model.DBModel();
                                a55.setType(0);
                                a55.setBloodPressure_high(i53);
                                a55.setTimeInMillis(j4);
                                a55.setBloodPressure_low(i54);
                                a55.setMacAddress(com.wakeup.wearfit2.AppApplication.device_address);
                                if (i53 != 0 && i54 != 0 && a55.save()) {
                                    String s7 = com.wakeup.wearfit2.AppApplication.TAG;
                                    StringBuilder a56 = new StringBuilder();
                                    a56.append("\u8840\u538b\u4fdd\u5b58\u6210\u529f");
                                    a56.append(a55.toString());
                                    android.util.Log.d(s7, a56.toString());
                                    org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.SYNCING_DATA));
                                }
                            }
                        } else {
                            android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, "\u8840\u6c27");
                            int i55 = ((Integer)((java.util.List)a4).get(6)).intValue() + 2000;
                            int i56 = ((Integer)((java.util.List)a4).get(7)).intValue();
                            int i57 = ((Integer)((java.util.List)a4).get(8)).intValue();
                            int i58 = ((Integer)((java.util.List)a4).get(9)).intValue();
                            int i59 = ((Integer)((java.util.List)a4).get(10)).intValue();
                            StringBuilder a57 = new StringBuilder();
                            a57.append(i55);
                            Object[] a58 = new Object[1];
                            a58[0] = Integer.valueOf(i56);
                            a57.append(String.format("%02d", a58));
                            Object[] a59 = new Object[1];
                            a59[0] = Integer.valueOf(i57);
                            a57.append(String.format("%02d", a59));
                            Object[] a60 = new Object[1];
                            a60[0] = Integer.valueOf(i58);
                            a57.append(String.format("%02d", a60));
                            Object[] a61 = new Object[1];
                            a61[0] = Integer.valueOf(i59);
                            a57.append(String.format("%02d", a61));
                            long j5 = com.wakeup.wearfit2.util.DateUtils.getMilliSecondFromTime(a57.toString());
                            int i60 = ((Integer)((java.util.List)a4).get(11)).intValue();
                            com.wakeup.wearfit2.model.DBModel a62 = new com.wakeup.wearfit2.model.DBModel();
                            a62.setType(0);
                            a62.setBloodOxygen(i60);
                            a62.setTimeInMillis(j5);
                            a62.setMacAddress(com.wakeup.wearfit2.AppApplication.device_address);
                            if (i60 != 0 && a62.save()) {
                                String s8 = com.wakeup.wearfit2.AppApplication.TAG;
                                StringBuilder a63 = new StringBuilder();
                                a63.append("\u8840\u6c27\u4fdd\u5b58\u6210\u529f");
                                a63.append(a62.toString());
                                android.util.Log.d(s8, a63.toString());
                                org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.SYNCING_DATA));
                            }
                        }
                    } else {
                        boolean b0 = com.wakeup.wearfit2.AppApplication.hasContinueHr;
                        label24: {
                            label25: {
                                if (b0) {
                                    break label25;
                                }
                                if (com.wakeup.wearfit2.AppApplication.hasContinueHrD) {
                                    break label25;
                                }
                                if (com.wakeup.wearfit2.AppApplication.hasContinueHrE) {
                                    break label25;
                                }
                                if (!com.wakeup.wearfit2.AppApplication.hasContinueHrF) {
                                    break label24;
                                }
                            }
                            int i61 = ((Integer)((java.util.List)a4).get(6)).intValue() + 2000;
                            int i62 = ((Integer)((java.util.List)a4).get(7)).intValue();
                            int i63 = ((Integer)((java.util.List)a4).get(8)).intValue();
                            int i64 = ((Integer)((java.util.List)a4).get(9)).intValue();
                            int i65 = ((Integer)((java.util.List)a4).get(10)).intValue();
                            StringBuilder a64 = new StringBuilder();
                            a64.append(i61);
                            Object[] a65 = new Object[1];
                            a65[0] = Integer.valueOf(i62);
                            a64.append(String.format("%02d", a65));
                            Object[] a66 = new Object[1];
                            a66[0] = Integer.valueOf(i63);
                            a64.append(String.format("%02d", a66));
                            Object[] a67 = new Object[1];
                            a67[0] = Integer.valueOf(i64);
                            a64.append(String.format("%02d", a67));
                            Object[] a68 = new Object[1];
                            a68[0] = Integer.valueOf(i65);
                            a64.append(String.format("%02d", a68));
                            long j6 = com.wakeup.wearfit2.util.DateUtils.getMilliSecondFromTime(a64.toString());
                            int i66 = ((Integer)((java.util.List)a4).get(11)).intValue();
                            com.wakeup.wearfit2.bean.DBHrBean a69 = new com.wakeup.wearfit2.bean.DBHrBean();
                            a69.setMacAddress(com.wakeup.wearfit2.AppApplication.device_address);
                            a69.setTimeInMillis(j6);
                            a69.setHeartRate(i66);
                            if (i66 == 0) {
                                break label23;
                            }
                            if (!a69.save()) {
                                break label23;
                            }
                            android.util.Log.i("wwxk", a69.toString());
                            org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.SYNCING_DATA));
                            break label23;
                        }
                        int i67 = ((Integer)((java.util.List)a4).get(6)).intValue() + 2000;
                        int i68 = ((Integer)((java.util.List)a4).get(7)).intValue();
                        int i69 = ((Integer)((java.util.List)a4).get(8)).intValue();
                        int i70 = ((Integer)((java.util.List)a4).get(9)).intValue();
                        int i71 = ((Integer)((java.util.List)a4).get(10)).intValue();
                        StringBuilder a70 = new StringBuilder();
                        a70.append(i67);
                        Object[] a71 = new Object[1];
                        a71[0] = Integer.valueOf(i68);
                        a70.append(String.format("%02d", a71));
                        Object[] a72 = new Object[1];
                        a72[0] = Integer.valueOf(i69);
                        a70.append(String.format("%02d", a72));
                        Object[] a73 = new Object[1];
                        a73[0] = Integer.valueOf(i70);
                        a70.append(String.format("%02d", a73));
                        Object[] a74 = new Object[1];
                        a74[0] = Integer.valueOf(i71);
                        a70.append(String.format("%02d", a74));
                        long j7 = com.wakeup.wearfit2.util.DateUtils.getMilliSecondFromTime(a70.toString());
                        int i72 = ((Integer)((java.util.List)a4).get(11)).intValue();
                        com.wakeup.wearfit2.model.DBModel a75 = new com.wakeup.wearfit2.model.DBModel();
                        a75.setType(0);
                        a75.setHeartRate(i72);
                        a75.setTimeInMillis(j7);
                        a75.setMacAddress(com.wakeup.wearfit2.AppApplication.device_address);
                        if (i72 != 0 && a75.save()) {
                            String s9 = com.wakeup.wearfit2.AppApplication.TAG;
                            StringBuilder a76 = new StringBuilder();
                            a76.append("\u5fc3\u7387\u4fdd\u5b58\u6210\u529f");
                            a76.append(a75.toString());
                            android.util.Log.d(s9, a76.toString());
                            org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.SYNCING_DATA));
                        }
                    }
                }
                if (((java.util.List)a4).size() == 20 && ((Integer)((java.util.List)a4).get(0)).intValue() == 171 && ((Integer)((java.util.List)a4).get(4)).intValue() == 38) {
                    com.wakeup.wearfit2.AppApplication.isEcg = true;
                }
                if (((Integer)((java.util.List)a4).get(0)).intValue() == 0) {
                    if (com.wakeup.wearfit2.AppApplication.isEcg) {
                        return;
                    }
                    android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, "\u7b2c\u4e8c\u4e2a\u5305");
                    com.wakeup.wearfit2.model.DBModel a77 = com.wakeup.wearfit2.AppApplication.dbModel;
                    if (a77 == null) {
                        return;
                    }
                    if (((java.util.List)a4).size() > 5) {
                        long j8 = (long)(((Integer)((java.util.List)a4).get(1)).intValue() * 60 * 60) * 1000L + (long)(((Integer)((java.util.List)a4).get(2)).intValue() * 60) * 1000L;
                        long j9 = (long)(((Integer)((java.util.List)a4).get(3)).intValue() * 60 * 60) * 1000L + (long)(((Integer)((java.util.List)a4).get(4)).intValue() * 60) * 1000L;
                        long j10 = j8 + j9;
                        a77.setShallow_sleep_time(j8);
                        a77.setDeep_sleep_time(j9);
                        a77.setSleep_time(j10);
                        a77.setWakeup_times(((Integer)((java.util.List)a4).get(5)).intValue());
                    }
                    boolean b1 = com.wakeup.wearfit2.AppApplication.hasContinueHr;
                    label22: {
                        label21: {
                            {
                                if (b1) {
                                    break label22;
                                }
                                if (com.wakeup.wearfit2.AppApplication.hasContinueHrD) {
                                    break label22;
                                }
                                if (com.wakeup.wearfit2.AppApplication.hasContinueHrE) {
                                    break label22;
                                }
                                if (!com.wakeup.wearfit2.AppApplication.hasContinueHrF) {
                                    break label21;
                                }
                            }
                            break label22;
                        }
                        int i73 = a77.getBloodPressure_high();
                        label16: {
                            label17: {
                                {
                                    label19: {
                                        label20: {
                                            if (i73 >= 90) {
                                                break label20;
                                            }
                                            if (a77.getBloodPressure_high() != 0) {
                                                break label19;
                                            }
                                        }
                                        if (a77.getBloodPressure_high() > 140) {
                                            break label19;
                                        }
                                        int i74 = a77.getBloodPressure_low();
                                        label18: {
                                            if (i74 >= 60) {
                                                break label18;
                                            }
                                            if (a77.getBloodPressure_low() != 0) {
                                                break label17;
                                            }
                                        }
                                        if (a77.getBloodPressure_low() <= 90) {
                                            break label16;
                                        }
                                        break label17;
                                    }
                                    com.wakeup.wearfit2.bean.ExceptionModel a78 = new com.wakeup.wearfit2.bean.ExceptionModel();
                                    a78.setAsc(com.wakeup.wearfit2.AppApplication.device_address);
                                    a78.setTimeInMillis(a77.getTimeInMillis());
                                    a78.setValua(a77.getBloodPressure_high());
                                    a78.setValua2(a77.getBloodPressure_low());
                                    int i75 = a77.getBloodPressure_low();
                                    label15: {
                                        label13: {
                                            label14: {
                                                if (i75 < 60) {
                                                    break label14;
                                                }
                                                if (a77.getBloodPressure_low() <= 90) {
                                                    break label13;
                                                }
                                            }
                                            a78.setType(6);
                                            break label15;
                                        }
                                        a78.setType(4);
                                    }
                                    a78.save();
                                }
                                break label16;
                            }
                            com.wakeup.wearfit2.bean.ExceptionModel a79 = new com.wakeup.wearfit2.bean.ExceptionModel();
                            a79.setAsc(com.wakeup.wearfit2.AppApplication.device_address);
                            StringBuilder a80 = new StringBuilder();
                            a80.append(a77.getBloodPressure_high());
                            a80.append("");
                            android.util.Log.i("wxk", a80.toString());
                            a79.setTimeInMillis(a77.getTimeInMillis());
                            a79.setValua(a77.getBloodPressure_high());
                            a79.setValua2(a77.getBloodPressure_low());
                            a79.setType(5);
                            StringBuilder a81 = new StringBuilder();
                            a81.append(a77.getBloodPressure_high());
                            a81.append("");
                            android.util.Log.i("wxk", a81.toString());
                            if (a79.save()) {
                                android.util.Log.i("wxk", "true");
                            } else {
                                android.util.Log.i("wxk", "false");
                            }
                        }
                        if (a77.getHeartRate() < 60 && a77.getHeartRate() != 0) {
                            com.wakeup.wearfit2.bean.ExceptionModel a82 = new com.wakeup.wearfit2.bean.ExceptionModel();
                            a82.setAsc(com.wakeup.wearfit2.AppApplication.device_address);
                            a82.setTimeInMillis(a77.getTimeInMillis());
                            a82.setValua(a77.getHeartRate());
                            a82.setType(2);
                            a82.save();
                        }
                        if (a77.getHeartRate() > 100) {
                            com.wakeup.wearfit2.bean.ExceptionModel a83 = new com.wakeup.wearfit2.bean.ExceptionModel();
                            a83.setAsc(com.wakeup.wearfit2.AppApplication.device_address);
                            a83.setTimeInMillis(a77.getTimeInMillis());
                            a83.setValua(a77.getHeartRate());
                            a83.setType(3);
                            a83.save();
                        }
                        if (a77.getBloodOxygen() < 93 && a77.getBloodOxygen() != 0) {
                            com.wakeup.wearfit2.bean.ExceptionModel a84 = new com.wakeup.wearfit2.bean.ExceptionModel();
                            a84.setAsc(com.wakeup.wearfit2.AppApplication.device_address);
                            a84.setTimeInMillis(a77.getTimeInMillis());
                            a84.setValua(a77.getBloodOxygen());
                            a84.setType(0);
                            a84.save();
                        }
                        if (a77.getBloodOxygen() > 101) {
                            com.wakeup.wearfit2.bean.ExceptionModel a85 = new com.wakeup.wearfit2.bean.ExceptionModel();
                            a85.setAsc(com.wakeup.wearfit2.AppApplication.device_address);
                            a85.setTimeInMillis(a77.getTimeInMillis());
                            a85.setValua(a77.getBloodOxygen());
                            a85.setType(1);
                            a85.save();
                        }
                    }
                    String[] a86 = new String[2];
                    a86[0] = "timeInMillis=?";
                    a86[1] = String.valueOf(a77.getTimeInMillis());
                    if (a77.saveOrUpdate(a86)) {
                        String s10 = com.wakeup.wearfit2.AppApplication.TAG;
                        StringBuilder a87 = new StringBuilder();
                        a87.append("\u6574\u70b9\u6570\u636e\u4fdd\u5b58\u6210\u529f\uff1a");
                        a87.append(a77.toString());
                        android.util.Log.d(s10, a87.toString());
                        org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.SYNCING_DATA));
                    } else {
                        android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, "\u6574\u70b9\u6570\u636e\u4fdd\u5b58\u5931\u8d25");
                    }
                }
                if (((Integer)((java.util.List)a4).get(0)).intValue() == 171 && ((Integer)((java.util.List)a4).get(4)).intValue() == 49) {
                    int i76 = ((Integer)((java.util.List)a4).get(5)).intValue();
                    label9: {
                        label11: {
                            label12: {
                                if (i76 == 9) {
                                    break label12;
                                }
                                if (((Integer)((java.util.List)a4).get(5)).intValue() == 17) {
                                    break label12;
                                }
                                if (((Integer)((java.util.List)a4).get(5)).intValue() != 33) {
                                    break label11;
                                }
                            }
                            if (((java.util.List)a4).size() >= 7 && ((Integer)((java.util.List)a4).get(6)).intValue() == 0) {
                                return;
                            }
                            com.wakeup.wearfit2.model.DBModel a88 = new com.wakeup.wearfit2.model.DBModel();
                            a88.setType(0);
                            a88.setMacAddress(com.wakeup.wearfit2.AppApplication.device_address);
                            a88.setTimeInMillis(System.currentTimeMillis() / 1000L * 1000L);
                            a88.setMeasureTime(com.wakeup.wearfit2.util.DateUtils.getDayHourMinutes(a88.getTimeInMillis()));
                            int i77 = ((Integer)((java.util.List)a4).get(5)).intValue();
                            if (i77 == 9) {
                                a88.setHeartRate(((Integer)((java.util.List)a4).get(6)).intValue());
                                com.wakeup.wearfit2.model.event.BaseEvent a89 = new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.HEARTRATE_ONE_MEASURE);
                                a89.setmObject((Object)a88);
                                org.greenrobot.eventbus.EventBus.getDefault().post((Object)a89);
                            } else if (i77 == 17) {
                                a88.setBloodOxygen(((Integer)((java.util.List)a4).get(6)).intValue());
                                com.wakeup.wearfit2.model.event.BaseEvent a90 = new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.OXYGEN_ONE_MEASURE);
                                a90.setmObject((Object)a88);
                                org.greenrobot.eventbus.EventBus.getDefault().post((Object)a90);
                            } else if (i77 == 33) {
                                a88.setBloodPressure_high(((Integer)((java.util.List)a4).get(6)).intValue());
                                a88.setBloodPressure_low(((Integer)((java.util.List)a4).get(7)).intValue());
                                com.wakeup.wearfit2.model.event.BaseEvent a91 = new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.BLOODPRESSURE_ONE_MEASURE);
                                a91.setmObject((Object)a88);
                                org.greenrobot.eventbus.EventBus.getDefault().post((Object)a91);
                            }
                            String[] a92 = new String[2];
                            a92[0] = "timeInMillis=?";
                            a92[1] = String.valueOf(a88.getTimeInMillis());
                            if (a88.saveOrUpdate(a92)) {
                                com.wakeup.wearfit2.model.event.BaseEvent a93 = new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.BLOODPRESSURE_ONE_MEASURE);
                                a93.setmObject((Object)a88);
                                org.greenrobot.eventbus.EventBus.getDefault().post((Object)a93);
                                android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, "\u5355\u6b21\u6d4b\u91cf\u50a8\u5b58\u6210\u529f");
                                break label9;
                            } else {
                                android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, "\u5355\u6b21\u6d4b\u91cf\u50a8\u5b58\u5931\u8d25");
                                break label9;
                            }
                        }
                        int i78 = ((Integer)((java.util.List)a4).get(5)).intValue();
                        label10: {
                            if (i78 == 10) {
                                break label10;
                            }
                            if (((Integer)((java.util.List)a4).get(5)).intValue() == 18) {
                                break label10;
                            }
                            if (((Integer)((java.util.List)a4).get(5)).intValue() != 34) {
                                break label9;
                            }
                        }
                        com.wakeup.wearfit2.model.DBModel a94 = new com.wakeup.wearfit2.model.DBModel();
                        if (((Integer)((java.util.List)a4).get(6)).intValue() == 0) {
                            return;
                        }
                        int i79 = ((Integer)((java.util.List)a4).get(5)).intValue();
                        if (i79 == 10) {
                            a94.setHeartRate(((Integer)((java.util.List)a4).get(6)).intValue());
                            com.wakeup.wearfit2.model.event.BaseEvent a95 = new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.HEARTRATE_REALTIME_MEASURE);
                            a95.setmObject((Object)a94);
                            org.greenrobot.eventbus.EventBus.getDefault().post((Object)a95);
                        } else if (i79 == 18) {
                            a94.setBloodOxygen(((Integer)((java.util.List)a4).get(6)).intValue());
                            com.wakeup.wearfit2.model.event.BaseEvent a96 = new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.OXYGEN_REALTIME_MEASURE);
                            a96.setmObject((Object)a94);
                            org.greenrobot.eventbus.EventBus.getDefault().post((Object)a96);
                        } else if (i79 == 34) {
                            a94.setBloodPressure_high(((Integer)((java.util.List)a4).get(6)).intValue());
                            a94.setBloodPressure_low(((Integer)((java.util.List)a4).get(7)).intValue());
                            com.wakeup.wearfit2.model.event.BaseEvent a97 = new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.BLOODPRESSURE_REALTIME_MEASURE);
                            a97.setmObject((Object)a94);
                            org.greenrobot.eventbus.EventBus.getDefault().post((Object)a97);
                        }
                        a94.setType(0);
                        a94.setMacAddress(com.wakeup.wearfit2.AppApplication.device_address);
                        a94.setTimeInMillis(System.currentTimeMillis());
                        com.wakeup.wearfit2.model.event.BaseEvent a98 = new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.BLOODPRESSURE_REALTIME_MEASURE);
                        a98.setmObject((Object)a94);
                        org.greenrobot.eventbus.EventBus.getDefault().post((Object)a98);
                    }
                    if (((Integer)((java.util.List)a4).get(5)).intValue() == 129) {
                        int i80 = ((Integer)((java.util.List)a4).get(6)).intValue();
                        int i81 = ((Integer)((java.util.List)a4).get(7)).intValue();
                        if (i80 != 0) {
                            new com.wakeup.wearfit2.bean.BodyTempBean((float)i80 + (float)i81 / 10f, System.currentTimeMillis(), com.wakeup.wearfit2.AppApplication.device_address).save();
                        }
                        org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.BODY_TEMP_FINISHED));
                    }
                    if (((Integer)((java.util.List)a4).get(5)).intValue() == 65) {
                        int i82 = ((Integer)((java.util.List)a4).get(6)).intValue();
                        if (i82 != 0) {
                            new com.wakeup.wearfit2.bean.MianyiBean((float)i82, System.currentTimeMillis()).save();
                        }
                        org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.MIANYI_FINISHED));
                    }
                }
                if (((Integer)((java.util.List)a4).get(0)).intValue() == 171 && ((Integer)((java.util.List)a4).get(4)).intValue() == 50) {
                    int i83 = 0;
                    int i84 = 0;
                    com.wakeup.wearfit2.model.DBModel a99 = new com.wakeup.wearfit2.model.DBModel();
                    a99.setMacAddress(com.wakeup.wearfit2.AppApplication.device_address);
                    a99.setHeartRate(((Integer)((java.util.List)a4).get(6)).intValue());
                    a99.setBloodOxygen(((Integer)((java.util.List)a4).get(7)).intValue());
                    a99.setBloodPressure_high(((Integer)((java.util.List)a4).get(8)).intValue());
                    a99.setBloodPressure_low(((Integer)((java.util.List)a4).get(9)).intValue());
                    int i85 = com.wakeup.wearfit2.util.DateUtils.getHourFromLong(System.currentTimeMillis());
                    com.wakeup.wearfit2.model.StepAndSleepModel a100 = (com.wakeup.wearfit2.model.StepAndSleepModel)org.litepal.LitePal.findFirst(com.wakeup.wearfit2.model.StepAndSleepModel.class);
                    int i86 = (a100 == null) ? 0 : a100.getStepCount();
                    label6: {
                        label8: {
                            if (i85 < 6) {
                                break label8;
                            }
                            if (i85 >= 11) {
                                break label8;
                            }
                            i83 = 1;
                            break label6;
                        }
                        label7: {
                            if (i85 < 11) {
                                break label7;
                            }
                            if (i85 >= 18) {
                                break label7;
                            }
                            i83 = 10;
                            break label6;
                        }
                        label4: {
                            label5: {
                                if (i85 < 18) {
                                    break label5;
                                }
                                if (i85 < 24) {
                                    break label4;
                                }
                            }
                            i83 = 30;
                            break label6;
                        }
                        i83 = 20;
                    }
                    int i87 = i83 + (int)Math.sqrt((double)i86) / 2;
                    label2: if (i87 >= 30) {
                        label3: {
                            if (i87 < 30) {
                                break label3;
                            }
                            if (i87 >= 60) {
                                break label3;
                            }
                            i84 = 2;
                            break label2;
                        }
                        label0: {
                            label1: {
                                if (i87 < 60) {
                                    break label1;
                                }
                                if (i87 < 80) {
                                    break label0;
                                }
                            }
                            i84 = 4;
                            break label2;
                        }
                        i84 = 3;
                    } else {
                        i84 = 1;
                    }
                    a99.setTiredValue(i87);
                    a99.setTiredType(i84);
                    a99.setTimeInMillis(com.wakeup.wearfit2.util.DateSupport.String2Data(com.wakeup.wearfit2.util.DateSupport.toString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm"), "yyyy-MM-dd HH:mm").getTime());
                    a99.setType(0);
                    String[] a101 = new String[2];
                    a101[0] = "timeInMillis=?";
                    a101[1] = String.valueOf(a99.getTimeInMillis());
                    if (a99.saveOrUpdate(a101)) {
                        com.wakeup.wearfit2.model.event.BaseEvent a102 = new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.ONE_KEY_MEASURE);
                        a102.setmObject((Object)a99);
                        org.greenrobot.eventbus.EventBus.getDefault().post((Object)a102);
                        android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, "save success");
                    } else {
                        android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, "save failed");
                    }
                }
                if (((Integer)((java.util.List)a4).get(0)).intValue() == 171 && ((Integer)((java.util.List)a4).get(4)).intValue() == 82) {
                    java.util.List a103 = null;
                    com.wakeup.wearfit2.bean.SleepModel a104 = new com.wakeup.wearfit2.bean.SleepModel();
                    int i88 = ((Integer)((java.util.List)a4).get(6)).intValue() + 2000;
                    int i89 = ((Integer)((java.util.List)a4).get(7)).intValue();
                    int i90 = ((Integer)((java.util.List)a4).get(8)).intValue();
                    int i91 = ((Integer)((java.util.List)a4).get(9)).intValue();
                    int i92 = ((Integer)((java.util.List)a4).get(10)).intValue();
                    int i93 = ((Integer)((java.util.List)a4).get(11)).intValue();
                    int i94 = ((Integer)((java.util.List)a4).get(12)).intValue() * 16 * 16 + ((Integer)((java.util.List)a4).get(13)).intValue();
                    StringBuilder a105 = new StringBuilder();
                    a105.append("");
                    a105.append(i88);
                    Object[] a106 = new Object[1];
                    a106[0] = Integer.valueOf(i89);
                    a105.append(String.format("%1$,02d", a106));
                    Object[] a107 = new Object[1];
                    a107[0] = Integer.valueOf(i90);
                    a105.append(String.format("%1$,02d", a107));
                    Object[] a108 = new Object[1];
                    a108[0] = Integer.valueOf(i91);
                    a105.append(String.format("%1$,02d", a108));
                    Object[] a109 = new Object[1];
                    a109[0] = Integer.valueOf(i92);
                    a105.append(String.format("%1$,02d", a109));
                    long j11 = com.wakeup.wearfit2.util.DateUtils.getMilliSecondFromTime(a105.toString());
                    a104.setStartTimeInMillis(Long.valueOf(j11));
                    long j12 = (long)(i94 * 60 * 1000) + j11;
                    a104.setEntTimeInMillis(Long.valueOf(j12));
                    a104.setYear(i88);
                    a104.setMonth(i89);
                    a104.setDay(i90);
                    a104.setHour(i91);
                    a104.setMinute(i92);
                    a104.setSleep_id(i93);
                    a104.setSleep_time((long)i94);
                    a104.setBandAddress(com.wakeup.wearfit2.AppApplication.device_address);
                    String s11 = com.wakeup.wearfit2.AppApplication.device_address;
                    Object a110 = null;
                    if (s11 == null) {
                        a103 = (java.util.List)a110;
                    } else {
                        String[] a111 = new String[4];
                        a111[0] = "entTimeInMillis = ? and bandAddress = ? and startTimeInMillis = ?";
                        StringBuilder a112 = new StringBuilder();
                        a112.append(j12);
                        a112.append("");
                        a111[1] = a112.toString();
                        a111[2] = com.wakeup.wearfit2.AppApplication.device_address;
                        StringBuilder a113 = new StringBuilder();
                        a113.append(j11);
                        a113.append("");
                        a111[3] = a113.toString();
                        a103 = org.litepal.LitePal.where(a111).find(com.wakeup.wearfit2.bean.SleepModel.class);
                    }
                    if (a103 != null && a103.size() > 0) {
                        String s12 = com.wakeup.wearfit2.AppApplication.TAG;
                        StringBuilder a114 = new StringBuilder();
                        a114.append("sleepModel\u6570\u636e\u91cd\u590d");
                        a114.append(((com.wakeup.wearfit2.bean.SleepModel)a103.get(0)).toString());
                        android.util.Log.d(s12, a114.toString());
                        return;
                    }
                    android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, "sleepModel\u6570\u636e\u4fdd\u5b58\u6210\u529f");
                    if (a104.save()) {
                        android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, "sleepModel\u6570\u636e\u4fdd\u5b58\u6210\u529f");
                        String s13 = com.wakeup.wearfit2.AppApplication.TAG;
                        StringBuilder a115 = new StringBuilder();
                        a115.append("sleepModel::");
                        a115.append(a104.toString());
                        android.util.Log.d(s13, a115.toString());
                        org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.SYNCING_DATA));
                    } else {
                        android.util.Log.e(com.wakeup.wearfit2.AppApplication.TAG, "sleepModel\u6570\u636e\u4fdd\u5b58\u5931\u8d25");
                    }
                }
                if (com.wakeup.wearfit2.AppApplication.device_name.equals((Object)"GW66") && ((Integer)((java.util.List)a4).get(0)).intValue() == 171 && ((Integer)((java.util.List)a4).get(4)).intValue() == 81 && ((Integer)((java.util.List)a4).get(5)).intValue() == 48) {
                    java.util.List a116 = null;
                    android.util.Log.i(com.wakeup.wearfit2.AppApplication.TAG, "52 \u7761\u7720\u6570\u636e");
                    com.wakeup.wearfit2.bean.SleepModel a117 = new com.wakeup.wearfit2.bean.SleepModel();
                    int i95 = ((Integer)((java.util.List)a4).get(6)).intValue() + 2000;
                    int i96 = ((Integer)((java.util.List)a4).get(7)).intValue();
                    int i97 = ((Integer)((java.util.List)a4).get(8)).intValue();
                    int i98 = ((Integer)((java.util.List)a4).get(9)).intValue();
                    int i99 = ((Integer)((java.util.List)a4).get(10)).intValue();
                    int i100 = ((Integer)((java.util.List)a4).get(11)).intValue();
                    int i101 = ((Integer)((java.util.List)a4).get(12)).intValue() * 16 * 16 + ((Integer)((java.util.List)a4).get(13)).intValue();
                    StringBuilder a118 = new StringBuilder();
                    a118.append("");
                    a118.append(i95);
                    Object[] a119 = new Object[1];
                    a119[0] = Integer.valueOf(i96);
                    a118.append(String.format("%1$,02d", a119));
                    Object[] a120 = new Object[1];
                    a120[0] = Integer.valueOf(i97);
                    a118.append(String.format("%1$,02d", a120));
                    Object[] a121 = new Object[1];
                    a121[0] = Integer.valueOf(i98);
                    a118.append(String.format("%1$,02d", a121));
                    Object[] a122 = new Object[1];
                    a122[0] = Integer.valueOf(i99);
                    a118.append(String.format("%1$,02d", a122));
                    long j13 = com.wakeup.wearfit2.util.DateUtils.getMilliSecondFromTime(a118.toString());
                    a117.setStartTimeInMillis(Long.valueOf(j13));
                    long j14 = (long)(i101 * 60 * 1000) + j13;
                    a117.setEntTimeInMillis(Long.valueOf(j14));
                    a117.setYear(i95);
                    a117.setMonth(i96);
                    a117.setDay(i97);
                    a117.setHour(i98);
                    a117.setMinute(i99);
                    a117.setSleep_id(i100);
                    a117.setSleep_time((long)i101);
                    a117.setBandAddress(com.wakeup.wearfit2.AppApplication.device_address);
                    String s14 = com.wakeup.wearfit2.AppApplication.device_address;
                    Object a123 = null;
                    if (s14 == null) {
                        a116 = (java.util.List)a123;
                    } else {
                        String[] a124 = new String[4];
                        a124[0] = "entTimeInMillis = ? and bandAddress = ? and startTimeInMillis = ?";
                        StringBuilder a125 = new StringBuilder();
                        a125.append(j14);
                        a125.append("");
                        a124[1] = a125.toString();
                        a124[2] = com.wakeup.wearfit2.AppApplication.device_address;
                        StringBuilder a126 = new StringBuilder();
                        a126.append(j13);
                        a126.append("");
                        a124[3] = a126.toString();
                        a116 = org.litepal.LitePal.where(a124).find(com.wakeup.wearfit2.bean.SleepModel.class);
                    }
                    if (a116 != null && a116.size() > 0) {
                        String s15 = com.wakeup.wearfit2.AppApplication.TAG;
                        StringBuilder a127 = new StringBuilder();
                        a127.append("sleepModel\u6570\u636e\u91cd\u590d");
                        a127.append(((com.wakeup.wearfit2.bean.SleepModel)a116.get(0)).toString());
                        android.util.Log.d(s15, a127.toString());
                        return;
                    }
                    android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, "sleepModel\u6570\u636e\u4fdd\u5b58\u6210\u529f");
                    if (a117.save()) {
                        android.util.Log.d(com.wakeup.wearfit2.AppApplication.TAG, "sleepModel\u6570\u636e\u4fdd\u5b58\u6210\u529f");
                        String s16 = com.wakeup.wearfit2.AppApplication.TAG;
                        StringBuilder a128 = new StringBuilder();
                        a128.append("sleepModel::");
                        a128.append(a117.toString());
                        android.util.Log.d(s16, a128.toString());
                        org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.SYNCING_DATA));
                    } else {
                        android.util.Log.e(com.wakeup.wearfit2.AppApplication.TAG, "sleepModel\u6570\u636e\u4fdd\u5b58\u5931\u8d25");
                    }
                }
                if (((Integer)((java.util.List)a4).get(0)).intValue() == 171 && ((Integer)((java.util.List)a4).get(4)).intValue() == 125) {
                    if (((Integer)((java.util.List)a4).get(6)).intValue() != 0) {
                        if (((Integer)((java.util.List)a4).get(6)).intValue() == 1 && com.wakeup.wearfit2.AppApplication.access$400(this.this$0) == null) {
                            com.wakeup.wearfit2.AppApplication.access$500(this.this$0);
                            com.wakeup.wearfit2.AppApplication.access$300(this.this$0).sendEmptyMessageDelayed(2, 60000L);
                        }
                    } else {
                        com.wakeup.wearfit2.AppApplication.access$100(this.this$0);
                    }
                }
                if (((Integer)((java.util.List)a4).get(0)).intValue() == 171 && ((Integer)((java.util.List)a4).get(4)).intValue() == 129 && ((Integer)((java.util.List)a4).get(5)).intValue() == 1) {
                    String s17 = com.wakeup.wearfit2.AppApplication.TAG;
                    StringBuilder a129 = new StringBuilder();
                    a129.append("\u6302\u65ad\u7535\u8bdd PHONE_STATE:");
                    a129.append(com.wakeup.wearfit2.AppApplication.PHONE_STATE);
                    android.util.Log.i(s17, a129.toString());
                    if (com.wakeup.wearfit2.AppApplication.PHONE_STATE == 1) {
                        com.wakeup.wearfit2.AppApplication.access$600(this.this$0);
                        com.wakeup.wearfit2.AppApplication.access$700(this.this$0).setHangUpPhone();
                    }
                }
                if (((Integer)((java.util.List)a4).get(0)).intValue() == 171 && ((Integer)((java.util.List)a4).get(4)).intValue() == 151) {
                    int i102 = ((Integer)((java.util.List)a4).get(6)).intValue() + 2000;
                    int i103 = ((Integer)((java.util.List)a4).get(7)).intValue();
                    int i104 = ((Integer)((java.util.List)a4).get(8)).intValue();
                    int i105 = ((Integer)((java.util.List)a4).get(9)).intValue();
                    String s18 = com.wakeup.wearfit2.AppApplication.TAG;
                    StringBuilder a130 = new StringBuilder();
                    a130.append("year: ");
                    a130.append(i102);
                    a130.append("month: ");
                    a130.append(i103);
                    a130.append("day: ");
                    a130.append(i104);
                    a130.append("min ");
                    a130.append(i105);
                    android.util.Log.i(s18, a130.toString());
                }
                if (((Integer)((java.util.List)a4).get(0)).intValue() == 171 && ((Integer)((java.util.List)a4).get(4)).intValue() == 149) {
                    if (((java.util.List)a4).size() > 8) {
                        int i106 = ((Integer)((java.util.List)a4).get(8)).intValue();
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.this$0.getApplicationContext(), "biaopan_num", i106);
                    }
                    if (((java.util.List)a4).size() > 7) {
                        int i107 = ((Integer)((java.util.List)a4).get(7)).intValue();
                        com.wakeup.wearfit2.util.SPUtils.putInt(this.this$0.getApplicationContext(), "xiangmubianhao", i107);
                    }
                }
                if (((Integer)((java.util.List)a4).get(0)).intValue() != 171) {
                    break;
                }
                if (((Integer)((java.util.List)a4).get(4)).intValue() != 155) {
                    break;
                }
                int i108 = ((Integer)((java.util.List)a4).get(5)).intValue();
                boolean b2 = (i108 & 1) == 1;
                boolean b3 = (i108 & 2) == 2;
                boolean b4 = (i108 & 4) == 4;
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.this$0.getApplicationContext(), "hasmianyi", b2);
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.this$0.getApplicationContext(), "hastiwen", b3);
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.this$0.getApplicationContext(), "hasnewtiwen", b4);
                org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.TIWEN_AND_MIANYI));
                int i109 = ((Integer)((java.util.List)a4).get(6)).intValue();
                boolean b5 = (i109 & 1) == 1;
                boolean b6 = (i109 & 2) == 2;
                boolean b7 = (i109 & 4) == 4;
                boolean b8 = (i109 & 8) == 8;
                boolean b9 = (i109 & 16) == 16;
                boolean b10 = (i109 & 32) == 32;
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.this$0.getApplicationContext(), "hasviber", b5);
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.this$0.getApplicationContext(), "hasvkclient", b6);
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.this$0.getApplicationContext(), "hastelegram", b7);
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.this$0.getApplicationContext(), "hasskype", b8);
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.this$0.getApplicationContext(), "haswearfit", b9);
                com.wakeup.wearfit2.util.SPUtils.putBoolean(this.this$0.getApplicationContext(), "hasdrinkwater", b10);
                break;
            }
            case 1: {
                if (a0.getIntExtra("com.wakeup.wearfit2.EXTRA_CONNECTION_STATE", 0) != 1) {
                    break;
                }
                com.wakeup.wearfit2.AppApplication.access$1000(this.this$0);
                break;
            }
            case 0: {
                android.util.Log.i(com.wakeup.wearfit2.AppApplication.TAG, "Intent.ACTION_LOCALE_CHANGED");
                com.wakeup.wearfit2.AppApplication.access$800(this.this$0);
                break;
            }
        }
    }
}
