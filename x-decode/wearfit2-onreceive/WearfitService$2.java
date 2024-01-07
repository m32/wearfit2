package com.wakeup.wearfit2.ble;

class WearfitService$2 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.ble.WearfitService this$0;
    
    WearfitService$2(com.wakeup.wearfit2.ble.WearfitService a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        int i = 0;
        String s = a0.getAction();
        if (s == null) {
            return;
        }
        s.hashCode();
        switch(s.hashCode()) {
            case 1985988626: {
                i = (s.equals((Object)"com.wakeup.wearfit2.ACTION_SEND_DATA_TO_BLE")) ? 2 : -1;
                break;
            }
            case 1158309611: {
                i = (s.equals((Object)"USER_UNBIND_DEVICE")) ? 1 : -1;
                break;
            }
            case -1658084564: {
                i = (s.equals((Object)"connect_classic_bluetooth")) ? 0 : -1;
                break;
            }
            default: {
                i = -1;
            }
        }
        switch(i) {
            case 2: {
                String s0 = a0.getStringExtra("com.wakeup.wearfit2.EXTRA_SEND_DATA_TO_BLE_ORDER");
                if (s0 == null) {
                    s0 = "";
                }
                com.wakeup.wearfit2.util.BleUtil.getInstance().writeBytes(s0, a0.getByteArrayExtra("com.wakeup.wearfit2.EXTRA_SEND_DATA_TO_BLE"));
                break;
            }
            case 1: {
                android.util.Log.i(com.wakeup.wearfit2.ble.WearfitService.access$000(), "USER_UNBIND_DEVICE");
                com.wakeup.wearfit2.AppApplication.batteryPercent = 0;
                com.wakeup.wearfit2.AppApplication.ota_version = 0.0f;
                org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.DEVICE_CONNECT_CHANGE));
                com.wakeup.wearfit2.util.BleUtil.getInstance().disconnectDevice();
                if ("".equals((Object)com.wakeup.wearfit2.ble.WearfitService.access$100(this.this$0))) {
                    break;
                }
                com.wakeup.wearfit2.ble.WearfitService a1 = this.this$0;
                com.wakeup.wearfit2.ble.WearfitService.access$300(a1, com.wakeup.wearfit2.ble.WearfitService.access$200(a1));
                break;
            }
            case 0: {
                com.wakeup.wearfit2.ble.WearfitService.access$400(this.this$0);
                break;
            }
        }
    }
}
