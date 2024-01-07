package com.wakeup.wearfit2.service;

public class BleService extends android.app.Service {
    final public static java.util.UUID CCCD;
    final private static int FREE = 0;
    final private static int RECEIVING = 2;
    final public static java.util.UUID RX_CHAR_UUID;
    final public static java.util.UUID RX_SERVICE_UUID;
    final private static int SENDING = 1;
    final private static int SEND_PACKET_SIZE = 20;
    final public static int STATE_CONNECTED = 2;
    final public static int STATE_CONNECTING = 1;
    final public static int STATE_DISCONNECTED = 0;
    final private static String TAG = "BleService";
    final public static java.util.UUID TX_CHAR_UUID;
    public static int mConnectionState;
    private int CONNECT_DEVICE;
    private int CONNECT_Gatt;
    private int STOP_SCAN;
    private int TIMER_INTERVAL;
    private int TIME_OUT_LIMIT;
    private int ble_status;
    public java.util.ArrayList data_queue;
    private boolean final_packet;
    private boolean first_packet;
    String intentAction;
    final private android.os.IBinder mBinder;
    private boolean mBleOff;
    private android.bluetooth.BluetoothAdapter mBluetoothAdapter;
    private String mBluetoothDeviceAddress;
    private android.bluetooth.BluetoothGatt mBluetoothGatt;
    private android.bluetooth.BluetoothManager mBluetoothManager;
    private android.content.BroadcastReceiver mBtReceiver;
    private boolean mConnecting;
    private String mDevice1;
    private boolean mFirstBinds;
    private boolean mForceDisconnectd;
    private android.bluetooth.BluetoothGattCallback mGattCallback;
    private android.os.Handler mHandler;
    private String mScanAddress;
    private android.bluetooth.BluetoothAdapter$LeScanCallback mScanCallback;
    private boolean mScanning;
    public com.wakeup.wearfit2.service.BleService$SendDataToBleReceiver mSendDataToBleReceiver;
    private java.util.Timer mTimer;
    private java.util.Timer mTimer2;
    private int packet_counter;
    private boolean packet_send;
    private byte[] send_data;
    private int send_data_pointer;
    boolean sendingStoredData;
    private int time_out_counter;
    
    static {
        CCCD = java.util.UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
        RX_SERVICE_UUID = java.util.UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e");
        RX_CHAR_UUID = java.util.UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e");
        TX_CHAR_UUID = java.util.UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e");
    }
    
    public BleService() {
        this.mForceDisconnectd = false;
        this.mConnecting = false;
        this.STOP_SCAN = 1;
        this.CONNECT_DEVICE = 3;
        this.CONNECT_Gatt = 2;
        this.mScanAddress = null;
        this.mScanning = false;
        this.ble_status = 0;
        this.packet_counter = 0;
        this.send_data_pointer = 0;
        this.send_data = null;
        this.first_packet = false;
        this.final_packet = false;
        this.packet_send = false;
        this.time_out_counter = 0;
        this.TIMER_INTERVAL = 100;
        this.TIME_OUT_LIMIT = 100;
        this.data_queue = new java.util.ArrayList();
        this.sendingStoredData = false;
        this.mBtReceiver = new com.wakeup.wearfit2.service.BleService$1(this);
        this.mScanCallback = (android.bluetooth.BluetoothAdapter$LeScanCallback)(Object)new com.wakeup.wearfit2.service.BleService$2(this);
        this.mHandler = new com.wakeup.wearfit2.service.BleService$3(this);
        this.mGattCallback = new com.wakeup.wearfit2.service.BleService$4(this);
        this.mBleOff = true;
        this.mBinder = (android.os.IBinder)(Object)new com.wakeup.wearfit2.service.BleService$LocalBinder(this);
    }
    
    private void BLE_data_send() {
        int i = 0;
        while(!this.final_packet) {
            byte[] a = null;
            int i0 = this.send_data_pointer;
            boolean b = this.first_packet;
            if (b) {
                byte[] a0 = this.send_data;
                if (a0.length - i0 <= 20) {
                    int i1 = a0.length - i0;
                    a = new byte[i1];
                    int i2 = 0;
                    for(; i2 < i1; i2 = i2 + 1) {
                        byte[] a1 = this.send_data;
                        int i3 = this.send_data_pointer;
                        a[i2] = (byte)(int)a1[i3];
                        this.send_data_pointer = i3 + 1;
                    }
                    this.final_packet = true;
                } else {
                    a = new byte[20];
                    int i4 = 0;
                    for(; i4 < 20; i4 = i4 + 1) {
                        byte[] a2 = this.send_data;
                        int i5 = this.send_data_pointer;
                        a[i4] = (byte)(int)a2[i5];
                        this.send_data_pointer = i5 + 1;
                    }
                }
                this.first_packet = false;
            } else {
                byte[] a3 = this.send_data;
                if (a3.length - i0 < 20) {
                    this.final_packet = true;
                    int i6 = a3.length - i0 + 1;
                    a = new byte[i6];
                    a[0] = (byte)(int)(byte)this.packet_counter;
                    int i7 = 1;
                    for(; i7 < i6; i7 = i7 + 1) {
                        byte[] a4 = this.send_data;
                        int i8 = this.send_data_pointer;
                        a[i7] = (byte)(int)a4[i8];
                        this.send_data_pointer = i8 + 1;
                    }
                } else {
                    a = new byte[20];
                    a[0] = (byte)(int)(byte)this.packet_counter;
                    int i9 = 1;
                    for(; i9 < 20; i9 = i9 + 1) {
                        byte[] a5 = this.send_data;
                        int i10 = this.send_data_pointer;
                        a[i9] = (byte)(int)a5[i10];
                        this.send_data_pointer = i10 + 1;
                    }
                }
                this.packet_counter = this.packet_counter + 1;
            }
            this.packet_send = false;
            if (!this.writeRXCharacteristic(a) && i < 3) {
                try {
                    i = i + 1;
                    Thread.sleep(50L);
                } catch(InterruptedException a6) {
                    a6.printStackTrace();
                }
                this.send_data_pointer = i0;
                this.first_packet = b;
                this.packet_counter = this.packet_counter - 1;
            }
            int i11 = 0;
            while(i11 < 5 && !this.packet_send) {
                try {
                    Thread.sleep(1L);
                } catch(Exception a7) {
                    a7.printStackTrace();
                }
                i11 = i11 + 1;
            }
        }
        this.final_packet = false;
        this.ble_status = 0;
    }
    
    private void BLE_send_data_set(byte[] a, boolean b) {
        int i = this.ble_status;
        label2: {
            label0: {
                label1: {
                    if (i != 0) {
                        break label1;
                    }
                    if (mConnectionState == 2) {
                        break label0;
                    }
                }
                if (this.sendingStoredData) {
                    if (!b) {
                        this.data_queue.add((Object)a);
                    }
                    return;
                }
                this.data_queue.add((Object)a);
                this.start_timer();
                break label2;
            }
            this.ble_status = 1;
            if (this.data_queue.size() == 0) {
                this.send_data = a;
            } else {
                this.send_data = (byte[])this.data_queue.get(0);
                this.sendingStoredData = false;
            }
            this.packet_counter = 0;
            this.send_data_pointer = 0;
            this.first_packet = true;
            this.BLE_data_send();
            if (this.data_queue.size() != 0) {
                this.data_queue.remove(0);
            }
            if (this.data_queue.size() == 0) {
                java.util.Timer a0 = this.mTimer;
                if (a0 != null) {
                    a0.cancel();
                }
            }
        }
    }
    
    static android.bluetooth.BluetoothAdapter access$000(com.wakeup.wearfit2.service.BleService a) {
        return a.mBluetoothAdapter;
    }
    
    static android.bluetooth.BluetoothAdapter access$002(com.wakeup.wearfit2.service.BleService a, android.bluetooth.BluetoothAdapter a0) {
        a.mBluetoothAdapter = a0;
        return a0;
    }
    
    static android.bluetooth.BluetoothManager access$100(com.wakeup.wearfit2.service.BleService a) {
        return a.mBluetoothManager;
    }
    
    static int access$1000(com.wakeup.wearfit2.service.BleService a) {
        return a.CONNECT_Gatt;
    }
    
    static android.bluetooth.BluetoothManager access$102(com.wakeup.wearfit2.service.BleService a, android.bluetooth.BluetoothManager a0) {
        a.mBluetoothManager = a0;
        return a0;
    }
    
    static android.bluetooth.BluetoothAdapter$LeScanCallback access$1100(com.wakeup.wearfit2.service.BleService a) {
        return a.mScanCallback;
    }
    
    static android.bluetooth.BluetoothGatt access$1200(com.wakeup.wearfit2.service.BleService a) {
        return a.mBluetoothGatt;
    }
    
    static android.bluetooth.BluetoothGatt access$1202(com.wakeup.wearfit2.service.BleService a, android.bluetooth.BluetoothGatt a0) {
        a.mBluetoothGatt = a0;
        return a0;
    }
    
    static android.bluetooth.BluetoothGattCallback access$1300(com.wakeup.wearfit2.service.BleService a) {
        return a.mGattCallback;
    }
    
    static String access$1402(com.wakeup.wearfit2.service.BleService a, String s) {
        a.mBluetoothDeviceAddress = s;
        return s;
    }
    
    static int access$1500(com.wakeup.wearfit2.service.BleService a) {
        return a.CONNECT_DEVICE;
    }
    
    static String access$1600(com.wakeup.wearfit2.service.BleService a) {
        return a.mDevice1;
    }
    
    static java.util.Timer access$1700(com.wakeup.wearfit2.service.BleService a) {
        return a.mTimer2;
    }
    
    static void access$1800(com.wakeup.wearfit2.service.BleService a, String s, android.bluetooth.BluetoothGattCharacteristic a0) {
        a.broadcastUpdate(s, a0);
    }
    
    static boolean access$1902(com.wakeup.wearfit2.service.BleService a, boolean b) {
        a.packet_send = b;
        return b;
    }
    
    static boolean access$200(com.wakeup.wearfit2.service.BleService a) {
        return a.mBleOff;
    }
    
    static void access$2000(com.wakeup.wearfit2.service.BleService a, byte[] a0, boolean b) {
        a.BLE_send_data_set(a0, b);
    }
    
    static boolean access$202(com.wakeup.wearfit2.service.BleService a, boolean b) {
        a.mBleOff = b;
        return b;
    }
    
    static void access$2100(com.wakeup.wearfit2.service.BleService a) {
        a.timer_Tick();
    }
    
    static void access$300(com.wakeup.wearfit2.service.BleService a, String s) {
        a.broadcastUpdate(s);
    }
    
    static boolean access$400(com.wakeup.wearfit2.service.BleService a) {
        return a.mScanning;
    }
    
    static int access$500(com.wakeup.wearfit2.service.BleService a) {
        return a.STOP_SCAN;
    }
    
    static android.os.Handler access$600(com.wakeup.wearfit2.service.BleService a) {
        return a.mHandler;
    }
    
    static boolean access$700(com.wakeup.wearfit2.service.BleService a) {
        return a.mForceDisconnectd;
    }
    
    static boolean access$702(com.wakeup.wearfit2.service.BleService a, boolean b) {
        a.mForceDisconnectd = b;
        return b;
    }
    
    static String access$800(com.wakeup.wearfit2.service.BleService a) {
        return a.mScanAddress;
    }
    
    static boolean access$900(com.wakeup.wearfit2.service.BleService a) {
        return a.mConnecting;
    }
    
    static boolean access$902(com.wakeup.wearfit2.service.BleService a, boolean b) {
        a.mConnecting = b;
        return b;
    }
    
    private void broadcastUpdate(String s) {
        android.content.Intent a = new android.content.Intent(s);
        androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance((android.content.Context)this).sendBroadcast(a);
    }
    
    private void broadcastUpdate(String s, android.bluetooth.BluetoothGattCharacteristic a) {
        android.content.Intent a0 = new android.content.Intent(s);
        label2: if (TX_CHAR_UUID.equals((Object)a.getUuid())) {
            byte[] a1 = a.getValue();
            StringBuilder a2 = new StringBuilder();
            a2.append("received from bleq:");
            a2.append(com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a1));
            android.util.Log.d("lq", a2.toString());
            if (a1 != null) {
                a0.putExtra("com.wakeup.wearfit2.EXTRA_DATA", a1);
                androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance((android.content.Context)this).sendBroadcast(a0);
            }
            int i = this.ble_status;
            label0: {
                label1: {
                    if (i == 0) {
                        break label1;
                    }
                    if (i != 2) {
                        break label0;
                    }
                }
                this.ble_status = 2;
                this.ble_status = 0;
                break label2;
            }
            if (i == 1) {
                if (this.final_packet) {
                    this.final_packet = false;
                }
                this.ble_status = 0;
            }
        }
    }
    
    private static android.content.IntentFilter makeGattUpdateIntentFilter() {
        android.content.IntentFilter a = new android.content.IntentFilter();
        a.addAction("com.wakeup.wearfit2.ACTION_SEND_DATA_TO_BLE");
        return a;
    }
    
    private void registerReceiver() {
        this.registerReceiver(this.mBtReceiver, new android.content.IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        if (this.mSendDataToBleReceiver == null) {
            this.mSendDataToBleReceiver = new com.wakeup.wearfit2.service.BleService$SendDataToBleReceiver(this);
            androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance((android.content.Context)this).registerReceiver((android.content.BroadcastReceiver)this.mSendDataToBleReceiver, com.wakeup.wearfit2.service.BleService.makeGattUpdateIntentFilter());
        }
    }
    
    private void searchDevice(String s) {
        this.mScanAddress = s;
        this.mConnecting = false;
        this.mBluetoothAdapter.startLeScan(this.mScanCallback);
    }
    
    private void start_timer() {
        this.sendingStoredData = true;
        java.util.Timer a = this.mTimer;
        if (a != null) {
            a.cancel();
        }
        java.util.Timer a0 = new java.util.Timer(true);
        this.mTimer = a0;
        a0.schedule((java.util.TimerTask)new com.wakeup.wearfit2.service.BleService$6(this), 100L, (long)this.TIMER_INTERVAL);
    }
    
    private void timer_Tick() {
        android.util.Log.i("wxk", "\u5faa\u73af\u4e2d");
        if (this.data_queue.size() != 0) {
            this.sendingStoredData = true;
            this.BLE_send_data_set((byte[])this.data_queue.get(0), true);
        }
        int i = this.time_out_counter;
        if (i >= this.TIME_OUT_LIMIT) {
            this.ble_status = 0;
            this.time_out_counter = 0;
        } else {
            this.time_out_counter = i + 1;
        }
    }
    
    public void close() {
        android.bluetooth.BluetoothGatt a = this.mBluetoothGatt;
        if (a == null) {
            return;
        }
        this.mBluetoothDeviceAddress = null;
        a.close();
        this.mBluetoothGatt = null;
    }
    
    public boolean connect(String s, boolean b) {
        this.mFirstBinds = true;
        if (this.mBluetoothAdapter != null && !android.text.TextUtils.isEmpty((CharSequence)(Object)s) && this.mBluetoothAdapter.isEnabled()) {
            if (s != null && !android.text.TextUtils.isEmpty((CharSequence)(Object)s)) {
                this.mDevice1 = s;
            }
            this.mForceDisconnectd = false;
            if (this.mBluetoothDeviceAddress != null) {
                android.util.Log.i("wxk", "4");
                if (s.equals((Object)this.mBluetoothDeviceAddress)) {
                    android.util.Log.i("wxk", "5");
                    if (this.mBluetoothGatt != null) {
                        android.util.Log.i("wxk", "2");
                        if (!this.mBluetoothGatt.connect()) {
                            return false;
                        }
                        mConnectionState = 1;
                        this.searchDevice(s);
                        return true;
                    }
                }
            }
            android.bluetooth.BluetoothDevice a = this.mBluetoothAdapter.getRemoteDevice(s);
            if (a != null && b) {
                this.mBluetoothGatt = a.connectGatt((android.content.Context)this, false, this.mGattCallback);
                this.mBluetoothDeviceAddress = s;
                mConnectionState = 1;
                return true;
            }
            android.util.Log.i("wxk", "3");
            this.searchDevice(s);
            return false;
        }
        android.util.Log.i("wxk", "1");
        return false;
    }
    
    public void disconnect() {
        if (this.mBluetoothAdapter != null) {
            android.bluetooth.BluetoothGatt a = this.mBluetoothGatt;
            if (a != null) {
                this.mForceDisconnectd = true;
                a.disconnect();
                this.close();
            }
        }
    }
    
    public void enableTXNotification() {
        android.bluetooth.BluetoothGattService a = this.mBluetoothGatt.getService(RX_SERVICE_UUID);
        if (a == null) {
            return;
        }
        android.bluetooth.BluetoothGattCharacteristic a0 = a.getCharacteristic(TX_CHAR_UUID);
        if (a0 == null) {
            return;
        }
        this.mBluetoothGatt.setCharacteristicNotification(a0, true);
        android.bluetooth.BluetoothGattDescriptor a1 = a0.getDescriptor(CCCD);
        a1.setValue(android.bluetooth.BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        this.mBluetoothGatt.writeDescriptor(a1);
    }
    
    public boolean initialize() {
        if (this.mBluetoothManager == null) {
            android.bluetooth.BluetoothManager a = (android.bluetooth.BluetoothManager)this.getSystemService("bluetooth");
            this.mBluetoothManager = a;
            if (a == null) {
                return false;
            }
        }
        android.bluetooth.BluetoothAdapter a0 = this.mBluetoothManager.getAdapter();
        this.mBluetoothAdapter = a0;
        if (a0 != null) {
            return true;
        }
        return false;
    }
    
    public boolean isConnected() {
        return mConnectionState == 2;
    }
    
    public android.os.IBinder onBind(android.content.Intent a) {
        return this.mBinder;
    }
    
    public void onCreate() {
        ((android.app.Service)this).onCreate();
        org.greenrobot.eventbus.EventBus.getDefault().register((Object)this);
        this.registerReceiver();
        if (com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) {
            mConnectionState = 2;
        }
        StringBuilder a = new StringBuilder();
        a.append(com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected());
        a.append("");
        android.util.Log.i("wxk888", a.toString());
        this.initialize();
    }
    
    public void onDestroy() {
        ((android.app.Service)this).onDestroy();
        java.util.Timer a = this.mTimer;
        if (a != null) {
            a.cancel();
        }
        java.util.Timer a0 = this.mTimer2;
        if (a0 != null) {
            a0.cancel();
        }
        this.disconnect();
        this.unregisterReceiver(this.mBtReceiver);
        androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance((android.content.Context)this).unregisterReceiver((android.content.BroadcastReceiver)this.mSendDataToBleReceiver);
        org.greenrobot.eventbus.EventBus.getDefault().unregister((Object)this);
    }
    
    public void onEventMainThread(com.wakeup.wearfit2.model.event.BaseEvent a) {
        if (com.wakeup.wearfit2.service.BleService$7.$SwitchMap$com$wakeup$wearfit2$model$event$BaseEvent$EventType[a.getEventType().ordinal()] == 1) {
            this.disconnect();
        }
    }
    
    public int onStartCommand(android.content.Intent a, int i, int i0) {
        return 1;
    }
    
    public boolean onUnbind(android.content.Intent a) {
        return ((android.app.Service)this).onUnbind(a);
    }
    
    public void reConnent() {
        android.util.Log.i("wxk888", "888");
        java.util.Timer a = this.mTimer;
        if (a != null) {
            a.cancel();
        }
        java.util.Timer a0 = new java.util.Timer();
        this.mTimer2 = a0;
        a0.schedule((java.util.TimerTask)new com.wakeup.wearfit2.service.BleService$5(this), 1000L, 4000L);
    }
    
    public boolean writeRXCharacteristic(byte[] a) {
        android.bluetooth.BluetoothGatt a0 = this.mBluetoothGatt;
        if (a0 == null) {
            return false;
        }
        android.bluetooth.BluetoothGattService a1 = a0.getService(RX_SERVICE_UUID);
        if (a1 == null) {
            return false;
        }
        android.bluetooth.BluetoothGattCharacteristic a2 = a1.getCharacteristic(RX_CHAR_UUID);
        if (a2 == null) {
            return false;
        }
        a2.setValue(a);
        boolean b = this.mBluetoothGatt.writeCharacteristic(a2);
        String s = TAG;
        StringBuilder a3 = new StringBuilder();
        a3.append("\u53d1\u9001\u6307\u4ee4\uff1astatus\uff1a");
        a3.append(b);
        a3.append("-->");
        a3.append(com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a));
        android.util.Log.d(s, a3.toString());
        return b;
    }
}
