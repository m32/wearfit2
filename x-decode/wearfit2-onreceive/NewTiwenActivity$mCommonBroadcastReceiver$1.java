package com.wakeup.wearfit2.kotlin.activity;

final public class NewTiwenActivity$mCommonBroadcastReceiver$1 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.kotlin.activity.NewTiwenActivity this$0;
    
    NewTiwenActivity$mCommonBroadcastReceiver$1(com.wakeup.wearfit2.kotlin.activity.NewTiwenActivity a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a, "context");
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a0, "intent");
        String s = a0.getAction();
        if (s != null) {
            switch(s.hashCode()) {
                case 1752867454: {
                    if (!s.equals((Object)"com.wakeup.wearfit2.BROADCAST_DATA_AVAILABLE")) {
                        break;
                    }
                    byte[] a1 = a0.getByteArrayExtra("com.wakeup.wearfit2.EXTRA_DATA");
                    String s0 = com.wakeup.wearfit2.kotlin.activity.NewTiwenActivity.access$getTAG$p(this.this$0);
                    String s1 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a1);
                    kotlin.jvm.internal.Intrinsics.checkNotNull((Object)s1);
                    android.util.Log.i(s0, kotlin.jvm.internal.Intrinsics.stringPlus("data receive:-->", (Object)s1));
                    java.util.List a2 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToArrayList(a1);
                    Integer a3 = (Integer)a2.get(4);
                    if (a3 == null) {
                        break;
                    }
                    if (a3.intValue() != 134) {
                        break;
                    }
                    Integer a4 = (Integer)a2.get(6);
                    Integer a5 = (Integer)a2.get(7);
                    label4: {
                        label6: {
                            label5: {
                                if (a4 != null) {
                                    break label5;
                                }
                                break label6;
                            }
                            if (a4.intValue() == 0) {
                                break label4;
                            }
                        }
                        float f = (float)a4.intValue() + (float)a5.intValue() / 10f;
                        if (com.wakeup.wearfit2.kotlin.activity.NewTiwenActivity.access$getSheshidu$p(this.this$0)) {
                            android.widget.TextView a6 = (android.widget.TextView)this.this$0._$_findCachedViewById(com.wakeup.wearfit2.R$id.mData);
                            StringBuilder a7 = new StringBuilder();
                            a7.append(f);
                            a7.append(" \u2103");
                            a6.setText((CharSequence)(Object)a7.toString());
                        } else {
                            double d = (double)32 + (double)f * 1.8;
                            kotlin.jvm.internal.StringCompanionObject dummy = kotlin.jvm.internal.StringCompanionObject.INSTANCE;
                            Object[] a8 = new Object[1];
                            a8[0] = Double.valueOf(d);
                            String s2 = String.format("%.1f", java.util.Arrays.copyOf(a8, 1));
                            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue((Object)s2, "format(format, *args)");
                            ((android.widget.TextView)this.this$0._$_findCachedViewById(com.wakeup.wearfit2.R$id.mData)).setText((CharSequence)(Object)kotlin.jvm.internal.Intrinsics.stringPlus(s2, (Object)" \u2109"));
                        }
                        if (!com.wakeup.wearfit2.kotlin.activity.NewTiwenActivity.access$getAnimationRunning$p(this.this$0)) {
                            com.wakeup.wearfit2.kotlin.activity.NewTiwenActivity.access$startAnimation(this.this$0);
                            ((android.widget.TextView)this.this$0._$_findCachedViewById(com.wakeup.wearfit2.R$id.measure_status)).setVisibility(0);
                        }
                    }
                    label1: {
                        label3: {
                            label2: {
                                if (a4 != null) {
                                    break label2;
                                }
                                break label3;
                            }
                            if (a4.intValue() == 255) {
                                break label1;
                            }
                        }
                        label0: {
                            if (a5 != null) {
                                break label0;
                            }
                            break;
                        }
                        if (a5.intValue() != 255) {
                            break;
                        }
                    }
                    if (com.wakeup.wearfit2.kotlin.activity.NewTiwenActivity.access$getSheshidu$p(this.this$0)) {
                        ((android.widget.TextView)this.this$0._$_findCachedViewById(com.wakeup.wearfit2.R$id.mData)).setText((CharSequence)(Object)"-- \u2103");
                    } else {
                        ((android.widget.TextView)this.this$0._$_findCachedViewById(com.wakeup.wearfit2.R$id.mData)).setText((CharSequence)(Object)"-- \u2109");
                    }
                    com.wakeup.wearfit2.kotlin.activity.NewTiwenActivity.access$stopAnimation(this.this$0);
                    ((android.widget.TextView)this.this$0._$_findCachedViewById(com.wakeup.wearfit2.R$id.measure_status)).setVisibility(8);
                    break;
                }
                case 918932346: {
                    if (!s.equals((Object)"com.wakeup.wearfit2.BROADCAST_CONNECTION_STATE")) {
                        break;
                    }
                    int i = a0.getIntExtra("com.wakeup.wearfit2.EXTRA_CONNECTION_STATE", 0);
                    if (i == 0) {
                        com.wakeup.wearfit2.kotlin.activity.NewTiwenActivity.access$onDeviceDisconnected(this.this$0);
                        break;
                    } else {
                        if (i != 1) {
                            break;
                        }
                        com.wakeup.wearfit2.kotlin.activity.NewTiwenActivity.access$onDeviceConnected(this.this$0);
                        break;
                    }
                }
                case 110849683: {
                    if (!s.equals((Object)"com.wakeup.wearfit2.BROADCAST_DATA_SEND")) {
                        break;
                    }
                    byte[] a9 = a0.getByteArrayExtra("com.wakeup.wearfit2.EXTRA_DATA");
                    String s3 = com.wakeup.wearfit2.kotlin.activity.NewTiwenActivity.access$getTAG$p(this.this$0);
                    String s4 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a9);
                    kotlin.jvm.internal.Intrinsics.checkNotNull((Object)s4);
                    android.util.Log.i(s3, kotlin.jvm.internal.Intrinsics.stringPlus("data send:-->", (Object)s4));
                    break;
                }
                case -2135702746: {
                    if (!s.equals((Object)"com.wakeup.wearfit2.DEVICE_READY")) {
                        break;
                    }
                    com.wakeup.wearfit2.kotlin.activity.NewTiwenActivity.access$onDeviceReady(this.this$0);
                    break;
                }
            }
        }
    }
}
