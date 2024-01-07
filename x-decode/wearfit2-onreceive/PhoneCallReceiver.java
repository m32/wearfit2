package com.wakeup.wearfit2.receiver;

abstract public class PhoneCallReceiver extends android.content.BroadcastReceiver {
    final private static String TAG = "PhoneCallReceiver";
    private static java.util.Date callStartTime;
    public static boolean isIncoming;
    private static int lastState;
    private static String savedNumber;
    
    static {
    }
    
    public PhoneCallReceiver() {
    }
    
    public void onCallStateChanged(android.content.Context a, int i, String s) {
        String s0 = TAG;
        StringBuilder a0 = new StringBuilder();
        a0.append("onCallStateChanged:");
        a0.append(s);
        android.util.Log.i(s0, a0.toString());
        if (i == 0) {
            if (lastState != 1) {
                if (isIncoming) {
                    this.onIncomingCallEnded(a, savedNumber, callStartTime, new java.util.Date());
                } else {
                    this.onOutgoingCallEnded(a, savedNumber, callStartTime, new java.util.Date());
                }
            } else {
                this.onMissedCall(a, savedNumber, callStartTime);
            }
        } else if (i == 1) {
            isIncoming = true;
            java.util.Date a1 = new java.util.Date();
            callStartTime = a1;
            savedNumber = s;
            this.onIncomingCallReceived(a, s, a1);
        } else if (i == 2) {
            if (lastState == 1) {
                isIncoming = true;
                java.util.Date a2 = new java.util.Date();
                callStartTime = a2;
                this.onIncomingCallAnswered(a, savedNumber, a2);
            } else {
                isIncoming = false;
                java.util.Date a3 = new java.util.Date();
                callStartTime = a3;
                this.onOutgoingCallStarted(a, savedNumber, a3);
            }
        }
        lastState = i;
    }
    
    abstract protected void onIncomingCallAnswered(android.content.Context arg, String arg0, java.util.Date arg1);
    
    
    abstract protected void onIncomingCallEnded(android.content.Context arg, String arg0, java.util.Date arg1, java.util.Date arg2);
    
    
    abstract protected void onIncomingCallReceived(android.content.Context arg, String arg0, java.util.Date arg1);
    
    
    abstract protected void onMissedCall(android.content.Context arg, String arg0, java.util.Date arg1);
    
    
    abstract protected void onOutgoingCallEnded(android.content.Context arg, String arg0, java.util.Date arg1, java.util.Date arg2);
    
    
    abstract protected void onOutgoingCallStarted(android.content.Context arg, String arg0, java.util.Date arg1);
    
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        if (a0.getAction().equals((Object)"android.intent.action.NEW_OUTGOING_CALL")) {
            savedNumber = a0.getExtras().getString("android.intent.extra.PHONE_NUMBER");
            String s = TAG;
            StringBuilder a1 = new StringBuilder();
            a1.append("savedNumber:");
            a1.append(savedNumber);
            android.util.Log.i(s, a1.toString());
        } else {
            String s0 = a0.getExtras().getString("state");
            String s1 = a0.getExtras().getString("incoming_number");
            String s2 = TAG;
            StringBuilder a2 = new StringBuilder();
            a2.append("number:");
            a2.append(s1);
            android.util.Log.i(s2, a2.toString());
            this.onCallStateChanged(a, (s0.equals((Object)android.telephony.TelephonyManager.EXTRA_STATE_IDLE)) ? 0 : (s0.equals((Object)android.telephony.TelephonyManager.EXTRA_STATE_OFFHOOK)) ? 2 : (s0.equals((Object)android.telephony.TelephonyManager.EXTRA_STATE_RINGING)) ? 1 : 0, s1);
        }
    }
}
