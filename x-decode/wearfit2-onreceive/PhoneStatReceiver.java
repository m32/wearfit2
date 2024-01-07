package com.wakeup.wearfit2.receiver;

public class PhoneStatReceiver extends android.content.BroadcastReceiver {
    final private static String TAG = "PhoneStatReceiver";
    private static boolean incomingFlag = false;
    
    static {
    }
    
    public PhoneStatReceiver() {
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        if (a0.getAction().equals((Object)"android.intent.action.NEW_OUTGOING_CALL")) {
            incomingFlag = false;
            String s = a0.getStringExtra("android.intent.extra.PHONE_NUMBER");
            String s0 = TAG;
            StringBuilder a1 = new StringBuilder();
            a1.append("call OUT:");
            a1.append(s);
            android.util.Log.i(s0, a1.toString());
        } else {
            int i = ((android.telephony.TelephonyManager)a.getSystemService("phone")).getCallState();
            if (i == 0) {
                android.util.Log.i(TAG, "CALL_STATE_IDLE");
                com.wakeup.wearfit2.AppApplication.PHONE_STATE = 0;
                if (incomingFlag && com.wakeup.wearfit2.AppApplication.isIncallWarnOn && com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) {
                    com.wakeup.wearfit2.manager.CommandManager.getInstance(a).setSmartWarnNoContent(2, 2);
                }
            } else if (i == 1) {
                incomingFlag = true;
                com.wakeup.wearfit2.AppApplication.PHONE_STATE = 1;
                String s1 = a0.getStringExtra("incoming_number");
                String s2 = TAG;
                StringBuilder a2 = new StringBuilder();
                a2.append("RINGING :");
                a2.append(s1);
                android.util.Log.i(s2, a2.toString());
                StringBuilder a3 = new StringBuilder();
                a3.append(com.wakeup.wearfit2.AppApplication.isIncallWarnOn);
                a3.append("/");
                a3.append(com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected());
                android.util.Log.i(s2, a3.toString());
                if (com.wakeup.wearfit2.AppApplication.isIncallWarnOn && com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) {
                    if (com.wakeup.wearfit2.AppApplication.band_type != 106) {
                        String s3 = com.wakeup.wearfit2.util.AbAppUtil.getDisplayNameByNumber(a, s1);
                        StringBuilder a4 = new StringBuilder();
                        a4.append("displayNameByNumber:");
                        a4.append(s3);
                        android.util.Log.d(s2, a4.toString());
                        if (com.wakeup.wearfit2.AppApplication.band_type != 240) {
                            if (android.text.TextUtils.isEmpty((CharSequence)(Object)s3)) {
                                if (android.text.TextUtils.isEmpty((CharSequence)(Object)s1)) {
                                    s1 = "\u672a\u77e5\u8054\u7cfb\u4eba";
                                }
                                StringBuilder a5 = new StringBuilder();
                                a5.append("incoming_number: ");
                                a5.append(s1);
                                android.util.Log.i(s2, a5.toString());
                                com.wakeup.wearfit2.manager.CommandManager.getInstance(a).setSmartWarn(1, 2, s1);
                            } else {
                                StringBuilder a6 = new StringBuilder();
                                a6.append("displayNameByNumber: ");
                                a6.append(s3);
                                android.util.Log.i(s2, a6.toString());
                                com.wakeup.wearfit2.manager.CommandManager.getInstance(a).setSmartWarn(1, 2, s3);
                            }
                        } else if (android.text.TextUtils.isEmpty((CharSequence)(Object)s3)) {
                            StringBuilder a7 = new StringBuilder();
                            a7.append("incoming_number: ");
                            a7.append(s1);
                            android.util.Log.i(s2, a7.toString());
                            com.wakeup.wearfit2.manager.CommandManager.getInstance(a).setSmartWarn(1, 2, s1);
                        } else {
                            StringBuilder a8 = new StringBuilder();
                            a8.append("displayNameByNumber+incoming_number: ");
                            a8.append(s3);
                            a8.append(":");
                            a8.append(s1);
                            android.util.Log.i(s2, a8.toString());
                            com.wakeup.wearfit2.manager.CommandManager a9 = com.wakeup.wearfit2.manager.CommandManager.getInstance(a);
                            StringBuilder a10 = new StringBuilder();
                            a10.append(s3);
                            a10.append(":");
                            a10.append(s1);
                            a9.setSmartWarn(1, 2, a10.toString());
                        }
                    } else {
                        android.util.Log.i(s2, "106");
                        com.wakeup.wearfit2.manager.CommandManager.getInstance(a).setSmartWarn(1, 2, s1);
                    }
                }
            } else if (i == 2) {
                String s4 = TAG;
                android.util.Log.i(s4, "CALL_STATE_OFFHOOK");
                com.wakeup.wearfit2.AppApplication.PHONE_STATE = 2;
                if (incomingFlag && com.wakeup.wearfit2.AppApplication.isIncallWarnOn && com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) {
                    com.wakeup.wearfit2.manager.CommandManager.getInstance(a).setSmartWarnNoContent(2, 2);
                }
                android.util.Log.i(s4, "\u63a5\u901a\u7535\u8bdd");
            }
        }
    }
}
