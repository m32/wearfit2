package com.wakeup.wearfit2.util;

class BleUtil$5 extends com.clj.fastble.callback.BleNotifyCallback {
    final com.wakeup.wearfit2.util.BleUtil this$0;
    
    BleUtil$5(com.wakeup.wearfit2.util.BleUtil a) {
        this.this$0 = a;
        super();
    }
    
    public void onCharacteristicChanged(byte[] a) {
        StringBuilder a0 = new StringBuilder();
        a0.append("\u6765\u6d88\u606f:");
        a0.append(new com.google.gson.Gson().toJson((Object)a));
        com.wakeup.wearfit2.util.LogUtil.e("BleUtil", a0.toString());
        if (a == null) {
            return;
        }
        android.content.Intent a1 = new android.content.Intent("com.wakeup.wearfit2.BROADCAST_DATA_AVAILABLE");
        a1.putExtra("com.wakeup.wearfit2.EXTRA_DATA", a);
        com.wakeup.wearfit2.AppApplication.getAppContext().sendBroadcast(a1);
        com.wakeup.wearfit2.util.BleUtil.access$400(this.this$0, a);
    }
    
    public void onNotifyFailure(String s, com.clj.fastble.exception.BleException a) {
        StringBuilder a0 = new StringBuilder();
        a0.append("\u6253\u5f00\u901a\u77e5\u5931\u8d25:code = ");
        a0.append(a.getCode());
        a0.append("    msg = ");
        a0.append(a.getDescription());
        com.wakeup.wearfit2.util.LogUtil.e("BleUtil", a0.toString());
    }
    
    public void onNotifySuccess(String s) {
        com.wakeup.wearfit2.util.LogUtil.e("BleUtil", "\u6253\u5f00\u901a\u77e5\u6210\u529f");
        com.clj.fastble.data.BleDevice a = com.wakeup.wearfit2.util.BleUtil.access$300(this.this$0);
        new android.os.Handler().postDelayed((Runnable)(Object)new com.wakeup.wearfit2.util.BleUtil$5$1(this), 4000L);
        if (a == null) {
            return;
        }
        android.content.Intent a0 = new android.content.Intent("com.wakeup.wearfit2.DEVICE_READY");
        a0.putExtra("com.wakeup.wearfit2.EXTRA_DEVICE", (android.os.Parcelable)(Object)a.getDevice());
        com.wakeup.wearfit2.AppApplication.getAppContext().sendBroadcast(a0);
    }
}
