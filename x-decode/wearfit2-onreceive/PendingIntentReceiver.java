package no.nordicsemi.android.support.v18.scanner;

public class PendingIntentReceiver extends android.content.BroadcastReceiver {
    final static String ACTION = "no.nordicsemi.android.support.v18.ACTION_FOUND";
    final static String EXTRA_FILTERS = "no.nordicsemi.android.support.v18.EXTRA_FILTERS";
    final static String EXTRA_MATCH_LOST_INTERVAL = "no.nordicsemi.android.support.v18.EXTRA_MATCH_LOST_INTERVAL";
    final static String EXTRA_MATCH_LOST_TIMEOUT = "no.nordicsemi.android.support.v18.EXTRA_MATCH_LOST_TIMEOUT";
    final static String EXTRA_MATCH_MODE = "no.nordicsemi.android.support.v18.EXTRA_MATCH_MODE";
    final static String EXTRA_NUM_OF_MATCHES = "no.nordicsemi.android.support.v18.EXTRA_NUM_OF_MATCHES";
    final static String EXTRA_PENDING_INTENT = "no.nordicsemi.android.support.v18.EXTRA_PENDING_INTENT";
    final static String EXTRA_SETTINGS = "no.nordicsemi.android.support.v18.EXTRA_SETTINGS";
    final static String EXTRA_USE_HARDWARE_BATCHING = "no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_BATCHING";
    final static String EXTRA_USE_HARDWARE_CALLBACK_TYPES = "no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_CALLBACK_TYPES";
    final static String EXTRA_USE_HARDWARE_FILTERING = "no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_FILTERING";
    
    public PendingIntentReceiver() {
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat a1 = null;
        Throwable a2 = null;
        label0: {
            label2: {
                if (a != null && a0 != null) {
                    android.app.PendingIntent a3 = (android.app.PendingIntent)(Object)a0.getParcelableExtra("no.nordicsemi.android.support.v18.EXTRA_PENDING_INTENT");
                    if (a3 == null) {
                        return;
                    }
                    java.util.ArrayList a4 = a0.getParcelableArrayListExtra("no.nordicsemi.android.support.v18.EXTRA_FILTERS");
                    android.bluetooth.le.ScanSettings a5 = (android.bluetooth.le.ScanSettings)(Object)a0.getParcelableExtra("no.nordicsemi.android.support.v18.EXTRA_SETTINGS");
                    if (a4 != null && a5 != null) {
                        no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplOreo$PendingIntentExecutorWrapper a6 = null;
                        boolean b = a0.getBooleanExtra("no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_BATCHING", true);
                        boolean b0 = a0.getBooleanExtra("no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_FILTERING", true);
                        boolean b1 = a0.getBooleanExtra("no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_CALLBACK_TYPES", true);
                        long j = a0.getLongExtra("no.nordicsemi.android.support.v18.EXTRA_MATCH_LOST_TIMEOUT", 10000L);
                        long j0 = a0.getLongExtra("no.nordicsemi.android.support.v18.EXTRA_MATCH_LOST_INTERVAL", 10000L);
                        int i = a0.getIntExtra("no.nordicsemi.android.support.v18.EXTRA_MATCH_MODE", 1);
                        int i0 = a0.getIntExtra("no.nordicsemi.android.support.v18.EXTRA_NUM_OF_MATCHES", 3);
                        no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplOreo a7 = (no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplOreo)no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat.getScanner();
                        a1 = a7;
                        java.util.ArrayList a8 = a7.fromNativeScanFilters((java.util.List)(Object)a4);
                        no.nordicsemi.android.support.v18.scanner.ScanSettings a9 = a7.fromNativeScanSettings(a5, b, b0, b1, j, j0, i, i0);
                        android.bluetooth.BluetoothAdapter a10 = android.bluetooth.BluetoothAdapter.getDefaultAdapter();
                        boolean b2 = a10.isOffloadedScanBatchingSupported();
                        boolean b3 = a10.isOffloadedFilteringSupported();
                        /*monenter(a1)*/;
                        try {
                            label1: {
                                try {
                                    a6 = a7.getWrapper(a3);
                                    break label1;
                                } catch(IllegalStateException ignoredException) {
                                }
                                /*monexit(a1)*/;
                                break label2;
                            }
                            if (a6 == null) {
                                a6 = new no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplOreo$PendingIntentExecutorWrapper(b2, b3, (java.util.List)(Object)a8, a9, a3);
                                a7.addWrapper(a3, a6);
                            }
                            /*monexit(a1)*/;
                        } catch(Throwable a11) {
                            a2 = a11;
                            break label0;
                        }
                        a6.executor.setTemporaryContext(a);
                        java.util.ArrayList a12 = a0.getParcelableArrayListExtra("android.bluetooth.le.extra.LIST_SCAN_RESULT");
                        if (a12 == null) {
                            int i1 = a0.getIntExtra("android.bluetooth.le.extra.ERROR_CODE", 0);
                            if (i1 != 0) {
                                a6.handleScanError(i1);
                            }
                        } else {
                            java.util.ArrayList a13 = a7.fromNativeScanResults((java.util.List)(Object)a12);
                            if (a9.getReportDelayMillis() <= 0L) {
                                if (!a13.isEmpty()) {
                                    a6.handleScanResult(a0.getIntExtra("android.bluetooth.le.extra.CALLBACK_TYPE", 1), (no.nordicsemi.android.support.v18.scanner.ScanResult)a13.get(0));
                                }
                            } else {
                                a6.handleScanResults((java.util.List)(Object)a13);
                            }
                        }
                        a6.executor.setTemporaryContext((android.content.Context)null);
                        return;
                    }
                }
                return;
            }
            return;
        }
        while(true) {
            try {
                /*monexit(a1)*/;
            } catch(IllegalMonitorStateException | NullPointerException a14) {
                Throwable a15 = a14;
                a2 = a15;
                continue;
            }
            throw a2;
        }
    }
}
