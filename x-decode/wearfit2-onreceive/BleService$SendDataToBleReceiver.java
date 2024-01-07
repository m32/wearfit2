package com.wakeup.wearfit2.service;

class BleService$SendDataToBleReceiver extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.service.BleService this$0;
    
    BleService$SendDataToBleReceiver(com.wakeup.wearfit2.service.BleService a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        if (a0.getAction().equals((Object)"com.wakeup.wearfit2.ACTION_SEND_DATA_TO_BLE")) {
            byte[] a1 = a0.getByteArrayExtra("EXTRA_SEND_DATA_TO_BLE");
            if (a1 != null) {
                com.wakeup.wearfit2.service.BleService.access$2000(this.this$0, a1, false);
            }
        }
    }
}
