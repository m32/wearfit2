package com.wakeup.wearfit2.ui.activity.camera;

class CameraActivity$1 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.ui.activity.camera.CameraActivity this$0;
    
    CameraActivity$1(com.wakeup.wearfit2.ui.activity.camera.CameraActivity a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        if ("com.wakeup.wearfit2.BROADCAST_DATA_AVAILABLE".equals((Object)a0.getAction())) {
            byte[] a1 = a0.getByteArrayExtra("com.wakeup.wearfit2.EXTRA_DATA");
            if (a1 == null) {
                return;
            }
            StringBuilder a2 = new StringBuilder();
            a2.append("\u63a5\u6536\u7684\u6570\u636ecamera\uff1a");
            a2.append(com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a1));
            android.util.Log.d("lq", a2.toString());
            if (((int)a1[0] & 255) == 171 && ((int)a1[4] & 255) == 121 && this.this$0.mContainer != null) {
                this.this$0.mContainer.takePicture((com.linj.camera.view.CameraContainer$TakePictureListener)(Object)this.this$0);
            }
        }
    }
}
