package com.wakeup.wearfit2.util;

public class BleUtil {
    final public static int CONNECT_BLEDEVICE = 2;
    final public static int CONNECT_MAC = 1;
    final public static String NOTIFY_CHARACTERISTIC_UUID = "6E400003-B5A3-F393-E0A9-E50E24DCCA9E";
    final public static int SCAN_AND_CONNECT = 3;
    final public static String SERVICE_UUID = "6E400001-B5A3-F393-E0A9-E50E24DCCA9E";
    final public static String TAG = "BleUtil";
    final public static String WRITE_CHARACTERISTIC_UUID = "6E400002-B5A3-F393-E0A9-E50E24DCCA9E";
    public static int connectType;
    private static com.wakeup.wearfit2.util.BleUtil instance;
    private com.clj.fastble.callback.BleGattCallback gattCallback;
    private com.clj.fastble.callback.BleNotifyCallback notifyCallback;
    
    public BleUtil() {
    }
    
    static void access$000(com.wakeup.wearfit2.util.BleUtil a, com.clj.fastble.data.BleDevice a0, android.bluetooth.BluetoothGatt a1, int i) {
        a.connectSuccess(a0, a1, i);
    }
    
    static void access$100(com.wakeup.wearfit2.util.BleUtil a, boolean b, com.clj.fastble.data.BleDevice a0, android.bluetooth.BluetoothGatt a1, int i) {
        a.disConnected(b, a0, a1, i);
    }
    
    static void access$200(com.wakeup.wearfit2.util.BleUtil a, com.clj.fastble.data.BleDevice a0, com.clj.fastble.exception.BleException a1) {
        a.connectFail(a0, a1);
    }
    
    static com.clj.fastble.data.BleDevice access$300(com.wakeup.wearfit2.util.BleUtil a) {
        return a.getBleDevice();
    }
    
    static void access$400(com.wakeup.wearfit2.util.BleUtil a, byte[] a0) {
        a.musicControl(a0);
    }
    
    private void connectFail(com.clj.fastble.data.BleDevice a, com.clj.fastble.exception.BleException a0) {
        org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.BleConnectResultEvent("\u8fde\u63a5\u5931\u8d25"));
    }
    
    private void connectSuccess(com.clj.fastble.data.BleDevice a, android.bluetooth.BluetoothGatt a0, int i) {
        org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.BleConnectResultEvent("\u8fde\u63a5\u6210\u529f", a.getName(), a.getMac()));
        android.content.Intent a1 = new android.content.Intent("com.wakeup.wearfit2.BROADCAST_CONNECTION_STATE");
        a1.putExtra("com.wakeup.wearfit2.EXTRA_CONNECTION_STATE", 1);
        a1.putExtra("com.wakeup.wearfit2.EXTRA_DEVICE", (android.os.Parcelable)(Object)a.getDevice());
        a1.putExtra("com.wakeup.wearfit2.EXTRA_DEVICE_NAME", a.getName());
        com.wakeup.wearfit2.AppApplication.getAppContext().sendBroadcast(a1);
        org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.DEVICE_CONNECT_CHANGE));
        com.wakeup.wearfit2.util.SPUtils.putBoolean(com.wakeup.wearfit2.AppApplication.getAppContext(), "operation", false);
        this.openNotify();
    }
    
    private void controlMusic(int i) {
        long j = android.os.SystemClock.uptimeMillis();
        android.view.KeyEvent a = new android.view.KeyEvent(j, j, 0, i, 0);
        this.dispatchMediaKeyToAudioService(a);
        this.dispatchMediaKeyToAudioService(android.view.KeyEvent.changeAction(a, 1));
    }
    
    private void disConnected(boolean b, com.clj.fastble.data.BleDevice a, android.bluetooth.BluetoothGatt a0, int i) {
        android.content.Intent a1 = new android.content.Intent("com.wakeup.wearfit2.BROADCAST_CONNECTION_STATE");
        a1.putExtra("com.wakeup.wearfit2.EXTRA_DEVICE", (android.os.Parcelable)(Object)a.getDevice());
        a1.putExtra("com.wakeup.wearfit2.EXTRA_CONNECTION_STATE", 0);
        com.wakeup.wearfit2.AppApplication.getAppContext().sendBroadcast(a1);
        com.wakeup.wearfit2.AppApplication.batteryPercent = 0;
        com.wakeup.wearfit2.AppApplication.ota_version = 0.0f;
        org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.BleConnectResultEvent("\u8fde\u63a5\u65ad\u5f00", a.getName(), a.getMac()));
        org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.DEVICE_CONNECT_CHANGE));
    }
    
    private void dispatchMediaKeyToAudioService(android.view.KeyEvent a) {
        android.media.AudioManager a0 = (android.media.AudioManager)com.wakeup.wearfit2.AppApplication.getAppContext().getSystemService("audio");
        label1: {
            Exception a1 = null;
            if (a0 == null) {
                break label1;
            }
            label0: {
                try {
                    a0.dispatchMediaKeyEvent(a);
                } catch(Exception a2) {
                    a1 = a2;
                    break label0;
                }
                break label1;
            }
            a1.printStackTrace();
        }
    }
    
    private com.clj.fastble.data.BleDevice getBleDevice() {
        java.util.List a = com.clj.fastble.BleManager.getInstance().getAllConnectedDevice();
        if (a != null && !a.isEmpty()) {
            return (com.clj.fastble.data.BleDevice)a.get(0);
        }
        com.clj.fastble.data.BleDevice a0 = null;
        return a0;
    }
    
    public static com.wakeup.wearfit2.util.BleUtil getInstance() {
        /*monenter(com.wakeup.wearfit2.util.BleUtil.class)*/;
        com.wakeup.wearfit2.util.BleUtil a = instance;
        label0: {
            Throwable a0 = null;
            if (a != null) {
                break label0;
            }
            try {
                instance = new com.wakeup.wearfit2.util.BleUtil();
                break label0;
            } catch(Throwable a1) {
                a0 = a1;
            }
            /*monexit(com.wakeup.wearfit2.util.BleUtil.class)*/;
            throw a0;
        }
        com.wakeup.wearfit2.util.BleUtil a2 = instance;
        /*monexit(com.wakeup.wearfit2.util.BleUtil.class)*/;
        return a2;
    }
    
    private void musicControl(byte[] a) {
        java.util.List a0 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToArrayList(a);
        if (((Integer)a0.get(0)).intValue() == 171 && ((Integer)a0.get(4)).intValue() == 153) {
            int i = ((Integer)a0.get(6)).intValue();
            if (i == 0) {
                com.wakeup.wearfit2.util.LogUtil.e("BleUtil", "\u64ad\u653e\u6216\u6682\u505c");
                this.controlMusic(85);
            } else if (i == 1) {
                com.wakeup.wearfit2.util.LogUtil.e("BleUtil", "\u4e0a\u4e00\u9996");
                this.controlMusic(88);
            } else if (i == 2) {
                com.wakeup.wearfit2.util.LogUtil.e("BleUtil", "\u4e0b\u4e00\u9996");
                this.controlMusic(87);
            }
        }
    }
    
    public void close() {
        com.wakeup.wearfit2.util.LogUtil.e("BleUtil", "close");
        com.clj.fastble.BleManager.getInstance().destroy();
        this.gattCallback = null;
        this.notifyCallback = null;
        instance = null;
    }
    
    public void connect(com.clj.fastble.data.BleDevice a) {
        connectType = 2;
        this.disconnectDevice();
        new android.os.Handler().postDelayed((Runnable)(Object)new com.wakeup.wearfit2.util.BleUtil$2(this, a), 4000L);
    }
    
    public void connect(String s) {
        connectType = 1;
        StringBuilder a = new StringBuilder();
        a.append("\u8fde\u63a5\u84dd\u7259\uff1aMac = ");
        a.append(s);
        com.wakeup.wearfit2.util.LogUtil.e("BleUtil", a.toString());
        com.clj.fastble.BleManager.getInstance().connect(s, this.getGattCallback());
    }
    
    public void disconnectDevice() {
        com.wakeup.wearfit2.util.LogUtil.e("BleUtil", "\u65ad\u5f00\u6240\u6709\u84dd\u7259");
        com.clj.fastble.BleManager.getInstance().disconnectAllDevice();
    }
    
    public com.clj.fastble.callback.BleGattCallback getGattCallback() {
        if (this.gattCallback == null) {
            this.gattCallback = new com.wakeup.wearfit2.util.BleUtil$4(this);
        }
        return this.gattCallback;
    }
    
    public com.clj.fastble.callback.BleNotifyCallback getNotifyCallback() {
        if (this.notifyCallback == null) {
            this.notifyCallback = new com.wakeup.wearfit2.util.BleUtil$5(this);
        }
        return this.notifyCallback;
    }
    
    public boolean isBlueEnable() {
        if (!com.clj.fastble.BleManager.getInstance().isSupportBle()) {
            com.wakeup.wearfit2.util.LogUtil.e("BleUtil", "\u4e0d\u652f\u6301\u84dd\u7259\uff0c\u8fde\u5565\uff1f");
            return false;
        }
        if (com.clj.fastble.BleManager.getInstance().isBlueEnable()) {
            return true;
        }
        com.wakeup.wearfit2.util.LogUtil.e("BleUtil", "\u84dd\u7259\u672a\u5f00\u542f\uff0c\u8fde\u5565\uff1f");
        return false;
    }
    
    public boolean isConnected() {
        return com.clj.fastble.BleManager.getInstance().getConnectState(this.getBleDevice()) == 2;
    }
    
    public void openNotify() {
        com.clj.fastble.data.BleDevice a = this.getBleDevice();
        if (a == null) {
            com.wakeup.wearfit2.util.LogUtil.e("BleUtil", "\u6253\u5f00\u901a\u77e5\u5931\u8d25\uff1a\u6ca1\u6709\u627e\u5230\u8bbe\u5907");
            return;
        }
        com.wakeup.wearfit2.util.LogUtil.e("BleUtil", "\u5f00\u59cb\u6253\u5f00\u901a\u77e5");
        com.clj.fastble.BleManager.getInstance().notify(a, "6E400001-B5A3-F393-E0A9-E50E24DCCA9E", "6E400003-B5A3-F393-E0A9-E50E24DCCA9E", this.getNotifyCallback());
    }
    
    public void scanAndConnect(String s) {
        connectType = 3;
        StringBuilder a = new StringBuilder();
        a.append("\u641c\u7d22\u5e76\u8fde\u63a5\uff1amac = ");
        a.append(s);
        com.wakeup.wearfit2.util.LogUtil.e("BleUtil", a.toString());
        com.clj.fastble.BleManager.getInstance().initScanRule(new com.clj.fastble.scan.BleScanRuleConfig$Builder().setAutoConnect(true).setDeviceMac(s).build());
        com.clj.fastble.BleManager.getInstance().scanAndConnect((com.clj.fastble.callback.BleScanAndConnectCallback)new com.wakeup.wearfit2.util.BleUtil$1(this));
    }
    
    public void writeBytes(String s, byte[] a) {
        com.clj.fastble.data.BleDevice a0 = this.getBleDevice();
        if (a0 == null) {
            StringBuilder a1 = new StringBuilder();
            a1.append("\u5199\u5165\u6570\u636e\u5931\u8d25\uff1a\u6ca1\u6709\u627e\u5230\u8bbe\u5907:");
            a1.append(new com.google.gson.Gson().toJson((Object)a));
            com.wakeup.wearfit2.util.LogUtil.e("BleUtil", a1.toString());
            return;
        }
        StringBuilder a2 = new StringBuilder();
        a2.append("\u5f00\u59cb\u5199\u5165\u6570\u636e\uff1a");
        a2.append(new com.google.gson.Gson().toJson((Object)a));
        com.wakeup.wearfit2.util.LogUtil.e("BleUtil", a2.toString());
        com.clj.fastble.BleManager.getInstance().write(a0, "6E400001-B5A3-F393-E0A9-E50E24DCCA9E", "6E400002-B5A3-F393-E0A9-E50E24DCCA9E", a, (com.clj.fastble.callback.BleWriteCallback)new com.wakeup.wearfit2.util.BleUtil$3(this, s, a));
    }
}
