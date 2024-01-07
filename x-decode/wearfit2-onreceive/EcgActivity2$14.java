package com.wakeup.wearfit2.ui.activity.ecg;

class EcgActivity2$14 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2 this$0;
    
    EcgActivity2$14(com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2 a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        String s = a0.getAction();
        s.hashCode();
        if (s.equals((Object)"com.wakeup.wearfit2.BROADCAST_CONNECTION_STATE")) {
            int i = a0.getIntExtra("com.wakeup.wearfit2.EXTRA_CONNECTION_STATE", 0);
            if (i == 0) {
                com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.access$1000(this.this$0);
            } else if (i == 1) {
                com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.access$900(this.this$0);
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
            String s0 = com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.TAG;
            StringBuilder a2 = new StringBuilder();
            a2.append("data receive:-->");
            a2.append(com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a1));
            android.util.Log.i(s0, a2.toString());
            java.util.List a3 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToArrayList(a1);
            if (((Integer)a3.get(0)).intValue() == 172 && ((Integer)a3.get(1)).intValue() == 3) {
                String s1 = com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.TAG;
                StringBuilder a4 = new StringBuilder();
                a4.append("\u5f00\u59cb\u753b\u5fc3\u7535\u56fe");
                a4.append(com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.access$000(this.this$0));
                android.util.Log.i(s1, a4.toString());
                if (!com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.access$000(this.this$0)) {
                    com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.access$200(this.this$0);
                    com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.access$300(this.this$0);
                }
                com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.access$402(this.this$0, false);
            }
            if (((Integer)a3.get(0)).intValue() == 172 && ((Integer)a3.get(1)).intValue() == 7) {
                android.util.Log.i(com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.TAG, "\u624b\u62ff\u5f00\u4e86");
                com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.access$402(this.this$0, true);
            }
            if (((Integer)a3.get(0)).intValue() == 172 && ((Integer)a3.get(1)).intValue() == 6) {
                android.util.Log.i(com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.TAG, "\u68c0\u6d4b\u5931\u8d25");
                if (com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.access$500(this.this$0) != null) {
                    com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.access$500(this.this$0).dismiss();
                }
                if (com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.access$600(this.this$0).equals((Object)"S20") && com.wakeup.wearfit2.AppApplication.band_type == 253) {
                    android.util.Log.i(com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.TAG, "S20\u6ca1\u6709\u68c0\u6d4b");
                    return;
                }
                com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.access$100(this.this$0, 0);
            }
            if (((Integer)a3.get(0)).intValue() == 172 && ((Integer)a3.get(1)).intValue() == 8) {
                android.util.Log.i(com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.TAG, "\u6d4b\u91cf\u5931\u8d25");
                com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.access$700(this.this$0);
            }
            if (((Integer)a3.get(0)).intValue() == 172 && ((Integer)a3.get(1)).intValue() == 5) {
                android.util.Log.i(com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.TAG, "\u6d4b\u91cf\u5b8c\u6210");
                com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.access$700(this.this$0);
            }
            if (((Integer)a3.get(0)).intValue() == 172 && ((Integer)a3.get(1)).intValue() == 2) {
                int i0 = ((Integer)a3.get(3)).intValue();
                com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.access$800(this.this$0).add((Object)Integer.valueOf(i0));
                String s2 = com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.TAG;
                StringBuilder a5 = new StringBuilder();
                a5.append("heartRate: ");
                a5.append(i0);
                android.util.Log.i(s2, a5.toString());
                if (this.this$0.tvEcgValue != null) {
                    this.this$0.tvEcgValue.setText((CharSequence)(Object)String.valueOf(i0));
                }
                if (this.this$0.bpmValue != null) {
                    android.widget.TextView a6 = this.this$0.bpmValue;
                    StringBuilder a7 = new StringBuilder();
                    a7.append(String.valueOf(i0));
                    a7.append(" BPM");
                    a6.setText((CharSequence)(Object)a7.toString());
                }
                if (this.this$0.tvUnfoldEcgValue != null) {
                    this.this$0.tvUnfoldEcgValue.setText((CharSequence)(Object)String.valueOf(i0));
                }
            }
        }
    }
}
