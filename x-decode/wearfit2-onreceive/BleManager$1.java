package no.nordicsemi.android.ble;

class BleManager$1 extends android.content.BroadcastReceiver {
    final no.nordicsemi.android.ble.BleManager this$0;
    
    BleManager$1(no.nordicsemi.android.ble.BleManager a) {
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
        int i0 = a0.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", 10);
        StringBuilder a1 = new StringBuilder();
        a1.append("[Broadcast] Action received: android.bluetooth.adapter.action.STATE_CHANGED, state changed to ");
        a1.append(this.state2String(i));
        String s = a1.toString();
        this.this$0.log(3, s);
        label2: {
            label3: {
                if (i == 10) {
                    break label3;
                }
                if (i == 13) {
                    break label3;
                }
                break label2;
            }
            label0: {
                label1: {
                    if (i0 == 13) {
                        break label1;
                    }
                    if (i0 != 10) {
                        break label0;
                    }
                }
                this.this$0.close();
                break label2;
            }
            no.nordicsemi.android.ble.BleManager$BleManagerGattCallback a2 = no.nordicsemi.android.ble.BleManager.access$000(this.this$0);
            if (a2 != null) {
                no.nordicsemi.android.ble.BleManager$BleManagerGattCallback.access$102(a2, true);
                a2.cancelQueue();
                no.nordicsemi.android.ble.BleManager$BleManagerGattCallback.access$202(a2, (java.util.Deque)null);
            }
            android.bluetooth.BluetoothDevice a3 = no.nordicsemi.android.ble.BleManager.access$300(this.this$0);
            if (a3 != null) {
                if (no.nordicsemi.android.ble.BleManager.access$400(this.this$0) != null && no.nordicsemi.android.ble.BleManager.access$400(this.this$0).type != no.nordicsemi.android.ble.Request$Type.DISCONNECT) {
                    no.nordicsemi.android.ble.BleManager.access$400(this.this$0).notifyFail(a3, -100);
                    no.nordicsemi.android.ble.BleManager.access$402(this.this$0, (no.nordicsemi.android.ble.Request)null);
                }
                if (no.nordicsemi.android.ble.BleManager.access$500(this.this$0) != null) {
                    no.nordicsemi.android.ble.BleManager.access$500(this.this$0).notifyFail(a3, -100);
                    no.nordicsemi.android.ble.BleManager.access$502(this.this$0, (no.nordicsemi.android.ble.WaitForValueChangedRequest)null);
                }
                if (no.nordicsemi.android.ble.BleManager.access$600(this.this$0) != null) {
                    no.nordicsemi.android.ble.BleManager.access$600(this.this$0).notifyFail(a3, -100);
                    no.nordicsemi.android.ble.BleManager.access$602(this.this$0, (no.nordicsemi.android.ble.ConnectRequest)null);
                }
            }
            no.nordicsemi.android.ble.BleManager.access$702(this.this$0, true);
            if (a2 != null) {
                no.nordicsemi.android.ble.BleManager$BleManagerGattCallback.access$102(a2, false);
                if (a3 != null) {
                    no.nordicsemi.android.ble.BleManager$BleManagerGattCallback.access$800(a2, a3);
                }
            }
        }
    }
}
