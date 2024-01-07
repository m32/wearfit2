package com.wakeup.wearfit2.service;

class WakeBleService$2 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.service.WakeBleService this$0;
    
    WakeBleService$2(com.wakeup.wearfit2.service.WakeBleService a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        String s = a0.getAction();
        if (s.equals((Object)"com.wakeup.wearfit2.ACTION_SEND_DATA_TO_BLE")) {
            android.util.Log.i(com.wakeup.wearfit2.service.WakeBleService.access$200(), "\u6536\u5230\u5e7f\u64ad--\u53d1\u9001\u6570\u636e\u5230\u84dd\u7259\u8bbe\u5907");
            byte[] a1 = a0.getByteArrayExtra("com.wakeup.wearfit2.EXTRA_SEND_DATA_TO_BLE");
            if (a1 != null) {
                com.wakeup.wearfit2.service.WakeBleService.access$500(this.this$0, a1, false);
            }
        } else if (s.equals((Object)"USER_UNBIND_DEVICE")) {
            android.util.Log.i(com.wakeup.wearfit2.service.WakeBleService.access$200(), "USER_UNBIND_DEVICE");
            this.this$0.disconnect();
        }
    }
}
