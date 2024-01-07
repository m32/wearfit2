package com.wakeup.wearfit2;

class AppApplication$TimeChangedReceiver extends android.content.BroadcastReceiver {
    private java.text.SimpleDateFormat dateFormat;
    final com.wakeup.wearfit2.AppApplication this$0;
    
    AppApplication$TimeChangedReceiver(com.wakeup.wearfit2.AppApplication a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        int i = 0;
        if (this.dateFormat == null) {
            this.dateFormat = new java.text.SimpleDateFormat("mm");
        }
        String s = this.dateFormat.format(new java.util.Date(System.currentTimeMillis()));
        s.hashCode();
        switch(s.hashCode()) {
            case 1691: {
                i = (s.equals((Object)"50")) ? 5 : -1;
                break;
            }
            case 1660: {
                i = (s.equals((Object)"40")) ? 4 : -1;
                break;
            }
            case 1629: {
                i = (s.equals((Object)"30")) ? 3 : -1;
                break;
            }
            case 1598: {
                i = (s.equals((Object)"20")) ? 2 : -1;
                break;
            }
            case 1567: {
                i = (s.equals((Object)"10")) ? 1 : -1;
                break;
            }
            case 1538: {
                i = (s.equals((Object)"02")) ? 0 : -1;
                break;
            }
            default: {
                i = -1;
            }
        }
        switch(i) {
            case 0: case 1: case 2: case 3: case 4: case 5: {
                com.wakeup.wearfit2.AppApplication.access$700(this.this$0).setTimeSync();
                break;
            }
        }
    }
}
