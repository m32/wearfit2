package com.wakeup.wearfit2.ui.activity.about;

class MeasureActivity$1 extends android.os.Handler {
    final com.wakeup.wearfit2.ui.activity.about.MeasureActivity this$0;
    
    MeasureActivity$1(com.wakeup.wearfit2.ui.activity.about.MeasureActivity a) {
        this.this$0 = a;
        super();
    }
    
    public void handleMessage(android.os.Message a) {
        if (a.what == 2) {
            com.wakeup.wearfit2.ui.activity.about.MeasureActivity.access$000(this.this$0).setOnceKeyMeasure(0);
            com.wakeup.wearfit2.ui.activity.about.MeasureActivity.access$102(this.this$0, 0);
            this.this$0.one_key_view.stopRotate();
            this.this$0.tv_one_key_measure.setEnabled(true);
            com.wakeup.wearfit2.ui.activity.about.MeasureActivity.access$202(this.this$0, false);
            long[] a0 = new long[4];
            a0[0] = 100L;
            a0[1] = 200L;
            a0[2] = 100L;
            a0[3] = 200L;
            com.wakeup.wearfit2.ui.activity.about.MeasureActivity.access$300(this.this$0).vibrate(a0, -1);
        }
    }
}
