package no.nordicsemi.android.dfu;

class DfuBaseService$4 extends android.content.BroadcastReceiver {
    final no.nordicsemi.android.dfu.DfuBaseService this$0;
    
    DfuBaseService$4(no.nordicsemi.android.dfu.DfuBaseService a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        android.bluetooth.BluetoothDevice a1 = (android.bluetooth.BluetoothDevice)(Object)a0.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (a1 != null && a1.getAddress().equals((Object)no.nordicsemi.android.dfu.DfuBaseService.access$500(this.this$0))) {
            String s = a0.getAction();
            no.nordicsemi.android.dfu.DfuBaseService a2 = this.this$0;
            StringBuilder a3 = new StringBuilder();
            a3.append("Action received: ");
            a3.append(s);
            no.nordicsemi.android.dfu.DfuBaseService.access$000(a2, a3.toString());
            no.nordicsemi.android.dfu.DfuBaseService a4 = this.this$0;
            StringBuilder a5 = new StringBuilder();
            a5.append("[Broadcast] Action received: ");
            a5.append(s);
            a4.sendLogBroadcast(0, a5.toString());
        }
    }
}
