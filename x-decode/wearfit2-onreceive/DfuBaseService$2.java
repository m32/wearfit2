package no.nordicsemi.android.dfu;

class DfuBaseService$2 extends android.content.BroadcastReceiver {
    final no.nordicsemi.android.dfu.DfuBaseService this$0;
    
    DfuBaseService$2(no.nordicsemi.android.dfu.DfuBaseService a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        Object a1 = null;
        Throwable a2 = null;
        int i = a0.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
        int i0 = a0.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", 12);
        no.nordicsemi.android.dfu.DfuBaseService a3 = this.this$0;
        StringBuilder a4 = new StringBuilder();
        a4.append("Action received: android.bluetooth.adapter.action.STATE_CHANGED [state: ");
        a4.append(i);
        a4.append(", previous state: ");
        a4.append(i0);
        a4.append("]");
        no.nordicsemi.android.dfu.DfuBaseService.access$300(a3, a4.toString());
        label0: {
            label1: if (i0 == 12) {
                label2: {
                    if (i == 13) {
                        break label2;
                    }
                    if (i != 10) {
                        break label1;
                    }
                }
                this.this$0.sendLogBroadcast(15, "Bluetooth adapter disabled");
                this.this$0.mConnectionState = 0;
                if (no.nordicsemi.android.dfu.DfuBaseService.access$100(this.this$0) != null) {
                    no.nordicsemi.android.dfu.DfuBaseService.access$100(this.this$0).getGattCallback().onDisconnected();
                }
                a1 = no.nordicsemi.android.dfu.DfuBaseService.access$400(this.this$0);
                /*monenter(a1)*/;
                try {
                    no.nordicsemi.android.dfu.DfuBaseService.access$400(this.this$0).notifyAll();
                    /*monexit(a1)*/;
                } catch(Throwable a5) {
                    a2 = a5;
                    break label0;
                }
            }
            return;
        }
        while(true) {
            try {
                /*monexit(a1)*/;
            } catch(IllegalMonitorStateException | NullPointerException a6) {
                Throwable a7 = a6;
                a2 = a7;
                continue;
            }
            throw a2;
        }
    }
}
