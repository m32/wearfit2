package com.wakeup.wearfit2.kotlin.activity;

final public class MianyiActivity extends com.wakeup.wearfit2.kotlin.activity.BaseActivity2 {
    final private long PERIOD;
    final private String TAG;
    public java.util.Map _$_findViewCache;
    private android.os.Handler mHandler;
    private android.widget.TextView measure;
    private boolean measuring;
    
    public MianyiActivity() {
        this._$_findViewCache = (java.util.Map)(Object)new java.util.LinkedHashMap();
        this.TAG = "MianyiActivity";
        this.PERIOD = 40000L;
    }
    
    public static void lambda$JHNdTv3SVnRfS4rok2oakEqrU8g(com.wakeup.wearfit2.kotlin.activity.MianyiActivity a, android.view.View a0) {
        com.wakeup.wearfit2.kotlin.activity.MianyiActivity.onCreate$lambda-1(a, a0);
    }
    
    public static void lambda$RSLUWYs74h2E3ksmmufg2invAPY(com.wakeup.wearfit2.kotlin.activity.MianyiActivity a, com.wakeup.wearfit2.manager.CommandManager a0, android.view.View a1) {
        com.wakeup.wearfit2.kotlin.activity.MianyiActivity.onCreate$lambda-5(a, a0, a1);
    }
    
    public static void lambda$YSSXkcNvxMYoiRWz5d5HpObkBVQ(com.wakeup.wearfit2.manager.CommandManager a, com.wakeup.wearfit2.kotlin.activity.MianyiActivity a0) {
        com.wakeup.wearfit2.kotlin.activity.MianyiActivity.onCreate$lambda-5$lambda-4(a, a0);
    }
    
    public static void lambda$cemRJScfal3scSsUokJK6e7gKcI(com.wakeup.wearfit2.kotlin.activity.MianyiActivity a, android.view.View a0) {
        com.wakeup.wearfit2.kotlin.activity.MianyiActivity.onCreate$lambda-2(a, a0);
    }
    
    public static void lambda$gjpEP0YOn60mWIM94ynFARfCn8A(com.wakeup.wearfit2.kotlin.activity.MianyiActivity a) {
        com.wakeup.wearfit2.kotlin.activity.MianyiActivity.onCreate$lambda-5$lambda-4$lambda-3(a);
    }
    
    public static void lambda$nWiVRSTz_BKGsLCNK_ObEQWrtyM(com.wakeup.wearfit2.kotlin.activity.MianyiActivity a, android.view.View a0) {
        com.wakeup.wearfit2.kotlin.activity.MianyiActivity.onCreate$lambda-0(a, a0);
    }
    
    final private static void onCreate$lambda-0(com.wakeup.wearfit2.kotlin.activity.MianyiActivity a, android.view.View a0) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a, "this$0");
        androidx.navigation.ActivityKt.findNavController((android.app.Activity)a, 2131297192).navigate(2131297160);
    }
    
    final private static void onCreate$lambda-1(com.wakeup.wearfit2.kotlin.activity.MianyiActivity a, android.view.View a0) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a, "this$0");
        androidx.navigation.ActivityKt.findNavController((android.app.Activity)a, 2131297192).navigate(2131297162);
    }
    
    final private static void onCreate$lambda-2(com.wakeup.wearfit2.kotlin.activity.MianyiActivity a, android.view.View a0) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a, "this$0");
        androidx.navigation.ActivityKt.findNavController((android.app.Activity)a, 2131297192).navigate(2131297161);
    }
    
    final private static void onCreate$lambda-5(com.wakeup.wearfit2.kotlin.activity.MianyiActivity a, com.wakeup.wearfit2.manager.CommandManager a0, android.view.View a1) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a, "this$0");
        if (com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) {
            if (a.measuring) {
                String s = a.getString(2131886668);
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue((Object)s, "getString(R.string.measuring)");
                a.showMessage(s);
            } else {
                a0.setOnceOrRealTimeMeasure(65, 1);
                android.widget.TextView a2 = a.measure;
                if (a2 != null) {
                    a2.setText((CharSequence)(Object)kotlin.jvm.internal.Intrinsics.stringPlus(a.getString(2131886668), (Object)"..."));
                }
                a.measuring = true;
                android.os.Handler a3 = a.mHandler;
                if (a3 != null) {
                    a3.postDelayed((Runnable)(Object)new com.wakeup.wearfit2.kotlin.activity.-$$Lambda$MianyiActivity$YSSXkcNvxMYoiRWz5d5HpObkBVQ(a0, a), a.PERIOD);
                }
            }
        } else {
            android.widget.Toast.makeText((android.content.Context)a, (CharSequence)(Object)a.getString(2131886401), 0).show();
        }
    }
    
    final private static void onCreate$lambda-5$lambda-4(com.wakeup.wearfit2.manager.CommandManager a, com.wakeup.wearfit2.kotlin.activity.MianyiActivity a0) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a0, "this$0");
        a.setOnceOrRealTimeMeasure(65, 0);
        a0.runOnUiThread((Runnable)(Object)new com.wakeup.wearfit2.kotlin.activity.-$$Lambda$MianyiActivity$gjpEP0YOn60mWIM94ynFARfCn8A(a0));
        a0.measuring = false;
    }
    
    final private static void onCreate$lambda-5$lambda-4$lambda-3(com.wakeup.wearfit2.kotlin.activity.MianyiActivity a) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter((Object)a, "this$0");
        android.widget.TextView a0 = a.measure;
        if (a0 != null) {
            a0.setText((CharSequence)(Object)a.getString(2131887168));
        }
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
        this.setContentView(2131493005);
        this.setStatusBarColor(2131099873);
        ((android.widget.RadioButton)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.mianyi_day)).setChecked(true);
        ((android.widget.RadioButton)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.mianyi_day)).setOnClickListener((android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.kotlin.activity.-$$Lambda$MianyiActivity$nWiVRSTz_BKGsLCNK_ObEQWrtyM(this));
        ((android.widget.RadioButton)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.mianyi_week)).setOnClickListener((android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.kotlin.activity.-$$Lambda$MianyiActivity$JHNdTv3SVnRfS4rok2oakEqrU8g(this));
        ((android.widget.RadioButton)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.mianyi_month)).setOnClickListener((android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.kotlin.activity.-$$Lambda$MianyiActivity$cemRJScfal3scSsUokJK6e7gKcI(this));
        ((com.wakeup.wearfit2.view.CommonTitleLayout)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.mianyi_title)).setTitle(this.getString(2131886677));
        this.measure = (android.widget.TextView)this.findViewById(2131297142);
        com.wakeup.wearfit2.manager.CommandManager a0 = com.wakeup.wearfit2.manager.CommandManager.getInstance((android.content.Context)this);
        this.mHandler = new android.os.Handler();
        android.widget.TextView a1 = this.measure;
        if (a1 != null) {
            a1.setOnClickListener((android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.kotlin.activity.-$$Lambda$MianyiActivity$RSLUWYs74h2E3ksmmufg2invAPY(this, a0));
        }
        com.wakeup.wearfit2.bean.MianyiBean a2 = (com.wakeup.wearfit2.bean.MianyiBean)org.litepal.LitePal.findLast(com.wakeup.wearfit2.bean.MianyiBean.class);
        Float a3 = (a2 != null) ? Float.valueOf(a2.getData()) : null;
        if (a3 != null) {
            android.widget.TextView a4 = (android.widget.TextView)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.recent_mianyi);
            if (a4 != null) {
                StringBuilder a5 = new StringBuilder();
                a5.append((int)a3.floatValue());
                a5.append((char)37);
                a4.setText((CharSequence)(Object)a5.toString());
            }
        }
        org.greenrobot.eventbus.EventBus.getDefault().register((Object)this);
        if (kotlin.jvm.internal.Intrinsics.areEqual((Object)"F22", (Object)com.wakeup.wearfit2.util.SPUtils.getString((android.content.Context)this, com.wakeup.wearfit2.util.SPUtils.DEVICE_NAME, ""))) {
            android.widget.TextView a6 = this.measure;
            if (a6 != null) {
                a6.setVisibility(8);
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
        if (((a0 != null) ? com.wakeup.wearfit2.kotlin.activity.MianyiActivity$WhenMappings.$EnumSwitchMapping$0[a0.ordinal()] : -1) == 1) {
            com.wakeup.wearfit2.bean.MianyiBean a1 = (com.wakeup.wearfit2.bean.MianyiBean)org.litepal.LitePal.findLast(com.wakeup.wearfit2.bean.MianyiBean.class);
            Float a2 = (a1 != null) ? Float.valueOf(a1.getData()) : null;
            if (a2 != null) {
                android.widget.TextView a3 = (android.widget.TextView)this._$_findCachedViewById(com.wakeup.wearfit2.R$id.recent_mianyi);
                if (a3 != null) {
                    StringBuilder a4 = new StringBuilder();
                    a4.append((int)a2.floatValue());
                    a4.append((char)37);
                    a3.setText((CharSequence)(Object)a4.toString());
                }
            }
        }
    }
}
