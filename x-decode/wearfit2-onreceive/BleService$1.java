package com.wakeup.wearfit2.service;

class BleService$1 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.service.BleService this$0;
    
    BleService$1(com.wakeup.wearfit2.service.BleService a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        if ("android.bluetooth.adapter.action.STATE_CHANGED".equals((Object)a0.getAction())) {
            if (com.wakeup.wearfit2.service.BleService.access$000(this.this$0) == null) {
                com.wakeup.wearfit2.service.BleService a1 = this.this$0;
                com.wakeup.wearfit2.service.BleService.access$102(a1, (android.bluetooth.BluetoothManager)a1.getSystemService("bluetooth"));
                com.wakeup.wearfit2.service.BleService a2 = this.this$0;
                com.wakeup.wearfit2.service.BleService.access$002(a2, com.wakeup.wearfit2.service.BleService.access$100(a2).getAdapter());
            }
            if (a0.getIntExtra("android.bluetooth.adapter.extra.STATE", 10) != 10) {
                if (a0.getIntExtra("android.bluetooth.adapter.extra.STATE", 12) == 12) {
                    android.util.Log.i("wxkkkk", "\u84dd\u7259\u91cd\u542f");
                    this.this$0.intentAction = "com.wakeup.wearfit2.ACTION_CONNECT_BLE_DEVICE";
                    com.wakeup.wearfit2.service.BleService a3 = this.this$0;
                    com.wakeup.wearfit2.service.BleService.access$300(a3, a3.intentAction);
                    com.wakeup.wearfit2.service.BleService.access$202(this.this$0, true);
                }
            } else {
                com.wakeup.wearfit2.service.BleService.access$202(this.this$0, false);
                this.this$0.intentAction = "com.wakeup.wearfit2.ACTION_DISCONNECT_BLE_DEVICE";
                com.wakeup.wearfit2.service.BleService a4 = this.this$0;
                com.wakeup.wearfit2.service.BleService.access$300(a4, a4.intentAction);
                if (com.wakeup.wearfit2.service.BleService.access$400(this.this$0)) {
                    com.wakeup.wearfit2.service.BleService.access$600(this.this$0).sendEmptyMessage(com.wakeup.wearfit2.service.BleService.access$500(this.this$0));
                } else if (com.wakeup.wearfit2.service.BleService.mConnectionState != 0) {
                    com.wakeup.wearfit2.service.BleService.access$702(this.this$0, true);
                    this.this$0.close();
                } else {
                    this.this$0.close();
                }
            }
        }
    }
}
