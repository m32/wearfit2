package no.nordicsemi.android.ble;

class BleManager$3 extends android.content.BroadcastReceiver {
    final no.nordicsemi.android.ble.BleManager this$0;
    
    BleManager$3(no.nordicsemi.android.ble.BleManager a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        android.bluetooth.BluetoothDevice a1 = (android.bluetooth.BluetoothDevice)(Object)a0.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (no.nordicsemi.android.ble.BleManager.access$300(this.this$0) != null && a1 != null && a1.getAddress().equals((Object)no.nordicsemi.android.ble.BleManager.access$300(this.this$0).getAddress())) {
            int i = a0.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", 0);
            no.nordicsemi.android.ble.BleManager a2 = this.this$0;
            StringBuilder a3 = new StringBuilder();
            a3.append("[Broadcast] Action received: android.bluetooth.device.action.PAIRING_REQUEST, pairing variant: ");
            a3.append(this.this$0.pairingVariantToString(i));
            a3.append(" (");
            a3.append(i);
            a3.append(")");
            a2.log(3, a3.toString());
            this.this$0.onPairingRequestReceived(a1, i);
        }
    }
}
