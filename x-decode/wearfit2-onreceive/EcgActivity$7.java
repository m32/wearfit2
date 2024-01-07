package com.wakeup.wearfit2.ui.activity.ecg;

class EcgActivity$7 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.ui.activity.ecg.EcgActivity this$0;
    
    EcgActivity$7(com.wakeup.wearfit2.ui.activity.ecg.EcgActivity a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        String s = a0.getAction();
        label1: if (s.equals((Object)"com.wakeup.wearfit2.BROADCAST_DATA_AVAILABLE")) {
            byte[] a1 = a0.getByteArrayExtra("com.wakeup.wearfit2.EXTRA_DATA");
            if (a1 == null) {
                return;
            }
            android.util.Log.d(com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.TAG, "ecg ACTION_DATA_AVAILABLE");
            String s0 = com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.TAG;
            StringBuilder a2 = new StringBuilder();
            a2.append("\u63a5\u6536\u7684\u6570\u636e");
            a2.append(com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a1));
            android.util.Log.e(s0, a2.toString());
            String s1 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a1).replace((CharSequence)(Object)" ", (CharSequence)(Object)"");
            String s2 = s1.substring(0, 4);
            boolean b = s2.equals((Object)"ac06");
            label2: {
                if (!b) {
                    break label2;
                }
                if (s1.length() != 4) {
                    break label2;
                }
                com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$1102(this.this$0, 0);
                com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$502(this.this$0, 3);
                com.wakeup.wearfit2.ui.activity.ecg.EcgActivity a3 = this.this$0;
                com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$1900(a3, com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$500(a3));
                break label1;
            }
            if (s2.equals((Object)"ac02")) {
                Integer a4 = Integer.valueOf(s1.substring(4, 6), 16);
                String s3 = com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.TAG;
                StringBuilder a5 = new StringBuilder();
                a5.append((Object)a4);
                a5.append("");
                android.util.Log.d(s3, a5.toString());
                if (com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$800(this.this$0) != null) {
                    String s4 = String.valueOf((Object)s1.substring(4, 6));
                    if (!s4.equals((Object)"00")) {
                        com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$2602(this.this$0, s4);
                        com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$884(this.this$0, (Object)s4);
                    }
                } else {
                    String s5 = String.valueOf((Object)s1.substring(4, 6));
                    if (!s5.equals((Object)"00")) {
                        com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$802(this.this$0, s5);
                        com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$2602(this.this$0, s5);
                    }
                }
                android.os.Message a6 = android.os.Message.obtain();
                a6.what = 10;
                a6.arg1 = a4.intValue();
                com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$2700(this.this$0).sendMessage(a6);
            } else {
                boolean b0 = s2.equals((Object)"ac05");
                label0: {
                    if (!b0) {
                        break label0;
                    }
                    if (com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$500(this.this$0) == 4) {
                        break label0;
                    }
                    break label1;
                }
                if (s2.equals((Object)"ac01") && s1.length() > 10 && com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$2400(this.this$0) != null) {
                    com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$2800(this.this$0, com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a1));
                    if (com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$900(this.this$0) != null) {
                        com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$984(this.this$0, (Object)String.valueOf((Object)s1.substring(4)));
                    } else {
                        com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$902(this.this$0, String.valueOf((Object)s1.substring(4)));
                    }
                    com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$2208(this.this$0);
                    if (com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$2200(this.this$0) == 1) {
                        android.util.Log.e(com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.TAG, "\u6570\u636e\u6765\u4e86");
                        if (com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$000(this.this$0) != null) {
                            android.util.Log.e(com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.TAG, "dismiss");
                            com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$000(this.this$0).dismiss();
                        }
                        com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$502(this.this$0, 2);
                        com.wakeup.wearfit2.ui.activity.ecg.EcgActivity a7 = this.this$0;
                        com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$1900(a7, com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$500(a7));
                        com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$1102(this.this$0, 1);
                        this.this$0.mHandlers.sendEmptyMessageDelayed(0, 0L);
                    }
                }
            }
        } else if (s.equals((Object)"com.wakeup.wearfit2.ACTION_GATT_CONNECTED")) {
            String s6 = com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.TAG;
            StringBuilder a8 = new StringBuilder();
            a8.append(com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected());
            a8.append("");
            android.util.Log.i(s6, a8.toString());
        } else if (s.equals((Object)"com.wakeup.wearfit2.ACTION_GATT_DISCONNECTED")) {
            android.util.Log.e(com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.TAG, "\u65ad\u5f00\u8fde\u63a5");
            com.wakeup.wearfit2.ui.activity.ecg.EcgActivity.access$1900(this.this$0, 5);
        }
    }
}
