package com.wakeup.wearfit2.ui.activity.heartrate;

class HeartRate2Acitivity$2 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.ui.activity.heartrate.HeartRate2Acitivity this$0;
    
    HeartRate2Acitivity$2(com.wakeup.wearfit2.ui.activity.heartrate.HeartRate2Acitivity a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        String s = a0.getAction();
        if (s.equals((Object)"com.wakeup.wearfit2.BROADCAST_DATA_AVAILABLE")) {
            byte[] a1 = a0.getByteArrayExtra("com.wakeup.wearfit2.EXTRA_DATA");
            android.util.Log.d(com.wakeup.wearfit2.ui.activity.heartrate.HeartRate2Acitivity.access$100(), "ecg ACTION_DATA_AVAILABLE");
            if (a1 == null) {
                return;
            }
            if (com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a1) == null) {
                return;
            }
            android.util.Log.e(com.wakeup.wearfit2.ui.activity.heartrate.HeartRate2Acitivity.access$100(), com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a1));
            java.util.List a2 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToArrayList(a1);
            if (((Integer)a2.get(0)).intValue() == 171 && ((Integer)a2.get(4)).intValue() == 132) {
                int i = ((Integer)a2.get(6)).intValue();
                android.os.Message a3 = android.os.Message.obtain();
                a3.what = 3;
                a3.arg1 = i;
                com.wakeup.wearfit2.ui.activity.heartrate.HeartRate2Acitivity.access$200(this.this$0).sendMessage(a3);
            }
        } else if (s.equals((Object)"com.wakeup.wearfit2.ACTION_GATT_CONNECTED")) {
            String s0 = com.wakeup.wearfit2.ui.activity.heartrate.HeartRate2Acitivity.access$100();
            StringBuilder a4 = new StringBuilder();
            a4.append(com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected());
            a4.append("");
            android.util.Log.i(s0, a4.toString());
            if (this.this$0.mIvBeat == null) {
                return;
            }
            this.this$0.mIvBeat.startAnimation((android.view.animation.Animation)this.this$0.animationSet);
        } else if (s.equals((Object)"com.wakeup.wearfit2.ACTION_GATT_DISCONNECTED")) {
            android.util.Log.e(com.wakeup.wearfit2.ui.activity.heartrate.HeartRate2Acitivity.access$100(), "\u65ad\u5f00\u8fde\u63a5");
            this.this$0.mIvBeat.clearAnimation();
            this.this$0.mMyTextView.setTextSize(8f);
        }
    }
}
