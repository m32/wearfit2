package com.wakeup.wearfit2.ui.activity.ecg;

class EcgHistoryRecordActivity$3 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity this$0;
    
    EcgHistoryRecordActivity$3(com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        this.this$0.handler.removeMessages(1);
        String s = a0.getAction();
        com.wakeup.wearfit2.bean.EcgBean a1 = new com.wakeup.wearfit2.bean.EcgBean();
        label0: if (s.equals((Object)"com.wakeup.wearfit2.BROADCAST_DATA_AVAILABLE")) {
            byte[] a2 = a0.getByteArrayExtra("com.wakeup.wearfit2.EXTRA_DATA");
            if (a2 == null) {
                return;
            }
            android.util.Log.e("lq656789", com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a2));
            java.util.List a3 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToArrayList(a2);
            String s0 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a2).replace((CharSequence)(Object)" ", (CharSequence)(Object)"");
            int i = a3.size();
            label8: {
                if (i != 20) {
                    break label8;
                }
                if (((Integer)a3.get(0)).intValue() != 171) {
                    break label8;
                }
                if (((Integer)a3.get(4)).intValue() != 38) {
                    break label8;
                }
                if (com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity.access$200(this.this$0)) {
                    com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity.access$484(this.this$0, (Object)String.valueOf((Object)s0.substring(12, s0.length())));
                    break label0;
                } else {
                    com.wakeup.wearfit2.AppApplication.isEcg = true;
                    com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity.access$302(this.this$0, "");
                    com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity.access$402(this.this$0, String.valueOf((Object)s0.substring(12, s0.length())));
                    com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity a4 = this.this$0;
                    com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity.access$202(a4, com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity.access$200(a4) ^ true);
                    break label0;
                }
            }
            int i0 = a3.size();
            label7: {
                if (i0 != 20) {
                    break label7;
                }
                if (((Integer)a3.get(0)).intValue() != 0) {
                    break label7;
                }
                com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity.access$484(this.this$0, (Object)String.valueOf((Object)s0.substring(2, s0.length())));
                break label0;
            }
            int i1 = a3.size();
            label6: {
                if (i1 != 20) {
                    break label6;
                }
                if (((Integer)a3.get(0)).intValue() != 1) {
                    break label6;
                }
                com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity.access$484(this.this$0, (Object)String.valueOf((Object)s0.substring(2, s0.length())));
                break label0;
            }
            int i2 = a3.size();
            label5: {
                if (i2 != 20) {
                    break label5;
                }
                if (((Integer)a3.get(0)).intValue() != 2) {
                    break label5;
                }
                com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity.access$484(this.this$0, (Object)String.valueOf((Object)s0.substring(2, s0.length())));
                break label0;
            }
            int i3 = a3.size();
            label4: {
                if (i3 != 3) {
                    break label4;
                }
                if (((Integer)a3.get(0)).intValue() != 3) {
                    break label4;
                }
                com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity.access$484(this.this$0, (Object)String.valueOf((Object)s0.substring(2, 4)));
                if (s0.substring(4, 6).equals((Object)"00")) {
                    break label0;
                }
                com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity.access$384(this.this$0, (Object)String.valueOf((Object)s0.substring(4, 6)));
                break label0;
            }
            int i4 = a3.size();
            label3: {
                if (i4 != 2) {
                    break label3;
                }
                if (((Integer)a3.get(0)).intValue() != 3) {
                    break label3;
                }
                com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity.access$484(this.this$0, (Object)String.valueOf((Object)s0.substring(2, 4)));
                break label0;
            }
            int i5 = s0.length();
            label1: {
                label2: {
                    if (i5 != 22) {
                        break label2;
                    }
                    if (s0.substring(0, 12).equals((Object)"ab0008ff2680")) {
                        break label1;
                    }
                }
                if (a3.size() != 7) {
                    break label0;
                }
                if (((Integer)a3.get(0)).intValue() != 171) {
                    break label0;
                }
                if (((Integer)a3.get(4)).intValue() != 39) {
                    break label0;
                }
                if (((Integer)a3.get(6)).intValue() != 0) {
                    break label0;
                }
                this.this$0.mSwip.setRefreshing(false);
                com.wakeup.wearfit2.AppApplication.isEcg = false;
                break label0;
            }
            int i6 = ((Integer)a3.get(6)).intValue() + 2000;
            int i7 = ((Integer)a3.get(7)).intValue();
            int i8 = ((Integer)a3.get(8)).intValue();
            int i9 = ((Integer)a3.get(9)).intValue();
            int i10 = ((Integer)a3.get(10)).intValue();
            StringBuilder a5 = new StringBuilder();
            a5.append(i6);
            Object[] a6 = new Object[1];
            a6[0] = Integer.valueOf(i7);
            a5.append(String.format("%02d", a6));
            Object[] a7 = new Object[1];
            a7[0] = Integer.valueOf(i8);
            a5.append(String.format("%02d", a7));
            Object[] a8 = new Object[1];
            a8[0] = Integer.valueOf(i9);
            a5.append(String.format("%02d", a8));
            Object[] a9 = new Object[1];
            a9[0] = Integer.valueOf(i10);
            a5.append(String.format("%02d", a9));
            long j = com.wakeup.wearfit2.util.DateUtils.getMilliSecondFromTime(a5.toString());
            a1.setEcgData(com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity.access$400(this.this$0));
            a1.setBpmData(com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity.access$300(this.this$0));
            a1.setTimeMillis(j);
            a1.setMac(com.wakeup.wearfit2.AppApplication.device_address);
            if (a1.save()) {
                com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity a10 = this.this$0;
                StringBuilder a11 = new StringBuilder();
                a11.append(com.wakeup.wearfit2.AppApplication.device_address);
                a11.append("_ecg");
                com.wakeup.wearfit2.util.SPUtils.putLong((android.content.Context)a10, a11.toString(), j);
                new com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity$ReadDBTask(this.this$0, (com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity$1)null).execute((Object[])new Long[0]);
            }
            com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity.access$202(this.this$0, false);
        } else if (s.equals((Object)"com.wakeup.wearfit2.ACTION_GATT_CONNECTED")) {
            StringBuilder a12 = new StringBuilder();
            a12.append(com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected());
            a12.append("");
            android.util.Log.i("wxk--data", a12.toString());
        } else if (s.equals((Object)"com.wakeup.wearfit2.ACTION_GATT_DISCONNECTED")) {
            android.util.Log.e("wxk--data", "\u65ad\u5f00\u8fde\u63a5");
        }
    }
}
