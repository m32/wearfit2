package no.nordicsemi.android.dfu;

class DfuBaseService$1 extends android.content.BroadcastReceiver {
    final no.nordicsemi.android.dfu.DfuBaseService this$0;
    
    DfuBaseService$1(no.nordicsemi.android.dfu.DfuBaseService a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        int i = a0.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_ACTION", 0);
        no.nordicsemi.android.dfu.DfuBaseService a1 = this.this$0;
        StringBuilder a2 = new StringBuilder();
        a2.append("User action received: ");
        a2.append(i);
        no.nordicsemi.android.dfu.DfuBaseService.access$000(a1, a2.toString());
        if (i == 0) {
            this.this$0.sendLogBroadcast(15, "[Broadcast] Pause action received");
            if (no.nordicsemi.android.dfu.DfuBaseService.access$100(this.this$0) != null) {
                no.nordicsemi.android.dfu.DfuBaseService.access$100(this.this$0).pause();
            }
        } else if (i == 1) {
            this.this$0.sendLogBroadcast(15, "[Broadcast] Resume action received");
            if (no.nordicsemi.android.dfu.DfuBaseService.access$100(this.this$0) != null) {
                no.nordicsemi.android.dfu.DfuBaseService.access$100(this.this$0).resume();
            }
        } else if (i == 2) {
            this.this$0.sendLogBroadcast(15, "[Broadcast] Abort action received");
            no.nordicsemi.android.dfu.DfuBaseService.access$202(this.this$0, true);
            if (no.nordicsemi.android.dfu.DfuBaseService.access$100(this.this$0) != null) {
                no.nordicsemi.android.dfu.DfuBaseService.access$100(this.this$0).abort();
            }
        }
    }
}
