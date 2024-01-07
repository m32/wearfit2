package com.wakeup.wearfit2.ui.activity.bloodpressure;

public class BloodpressureAcitivity extends com.wakeup.wearfit2.ui.BaseActivity implements android.view.View$OnClickListener, com.wakeup.wearfit2.view.BPPinnedHeaderListView$OnLoadMoreListener {
    final private static int JUMP_ACTIVITY = 1;
    final private static int MEASURE_COMPLETED = 2;
    final private static String TAG = "BloodpressureAcitivity";
    private String address;
    com.wakeup.wearfit2.view.BPPinnedHeaderListView bloodPressListview;
    java.util.List bloodpressureBeanList;
    com.wakeup.wearfit2.ui.view.BloopressMeasureView bloopressMeasureView;
    com.wakeup.wearfit2.model.DBModel dbModel;
    private long exitTime;
    private com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity$GetDataTask getDataTask;
    boolean isCurrentView;
    android.view.View lineBlood;
    private int listSize;
    android.widget.ImageView mAnimationView;
    private java.util.List mBloodpressureBeens;
    com.wakeup.wearfit2.ui.widge.CommonTopBar mCommonTopBar;
    private android.os.Handler mHanlder;
    android.widget.LinearLayout mIsNoData;
    private android.os.Handler mLoadHandler;
    android.widget.RadioGroup mRadioGroup;
    Runnable mRunnable;
    android.widget.LinearLayout mShowMessageLinearlayout;
    com.wakeup.wearfit2.ui.view.xlistview.ScrollableLayout mSl;
    android.widget.TextView mTv;
    int measureTime;
    private int measure_state;
    android.widget.ImageView measure_static;
    android.widget.LinearLayout measureing_layout;
    private int page;
    android.widget.ProgressBar progressBarTime;
    android.widget.RadioButton radio_day;
    android.widget.RadioButton radio_month;
    android.widget.RadioButton radio_week;
    android.widget.RelativeLayout relativeLayout1;
    android.widget.RelativeLayout relativeLayout2;
    android.widget.RelativeLayout relativeLayout3;
    android.widget.RelativeLayout rlBloodpress;
    private java.text.SimpleDateFormat sdf;
    private java.util.Set set;
    java.util.List taskModels;
    com.wakeup.wearfit2.ui.view.CountdownTimerView timer;
    android.widget.TextView tv_measure_value;
    android.widget.TextView tv_once_measure;
    android.widget.TextView tv_real_time_close;
    android.widget.TextView tv_real_time_measure;
    
    static {
    }
    
    public BloodpressureAcitivity() {
        this.taskModels = null;
        this.sdf = new java.text.SimpleDateFormat("MM-dd HH:mm:ss");
        this.measureTime = 60;
        this.dbModel = null;
        this.measure_state = 0;
        this.exitTime = 0L;
        this.isCurrentView = true;
        this.page = 1;
        this.listSize = 50;
        this.mHanlder = new com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity$1(this);
        this.mRunnable = (Runnable)(Object)new com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity$7(this);
    }
    
    static android.os.Handler access$000(com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity a) {
        return a.mHanlder;
    }
    
    static int access$100(com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity a) {
        return a.measure_state;
    }
    
    static java.util.List access$300(com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity a) {
        return a.mBloodpressureBeens;
    }
    
    static java.util.List access$302(com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity a, java.util.List a0) {
        a.mBloodpressureBeens = a0;
        return a0;
    }
    
    static int access$400(com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity a) {
        return a.page;
    }
    
    static int access$408(com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity a) {
        int i = a.page;
        a.page = i + 1;
        return i;
    }
    
    static int access$500(com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity a) {
        return a.listSize;
    }
    
    static java.util.Set access$600(com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity a) {
        return a.set;
    }
    
    static String access$700(com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity a) {
        return a.address;
    }
    
    static String access$800() {
        return TAG;
    }
    
    private void initCountdownTimer() {
        this.timer.setTimeProgress(this.progressBarTime);
        this.timer.initTime((long)this.measureTime);
        this.timer.setOnTimeCompleteListener((com.wakeup.wearfit2.ui.view.CountdownTimerView$OnTimeCompleteListener)(Object)new com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity$2(this));
        this.timer.start();
    }
    
    private void initTopBar() {
        this.mCommonTopBar.setTitle(this.getResources().getString(2131886196));
        this.mCommonTopBar.setTitleColor(2131099984);
        this.mCommonTopBar.setBackgroundColor(this.getResources().getColor(2131099683));
        this.mCommonTopBar.setUpLeftImgOption(2131231266, (android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity$4(this));
        this.mCommonTopBar.setUpRightImgOneOption(2131231508, (android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity$5(this));
    }
    
    public void closeOnceMeasure() {
        com.wakeup.wearfit2.manager.CommandManager.getInstance((android.content.Context)this).setOnceOrRealTimeMeasure(33, 0);
        this.measure_state = 0;
        this.bloopressMeasureView.stop();
    }
    
    public void closeRealtimeMeasure() {
        com.wakeup.wearfit2.manager.CommandManager.getInstance((android.content.Context)this).setOnceOrRealTimeMeasure(34, 0);
        this.relativeLayout1.setVisibility(0);
        this.relativeLayout2.setVisibility(8);
        this.relativeLayout3.setVisibility(8);
        this.measure_state = 0;
        this.bloopressMeasureView.stop();
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
    
    public void initAdapter() {
        this.mSl.getHelper().setCurrentScrollableContainer((android.view.View)this.bloodPressListview);
        if (!com.wakeup.wearfit2.AppApplication.hasContinueHrD && !com.wakeup.wearfit2.AppApplication.hasContinueHrE) {
            this.bloodPressListview.setOnItemClickListener((android.widget.AdapterView$OnItemClickListener)(Object)new com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity$3(this));
        }
    }
    
    public void initData() {
        this.set = (java.util.Set)(Object)new java.util.HashSet();
        String s = com.wakeup.wearfit2.util.SPUtils.getString((android.content.Context)this, com.wakeup.wearfit2.util.SPUtils.DEVICE_ADDRESS_GET_DATA);
        this.address = s;
        if (!android.text.TextUtils.isEmpty((CharSequence)(Object)s)) {
            com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity$GetDataTask a = new com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity$GetDataTask(this, (com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity$1)null);
            this.getDataTask = a;
            a.execute((Object[])new Void[0]);
        }
        this.progressBarTime.setMax(this.measureTime);
        this.relativeLayout1.setVisibility(0);
        this.relativeLayout2.setVisibility(8);
        this.relativeLayout3.setVisibility(8);
        this.progressBarTime.setProgress(0);
        this.tv_measure_value.setText((CharSequence)(Object)"0/0");
        this.measure_static.setVisibility(0);
        this.measureing_layout.setVisibility(8);
        this.dbModel = null;
    }
    
    public void initView() {
        this.radio_day.setOnClickListener((android.view.View$OnClickListener)(Object)this);
        this.radio_week.setOnClickListener((android.view.View$OnClickListener)(Object)this);
        this.radio_month.setOnClickListener((android.view.View$OnClickListener)(Object)this);
        this.tv_once_measure.setOnClickListener((android.view.View$OnClickListener)(Object)this);
        this.tv_real_time_measure.setOnClickListener((android.view.View$OnClickListener)(Object)this);
        this.tv_real_time_close.setOnClickListener((android.view.View$OnClickListener)(Object)this);
        this.mSl.getHelper().setCurrentScrollableContainer((android.view.View)this.bloodPressListview);
    }
    
    public void jumpActivity(com.wakeup.wearfit2.bean.BloodpressureBean a) {
        if (a.getBloodPressure_high() != 0) {
            this.mHanlder.removeMessages(2);
            android.os.Message a0 = android.os.Message.obtain();
            a0.what = 1;
            a0.obj = a;
            this.mHanlder.sendMessageDelayed(a0, 1000L);
        }
    }
    
    protected void onActivityResult(int i, int i0, android.content.Intent a) {
        ((com.wakeup.wearfit2.ui.BaseActivity)this).onActivityResult(i, i0, a);
    }
    
    public void onClick(android.view.View a) {
        android.content.Intent a0 = new android.content.Intent((android.content.Context)this, com.wakeup.wearfit2.ui.fragment.bloodpressurefragment.BloodpressureFragmentActivity.class);
        android.os.Bundle a1 = new android.os.Bundle();
        if (a != this.radio_day) {
            if (a != this.radio_week) {
                if (a != this.radio_month) {
                    if (a != this.tv_once_measure) {
                        if (a != this.tv_real_time_measure) {
                            if (a == this.tv_real_time_close) {
                                this.closeRealtimeMeasure();
                                this.measure_static.setVisibility(0);
                                this.measureing_layout.setVisibility(8);
                            }
                        } else {
                            if (!com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) {
                                com.wakeup.wearfit2.util.ToastUtils.showSingleToast((android.content.Context)this, this.getResources().getString(2131886821));
                                return;
                            }
                            this.openRealtimeMeasure();
                        }
                    } else {
                        if (!com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) {
                            com.wakeup.wearfit2.util.ToastUtils.showSingleToast((android.content.Context)this, this.getResources().getString(2131886821));
                            return;
                        }
                        this.openOnceMeasure();
                    }
                } else {
                    a1.putInt("fragment", 2);
                    a0.putExtras(a1);
                    this.startActivity(a0);
                }
            } else {
                a1.putInt("fragment", 1);
                a0.putExtras(a1);
                this.startActivity(a0);
            }
        } else {
            a1.putInt("fragment", 0);
            a0.putExtras(a1);
            this.startActivity(a0);
        }
    }
    
    protected void onCreate(android.os.Bundle a) {
        ((com.wakeup.wearfit2.ui.BaseActivity)this).onCreate(a);
        this.page = 1;
        com.wakeup.wearfit2.ad.ADHolder.showInterstitial((android.app.Activity)this);
        this.setContentView(2131492906);
        butterknife.ButterKnife.bind((android.app.Activity)this);
        org.greenrobot.eventbus.EventBus.getDefault().register((Object)this);
        this.initTopBar();
        this.initAdapter();
        this.initView();
        this.initData();
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
            this.mRadioGroup.setVisibility(8);
            this.lineBlood.setVisibility(8);
        }
    }
    
    public void onDestroy() {
        ((com.wakeup.wearfit2.ui.BaseActivity)this).onDestroy();
        this.stateCloseMeasure();
        org.greenrobot.eventbus.EventBus.getDefault().unregister((Object)this);
        android.os.Handler a = this.mLoadHandler;
        if (a != null) {
            a.removeCallbacksAndMessages((Object)null);
        }
        com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity$GetDataTask a0 = this.getDataTask;
        if (a0 != null) {
            a0.cancel(true);
        }
    }
    
    public void onEventMainThread(com.wakeup.wearfit2.model.event.BaseEvent a) {
        int i = com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity$8.$SwitchMap$com$wakeup$wearfit2$model$event$BaseEvent$EventType[a.getEventType().ordinal()];
        if (i == 1) {
            com.wakeup.wearfit2.model.DBModel a0 = (com.wakeup.wearfit2.model.DBModel)a.getmObject();
            this.dbModel = a0;
            if (a0 == null) {
                com.wakeup.wearfit2.util.ToastUtils.showSafeToast((android.content.Context)this, this.getResources().getString(2131886652));
            } else {
                android.widget.TextView a1 = this.tv_measure_value;
                StringBuilder a2 = new StringBuilder();
                a2.append(this.dbModel.getBloodPressure_high());
                a2.append("/");
                a2.append(this.dbModel.getBloodPressure_low());
                a1.setText((CharSequence)(Object)a2.toString());
                com.wakeup.wearfit2.bean.BloodpressureBean a3 = new com.wakeup.wearfit2.bean.BloodpressureBean();
                a3.setTimeInMillis(this.dbModel.getTimeInMillis());
                a3.setBloodPressure_high(this.dbModel.getBloodPressure_high());
                a3.setBloodPressure_low(this.dbModel.getBloodPressure_low());
                a3.setType(this.dbModel.getType());
                this.jumpActivity(a3);
            }
        } else if (i == 2) {
            com.wakeup.wearfit2.model.DBModel a4 = (com.wakeup.wearfit2.model.DBModel)a.getmObject();
            this.dbModel = a4;
            if (a4 != null) {
                android.widget.TextView a5 = this.tv_measure_value;
                StringBuilder a6 = new StringBuilder();
                a6.append(this.dbModel.getBloodPressure_high());
                a6.append("/");
                a6.append(this.dbModel.getBloodPressure_low());
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
    
    public void onloadMore() {
        com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity$6 a = new com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity$6(this);
        this.mLoadHandler = a;
        ((android.os.Handler)a).sendEmptyMessageDelayed(0, 2000L);
    }
    
    public void openOnceMeasure() {
        this.dbModel = null;
        com.wakeup.wearfit2.manager.CommandManager.getInstance((android.content.Context)this).setOnceOrRealTimeMeasure(33, 1);
        this.relativeLayout1.setVisibility(8);
        this.relativeLayout2.setVisibility(0);
        this.relativeLayout3.setVisibility(8);
        this.initCountdownTimer();
        this.measure_state = 1;
        this.bloopressMeasureView.start();
        this.measure_static.setVisibility(8);
        this.measureing_layout.setVisibility(0);
        this.tv_measure_value.setText((CharSequence)(Object)"0/0");
    }
    
    public void openRealtimeMeasure() {
        this.dbModel = null;
        com.wakeup.wearfit2.manager.CommandManager.getInstance((android.content.Context)this).setOnceOrRealTimeMeasure(34, 1);
        this.relativeLayout1.setVisibility(8);
        this.relativeLayout2.setVisibility(8);
        this.relativeLayout3.setVisibility(0);
        this.measure_state = 2;
        this.bloopressMeasureView.start();
        this.measure_static.setVisibility(8);
        this.measureing_layout.setVisibility(0);
        this.tv_measure_value.setText((CharSequence)(Object)"0/0");
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
    }
}
