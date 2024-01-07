package com.wakeup.wearfit2.ui.activity.camera;

final public class CameraFragment$volumeDownReceiver$1 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.ui.activity.camera.CameraFragment this$0;
    
    CameraFragment$volumeDownReceiver$1(com.wakeup.wearfit2.ui.activity.camera.CameraFragment a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a, "context");
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a0, "intent");
        if (a0.getIntExtra("key_event_extra", 0) == 25) {
            androidx.constraintlayout.widget.ConstraintLayout a1 = com.wakeup.wearfit2.ui.activity.camera.CameraFragment.access$getContainer$p(this.this$0);
            if (a1 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("container");
                a1 = null;
            }
            android.widget.ImageButton a2 = (android.widget.ImageButton)a1.findViewById(2131296458);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue((Object)a2, "shutter");
            com.wakeup.wearfit2.ui.activity.camera.ViewExtensionsKt.simulateClick$default(a2, 0L, 1, (Object)null);
        }
    }
}
