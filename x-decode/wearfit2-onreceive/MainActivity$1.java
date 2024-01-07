package com.wakeup.wearfit2.ui.activity;

class MainActivity$1 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.ui.activity.MainActivity this$0;
    
    MainActivity$1(com.wakeup.wearfit2.ui.activity.MainActivity a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        int i = 0;
        String s = a0.getAction();
        s.hashCode();
        switch(s.hashCode()) {
            case 1752867454: {
                i = (s.equals((Object)"com.wakeup.wearfit2.BROADCAST_DATA_AVAILABLE")) ? 3 : -1;
                break;
            }
            case 918932346: {
                i = (s.equals((Object)"com.wakeup.wearfit2.BROADCAST_CONNECTION_STATE")) ? 2 : -1;
                break;
            }
            case 110849683: {
                i = (s.equals((Object)"com.wakeup.wearfit2.BROADCAST_DATA_SEND")) ? 1 : -1;
                break;
            }
            case -2135702746: {
                i = (s.equals((Object)"com.wakeup.wearfit2.DEVICE_READY")) ? 0 : -1;
                break;
            }
            default: {
                i = -1;
            }
        }
        switch(i) {
            case 3: {
                byte[] a1 = a0.getByteArrayExtra("com.wakeup.wearfit2.EXTRA_DATA");
                String s0 = com.wakeup.wearfit2.ui.activity.MainActivity.access$000();
                StringBuilder a2 = new StringBuilder();
                a2.append("data receive:-->");
                a2.append(com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a1));
                android.util.Log.i(s0, a2.toString());
                break;
            }
            case 2: {
                int i0 = a0.getIntExtra("com.wakeup.wearfit2.EXTRA_CONNECTION_STATE", 0);
                if (i0 == 0) {
                    com.wakeup.wearfit2.ui.activity.MainActivity.access$300(this.this$0);
                    break;
                } else {
                    if (i0 != 1) {
                        break;
                    }
                    com.wakeup.wearfit2.ui.activity.MainActivity.access$100(this.this$0);
                    com.wakeup.wearfit2.ui.activity.MainActivity.access$200(this.this$0);
                    break;
                }
            }
            case 1: {
                byte[] a3 = a0.getByteArrayExtra("com.wakeup.wearfit2.EXTRA_DATA");
                String s1 = com.wakeup.wearfit2.ui.activity.MainActivity.access$000();
                StringBuilder a4 = new StringBuilder();
                a4.append("data send:-->");
                a4.append(com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a3));
                android.util.Log.i(s1, a4.toString());
                break;
            }
            case 0: {
                com.wakeup.wearfit2.ui.activity.MainActivity.access$400(this.this$0);
                break;
            }
        }
    }
}
