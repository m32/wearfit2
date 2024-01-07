package no.nordicsemi.android.dfu;

class DfuServiceListenerHelper$LogBroadcastReceiver extends android.content.BroadcastReceiver {
    private no.nordicsemi.android.dfu.DfuLogListener mGlobalLogListener;
    final private java.util.Map mListeners;
    
    private DfuServiceListenerHelper$LogBroadcastReceiver() {
        this.mListeners = (java.util.Map)(Object)new java.util.HashMap();
    }
    
    DfuServiceListenerHelper$LogBroadcastReceiver(no.nordicsemi.android.dfu.DfuServiceListenerHelper$1 a) {
        this();
    }
    
    static void access$600(no.nordicsemi.android.dfu.DfuServiceListenerHelper$LogBroadcastReceiver a, no.nordicsemi.android.dfu.DfuLogListener a0) {
        a.setLogListener(a0);
    }
    
    static void access$700(no.nordicsemi.android.dfu.DfuServiceListenerHelper$LogBroadcastReceiver a, String s, no.nordicsemi.android.dfu.DfuLogListener a0) {
        a.setLogListener(s, a0);
    }
    
    static boolean access$800(no.nordicsemi.android.dfu.DfuServiceListenerHelper$LogBroadcastReceiver a, no.nordicsemi.android.dfu.DfuLogListener a0) {
        return a.removeLogListener(a0);
    }
    
    private boolean removeLogListener(no.nordicsemi.android.dfu.DfuLogListener a) {
        if (this.mGlobalLogListener == a) {
            this.mGlobalLogListener = null;
        }
        java.util.Iterator a0 = this.mListeners.entrySet().iterator();
        Object a1 = a;
        Object a2 = a0;
        label3: while(true) {
            if (((java.util.Iterator)a2).hasNext()) {
                Object a3 = ((java.util.Iterator)a2).next();
                if (((java.util.Map$Entry)a3).getValue() != a1) {
                    continue label3;
                }
                this.mListeners.remove(((java.util.Map$Entry)a3).getKey());
            }
            Object a4 = this.mListeners.entrySet().iterator();
            while(true) {
                boolean b = false;
                if (((java.util.Iterator)a4).hasNext()) {
                    Object a5 = ((java.util.Iterator)a4).next();
                    if (((java.util.Map$Entry)a5).getValue() != a1) {
                        continue;
                    }
                    this.mListeners.remove(((java.util.Map$Entry)a5).getKey());
                }
                no.nordicsemi.android.dfu.DfuLogListener a6 = this.mGlobalLogListener;
                label2: {
                    label0: {
                        label1: {
                            if (a6 != null) {
                                break label1;
                            }
                            if (this.mListeners.isEmpty()) {
                                break label0;
                            }
                        }
                        b = false;
                        break label2;
                    }
                    b = true;
                }
                return b;
            }
        }
    }
    
    private void setLogListener(String s, no.nordicsemi.android.dfu.DfuLogListener a) {
        this.mListeners.put((Object)s, (Object)a);
        this.mListeners.put((Object)no.nordicsemi.android.dfu.DfuServiceListenerHelper.access$000(s), (Object)a);
    }
    
    private void setLogListener(no.nordicsemi.android.dfu.DfuLogListener a) {
        this.mGlobalLogListener = a;
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        String s = a0.getStringExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS");
        no.nordicsemi.android.dfu.DfuLogListener a1 = this.mGlobalLogListener;
        Object a2 = this.mListeners.get((Object)s);
        if (a1 == null && a2 == null) {
            return;
        }
        int i = a0.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_LOG_LEVEL", 0);
        String s0 = a0.getStringExtra("no.nordicsemi.android.dfu.extra.EXTRA_LOG_INFO");
        if (a1 != null) {
            a1.onLogEvent(s, i, s0);
        }
        if (a2 != null) {
            ((no.nordicsemi.android.dfu.DfuLogListener)a2).onLogEvent(s, i, s0);
        }
    }
}
