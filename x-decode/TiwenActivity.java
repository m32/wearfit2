package com.wakeup.wearfit2.kotlin.activity;

final public class TiwenActivity extends com.wakeup.wearfit2.kotlin.activity.BaseActivity2 implements com.wakeup.wearfit2.kotlin.fragment.TiwenDialogFragment$OnFragmentInteractionListener {
    final private long PERIOD;
    final private String TAG;
    public java.util.Map _$_findViewCache;
    private android.os.Handler mHandler;
    private boolean measuring;
    private boolean sheshidu;
    
    public TiwenActivity() {
        this._$_findViewCache = (java.util.Map)(Object)new java.util.LinkedHashMap();
        this.TAG = "TiwenActivity";
        this.PERIOD = 40000L;
    }
    
    public static void lambda$0HDGSpG7aFI0Ui_4xaUXExPR73A(com.wakeup.wearfit2.kotlin.activity.TiwenActivity a, android.view.View a0) {
        com.wakeup.wearfit2.kotlin.activity.TiwenActivity.onCreate$lambda-2(a, a0);
    }
    
    public static void lambda$KfeGONY_iEtpa_T7uX_SLEYgJ6k(com.wakeup.wearfit2.kotlin.activity.TiwenActivity a, android.view.View a0) {
        com.wakeup.wearfit2.kotlin.activity.TiwenActivity.onCreate$lambda-3(a, a0);
    }
    
    public static void lambda$UYF-lzkA9GSxF8Aw1HNx8_xd0Rg(android.widget.TextView a, com.wakeup.wearfit2.kotlin.activity.TiwenActivity a0) {
        com.wakeup.wearfit2.kotlin.activity.TiwenActivity.onCreate$lambda-6$lambda-5$lambda-4(a, a0);
    }
    
    public static void lambda$X29-BfdpRiV1cIkMMXEPfT4JBDo(com.wakeup.wearfit2.kotlin.activity.TiwenActivity a, android.view.View a0) {
        com.wakeup.wearfit2.kotlin.activity.TiwenActivity.onCreate$lambda-0(a, a0);
    }
    
    public static void lambda$fhtzfTHv84QVPBTAydeAOXIfpZI(com.wakeup.wearfit2.manager.CommandManager a, com.wakeup.wearfit2.kotlin.activity.TiwenActivity a0, android.widget.TextView a1) {
        com.wakeup.wearfit2.kotlin.activity.TiwenActivity.onCreate$lambda-6$lambda-5(a, a0, a1);
    }
    
    public static void lambda$nJxVi-x6Rq0hv6hVe7VtealyFLQ(com.wakeup.wearfit2.kotlin.activity.TiwenActivity a, com.wakeup.wearfit2.manager.CommandManager a0, android.widget.TextView a1, android.view.View a2) {
        com.wakeup.wearfit2.kotlin.activity.TiwenActivity.onCreate$lambda-6(a, a0, a1, a2);
    }
    
    public static void lambda$vbb1ovKk1q8OgdFqniMLl7QTm4E(com.wakeup.wearfit2.kotlin.activity.TiwenActivity a, android.view.View a0) {
        com.wakeup.wearfit2.kotlin.activity.TiwenActivity.onCreate$lambda-1(a, a0);
    }
    
    final private static void onCreate$lambda-0(com.wakeup.wearfit2.kotlin.activity.TiwenActivity a, android.view.View a0) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a, "this$0");
        androidx.navigation.ActivityKt.findNavController((android.app.Activity)a, 2131297192).navigate(2131297625);
    }
    
    final private static void onCreate$lambda-1(com.wakeup.wearfit2.kotlin.activity.TiwenActivity a, android.view.View a0) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a, "this$0");
        androidx.navigation.ActivityKt.findNavController((android.app.Activity)a, 2131297192).navigate(2131297627);
    }
    
    final private static void onCreate$lambda-2(com.wakeup.wearfit2.kotlin.activity.TiwenActivity a, android.view.View a0) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a, "this$0");
        androidx.navigation.ActivityKt.findNavController((android.app.Activity)a, 2131297192).navigate(2131297626);
    }
    
    final private static void onCreate$lambda-3(com.wakeup.wearfit2.kotlin.activity.TiwenActivity a, android.view.View a0) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a, "this$0");
        com.wakeup.wearfit2.kotlin.fragment.TiwenDialogFragment.Companion.newInstance().show(a.getSupportFragmentManager(), "tiwen");
    }
    
    final private static void onCreate$lambda-6(com.wakeup.wearfit2.kotlin.activity.TiwenActivity a, com.wakeup.wearfit2.manager.CommandManager a0, android.widget.TextView a1, android.view.View a2) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a, "this$0");
        if (com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) {
            if (a.measuring) {
                android.widget.Toast.makeText((android.content.Context)a, (CharSequence)(Object)a.getString(2131886668), 0).show();
            } else {
                a0.setOnceOrRealTimeMeasure(129, 1);
                a1.setText((CharSequence)(Object)kotlin.jvm.internal.Intrinsics.stringPlus(a.getString(2131887162), (Object)"..."));
                a.measuring = true;
                android.os.Handler a3 = a.mHandler;
                if (a3 != null) {
                    a3.postDelayed((Runnable)(Object)new com.wakeup.wearfit2.kotlin.activity.-$$Lambda$TiwenActivity$fhtzfTHv84QVPBTAydeAOXIfpZI(a0, a, a1), a.PERIOD);
                }
            }
        } else {
            android.widget.Toast.makeText((android.content.Context)a, (CharSequence)(Object)a.getString(2131886401), 0).show();
        }
    }
    
    final private static void onCreate$lambda-6$lambda-5(com.wakeup.wearfit2.manager.CommandManager a, com.wakeup.wearfit2.kotlin.activity.TiwenActivity a0, android.widget.TextView a1) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a0, "this$0");
        a.setOnceOrRealTimeMeasure(129, 0);
        a0.runOnUiThread((Runnable)(Object)new com.wakeup.wearfit2.kotlin.activity.-$$Lambda$TiwenActivity$UYF-lzkA9GSxF8Aw1HNx8_xd0Rg(a1, a0));
        a0.measuring = false;
    }
    
    final private static void onCreate$lambda-6$lambda-5$lambda-4(android.widget.TextView a, com.wakeup.wearfit2.kotlin.activity.TiwenActivity a0) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a0, "this$0");
        a.setText((CharSequence)(Object)a0.getString(2131887168));
    }
    
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }
    
    public android.view.View _$_findCachedViewById(int i) {
        java.util.Map a = this._$_findViewCache;
        android.view.View a0 = (android.view.View)a.get((Object)Integer.valueOf(i));
        if (a0 == null) {
            a0 = this.findViewById(i);
            if (a0 != null) {
                a.put((Object)Integer.valueOf(i), (Object)a0);
            } else {
                a0 = null;
            }
        }
        return a0;
    }
    
    public void onBackPressed() {
        if (this.measuring) {
            String s = this.getString(2131886668);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue((Object)s, "getString(R.string.measuring)");
            this.showMessage(s);
        } else {
            ((com.wakeup.wearfit2.kotlin.activity.BaseActivity2)this).onBackPressed();
        }
    }
    
    protected void onCreate(android.os.Bundle a) {
        ((com.wakeup.wearfit2.kotlin.activity.BaseActivity2)this).onCreate(a);
        com.wakeup.wearfit2.ad.ADHolder.showInterstitial((android.app.Activity)this);
        this.setContentView(2131493045);
        this.setStatusBarColor(2131099993);
        ((android.widget.RadioButton)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.tiwen_day)).setChecked(true);
        ((android.widget.RadioButton)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.tiwen_day)).setOnClickListener((android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.kotlin.activity.-$$Lambda$TiwenActivity$X29-BfdpRiV1cIkMMXEPfT4JBDo(this));
        ((android.widget.RadioButton)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.tiwen_week)).setOnClickListener((android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.kotlin.activity.-$$Lambda$TiwenActivity$vbb1ovKk1q8OgdFqniMLl7QTm4E(this));
        ((android.widget.RadioButton)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.tiwen_month)).setOnClickListener((android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.kotlin.activity.-$$Lambda$TiwenActivity$0HDGSpG7aFI0Ui_4xaUXExPR73A(this));
        ((com.wakeup.wearfit2.view.CommonTitleLayout2)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.tiwen_title)).setTitle(this.getString(2131887150));
        ((com.wakeup.wearfit2.view.CommonTitleLayout2)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.tiwen_title)).setRightImgOnclickListener((android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.kotlin.activity.-$$Lambda$TiwenActivity$KfeGONY_iEtpa_T7uX_SLEYgJ6k(this));
        android.widget.TextView a0 = (android.widget.TextView)this.findViewById(2131297142);
        com.wakeup.wearfit2.manager.CommandManager a1 = com.wakeup.wearfit2.manager.CommandManager.getInstance((android.content.Context)this);
        this.mHandler = new android.os.Handler();
        a0.setOnClickListener((android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.kotlin.activity.-$$Lambda$TiwenActivity$nJxVi-x6Rq0hv6hVe7VtealyFLQ(this, a1, a0));
        org.greenrobot.eventbus.EventBus.getDefault().register((Object)this);
        com.wakeup.wearfit2.bean.BodyTempBean a2 = (com.wakeup.wearfit2.bean.BodyTempBean)org.litepal.LitePal.findLast(com.wakeup.wearfit2.bean.BodyTempBean.class);
        Float a3 = (a2 != null) ? Float.valueOf(a2.getBodyTemp()) : null;
        boolean b = com.wakeup.wearfit2.util.SPUtils.getBoolean((android.content.Context)this, "sheshidu", true);
        this.sheshidu = b;
        if (b) {
            if (a3 != null) {
                android.widget.TextView a4 = (android.widget.TextView)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.recent_temp);
                if (a4 != null) {
                    StringBuilder a5 = new StringBuilder();
                    a5.append((Object)a3);
                    a5.append((char)8451);
                    a4.setText((CharSequence)(Object)a5.toString());
                }
            }
        } else if (a3 != null) {
            double d = (double)a3.floatValue() * 1.8 + (double)32;
            kotlin.jvm.internal.StringCompanionObject dummy = kotlin.jvm.internal.StringCompanionObject.INSTANCE;
            Object[] a6 = new Object[1];
            a6[0] = Double.valueOf(d);
            String s = String.format("%.1f", java.util.Arrays.copyOf(a6, 1));
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue((Object)s, "format(format, *args)");
            android.widget.TextView a7 = (android.widget.TextView)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.recent_temp);
            if (a7 != null) {
                a7.setText((CharSequence)(Object)kotlin.jvm.internal.Intrinsics.stringPlus(s, (Object)"\u2109"));
            }
        }
        Float a8 = (a3 != null) ? Float.valueOf(Float.valueOf(a3.floatValue() - 35f).floatValue() / 6f * (float)100) : null;
        android.util.Log.i(this.TAG, kotlin.jvm.internal.Intrinsics.stringPlus("div ", (Object)a8));
        if (a8 != null) {
            com.wakeup.wearfit2.ui.view.CircleView a9 = (com.wakeup.wearfit2.ui.view.CircleView)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.stepCircle);
            if (a9 != null) {
                a9.setPercent((int)a8.floatValue());
            }
        }
    }
    
    protected void onDestroy() {
        ((com.wakeup.wearfit2.kotlin.activity.BaseActivity2)this).onDestroy();
        android.util.Log.i(this.TAG, "onDestroy");
        org.greenrobot.eventbus.EventBus.getDefault().unregister((Object)this);
    }
    
    final public void onEventMainThread(com.wakeup.wearfit2.model.event.BaseEvent a) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a, "baseEvent");
        com.wakeup.wearfit2.model.event.BaseEvent$EventType a0 = a.getEventType();
        if (((a0 != null) ? com.wakeup.wearfit2.kotlin.activity.TiwenActivity$WhenMappings.$EnumSwitchMapping$0[a0.ordinal()] : -1) == 1) {
            com.wakeup.wearfit2.bean.BodyTempBean a1 = (com.wakeup.wearfit2.bean.BodyTempBean)org.litepal.LitePal.findLast(com.wakeup.wearfit2.bean.BodyTempBean.class);
            Float a2 = (a1 != null) ? Float.valueOf(a1.getBodyTemp()) : null;
            if (this.sheshidu) {
                if (a2 != null) {
                    android.widget.TextView a3 = (android.widget.TextView)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.recent_temp);
                    if (a3 != null) {
                        StringBuilder a4 = new StringBuilder();
                        a4.append((Object)a2);
                        a4.append((char)8451);
                        a3.setText((CharSequence)(Object)a4.toString());
                    }
                }
            } else if (a2 != null) {
                double d = (double)a2.floatValue() * 1.8 + (double)32;
                kotlin.jvm.internal.StringCompanionObject dummy = kotlin.jvm.internal.StringCompanionObject.INSTANCE;
                Object[] a5 = new Object[1];
                a5[0] = Double.valueOf(d);
                String s = String.format("%.1f", java.util.Arrays.copyOf(a5, 1));
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue((Object)s, "format(format, *args)");
                android.widget.TextView a6 = (android.widget.TextView)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.recent_temp);
                if (a6 != null) {
                    a6.setText((CharSequence)(Object)kotlin.jvm.internal.Intrinsics.stringPlus(s, (Object)"\u2109"));
                }
            }
            Float a7 = (a2 != null) ? Float.valueOf(Float.valueOf(a2.floatValue() - 35f).floatValue() / 6f * (float)100) : null;
            android.util.Log.i(this.TAG, kotlin.jvm.internal.Intrinsics.stringPlus("div ", (Object)a7));
            if (a7 != null) {
                com.wakeup.wearfit2.ui.view.CircleView a8 = (com.wakeup.wearfit2.ui.view.CircleView)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.stepCircle);
                if (a8 != null) {
                    a8.setPercent((int)a7.floatValue());
                }
            }
        }
    }
    
    public void onFragmentInteraction(float f) {
        android.util.Log.i(this.TAG, kotlin.jvm.internal.Intrinsics.stringPlus("onFragmentInteraction: ", (Object)Float.valueOf(f)));
        new com.wakeup.wearfit2.bean.BodyTempBean(f, System.currentTimeMillis(), "").save();
        org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.BODY_TEMP_FINISHED));
    }
}
