package com.wakeup.wearfit2.ui.activity.ecg;

class EcgHistoryRecordActivity2$12 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity2 this$0;
    
    EcgHistoryRecordActivity2$12(com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity2 a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        String s = a0.getAction();
        s.hashCode();
        if (s.equals((Object)"com.wakeup.wearfit2.BROADCAST_CONNECTION_STATE")) {
            int i = a0.getIntExtra("com.wakeup.wearfit2.EXTRA_CONNECTION_STATE", 0);
            if (i == 0) {
                com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity2.access$500(this.this$0);
            } else if (i == 1) {
                com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity2.access$400(this.this$0);
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
            String s0 = com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity2.access$200();
            StringBuilder a2 = new StringBuilder();
            a2.append("data receive:-->");
            a2.append(com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a1));
            android.util.Log.i(s0, a2.toString());
            java.util.List a3 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToArrayList(a1);
            if (((Integer)a3.get(0)).intValue() == 171 && ((Integer)a3.get(2)).intValue() == 9 && ((Integer)a3.get(4)).intValue() == 40) {
                int i0 = ((Integer)a3.get(6)).intValue() + 2000;
                int i1 = ((Integer)a3.get(7)).intValue();
                int i2 = ((Integer)a3.get(8)).intValue();
                int i3 = ((Integer)a3.get(9)).intValue();
                int i4 = ((Integer)a3.get(10)).intValue();
                int i5 = ((Integer)a3.get(11)).intValue();
                StringBuilder a4 = new StringBuilder();
                a4.append(i0);
                Object[] a5 = new Object[1];
                a5[0] = Integer.valueOf(i1);
                a4.append(String.format("%02d", a5));
                Object[] a6 = new Object[1];
                a6[0] = Integer.valueOf(i2);
                a4.append(String.format("%02d", a6));
                Object[] a7 = new Object[1];
                a7[0] = Integer.valueOf(i3);
                a4.append(String.format("%02d", a7));
                Object[] a8 = new Object[1];
                a8[0] = Integer.valueOf(i4);
                a4.append(String.format("%02d", a8));
                long j = com.wakeup.wearfit2.util.DateUtils.getMilliSecondFromTime(a4.toString());
                com.wakeup.wearfit2.bean.EcgBean2 a9 = new com.wakeup.wearfit2.bean.EcgBean2();
                a9.setTimeMillis(j);
                a9.setMac(com.wakeup.wearfit2.AppApplication.device_address);
                a9.setEcgData(com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity2.access$300(this.this$0));
                a9.setMeasureTime(35000);
                java.util.ArrayList a10 = new java.util.ArrayList();
                ((java.util.List)(Object)a10).add((Object)Integer.valueOf(i5));
                a9.setHeartrates((java.util.List)(Object)a10);
                String[] a11 = new String[2];
                a11[0] = "timeMillis = ?";
                a11[1] = String.valueOf(j);
                if (a9.saveOrUpdate(a11)) {
                    String s1 = com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity2.access$200();
                    StringBuilder a12 = new StringBuilder();
                    a12.append("\u4fdd\u5b58\u6210\u529f:");
                    a12.append(a9.toString());
                    android.util.Log.i(s1, a12.toString());
                    new com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity2$ReadDBTask(this.this$0, (com.wakeup.wearfit2.ui.activity.ecg.EcgHistoryRecordActivity2$1)null).execute((Object[])new Long[0]);
                }
            }
        }
    }
}
