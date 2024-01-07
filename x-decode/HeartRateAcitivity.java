package com.wakeup.wearfit2.ui.activity.heartrate;

public class HeartRateAcitivity extends com.wakeup.wearfit2.ui.activity.splash.BaseADActivity implements android.view.View$OnClickListener, com.wakeup.wearfit2.view.HRPinnedHeaderListView$OnLoadMoreListener {
    final private static int JUMP_ACTIVITY = 1;
    final private static int MEASURE_COMPLETED = 2;
    final private static String TAG = "HeartRateAcitivity";
    private String address;
    android.view.animation.AnimationSet animationSet;
    com.wakeup.wearfit2.model.DBModel dbModel;
    private long exitTime;
    private com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity$GetHRDataTask getHRDataTask;
    private com.wakeup.wearfit2.bean.HeartRateBean heartRateBean;
    java.util.List heartRateBeanList;
    com.wakeup.wearfit2.ui.view.HeartRateMeasureCureView heartRateMeasureCureView;
    boolean isCurrentView;
    private int listSize;
    com.wakeup.wearfit2.view.HRPinnedHeaderListView lv;
    android.widget.ImageView mAnimationView;
    com.wakeup.wearfit2.ui.widge.CommonTopBar mCommonTopBar;
    private android.os.Handler mHanlder;
    private java.util.List mHeartRateBeanLists;
    android.widget.LinearLayout mIsNoData;
    private android.os.Handler mLoadHandler;
    private com.wakeup.wearfit2.manager.CommandManager mManager;
    android.widget.RadioButton mRadioDay;
    android.widget.RadioGroup mRadioGroup;
    android.widget.RadioButton mRadioMonth;
    android.widget.RadioButton mRadioWeek;
    Runnable mRunnable;
    com.wakeup.wearfit2.ui.view.xlistview.ScrollableLayout mSl;
    android.widget.TextView mTv;
    private int measure_state;
    android.widget.ImageView measure_static;
    android.widget.LinearLayout measureing_layout;
    private int page;
    android.widget.ProgressBar progressBarTime;
    android.widget.RelativeLayout rl_measure_btn;
    android.widget.RelativeLayout rl_measureing;
    android.widget.RelativeLayout rl_real_time_measure;
    private java.util.Set set;
    com.wakeup.wearfit2.ui.view.CountdownTimerView timer;
    android.widget.TextView tv_measure_value;
    android.widget.TextView tv_once_measure;
    android.widget.TextView tv_real_time_measure;
    
    static {
    }
    
    public HeartRateAcitivity() {
        this.dbModel = null;
        this.measure_state = 0;
        this.exitTime = 0L;
        this.isCurrentView = true;
        this.listSize = 50;
        this.page = 1;
        this.mHanlder = new com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity$1(this);
        this.mRunnable = (Runnable)(Object)new com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity$7(this);
    }
    
    static com.wakeup.wearfit2.bean.HeartRateBean access$000(com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity a) {
        return a.heartRateBean;
    }
    
    static int access$100(com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity a) {
        return a.measure_state;
    }
    
    static java.util.List access$300(com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity a) {
        return a.mHeartRateBeanLists;
    }
    
    static java.util.List access$302(com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity a, java.util.List a0) {
        a.mHeartRateBeanLists = a0;
        return a0;
    }
    
    static int access$400(com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity a) {
        return a.page;
    }
    
    static int access$408(com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity a) {
        int i = a.page;
        a.page = i + 1;
        return i;
    }
    
    static int access$500(com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity a) {
        return a.listSize;
    }
    
    static java.util.Set access$600(com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity a) {
        return a.set;
    }
    
    static String access$700(com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity a) {
        return a.address;
    }
    
    static String access$800() {
        return TAG;
    }
    
    private void init() {
        this.mManager = com.wakeup.wearfit2.manager.CommandManager.getInstance((android.content.Context)this);
        this.initData();
        this.initTopBar();
        this.initAdapter();
        this.initAnimation();
        if ("KOSPET MAGIC 2".equals((Object)com.wakeup.wearfit2.util.SPUtils.getString((android.content.Context)this, com.wakeup.wearfit2.util.SPUtils.DEVICE_NAME, ""))) {
            this.tv_real_time_measure.setVisibility(8);
        }
    }
    
    private void initCountdownTimer() {
        this.timer.setTimeProgress(this.progressBarTime);
        this.timer.initTime(40L);
        this.timer.setOnTimeCompleteListener((com.wakeup.wearfit2.ui.view.CountdownTimerView$OnTimeCompleteListener)(Object)new com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity$2(this));
        this.timer.start();
    }
    
    private void initTopBar() {
        this.mSl.getHelper().setCurrentScrollableContainer((android.view.View)this.lv);
        this.mCommonTopBar.setTitle(this.getString(2131886525));
        this.mCommonTopBar.setTitleColor(2131099984);
        this.mCommonTopBar.setBackgroundColor(this.getResources().getColor(2131099683));
        this.mCommonTopBar.setUpLeftImgOption(2131231266, (android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity$3(this));
        this.mCommonTopBar.setUpRightImgOneOption(2131231508, (android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity$4(this));
    }
    
    private void onceKeyMeasure() {
        this.dbModel = null;
        this.measure_state = 1;
        this.mManager.setOnceOrRealTimeMeasure(9, 1);
        this.rl_measure_btn.setVisibility(8);
        this.rl_measureing.setVisibility(0);
        this.initCountdownTimer();
        this.heartRateMeasureCureView.start();
        this.measure_static.setVisibility(8);
        this.measureing_layout.setVisibility(0);
        this.tv_measure_value.setText((CharSequence)(Object)"0");
    }
    
    private void realTimeMeasure() {
        this.dbModel = null;
        this.measure_state = 2;
        this.mManager.setOnceOrRealTimeMeasure(10, 1);
        this.rl_measure_btn.setVisibility(8);
        this.rl_real_time_measure.setVisibility(0);
        this.heartRateMeasureCureView.start();
        this.measure_static.setVisibility(8);
        this.measureing_layout.setVisibility(0);
        this.tv_measure_value.setText((CharSequence)(Object)"0");
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
        this.mSl.getHelper().setCurrentScrollableContainer((android.view.View)this.lv);
        this.lv.setOnItemClickListener((android.widget.AdapterView$OnItemClickListener)(Object)new com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity$5(this));
    }
    
    public void initAnimation() {
        this.animationSet = new android.view.animation.AnimationSet(true);
        android.view.animation.ScaleAnimation a = new android.view.animation.ScaleAnimation(1f, 0.65f, 1f, 0.65f, 1, 0.5f, 1, 0.5f);
        a.setDuration(800L);
        a.setRepeatCount(-1);
        android.view.animation.ScaleAnimation a0 = new android.view.animation.ScaleAnimation(1.1f, 0.85f, 1.1f, 0.85f, 1, 0.5f, 1, 0.5f);
        a0.setDuration(1100L);
        a0.setRepeatCount(-1);
        this.animationSet.addAnimation((android.view.animation.Animation)a);
        this.animationSet.addAnimation((android.view.animation.Animation)a0);
    }
    
    public void initData() {
        this.set = (java.util.Set)(Object)new java.util.HashSet();
        String s = com.wakeup.wearfit2.util.SPUtils.getString((android.content.Context)this, com.wakeup.wearfit2.util.SPUtils.DEVICE_ADDRESS_GET_DATA);
        this.address = s;
        if (!android.text.TextUtils.isEmpty((CharSequence)(Object)s)) {
            com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity$GetHRDataTask a = new com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity$GetHRDataTask(this, (com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity$1)null);
            this.getHRDataTask = a;
            a.execute((Object[])new Void[0]);
        }
        this.rl_real_time_measure.setVisibility(8);
        this.rl_measure_btn.setVisibility(0);
        this.rl_measureing.setVisibility(8);
        this.tv_measure_value.setText((CharSequence)(Object)"0");
        this.measure_static.setVisibility(0);
        this.measureing_layout.setVisibility(8);
        this.dbModel = null;
    }
    
    public void jumpActivity(com.wakeup.wearfit2.bean.HeartRateBean a) {
        if (a.getHeartRate() != 0) {
            this.mHanlder.removeMessages(2);
            android.os.Message a0 = android.os.Message.obtain();
            a0.what = 1;
            a0.obj = a;
            this.mHanlder.sendMessageDelayed(a0, 1000L);
        }
    }
    
    protected void onActivityResult(int i, int i0, android.content.Intent a) {
        ((com.wakeup.wearfit2.ui.activity.splash.BaseADActivity)this).onActivityResult(i, i0, a);
    }
    
    public void onClick(android.view.View a) {
        android.content.Intent a0 = new android.content.Intent((android.content.Context)this, com.wakeup.wearfit2.ui.fragment.heartratefragment.HeartRateFragmentActivity.class);
        android.os.Bundle a1 = new android.os.Bundle();
        switch(a.getId()) {
            case 2131297835: {
                if (!com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) {
                    com.wakeup.wearfit2.util.ToastUtils.showSingleToast((android.content.Context)this, this.getString(2131886821));
                    return;
                }
                this.realTimeMeasure();
                break;
            }
            case 2131297834: {
                this.stateCloseMeasure();
                this.rl_real_time_measure.setVisibility(8);
                this.rl_measure_btn.setVisibility(0);
                this.measure_static.setVisibility(0);
                this.measureing_layout.setVisibility(8);
                break;
            }
            case 2131297824: {
                if (!com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) {
                    com.wakeup.wearfit2.util.ToastUtils.showSingleToast((android.content.Context)this, this.getString(2131886821));
                    return;
                }
                this.onceKeyMeasure();
                break;
            }
            case 2131297337: {
                a1.putInt("fragment", 1);
                a0.putExtras(a1);
                this.startActivity(a0);
                break;
            }
            case 2131297336: {
                a1.putInt("fragment", 2);
                a0.putExtras(a1);
                this.startActivity(a0);
                break;
            }
            case 2131297334: {
                a1.putInt("fragment", 0);
                a0.putExtras(a1);
                this.startActivity(a0);
                break;
            }
        }
    }
    
    protected void onCreate(android.os.Bundle a) {
        this.page = 1;
        ((com.wakeup.wearfit2.ui.activity.splash.BaseADActivity)this).onCreate(a);
        this.setContentView(2131492987);
        butterknife.ButterKnife.bind((android.app.Activity)this);
        org.greenrobot.eventbus.EventBus.getDefault().register((Object)this);
        this.init();
        com.wakeup.wearfit2.ad.ADHolder.showInterstitial((android.app.Activity)this);
    }
    
    public void onDestroy() {
        ((com.wakeup.wearfit2.ui.activity.splash.BaseADActivity)this).onDestroy();
        android.util.Log.e(TAG, "onDestroy: ");
        org.greenrobot.eventbus.EventBus.getDefault().unregister((Object)this);
        this.stateCloseMeasure();
        android.os.Handler a = this.mLoadHandler;
        if (a != null) {
            a.removeCallbacksAndMessages((Object)null);
        }
        com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity$GetHRDataTask a0 = this.getHRDataTask;
        if (a0 != null) {
            a0.cancel(true);
        }
        this.mHanlder.removeMessages(2);
    }
    
    public void onEventMainThread(com.wakeup.wearfit2.model.event.BaseEvent a) {
        int i = com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity$8.$SwitchMap$com$wakeup$wearfit2$model$event$BaseEvent$EventType[a.getEventType().ordinal()];
        if (i == 1) {
            com.wakeup.wearfit2.model.DBModel a0 = (com.wakeup.wearfit2.model.DBModel)a.getmObject();
            this.dbModel = a0;
            if (a0 == null) {
                com.wakeup.wearfit2.util.ToastUtils.showSafeToast((android.content.Context)this, this.getResources().getString(2131886652));
            } else {
                long j = a0.getTimeInMillis();
                android.util.Log.i(TAG, com.wakeup.wearfit2.util.DateUtils.formatTime(j, "yyyy-MM-dd HH:mm:ss"));
                android.widget.TextView a1 = this.tv_measure_value;
                StringBuilder a2 = new StringBuilder();
                a2.append(this.dbModel.getHeartRate());
                a2.append("");
                a1.setText((CharSequence)(Object)a2.toString());
                com.wakeup.wearfit2.bean.HeartRateBean a3 = new com.wakeup.wearfit2.bean.HeartRateBean();
                this.heartRateBean = a3;
                a3.setTimeInMillis(j);
                this.heartRateBean.setHeartRate(this.dbModel.getHeartRate());
                this.heartRateBean.setType(this.dbModel.getType());
                this.heartRateBean.setDate(com.wakeup.wearfit2.util.DateUtils.getYearDateFromLong(j));
                com.wakeup.wearfit2.model.DBModel a4 = this.dbModel;
                label0: {
                    label1: {
                        if (a4 == null) {
                            break label1;
                        }
                        if (a4.getHeartRate() != 0) {
                            break label0;
                        }
                    }
                    com.wakeup.wearfit2.util.ToastUtils.showSingleToast((android.content.Context)this, this.getString(2131886652));
                    this.initData();
                    this.tv_measure_value.setText((CharSequence)(Object)"0");
                }
                this.mHanlder.sendEmptyMessageDelayed(2, 1000L);
            }
        } else if (i == 2) {
            com.wakeup.wearfit2.model.DBModel a5 = (com.wakeup.wearfit2.model.DBModel)a.getmObject();
            this.dbModel = a5;
            if (a5 != null) {
                this.tv_measure_value.setText((CharSequence)(Object)String.valueOf(a5.getHeartRate()));
            }
        }
    }
    
    public boolean onKeyDown(int i, android.view.KeyEvent a) {
        if (i == 4) {
            this.exit();
            return false;
        }
        return ((com.wakeup.wearfit2.ui.activity.splash.BaseADActivity)this).onKeyDown(i, a);
    }
    
    protected void onPause() {
        ((com.wakeup.wearfit2.ui.activity.splash.BaseADActivity)this).onPause();
    }
    
    protected void onResume() {
        ((com.wakeup.wearfit2.ui.activity.splash.BaseADActivity)this).onResume();
        this.isCurrentView = true;
    }
    
    protected void onStop() {
        ((com.wakeup.wearfit2.ui.activity.splash.BaseADActivity)this).onStop();
        this.isCurrentView = false;
    }
    
    public void onloadMore() {
        com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity$6 a = new com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity$6(this);
        this.mLoadHandler = a;
        ((android.os.Handler)a).sendEmptyMessageDelayed(0, 2000L);
    }
    
    public void stateCloseMeasure() {
        int i = this.measure_state;
        if (i != 1) {
            if (i == 2) {
                this.mManager.setOnceOrRealTimeMeasure(10, 0);
            }
        } else {
            this.mManager.setOnceOrRealTimeMeasure(9, 0);
        }
        this.measure_state = 0;
        this.heartRateMeasureCureView.stop();
    }
}
