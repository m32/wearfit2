package no.nordicsemi.android.dfu;

class DfuBaseService$3 extends android.content.BroadcastReceiver {
    final no.nordicsemi.android.dfu.DfuBaseService this$0;
    
    DfuBaseService$3(no.nordicsemi.android.dfu.DfuBaseService a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        android.bluetooth.BluetoothDevice a1 = (android.bluetooth.BluetoothDevice)(Object)a0.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (a1 != null && a1.getAddress().equals((Object)no.nordicsemi.android.dfu.DfuBaseService.access$500(this.this$0))) {
            int i = a0.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1);
            if (i == 11) {
                return;
            }
            if (no.nordicsemi.android.dfu.DfuBaseService.access$100(this.this$0) != null) {
                no.nordicsemi.android.dfu.DfuBaseService.access$100(this.this$0).onBondStateChanged(i);
            }
        }
    }
}
