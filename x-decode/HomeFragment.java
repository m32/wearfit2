package com.wakeup.wearfit2.ui.fragment;

public class HomeFragment extends androidx.fragment.app.Fragment implements android.widget.AdapterView$OnItemClickListener, com.wakeup.wearfit2.ui.view.DragGrid$DragGridListener, com.amap.api.location.AMapLocationListener {
    final private static int CANCE_UPDATE = 3;
    final private static int CLOSE_REFRESH_DATA = 7;
    final private static int GET_PHONE_DATA = 4;
    final private static int NO_CONNECTED = 1;
    final private static long REFRESH_PERIOD = 3600000L;
    final private static int SYNCING_DATA = 0;
    final private static int SYNC_ALL_DATA_HR = 8;
    final private static int SYNC_All_DATA = 5;
    final private static int SYNC_SLEEP_DATA = 6;
    final private static String TAG = "HomeFragment";
    final private static int UPDATE_ON_TIME_MEASURE = 2;
    public static int stepCount;
    private boolean OutofBounds;
    private String address;
    private com.wakeup.wearfit2.bean.HomeDBModle dbModel;
    android.os.Handler handler;
    private boolean hasGuahao;
    private boolean hasGuoNeiYiQing;
    private boolean hasGuoWaiYiQing;
    private boolean hasJiankangDaka;
    private boolean hasJiankangjisuanqi;
    private boolean hasJuankuan;
    private boolean hasLianXuTiwen;
    private boolean hasMianyi;
    private boolean hasTiwen;
    android.widget.TextView homeDrag;
    boolean isMove;
    private boolean isRefreshing;
    private double latitude;
    private com.wakeup.wearfit2.ui.fragment.HomeFragment$ChangeBottomColorListener listener;
    private android.location.LocationListener locationListener;
    private double longitude;
    private boolean mConnected;
    private android.content.Context mContext;
    android.widget.LinearLayout mFirstPageFragment;
    public com.amap.api.location.AMapLocationClientOption mLocationOption;
    private com.wakeup.wearfit2.manager.CommandManager mManager;
    private com.wakeup.wearfit2.ui.fragment.HomeFragment$OnListenPop mOnListenPop;
    private java.util.Timer mTimer;
    private android.os.Vibrator mVibrator;
    public com.amap.api.location.AMapLocationClient mlocationClient;
    com.wakeup.wearfit2.adapter.OtherAdapter otherAdapter;
    private com.wakeup.wearfit2.bean.ChannelItem otherChannel;
    java.util.List otherChannelList;
    com.wakeup.wearfit2.ui.view.OtherGridView otherGridView;
    private com.wakeup.wearfit2.kotlin.RetrofitService retrofitService;
    private com.wakeup.wearfit2.bean.ChannelItem slectedChannel;
    private com.wakeup.wearfit2.model.StepAndSleepModel stepAndSleepModel;
    com.wakeup.wearfit2.view.ScrollableSwipeRefreshLayout swip;
    androidx.swiperefreshlayout.widget.SwipeRefreshLayout$OnRefreshListener swipeRefreshListener;
    private java.util.Timer timer;
    com.wakeup.wearfit2.adapter.DragAdapter userAdapter;
    java.util.List userChannelList;
    com.wakeup.wearfit2.ui.view.DragGrid userGridView;
    
    static {
    }
    
    public HomeFragment() {
        this.hasTiwen = false;
        this.hasMianyi = false;
        this.hasLianXuTiwen = false;
        this.otherChannelList = (java.util.List)(Object)new java.util.ArrayList();
        this.userChannelList = (java.util.List)(Object)new java.util.ArrayList();
        this.isMove = false;
        this.mConnected = false;
        this.mLocationOption = null;
        this.hasGuoNeiYiQing = false;
        this.hasGuahao = false;
        this.hasJiankangDaka = false;
        this.hasJuankuan = false;
        this.hasJiankangjisuanqi = false;
        this.hasGuoWaiYiQing = false;
        this.handler = new com.wakeup.wearfit2.ui.fragment.HomeFragment$1(this);
        this.locationListener = (android.location.LocationListener)(Object)new com.wakeup.wearfit2.ui.fragment.HomeFragment$2(this);
        this.swipeRefreshListener = (androidx.swiperefreshlayout.widget.SwipeRefreshLayout$OnRefreshListener)(Object)new com.wakeup.wearfit2.ui.fragment.HomeFragment$4(this);
    }
    
    private void JumpToActivity(com.wakeup.wearfit2.bean.ChannelItem a) {
        switch(a.getType()) {
            case 16: {
                this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.kotlin.activity.NewTiwenActivity.class));
                break;
            }
            case 15: {
                this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.html5.HealthCountActivity.class));
                break;
            }
            case 14: {
                this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.html5.FundActivity.class));
                break;
            }
            case 13: {
                this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.html5.YiQingEnActivity.class));
                break;
            }
            case 12: {
                if (android.text.TextUtils.isEmpty((CharSequence)(Object)com.wakeup.wearfit2.util.SPUtils.getString(this.mContext, "token", ""))) {
                    if (com.wakeup.wearfit2.util.CommonUtils.is_zh_CN()) {
                        android.content.Intent a0 = new android.content.Intent((android.content.Context)this.getActivity(), com.wakeup.wearfit2.ui.activity.login.LoginActivity.class);
                        a0.setFlags(268468224);
                        this.startActivity(a0);
                        break;
                    } else {
                        android.content.Intent a1 = new android.content.Intent((android.content.Context)this.getActivity(), com.wakeup.wearfit2.ui.activity.login.EmailLoginActivity3.class);
                        a1.setFlags(268468224);
                        this.startActivity(a1);
                        break;
                    }
                } else {
                    this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.daka.DakaActivity.class));
                    break;
                }
            }
            case 11: {
                this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.html5.GuaHaoActivity.class));
                break;
            }
            case 10: {
                this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.html5.YiQingActivity.class));
                break;
            }
            case 9: {
                this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.kotlin.activity.MianyiActivity.class));
                break;
            }
            case 8: {
                this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.kotlin.activity.TiwenActivity.class));
                break;
            }
            case 7: {
                this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.StepAcitivity.class));
                break;
            }
            case 6: {
                if (com.wakeup.wearfit2.AppApplication.hasEcg) {
                    this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.ecg.EcgActivity2.class));
                    break;
                } else {
                    boolean b = com.wakeup.wearfit2.AppApplication.hasContinueHr;
                    label8: {
                        label9: {
                            if (b) {
                                break label9;
                            }
                            if (!com.wakeup.wearfit2.AppApplication.ox30) {
                                break label8;
                            }
                        }
                        android.util.Log.i(TAG, "1:");
                        this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.health.HealthWeeklyHrActivity.class));
                        break;
                    }
                    boolean b0 = com.wakeup.wearfit2.AppApplication.hasContinueHrD;
                    label6: {
                        label7: {
                            if (b0) {
                                break label7;
                            }
                            if (!com.wakeup.wearfit2.AppApplication.ox0a) {
                                break label6;
                            }
                        }
                        android.util.Log.i(TAG, "2:");
                        this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.health.HealthWeeklyHrDActivity.class));
                        break;
                    }
                    if (com.wakeup.wearfit2.AppApplication.hasContinueHrE) {
                        android.util.Log.i(TAG, "3:");
                        this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.health.HealthWeeklyHrEActivity.class));
                        break;
                    } else if (com.wakeup.wearfit2.AppApplication.hasContinueHrF) {
                        android.util.Log.i(TAG, "4:");
                        this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.health.HealthWeeklyHrFActivity.class));
                        break;
                    } else if (com.wakeup.wearfit2.AppApplication.ox10) {
                        this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.health.HealthWeekly10Activity.class));
                        break;
                    } else {
                        this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.health.HealthWeeklyActivity.class));
                        break;
                    }
                }
            }
            case 5: {
                this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.fatigue.FatigueActivity.class));
                break;
            }
            case 4: {
                boolean b1 = com.wakeup.wearfit2.AppApplication.hasContinueHr;
                label4: {
                    label5: {
                        if (b1) {
                            break label5;
                        }
                        if (com.wakeup.wearfit2.AppApplication.hasContinueHrD) {
                            break label5;
                        }
                        if (com.wakeup.wearfit2.AppApplication.hasContinueHrF) {
                            break label5;
                        }
                        if (!com.wakeup.wearfit2.AppApplication.ox30) {
                            break label4;
                        }
                    }
                    this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.device.DeviceManagerActivity.class));
                    break;
                }
                if (com.wakeup.wearfit2.AppApplication.ox0a) {
                    com.wakeup.wearfit2.run.MotionRecordActivity.open((android.content.Context)this.getActivity());
                    break;
                } else {
                    this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne.class));
                    break;
                }
            }
            case 3: {
                boolean b2 = com.wakeup.wearfit2.AppApplication.hasContinueHr;
                label3: {
                    if (b2) {
                        break label3;
                    }
                    boolean b3 = com.wakeup.wearfit2.AppApplication.ox30;
                    label2: {
                        if (!b3) {
                            break label2;
                        }
                        break label3;
                    }
                    if (com.wakeup.wearfit2.AppApplication.hasContinueHrF) {
                        this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.oxygen.OxygenActivityOne.class));
                        break;
                    } else if (com.wakeup.wearfit2.AppApplication.ox10) {
                        this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.device.DeviceManagerActivity.class));
                        break;
                    } else {
                        this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.bloodpressure.BloodpressureAcitivity.class));
                        break;
                    }
                }
                if (com.wakeup.wearfit2.util.SPUtils.getString(this.mContext, "token", "").equals((Object)"")) {
                    android.content.Context a2 = this.mContext;
                    com.wakeup.wearfit2.util.ToastUtils.showSafeToast(a2, a2.getResources().getString(2131886823));
                    break;
                } else {
                    com.wakeup.wearfit2.run.MotionRecordActivity.open((android.content.Context)this.getActivity());
                    break;
                }
            }
            case 2: {
                boolean b4 = com.wakeup.wearfit2.AppApplication.hasContinueHr;
                label0: {
                    label1: {
                        if (b4) {
                            break label1;
                        }
                        if (com.wakeup.wearfit2.AppApplication.hasContinueHrD) {
                            break label1;
                        }
                        if (com.wakeup.wearfit2.AppApplication.hasContinueHrE) {
                            break label1;
                        }
                        if (!com.wakeup.wearfit2.AppApplication.hasContinueHrF) {
                            break label0;
                        }
                    }
                    this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.heartrate.HeartRate2Acitivity.class));
                    break;
                }
                this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.heartrate.HeartRateAcitivity.class));
                break;
            }
            case 1: {
                this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.SleepActivity.class));
                break;
            }
        }
    }
    
    static android.content.Context access$000(com.wakeup.wearfit2.ui.fragment.HomeFragment a) {
        return a.mContext;
    }
    
    static android.os.Vibrator access$100(com.wakeup.wearfit2.ui.fragment.HomeFragment a) {
        return a.mVibrator;
    }
    
    static void access$1000(com.wakeup.wearfit2.ui.fragment.HomeFragment a) {
        a.refreshWeather();
    }
    
    static void access$1100(com.wakeup.wearfit2.ui.fragment.HomeFragment a) {
        a.refresh();
    }
    
    static boolean access$1202(com.wakeup.wearfit2.ui.fragment.HomeFragment a, boolean b) {
        a.isRefreshing = b;
        return b;
    }
    
    static com.wakeup.wearfit2.bean.HomeDBModle access$1400(com.wakeup.wearfit2.ui.fragment.HomeFragment a) {
        return a.dbModel;
    }
    
    static com.wakeup.wearfit2.bean.HomeDBModle access$1402(com.wakeup.wearfit2.ui.fragment.HomeFragment a, com.wakeup.wearfit2.bean.HomeDBModle a0) {
        a.dbModel = a0;
        return a0;
    }
    
    static void access$1500(com.wakeup.wearfit2.ui.fragment.HomeFragment a, com.wakeup.wearfit2.bean.HomeDBModle a0) {
        a.updateUI(a0);
    }
    
    static com.wakeup.wearfit2.ui.fragment.HomeFragment$OnListenPop access$1600(com.wakeup.wearfit2.ui.fragment.HomeFragment a) {
        return a.mOnListenPop;
    }
    
    static com.wakeup.wearfit2.kotlin.RetrofitService access$1700(com.wakeup.wearfit2.ui.fragment.HomeFragment a) {
        return a.retrofitService;
    }
    
    static com.wakeup.wearfit2.manager.CommandManager access$200(com.wakeup.wearfit2.ui.fragment.HomeFragment a) {
        return a.mManager;
    }
    
    static void access$300(com.wakeup.wearfit2.ui.fragment.HomeFragment a) {
        a.getDataAndUpdateUI();
    }
    
    static String access$400() {
        return TAG;
    }
    
    static String access$500(com.wakeup.wearfit2.ui.fragment.HomeFragment a) {
        return a.address;
    }
    
    static void access$600(com.wakeup.wearfit2.ui.fragment.HomeFragment a) {
        a.stopRefresh();
    }
    
    static double access$700(com.wakeup.wearfit2.ui.fragment.HomeFragment a) {
        return a.latitude;
    }
    
    static double access$702(com.wakeup.wearfit2.ui.fragment.HomeFragment a, double d) {
        a.latitude = d;
        return d;
    }
    
    static double access$800(com.wakeup.wearfit2.ui.fragment.HomeFragment a) {
        return a.longitude;
    }
    
    static double access$802(com.wakeup.wearfit2.ui.fragment.HomeFragment a, double d) {
        a.longitude = d;
        return d;
    }
    
    static void access$900(com.wakeup.wearfit2.ui.fragment.HomeFragment a) {
        a.requestSyncData();
    }
    
    private void controlFunction() {
        String[] a = new String[2];
        a[0] = "type = ?";
        a[1] = "8";
        java.util.List a0 = org.litepal.LitePal.where(a).find(com.wakeup.wearfit2.bean.ChannelItem.class);
        label16: if (this.hasTiwen) {
            label17: {
                if (a0 == null) {
                    break label17;
                }
                if (a0.size() > 0) {
                    break label16;
                }
            }
            com.wakeup.wearfit2.bean.ChannelItem a1 = new com.wakeup.wearfit2.bean.ChannelItem(6, 8, "\u4f53\u6e29", 1);
            String[] a2 = new String[2];
            a2[0] = "type=?";
            a2[1] = "8";
            a1.saveOrUpdate(a2);
        } else if (a0 != null && !a0.isEmpty()) {
            org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a0.get(0)).getId());
        }
        String[] a3 = new String[2];
        a3[0] = "type = ?";
        a3[1] = "9";
        java.util.List a4 = org.litepal.LitePal.where(a3).find(com.wakeup.wearfit2.bean.ChannelItem.class);
        label14: if (this.hasMianyi) {
            label15: {
                if (a4 == null) {
                    break label15;
                }
                if (a4.size() > 0) {
                    break label14;
                }
            }
            com.wakeup.wearfit2.bean.ChannelItem a5 = new com.wakeup.wearfit2.bean.ChannelItem(7, 9, "\u514d\u75ab\u503c", 1);
            String[] a6 = new String[2];
            a6[0] = "type=?";
            a6[1] = "9";
            a5.saveOrUpdate(a6);
        } else if (a4 != null && !a4.isEmpty()) {
            org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a4.get(0)).getId());
        }
        String[] a7 = new String[2];
        a7[0] = "type = ?";
        a7[1] = "10";
        java.util.List a8 = org.litepal.LitePal.where(a7).find(com.wakeup.wearfit2.bean.ChannelItem.class);
        label12: if (this.hasGuoNeiYiQing) {
            label13: {
                if (a8 == null) {
                    break label13;
                }
                if (a8.size() > 0) {
                    break label12;
                }
            }
            com.wakeup.wearfit2.bean.ChannelItem a9 = new com.wakeup.wearfit2.bean.ChannelItem(8, 10, "\u56fd\u5185\u75ab\u60c5", 1);
            String[] a10 = new String[2];
            a10[0] = "type=?";
            a10[1] = "10";
            a9.saveOrUpdate(a10);
        } else if (a8 != null && !a8.isEmpty()) {
            org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a8.get(0)).getId());
        }
        String[] a11 = new String[2];
        a11[0] = "type = ?";
        a11[1] = "11";
        java.util.List a12 = org.litepal.LitePal.where(a11).find(com.wakeup.wearfit2.bean.ChannelItem.class);
        label10: if (this.hasGuahao) {
            label11: {
                if (a12 == null) {
                    break label11;
                }
                if (a12.size() > 0) {
                    break label10;
                }
            }
            com.wakeup.wearfit2.bean.ChannelItem a13 = new com.wakeup.wearfit2.bean.ChannelItem(9, 11, "\u9884\u7ea6\u6302\u53f7", 1);
            String[] a14 = new String[2];
            a14[0] = "type=?";
            a14[1] = "11";
            a13.saveOrUpdate(a14);
        } else if (a12 != null && !a12.isEmpty()) {
            org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a12.get(0)).getId());
        }
        String[] a15 = new String[2];
        a15[0] = "type = ?";
        a15[1] = "12";
        java.util.List a16 = org.litepal.LitePal.where(a15).find(com.wakeup.wearfit2.bean.ChannelItem.class);
        label8: if (this.hasJiankangDaka) {
            label9: {
                if (a16 == null) {
                    break label9;
                }
                if (a16.size() > 0) {
                    break label8;
                }
            }
            com.wakeup.wearfit2.bean.ChannelItem a17 = new com.wakeup.wearfit2.bean.ChannelItem(10, 12, "\u5065\u5eb7\u6253\u5361", 1);
            String[] a18 = new String[2];
            a18[0] = "type=?";
            a18[1] = "12";
            a17.saveOrUpdate(a18);
        } else if (a16 != null && !a16.isEmpty()) {
            org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a16.get(0)).getId());
        }
        String[] a19 = new String[2];
        a19[0] = "type = ?";
        a19[1] = "13";
        java.util.List a20 = org.litepal.LitePal.where(a19).find(com.wakeup.wearfit2.bean.ChannelItem.class);
        label6: if (this.hasGuoWaiYiQing) {
            label7: {
                if (a20 == null) {
                    break label7;
                }
                if (a20.size() > 0) {
                    break label6;
                }
            }
            com.wakeup.wearfit2.bean.ChannelItem a21 = new com.wakeup.wearfit2.bean.ChannelItem(11, 13, "\u56fd\u5916\u75ab\u60c5", 1);
            String[] a22 = new String[2];
            a22[0] = "type=?";
            a22[1] = "13";
            a21.saveOrUpdate(a22);
        } else if (a20 != null && !a20.isEmpty()) {
            org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a20.get(0)).getId());
        }
        String[] a23 = new String[2];
        a23[0] = "type = ?";
        a23[1] = "14";
        java.util.List a24 = org.litepal.LitePal.where(a23).find(com.wakeup.wearfit2.bean.ChannelItem.class);
        label4: if (this.hasJuankuan) {
            label5: {
                if (a24 == null) {
                    break label5;
                }
                if (a24.size() > 0) {
                    break label4;
                }
            }
            com.wakeup.wearfit2.bean.ChannelItem a25 = new com.wakeup.wearfit2.bean.ChannelItem(12, 14, "\u6350\u6b3e", 1);
            String[] a26 = new String[2];
            a26[0] = "type=?";
            a26[1] = "14";
            a25.saveOrUpdate(a26);
        } else if (a24 != null && !a24.isEmpty()) {
            org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a24.get(0)).getId());
        }
        String[] a27 = new String[2];
        a27[0] = "type = ?";
        a27[1] = "15";
        java.util.List a28 = org.litepal.LitePal.where(a27).find(com.wakeup.wearfit2.bean.ChannelItem.class);
        label2: if (this.hasJiankangjisuanqi) {
            label3: {
                if (a28 == null) {
                    break label3;
                }
                if (a28.size() > 0) {
                    break label2;
                }
            }
            com.wakeup.wearfit2.bean.ChannelItem a29 = new com.wakeup.wearfit2.bean.ChannelItem(13, 15, "\u5065\u5eb7\u8ba1\u7b97\u5668", 1);
            String[] a30 = new String[2];
            a30[0] = "type=?";
            a30[1] = "15";
            a29.saveOrUpdate(a30);
        } else if (a28 != null && !a28.isEmpty()) {
            org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a28.get(0)).getId());
        }
        String[] a31 = new String[2];
        a31[0] = "type = ?";
        a31[1] = "16";
        java.util.List a32 = org.litepal.LitePal.where(a31).find(com.wakeup.wearfit2.bean.ChannelItem.class);
        label0: if (this.hasLianXuTiwen) {
            label1: {
                if (a32 == null) {
                    break label1;
                }
                if (a32.size() > 0) {
                    break label0;
                }
            }
            com.wakeup.wearfit2.bean.ChannelItem a33 = new com.wakeup.wearfit2.bean.ChannelItem(14, 16, "\u8fde\u7eed\u4f53\u6e29", 1);
            String[] a34 = new String[2];
            a34[0] = "type=?";
            a34[1] = "16";
            a33.saveOrUpdate(a34);
        } else if (a32 != null && !a32.isEmpty()) {
            org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a32.get(0)).getId());
        }
        this.keepNormal();
        this.findDataAndUpdateUI();
    }
    
    private void findDataAndUpdateUI() {
        String[] a = new String[2];
        a[0] = "selected = ?";
        a[1] = "1";
        this.userChannelList = org.litepal.LitePal.where(a).order("orderId").find(com.wakeup.wearfit2.bean.ChannelItem.class);
        String[] a0 = new String[2];
        a0[0] = "selected = ?";
        a0[1] = "0";
        this.otherChannelList = org.litepal.LitePal.where(a0).order("orderId").find(com.wakeup.wearfit2.bean.ChannelItem.class);
        this.userAdapter.setListDate(this.userChannelList);
        this.otherAdapter.setListDate(this.otherChannelList);
        this.userAdapter.notifyDataSetChanged();
        this.otherAdapter.notifyDataSetChanged();
    }
    
    private void getDataAndUpdateUI() {
        android.util.Log.e(TAG, "getDataAndUpdateUI");
        String s = com.wakeup.wearfit2.util.SPUtils.getString(this.mContext, com.wakeup.wearfit2.util.SPUtils.DEVICE_ADDRESS_GET_DATA);
        this.address = s;
        if (android.text.TextUtils.isEmpty((CharSequence)(Object)s)) {
            return;
        }
        long j = com.wakeup.wearfit2.util.DateUtils.getStartTimeStampOfDay(new java.util.Date());
        com.wakeup.wearfit2.ui.fragment.HomeFragment$ReadDbTask a = new com.wakeup.wearfit2.ui.fragment.HomeFragment$ReadDbTask(this, (com.wakeup.wearfit2.ui.fragment.HomeFragment$1)null);
        Long[] a0 = new Long[1];
        a0[0] = Long.valueOf(j);
        a.execute((Object[])a0);
    }
    
    private void getLocationFromAMap() {
        android.util.Log.i(TAG, "\u9ad8\u5fb7\u5730\u56fe \u5355\u6b21\u5b9a\u4f4d");
        this.mlocationClient = new com.amap.api.location.AMapLocationClient((android.content.Context)this.getActivity());
        this.mLocationOption = new com.amap.api.location.AMapLocationClientOption();
        this.mlocationClient.setLocationListener((com.amap.api.location.AMapLocationListener)(Object)this);
        this.mLocationOption.setLocationMode(com.amap.api.location.AMapLocationClientOption$AMapLocationMode.Hight_Accuracy);
        this.mLocationOption.setInterval(2000L);
        this.mLocationOption.setOnceLocation(true);
        this.mlocationClient.setLocationOption(this.mLocationOption);
        this.mlocationClient.startLocation();
    }
    
    public static int getStep() {
        return stepCount;
    }
    
    private void getWeatherForeCast() {
        android.util.Log.i(TAG, "getWeatherForeCast");
        if (this.latitude == 0.0 && this.longitude == 0.0) {
            return;
        }
        java.util.HashMap a = new java.util.HashMap();
        ((java.util.Map)(Object)a).put((Object)"lat", (Object)Double.valueOf(this.latitude));
        ((java.util.Map)(Object)a).put((Object)"lon", (Object)Double.valueOf(this.longitude));
        ((com.wakeup.wearfit2.kotlin.RetrofitService)com.wakeup.wearfit2.AppApplication.retrofit2.create(com.wakeup.wearfit2.kotlin.RetrofitService.class)).getForecast("http://www.iwhop.com/weatherapi/weather/weekforecasting", (java.util.Map)(Object)a).enqueue((retrofit2.Callback)(Object)new com.wakeup.wearfit2.ui.fragment.HomeFragment$6(this));
    }
    
    private void initAdapter() {
        String[] a = new String[2];
        a[0] = "selected = ?";
        a[1] = "1";
        this.userChannelList = org.litepal.LitePal.where(a).order("orderId").find(com.wakeup.wearfit2.bean.ChannelItem.class);
        String[] a0 = new String[2];
        a0[0] = "selected = ?";
        a0[1] = "0";
        this.otherChannelList = org.litepal.LitePal.where(a0).order("orderId").find(com.wakeup.wearfit2.bean.ChannelItem.class);
        com.wakeup.wearfit2.adapter.DragAdapter a1 = new com.wakeup.wearfit2.adapter.DragAdapter(this.mContext, this.userChannelList, this.dbModel);
        this.userAdapter = a1;
        this.userGridView.setAdapter((android.widget.ListAdapter)(Object)a1);
        com.wakeup.wearfit2.adapter.OtherAdapter a2 = new com.wakeup.wearfit2.adapter.OtherAdapter(this.mContext, this.otherChannelList, this.dbModel);
        this.otherAdapter = a2;
        this.otherGridView.setAdapter((android.widget.ListAdapter)(Object)a2);
        this.otherGridView.setOnItemClickListener((android.widget.AdapterView$OnItemClickListener)(Object)this);
        this.userGridView.setOnItemClickListener((android.widget.AdapterView$OnItemClickListener)(Object)this);
        this.userGridView.setDragGridListener((com.wakeup.wearfit2.ui.view.DragGrid$DragGridListener)(Object)this);
        this.otherChannel = this.otherAdapter.getItem(0);
    }
    
    private void keepNormal() {
        String s = TAG;
        StringBuilder a = new StringBuilder();
        a.append("keepNormal ");
        a.append(this.userChannelList.size());
        a.append(" ");
        a.append(this.otherChannelList.size());
        android.util.Log.i(s, a.toString());
        int i = this.userChannelList.size();
        label0: {
            label18: {
                if (i < 6) {
                    break label18;
                }
                if (this.otherChannelList.size() == 1) {
                    break label0;
                }
            }
            android.util.Log.e(s, "\u6570\u636e\u5e93\u6570\u636e\u51fa\u73b0\u95ee\u9898\u65f6\uff0c\u786e\u4fdd\u9996\u9875\u6b63\u5e38\u663e\u793a");
            org.litepal.LitePal.deleteAll(com.wakeup.wearfit2.bean.ChannelItem.class, new String[0]);
            com.wakeup.wearfit2.bean.ChannelItem a0 = new com.wakeup.wearfit2.bean.ChannelItem(0, 1, "\u7761\u7720", 1);
            String[] a1 = new String[2];
            a1[0] = "type=?";
            a1[1] = "1";
            a0.saveOrUpdate(a1);
            com.wakeup.wearfit2.bean.ChannelItem a2 = new com.wakeup.wearfit2.bean.ChannelItem(1, 2, "\u5fc3\u7387", 1);
            String[] a3 = new String[2];
            a3[0] = "type=?";
            a3[1] = "2";
            a2.saveOrUpdate(a3);
            com.wakeup.wearfit2.bean.ChannelItem a4 = new com.wakeup.wearfit2.bean.ChannelItem(2, 3, "\u8840\u538b", 1);
            String[] a5 = new String[2];
            a5[0] = "type=?";
            a5[1] = "3";
            a4.saveOrUpdate(a5);
            com.wakeup.wearfit2.bean.ChannelItem a6 = new com.wakeup.wearfit2.bean.ChannelItem(3, 4, "\u8840\u6c27", 1);
            String[] a7 = new String[2];
            a7[0] = "type=?";
            a7[1] = "4";
            a6.saveOrUpdate(a7);
            com.wakeup.wearfit2.bean.ChannelItem a8 = new com.wakeup.wearfit2.bean.ChannelItem(4, 5, "\u75b2\u52b3\u5ea6", 1);
            String[] a9 = new String[2];
            a9[0] = "type=?";
            a9[1] = "5";
            a8.saveOrUpdate(a9);
            com.wakeup.wearfit2.bean.ChannelItem a10 = new com.wakeup.wearfit2.bean.ChannelItem(5, 6, "\u5fc3\u7535", 1);
            String[] a11 = new String[2];
            a11[0] = "type=?";
            a11[1] = "6";
            a10.saveOrUpdate(a11);
            com.wakeup.wearfit2.bean.ChannelItem a12 = new com.wakeup.wearfit2.bean.ChannelItem(0, 7, "\u8ba1\u6b65", 0);
            String[] a13 = new String[2];
            a13[0] = "type=?";
            a13[1] = "7";
            a12.saveOrUpdate(a13);
            String[] a14 = new String[2];
            a14[0] = "type = ?";
            a14[1] = "8";
            java.util.List a15 = org.litepal.LitePal.where(a14).find(com.wakeup.wearfit2.bean.ChannelItem.class);
            label16: if (this.hasTiwen) {
                label17: {
                    if (a15 == null) {
                        break label17;
                    }
                    if (a15.size() > 0) {
                        break label16;
                    }
                }
                com.wakeup.wearfit2.bean.ChannelItem a16 = new com.wakeup.wearfit2.bean.ChannelItem(6, 8, "\u4f53\u6e29", 1);
                String[] a17 = new String[2];
                a17[0] = "type=?";
                a17[1] = "8";
                a16.saveOrUpdate(a17);
            } else if (a15 != null && !a15.isEmpty()) {
                org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a15.get(0)).getId());
            }
            String[] a18 = new String[2];
            a18[0] = "type = ?";
            a18[1] = "9";
            java.util.List a19 = org.litepal.LitePal.where(a18).find(com.wakeup.wearfit2.bean.ChannelItem.class);
            label14: if (this.hasMianyi) {
                label15: {
                    if (a19 == null) {
                        break label15;
                    }
                    if (a19.size() > 0) {
                        break label14;
                    }
                }
                com.wakeup.wearfit2.bean.ChannelItem a20 = new com.wakeup.wearfit2.bean.ChannelItem(7, 9, "\u514d\u75ab\u503c", 1);
                String[] a21 = new String[2];
                a21[0] = "type=?";
                a21[1] = "9";
                a20.saveOrUpdate(a21);
            } else if (a19 != null && !a19.isEmpty()) {
                org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a19.get(0)).getId());
            }
            String[] a22 = new String[2];
            a22[0] = "type = ?";
            a22[1] = "10";
            java.util.List a23 = org.litepal.LitePal.where(a22).find(com.wakeup.wearfit2.bean.ChannelItem.class);
            label12: if (this.hasGuoNeiYiQing) {
                label13: {
                    if (a23 == null) {
                        break label13;
                    }
                    if (a23.size() > 0) {
                        break label12;
                    }
                }
                com.wakeup.wearfit2.bean.ChannelItem a24 = new com.wakeup.wearfit2.bean.ChannelItem(8, 10, "\u56fd\u5185\u75ab\u60c5", 1);
                String[] a25 = new String[2];
                a25[0] = "type=?";
                a25[1] = "10";
                a24.saveOrUpdate(a25);
            } else if (a23 != null && !a23.isEmpty()) {
                org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a23.get(0)).getId());
            }
            String[] a26 = new String[2];
            a26[0] = "type = ?";
            a26[1] = "11";
            java.util.List a27 = org.litepal.LitePal.where(a26).find(com.wakeup.wearfit2.bean.ChannelItem.class);
            label10: if (this.hasGuahao) {
                label11: {
                    if (a27 == null) {
                        break label11;
                    }
                    if (a27.size() > 0) {
                        break label10;
                    }
                }
                com.wakeup.wearfit2.bean.ChannelItem a28 = new com.wakeup.wearfit2.bean.ChannelItem(9, 11, "\u9884\u7ea6\u6302\u53f7", 1);
                String[] a29 = new String[2];
                a29[0] = "type=?";
                a29[1] = "11";
                a28.saveOrUpdate(a29);
            } else if (a27 != null && !a27.isEmpty()) {
                org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a27.get(0)).getId());
            }
            String[] a30 = new String[2];
            a30[0] = "type = ?";
            a30[1] = "12";
            java.util.List a31 = org.litepal.LitePal.where(a30).find(com.wakeup.wearfit2.bean.ChannelItem.class);
            label8: if (this.hasJiankangDaka) {
                label9: {
                    if (a31 == null) {
                        break label9;
                    }
                    if (a31.size() > 0) {
                        break label8;
                    }
                }
                com.wakeup.wearfit2.bean.ChannelItem a32 = new com.wakeup.wearfit2.bean.ChannelItem(10, 12, "\u5065\u5eb7\u6253\u5361", 1);
                String[] a33 = new String[2];
                a33[0] = "type=?";
                a33[1] = "12";
                a32.saveOrUpdate(a33);
            } else if (a31 != null && !a31.isEmpty()) {
                org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a31.get(0)).getId());
            }
            String[] a34 = new String[2];
            a34[0] = "type = ?";
            a34[1] = "13";
            java.util.List a35 = org.litepal.LitePal.where(a34).find(com.wakeup.wearfit2.bean.ChannelItem.class);
            label6: if (this.hasGuoWaiYiQing) {
                label7: {
                    if (a35 == null) {
                        break label7;
                    }
                    if (a35.size() > 0) {
                        break label6;
                    }
                }
                com.wakeup.wearfit2.bean.ChannelItem a36 = new com.wakeup.wearfit2.bean.ChannelItem(11, 13, "\u56fd\u5916\u75ab\u60c5", 1);
                String[] a37 = new String[2];
                a37[0] = "type=?";
                a37[1] = "13";
                a36.saveOrUpdate(a37);
            } else if (a35 != null && !a35.isEmpty()) {
                org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a35.get(0)).getId());
            }
            String[] a38 = new String[2];
            a38[0] = "type = ?";
            a38[1] = "14";
            java.util.List a39 = org.litepal.LitePal.where(a38).find(com.wakeup.wearfit2.bean.ChannelItem.class);
            label4: if (this.hasJuankuan) {
                label5: {
                    if (a39 == null) {
                        break label5;
                    }
                    if (a39.size() > 0) {
                        break label4;
                    }
                }
                com.wakeup.wearfit2.bean.ChannelItem a40 = new com.wakeup.wearfit2.bean.ChannelItem(12, 14, "\u6350\u6b3e", 1);
                String[] a41 = new String[2];
                a41[0] = "type=?";
                a41[1] = "14";
                a40.saveOrUpdate(a41);
            } else if (a39 != null && !a39.isEmpty()) {
                org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a39.get(0)).getId());
            }
            String[] a42 = new String[2];
            a42[0] = "type = ?";
            a42[1] = "15";
            java.util.List a43 = org.litepal.LitePal.where(a42).find(com.wakeup.wearfit2.bean.ChannelItem.class);
            label2: if (this.hasJiankangjisuanqi) {
                label3: {
                    if (a43 == null) {
                        break label3;
                    }
                    if (a43.size() > 0) {
                        break label2;
                    }
                }
                com.wakeup.wearfit2.bean.ChannelItem a44 = new com.wakeup.wearfit2.bean.ChannelItem(13, 15, "\u5065\u5eb7\u8ba1\u7b97\u5668", 1);
                String[] a45 = new String[2];
                a45[0] = "type=?";
                a45[1] = "15";
                a44.saveOrUpdate(a45);
            } else if (a43 != null && !a43.isEmpty()) {
                org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a43.get(0)).getId());
            }
            String[] a46 = new String[2];
            a46[0] = "type = ?";
            a46[1] = "16";
            java.util.List a47 = org.litepal.LitePal.where(a46).find(com.wakeup.wearfit2.bean.ChannelItem.class);
            if (this.hasLianXuTiwen) {
                label1: {
                    if (a47 == null) {
                        break label1;
                    }
                    if (a47.size() > 0) {
                        break label0;
                    }
                }
                com.wakeup.wearfit2.bean.ChannelItem a48 = new com.wakeup.wearfit2.bean.ChannelItem(14, 16, "\u8fde\u7eed\u4f53\u6e29", 1);
                String[] a49 = new String[2];
                a49[0] = "type=?";
                a49[1] = "16";
                a48.saveOrUpdate(a49);
            } else if (a47 != null && !a47.isEmpty()) {
                org.litepal.LitePal.delete(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a47.get(0)).getId());
            }
        }
    }
    
    public static com.wakeup.wearfit2.ui.fragment.HomeFragment newInstance() {
        return new com.wakeup.wearfit2.ui.fragment.HomeFragment();
    }
    
    private void refresh() {
        String s = TAG;
        android.util.Log.i(s, "refresh");
        long j = com.wakeup.wearfit2.util.SPUtils.getLong(this.mContext, "last_jingqi", org.joda.time.LocalDate.now().toDateTimeAtStartOfDay().getMillis());
        org.joda.time.format.DateTimeFormatter a = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd");
        int i = com.wakeup.wearfit2.util.SPUtils.getInt(this.mContext, "jingqi_changdu", -1);
        int i0 = com.wakeup.wearfit2.util.SPUtils.getInt(this.mContext, "jingqi_jiange", -2);
        StringBuilder a0 = new StringBuilder();
        a0.append("jingqi_changdu ");
        a0.append(i);
        a0.append(" jiangqi_jiange ");
        a0.append(i0);
        android.util.Log.i(s, a0.toString());
        if (i == -1 && i0 == -2) {
            return;
        }
        boolean b = com.wakeup.wearfit2.util.SPUtils.getBoolean(this.mContext, "jingqi_switch", false);
        int i1 = com.wakeup.wearfit2.util.SPUtils.getInt(this.mContext, "id", -1);
        org.json.JSONObject a1 = new org.json.JSONObject();
        try {
            if (i1 != -1) {
                a1.put("id", i1);
            }
            a1.put("intervals", i0);
            a1.put("lastMenstruation", (Object)a.print(j));
            a1.put("len", i);
            a1.put("onOff", b ? 1 : 0);
        } catch(org.json.JSONException a2) {
            a2.printStackTrace();
        }
        String s0 = a1.toString();
        android.util.Log.i(TAG, s0);
        okhttp3.RequestBody a3 = okhttp3.RequestBody.create(com.wakeup.wearfit2.custom.Constants.JSON, s0);
        java.util.HashMap a4 = new java.util.HashMap();
        org.joda.time.LocalDate a5 = org.joda.time.LocalDate.now();
        int i2 = a5.getYear();
        int i3 = a5.getMonthOfYear();
        StringBuilder a6 = new StringBuilder();
        a6.append(i2);
        a6.append("-");
        a6.append(i3);
        ((java.util.Map)(Object)a4).put((Object)"qudate", (Object)a6.toString());
        this.retrofitService.mergeMenstruation(a3).enqueue((retrofit2.Callback)(Object)new com.wakeup.wearfit2.ui.fragment.HomeFragment$10(this, (java.util.Map)(Object)a4, a5));
    }
    
    private void refreshWeather() {
        String s = TAG;
        android.util.Log.d(s, "\u83b7\u53d6\u5929\u6c14\u53d1\u9001\u5929\u6c141");
        if (!com.wakeup.wearfit2.util.SPUtils.getBoolean(this.mContext, "has_weather", false)) {
            return;
        }
        android.util.Log.d(s, "\u83b7\u53d6\u5929\u6c14\u53d1\u9001\u5929\u6c14");
        this.getLocationFromAMap();
    }
    
    private void requestSyncData() {
        android.util.Log.d(TAG, "\u5f00\u59cb\u540c\u6b65\u6570\u636e");
        this.handler.sendEmptyMessageDelayed(7, 10000L);
        this.mManager.setTimeSync();
        boolean b = com.wakeup.wearfit2.AppApplication.hasContinueHr;
        label2: {
            label0: {
                label1: {
                    if (b) {
                        break label1;
                    }
                    if (com.wakeup.wearfit2.AppApplication.hasContinueHrD) {
                        break label1;
                    }
                    if (com.wakeup.wearfit2.AppApplication.hasContinueHrE) {
                        break label1;
                    }
                    if (!com.wakeup.wearfit2.AppApplication.hasContinueHrF) {
                        break label0;
                    }
                }
                this.handler.sendEmptyMessageDelayed(8, 1000L);
                break label2;
            }
            this.handler.sendEmptyMessageDelayed(5, 1000L);
        }
        this.handler.sendEmptyMessageDelayed(6, 2000L);
    }
    
    private void saveChannel() {
        Object a = null;
        int i = 0;
        String s = TAG;
        android.util.Log.d(s, "saveChannel: ");
        java.util.List a0 = this.userAdapter.getChannnelLst();
        java.util.List a1 = this.otherAdapter.getChannnelLst();
        StringBuilder a2 = new StringBuilder();
        a2.append("useList:");
        a2.append(((Object)a0).toString());
        android.util.Log.i(s, a2.toString());
        StringBuilder a3 = new StringBuilder();
        a3.append("otherList:");
        a3.append(((Object)a1).toString());
        android.util.Log.i(s, a3.toString());
        String[] a4 = new String[2];
        a4[0] = "selected = ?";
        a4[1] = "1";
        this.userChannelList = org.litepal.LitePal.where(a4).order("orderId").find(com.wakeup.wearfit2.bean.ChannelItem.class);
        String[] a5 = new String[2];
        a5[0] = "selected = ?";
        a5[1] = "0";
        this.otherChannelList = org.litepal.LitePal.where(a5).order("orderId").find(com.wakeup.wearfit2.bean.ChannelItem.class);
        StringBuilder a6 = new StringBuilder();
        a6.append("1--");
        a6.append(((Object)this.userChannelList).toString());
        android.util.Log.i(s, a6.toString());
        StringBuilder a7 = new StringBuilder();
        a7.append("0--\uff1a");
        a7.append(((Object)this.otherChannelList).toString());
        android.util.Log.i(s, a7.toString());
        if (a1.isEmpty()) {
            a = a0;
            i = 0;
        } else {
            com.wakeup.wearfit2.bean.ChannelItem a8 = (com.wakeup.wearfit2.bean.ChannelItem)org.litepal.LitePal.find(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)a1.get(0)).getId());
            a8.setOrderId(0);
            a8.setSelected(0);
            a8.save();
            a = a0;
            i = 0;
        }
        for(; i < ((java.util.List)a).size(); i = i + 1) {
            com.wakeup.wearfit2.bean.ChannelItem a9 = (com.wakeup.wearfit2.bean.ChannelItem)org.litepal.LitePal.find(com.wakeup.wearfit2.bean.ChannelItem.class, (long)((com.wakeup.wearfit2.bean.ChannelItem)((java.util.List)a).get(i)).getId());
            a9.setOrderId(i);
            a9.setSelected(1);
            a9.save();
        }
        String[] a10 = new String[2];
        a10[0] = "selected = ?";
        a10[1] = "1";
        this.userChannelList = org.litepal.LitePal.where(a10).order("orderId").find(com.wakeup.wearfit2.bean.ChannelItem.class);
        String[] a11 = new String[2];
        a11[0] = "selected = ?";
        a11[1] = "0";
        this.otherChannelList = org.litepal.LitePal.where(a11).order("orderId").find(com.wakeup.wearfit2.bean.ChannelItem.class);
        String s0 = TAG;
        StringBuilder a12 = new StringBuilder();
        a12.append("1--");
        a12.append(((Object)this.userChannelList).toString());
        android.util.Log.i(s0, a12.toString());
        StringBuilder a13 = new StringBuilder();
        a13.append("0--\uff1a");
        a13.append(((Object)this.otherChannelList).toString());
        android.util.Log.i(s0, a13.toString());
    }
    
    private void stopRefresh() {
        com.wakeup.wearfit2.view.ScrollableSwipeRefreshLayout a = this.swip;
        if (a != null && a.isRefreshing()) {
            android.util.Log.i(TAG, "stopRefresh");
            this.swip.setRefreshing(false);
            this.isRefreshing = false;
            String s = com.wakeup.wearfit2.util.SPUtils.getString(this.getContext(), "token", "");
            if (s != null && !s.isEmpty()) {
                new com.wakeup.wearfit2.Biz.UpDeviceDataBiz().upData();
            }
        }
    }
    
    private void updateUI(com.wakeup.wearfit2.bean.HomeDBModle a) {
        android.util.Log.i(TAG, a.toString());
        this.userAdapter.setData(a);
        this.otherAdapter.setData(a);
        this.userAdapter.notifyDataSetChanged();
        this.otherAdapter.notifyDataSetChanged();
    }
    
    public void OutofBounds() {
        this.OutofBounds = true;
    }
    
    public void onAttach(android.app.Activity a) {
        ((androidx.fragment.app.Fragment)this).onAttach(a);
        this.mOnListenPop = (com.wakeup.wearfit2.ui.fragment.HomeFragment$OnListenPop)(Object)a;
    }
    
    public void onAttach(android.content.Context a) {
        ((androidx.fragment.app.Fragment)this).onAttach(a);
        this.mContext = a;
    }
    
    public void onCreate(android.os.Bundle a) {
        ((androidx.fragment.app.Fragment)this).onCreate(a);
        this.hasTiwen = com.wakeup.wearfit2.util.SPUtils.getBoolean(this.requireContext(), "hastiwen", false);
        this.hasMianyi = com.wakeup.wearfit2.util.SPUtils.getBoolean(this.requireContext(), "hasmianyi", false);
        this.hasLianXuTiwen = com.wakeup.wearfit2.util.SPUtils.getBoolean(this.requireContext(), "hasnewtiwen", false);
        this.retrofitService = (com.wakeup.wearfit2.kotlin.RetrofitService)com.wakeup.wearfit2.AppApplication.retrofit2.create(com.wakeup.wearfit2.kotlin.RetrofitService.class);
        org.greenrobot.eventbus.EventBus.getDefault().register((Object)this);
        this.mVibrator = (android.os.Vibrator)this.mContext.getSystemService("vibrator");
        android.util.Log.i(TAG, "onCreate");
    }
    
    public android.view.View onCreateView(android.view.LayoutInflater a, android.view.ViewGroup a0, android.os.Bundle a1) {
        android.util.Log.i("life", "onCreateView");
        android.view.View a2 = a.inflate(2131493143, a0, false);
        butterknife.ButterKnife.bind((Object)this, a2);
        return a2;
    }
    
    public void onDestroy() {
        ((androidx.fragment.app.Fragment)this).onDestroy();
        android.util.Log.i(TAG, "onDestroy");
        org.greenrobot.eventbus.EventBus.getDefault().unregister((Object)this);
        java.util.Timer a = this.mTimer;
        if (a != null) {
            a.cancel();
        }
        this.handler.removeCallbacksAndMessages((Object)null);
    }
    
    public void onDestroyView() {
        ((androidx.fragment.app.Fragment)this).onDestroyView();
    }
    
    public void onEventMainThread(com.wakeup.wearfit2.model.event.BaseEvent a) {
        switch(com.wakeup.wearfit2.ui.fragment.HomeFragment$11.$SwitchMap$com$wakeup$wearfit2$model$event$BaseEvent$EventType[a.getEventType().ordinal()]) {
            case 13: {
                this.hasMianyi = com.wakeup.wearfit2.util.SPUtils.getBoolean(this.requireContext(), "hasmianyi", false);
                this.hasTiwen = com.wakeup.wearfit2.util.SPUtils.getBoolean(this.requireContext(), "hastiwen", false);
                this.hasLianXuTiwen = com.wakeup.wearfit2.util.SPUtils.getBoolean(this.requireContext(), "hasnewtiwen", false);
                android.util.Log.i(TAG, "TIWEN_AND_MIANYI-->hasMianyi: $hasMianyi hasTiwen: $hasTiwen hasLianXuTiwen: $hasLianXuTiwen");
                this.controlFunction();
                break;
            }
            case 12: {
                String s = TAG;
                android.util.Log.i(s, "\u83b7\u5f97\u7248\u672c\u53f7");
                this.startRefresh();
                this.getDataAndUpdateUI();
                this.refreshWeather();
                this.hasMianyi = com.wakeup.wearfit2.util.SPUtils.getBoolean(this.requireContext(), "hasmianyi", false);
                this.hasTiwen = com.wakeup.wearfit2.util.SPUtils.getBoolean(this.requireContext(), "hastiwen", false);
                this.hasLianXuTiwen = com.wakeup.wearfit2.util.SPUtils.getBoolean(this.requireContext(), "hasnewtiwen", false);
                android.util.Log.i(s, "BAND_VERSION_GOT-->hasMianyi: $hasMianyi hasTiwen: $hasTiwen hasLianXuTiwen: $hasLianXuTiwen");
                this.controlFunction();
                break;
            }
            case 11: {
                android.util.Log.i(TAG, "BAND_BATTERY_INFO");
                break;
            }
            case 10: {
                this.otherAdapter.notifyDataSetChanged();
                break;
            }
            case 8: {
                if (com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) {
                    if (this.swip.isRefreshing()) {
                        return;
                    }
                    break;
                } else {
                    if (this.swip.isRefreshing()) {
                        this.swip.setRefreshing(false);
                    }
                    org.greenrobot.eventbus.EventBus.getDefault().post((Object)new com.wakeup.wearfit2.model.event.BaseEvent(com.wakeup.wearfit2.model.event.BaseEvent$EventType.UPDATE_NOTIFICATION));
                    break;
                }
            }
            case 7: {
                this.getDataAndUpdateUI();
                break;
            }
            case 6: {
                this.getDataAndUpdateUI();
                break;
            }
            case 5: {
                this.getDataAndUpdateUI();
                break;
            }
            case 4: {
                this.getDataAndUpdateUI();
                break;
            }
            case 3: {
                this.getDataAndUpdateUI();
                break;
            }
            case 2: {
                this.stepAndSleepModel = (com.wakeup.wearfit2.model.StepAndSleepModel)a.getmObject();
                break;
            }
            case 1: {
                android.util.Log.e(TAG, "\u63a5\u6536\u6570\u636e\u4e2d");
                this.handler.removeMessages(7);
                this.handler.sendEmptyMessageDelayed(7, 7000L);
                break;
            }
        }
    }
    
    public void onHiddenChanged(boolean b) {
        ((androidx.fragment.app.Fragment)this).onHiddenChanged(b);
        if (!b) {
            this.mOnListenPop.popClose();
            com.wakeup.wearfit2.adapter.OtherAdapter a = this.otherAdapter;
            if (a != null) {
                a.notifyDataSetChanged();
            }
        }
        com.wakeup.wearfit2.adapter.OtherAdapter a0 = this.otherAdapter;
        if (a0 != null) {
            a0.notifyDataSetChanged();
        }
    }
    
    public void onItemClick(android.widget.AdapterView a, android.view.View a0, int i, long j) {
        if (this.isMove) {
            return;
        }
        int i0 = a.getId();
        if (i0 == 2131296629) {
            com.wakeup.wearfit2.bean.ChannelItem a1 = ((com.wakeup.wearfit2.adapter.DragAdapter)(Object)a.getAdapter()).getItem(i);
            this.otherAdapter.setVisible(false);
            this.JumpToActivity(a1);
        } else if (i0 == 2131296763) {
            com.wakeup.wearfit2.bean.ChannelItem a2 = ((com.wakeup.wearfit2.adapter.OtherAdapter)(Object)a.getAdapter()).getItem(i);
            this.userAdapter.setVisible(false);
            this.JumpToActivity(a2);
        }
    }
    
    public void onLocationChanged(com.amap.api.location.AMapLocation a) {
        String s = TAG;
        android.util.Log.i(s, "AMap onLocationChanged");
        if (a != null) {
            if (a.getErrorCode() != 0) {
                StringBuilder a0 = new StringBuilder();
                a0.append("location Error, ErrCode:");
                a0.append(a.getErrorCode());
                a0.append(", errInfo:");
                a0.append(a.getErrorInfo());
                android.util.Log.e("AmapError", a0.toString());
            } else {
                a.getLocationType();
                this.latitude = a.getLatitude();
                this.longitude = a.getLongitude();
                String s0 = a.getCountry();
                com.wakeup.wearfit2.util.SPUtils.putString(this.requireContext(), "country", s0);
                com.wakeup.wearfit2.util.SPUtils.putFloat(this.requireContext(), "latitude", (float)this.latitude);
                com.wakeup.wearfit2.util.SPUtils.putFloat(this.requireContext(), "longitude", (float)this.longitude);
                a.getAccuracy();
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(a.getTime()));
                StringBuilder a1 = new StringBuilder();
                a1.append("latitude+ ");
                a1.append(this.latitude);
                a1.append(" / longitude+ ");
                a1.append(this.longitude);
                android.util.Log.i(s, a1.toString());
                this.getWeatherForeCast();
            }
        }
    }
    
    public void onPause() {
        ((androidx.fragment.app.Fragment)this).onPause();
        android.util.Log.i(TAG, "onPause");
        if (this.isRefreshing) {
            this.stopRefresh();
        }
    }
    
    public void onStart() {
        ((androidx.fragment.app.Fragment)this).onStart();
        if (com.wakeup.wearfit2.util.SPUtils.getInt(this.requireContext(), "googlefit_connect", 0) == 1) {
            if (com.wakeup.wearfit2.ui.activity.googlefit.GoogleFitActivity.mClient == null) {
                android.util.Log.i(TAG, "mClient= =null");
                com.wakeup.wearfit2.ui.activity.googlefit.GoogleFitActivity.buildFitnessClient(this.mContext, (android.app.Activity)this.getActivity(), (android.widget.Button)null);
            } else {
                android.util.Log.i(TAG, "mClient!=null");
                com.wakeup.wearfit2.ui.activity.googlefit.GoogleFitActivity.mClient.disconnect();
                com.wakeup.wearfit2.ui.activity.googlefit.GoogleFitActivity.mClient.connect();
            }
        }
        this.timer = new java.util.Timer();
        com.wakeup.wearfit2.ui.fragment.HomeFragment$9 a = new com.wakeup.wearfit2.ui.fragment.HomeFragment$9(this);
        this.timer.scheduleAtFixedRate((java.util.TimerTask)a, 3600000L, 3600000L);
    }
    
    public void onStop() {
        ((androidx.fragment.app.Fragment)this).onStop();
        android.util.Log.i(TAG, "onStop");
        if (com.wakeup.wearfit2.ui.activity.googlefit.GoogleFitActivity.mClient != null) {
            com.wakeup.wearfit2.ui.activity.googlefit.GoogleFitActivity.mClient.stopAutoManage(this.getActivity());
            com.wakeup.wearfit2.ui.activity.googlefit.GoogleFitActivity.mClient.disconnect();
        }
        java.util.Timer a = this.timer;
        if (a != null) {
            a.cancel();
            this.timer = null;
        }
    }
    
    public void onViewCreated(android.view.View a, android.os.Bundle a0) {
        ((androidx.fragment.app.Fragment)this).onViewCreated(a, a0);
        android.util.Log.i(TAG, "onViewCreated");
        this.initAdapter();
        this.mManager = com.wakeup.wearfit2.manager.CommandManager.getInstance(this.mContext);
        if (!com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected() && !com.wakeup.wearfit2.util.SPUtils.getString(this.mContext, com.wakeup.wearfit2.util.SPUtils.DEVICE_ADDRESS, "").equals((Object)"")) {
            this.swip.post((Runnable)(Object)new com.wakeup.wearfit2.ui.fragment.HomeFragment$3(this));
        }
        this.getDataAndUpdateUI();
        com.wakeup.wearfit2.view.ScrollableSwipeRefreshLayout a1 = this.swip;
        int[] a2 = new int[4];
        a2[0] = 2131100001;
        a2[1] = 2131099997;
        a2[2] = 2131100000;
        a2[3] = 2131100003;
        a1.setColorSchemeResources(a2);
        this.swip.setOnRefreshListener(this.swipeRefreshListener);
        this.getLocationFromAMap();
    }
    
    public void popException(int i) {
        com.wakeup.wearfit2.ui.fragment.HomeFragment$OnListenPop a = this.mOnListenPop;
        if (a != null) {
            a.popOpen();
        }
        android.view.View a0 = android.view.LayoutInflater.from(this.mContext).inflate(2131493298, (android.view.ViewGroup)null);
        android.widget.PopupWindow a1 = new android.widget.PopupWindow(a0, -1, -2, false);
        a1.setFocusable(false);
        a1.setTouchable(true);
        a1.setOutsideTouchable(true);
        a1.setBackgroundDrawable((android.graphics.drawable.Drawable)new android.graphics.drawable.BitmapDrawable());
        a1.showAtLocation(this.getActivity().findViewById(2131296681), 48, 0, 0);
        ((android.widget.TextView)a0.findViewById(2131297694)).setText((CharSequence)(Object)String.valueOf(i));
        a0.findViewById(2131297032).setOnClickListener((android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.ui.fragment.HomeFragment$7(this, a1));
        a1.setOnDismissListener((android.widget.PopupWindow$OnDismissListener)(Object)new com.wakeup.wearfit2.ui.fragment.HomeFragment$8(this));
    }
    
    public void selectedChannel(com.wakeup.wearfit2.bean.ChannelItem a) {
        this.slectedChannel = a;
        String s = TAG;
        StringBuilder a0 = new StringBuilder();
        a0.append("channelItem.getName():");
        a0.append(a.getName());
        android.util.Log.i(s, a0.toString());
        com.wakeup.wearfit2.bean.ChannelItem a1 = this.otherAdapter.getItem(0);
        if (a1 != null) {
            StringBuilder a2 = new StringBuilder();
            a2.append("other\u7684channel");
            a2.append(a1.getName());
            android.util.Log.i(s, a2.toString());
            this.otherChannel = a1;
        }
        this.swip.setEnabled(false);
    }
    
    public void startRefresh() {
        com.wakeup.wearfit2.view.ScrollableSwipeRefreshLayout a = this.swip;
        if (a != null && !this.isRefreshing) {
            a.post((Runnable)(Object)new com.wakeup.wearfit2.ui.fragment.HomeFragment$5(this));
        }
    }
    
    public void upHands() {
        this.userGridView.setAlpha(1f);
        this.swip.setEnabled(true);
        if (this.OutofBounds) {
            this.otherAdapter.replace(this.slectedChannel);
            this.userAdapter.replacewith(this.slectedChannel, this.otherChannel);
            this.OutofBounds = false;
            android.content.Context a = this.mContext;
            this.listener = (com.wakeup.wearfit2.ui.fragment.HomeFragment$ChangeBottomColorListener)(Object)a;
            ((com.wakeup.wearfit2.ui.fragment.HomeFragment$ChangeBottomColorListener)(Object)a).onChangeColor(this.slectedChannel);
        }
        this.saveChannel();
    }
}
