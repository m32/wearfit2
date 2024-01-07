package com.wakeup.wearfit2.service;

class BluetoothLeService$SendDataToBleReceiver extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.service.BluetoothLeService this$0;
    
    BluetoothLeService$SendDataToBleReceiver(com.wakeup.wearfit2.service.BluetoothLeService a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        if (a0.getAction().equals((Object)"com.wakeup.wearfit2.ACTION_SEND_DATA_TO_BLE")) {
            byte[] a1 = a0.getByteArrayExtra("com.wakeup.wearfit2.EXTRA_SEND_DATA_TO_BLE");
            if (a1 != null) {
                com.wakeup.wearfit2.service.BluetoothLeService.access$700(this.this$0, a1, false);
            }
        }
    }
}
