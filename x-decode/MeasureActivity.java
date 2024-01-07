package com.wakeup.wearfit2.ui.activity.about;

public class MeasureActivity extends com.wakeup.wearfit2.ui.BaseActivity {
    final private static int CLOSE_ONE_KEY_MEASURE = 2;
    private boolean isMeasuring;
    com.wakeup.wearfit2.ui.widge.CommonTopBar mCommonTopBar;
    public android.content.Context mContext;
    private com.wakeup.wearfit2.ui.activity.about.MeasureActivity$GetDBModelTask mGetDBModelTask;
    private android.os.Handler mHandler;
    private com.wakeup.wearfit2.manager.CommandManager mManager;
    private android.os.Vibrator mVibrator;
    private int measure_state;
    com.wakeup.wearfit2.ui.view.OneKeyView one_key_view;
    android.widget.LinearLayout rlBloodPressure;
    android.view.View root;
    android.widget.TextView tv_blood_oxygen;
    android.widget.TextView tv_blood_pressure;
    android.widget.TextView tv_heart_rate;
    android.widget.TextView tv_measure_time;
    android.widget.TextView tv_one_key_measure;
    android.widget.TextView tv_tired;
    
    public MeasureActivity() {
        this.measure_state = 0;
        this.mHandler = new com.wakeup.wearfit2.ui.activity.about.MeasureActivity$1(this);
    }
    
    static com.wakeup.wearfit2.manager.CommandManager access$000(com.wakeup.wearfit2.ui.activity.about.MeasureActivity a) {
        return a.mManager;
    }
    
    static int access$102(com.wakeup.wearfit2.ui.activity.about.MeasureActivity a, int i) {
        a.measure_state = i;
        return i;
    }
    
    static boolean access$202(com.wakeup.wearfit2.ui.activity.about.MeasureActivity a, boolean b) {
        a.isMeasuring = b;
        return b;
    }
    
    static android.os.Vibrator access$300(com.wakeup.wearfit2.ui.activity.about.MeasureActivity a) {
        return a.mVibrator;
    }
    
    static void access$400(com.wakeup.wearfit2.ui.activity.about.MeasureActivity a, com.wakeup.wearfit2.model.DBModel a0) {
        a.setUIShow(a0);
    }
    
    private void init() {
        this.mContext = this;
        this.mManager = com.wakeup.wearfit2.manager.CommandManager.getInstance((android.content.Context)this);
        this.mVibrator = (android.os.Vibrator)this.getSystemService("vibrator");
        this.iniTopBar();
        this.initDatas();
        this.initTF();
    }
    
    private void initDatas() {
        com.wakeup.wearfit2.ui.activity.about.MeasureActivity$GetDBModelTask a = new com.wakeup.wearfit2.ui.activity.about.MeasureActivity$GetDBModelTask(this);
        this.mGetDBModelTask = a;
        a.execute((Object[])new Void[0]);
    }
    
    private void initTF() {
        android.graphics.Typeface a = android.graphics.Typeface.createFromAsset(this.getAssets(), "fonts/number.ttf");
        this.tv_heart_rate.setTypeface(a);
        this.tv_blood_pressure.setTypeface(a);
        this.tv_blood_oxygen.setTypeface(a);
    }
    
    private void setUIShow(com.wakeup.wearfit2.model.DBModel a) {
        label2: if (a != null) {
            if (a.getHeartRate() != 0) {
                android.widget.TextView a0 = this.tv_heart_rate;
                StringBuilder a1 = new StringBuilder();
                a1.append(a.getHeartRate());
                a1.append("");
                a0.setText((CharSequence)(Object)a1.toString());
            } else {
                this.tv_heart_rate.setText((CharSequence)(Object)"--");
            }
            int i = a.getBloodPressure_high();
            label5: {
                label3: {
                    label4: {
                        if (i == 0) {
                            break label4;
                        }
                        if (a.getBloodPressure_low() != 0) {
                            break label3;
                        }
                    }
                    this.tv_blood_pressure.setText((CharSequence)(Object)"--/--");
                    break label5;
                }
                android.widget.TextView a2 = this.tv_blood_pressure;
                StringBuilder a3 = new StringBuilder();
                a3.append(a.getBloodPressure_high());
                a3.append("/");
                a3.append(a.getBloodPressure_low());
                a2.setText((CharSequence)(Object)a3.toString());
            }
            if (a.getBloodOxygen() != 0) {
                this.tv_blood_oxygen.setText((CharSequence)(Object)String.valueOf(a.getBloodOxygen()));
            } else {
                this.tv_blood_oxygen.setText((CharSequence)(Object)"--");
            }
            if (a.getTiredType() != 0) {
                int i0 = a.getTiredType();
                if (i0 != 1) {
                    if (i0 != 2) {
                        if (i0 != 3) {
                            this.tv_tired.setText((CharSequence)(Object)this.getString(2131886531));
                        } else {
                            this.tv_tired.setText((CharSequence)(Object)this.getString(2131886681));
                        }
                    } else {
                        this.tv_tired.setText((CharSequence)(Object)this.getString(2131886683));
                    }
                } else {
                    this.tv_tired.setText((CharSequence)(Object)this.getString(2131886736));
                }
            } else {
                this.tv_tired.setText((CharSequence)(Object)"--");
            }
            int i1 = a.getHeartRate();
            label0: {
                label1: {
                    if (i1 != 0) {
                        break label1;
                    }
                    if (a.getBloodPressure_high() != 0) {
                        break label1;
                    }
                    if (a.getBloodPressure_low() != 0) {
                        break label1;
                    }
                    if (a.getBloodOxygen() != 0) {
                        break label1;
                    }
                    if (a.getTiredType() == 0) {
                        break label0;
                    }
                }
                this.tv_measure_time.setText((CharSequence)(Object)com.wakeup.wearfit2.util.DateUtils.getDayHourMinutes(a.getTimeInMillis()));
                break label2;
            }
            this.tv_measure_time.setText((CharSequence)(Object)"--");
        } else {
            this.tv_heart_rate.setText((CharSequence)(Object)"--");
            this.tv_blood_pressure.setText((CharSequence)(Object)"--/--");
            this.tv_blood_oxygen.setText((CharSequence)(Object)"--");
            this.tv_tired.setText((CharSequence)(Object)"--");
            this.tv_measure_time.setText((CharSequence)(Object)"--");
        }
    }
    
    public void iniTopBar() {
        this.mCommonTopBar.setBackground(this.getResources().getColor(2131100001));
        this.mCommonTopBar.setTitle(this.getResources().getString(2131886764));
        this.mCommonTopBar.setUpLeftImgOption(2131230830, (android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.ui.activity.about.MeasureActivity$3(this));
        this.mCommonTopBar.setUpRightImgOneOption(2131231430, (android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.ui.activity.about.MeasureActivity$4(this));
    }
    
    protected void onActivityResult(int i, int i0, android.content.Intent a) {
        ((com.wakeup.wearfit2.ui.BaseActivity)this).onActivityResult(i, i0, a);
    }
    
    public void onClick(android.view.View a) {
        switch(a.getId()) {
            case 2131297825: {
                if (!com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) {
                    android.widget.Toast.makeText((android.content.Context)this, (CharSequence)(Object)this.getString(2131886822), 0).show();
                    return;
                }
                this.mManager.setOnceKeyMeasure(1);
                this.tv_one_key_measure.setEnabled(false);
                this.one_key_view.startRotate();
                this.tv_one_key_measure.setText((CharSequence)(Object)this.getString(2131886668));
                this.isMeasuring = true;
                this.mHandler.sendEmptyMessageDelayed(2, 60000L);
                this.measure_state = 1;
                break;
            }
            case 2131297410: {
                if (this.isMeasuring) {
                    com.wakeup.wearfit2.util.ToastUtils.showSingleToast((android.content.Context)this, this.getString(2131886763));
                    break;
                } else {
                    com.wakeup.wearfit2.ui.activity.fatigue.FatigueActivity.startTargetActivity(this.mContext, com.wakeup.wearfit2.ui.activity.fatigue.FatigueActivity.class);
                    break;
                }
            }
            case 2131297386: {
                if (this.isMeasuring) {
                    com.wakeup.wearfit2.util.ToastUtils.showSingleToast((android.content.Context)this, this.getString(2131886763));
                    break;
                } else {
                    com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity.startTargetActivity(this.mContext, com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity.class);
                    break;
                }
            }
            case 2131297376: {
                if (this.isMeasuring) {
                    com.wakeup.wearfit2.util.ToastUtils.showSingleToast((android.content.Context)this, this.getString(2131886763));
                    break;
                } else {
                    com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity.startTargetActivity(this.mContext, com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity.class);
                    break;
                }
            }
            case 2131297375: {
                if (this.isMeasuring) {
                    com.wakeup.wearfit2.util.ToastUtils.showSingleToast((android.content.Context)this, this.getString(2131886763));
                    break;
                } else {
                    com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne.startTargetActivity(this.mContext, com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne.class);
                    break;
                }
            }
            case 2131297368: {
                com.wakeup.wearfit2.util.ShareUtils.share((android.content.Context)this, this.root);
                break;
            }
        }
    }
    
    protected void onCreate(android.os.Bundle a) {
        ((com.wakeup.wearfit2.ui.BaseActivity)this).onCreate(a);
        if (com.wakeup.wearfit2.AppApplication.ox10) {
            this.setContentView(2131493002);
        } else {
            this.setContentView(2131493001);
        }
        butterknife.ButterKnife.bind((android.app.Activity)this);
        org.greenrobot.eventbus.EventBus.getDefault().register((Object)this);
        this.init();
    }
    
    protected void onDestroy() {
        ((com.wakeup.wearfit2.ui.BaseActivity)this).onDestroy();
        this.mHandler.removeCallbacksAndMessages((Object)null);
        this.mGetDBModelTask.cancel(true);
        org.greenrobot.eventbus.EventBus.getDefault().unregister((Object)this);
    }
    
    public void onEventMainThread(com.wakeup.wearfit2.model.event.BaseEvent a) {
        int i = com.wakeup.wearfit2.ui.activity.about.MeasureActivity$5.$SwitchMap$com$wakeup$wearfit2$model$event$BaseEvent$EventType[a.getEventType().ordinal()];
        if (i == 1) {
            com.wakeup.wearfit2.model.DBModel a0 = (com.wakeup.wearfit2.model.DBModel)a.getmObject();
            label2: {
                label0: {
                    label1: {
                        if (a0 == null) {
                            break label1;
                        }
                        if (a0.getHeartRate() != 0) {
                            break label0;
                        }
                        if (a0.getBloodPressure_high() == 0) {
                            break label0;
                        }
                        if (a0.getBloodPressure_low() == 0) {
                            break label0;
                        }
                        if (a0.getBloodOxygen() == 0) {
                            break label0;
                        }
                        if (a0.getTiredType() == 0) {
                            break label0;
                        }
                    }
                    this.tv_one_key_measure.setText((CharSequence)(Object)this.getString(2131886653));
                    this.tv_measure_time.setText((CharSequence)(Object)"--");
                    break label2;
                }
                this.tv_one_key_measure.setText((CharSequence)(Object)this.getString(2131886663));
                this.tv_measure_time.setText((CharSequence)(Object)com.wakeup.wearfit2.util.DateUtils.getDayHourMinutes(a0.getTimeInMillis()));
            }
            this.setUIShow(a0);
        } else {
            if (i != 2) {
                return;
            }
            if (!com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected() && this.isMeasuring) {
                this.mHandler.sendEmptyMessage(2);
                this.tv_one_key_measure.setText((CharSequence)(Object)this.getString(2131886652));
            }
        }
    }
    
    public boolean onKeyDown(int i, android.view.KeyEvent a) {
        if (i != 4) {
            return ((com.wakeup.wearfit2.ui.BaseActivity)this).onKeyDown(i, a);
        }
        if (this.isMeasuring) {
            com.wakeup.wearfit2.util.ToastUtils.showSingleToast((android.content.Context)this, this.getResources().getString(2131886763));
        } else {
            this.finish();
        }
        return true;
    }
    
    protected void onPause() {
        ((com.wakeup.wearfit2.ui.BaseActivity)this).onPause();
    }
    
    protected void onResume() {
        ((com.wakeup.wearfit2.ui.BaseActivity)this).onResume();
        boolean b = this.getString(2131886663).equals((Object)this.tv_one_key_measure.getText().toString());
        label0: {
            label1: {
                if (b) {
                    break label1;
                }
                if (!this.getString(2131886652).equals((Object)this.tv_one_key_measure.getText().toString())) {
                    break label0;
                }
            }
            this.mHandler.postDelayed((Runnable)(Object)new com.wakeup.wearfit2.ui.activity.about.MeasureActivity$2(this), 300L);
        }
    }
    
    protected void onStop() {
        ((com.wakeup.wearfit2.ui.BaseActivity)this).onStop();
        this.mVibrator.cancel();
        if (this.measure_state == 1) {
            this.mManager.setOnceKeyMeasure(0);
            this.measure_state = 0;
        }
    }
}
