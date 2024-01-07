package no.nordicsemi.android.ble;

class BleManager$2 extends android.content.BroadcastReceiver {
    final no.nordicsemi.android.ble.BleManager this$0;
    
    BleManager$2(no.nordicsemi.android.ble.BleManager a) {
        this.this$0 = a;
        super();
    }
    
    public void lambda$onReceive$0$BleManager$2() {
        this.this$0.log(2, "Discovering services...");
        this.this$0.log(3, "gatt.discoverServices()");
        no.nordicsemi.android.ble.BleManager.access$1300(this.this$0).discoverServices();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        android.bluetooth.BluetoothDevice a1 = (android.bluetooth.BluetoothDevice)(Object)a0.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int i = a0.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1);
        int i0 = a0.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", -1);
        if (no.nordicsemi.android.ble.BleManager.access$300(this.this$0) != null && a1.getAddress().equals((Object)no.nordicsemi.android.ble.BleManager.access$300(this.this$0).getAddress())) {
            no.nordicsemi.android.ble.BleManager a2 = this.this$0;
            StringBuilder a3 = new StringBuilder();
            a3.append("[Broadcast] Action received: android.bluetooth.device.action.BOND_STATE_CHANGED, bond state changed to: ");
            a3.append(this.this$0.bondStateToString(i));
            a3.append(" (");
            a3.append(i);
            a3.append(")");
            a2.log(3, a3.toString());
            switch(i) {
                case 12: {
                    this.this$0.log(4, "Device bonded");
                    this.this$0.mCallbacks.onBonded(a1);
                    no.nordicsemi.android.ble.Request a4 = no.nordicsemi.android.ble.BleManager.access$400(this.this$0);
                    label2: {
                        if (a4 == null) {
                            break label2;
                        }
                        if (no.nordicsemi.android.ble.BleManager.access$400(this.this$0).type != no.nordicsemi.android.ble.Request$Type.CREATE_BOND) {
                            break label2;
                        }
                        no.nordicsemi.android.ble.BleManager.access$400(this.this$0).notifySuccess(a1);
                        no.nordicsemi.android.ble.BleManager.access$402(this.this$0, (no.nordicsemi.android.ble.Request)null);
                        break;
                    }
                    if (!no.nordicsemi.android.ble.BleManager.access$900(this.this$0) && !no.nordicsemi.android.ble.BleManager.access$1000(this.this$0)) {
                        no.nordicsemi.android.ble.BleManager.access$1002(this.this$0, true);
                        this.this$0.mHandler.post((Runnable)(Object)new no.nordicsemi.android.ble.-$$Lambda$BleManager$2$athMMNIAw6GKhHoU7ecNCGB8jQw(this));
                        return;
                    }
                    int i1 = android.os.Build$VERSION.SDK_INT;
                    label0: {
                        label1: {
                            if (i1 >= 26) {
                                break label1;
                            }
                            if (no.nordicsemi.android.ble.BleManager.access$400(this.this$0) == null) {
                                break label1;
                            }
                            if (no.nordicsemi.android.ble.BleManager.access$400(this.this$0).type != no.nordicsemi.android.ble.Request$Type.CREATE_BOND) {
                                break label0;
                            }
                        }
                        return;
                    }
                    no.nordicsemi.android.ble.BleManager$BleManagerGattCallback.access$1100(no.nordicsemi.android.ble.BleManager.access$000(this.this$0), no.nordicsemi.android.ble.BleManager.access$400(this.this$0));
                    break;
                }
                case 11: {
                    this.this$0.mCallbacks.onBondingRequired(a1);
                    return;
                }
                case 10: {
                    if (i0 != 11) {
                        if (i0 != 12) {
                            break;
                        }
                        if (no.nordicsemi.android.ble.BleManager.access$400(this.this$0) == null) {
                            break;
                        }
                        if (no.nordicsemi.android.ble.BleManager.access$400(this.this$0).type != no.nordicsemi.android.ble.Request$Type.REMOVE_BOND) {
                            break;
                        }
                        this.this$0.log(4, "Bond information removed");
                        no.nordicsemi.android.ble.BleManager.access$400(this.this$0).notifySuccess(a1);
                        no.nordicsemi.android.ble.BleManager.access$402(this.this$0, (no.nordicsemi.android.ble.Request)null);
                        break;
                    } else {
                        this.this$0.mCallbacks.onBondingFailed(a1);
                        this.this$0.log(5, "Bonding failed");
                        if (no.nordicsemi.android.ble.BleManager.access$400(this.this$0) == null) {
                            break;
                        }
                        no.nordicsemi.android.ble.BleManager.access$400(this.this$0).notifyFail(a1, -4);
                        no.nordicsemi.android.ble.BleManager.access$402(this.this$0, (no.nordicsemi.android.ble.Request)null);
                        break;
                    }
                }
            }
            no.nordicsemi.android.ble.BleManager$BleManagerGattCallback.access$1200(no.nordicsemi.android.ble.BleManager.access$000(this.this$0), true);
        }
    }
}
