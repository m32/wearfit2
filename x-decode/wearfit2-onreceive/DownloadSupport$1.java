package com.wakeup.wearfit2.util;

class DownloadSupport$1 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.util.DownloadSupport this$0;
    
    DownloadSupport$1(com.wakeup.wearfit2.util.DownloadSupport a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        if (a0.getLongExtra("extra_download_id", -1L) == com.wakeup.wearfit2.util.DownloadSupport.access$000(this.this$0)) {
            a.unregisterReceiver(this.this$0.broadcastReceiver);
            com.wakeup.wearfit2.util.DownloadSupport.access$100(this.this$0).onSuccess();
        }
    }
}
