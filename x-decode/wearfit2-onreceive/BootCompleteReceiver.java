package com.wakeup.wearfit2.receiver;

public class BootCompleteReceiver extends android.content.BroadcastReceiver {
    public BootCompleteReceiver() {
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        if (a0.getAction().equals((Object)"android.intent.action.SCREEN_OFF")) {
            com.wakeup.wearfit2.ui.activity.HooliganActivity.startHooligan();
        } else if (a0.getAction().equals((Object)"android.intent.action.SCREEN_ON")) {
            com.wakeup.wearfit2.ui.activity.HooliganActivity.killHooligan();
        }
    }
}
