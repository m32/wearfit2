package com.wakeup.wearfit2.service;

class BluetoothLeService$2 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.service.BluetoothLeService this$0;
    
    BluetoothLeService$2(com.wakeup.wearfit2.service.BluetoothLeService a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        if (a0.getAction().equals((Object)"android.bluetooth.adapter.action.STATE_CHANGED")) {
            if (a0.getIntExtra("android.bluetooth.adapter.extra.STATE", -1) != 10) {
                if (a0.getIntExtra("android.bluetooth.adapter.extra.STATE", -1) == 12) {
                    android.util.Log.i(com.wakeup.wearfit2.service.BluetoothLeService.access$000(), "BluetoothAdapter.STATE_ON  \u84dd\u7259\u5f00\u542f\u4e86");
                }
            } else {
                android.util.Log.i(com.wakeup.wearfit2.service.BluetoothLeService.access$000(), "BluetoothAdapter.STATE_OFF \u84dd\u7259\u5173\u95ed\u4e86");
            }
        }
    }
}
