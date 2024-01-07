package com.wakeup.wearfit2.ui.activity.oxygen;

public class OxygenActivityOne extends com.wakeup.wearfit2.ui.BaseActivity implements com.wakeup.wearfit2.view.OXPinnedHeaderListView$OnLoadMoreListener {
    final private static int DAY = 0;
    final public static String FLAG = "flag";
    final private static int JUMP_ACTIVITY = 1;
    final private static int MEASURE_COMPLETED = 2;
    final private static int MONTH = 2;
    final private static String TAG = "OxygenActivityOne";
    final private static int WEEK = 1;
    private String address;
    com.wakeup.wearfit2.ui.widge.CommonTopBar commonTopbar;
    private android.content.Context context;
    com.wakeup.wearfit2.model.DBModel dbModel;
    private long exitTime;
    android.os.Handler handlerCountDown;
    android.os.Handler handlerMeasure;
    boolean isCurrentView;
    android.view.View lineOxygen;
    private int listSize;
    android.widget.RelativeLayout ll_oxygen1;
    android.os.Handler loadHandler;
    android.widget.ImageView mAnimationView;
    private android.os.Handler mHanlder;
    android.widget.LinearLayout mIsNoData;
    com.wakeup.wearfit2.ui.view.xlistview.ScrollableLayout mOs;
    com.wakeup.wearfit2.ui.view.SweepGradientCircleProgressBar mOxygenTopView;
    android.widget.RelativeLayout mShowEcgLineRelative;
    android.widget.TextView mTv;
    android.widget.TextView mTvRealTimeClose;
    android.widget.LinearLayout mValueLayout;
    int measureTime;
    private int measure_state;
    com.wakeup.wearfit2.adapter.OxygenAdapter oxygenAdapter;
    private java.util.List oxygenBeans;
    private java.util.List oxygenBeanss;
    com.wakeup.wearfit2.view.OXPinnedHeaderListView oxygenListview;
    private int page;
    android.widget.RadioButton radioDay;
    android.widget.RadioGroup radioGroup;
    android.widget.RadioButton radioMonth;
    android.widget.RadioButton radioWeek;
    java.util.Random rand;
    private com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$ReadDbTask readDbTask;
    android.widget.RelativeLayout relativeLayout1;
    android.widget.RelativeLayout relativeLayout3;
    Runnable runnableCountDown;
    Runnable runnableMeasure;
    private java.util.Set set;
    java.util.List taskModels;
    android.widget.TextView tvOnceMeasure;
    android.widget.TextView tvRealTimeMeasure;
    android.widget.TextView tv_remaining_time;
    android.widget.TextView tv_value;
    
    static {
    }
    
    public OxygenActivityOne() {
        this.taskModels = null;
        this.listSize = 50;
        this.page = 1;
        this.measureTime = 40;
        this.dbModel = null;
        this.measure_state = 0;
        this.rand = new java.util.Random();
        this.exitTime = 0L;
        this.isCurrentView = true;
        this.mHanlder = new com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$1(this);
    }
    
    static void access$000(com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne a) {
        a.initData();
    }
    
    static int access$100(com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne a) {
        return a.measure_state;
    }
    
    static java.util.List access$1000(com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne a) {
        return a.oxygenBeans;
    }
    
    static java.util.List access$1002(com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne a, java.util.List a0) {
        a.oxygenBeans = a0;
        return a0;
    }
    
    static java.util.List access$200(com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne a) {
        return a.oxygenBeanss;
    }
    
    static android.content.Context access$300(com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne a) {
        return a.context;
    }
    
    static android.os.Handler access$500(com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne a) {
        return a.mHanlder;
    }
    
    static int access$600(com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne a) {
        return a.page;
    }
    
    static int access$608(com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne a) {
        int i = a.page;
        a.page = i + 1;
        return i;
    }
    
    static int access$700(com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne a) {
        return a.listSize;
    }
    
    static java.util.Set access$800(com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne a) {
        return a.set;
    }
    
    static String access$900(com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne a) {
        return a.address;
    }
    
    private void initData() {
        this.set = (java.util.Set)(Object)new java.util.HashSet();
        this.address = com.wakeup.wearfit2.util.SPUtils.getString((android.content.Context)this, com.wakeup.wearfit2.util.SPUtils.DEVICE_ADDRESS_GET_DATA);
        com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$ReadDbTask a = new com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$ReadDbTask(this, (com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$1)null);
        this.readDbTask = a;
        Long[] a0 = new Long[1];
        a0[0] = Long.valueOf(System.currentTimeMillis());
        a.execute((Object[])a0);
        this.relativeLayout1.setVisibility(0);
        this.relativeLayout3.setVisibility(8);
        this.tv_remaining_time.setVisibility(8);
        this.dbModel = null;
    }
    
    private void initTopBar() {
        this.commonTopbar.setTitle(this.getResources().getString(2131886770));
        this.commonTopbar.setTitleColor(2131099984);
        this.commonTopbar.setBackgroundColor(this.getResources().getColor(2131099683));
        this.commonTopbar.setUpLeftImgOption(2131231266, (android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$2(this));
        this.commonTopbar.setUpRightImgOneOption(2131231508, (android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$3(this));
    }
    
    private void initView() {
        this.oxygenBeanss = (java.util.List)(Object)new java.util.ArrayList();
        com.wakeup.wearfit2.ui.view.SweepGradientCircleProgressBar a = this.mOxygenTopView;
        int[] a0 = new int[7];
        a0[0] = android.graphics.Color.parseColor("#FFCBB2");
        a0[1] = android.graphics.Color.parseColor("#FFB3A2");
        a0[2] = android.graphics.Color.parseColor("#F0776E");
        a0[3] = android.graphics.Color.parseColor("#F57A63");
        a0[4] = android.graphics.Color.parseColor("#F0776E");
        a0[5] = android.graphics.Color.parseColor("#FFB3A2");
        a0[6] = android.graphics.Color.parseColor("#FFCBB2");
        a.setArcColors(a0);
        this.mOxygenTopView.setMax(this.measureTime);
        this.tvOnceMeasure.setOnClickListener((android.view.View$OnClickListener)(Object)this);
        this.tvRealTimeMeasure.setOnClickListener((android.view.View$OnClickListener)(Object)this);
        this.mTvRealTimeClose.setOnClickListener((android.view.View$OnClickListener)(Object)this);
        if (!com.wakeup.wearfit2.AppApplication.hasContinueHrD && !com.wakeup.wearfit2.AppApplication.hasContinueHrE) {
            this.oxygenListview.setOnItemClickListener((android.widget.AdapterView$OnItemClickListener)(Object)new com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$4(this));
        }
    }
    
    public void closeOnceMeasure() {
        com.wakeup.wearfit2.manager.CommandManager.getInstance((android.content.Context)this).setOnceOrRealTimeMeasure(17, 0);
        this.offTimer();
        this.mOxygenTopView.setProgress(0.0f);
        this.tv_remaining_time.setVisibility(8);
        this.measure_state = 0;
    }
    
    public void closeRealtimeMeasure() {
        com.wakeup.wearfit2.manager.CommandManager.getInstance((android.content.Context)this).setOnceOrRealTimeMeasure(18, 0);
        this.relativeLayout1.setVisibility(0);
        this.relativeLayout3.setVisibility(8);
        this.mOxygenTopView.setProgress(0.0f);
        this.offTimer();
        this.measure_state = 0;
    }
    
    public void exit() {
        long j = System.currentTimeMillis() - this.exitTime;
        int i = (j < 2000L) ? -1 : (j == 2000L) ? 0 : 1;
        label2: {
            label0: {
                label1: {
                    if (i <= 0) {
                        break label1;
                    }
                    if (this.measure_state != 0) {
                        break label0;
                    }
                }
                this.finish();
                break label2;
            }
            android.widget.Toast.makeText((android.content.Context)this, 2131886666, 0).show();
            this.exitTime = System.currentTimeMillis();
        }
    }
    
    public void jumpActivity(com.wakeup.wearfit2.bean.OxygenBean a) {
        if (a.getBloodOxygen() != 0) {
            this.mHanlder.removeMessages(2);
            android.os.Message a0 = android.os.Message.obtain();
            a0.what = 1;
            a0.obj = a;
            this.mHanlder.sendMessageDelayed(a0, 1000L);
        }
    }
    
    public void offTimer() {
        android.os.Handler a = this.handlerMeasure;
        if (a != null) {
            a.removeCallbacks(this.runnableMeasure);
            this.handlerMeasure.removeCallbacksAndMessages((Object)null);
            this.runnableMeasure = null;
            this.handlerMeasure = null;
        }
        android.os.Handler a0 = this.handlerCountDown;
        if (a0 != null) {
            a0.removeCallbacks(this.runnableCountDown);
            this.handlerCountDown.removeCallbacksAndMessages((Object)null);
            this.runnableCountDown = null;
            this.handlerCountDown = null;
        }
    }
    
    public void onClick(android.view.View a) {
        if (a != this.radioDay) {
            if (a != this.radioWeek) {
                if (a != this.radioMonth) {
                    if (a != this.tvOnceMeasure) {
                        if (a != this.tvRealTimeMeasure) {
                            if (a == this.mTvRealTimeClose) {
                                this.closeRealtimeMeasure();
                                this.measure_state = 0;
                            }
                        } else {
                            if (!com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) {
                                com.wakeup.wearfit2.util.ToastUtils.showSingleToast((android.content.Context)this, this.getResources().getString(2131886821));
                                return;
                            }
                            this.openRealtimeMeasure();
                            this.realtimeMeasureCountDown();
                        }
                    } else {
                        if (!com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) {
                            com.wakeup.wearfit2.util.ToastUtils.showSingleToast((android.content.Context)this, this.getResources().getString(2131886821));
                            return;
                        }
                        this.openOnceMeasure();
                        this.onceMeasureCountDown();
                    }
                } else {
                    android.content.Intent a0 = new android.content.Intent((android.content.Context)this, com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivity.class);
                    a0.putExtra("flag", 2);
                    this.startActivity(a0);
                }
            } else {
                android.content.Intent a1 = new android.content.Intent((android.content.Context)this, com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivity.class);
                a1.putExtra("flag", 1);
                this.startActivity(a1);
            }
        } else {
            android.content.Intent a2 = new android.content.Intent((android.content.Context)this, com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivity.class);
            a2.putExtra("flag", 0);
            this.startActivity(a2);
        }
    }
    
    protected void onCreate(android.os.Bundle a) {
        ((com.wakeup.wearfit2.ui.BaseActivity)this).onCreate(a);
        com.wakeup.wearfit2.ad.ADHolder.showInterstitial((android.app.Activity)this);
        this.setContentView(2131493016);
        butterknife.ButterKnife.bind((android.app.Activity)this);
        this.context = this;
        org.greenrobot.eventbus.EventBus.getDefault().register((Object)this);
        this.initTopBar();
        this.initView();
        this.initData();
        this.page = 1;
        boolean b = com.wakeup.wearfit2.AppApplication.hasContinueHrD;
        label0: {
            label1: {
                if (b) {
                    break label1;
                }
                if (!com.wakeup.wearfit2.AppApplication.hasContinueHrE) {
                    break label0;
                }
            }
            this.radioGroup.setVisibility(8);
            this.lineOxygen.setVisibility(8);
        }
    }
    
    public void onDestroy() {
        ((com.wakeup.wearfit2.ui.BaseActivity)this).onDestroy();
        this.mHanlder.removeCallbacksAndMessages((Object)null);
        this.offTimer();
        this.stateCloseMeasure();
        org.greenrobot.eventbus.EventBus.getDefault().unregister((Object)this);
        android.os.Handler a = this.loadHandler;
        if (a != null) {
            a.removeCallbacksAndMessages((Object)null);
        }
        com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$ReadDbTask a0 = this.readDbTask;
        if (a0 != null) {
            a0.cancel(true);
        }
    }
    
    public void onEventMainThread(com.wakeup.wearfit2.model.event.BaseEvent a) {
        int i = com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$10.$SwitchMap$com$wakeup$wearfit2$model$event$BaseEvent$EventType[a.getEventType().ordinal()];
        if (i == 1) {
            com.wakeup.wearfit2.model.DBModel a0 = (com.wakeup.wearfit2.model.DBModel)a.getmObject();
            this.dbModel = a0;
            if (a0 == null) {
                com.wakeup.wearfit2.util.ToastUtils.showSafeToast((android.content.Context)this, this.getResources().getString(2131886652));
            } else {
                com.wakeup.wearfit2.bean.OxygenBean a1 = new com.wakeup.wearfit2.bean.OxygenBean();
                a1.setTimeInMillis(this.dbModel.getTimeInMillis());
                a1.setBloodOxygen(this.dbModel.getBloodOxygen());
                a1.setType(this.dbModel.getType());
                android.widget.TextView a2 = this.tv_value;
                StringBuilder a3 = new StringBuilder();
                a3.append(this.dbModel.getBloodOxygen());
                a3.append("");
                a2.setText((CharSequence)(Object)a3.toString());
                this.jumpActivity(a1);
            }
        } else if (i == 2) {
            com.wakeup.wearfit2.model.DBModel a4 = (com.wakeup.wearfit2.model.DBModel)a.getmObject();
            this.dbModel = a4;
            if (a4 != null) {
                android.widget.TextView a5 = this.tv_value;
                StringBuilder a6 = new StringBuilder();
                a6.append(this.dbModel.getBloodOxygen());
                a6.append("");
                a5.setText((CharSequence)(Object)a6.toString());
            }
        }
    }
    
    public boolean onKeyDown(int i, android.view.KeyEvent a) {
        if (i == 4) {
            this.exit();
            return false;
        }
        return ((com.wakeup.wearfit2.ui.BaseActivity)this).onKeyDown(i, a);
    }
    
    protected void onResume() {
        ((com.wakeup.wearfit2.ui.BaseActivity)this).onResume();
        this.isCurrentView = true;
    }
    
    protected void onStop() {
        ((com.wakeup.wearfit2.ui.BaseActivity)this).onStop();
        this.isCurrentView = false;
    }
    
    public void onceMeasureCountDown() {
        this.tv_remaining_time.setVisibility(0);
        this.offTimer();
        this.handlerCountDown = new com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$7(this);
        com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$8 a = new com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$8(this);
        this.runnableCountDown = (Runnable)(Object)a;
        this.handlerCountDown.postDelayed((Runnable)(Object)a, 0L);
    }
    
    public void onloadMore() {
        com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$9 a = new com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$9(this);
        this.loadHandler = a;
        ((android.os.Handler)a).sendEmptyMessageDelayed(0, 2000L);
    }
    
    public void openOnceMeasure() {
        this.dbModel = null;
        this.offTimer();
        com.wakeup.wearfit2.manager.CommandManager.getInstance((android.content.Context)this).setOnceOrRealTimeMeasure(17, 1);
        this.relativeLayout1.setVisibility(8);
        this.relativeLayout3.setVisibility(8);
        this.mOxygenTopView.setProgress(0.0f);
        this.measure_state = 1;
        this.tv_value.setText((CharSequence)(Object)"--");
    }
    
    public void openRealtimeMeasure() {
        this.dbModel = null;
        this.offTimer();
        com.wakeup.wearfit2.manager.CommandManager.getInstance((android.content.Context)this).setOnceOrRealTimeMeasure(18, 1);
        this.relativeLayout1.setVisibility(8);
        this.relativeLayout3.setVisibility(0);
        this.mOxygenTopView.setProgress(0.0f);
        this.measure_state = 2;
        this.tv_value.setText((CharSequence)(Object)"--");
    }
    
    public void realtimeMeasureCountDown() {
        this.offTimer();
        this.handlerCountDown = new com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$5(this);
        com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$6 a = new com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne$6(this);
        this.runnableCountDown = (Runnable)(Object)a;
        this.handlerCountDown.postDelayed((Runnable)(Object)a, 0L);
    }
    
    public void stateCloseMeasure() {
        int i = this.measure_state;
        if (i != 1) {
            if (i == 2) {
                this.closeRealtimeMeasure();
            }
        } else {
            this.closeOnceMeasure();
        }
        this.measure_state = 0;
    }
}
