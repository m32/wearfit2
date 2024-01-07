package com.wakeup.wearfit2.ui.activity.camera;

final public class CameraFragment$mCommonBroadcastReceiver$1 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.ui.activity.camera.CameraFragment this$0;
    
    CameraFragment$mCommonBroadcastReceiver$1(com.wakeup.wearfit2.ui.activity.camera.CameraFragment a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a, "context");
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a0, "intent");
        String s = a0.getAction();
        if (s == null) {
            return;
        }
        if (s.hashCode() == 1752867454 && s.equals((Object)"com.wakeup.wearfit2.BROADCAST_DATA_AVAILABLE")) {
            byte[] a1 = a0.getByteArrayExtra("com.wakeup.wearfit2.EXTRA_DATA");
            if (a1 != null && !(a1.length == 0)) {
                com.wakeup.wearfit2.ui.activity.camera.CameraFragment.access$getLog$cp().info(kotlin.jvm.internal.Intrinsics.stringPlus("receive data ", (Object)com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a1)));
                java.util.List a2 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToArrayList(a1);
                if (a2.isEmpty()) {
                    return;
                }
                Integer a3 = (Integer)a2.get(0);
                if (a3 != null && a3.intValue() == 171) {
                    Integer a4 = (Integer)a2.get(4);
                    if (a4 != null && a4.intValue() == 121) {
                        com.wakeup.wearfit2.ui.activity.camera.CameraFragment.access$cameraCapture(this.this$0);
                    }
                }
            }
        }
    }
}
