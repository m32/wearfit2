package com.wakeup.wearfit2.receiver;

public class SmsReceiver extends android.content.BroadcastReceiver {
    final private static String TAG = "SmsReceiver";
    
    static {
    }
    
    public SmsReceiver() {
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        String s = null;
        android.telephony.SmsMessage a1 = android.telephony.SmsMessage.createFromPdu((byte[])((Object[])a0.getExtras().get("pdus"))[0]);
        String s0 = a1.getDisplayOriginatingAddress();
        String s1 = com.wakeup.wearfit2.util.AbAppUtil.getDisplayNameByNumber(a, s0);
        if (android.text.TextUtils.isEmpty((CharSequence)(Object)s1)) {
            StringBuilder a2 = new StringBuilder();
            a2.append(s0);
            a2.append(":");
            a2.append(a1.getMessageBody());
            s = a2.toString();
        } else {
            StringBuilder a3 = new StringBuilder();
            a3.append(s1);
            a3.append(":");
            a3.append(a1.getMessageBody());
            s = a3.toString();
        }
        String s2 = TAG;
        StringBuilder a4 = new StringBuilder();
        a4.append("sender:");
        a4.append(s0);
        a4.append(" messageBody");
        a4.append(s);
        android.util.Log.e(s2, a4.toString());
        if (com.wakeup.wearfit2.util.SPUtils.getBoolean(a, com.wakeup.wearfit2.util.SPUtils.JIACHANG_MESSAGE_LENGTH, false)) {
            if (s.length() > 48) {
                StringBuilder a5 = new StringBuilder();
                a5.append(s.substring(0, 48));
                a5.append("...");
                s = a5.toString();
            }
        } else if (com.wakeup.wearfit2.AppApplication.band_type != 240) {
            if (s.length() > 33) {
                StringBuilder a6 = new StringBuilder();
                a6.append(s.substring(0, 33));
                a6.append("...");
                s = a6.toString();
            }
        } else if (s.length() > 200) {
            StringBuilder a7 = new StringBuilder();
            a7.append(s.substring(0, 200));
            a7.append("...");
            s = a7.toString();
        }
        if (com.wakeup.wearfit2.AppApplication.isInSMSWarnOn) {
            com.wakeup.wearfit2.manager.CommandManager.getInstance(a).setSmartWarn(3, 2, s);
        }
    }
}
