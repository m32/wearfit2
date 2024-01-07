package com.wakeup.wearfit2.manager;

public class CommandManager {
    final private static String TAG = "CommandManager";
    private static com.wakeup.wearfit2.manager.CommandManager instance;
    private static android.content.Context mContext;
    
    static {
    }
    
    private CommandManager() {
    }
    
    private void broadcastData(String s, byte[] a) {
        android.content.Intent a0 = new android.content.Intent("com.wakeup.wearfit2.ACTION_SEND_DATA_TO_BLE");
        a0.putExtra("com.wakeup.wearfit2.EXTRA_SEND_DATA_TO_BLE", a);
        a0.putExtra("com.wakeup.wearfit2.EXTRA_SEND_DATA_TO_BLE_ORDER", s);
        mContext.sendBroadcast(a0);
        String s0 = TAG;
        StringBuilder a1 = new StringBuilder();
        a1.append("ble send ");
        a1.append(com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a));
        android.util.Log.i(s0, a1.toString());
    }
    
    private void broadcastData(byte[] a) {
        android.content.Intent a0 = new android.content.Intent("com.wakeup.wearfit2.ACTION_SEND_DATA_TO_BLE");
        a0.putExtra("com.wakeup.wearfit2.EXTRA_SEND_DATA_TO_BLE", a);
        mContext.sendBroadcast(a0);
        String s = TAG;
        StringBuilder a1 = new StringBuilder();
        a1.append("ble send ");
        a1.append(com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a));
        android.util.Log.i(s, a1.toString());
    }
    
    public static String cut(String s, int i) {
        byte[] a = s.getBytes();
        if (a.length < i) {
            i = a.length;
        }
        int i0 = 0;
        int i1 = 0;
        while(i0 < i) {
            int i2 = 0;
            if (((int)a[i0] & 128) != 0) {
                if (((int)a[i0] & 224) != 192) {
                    if (((int)a[i0] & 240) != 224) {
                        i0 = i0 + 4;
                        i2 = 2;
                    } else {
                        i0 = i0 + 3;
                        i2 = 1;
                    }
                } else {
                    i0 = i0 + 2;
                    i2 = 1;
                }
            } else {
                i0 = i0 + 1;
                i2 = 1;
            }
            if (i0 <= i) {
                i1 = i1 + i2;
            }
        }
        return s.substring(0, i1);
    }
    
    public static com.wakeup.wearfit2.manager.CommandManager getInstance(android.content.Context a) {
        /*monenter(com.wakeup.wearfit2.manager.CommandManager.class)*/;
        if (mContext == null) {
            mContext = a;
        }
        com.wakeup.wearfit2.manager.CommandManager a0 = instance;
        label0: {
            Throwable a1 = null;
            if (a0 != null) {
                break label0;
            }
            try {
                instance = new com.wakeup.wearfit2.manager.CommandManager();
                break label0;
            } catch(Throwable a2) {
                a1 = a2;
            }
            /*monexit(com.wakeup.wearfit2.manager.CommandManager.class)*/;
            throw a1;
        }
        com.wakeup.wearfit2.manager.CommandManager a3 = instance;
        /*monexit(com.wakeup.wearfit2.manager.CommandManager.class)*/;
        return a3;
    }
    
    private int string2Int(String s) {
        int i = s.length();
        int i0 = 0;
        int i1 = 0;
        while(i0 < i) {
            int i2 = i0 + 1;
            String s0 = s.substring(i0, i2);
            int i3 = i0 * 4;
            i1 = i1 + (((s0.equals((Object)"+")) ? 10 : Integer.parseInt(s0)) << i3);
            i0 = i2;
        }
        return i1;
    }
    
    public void bodyTempMeasure(int i) {
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)(-122);
        a[5] = (byte)(-128);
        a[6] = (byte)(int)(byte)i;
        this.broadcastData(a);
    }
    
    public void bootloader() {
        android.util.Log.i(TAG, "bootloader: ");
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)37;
        a[5] = (byte)(-128);
        a[6] = (byte)1;
        this.broadcastData(a);
    }
    
    public void bootloaderMac() {
        android.util.Log.i(TAG, "bootloaderMac: ");
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)40;
        a[5] = (byte)(-128);
        a[6] = (byte)1;
        this.broadcastData(a);
    }
    
    public void bootloaderWatch() {
        android.util.Log.i(TAG, "bootloaderWatch: ");
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)(-102);
        a[5] = (byte)(-128);
        a[6] = (byte)1;
        this.broadcastData(a);
    }
    
    public void drinkWater(int i, int i0, int i1, int i2, int i3, int i4) {
        android.util.Log.i(TAG, "drinkWater: ");
        byte[] a = new byte[12];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)9;
        a[3] = (byte)(-1);
        a[4] = (byte)83;
        a[5] = (byte)(-128);
        a[6] = (byte)(int)(byte)i;
        a[7] = (byte)(int)(byte)i0;
        a[8] = (byte)(int)(byte)i1;
        a[9] = (byte)(int)(byte)i2;
        a[10] = (byte)(int)(byte)i3;
        a[11] = (byte)(int)(byte)i4;
        this.broadcastData(a);
    }
    
    public void getBattery() {
        byte[] a = new byte[6];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)3;
        a[3] = (byte)(-1);
        a[4] = (byte)(-78);
        a[5] = (byte)(-128);
        this.broadcastData(a);
    }
    
    public void getBatteryInfo() {
        android.util.Log.i(TAG, "getBatteryInfo: ");
        byte[] a = new byte[6];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)3;
        a[3] = (byte)(-1);
        a[4] = (byte)(-111);
        a[5] = (byte)(-128);
        this.broadcastData(a);
    }
    
    public void getBiaoPan() {
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)(-107);
        a[5] = (byte)(-128);
        a[6] = (byte)0;
        this.broadcastData(a);
    }
    
    public void getClassicMac() {
        byte[] a = new byte[6];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)3;
        a[3] = (byte)(-1);
        a[4] = (byte)(-90);
        a[5] = (byte)(-128);
        this.broadcastData(a);
    }
    
    public void getEcgHistoryData(long j, int i) {
        String s = TAG;
        android.util.Log.i(s, "getEcgHistoryData: ");
        com.wakeup.wearfit2.model.DateModel a = new com.wakeup.wearfit2.model.DateModel(j);
        byte[] a0 = new byte[12];
        a0[0] = (byte)(-85);
        a0[1] = (byte)0;
        a0[2] = (byte)9;
        a0[3] = (byte)(-1);
        a0[4] = (byte)38;
        a0[5] = (byte)(-128);
        a0[6] = (byte)(int)(byte)i;
        a0[7] = (byte)(int)(byte)(a.year + -2000);
        a0[8] = (byte)(int)(byte)a.month;
        a0[9] = (byte)(int)(byte)a.day;
        a0[10] = (byte)(int)(byte)a.hour;
        a0[11] = (byte)(int)(byte)a.minute;
        StringBuilder a1 = new StringBuilder();
        a1.append(a.year + -2000);
        a1.append("-");
        a1.append(a.month);
        a1.append("-");
        a1.append(a.day);
        a1.append("/");
        a1.append(a.hour);
        a1.append(":");
        a1.append(a.minute);
        android.util.Log.i(s, a1.toString());
        this.broadcastData(a0);
    }
    
    public void getExerciseRecord(long j) {
        com.wakeup.wearfit2.model.DateModel a = new com.wakeup.wearfit2.model.DateModel(j);
        byte[] a0 = new byte[12];
        a0[0] = (byte)(-85);
        a0[1] = (byte)0;
        a0[2] = (byte)9;
        a0[3] = (byte)(-1);
        a0[4] = (byte)41;
        a0[5] = (byte)(-128);
        a0[6] = (byte)0;
        a0[7] = (byte)(int)(byte)(a.year + -2000);
        a0[8] = (byte)(int)(byte)a.month;
        a0[9] = (byte)(int)(byte)a.day;
        a0[10] = (byte)(int)(byte)a.hour;
        a0[11] = (byte)(int)(byte)a.minute;
        this.broadcastData(a0);
    }
    
    public void getTrueTimeRate(int i) {
        android.util.Log.i(TAG, "getTrueTimeRate: ");
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)(-124);
        a[5] = (byte)(-128);
        a[6] = (byte)(int)(byte)i;
        this.broadcastData(a);
    }
    
    public void getVersionInfo() {
        android.util.Log.i(TAG, "getVersionInfo: ");
        byte[] a = new byte[6];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)3;
        a[3] = (byte)(-1);
        a[4] = (byte)(-110);
        a[5] = (byte)(-128);
        this.broadcastData(a);
    }
    
    public void jingQi(int i, int i0) {
        String s = TAG;
        StringBuilder a = new StringBuilder();
        a.append("jingQi: ");
        a.append(i);
        a.append("-");
        a.append(i0);
        android.util.Log.i(s, a.toString());
        byte[] a0 = new byte[8];
        a0[0] = (byte)(-85);
        a0[1] = (byte)0;
        a0[2] = (byte)5;
        a0[3] = (byte)(-1);
        a0[4] = (byte)36;
        a0[5] = (byte)(-128);
        a0[6] = (byte)(int)(byte)i;
        a0[7] = (byte)(int)(byte)i0;
        this.broadcastData(a0);
    }
    
    public void peidui() {
        android.util.Log.i(TAG, "peidui");
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)(-96);
        a[5] = (byte)(-128);
        a[6] = (byte)1;
        this.broadcastData(a);
    }
    
    public void sendContactName(int i, String s) {
        byte[] a = null;
        int i0 = 0;
        if (s == null) {
            return;
        }
        String s0 = com.wakeup.wearfit2.manager.CommandManager.cut(s, 21);
        if (android.text.TextUtils.isEmpty((CharSequence)(Object)s0)) {
            a = null;
            i0 = 0;
        } else {
            a = s0.getBytes();
            i0 = a.length;
        }
        byte[] a0 = new byte[6];
        a0[0] = (byte)(-85);
        int i1 = i0 + 3;
        a0[1] = (byte)(int)(byte)(i1 / 256);
        a0[2] = (byte)(int)(byte)(i1 % 256);
        a0[3] = (byte)(-1);
        a0[4] = (byte)(-94);
        a0[5] = (byte)(int)(byte)i;
        this.broadcastData("\u8bbe\u7f6e\u8054\u7cfb\u4eba\u59d3\u540d", com.wakeup.wearfit2.util.DataHandlerUtils.handleMessageBytes(com.wakeup.wearfit2.util.DataHandlerUtils.addBytes(a0, a)));
    }
    
    public void sendContactPhone(int i, String s) {
        byte[] a = null;
        if (s == null) {
            return;
        }
        String s0 = s.replace((CharSequence)(Object)" ", (CharSequence)(Object)"");
        if (s0.contains((CharSequence)(Object)"+")) {
            s0.replace((CharSequence)(Object)"+", (CharSequence)(Object)"A");
            int i0 = s0.length();
            if (i0 % 2 != 0) {
                int i1 = i0 / 2 + 1;
                a = new byte[i1];
                int i2 = 0;
                for(; i2 < i1; i2 = i2 + 1) {
                    String s1 = null;
                    if (i2 >= i1 + -1) {
                        int i3 = i2 * 2;
                        s1 = s0.substring(i3, i3 + 1);
                    } else {
                        int i4 = i2 * 2;
                        s1 = s0.substring(i4, i4 + 2);
                    }
                    a[i2] = (byte)(int)(byte)this.string2Int(s1);
                }
            } else {
                int i5 = i0 / 2;
                a = new byte[i5];
                int i6 = 0;
                for(; i6 < i5; i6 = i6 + 1) {
                    int i7 = i6 * 2;
                    a[i6] = (byte)(int)(byte)this.string2Int(s0.substring(i7, i7 + 2));
                }
            }
        } else {
            int i8 = s0.length();
            if (i8 % 2 != 0) {
                int i9 = i8 / 2 + 1;
                a = new byte[i9];
                int i10 = 0;
                for(; i10 < i9; i10 = i10 + 1) {
                    String s2 = null;
                    if (i10 >= i9 + -1) {
                        int i11 = i10 * 2;
                        s2 = s0.substring(i11, i11 + 1);
                    } else {
                        int i12 = i10 * 2;
                        s2 = s0.substring(i12, i12 + 2);
                    }
                    a[i10] = (byte)(int)(byte)this.string2Int(s2);
                }
            } else {
                int i13 = i8 / 2;
                a = new byte[i13];
                int i14 = 0;
                for(; i14 < i13; i14 = i14 + 1) {
                    int i15 = i14 * 2;
                    a[i14] = (byte)(int)(byte)this.string2Int(s0.substring(i15, i15 + 2));
                }
            }
        }
        byte[] a0 = new byte[7];
        a0[0] = (byte)(-85);
        a0[1] = (byte)(int)(byte)((a.length + 4) / 256);
        a0[2] = (byte)(int)(byte)((a.length + 4) % 256);
        a0[3] = (byte)(-1);
        a0[4] = (byte)(-93);
        a0[5] = (byte)(int)(byte)i;
        a0[6] = (byte)(int)(byte)s0.length();
        this.broadcastData("\u8bbe\u7f6e\u8054\u7cfb\u4eba\u7535\u8bdd\u53f7\u7801", com.wakeup.wearfit2.util.DataHandlerUtils.addBytes(a0, a));
    }
    
    public void sendImageContent(byte[] a) {
        this.broadcastData("\u5c4f\u4fdd\u63a8\u9001\u56fe\u7247", a);
    }
    
    public void sendWeatherInfo(com.wakeup.wearfit2.bean.WeatherInfo a) {
        android.util.Log.i(TAG, "sendWeatherInfo....");
        if (a == null) {
            return;
        }
        byte[] a0 = new byte[20];
        a0[0] = (byte)(-85);
        a0[1] = (byte)0;
        a0[2] = (byte)17;
        a0[3] = (byte)(-1);
        a0[4] = (byte)126;
        a0[5] = (byte)(-128);
        a0[6] = (byte)(int)(byte)Integer.parseInt(a.getWeatherType(), 16);
        a0[7] = (byte)(int)(byte)a.getTemperature();
        a0[8] = (byte)0;
        a0[9] = (byte)0;
        a0[10] = (byte)0;
        a0[11] = (byte)0;
        a0[12] = (byte)0;
        a0[13] = (byte)0;
        a0[14] = (byte)0;
        a0[15] = (byte)0;
        a0[16] = (byte)0;
        a0[17] = (byte)0;
        a0[18] = (byte)0;
        a0[19] = (byte)0;
        this.broadcastData(a0);
    }
    
    public void sendWeatherInfo(java.util.List a) {
        android.util.Log.i(TAG, "sendWeatherInfo....");
        byte[] a0 = new byte[20];
        a0[0] = (byte)(-85);
        a0[1] = (byte)0;
        a0[2] = (byte)17;
        a0[3] = (byte)(-1);
        a0[4] = (byte)126;
        a0[5] = (byte)(-128);
        Object a1 = a;
        int i = 0;
        for(; i < ((java.util.List)a1).size(); i = i + 1) {
            String s = null;
            com.wakeup.wearfit2.kotlin.bean.ForecastBean$ForecastsBean a2 = (com.wakeup.wearfit2.kotlin.bean.ForecastBean$ForecastsBean)((java.util.List)a1).get(i);
            int i0 = a2.getTemperature();
            int i1 = a2.getCode();
            if (i0 <= 0) {
                StringBuilder a3 = new StringBuilder();
                a3.append(i1);
                a3.append("1");
                s = a3.toString();
            } else {
                StringBuilder a4 = new StringBuilder();
                a4.append(i1);
                a4.append("0");
                s = a4.toString();
            }
            int i2 = i * 2;
            a0[i2 + 6] = (byte)(int)(byte)Integer.parseInt(s, 16);
            a0[i2 + 7] = (byte)(int)(byte)Math.abs(i0);
        }
        this.broadcastData(a0);
    }
    
    public void set12HourSystem(int i) {
        android.util.Log.i(TAG, "set12HourSystem: ");
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)124;
        a[5] = (byte)(-128);
        a[6] = (byte)(int)(byte)i;
        this.broadcastData(a);
    }
    
    public void setAlertClock(int i, int i0, int i1, int i2, int i3) {
        String s = TAG;
        StringBuilder a = new StringBuilder();
        a.append("setAlertClock: ");
        a.append(i);
        android.util.Log.i(s, a.toString());
        byte[] a0 = new byte[11];
        a0[0] = (byte)(-85);
        a0[1] = (byte)0;
        a0[2] = (byte)8;
        a0[3] = (byte)(-1);
        a0[4] = (byte)115;
        a0[5] = (byte)(-128);
        a0[6] = (byte)(int)(byte)i;
        a0[7] = (byte)(int)(byte)i0;
        a0[8] = (byte)(int)(byte)i1;
        a0[9] = (byte)(int)(byte)i2;
        a0[10] = (byte)(int)(byte)i3;
        this.broadcastData(a0);
    }
    
    public void setAntiLostAlert(int i) {
        android.util.Log.i(TAG, "setAntiLostAlert: ");
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)122;
        a[5] = (byte)(-128);
        a[6] = (byte)(int)(byte)i;
        this.broadcastData(a);
    }
    
    public void setBandLanguage(int i) {
        if (com.wakeup.wearfit2.util.SPUtils.getString(com.wakeup.wearfit2.AppApplication.getAppContext(), com.wakeup.wearfit2.util.SPUtils.DEVICE_NAME, "").equals((Object)"KOSPET MAGIC 2") && i == 18) {
            i = 1;
        }
        String s = TAG;
        StringBuilder a = new StringBuilder();
        a.append("setBandLanguage: ");
        a.append(i);
        android.util.Log.i(s, a.toString());
        byte[] a0 = new byte[7];
        a0[0] = (byte)(-85);
        a0[1] = (byte)0;
        a0[2] = (byte)4;
        a0[3] = (byte)(-1);
        a0[4] = (byte)123;
        a0[5] = (byte)(-128);
        a0[6] = (byte)(int)(byte)i;
        this.broadcastData(a0);
    }
    
    public void setBiaoPan(int i, int i0) {
        byte[] a = new byte[9];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)6;
        a[3] = (byte)(-1);
        a[4] = (byte)(-107);
        a[5] = (byte)(-128);
        a[6] = (byte)1;
        a[7] = (byte)(int)(byte)i;
        a[8] = (byte)(int)(byte)i0;
        this.broadcastData(a);
    }
    
    public void setBloodPressureReference() {
        android.util.Log.i(TAG, "setBloodPressureReference: ");
        boolean b = com.wakeup.wearfit2.util.SPUtils.getBoolean(mContext, "is_bp_refrence");
        int i = com.wakeup.wearfit2.util.SPUtils.getInt(mContext, "value_sp");
        int i0 = com.wakeup.wearfit2.util.SPUtils.getInt(mContext, "value_bp");
        byte[] a = new byte[9];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)6;
        a[3] = (byte)(-1);
        a[4] = (byte)(-107);
        a[5] = (byte)(-128);
        a[6] = (byte)(int)(byte)b;
        a[7] = (byte)(int)(byte)i;
        a[8] = (byte)(int)(byte)i0;
        this.broadcastData(a);
    }
    
    public void setCalibrationTime(int i, int i0) {
        android.util.Log.i(TAG, "setCalibrationTime: ");
        byte[] a = new byte[8];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)5;
        a[3] = (byte)(-1);
        a[4] = (byte)125;
        a[5] = (byte)(-128);
        a[6] = (byte)(int)(byte)i;
        a[7] = (byte)(int)(byte)i0;
        this.broadcastData(a);
    }
    
    public void setClearData() {
        android.util.Log.i(TAG, "setClearData: ");
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)35;
        a[5] = (byte)(-128);
        a[6] = (byte)0;
        this.broadcastData(a);
    }
    
    public void setContactNum(int i, int i0) {
        byte[] a = new byte[8];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)5;
        a[3] = (byte)(-1);
        a[4] = (byte)(-91);
        a[5] = (byte)(-128);
        a[6] = (byte)(int)(byte)i;
        a[7] = (byte)(int)(byte)i0;
        this.broadcastData("\u8bbe\u7f6e\u8054\u7cfb\u4eba\u4e2a\u6570\u548c\u8c01\u662fSOS", a);
    }
    
    public void setDisturbanceModel() {
        android.util.Log.i(TAG, "setDisturbanceModel: ");
        com.wakeup.wearfit2.model.DisturbanceModel a = new com.wakeup.wearfit2.model.DisturbanceModel(mContext);
        byte[] a0 = new byte[11];
        a0[0] = (byte)(-85);
        a0[1] = (byte)0;
        a0[2] = (byte)8;
        a0[3] = (byte)(-1);
        a0[4] = (byte)118;
        a0[5] = (byte)(-128);
        a0[6] = (byte)(int)(byte)a.isOn;
        a0[7] = (byte)(int)(byte)a.startHour;
        a0[8] = (byte)(int)(byte)a.startMinute;
        a0[9] = (byte)(int)(byte)a.endHour;
        a0[10] = (byte)(int)(byte)a.endMinute;
        this.broadcastData(a0);
    }
    
    public void setFindBand() {
        String s = TAG;
        android.util.Log.i(s, "setFindBand: ");
        byte[] a = new byte[6];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)3;
        a[3] = (byte)(-1);
        a[4] = (byte)113;
        a[5] = (byte)(-128);
        android.util.Log.i(s, "\u67e5\u627e\u624b\u73af");
        this.broadcastData(a);
    }
    
    public void setHangUpPhone() {
        android.util.Log.i(TAG, "setHangUpPhone: ");
        byte[] a = new byte[6];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)3;
        a[3] = (byte)(-1);
        a[4] = (byte)(-127);
        a[5] = (byte)0;
        this.broadcastData(a);
    }
    
    public void setHrWarn(int i) {
        android.util.Log.i(TAG, "setHrWarn: ");
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)(-123);
        a[5] = (byte)(-128);
        a[6] = (byte)(int)(byte)i;
        this.broadcastData(a);
    }
    
    public void setOnTimeMeasure(int i) {
        android.util.Log.i(TAG, "setOnTimeMeasure: ");
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)120;
        a[5] = (byte)(-128);
        a[6] = (byte)(int)(byte)i;
        this.broadcastData(a);
    }
    
    public void setOnceKeyMeasure(int i) {
        android.util.Log.i(TAG, "setOnceKeyMeasure: ");
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)50;
        a[5] = (byte)(-128);
        a[6] = (byte)(int)(byte)i;
        this.broadcastData(a);
    }
    
    public void setOnceOrRealTimeMeasure(int i, int i0) {
        android.util.Log.i(TAG, "setOnceOrRealTimeMeasure: ");
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)49;
        a[5] = (byte)(int)(byte)i;
        a[6] = (byte)(int)(byte)i0;
        this.broadcastData(a);
    }
    
    public void setPowerSaving(int i) {
        String s = TAG;
        StringBuilder a = new StringBuilder();
        a.append("setPowerSaving: ");
        a.append(i);
        android.util.Log.i(s, a.toString());
        byte[] a0 = new byte[7];
        a0[0] = (byte)(-85);
        a0[1] = (byte)0;
        a0[2] = (byte)4;
        a0[3] = (byte)(-1);
        a0[4] = (byte)(-106);
        a0[5] = (byte)(-128);
        a0[6] = (byte)(int)(byte)i;
        this.broadcastData(a0);
    }
    
    public void setResetBand() {
        android.util.Log.i(TAG, "setResetBand: ");
        byte[] a = new byte[6];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)3;
        a[3] = (byte)(-1);
        a[4] = (byte)(-1);
        a[5] = (byte)(-128);
        this.broadcastData(a);
    }
    
    public void setSedentarinessWarn(int i) {
        android.util.Log.i(TAG, "setSedentarinessWarn: ");
        com.wakeup.wearfit2.model.SedentarinessInfo a = new com.wakeup.wearfit2.model.SedentarinessInfo(mContext);
        byte[] a0 = new byte[12];
        a0[0] = (byte)(-85);
        a0[1] = (byte)0;
        a0[2] = (byte)9;
        a0[3] = (byte)(-1);
        a0[4] = (byte)117;
        a0[5] = (byte)(-128);
        a0[6] = (byte)(int)(byte)a.isOn;
        a0[7] = (byte)(int)(byte)a.startHour;
        a0[8] = (byte)(int)(byte)a.startMinute;
        a0[9] = (byte)(int)(byte)a.endHour;
        a0[10] = (byte)(int)(byte)a.endMinute;
        a0[11] = (byte)(int)(byte)i;
        this.broadcastData(a0);
    }
    
    public void setSharkTakePhoto(int i) {
        android.util.Log.i(TAG, "setSharkTakePhoto: ");
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)121;
        a[5] = (byte)(-128);
        a[6] = (byte)(int)(byte)i;
        this.broadcastData(a);
    }
    
    public void setShowPingBao(int i) {
        String s = TAG;
        StringBuilder a = new StringBuilder();
        a.append("setShowPingBao: ");
        a.append(i);
        android.util.Log.i(s, a.toString());
        byte[] a0 = new byte[7];
        a0[0] = (byte)(-85);
        a0[1] = (byte)0;
        a0[2] = (byte)4;
        a0[3] = (byte)(-1);
        a0[4] = (byte)(-27);
        a0[5] = (byte)(-128);
        a0[6] = (byte)(int)(byte)i;
        this.broadcastData(a0);
    }
    
    public void setSleepTimeRange(int i, com.wakeup.wearfit2.model.UserModel a) {
        int i0 = 0;
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        android.util.Log.i(TAG, "setSleepTimeRange: ");
        String s = a.getFall_asleep();
        if (android.text.TextUtils.isEmpty((CharSequence)(Object)s)) {
            i0 = 0;
            i1 = 0;
        } else {
            String[] a0 = s.split(":");
            i1 = Integer.parseInt(a0[0]);
            i0 = Integer.parseInt(a0[1]);
        }
        String s0 = a.getWake_up_time();
        if (android.text.TextUtils.isEmpty((CharSequence)(Object)s)) {
            i2 = 0;
            i3 = 0;
        } else {
            String[] a1 = s0.split(":");
            i2 = Integer.parseInt(a1[0]);
            i3 = Integer.parseInt(a1[1]);
        }
        byte[] a2 = new byte[11];
        a2[0] = (byte)(-85);
        a2[1] = (byte)0;
        a2[2] = (byte)8;
        a2[3] = (byte)(-1);
        a2[4] = (byte)127;
        a2[5] = (byte)(-128);
        a2[6] = (byte)(int)(byte)i;
        a2[7] = (byte)(int)(byte)i1;
        a2[8] = (byte)(int)(byte)i0;
        a2[9] = (byte)(int)(byte)i2;
        a2[10] = (byte)(int)(byte)i3;
        this.broadcastData(a2);
    }
    
    public void setSmartWarn(int i, int i0, String s) {
        byte[] a = null;
        int i1 = 0;
        String s0 = TAG;
        StringBuilder a0 = new StringBuilder();
        a0.append("MessageId:");
        a0.append(i);
        a0.append(" type: ");
        a0.append(i0);
        android.util.Log.i(s0, a0.toString());
        if (s == null) {
            return;
        }
        android.util.Log.i(s0, "setSmartWarn");
        StringBuilder a1 = new StringBuilder();
        a1.append(s);
        a1.append(" / ");
        a1.append(s.length());
        android.util.Log.d(s0, a1.toString());
        if (android.text.TextUtils.isEmpty((CharSequence)(Object)s)) {
            a = null;
            i1 = 0;
        } else {
            a = com.wakeup.wearfit2.util.CommonUtil.subString(s).getBytes();
            i1 = a.length;
        }
        java.util.ArrayList a2 = new java.util.ArrayList();
        ((java.util.List)(Object)a2).add((Object)Byte.valueOf((byte)(-85)));
        int i2 = i1 + 5;
        ((java.util.List)(Object)a2).add((Object)Byte.valueOf((byte)(int)(byte)(i2 / 256)));
        ((java.util.List)(Object)a2).add((Object)Byte.valueOf((byte)(int)(byte)(i2 % 256)));
        ((java.util.List)(Object)a2).add((Object)Byte.valueOf((byte)(-1)));
        ((java.util.List)(Object)a2).add((Object)Byte.valueOf((byte)114));
        ((java.util.List)(Object)a2).add((Object)Byte.valueOf((byte)(-128)));
        ((java.util.List)(Object)a2).add((Object)Byte.valueOf((byte)(int)(byte)i));
        ((java.util.List)(Object)a2).add((Object)Byte.valueOf((byte)(int)(byte)i0));
        if (a.length > 20 - ((java.util.List)(Object)a2).size()) {
            int i3 = 20 - ((java.util.List)(Object)a2).size();
            int i4 = 0;
            for(; i4 < i3; i4 = i4 + 1) {
                ((java.util.List)(Object)a2).add((Object)Byte.valueOf((byte)(int)a[i4]));
            }
            java.util.Queue a3 = com.clj.fastble.bluetooth.SplitWriter.splitByte(java.util.Arrays.copyOfRange(a, i3, a.length), 19);
            int i5 = 0;
            Object a4 = a3;
            while(((java.util.Queue)a4).peek() != null) {
                ((java.util.List)(Object)a2).add((Object)Byte.valueOf((byte)(int)(byte)i5));
                byte[] a5 = (byte[])((java.util.Queue)a4).poll();
                int i6 = a5.length;
                int i7 = 0;
                for(; i7 < i6; i7 = i7 + 1) {
                    ((java.util.List)(Object)a2).add((Object)Byte.valueOf((byte)(int)a5[i7]));
                }
                i5 = i5 + 1;
            }
        } else {
            int i8 = a.length;
            int i9 = 0;
            for(; i9 < i8; i9 = i9 + 1) {
                ((java.util.List)(Object)a2).add((Object)Byte.valueOf((byte)(int)a[i9]));
            }
        }
        byte[] a6 = new byte[((java.util.List)(Object)a2).size()];
        int i10 = 0;
        for(; i10 < ((java.util.List)(Object)a2).size(); i10 = i10 + 1) {
            a6[i10] = (byte)(int)((Byte)((java.util.List)(Object)a2).get(i10)).byteValue();
        }
        this.broadcastData(a6);
    }
    
    public void setSmartWarn2(int i, int i0, String s, int i1) {
        byte[] a = null;
        String s0 = TAG;
        android.util.Log.i(s0, "setSmartWarn2");
        if (android.text.TextUtils.isEmpty((CharSequence)(Object)s)) {
            android.util.Log.i(s0, "content \u7a7a");
            return;
        }
        byte[] a0 = s.getBytes();
        int i2 = a0.length;
        StringBuilder a1 = new StringBuilder();
        a1.append("contentBytesLength: ");
        a1.append(i2);
        a1.append("  length: ");
        a1.append(i1);
        android.util.Log.i(s0, a1.toString());
        if (i2 <= i1) {
            a = a0;
        } else {
            a = new byte[i1];
            System.arraycopy((Object)a0, 0, (Object)a, 0, i1);
            StringBuilder a2 = new StringBuilder();
            a2.append("length:");
            a2.append(i1);
            android.util.Log.i(s0, a2.toString());
        }
        byte[] a3 = new byte[8];
        a3[0] = (byte)(-85);
        a3[1] = (byte)0;
        a3[2] = (byte)(int)(byte)(a.length + 5);
        a3[3] = (byte)(-1);
        a3[4] = (byte)114;
        a3[5] = (byte)(-128);
        a3[6] = (byte)(int)(byte)i;
        a3[7] = (byte)(int)(byte)i0;
        this.broadcastData(com.wakeup.wearfit2.util.DataHandlerUtils.addBytes(a3, a));
    }
    
    public void setSmartWarnNoContent(int i, int i0) {
        android.util.Log.i(TAG, "setSmartWarnNoContent: ");
        byte[] a = new byte[8];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)5;
        a[3] = (byte)(-1);
        a[4] = (byte)114;
        a[5] = (byte)(-128);
        a[6] = (byte)(int)(byte)i;
        a[7] = (byte)(int)(byte)i0;
        this.broadcastData(a);
    }
    
    public void setSyncData(long j) {
        android.util.Log.i(TAG, "setSyncData: ");
        com.wakeup.wearfit2.model.DateModel a = new com.wakeup.wearfit2.model.DateModel(j);
        byte[] a0 = new byte[12];
        a0[0] = (byte)(-85);
        a0[1] = (byte)0;
        a0[2] = (byte)9;
        a0[3] = (byte)(-1);
        a0[4] = (byte)81;
        a0[5] = (byte)(-128);
        a0[7] = (byte)(int)(byte)(a.year + -2000);
        a0[8] = (byte)(int)(byte)a.month;
        a0[9] = (byte)(int)(byte)a.day;
        a0[10] = (byte)(int)(byte)a.hour;
        a0[11] = (byte)(int)(byte)a.minute;
        this.broadcastData(a0);
    }
    
    public void setSyncDataHr(long j, long j0) {
        android.util.Log.i(TAG, "setSyncDataHr: ");
        com.wakeup.wearfit2.model.DateModel a = new com.wakeup.wearfit2.model.DateModel(j);
        com.wakeup.wearfit2.model.DateModel a0 = new com.wakeup.wearfit2.model.DateModel(j0);
        byte[] a1 = new byte[17];
        a1[0] = (byte)(-85);
        a1[1] = (byte)0;
        a1[2] = (byte)14;
        a1[3] = (byte)(-1);
        a1[4] = (byte)81;
        a1[5] = (byte)(-128);
        a1[7] = (byte)(int)(byte)(a.year + -2000);
        a1[8] = (byte)(int)(byte)a.month;
        a1[9] = (byte)(int)(byte)a.day;
        a1[10] = (byte)(int)(byte)a.hour;
        a1[11] = (byte)(int)(byte)a.minute;
        a1[12] = (byte)(int)(byte)(a0.year + -2000);
        a1[13] = (byte)(int)(byte)a0.month;
        a1[14] = (byte)(int)(byte)a0.day;
        a1[15] = (byte)(int)(byte)a0.hour;
        a1[16] = (byte)(int)(byte)a0.minute;
        this.broadcastData(a1);
    }
    
    public void setSyncSleepData(long j) {
        android.util.Log.i(TAG, "setSyncSleepData");
        com.wakeup.wearfit2.model.DateModel a = new com.wakeup.wearfit2.model.DateModel(j);
        byte[] a0 = new byte[12];
        a0[0] = (byte)(-85);
        a0[1] = (byte)0;
        a0[2] = (byte)9;
        a0[3] = (byte)(-1);
        a0[4] = (byte)82;
        a0[5] = (byte)(-128);
        a0[7] = (byte)(int)(byte)(a.year + -2000);
        a0[8] = (byte)(int)(byte)a.month;
        a0[9] = (byte)(int)(byte)a.day;
        a0[10] = (byte)(int)(byte)a.hour;
        a0[11] = (byte)(int)(byte)a.minute;
        this.broadcastData(a0);
    }
    
    public void setTimeSync() {
        android.util.Log.i(TAG, "setTimeSync: ");
        com.wakeup.wearfit2.model.DateModel a = new com.wakeup.wearfit2.model.DateModel(System.currentTimeMillis());
        byte[] a0 = new byte[14];
        a0[0] = (byte)(-85);
        a0[1] = (byte)0;
        a0[2] = (byte)11;
        a0[3] = (byte)(-1);
        a0[4] = (byte)(-109);
        a0[5] = (byte)(-128);
        a0[7] = (byte)(int)(byte)((a.year & 65280) >> 8);
        a0[8] = (byte)(int)(byte)(a.year & 255);
        a0[9] = (byte)(int)(byte)(a.month & 255);
        a0[10] = (byte)(int)(byte)(a.day & 255);
        a0[11] = (byte)(int)(byte)(a.hour & 255);
        a0[12] = (byte)(int)(byte)(a.minute & 255);
        a0[13] = (byte)(int)(byte)(a.second & 255);
        this.broadcastData(a0);
    }
    
    public void setUpHandLightScreen(int i) {
        android.util.Log.i(TAG, "setUpHandLightScreen: ");
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)119;
        a[5] = (byte)(-128);
        a[6] = (byte)(int)(byte)i;
        StringBuilder a0 = new StringBuilder();
        a0.append("\u62ac\u624b\u4eae\u5c4f--");
        a0.append(i);
        android.util.Log.i("lq", a0.toString());
        this.broadcastData(a);
    }
    
    public void setUserInfo(com.wakeup.wearfit2.model.UserModel a) {
        String s = TAG;
        StringBuilder a0 = new StringBuilder();
        a0.append("setUserInfo: ");
        a0.append(a.toString());
        android.util.Log.i(s, a0.toString());
        if (a != null) {
            if (com.wakeup.wearfit2.AppApplication.band_type != 115) {
                byte[] a1 = new byte[13];
                a1[0] = (byte)(-85);
                a1[1] = (byte)0;
                a1[2] = (byte)10;
                a1[3] = (byte)(-1);
                a1[4] = (byte)116;
                a1[5] = (byte)(-128);
                if ("in".equals((Object)a.getHeightUint())) {
                    a1[6] = (byte)(int)(byte)(int)((double)Integer.parseInt(a.getStepLength()) * 2.54);
                } else {
                    a1[6] = (byte)(int)(byte)Integer.parseInt(a.getStepLength());
                }
                a1[7] = (byte)(int)(byte)Integer.parseInt((android.text.TextUtils.isEmpty((CharSequence)(Object)a.getAge())) ? "25" : a.getAge());
                a1[8] = (byte)(int)(byte)Integer.parseInt((a.getHeight() != null) ? a.getHeight() : "170");
                if ("kg".equals((Object)a.getWeightUint())) {
                    a1[9] = (byte)(int)(byte)Integer.parseInt((a.getWeight() != null) ? a.getWeight() : "60");
                } else {
                    a1[9] = (byte)(int)(byte)(int)((double)Integer.parseInt((a.getWeight() != null) ? a.getWeight() : "60") / 2.2);
                }
                if ("km".equals((Object)a.getDistanceUnit())) {
                    a1[10] = (byte)1;
                } else {
                    a1[10] = (byte)0;
                }
                a1[11] = (byte)(int)(byte)(Integer.parseInt(a.getStep_count()) / 1000);
                if (com.wakeup.wearfit2.util.SPUtils.getBoolean(mContext, "sheshidu", true)) {
                    a1[12] = (byte)0;
                } else {
                    a1[12] = (byte)1;
                }
                this.broadcastData(a1);
            } else {
                byte[] a2 = new byte[14];
                a2[0] = (byte)(-85);
                a2[1] = (byte)0;
                a2[2] = (byte)11;
                a2[3] = (byte)(-1);
                a2[4] = (byte)116;
                a2[5] = (byte)(-128);
                if ("in".equals((Object)a.getHeightUint())) {
                    a2[6] = (byte)(int)(byte)(int)((double)Integer.parseInt(a.getStepLength()) * 2.54);
                } else {
                    a2[6] = (byte)(int)(byte)Integer.parseInt(a.getStepLength());
                }
                a2[7] = (byte)(int)(byte)Integer.parseInt((android.text.TextUtils.isEmpty((CharSequence)(Object)a.getAge())) ? "25" : a.getAge());
                a2[8] = (byte)(int)(byte)Integer.parseInt((a.getHeight() != null) ? a.getHeight() : "170");
                if ("kg".equals((Object)a.getWeightUint())) {
                    a2[9] = (byte)(int)(byte)Integer.parseInt((a.getWeight() != null) ? a.getWeight() : "60");
                } else {
                    a2[9] = (byte)(int)(byte)(int)((double)Integer.parseInt((a.getWeight() != null) ? a.getWeight() : "60") / 2.2);
                }
                a2[10] = (byte)0;
                a2[11] = (byte)0;
                if ("km".equals((Object)a.getDistanceUnit())) {
                    a2[12] = (byte)1;
                } else {
                    a2[12] = (byte)0;
                }
                a2[13] = (byte)(int)(byte)(Integer.parseInt(a.getStep_count()) / 1000);
                this.broadcastData(a2);
            }
        }
    }
    
    public void setUserInfo1(com.wakeup.wearfit2.model.UserModel a) {
        android.util.Log.d(TAG, a.toString());
        byte[] a0 = new byte[14];
        a0[0] = (byte)(-85);
        a0[1] = (byte)0;
        a0[2] = (byte)11;
        a0[3] = (byte)(-1);
        a0[4] = (byte)116;
        a0[5] = (byte)(-128);
        if ("in".equals((Object)a.getHeightUint())) {
            a0[6] = (byte)(int)(byte)(int)((double)Integer.parseInt(a.getStepLength()) * 2.54);
        } else {
            a0[6] = (byte)(int)(byte)Integer.parseInt(a.getStepLength());
        }
        a0[7] = (byte)(int)(byte)Integer.parseInt((android.text.TextUtils.isEmpty((CharSequence)(Object)a.getAge())) ? "25" : a.getAge());
        a0[8] = (byte)(int)(byte)Integer.parseInt((a.getHeight() != null) ? a.getHeight() : "170");
        if ("kg".equals((Object)a.getWeightUint())) {
            a0[9] = (byte)(int)(byte)Integer.parseInt((a.getWeight() != null) ? a.getWeight() : "60");
        } else {
            a0[9] = (byte)(int)(byte)(int)((double)Integer.parseInt((a.getWeight() != null) ? a.getWeight() : "60") / 2.2);
        }
        a0[10] = (byte)0;
        a0[11] = (byte)0;
        if ("km".equals((Object)a.getDistanceUnit())) {
            a0[12] = (byte)1;
        } else {
            a0[12] = (byte)0;
        }
        a0[13] = (byte)(int)(byte)(Integer.parseInt(a.getStep_count()) / 1000);
        this.broadcastData(a0);
    }
    
    public void sethongwai(int i) {
        android.util.Log.i(TAG, "\u7ea2\u5916\u53c2\u6570\u8bbe\u7f6e: ");
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)(-104);
        a[5] = (byte)(-128);
        a[6] = (byte)(int)(byte)i;
        this.broadcastData(a);
    }
    
    public void startMeasureEcg() {
        android.util.Log.i(TAG, "startMeasureEcg: ");
        byte[] a = new byte[2];
        a[0] = (byte)(-84);
        a[1] = (byte)1;
        this.broadcastData(a);
    }
    
    public void startSendPic(int i, int i0, int i1, int i2) {
        byte[] a = new byte[8];
        a[0] = (byte)(-83);
        a[1] = (byte)(int)(byte)(i / 256);
        a[2] = (byte)(int)(byte)(i % 256);
        a[3] = (byte)(int)(byte)(i0 / 256);
        a[4] = (byte)(int)(byte)(i0 % 256);
        a[5] = (byte)(int)(byte)(i1 / 256);
        a[6] = (byte)(int)(byte)(i1 % 256);
        a[7] = (byte)(int)(byte)i2;
        this.broadcastData("\u5c4f\u4fdd\u63a8\u9001\u5934\u5c3e", a);
    }
    
    public void stoptMeasureEcg() {
        android.util.Log.i(TAG, "stoptMeasureEcg: ");
        byte[] a = new byte[2];
        a[0] = (byte)(-84);
        a[1] = (byte)0;
        this.broadcastData(a);
    }
    
    public void switchSpeed(int i) {
        byte[] a = new byte[7];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)4;
        a[3] = (byte)(-1);
        a[4] = (byte)(-121);
        a[5] = (byte)(-128);
        a[6] = (byte)(int)(byte)i;
        this.broadcastData("BLE\u9ad8\u4f4e\u901f\u5207\u6362", a);
    }
    
    public void unbind() {
        byte[] a = new byte[6];
        a[0] = (byte)(-85);
        a[1] = (byte)0;
        a[2] = (byte)3;
        a[3] = (byte)(-1);
        a[4] = (byte)(-95);
        a[5] = (byte)(-128);
        this.broadcastData(a);
    }
}
