package no.nordicsemi.android.dfu;

class DfuServiceListenerHelper$ProgressBroadcastsReceiver extends android.content.BroadcastReceiver {
    private no.nordicsemi.android.dfu.DfuProgressListener mGlobalProgressListener;
    final private java.util.Map mListeners;
    
    private DfuServiceListenerHelper$ProgressBroadcastsReceiver() {
        this.mListeners = (java.util.Map)(Object)new java.util.HashMap();
    }
    
    DfuServiceListenerHelper$ProgressBroadcastsReceiver(no.nordicsemi.android.dfu.DfuServiceListenerHelper$1 a) {
        this();
    }
    
    static void access$200(no.nordicsemi.android.dfu.DfuServiceListenerHelper$ProgressBroadcastsReceiver a, no.nordicsemi.android.dfu.DfuProgressListener a0) {
        a.setProgressListener(a0);
    }
    
    static void access$300(no.nordicsemi.android.dfu.DfuServiceListenerHelper$ProgressBroadcastsReceiver a, String s, no.nordicsemi.android.dfu.DfuProgressListener a0) {
        a.setProgressListener(s, a0);
    }
    
    static boolean access$400(no.nordicsemi.android.dfu.DfuServiceListenerHelper$ProgressBroadcastsReceiver a, no.nordicsemi.android.dfu.DfuProgressListener a0) {
        return a.removeProgressListener(a0);
    }
    
    private boolean removeProgressListener(no.nordicsemi.android.dfu.DfuProgressListener a) {
        if (this.mGlobalProgressListener == a) {
            this.mGlobalProgressListener = null;
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
                no.nordicsemi.android.dfu.DfuProgressListener a6 = this.mGlobalProgressListener;
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
    
    private void setProgressListener(String s, no.nordicsemi.android.dfu.DfuProgressListener a) {
        this.mListeners.put((Object)s, (Object)a);
        this.mListeners.put((Object)no.nordicsemi.android.dfu.DfuServiceListenerHelper.access$000(s), (Object)a);
    }
    
    private void setProgressListener(no.nordicsemi.android.dfu.DfuProgressListener a) {
        this.mGlobalProgressListener = a;
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        String s = a0.getStringExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS");
        if (s == null) {
            return;
        }
        no.nordicsemi.android.dfu.DfuProgressListener a1 = this.mGlobalProgressListener;
        Object a2 = this.mListeners.get((Object)s);
        if (a1 == null && a2 == null) {
            return;
        }
        String s0 = a0.getAction();
        if (s0 == null) {
            return;
        }
        s0.hashCode();
        if (s0.equals((Object)"no.nordicsemi.android.dfu.broadcast.BROADCAST_PROGRESS")) {
            int i = a0.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_DATA", 0);
            float f = a0.getFloatExtra("no.nordicsemi.android.dfu.extra.EXTRA_SPEED_B_PER_MS", 0.0f);
            float f0 = a0.getFloatExtra("no.nordicsemi.android.dfu.extra.EXTRA_AVG_SPEED_B_PER_MS", 0.0f);
            int i0 = a0.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_PART_CURRENT", 0);
            int i1 = a0.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_PARTS_TOTAL", 0);
            switch(i) {
                case -1: {
                    if (a1 != null) {
                        a1.onDeviceConnecting(s);
                    }
                    if (a2 == null) {
                        break;
                    }
                    ((no.nordicsemi.android.dfu.DfuProgressListener)a2).onDeviceConnecting(s);
                    break;
                }
                case -2: {
                    if (a1 != null) {
                        a1.onDeviceConnected(s);
                        a1.onDfuProcessStarting(s);
                    }
                    if (a2 == null) {
                        break;
                    }
                    ((no.nordicsemi.android.dfu.DfuProgressListener)a2).onDeviceConnected(s);
                    ((no.nordicsemi.android.dfu.DfuProgressListener)a2).onDfuProcessStarting(s);
                    break;
                }
                case -3: {
                    if (a1 != null) {
                        a1.onEnablingDfuMode(s);
                    }
                    if (a2 == null) {
                        break;
                    }
                    ((no.nordicsemi.android.dfu.DfuProgressListener)a2).onEnablingDfuMode(s);
                    break;
                }
                case -4: {
                    if (a1 != null) {
                        a1.onFirmwareValidating(s);
                    }
                    if (a2 == null) {
                        break;
                    }
                    ((no.nordicsemi.android.dfu.DfuProgressListener)a2).onFirmwareValidating(s);
                    break;
                }
                case -5: {
                    if (a1 != null) {
                        a1.onDeviceDisconnecting(s);
                    }
                    if (a2 == null) {
                        break;
                    }
                    ((no.nordicsemi.android.dfu.DfuProgressListener)a2).onDeviceDisconnecting(s);
                    break;
                }
                case -6: {
                    if (a1 != null) {
                        a1.onDeviceDisconnected(s);
                        a1.onDfuCompleted(s);
                    }
                    if (a2 == null) {
                        break;
                    }
                    ((no.nordicsemi.android.dfu.DfuProgressListener)a2).onDeviceDisconnected(s);
                    ((no.nordicsemi.android.dfu.DfuProgressListener)a2).onDfuCompleted(s);
                    break;
                }
                case -7: {
                    if (a1 != null) {
                        a1.onDeviceDisconnected(s);
                        a1.onDfuAborted(s);
                    }
                    if (a2 == null) {
                        break;
                    }
                    ((no.nordicsemi.android.dfu.DfuProgressListener)a2).onDeviceDisconnected(s);
                    ((no.nordicsemi.android.dfu.DfuProgressListener)a2).onDfuAborted(s);
                    break;
                }
                default: {
                    if (i == 0) {
                        if (a1 != null) {
                            a1.onDfuProcessStarted(s);
                        }
                        if (a2 != null) {
                            ((no.nordicsemi.android.dfu.DfuProgressListener)a2).onDfuProcessStarted(s);
                        }
                    }
                    if (a1 != null) {
                        a1.onProgressChanged(s, i, f, f0, i0, i1);
                    }
                    if (a2 != null) {
                        ((no.nordicsemi.android.dfu.DfuProgressListener)a2).onProgressChanged(s, i, f, f0, i0, i1);
                    }
                }
            }
        } else if (s0.equals((Object)"no.nordicsemi.android.dfu.broadcast.BROADCAST_ERROR")) {
            int i2 = a0.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_DATA", 0);
            int i3 = a0.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_ERROR_TYPE", 0);
            if (a1 != null) {
                a1.onDeviceDisconnected(s);
            }
            if (a2 != null) {
                ((no.nordicsemi.android.dfu.DfuProgressListener)a2).onDeviceDisconnected(s);
            }
            if (i3 == 1) {
                if (a1 != null) {
                    a1.onError(s, i2, i3, no.nordicsemi.android.error.GattError.parseConnectionError(i2));
                }
                if (a2 != null) {
                    ((no.nordicsemi.android.dfu.DfuProgressListener)a2).onError(s, i2, i3, no.nordicsemi.android.error.GattError.parseConnectionError(i2));
                }
            } else if (i3 == 3) {
                if (a1 != null) {
                    a1.onError(s, i2, i3, no.nordicsemi.android.error.GattError.parseDfuRemoteError(i2));
                }
                if (a2 != null) {
                    ((no.nordicsemi.android.dfu.DfuProgressListener)a2).onError(s, i2, i3, no.nordicsemi.android.error.GattError.parseDfuRemoteError(i2));
                }
            } else {
                if (a1 != null) {
                    a1.onError(s, i2, i3, no.nordicsemi.android.error.GattError.parse(i2));
                }
                if (a2 != null) {
                    ((no.nordicsemi.android.dfu.DfuProgressListener)a2).onError(s, i2, i3, no.nordicsemi.android.error.GattError.parse(i2));
                }
            }
        }
    }
}
