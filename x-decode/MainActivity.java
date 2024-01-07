package com.wakeup.wearfit2.ui.activity;

public class MainActivity extends com.wakeup.wearfit2.ui.activity.splash.BaseADActivity implements com.wakeup.wearfit2.ui.fragment.HomeFragment$ChangeBottomColorListener, com.wakeup.wearfit2.ui.fragment.HomeFragment$OnListenPop {
    final public static int BLUESELECTED = 3;
    final private static String CURRENTFRAGMENT = "current_fragment";
    final private static int DIALOG_VERSION_ALERT = 0;
    final private static String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    final public static int PURPLESELECTED = 4;
    final private static int RC_CAMERA_AND_LOCATION = 1001;
    final public static int REDSELECTED = 1;
    final private static String TAG = "MainActivity";
    final public static int YELLOWSELECTED = 2;
    android.widget.RelativeLayout activityMain;
    android.widget.FrameLayout bannerContainer;
    private android.graphics.Bitmap bitmap;
    private int channelID;
    android.widget.TextView connect_status;
    private int currentFragment;
    private com.wakeup.wearfit2.ui.quanzi.fragment.DiscoverFragment discoverFragment;
    private int flag;
    android.widget.TextView healthWeek;
    private com.wakeup.wearfit2.ui.fragment.HomeFragment homeFragment;
    private com.wakeup.wearfit2.ble.WearfitService$LocalBinder localBinder;
    final private android.content.BroadcastReceiver mCommonBroadcastReceiver;
    private android.content.Context mContext;
    private String mDeviceName;
    android.widget.ImageView mIvGuide;
    private com.wakeup.wearfit2.ble.WearfitService mService;
    private android.content.ServiceConnection mServiceConnection;
    android.widget.TextView measure;
    private android.os.Handler mhandler;
    private com.wakeup.wearfit2.ui.fragment.MineFragment mineFragment;
    java.util.List otherChannelList;
    private com.wakeup.wearfit2.util.PopupMenuUtil popupMenuUtil;
    android.widget.RadioButton rbDiscover;
    android.widget.RadioButton rbHome;
    android.widget.RadioButton rbMine;
    android.widget.RadioGroup rg;
    
    static {
    }
    
    public MainActivity() {
        this.otherChannelList = (java.util.List)(Object)new java.util.ArrayList();
        this.mCommonBroadcastReceiver = new com.wakeup.wearfit2.ui.activity.MainActivity$1(this);
        this.mServiceConnection = (android.content.ServiceConnection)(Object)new com.wakeup.wearfit2.ui.activity.MainActivity$3(this);
    }
    
    static String access$000() {
        return TAG;
    }
    
    static void access$100(com.wakeup.wearfit2.ui.activity.MainActivity a) {
        a.methodRequiresTwoPermission();
    }
    
    static android.content.Context access$1000(com.wakeup.wearfit2.ui.activity.MainActivity a) {
        return a.mContext;
    }
    
    static void access$1100(com.wakeup.wearfit2.ui.activity.MainActivity a) {
        a.disconnectDevice();
    }
    
    static android.app.Dialog access$1200(com.wakeup.wearfit2.ui.activity.MainActivity a, String s, String s0) {
        return a.createVersionAlertDialog(s, s0);
    }
    
    static void access$200(com.wakeup.wearfit2.ui.activity.MainActivity a) {
        a.onDeviceConnected();
    }
    
    static void access$300(com.wakeup.wearfit2.ui.activity.MainActivity a) {
        a.onDeviceDisconnected();
    }
    
    static void access$400(com.wakeup.wearfit2.ui.activity.MainActivity a) {
        a.onDeviceReady();
    }
    
    static com.wakeup.wearfit2.ble.WearfitService$LocalBinder access$500(com.wakeup.wearfit2.ui.activity.MainActivity a) {
        return a.localBinder;
    }
    
    static com.wakeup.wearfit2.ble.WearfitService$LocalBinder access$502(com.wakeup.wearfit2.ui.activity.MainActivity a, com.wakeup.wearfit2.ble.WearfitService$LocalBinder a0) {
        a.localBinder = a0;
        return a0;
    }
    
    static com.wakeup.wearfit2.ble.WearfitService access$602(com.wakeup.wearfit2.ui.activity.MainActivity a, com.wakeup.wearfit2.ble.WearfitService a0) {
        a.mService = a0;
        return a0;
    }
    
    static String access$702(com.wakeup.wearfit2.ui.activity.MainActivity a, String s) {
        a.mDeviceName = s;
        return s;
    }
    
    static void access$800(com.wakeup.wearfit2.ui.activity.MainActivity a) {
        a.initSetting();
    }
    
    static void access$900(com.wakeup.wearfit2.ui.activity.MainActivity a) {
        a.initPrompt();
    }
    
    private void checkAlerts() {
        String s = TAG;
        android.util.Log.d(s, "\u7b2c\u4e00\u6b21\u8fdb\u6765 \uff0c\u68c0\u6d4b\u66f4\u65b0 \u548c \u662f\u5426\u6709\u95ea\u9000");
        android.content.pm.PackageInfo a = this.getWearfitApplication().packageInfo();
        Object a0 = com.wakeup.wearfit2.AppApplication.retrofit2.create(com.wakeup.wearfit2.kotlin.RetrofitService.class);
        int i = a.versionCode;
        String s0 = com.wakeup.wearfit2.util.CommonUtils.getLocale();
        StringBuilder a1 = new StringBuilder();
        a1.append("versionCode: ");
        a1.append(i);
        android.util.Log.i(s, a1.toString());
        StringBuilder a2 = new StringBuilder();
        a2.append("locale: ");
        a2.append(s0);
        android.util.Log.i(s, a2.toString());
        ((com.wakeup.wearfit2.kotlin.RetrofitService)a0).checkUpdate(i, s0, 1).enqueue((retrofit2.Callback)(Object)new com.wakeup.wearfit2.ui.activity.MainActivity$14(this, i));
    }
    
    private android.app.Dialog createVersionAlertDialog(String s, String s0) {
        android.content.pm.PackageManager a = this.getPackageManager();
        Object[] a0 = new Object[1];
        a0[0] = this.getPackageName();
        android.content.Intent a1 = new android.content.Intent("android.intent.action.VIEW", android.net.Uri.parse(String.format("market://details?id=%s", a0)));
        android.content.Intent a2 = new android.content.Intent("android.intent.action.VIEW", android.net.Uri.parse(s));
        com.wakeup.wearfit2.view.DialogBuilder a3 = com.wakeup.wearfit2.view.DialogBuilder.warn((android.content.Context)this, 2131886713, (CharSequence)(Object)"msg");
        a3.setMessage((CharSequence)(Object)new StringBuilder(s0));
        if (a.resolveActivity(a1, 0) != null) {
            a3.setPositiveButton(2131886512, (android.content.DialogInterface$OnClickListener)(Object)new com.wakeup.wearfit2.ui.activity.MainActivity$15(this, a1));
        }
        a3.setNegativeButton(2131886215, (android.content.DialogInterface$OnClickListener)null);
        return a3.create();
    }
    
    private void disconnectDevice() {
        com.wakeup.wearfit2.ble.WearfitService$LocalBinder a = this.localBinder;
        if (a != null) {
            a.disconnet();
        }
    }
    
    private void getDakaData() {
        android.util.Log.i(TAG, "\u83b7\u53d6\u6253\u5361\u6570\u636e");
        String s = com.wakeup.wearfit2.util.SPUtils.getString((android.content.Context)this, "token", "");
        Object a = com.wakeup.wearfit2.AppApplication.retrofit2.create(com.wakeup.wearfit2.kotlin.RetrofitService.class);
        String s0 = com.wakeup.wearfit2.util.CommonUtils.getLocale();
        ((com.wakeup.wearfit2.kotlin.RetrofitService)a).dakaInfo(s, (int)(System.currentTimeMillis() / 1000L), s0).enqueue((retrofit2.Callback)(Object)new com.wakeup.wearfit2.ui.activity.MainActivity$13(this));
    }
    
    private void hideAllFragment(androidx.fragment.app.FragmentTransaction a) {
        com.wakeup.wearfit2.ui.fragment.HomeFragment a0 = this.homeFragment;
        if (a0 != null) {
            a.hide((androidx.fragment.app.Fragment)a0);
        }
        com.wakeup.wearfit2.ui.fragment.MineFragment a1 = this.mineFragment;
        if (a1 != null) {
            a.hide((androidx.fragment.app.Fragment)a1);
        }
        com.wakeup.wearfit2.ui.quanzi.fragment.DiscoverFragment a2 = this.discoverFragment;
        if (a2 != null) {
            a.hide((androidx.fragment.app.Fragment)a2);
        }
    }
    
    private void init() {
        this.mhandler = new android.os.Handler();
        this.popupMenuUtil = com.wakeup.wearfit2.util.PopupMenuUtil.getInstance();
        com.wakeup.wearfit2.manager.ChannelManage.init();
        String[] a = new String[2];
        a[0] = "selected = ?";
        a[1] = "0";
        java.util.List a0 = org.litepal.LitePal.where(a).order("orderId").find(com.wakeup.wearfit2.bean.ChannelItem.class);
        this.otherChannelList = a0;
        if (a0.size() > 0) {
            this.channelID = ((com.wakeup.wearfit2.bean.ChannelItem)this.otherChannelList.get(0)).getType();
        }
        this.setBottomColor(this.channelID);
        com.wakeup.wearfit2.util.SPUtils.putInt((android.content.Context)this, com.wakeup.wearfit2.util.SPUtils.NEW_SMS_COUNT, 0);
        if (com.wakeup.wearfit2.util.SPUtils.getInt((android.content.Context)this, "targetCount") < 4000) {
            com.wakeup.wearfit2.util.SPUtils.getInt((android.content.Context)this, "targetCount", 8000);
        }
        this.mContext = this;
        this.initMainpanle();
        label0: if (com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected() && com.wakeup.wearfit2.AppApplication.band_type != 0) {
            int i = com.wakeup.wearfit2.AppApplication.band_type;
            label1: {
                if (i <= 29) {
                    break label1;
                }
                if (com.wakeup.wearfit2.AppApplication.band_type < 100) {
                    break label0;
                }
            }
            if (com.wakeup.wearfit2.AppApplication.band_type <= 199) {
                this.mhandler.postDelayed((Runnable)(Object)new com.wakeup.wearfit2.ui.activity.MainActivity$6(this), 500L);
            }
        }
        new com.wakeup.wearfit2.login.wechat.SysNet().adsControl((com.wakeup.wearfit2.login.wechat.SysNet$OnAdsControlCallBack)(Object)new com.wakeup.wearfit2.ui.activity.MainActivity$7(this));
        if (androidx.core.app.ActivityCompat.checkSelfPermission((android.content.Context)this, "android.permission.READ_PHONE_STATE") == 0) {
            android.util.Log.e("8888", "\u6709\u7535\u8bdd\u76d1\u542c\u6743\u9650");
            return;
        }
        android.util.Log.e("8888", "\u6c92\u6709\u7535\u8bdd\u76d1\u542c\u6743\u9650");
    }
    
    private void initAD() {
        com.wakeup.wearfit2.ad.ADHolder.showBannerAd((android.app.Activity)this, this.bannerContainer);
    }
    
    private void initGuide() {
        if (com.wakeup.wearfit2.util.SPUtils.getBoolean((android.content.Context)this, "is_last_guide", true)) {
            String s = java.util.Locale.getDefault().getLanguage();
            if (s.equals((Object)"zh")) {
                this.mIvGuide.setImageResource(2131230898);
            } else if (s.equals((Object)"el")) {
                this.mIvGuide.setVisibility(8);
            } else {
                this.mIvGuide.setImageResource(2131230899);
            }
            com.wakeup.wearfit2.util.SPUtils.putBoolean((android.content.Context)this, "is_last_guide", false);
            this.mIvGuide.setOnClickListener((android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.ui.activity.MainActivity$4(this));
        } else {
            this.mIvGuide.setVisibility(8);
        }
    }
    
    private void initMainpanle() {
        if (com.wakeup.wearfit2.util.SPUtils.getBoolean(this.mContext, "is_phone", false)) {
            this.setBottomColor(7);
        }
    }
    
    private void initPrompt() {
        if (com.wakeup.wearfit2.AppApplication.band_type == 115) {
            return;
        }
        android.view.View a = android.view.LayoutInflater.from((android.content.Context)this).inflate(2131493301, (android.view.ViewGroup)null);
        android.widget.PopupWindow a0 = new android.widget.PopupWindow(a, -1, -1, false);
        this.initText((android.widget.TextView)a.findViewById(2131297323));
        a0.setFocusable(true);
        a0.setOutsideTouchable(false);
        a0.setBackgroundDrawable((android.graphics.drawable.Drawable)new android.graphics.drawable.BitmapDrawable());
        a0.showAtLocation(this.findViewById(2131296353), 17, 0, 0);
        a0.setAnimationStyle(2131951853);
        this.backgroundAlpha(0.9f);
        a.findViewById(2131297554).setOnClickListener((android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.ui.activity.MainActivity$8(this, a0));
        a0.setOnDismissListener((android.widget.PopupWindow$OnDismissListener)(Object)new com.wakeup.wearfit2.ui.activity.MainActivity$9(this));
    }
    
    private void initSetting() {
        android.view.View a = android.view.LayoutInflater.from((android.content.Context)this).inflate(2131493302, (android.view.ViewGroup)null);
        android.widget.PopupWindow a0 = new android.widget.PopupWindow(a, -1, -1, false);
        android.widget.TextView a1 = (android.widget.TextView)a.findViewById(2131297323);
        a0.setFocusable(true);
        a0.setOutsideTouchable(false);
        a0.setBackgroundDrawable((android.graphics.drawable.Drawable)new android.graphics.drawable.BitmapDrawable());
        a0.showAtLocation(this.findViewById(2131296353), 17, 0, 0);
        a0.setAnimationStyle(2131951853);
        this.backgroundAlpha(0.9f);
        a.findViewById(2131297554).setOnClickListener((android.view.View$OnClickListener)(Object)new com.wakeup.wearfit2.ui.activity.MainActivity$10(this, a0));
        a0.setOnDismissListener((android.widget.PopupWindow$OnDismissListener)(Object)new com.wakeup.wearfit2.ui.activity.MainActivity$11(this));
    }
    
    private void initText(android.widget.TextView a) {
        android.text.SpannableString a0 = new android.text.SpannableString((CharSequence)(Object)this.getResources().getString(2131886840));
        java.util.regex.Matcher a1 = java.util.regex.Pattern.compile("wearfit1.0").matcher((CharSequence)(Object)a0);
        while(a1.find()) {
            int i = a1.start();
            int i0 = a1.end();
            a0.setSpan((Object)new android.text.style.ForegroundColorSpan(this.getResources().getColor(2131099977)), i, i0, 33);
        }
        a.setText((CharSequence)(Object)a0);
    }
    
    private static android.content.IntentFilter makeIntentFilter() {
        android.content.IntentFilter a = new android.content.IntentFilter();
        a.addAction("com.wakeup.wearfit2.BROADCAST_CONNECTION_STATE");
        a.addAction("com.wakeup.wearfit2.BROADCAST_DATA_AVAILABLE");
        a.addAction("com.wakeup.wearfit2.BROADCAST_DATA_SEND");
        a.addAction("com.wakeup.wearfit2.DEVICE_READY");
        return a;
    }
    
    private void methodRequiresTwoPermission() {
        java.util.ArrayList a = new java.util.ArrayList();
        a.add((Object)"android.permission.CAMERA");
        a.add((Object)"android.permission.WRITE_EXTERNAL_STORAGE");
        a.add((Object)"android.permission.READ_EXTERNAL_STORAGE");
        a.add((Object)"android.permission.ACCESS_COARSE_LOCATION");
        a.add((Object)"android.permission.ACCESS_FINE_LOCATION");
        a.add((Object)"android.permission.READ_CONTACTS");
        a.add((Object)"android.permission.READ_CALL_LOG");
        a.add((Object)"android.permission.CALL_PHONE");
        a.add((Object)"android.permission.RECEIVE_SMS");
        a.add((Object)"android.permission.READ_SMS");
        if (android.os.Build$VERSION.SDK_INT >= 26) {
            a.add((Object)"android.permission.ANSWER_PHONE_CALLS");
        }
        String[] a0 = new String[a.size()];
        int i = 0;
        for(; i < a.size(); i = i + 1) {
            a0[i] = (String)a.get(i);
        }
        if (pub.devrel.easypermissions.EasyPermissions.hasPermissions((android.content.Context)this, a0)) {
            return;
        }
        pub.devrel.easypermissions.EasyPermissions.requestPermissions((android.app.Activity)this, this.getString(2131886794), 1001, a0);
    }
    
    private void onDeviceConnected() {
        android.util.Log.i(TAG, "onDeviceConnected");
        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.getApplicationContext(), "hasmianyi", false);
        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.getApplicationContext(), "hastiwen", false);
        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.getApplicationContext(), "hasnewtiwen", false);
        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.getApplicationContext(), "hasviber", false);
        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.getApplicationContext(), "hasvkclient", false);
        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.getApplicationContext(), "hastelegram", false);
        com.wakeup.wearfit2.util.SPUtils.putBoolean(this.getApplicationContext(), "hasskype", false);
        this.saveMacAddress();
        this.connect_status.setText((CharSequence)(Object)this.getString((com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) ? 2131886297 : 2131887202));
    }
    
    private void onDeviceDisconnected() {
        android.util.Log.i(TAG, "onDeviceDisconnected");
        this.connect_status.setText((CharSequence)(Object)this.getString((com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) ? 2131886297 : 2131887202));
    }
    
    private void onDeviceReady() {
        android.util.Log.i(TAG, "onDeviceReady");
        com.wakeup.wearfit2.util.SPUtils.putInt((android.content.Context)this, "biaopan_num", 0);
    }
    
    private void saveMacAddress() {
        String s = com.wakeup.wearfit2.util.SPUtils.getString((android.content.Context)this, com.wakeup.wearfit2.util.SPUtils.DEVICE_ADDRESS, "");
        java.util.List a = org.litepal.LitePal.findAll(com.wakeup.wearfit2.bean.MacAddressBean.class, new long[0]);
        com.wakeup.wearfit2.bean.MacAddressBean a0 = new com.wakeup.wearfit2.bean.MacAddressBean();
        java.util.ArrayList a1 = new java.util.ArrayList();
        if (a.size() != 0) {
            Object a2 = a.iterator();
            while(((java.util.Iterator)a2).hasNext()) {
                ((java.util.List)(Object)a1).add((Object)((com.wakeup.wearfit2.bean.MacAddressBean)((java.util.Iterator)a2).next()).getMacAddress());
            }
            if (!((java.util.List)(Object)a1).contains((Object)s)) {
                a0.setMacAddress(s);
                a0.save();
            }
        } else {
            a0.setMacAddress(s);
            a0.save();
        }
    }
    
    private void setBottomColor(int i) {
        int i0 = this.flag;
        if (i0 == 1) {
            android.graphics.drawable.Drawable a = this.getResources().getDrawable(2131231367);
            this.rbHome.setCompoundDrawablesWithIntrinsicBounds((android.graphics.drawable.Drawable)null, a, (android.graphics.drawable.Drawable)null, (android.graphics.drawable.Drawable)null);
            android.graphics.drawable.Drawable a0 = this.getResources().getDrawable(2131231370);
            this.rbMine.setCompoundDrawablesWithIntrinsicBounds((android.graphics.drawable.Drawable)null, a0, (android.graphics.drawable.Drawable)null, (android.graphics.drawable.Drawable)null);
            if (this.currentFragment != 0) {
                this.rbHome.setTextColor(this.getResources().getColor(2131099981));
                this.rbMine.setTextColor(this.getResources().getColor(2131099997));
            } else {
                this.rbHome.setTextColor(this.getResources().getColor(2131099997));
                this.rbMine.setTextColor(this.getResources().getColor(2131099981));
            }
        } else if (i0 == 2) {
            android.graphics.drawable.Drawable a1 = this.getResources().getDrawable(2131231368);
            this.rbHome.setCompoundDrawablesWithIntrinsicBounds((android.graphics.drawable.Drawable)null, a1, (android.graphics.drawable.Drawable)null, (android.graphics.drawable.Drawable)null);
            android.graphics.drawable.Drawable a2 = this.getResources().getDrawable(2131231371);
            this.rbMine.setCompoundDrawablesWithIntrinsicBounds((android.graphics.drawable.Drawable)null, a2, (android.graphics.drawable.Drawable)null, (android.graphics.drawable.Drawable)null);
            if (this.currentFragment != 0) {
                this.rbHome.setTextColor(this.getResources().getColor(2131099981));
                this.rbMine.setTextColor(this.getResources().getColor(2131100003));
            } else {
                this.rbHome.setTextColor(this.getResources().getColor(2131100003));
                this.rbMine.setTextColor(this.getResources().getColor(2131099981));
            }
        } else if (i0 == 3) {
            android.graphics.drawable.Drawable a3 = this.getResources().getDrawable(2131231365);
            this.rbHome.setCompoundDrawablesWithIntrinsicBounds((android.graphics.drawable.Drawable)null, a3, (android.graphics.drawable.Drawable)null, (android.graphics.drawable.Drawable)null);
            android.graphics.drawable.Drawable a4 = this.getResources().getDrawable(2131231369);
            this.rbMine.setCompoundDrawablesWithIntrinsicBounds((android.graphics.drawable.Drawable)null, a4, (android.graphics.drawable.Drawable)null, (android.graphics.drawable.Drawable)null);
            if (this.currentFragment != 0) {
                this.rbHome.setTextColor(this.getResources().getColor(2131099981));
                this.rbMine.setTextColor(this.getResources().getColor(2131100001));
            } else {
                this.rbHome.setTextColor(this.getResources().getColor(2131100001));
                this.rbMine.setTextColor(this.getResources().getColor(2131099981));
            }
        } else if (i0 == 4) {
            android.graphics.drawable.Drawable a5 = this.getResources().getDrawable(2131231366);
            this.rbHome.setCompoundDrawablesWithIntrinsicBounds((android.graphics.drawable.Drawable)null, a5, (android.graphics.drawable.Drawable)null, (android.graphics.drawable.Drawable)null);
            android.graphics.drawable.Drawable a6 = this.getResources().getDrawable(2131231372);
            this.rbMine.setCompoundDrawablesWithIntrinsicBounds((android.graphics.drawable.Drawable)null, a6, (android.graphics.drawable.Drawable)null, (android.graphics.drawable.Drawable)null);
            int i1 = this.currentFragment;
            if (i1 != 0) {
                if (i1 != 1) {
                    this.rbHome.setTextColor(this.getResources().getColor(2131099981));
                    this.rbMine.setTextColor(this.getResources().getColor(2131099981));
                    this.rbMine.setTextColor(this.getResources().getColor(2131100000));
                } else {
                    this.rbHome.setTextColor(this.getResources().getColor(2131099981));
                    this.rbMine.setTextColor(this.getResources().getColor(2131099981));
                    this.rbMine.setTextColor(this.getResources().getColor(2131099981));
                }
            } else {
                this.rbHome.setTextColor(this.getResources().getColor(2131100000));
                this.rbMine.setTextColor(this.getResources().getColor(2131099981));
                this.rbMine.setTextColor(this.getResources().getColor(2131099981));
            }
        }
    }
    
    private void setColor(int i) {
        if (i == 1) {
            if (this.currentFragment != 0) {
                this.rbHome.setTextColor(this.getResources().getColor(2131099981));
                this.rbMine.setTextColor(this.getResources().getColor(2131099997));
            } else {
                this.rbHome.setTextColor(this.getResources().getColor(2131099997));
                this.rbMine.setTextColor(this.getResources().getColor(2131099981));
            }
        } else if (i == 2) {
            if (this.currentFragment != 0) {
                this.rbHome.setTextColor(this.getResources().getColor(2131099981));
                this.rbMine.setTextColor(this.getResources().getColor(2131100003));
            } else {
                this.rbHome.setTextColor(this.getResources().getColor(2131100003));
                this.rbMine.setTextColor(this.getResources().getColor(2131099981));
            }
        } else if (i == 3) {
            if (this.currentFragment != 0) {
                this.rbHome.setTextColor(this.getResources().getColor(2131099981));
                this.rbMine.setTextColor(this.getResources().getColor(2131100001));
            } else {
                this.rbHome.setTextColor(this.getResources().getColor(2131100001));
                this.rbMine.setTextColor(this.getResources().getColor(2131099981));
            }
        } else if (i == 4) {
            if (this.currentFragment != 0) {
                this.rbHome.setTextColor(this.getResources().getColor(2131099981));
                this.rbMine.setTextColor(this.getResources().getColor(2131100000));
            } else {
                this.rbHome.setTextColor(this.getResources().getColor(2131100000));
                this.rbMine.setTextColor(this.getResources().getColor(2131099981));
            }
        }
    }
    
    private void setLanguage() {
        java.util.Locale a = (android.os.Build$VERSION.SDK_INT < 24) ? android.content.res.Resources.getSystem().getConfiguration().locale : android.content.res.Resources.getSystem().getConfiguration().getLocales().get(0);
        String s = TAG;
        StringBuilder a0 = new StringBuilder();
        a0.append("locale/");
        a0.append(a.toString());
        android.util.Log.i(s, a0.toString());
        String s0 = a.getLanguage();
        boolean b = "zh_TW".equals((Object)a.toString());
        label0: {
            label1: {
                if (b) {
                    break label1;
                }
                if ("zh_HK".equals((Object)a.toString())) {
                    break label1;
                }
                if ("zh_MO".equals((Object)a.toString())) {
                    break label1;
                }
                if ("zh_CN_#Hant".equals((Object)a.toString())) {
                    break label1;
                }
                if ("zh_HK_#Hant".equals((Object)a.toString())) {
                    break label1;
                }
                if ("zh_TW_#Hant".equals((Object)a.toString())) {
                    break label1;
                }
                if (!"zh_MO_#Hant".equals((Object)a.toString())) {
                    break label0;
                }
            }
            s0 = "zh_TW";
        }
        com.wakeup.wearfit2.util.SPUtils.putString(this.getApplicationContext(), "language", s0);
    }
    
    private void setSelect(int i) {
        this.currentFragment = i;
        androidx.fragment.app.FragmentManager a = this.getSupportFragmentManager();
        androidx.fragment.app.FragmentTransaction a0 = a.beginTransaction();
        this.hideAllFragment(a0);
        label2: if (i == 0) {
            com.wakeup.wearfit2.ui.fragment.HomeFragment a1 = (com.wakeup.wearfit2.ui.fragment.HomeFragment)a.findFragmentByTag("homeFragment");
            this.homeFragment = a1;
            if (a1 != null) {
                a0.show((androidx.fragment.app.Fragment)a1);
            } else {
                com.wakeup.wearfit2.ui.fragment.HomeFragment a2 = com.wakeup.wearfit2.ui.fragment.HomeFragment.newInstance();
                this.homeFragment = a2;
                a0.add(2131296685, (androidx.fragment.app.Fragment)a2, "homeFragment");
            }
            this.setColor(this.flag);
            this.connect_status.setVisibility(0);
            this.rbHome.setChecked(true);
            boolean b = com.wakeup.wearfit2.AppApplication.hasContinueHr;
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
                    if (com.wakeup.wearfit2.AppApplication.hasContinueHrF) {
                        break label1;
                    }
                    if (com.wakeup.wearfit2.AppApplication.ox0a) {
                        break label1;
                    }
                    if (!com.wakeup.wearfit2.AppApplication.ox30) {
                        break label0;
                    }
                }
                this.measure.setVisibility(8);
                break label2;
            }
            this.measure.setVisibility(0);
        } else if (i == 1) {
            com.wakeup.wearfit2.ui.quanzi.fragment.DiscoverFragment a3 = (com.wakeup.wearfit2.ui.quanzi.fragment.DiscoverFragment)a.findFragmentByTag("quanziFragment");
            this.discoverFragment = a3;
            if (a3 != null) {
                a0.show((androidx.fragment.app.Fragment)a3);
            } else {
                com.wakeup.wearfit2.ui.quanzi.fragment.DiscoverFragment a4 = com.wakeup.wearfit2.ui.quanzi.fragment.DiscoverFragment.newInstance();
                this.discoverFragment = a4;
                a0.add(2131296685, (androidx.fragment.app.Fragment)a4, "quanziFragment");
            }
            this.setColor(this.flag);
            this.connect_status.setVisibility(4);
            this.rbDiscover.setChecked(true);
            this.measure.setVisibility(8);
        } else if (i == 2) {
            com.wakeup.wearfit2.ui.fragment.MineFragment a5 = (com.wakeup.wearfit2.ui.fragment.MineFragment)a.findFragmentByTag("mineFragment");
            this.mineFragment = a5;
            if (a5 != null) {
                a0.show((androidx.fragment.app.Fragment)a5);
            } else {
                com.wakeup.wearfit2.ui.fragment.MineFragment a6 = com.wakeup.wearfit2.ui.fragment.MineFragment.newInstance();
                this.mineFragment = a6;
                a0.add(2131296685, (androidx.fragment.app.Fragment)a6, "mineFragment");
            }
            this.setColor(this.flag);
            this.connect_status.setVisibility(4);
            this.rbMine.setChecked(true);
            this.measure.setVisibility(8);
        }
        a0.commit();
    }
    
    private void toggleNotificationListenerService() {
        android.content.pm.PackageManager a = this.getPackageManager();
        a.setComponentEnabledSetting(new android.content.ComponentName((android.content.Context)this, com.wakeup.wearfit2.service.AlertService.class), 2, 1);
        a.setComponentEnabledSetting(new android.content.ComponentName((android.content.Context)this, com.wakeup.wearfit2.service.AlertService.class), 1, 1);
    }
    
    public void backgroundAlpha(float f) {
        android.view.WindowManager$LayoutParams a = this.getWindow().getAttributes();
        a.alpha = f;
        this.getWindow().setAttributes(a);
    }
    
    protected void onActivityResult(int i, int i0, android.content.Intent a) {
        ((com.wakeup.wearfit2.ui.activity.splash.BaseADActivity)this).onActivityResult(i, i0, a);
    }
    
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
    
    public void onChangeColor(com.wakeup.wearfit2.bean.ChannelItem a) {
        int i = a.getType();
        this.channelID = i;
        this.setBottomColor(i);
    }
    
    public void onClick(android.view.View a) {
        int i = a.getId();
        label2: if (i == 2131296734) {
            if (com.wakeup.wearfit2.AppApplication.hasContinueHr) {
                this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.health.HealthWeeklyHrActivity.class));
            } else {
                boolean b = com.wakeup.wearfit2.AppApplication.hasContinueHrD;
                label0: {
                    label1: {
                        if (b) {
                            break label1;
                        }
                        if (!com.wakeup.wearfit2.AppApplication.ox0a) {
                            break label0;
                        }
                    }
                    this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.health.HealthWeeklyHrDActivity.class));
                    break label2;
                }
                if (com.wakeup.wearfit2.AppApplication.hasContinueHrE) {
                    this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.health.HealthWeeklyHrEActivity.class));
                } else if (com.wakeup.wearfit2.AppApplication.hasContinueHrF) {
                    this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.health.HealthWeeklyHrFActivity.class));
                } else if (com.wakeup.wearfit2.AppApplication.ox10) {
                    this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.health.HealthWeekly10Activity.class));
                } else {
                    this.startActivity(new android.content.Intent(this.mContext, com.wakeup.wearfit2.ui.activity.health.HealthWeeklyActivity.class));
                }
            }
        } else if (i == 2131297142) {
            this.startActivity(new android.content.Intent((android.content.Context)this, com.wakeup.wearfit2.ui.activity.about.MeasureActivity.class));
        } else {
            switch(i) {
                case 2131297345: {
                    this.setSelect(2);
                    break;
                }
                case 2131297344: {
                    this.setSelect(0);
                    break;
                }
                case 2131297343: {
                    this.setSelect(1);
                    break;
                }
            }
        }
    }
    
    protected void onCreate(android.os.Bundle a) {
        ((com.wakeup.wearfit2.ui.activity.splash.BaseADActivity)this).onCreate(a);
        this.setContentView(2131492998);
        butterknife.ButterKnife.bind((android.app.Activity)this);
        org.greenrobot.eventbus.EventBus.getDefault().register((Object)this);
        String s = TAG;
        android.util.Log.e(s, "onCreate");
        if (a != null) {
            android.util.Log.i(s, "savedInstanceState != null");
            this.currentFragment = a.getInt("current_fragment");
            StringBuilder a0 = new StringBuilder();
            a0.append("currentFragment ");
            a0.append(this.currentFragment);
            android.util.Log.i(s, a0.toString());
        } else {
            android.util.Log.i(s, "savedInstanceState == null");
            this.checkAlerts();
        }
        this.setSelect(this.currentFragment);
        this.toggleNotificationListenerService();
        this.init();
        this.initGuide();
        this.initAD();
        new android.content.IntentFilter().addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        this.registerReceiver(this.mCommonBroadcastReceiver, com.wakeup.wearfit2.ui.activity.MainActivity.makeIntentFilter());
        String s0 = com.wakeup.wearfit2.util.SPUtils.getString((android.content.Context)this, com.wakeup.wearfit2.util.SPUtils.DEVICE_ADDRESS, "");
        String s1 = com.wakeup.wearfit2.util.SPUtils.getString((android.content.Context)this, com.wakeup.wearfit2.util.SPUtils.DEVICE_NAME, "");
        android.util.Log.i(s, "Creating service...");
        android.content.Intent a1 = new android.content.Intent((android.content.Context)this, com.wakeup.wearfit2.ble.WearfitService.class);
        a1.putExtra("com.wakeup.wearfit2.EXTRA_DEVICE_ADDRESS", s0);
        a1.putExtra("com.wakeup.wearfit2.EXTRA_DEVICE_NAME", s1);
        androidx.core.content.ContextCompat.startForegroundService((android.content.Context)this, a1);
        android.util.Log.i(s, "Binding to the service...");
        this.bindService(a1, this.mServiceConnection, 0);
        this.setLanguage();
        android.util.Log.i(s, "onCreate");
        this.getDakaData();
        if (com.wakeup.wearfit2.util.SPUtils.getBoolean((android.content.Context)this, "first_open", true)) {
            this.startActivity(new android.content.Intent((android.content.Context)this, com.wakeup.wearfit2.ui.activity.user.PersonalDetailActivity.class));
            com.wakeup.wearfit2.util.SPUtils.putBoolean(this.getApplicationContext(), "first_open", false);
        }
        cn.jpush.im.android.api.JMessageClient.getUserInfo("ranbo", (cn.jpush.im.android.api.callback.GetUserInfoCallback)new com.wakeup.wearfit2.ui.activity.MainActivity$2(this));
        if (!this.getSharedPreferences(com.wakeup.wearfit2.util.SPUtils.PREFERENCE_NAME, 0).getBoolean("WhiteList", false)) {
            com.wakeup.wearfit2.ui.widge.dialog.WhiteListTipDialog.showWhiteListTipDialog((android.content.Context)this, (android.app.Activity)this);
        }
    }
    
    protected android.app.Dialog onCreateDialog(int i, android.os.Bundle a) {
        if (i != 0) {
            throw new IllegalArgumentException();
        }
        return this.createVersionAlertDialog(a.getString("url"), a.getString("msg"));
    }
    
    protected void onDestroy() {
        this.unregisterReceiver(this.mCommonBroadcastReceiver);
        ((com.wakeup.wearfit2.ui.activity.splash.BaseADActivity)this).onDestroy();
        this.mhandler.removeCallbacksAndMessages((Object)null);
        org.greenrobot.eventbus.EventBus.getDefault().unregister((Object)this);
        android.util.Log.e(TAG, "onDestroy.");
    }
    
    public void onEventMainThread(com.wakeup.wearfit2.model.event.BaseEvent a) {
        if (com.wakeup.wearfit2.ui.activity.MainActivity$16.$SwitchMap$com$wakeup$wearfit2$model$event$BaseEvent$EventType[a.getEventType().ordinal()] == 1) {
            android.util.Log.i(TAG, "BAND_VERSION_GOT from mainactivity");
            if (com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected()) {
                label3: if (com.wakeup.wearfit2.AppApplication.band_type != 0) {
                    int i = com.wakeup.wearfit2.AppApplication.band_type;
                    label4: {
                        if (i <= 29) {
                            break label4;
                        }
                        if (com.wakeup.wearfit2.AppApplication.band_type < 100) {
                            break label3;
                        }
                    }
                    if (com.wakeup.wearfit2.AppApplication.band_type <= 199) {
                        this.mhandler.postDelayed((Runnable)(Object)new com.wakeup.wearfit2.ui.activity.MainActivity$12(this), 2000L);
                    }
                }
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
                            if (com.wakeup.wearfit2.AppApplication.hasContinueHrF) {
                                break label1;
                            }
                            if (com.wakeup.wearfit2.AppApplication.ox0a) {
                                break label1;
                            }
                            if (!com.wakeup.wearfit2.AppApplication.ox30) {
                                break label0;
                            }
                        }
                        this.measure.setVisibility(8);
                        break label2;
                    }
                    this.measure.setVisibility(0);
                }
                if (com.wakeup.wearfit2.AppApplication.hasEcg) {
                    this.healthWeek.setVisibility(0);
                } else {
                    this.healthWeek.setVisibility(8);
                }
            }
        }
    }
    
    protected void onPause() {
        ((com.wakeup.wearfit2.ui.activity.splash.BaseADActivity)this).onPause();
        android.util.Log.e(TAG, "onPause: ");
    }
    
    public void onRequestPermissionsResult(int i, String[] a, int[] a0) {
        ((com.wakeup.wearfit2.ui.activity.splash.BaseADActivity)this).onRequestPermissionsResult(i, a, a0);
        Object[] a1 = new Object[1];
        a1[0] = this;
        pub.devrel.easypermissions.EasyPermissions.onRequestPermissionsResult(i, a, a0, a1);
    }
    
    protected void onResume() {
        ((com.wakeup.wearfit2.ui.activity.splash.BaseADActivity)this).onResume();
        android.util.Log.e(TAG, "onResume");
        label0: if (com.wakeup.wearfit2.util.BleUtil.getInstance().isConnected() && com.wakeup.wearfit2.AppApplication.band_type != 0) {
            int i = com.wakeup.wearfit2.AppApplication.band_type;
            label1: {
                if (i <= 29) {
                    break label1;
                }
                if (com.wakeup.wearfit2.AppApplication.band_type < 100) {
                    break label0;
                }
            }
            if (com.wakeup.wearfit2.AppApplication.band_type <= 199) {
                this.mhandler.postDelayed((Runnable)(Object)new com.wakeup.wearfit2.ui.activity.MainActivity$5(this), 500L);
            }
        }
        com.wakeup.wearfit2.ui.widge.dialog.WhiteListTipDialog a = com.wakeup.wearfit2.ui.widge.dialog.WhiteListTipDialog.getInstance();
        if (a != null && a.isShowing()) {
            a.onResume();
        }
    }
    
    protected void onSaveInstanceState(android.os.Bundle a) {
        ((com.wakeup.wearfit2.ui.activity.splash.BaseADActivity)this).onSaveInstanceState(a);
        String s = TAG;
        StringBuilder a0 = new StringBuilder();
        a0.append("onSaveInstanceState");
        a0.append(this.currentFragment);
        android.util.Log.i(s, a0.toString());
        a.putInt("current_fragment", this.currentFragment);
    }
    
    protected void onStart() {
        ((com.wakeup.wearfit2.ui.activity.splash.BaseADActivity)this).onStart();
        this.bindService(new android.content.Intent((android.content.Context)this, com.wakeup.wearfit2.ble.WearfitService.class), this.mServiceConnection, 0);
        android.util.Log.i(TAG, "onStart--> bindService");
    }
    
    protected void onStop() {
        ((com.wakeup.wearfit2.ui.activity.splash.BaseADActivity)this).onStop();
        String s = TAG;
        android.util.Log.e(s, "onStop: ");
        com.wakeup.wearfit2.ble.WearfitService a = this.mService;
        try {
            if (a != null) {
                a.setActivityIsChangingConfiguration(this.isChangingConfigurations());
            }
            this.unbindService(this.mServiceConnection);
            this.mService = null;
            android.util.Log.i(s, "onStop--> Activity unbound from the service");
            this.localBinder = null;
            this.mDeviceName = null;
        } catch(IllegalArgumentException ignoredException) {
        }
    }
    
    public void popClose() {
        if (com.wakeup.wearfit2.util.SPUtils.getBoolean((android.content.Context)this, "is_phone")) {
            this.setBottomColor(6);
        } else {
            this.setBottomColor(this.channelID);
        }
    }
    
    public void popOpen() {
    }
}
