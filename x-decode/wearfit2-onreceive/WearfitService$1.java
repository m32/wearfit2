package com.wakeup.wearfit2.ble;

class WearfitService$1 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.ble.WearfitService this$0;
    
    WearfitService$1(com.wakeup.wearfit2.ble.WearfitService a) {
        this.this$0 = a;
        super();
    }
    
    private String state2String(int i) {
        switch(i) {
            case 13: {
                return "TURNING OFF";
            }
            case 12: {
                return "ON";
            }
            case 11: {
                return "TURNING ON";
            }
            case 10: {
                return "OFF";
            }
            default: {
                StringBuilder a = new StringBuilder();
                a.append("UNKNOWN (");
                a.append(i);
                a.append(")");
                return a.toString();
            }
        }
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        int i = a0.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
        StringBuilder a1 = new StringBuilder();
        a1.append("[Broadcast] Action received: android.bluetooth.adapter.action.STATE_CHANGED, state changed to ");
        a1.append(this.state2String(i));
        String s = a1.toString();
        android.util.Log.i(com.wakeup.wearfit2.ble.WearfitService.access$000(), s);
        if (i == 10) {
            com.wakeup.wearfit2.Biz.AutoConnectBleBiz.getInstance().cleanSearchedDevices();
            android.util.Log.e("liu0203", "\u84dd\u7259\u5173\u95ed");
            com.wakeup.wearfit2.AppApplication.batteryPercent = 0;
            com.wakeup.wearfit2.AppApplication.ota_version = 0.0f;
            org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.DEVICE_CONNECT_CHANGE));
            com.wakeup.wearfit2.util.BleUtil.getInstance().disconnectDevice();
        } else if (i == 12) {
            com.wakeup.wearfit2.Biz.AutoConnectBleBiz.getInstance().cleanSearchedDevices();
            android.util.Log.e("liu0203", "\u84dd\u7259\u5f00\u542f");
        }
    }
}
