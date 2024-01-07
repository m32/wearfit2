package com.wakeup.wearfit2.service;

public class WakeBleService extends android.app.Service {
    final public static String ACTION_DATA_AVAILABLE = "com.wakeup.wearfit2.ACTION_DATA_AVAILABLE";
    final public static String ACTION_GATT_CONNECTED = "com.wakeup.wearfit2.ACTION_GATT_CONNECTED";
    final public static String ACTION_GATT_DISCONNECTED = "com.wakeup.wearfit2.ACTION_GATT_DISCONNECTED";
    final public static String ACTION_GATT_SERVICES_DISCOVERED = "com.wakeup.wearfit2.ACTION_GATT_SERVICES_DISCOVERED";
    final public static String ACTION_GATT_SERVICE_DISCONNECTED = "com.wakeup.wearfit2.ACTION_GATT_SERVICE_DISCONNECTED";
    final public static String ACTION_SEND_DATA_TO_BLE = "com.wakeup.wearfit2.ACTION_SEND_DATA_TO_BLE";
    final public static java.util.UUID CCCD;
    final public static String DEVICE_DOES_NOT_SUPPORT_UART = "com.wakeup.wearfit2.DEVICE_DOES_NOT_SUPPORT_UART";
    final public static String EXTRA_DATA = "com.wakeup.wearfit2.EXTRA_DATA";
    final public static String EXTRA_SEND_DATA_TO_BLE = "com.wakeup.wearfit2.EXTRA_SEND_DATA_TO_BLE";
    final private static int FREE = 0;
    final private static int RECEIVING = 2;
    final public static java.util.UUID RX_CHAR_UUID;
    final public static java.util.UUID RX_SERVICE_UUID;
    final private static int SENDING = 1;
    final private static int SEND_PACKET_SIZE = 20;
    final private static int STATE_CONNECTED = 2;
    final private static int STATE_CONNECTING = 1;
    final private static int STATE_DISCONNECTED = 0;
    final private static String TAG = "WakeBleService";
    final public static java.util.UUID TX_CHAR_UUID;
    private int TIMER_INTERVAL;
    private int TIME_OUT_LIMIT;
    private int ble_status;
    public java.util.ArrayList data_queue;
    private boolean final_packet;
    private boolean first_packet;
    final private android.os.IBinder mBinder;
    private android.bluetooth.BluetoothGatt mBluetooehGatt;
    private android.bluetooth.BluetoothAdapter mBluetoothAdapter;
    private String mBluetoothDeviceAddress;
    private android.bluetooth.BluetoothGatt mBluetoothGatt;
    private android.bluetooth.BluetoothManager mBluetoothManager;
    private int mConnectionState;
    final private android.bluetooth.BluetoothGattCallback mGattCallback;
    final private android.content.BroadcastReceiver mServiceReceiver;
    private java.util.Timer mTimer;
    private int packet_counter;
    private boolean packet_send;
    private byte[] send_data;
    private int send_data_pointer;
    boolean sendingStoredData;
    private int time_out_counter;
    private java.util.Timer timer;
    private android.os.PowerManager$WakeLock wakeLock;
    
    static {
        RX_SERVICE_UUID = java.util.UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e");
        RX_CHAR_UUID = java.util.UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e");
        TX_CHAR_UUID = java.util.UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e");
        CCCD = java.util.UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    }
    
    public WakeBleService() {
        this.mConnectionState = 0;
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
        this.mGattCallback = new com.wakeup.wearfit2.service.WakeBleService$1(this);
        this.mBinder = (android.os.IBinder)(Object)new com.wakeup.wearfit2.service.WakeBleService$LocalBinder(this);
        this.mServiceReceiver = new com.wakeup.wearfit2.service.WakeBleService$2(this);
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
                    if (this.mConnectionState == 2) {
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
    
    static int access$002(com.wakeup.wearfit2.service.WakeBleService a, int i) {
        a.mConnectionState = i;
        return i;
    }
    
    static void access$100(com.wakeup.wearfit2.service.WakeBleService a, String s) {
        a.broadcastUpdate(s);
    }
    
    static String access$200() {
        return TAG;
    }
    
    static android.bluetooth.BluetoothGatt access$300(com.wakeup.wearfit2.service.WakeBleService a) {
        return a.mBluetoothGatt;
    }
    
    static void access$400(com.wakeup.wearfit2.service.WakeBleService a, String s, android.bluetooth.BluetoothGattCharacteristic a0) {
        a.broadcastUpdate(s, a0);
    }
    
    static void access$500(com.wakeup.wearfit2.service.WakeBleService a, byte[] a0, boolean b) {
        a.BLE_send_data_set(a0, b);
    }
    
    static void access$600(com.wakeup.wearfit2.service.WakeBleService a) {
        a.timer_Tick();
    }
    
    private void broadcastUpdate(String s) {
        android.content.Intent a = new android.content.Intent(s);
        androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance((android.content.Context)this).sendBroadcast(a);
    }
    
    private void broadcastUpdate(String s, android.bluetooth.BluetoothGattCharacteristic a) {
        android.content.Intent a0 = new android.content.Intent(s);
        if (TX_CHAR_UUID.equals((Object)a.getUuid())) {
            String s0 = TAG;
            StringBuilder a1 = new StringBuilder();
            a1.append("received from ble:");
            a1.append(com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a.getValue()));
            android.util.Log.d(s0, a1.toString());
            a0.putExtra("com.wakeup.wearfit2.EXTRA_DATA", a.getValue());
        }
        androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance((android.content.Context)this).sendBroadcast(a0);
    }
    
    private void showMessage(String s) {
        android.util.Log.e(TAG, s);
    }
    
    private void start_timer() {
        this.sendingStoredData = true;
        java.util.Timer a = this.mTimer;
        if (a != null) {
            a.cancel();
        }
        java.util.Timer a0 = new java.util.Timer(true);
        this.mTimer = a0;
        a0.schedule((java.util.TimerTask)new com.wakeup.wearfit2.service.WakeBleService$3(this), 100L, (long)this.TIMER_INTERVAL);
    }
    
    private void timer_Tick() {
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
        if (this.mBluetoothGatt == null) {
            return;
        }
        android.util.Log.w(TAG, "mBluetoothGatt closed");
        this.mBluetoothDeviceAddress = null;
        this.mBluetoothGatt.close();
        this.mBluetoothGatt = null;
    }
    
    public boolean connect(String s) {
        if (this.mBluetoothAdapter != null && s != null) {
            String s0 = this.mBluetoothDeviceAddress;
            if (s0 != null && s.equals((Object)s0) && this.mBluetoothGatt != null) {
                android.util.Log.d(TAG, "Trying to use an existing mBluetoothGatt for connection.");
                if (!this.mBluetoothGatt.connect()) {
                    return false;
                }
                this.mConnectionState = 1;
                return true;
            }
            android.bluetooth.BluetoothDevice a = this.mBluetoothAdapter.getRemoteDevice(s);
            if (a == null) {
                android.util.Log.w(TAG, "Device not found.  Unable to connect.");
                return false;
            }
            this.mBluetoothGatt = a.connectGatt((android.content.Context)this, false, this.mGattCallback);
            android.util.Log.d(TAG, "Trying to create a new connection.");
            this.mBluetoothDeviceAddress = s;
            this.mConnectionState = 1;
            return true;
        }
        android.util.Log.w(TAG, "BluetoothAdapter not initialized or unspecified address.");
        return false;
    }
    
    public void disconnect() {
        if (this.mBluetoothAdapter != null) {
            android.bluetooth.BluetoothGatt a = this.mBluetoothGatt;
            if (a != null) {
                a.disconnect();
                return;
            }
        }
        android.util.Log.w(TAG, "BluetoothAdapter not initialized");
    }
    
    public void enableTXNotification() {
        android.bluetooth.BluetoothGatt a = this.mBluetoothGatt;
        if (a == null) {
            return;
        }
        android.bluetooth.BluetoothGattService a0 = a.getService(RX_SERVICE_UUID);
        if (a0 == null) {
            this.showMessage("Rx service not found!");
            this.broadcastUpdate("com.wakeup.wearfit2.DEVICE_DOES_NOT_SUPPORT_UART");
            return;
        }
        android.bluetooth.BluetoothGattCharacteristic a1 = a0.getCharacteristic(TX_CHAR_UUID);
        if (a1 == null) {
            this.showMessage("Tx charateristic not found!");
            this.broadcastUpdate("com.wakeup.wearfit2.DEVICE_DOES_NOT_SUPPORT_UART");
            return;
        }
        this.mBluetoothGatt.setCharacteristicNotification(a1, true);
        android.bluetooth.BluetoothGattDescriptor a2 = a1.getDescriptor(CCCD);
        a2.setValue(android.bluetooth.BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        this.mBluetoothGatt.writeDescriptor(a2);
    }
    
    public boolean initialize() {
        if (this.mBluetoothManager != null) {
            android.util.Log.d(TAG, "mBluetoothManager \u4e0d\u4e3a\u7a7a");
        } else {
            android.bluetooth.BluetoothManager a = (android.bluetooth.BluetoothManager)this.getSystemService("bluetooth");
            this.mBluetoothManager = a;
            if (a == null) {
                android.util.Log.e(TAG, "Unable to initialize BlueManager.");
                return false;
            }
        }
        android.bluetooth.BluetoothAdapter a0 = this.mBluetoothManager.getAdapter();
        this.mBluetoothAdapter = a0;
        if (a0 != null) {
            return true;
        }
        android.util.Log.e(TAG, "Unable to obtain a BluetoothAdapter.");
        return false;
    }
    
    public android.os.IBinder onBind(android.content.Intent a) {
        return this.mBinder;
    }
    
    public void onCreate() {
        ((android.app.Service)this).onCreate();
        android.os.PowerManager a = (android.os.PowerManager)this.getSystemService("power");
        StringBuilder a0 = new StringBuilder();
        a0.append(this.getPackageName());
        a0.append(" bluetooth transaction submission");
        android.os.PowerManager$WakeLock a1 = a.newWakeLock(1, a0.toString());
        this.wakeLock = a1;
        a1.acquire();
        android.content.IntentFilter a2 = new android.content.IntentFilter();
        a2.addAction("com.wakeup.wearfit2.ACTION_SEND_DATA_TO_BLE");
        a2.addAction("USER_UNBIND_DEVICE");
        androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance((android.content.Context)this).registerReceiver(this.mServiceReceiver, a2);
    }
    
    public void onDestroy() {
        this.wakeLock.release();
        ((android.app.Service)this).onDestroy();
        android.util.Log.d(TAG, "onDestroy");
        androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance((android.content.Context)this).unregisterReceiver(this.mServiceReceiver);
    }
    
    public boolean onUnbind(android.content.Intent a) {
        this.close();
        return ((android.app.Service)this).onUnbind(a);
    }
    
    public boolean writeRXCharacteristic(byte[] a) {
        android.bluetooth.BluetoothGatt a0 = this.mBluetoothGatt;
        if (a0 == null) {
            return false;
        }
        android.bluetooth.BluetoothGattService a1 = a0.getService(RX_SERVICE_UUID);
        if (a1 == null) {
            this.showMessage("Rx service not found!");
            this.broadcastUpdate("com.wakeup.wearfit2.DEVICE_DOES_NOT_SUPPORT_UART");
            return false;
        }
        android.bluetooth.BluetoothGattCharacteristic a2 = a1.getCharacteristic(RX_CHAR_UUID);
        if (a2 == null) {
            this.showMessage("Rx charateristic not found!");
            this.broadcastUpdate("com.wakeup.wearfit2.DEVICE_DOES_NOT_SUPPORT_UART");
            return false;
        }
        a2.setValue(a);
        boolean b = this.mBluetoothGatt.writeCharacteristic(a2);
        String s = TAG;
        StringBuilder a3 = new StringBuilder();
        a3.append("write TXchar - status=");
        a3.append(b);
        a3.append(com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a));
        android.util.Log.d(s, a3.toString());
        return b;
    }
}
