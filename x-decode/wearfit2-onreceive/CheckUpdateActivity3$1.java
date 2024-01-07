package com.wakeup.wearfit2.ui.activity.update;

class CheckUpdateActivity3$1 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.ui.activity.update.CheckUpdateActivity3 this$0;
    
    CheckUpdateActivity3$1(com.wakeup.wearfit2.ui.activity.update.CheckUpdateActivity3 a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        String s = a0.getAction();
        s.hashCode();
        label0: if (s.equals((Object)"com.wakeup.wearfit2.BROADCAST_DATA_AVAILABLE")) {
            byte[] a1 = a0.getByteArrayExtra("com.wakeup.wearfit2.EXTRA_DATA");
            if (a1 == null) {
                return;
            }
            String s0 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a1);
            java.util.List a2 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToArrayList(a1);
            if (s0 == null) {
                return;
            }
            int i = ((Integer)a2.get(0)).intValue();
            label2: {
                label1: {
                    if (i != 1) {
                        break label1;
                    }
                    String[] a3 = s0.split(" ");
                    StringBuilder a4 = new StringBuilder();
                    int i0 = a3.length - 1;
                    for(; i0 >= 2; i0 = i0 + -1) {
                        a4.append(a3[i0]);
                        if (i0 != 2) {
                            a4.append(":");
                        }
                    }
                    this.this$0.ispMac = a4.toString().toUpperCase();
                    String s1 = com.wakeup.wearfit2.ui.activity.update.HtxotaActivity.TAG;
                    StringBuilder a5 = new StringBuilder();
                    a5.append("ispMac1\uff1a");
                    a5.append(this.this$0.ispMac);
                    android.util.Log.e(s1, a5.toString());
                    com.wakeup.wearfit2.ui.activity.update.CheckUpdateActivity3 a6 = this.this$0;
                    com.wakeup.wearfit2.util.SPUtils.putString((android.content.Context)a6, "isp_mac", a6.ispMac);
                    break label2;
                }
                if (((Integer)a2.get(0)).intValue() != 171) {
                    break label0;
                }
                if (((Integer)a2.get(4)).intValue() != 40) {
                    break label0;
                }
                if (((Integer)a2.get(5)).intValue() != 128) {
                    break label0;
                }
                String[] a7 = s0.split(" ");
                StringBuilder a8 = new StringBuilder();
                int i1 = a7.length - 1;
                for(; i1 >= 9; i1 = i1 + -1) {
                    a8.append(a7[i1]);
                    if (i1 != 9) {
                        a8.append(":");
                    }
                }
                this.this$0.ispMac = a8.toString().toUpperCase();
                String s2 = com.wakeup.wearfit2.ui.activity.update.HtxotaActivity.TAG;
                StringBuilder a9 = new StringBuilder();
                a9.append("ispMac2\uff1a");
                a9.append(this.this$0.ispMac);
                android.util.Log.e(s2, a9.toString());
                com.wakeup.wearfit2.ui.activity.update.CheckUpdateActivity3 a10 = this.this$0;
                com.wakeup.wearfit2.util.SPUtils.putString((android.content.Context)a10, "isp_mac", a10.ispMac);
            }
            if (com.wakeup.wearfit2.util.Is.isEmpty(this.this$0.ispMac)) {
                this.this$0.btUpdate.setVisibility(0);
                com.wakeup.wearfit2.ui.widge.dialog.LoadingDialog.dismissLoading();
                com.wakeup.wearfit2.ui.activity.update.CheckUpdateActivity3 a11 = this.this$0;
                android.widget.Toast.makeText((android.content.Context)a11, (CharSequence)(Object)a11.getString(2131886486), 0).show();
                return;
            }
            com.wakeup.wearfit2.ui.activity.update.CheckUpdateActivity3.access$000(this.this$0).bootloader();
            com.wakeup.wearfit2.ui.activity.update.CheckUpdateActivity3 a12 = this.this$0;
            com.wakeup.wearfit2.ui.activity.update.CheckUpdateActivity3.access$100(a12, a12.ispMac);
        }
    }
}
