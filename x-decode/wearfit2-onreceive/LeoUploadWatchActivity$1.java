package com.wakeup.wearfit2.ui.activity;

class LeoUploadWatchActivity$1 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.ui.activity.LeoUploadWatchActivity this$0;
    
    LeoUploadWatchActivity$1(com.wakeup.wearfit2.ui.activity.LeoUploadWatchActivity a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        String s = a0.getAction();
        s.hashCode();
        if (s.equals((Object)"com.wakeup.wearfit2.BROADCAST_CONNECTION_STATE")) {
            if (a0.getIntExtra("com.wakeup.wearfit2.EXTRA_CONNECTION_STATE", 0) == 0) {
                android.widget.Toast.makeText(a, 2131886401, 0).show();
                this.this$0.finish();
            }
        } else if (s.equals((Object)"com.wakeup.wearfit2.BROADCAST_DATA_AVAILABLE")) {
            byte[] a1 = a0.getByteArrayExtra("com.wakeup.wearfit2.EXTRA_DATA");
            label0: {
                label1: {
                    if (a1 == null) {
                        break label1;
                    }
                    if (a1.length != 0) {
                        break label0;
                    }
                }
                return;
            }
            String s0 = this.this$0.TAG;
            StringBuilder a2 = new StringBuilder();
            a2.append("data receive:-->");
            a2.append(com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a1));
            android.util.Log.i(s0, a2.toString());
            java.util.List a3 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToArrayList(a1);
            if (((Integer)a3.get(0)).intValue() == 173) {
                int i = ((Integer)a3.get(1)).intValue();
                StringBuffer a4 = new StringBuffer();
                a4.append(a3.get(2));
                a4.append(a3.get(3));
                int i0 = Integer.parseInt(a4.toString());
                int i1 = ((Integer)a3.get(4)).intValue();
                String s1 = this.this$0.TAG;
                StringBuilder a5 = new StringBuilder();
                a5.append("length = ");
                a5.append(i);
                a5.append("    index = ");
                a5.append(i0);
                a5.append("    status = ");
                a5.append(i1);
                com.wakeup.wearfit2.util.LogUtil.e(s1, a5.toString());
                com.wakeup.wearfit2.ui.activity.LeoUploadWatchActivity.access$000(this.this$0).setEvent(new com.wakeup.wearfit2.model.event.SendImageAnswerEvent(i, i0, i1));
            }
        }
    }
}
