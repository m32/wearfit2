package com.wakeup.wearfit2.ble;

public class WearfitService extends android.app.Service {
    final public static String ACTION_SEND_DATA_TO_BLE = "com.wakeup.wearfit2.ACTION_SEND_DATA_TO_BLE";
    final public static String BROADCAST_BATTERY_LEVEL = "com.wakeup.wearfit2.BROADCAST_BATTERY_LEVEL";
    final public static String BROADCAST_CONNECTION_STATE = "com.wakeup.wearfit2.BROADCAST_CONNECTION_STATE";
    final public static String BROADCAST_DATA_AVAILABLE = "com.wakeup.wearfit2.BROADCAST_DATA_AVAILABLE";
    final public static String BROADCAST_DATA_SEND = "com.wakeup.wearfit2.BROADCAST_DATA_SEND";
    final public static String BROADCAST_DEVICE_READY = "com.wakeup.wearfit2.DEVICE_READY";
    final public static String CHANNEL_ID = "ForegroundServiceChannel";
    final public static String EXTRA_BATTERY_LEVEL = "com.wakeup.wearfit2.EXTRA_BATTERY_LEVEL";
    final public static String EXTRA_CONNECTION_STATE = "com.wakeup.wearfit2.EXTRA_CONNECTION_STATE";
    final public static String EXTRA_DATA = "com.wakeup.wearfit2.EXTRA_DATA";
    final public static String EXTRA_DEVICE = "com.wakeup.wearfit2.EXTRA_DEVICE";
    final public static String EXTRA_DEVICE_ADDRESS = "com.wakeup.wearfit2.EXTRA_DEVICE_ADDRESS";
    final public static String EXTRA_DEVICE_NAME = "com.wakeup.wearfit2.EXTRA_DEVICE_NAME";
    final public static String EXTRA_SEND_DATA_TO_BLE = "com.wakeup.wearfit2.EXTRA_SEND_DATA_TO_BLE";
    final public static String EXTRA_SEND_DATA_TO_BLE_ORDER = "com.wakeup.wearfit2.EXTRA_SEND_DATA_TO_BLE_ORDER";
    final public static int STATE_CONNECTED = 1;
    final public static int STATE_CONNECTING = 2;
    final public static int STATE_DISCONNECTED = 0;
    final public static int STATE_DISCONNECTING = 3;
    final private static String TAG = "WearfitService";
    private android.bluetooth.BluetoothAdapter adapter;
    private String classicAddress;
    private boolean mActivityIsChangingConfiguration;
    final private android.content.BroadcastReceiver mBleBroadcastReceiver;
    private android.bluetooth.BluetoothDevice mBluetoothDevice1;
    final private android.content.BroadcastReceiver mBluetoothStateBroadcastReceiver;
    
    static {
    }
    
    public WearfitService() {
        this.mBluetoothStateBroadcastReceiver = new com.wakeup.wearfit2.ble.WearfitService$1(this);
        this.mBleBroadcastReceiver = new com.wakeup.wearfit2.ble.WearfitService$2(this);
    }
    
    static String access$000() {
        return TAG;
    }
    
    static String access$100(com.wakeup.wearfit2.ble.WearfitService a) {
        return a.classicAddress;
    }
    
    static android.bluetooth.BluetoothDevice access$200(com.wakeup.wearfit2.ble.WearfitService a) {
        return a.mBluetoothDevice1;
    }
    
    static void access$300(com.wakeup.wearfit2.ble.WearfitService a, android.bluetooth.BluetoothDevice a0) {
        a.unpairDevice(a0);
    }
    
    static void access$400(com.wakeup.wearfit2.ble.WearfitService a) {
        a.connectClassicBluetooth();
    }
    
    private void connectClassicBluetooth() {
        this.classicAddress = com.wakeup.wearfit2.util.SPUtils.getString((android.content.Context)this, "classic_mac_address", "");
        String s = TAG;
        StringBuilder a = new StringBuilder();
        a.append("\u7ecf\u5178\u84dd\u7259\u5730\u5740 address: ");
        a.append(this.classicAddress);
        android.util.Log.i(s, a.toString());
        try {
            if (!android.text.TextUtils.isEmpty((CharSequence)(Object)this.classicAddress)) {
                android.bluetooth.BluetoothDevice a0 = this.adapter.getRemoteDevice(this.classicAddress);
                this.mBluetoothDevice1 = a0;
                if (a0 != null) {
                    android.util.Log.d(s, "\u670d\u52a1\u542f\u52a8\uff0c\u8fde\u63a5\u7ecf\u5178\u84dd\u7259");
                    this.pairDevice(this.mBluetoothDevice1);
                }
            }
        } catch(Exception a1) {
            String s0 = TAG;
            StringBuilder a2 = new StringBuilder();
            a2.append("\u8fde\u63a5\u7ecf\u5178\u84dd\u7259\u9519\u8bef\uff1a");
            a2.append(a1.getMessage());
            android.util.Log.e(s0, a2.toString());
        }
    }
    
    private void createNotificationChannel() {
        if (android.os.Build$VERSION.SDK_INT >= 26) {
            android.app.NotificationChannel a = new android.app.NotificationChannel("ForegroundServiceChannel", (CharSequence)(Object)"Foreground Service Channel", 3);
            ((android.app.NotificationManager)this.getSystemService(android.app.NotificationManager.class)).createNotificationChannel(a);
        }
    }
    
    private android.content.IntentFilter getFilter() {
        android.content.IntentFilter a = new android.content.IntentFilter();
        a.addAction("com.wakeup.wearfit2.ACTION_SEND_DATA_TO_BLE");
        a.addAction("USER_UNBIND_DEVICE");
        a.addAction("connect_classic_bluetooth");
        return a;
    }
    
    private void pairDevice(android.bluetooth.BluetoothDevice a) {
        String s = TAG;
        android.util.Log.i(s, "\u5f00\u59cb\u914d\u5bf9\u7ecf\u5178\u84dd\u7259");
        try {
            Boolean a0 = (Boolean)((Object)a).getClass().getMethod("createBond", new Class[0]).invoke((Object)a, new Object[0]);
            StringBuilder a1 = new StringBuilder();
            a1.append("\u914d\u5bf9\u7ecf\u5178\u84dd\u7259\u7ed3\u679c\uff1a");
            a1.append((Object)a0);
            android.util.Log.e(s, a1.toString());
        } catch(Exception a2) {
            String s0 = TAG;
            StringBuilder a3 = new StringBuilder();
            a3.append("\u914d\u5bf9\u7ecf\u5178\u84dd\u7259\u9519\u8bef\uff1a");
            a3.append(a2.getMessage());
            android.util.Log.e(s0, a3.toString());
        }
    }
    
    private void unpairDevice(android.bluetooth.BluetoothDevice a) {
        try {
            ((Object)a).getClass().getMethod("removeBond", (Class[])null).invoke((Object)a, (Object[])null);
        } catch(Exception a0) {
            a0.printStackTrace();
        }
    }
    
    protected com.wakeup.wearfit2.ble.WearfitService$LocalBinder getBinder() {
        return new com.wakeup.wearfit2.ble.WearfitService$LocalBinder(this);
    }
    
    public android.os.IBinder onBind(android.content.Intent a) {
        return (android.os.IBinder)(Object)this.getBinder();
    }
    
    public void onCreate() {
        ((android.app.Service)this).onCreate();
        this.registerReceiver(this.mBluetoothStateBroadcastReceiver, new android.content.IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        this.registerReceiver(this.mBleBroadcastReceiver, this.getFilter());
    }
    
    public void onDestroy() {
        ((android.app.Service)this).onDestroy();
        this.unregisterReceiver(this.mBluetoothStateBroadcastReceiver);
        this.unregisterReceiver(this.mBleBroadcastReceiver);
        com.wakeup.wearfit2.util.BleUtil.getInstance().close();
        android.util.Log.i(TAG, "Service destroyed");
    }
    
    protected void onRebind() {
    }
    
    final public void onRebind(android.content.Intent a) {
        if (!this.mActivityIsChangingConfiguration) {
            this.onRebind();
        }
    }
    
    public int onStartCommand(android.content.Intent a, int i, int i0) {
        if (a != null && a.hasExtra("com.wakeup.wearfit2.EXTRA_DEVICE_ADDRESS")) {
            android.util.Log.i(TAG, "onStartCommand");
            this.createNotificationChannel();
            android.app.PendingIntent a0 = android.app.PendingIntent.getActivity((android.content.Context)this, 0, new android.content.Intent((android.content.Context)this, com.wakeup.wearfit2.ui.activity.MainActivity.class), 33554432);
            this.startForeground(1, new androidx.core.app.NotificationCompat$Builder((android.content.Context)this, "ForegroundServiceChannel").setContentTitle((CharSequence)(Object)this.getString(2131886163)).setContentText((CharSequence)(Object)this.getString(2131886921)).setSmallIcon(2131230814).setContentIntent(a0).build());
            this.adapter = android.bluetooth.BluetoothAdapter.getDefaultAdapter();
            return 2;
        }
        throw new UnsupportedOperationException("No device address at EXTRA_DEVICE_ADDRESS key");
    }
    
    public void onTaskRemoved(android.content.Intent a) {
        ((android.app.Service)this).onTaskRemoved(a);
        android.util.Log.i(TAG, "onTaskRemoved");
    }
    
    final public boolean onUnbind(android.content.Intent a) {
        return true;
    }
    
    public void setActivityIsChangingConfiguration(boolean b) {
        this.mActivityIsChangingConfiguration = b;
    }
}
