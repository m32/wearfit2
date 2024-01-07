package com.wakeup.wearfit2.kotlin.activity;

class SwitchBgActivity3$2 extends android.content.BroadcastReceiver {
    final com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3 this$0;
    
    SwitchBgActivity3$2(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3 a) {
        this.this$0 = a;
        super();
    }
    
    public void onReceive(android.content.Context a, android.content.Intent a0) {
        String s = a0.getAction();
        if ("com.wakeup.wearfit2.le.ACTION_GATT_CONNECTED".equals((Object)s)) {
            com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.mConnected = true;
            android.util.Log.e(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), "connected");
            android.widget.Toast.makeText((android.content.Context)this.this$0, 2131886297, 0).show();
        } else if ("com.wakeup.wearfit2.le.ACTION_GATT_DISCONNECTED".equals((Object)s)) {
            android.util.Log.e(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), "ACTION_GATT_DISCONNECTED");
            if (com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$100(this.this$0) != null) {
                com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$100(this.this$0).close();
            }
            com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.mConnected = false;
            com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$200(this.this$0);
            if (com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$500(this.this$0) && !com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$200(this.this$0)) {
                com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$600(this.this$0, true);
            }
        } else if ("com.wakeup.wearfit2.le.ACTION_GATT_SERVICES_DISCOVERED".equals((Object)s)) {
            android.util.Log.i(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), "ACTION_GATT_SERVICES_DISCOVERED");
            com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3 a1 = this.this$0;
            com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$702(a1, com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$100(a1).getSupportedGattServices());
            if (com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$700(this.this$0) == null) {
                return;
            }
            Object a2 = com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$700(this.this$0).iterator();
            while(((java.util.Iterator)a2).hasNext()) {
                java.util.List a3 = ((android.bluetooth.BluetoothGattService)((java.util.Iterator)a2).next()).getCharacteristics();
                java.util.ArrayList a4 = new java.util.ArrayList();
                Object a5 = a3.iterator();
                while(((java.util.Iterator)a5).hasNext()) {
                    android.bluetooth.BluetoothGattCharacteristic a6 = (android.bluetooth.BluetoothGattCharacteristic)((java.util.Iterator)a5).next();
                    String s0 = com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000();
                    StringBuilder a7 = new StringBuilder();
                    a7.append("uuid:");
                    a7.append(a6.getUuid().toString());
                    android.util.Log.e(s0, a7.toString());
                    if (com.wakeup.wearfit2.service.BluetoothLeService.UUID_OTA_TX_DAT.toString().equals((Object)a6.getUuid().toString())) {
                        this.this$0.ota_tx_dat_charac = a6;
                        if ((this.this$0.ota_tx_dat_charac.getProperties() & 16) > 0) {
                            if (com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$100(this.this$0).setCharacteristicNotification(a6, true)) {
                                android.util.Log.i(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), "Set Notify Success");
                            } else {
                                android.util.Log.i(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), "Notify failed!!");
                                android.widget.Toast.makeText(this.this$0.getApplicationContext(), (CharSequence)(Object)"Notify failed!!", 0).show();
                            }
                        }
                    } else if (com.wakeup.wearfit2.service.BluetoothLeService.UUID_OTA_RX_DAT.toString().equals((Object)a6.getUuid().toString())) {
                        try {
                            Thread.sleep(350L);
                        } catch(InterruptedException a8) {
                            a8.printStackTrace();
                        }
                        this.this$0.ota_rx_dat_charac = a6;
                        if ((this.this$0.ota_rx_dat_charac.getProperties() & 16) > 0) {
                            if (com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$100(this.this$0).setCharacteristicNotification(a6, true)) {
                                android.util.Log.i(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), "Set Notify Success");
                            } else {
                                android.util.Log.i(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), "Notify failed!!");
                            }
                        }
                    } else if (com.wakeup.wearfit2.service.BluetoothLeService.UUID_OTA_RX_CMD.toString().equals((Object)a6.getUuid().toString())) {
                        this.this$0.ota_rx_cmd_charac = a6;
                        if ((this.this$0.ota_rx_cmd_charac.getProperties() & 16) > 0) {
                            if (com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$100(this.this$0).setCharacteristicNotification(a6, true)) {
                                android.util.Log.i(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), "Set Notify Success");
                            } else {
                                android.util.Log.i(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), "Notify failed!!");
                                android.widget.Toast.makeText(this.this$0.getApplicationContext(), (CharSequence)(Object)"Notify failed!!", 0).show();
                            }
                        }
                    } else if (com.wakeup.wearfit2.service.BluetoothLeService.UUID_OTA_TX_CMD.toString().equals((Object)a6.getUuid().toString())) {
                        this.this$0.ota_tx_cmd_charac = a6;
                        if ((this.this$0.ota_tx_cmd_charac.getProperties() & 16) > 0) {
                            if (com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$100(this.this$0).setCharacteristicNotification(a6, true)) {
                                android.util.Log.i(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), "Set Notify Success");
                            } else {
                                android.util.Log.i(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), "Notify failed!!");
                            }
                        }
                    } else if (com.wakeup.wearfit2.service.BluetoothLeService.UUID_ISP_TX_CMD.toString().equals((Object)a6.getUuid().toString())) {
                        this.this$0.ota_tx_cmd_charac = a6;
                        if (this.this$0.ota_tx_cmd_charac.getProperties() == 16) {
                            if (com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$100(this.this$0).setCharacteristicNotification(this.this$0.ota_tx_cmd_charac, true)) {
                                android.util.Log.i(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), "Set Notify Success");
                            } else {
                                android.util.Log.i(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), "Notify failed!!");
                            }
                        }
                    } else if (com.wakeup.wearfit2.service.BluetoothLeService.UUID_ISP_RX_CMD.toString().equals((Object)a6.getUuid().toString())) {
                        this.this$0.ota_rx_cmd_charac = a6;
                        if (this.this$0.ota_rx_cmd_charac.getProperties() == 16) {
                            if (com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$100(this.this$0).setCharacteristicNotification(a6, true)) {
                                android.util.Log.i(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), "Set Notify Success");
                            } else {
                                android.util.Log.i(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), "Notify failed!!");
                                android.widget.Toast.makeText(this.this$0.getApplicationContext(), (CharSequence)(Object)"Notify failed!!", 0).show();
                            }
                        }
                    }
                }
            }
            if (com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$500(this.this$0)) {
                com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$900(this.this$0).postDelayed((Runnable)(Object)new com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3$2$1(this), 2000L);
            }
        } else if ("com.wakeup.wearfit2.BROADCAST_DATA_AVAILABLE".equals((Object)s)) {
            byte[] a9 = a0.getByteArrayExtra("com.wakeup.wearfit2.EXTRA_DATA");
            if (a9 == null) {
                return;
            }
            String s1 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToHexStr(a9);
            java.util.List a10 = com.wakeup.wearfit2.util.DataHandlerUtils.bytesToArrayList(a9);
            if (s1 != null) {
                if (((Integer)a10.get(0)).intValue() != 1) {
                    if (((Integer)a10.get(0)).intValue() == 171 && ((Integer)a10.get(4)).intValue() == 40 && ((Integer)a10.get(5)).intValue() == 128) {
                        String[] a11 = s1.split(" ");
                        StringBuilder a12 = new StringBuilder();
                        int i = a11.length - 1;
                        for(; i >= 9; i = i + -1) {
                            android.util.Log.i(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), a11[i]);
                            a12.append(a11[i]);
                            if (i != 9) {
                                a12.append(":");
                            }
                        }
                        com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$1002(this.this$0, a12.toString().toUpperCase());
                        String s2 = com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000();
                        StringBuilder a13 = new StringBuilder();
                        a13.append("ispMac2\uff1a");
                        a13.append(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$1000(this.this$0));
                        android.util.Log.i(s2, a13.toString());
                        com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3 a14 = this.this$0;
                        com.wakeup.wearfit2.util.SPUtils.putString((android.content.Context)a14, "isp_mac", com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$1000(a14));
                    }
                } else {
                    String[] a15 = s1.split(" ");
                    StringBuilder a16 = new StringBuilder();
                    int i0 = a15.length - 1;
                    for(; i0 >= 2; i0 = i0 + -1) {
                        android.util.Log.i(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000(), a15[i0]);
                        a16.append(a15[i0]);
                        if (i0 != 2) {
                            a16.append(":");
                        }
                    }
                    com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$1002(this.this$0, a16.toString().toUpperCase());
                    String s3 = com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$000();
                    StringBuilder a17 = new StringBuilder();
                    a17.append("ispMac1\uff1a");
                    a17.append(com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$1000(this.this$0));
                    android.util.Log.i(s3, a17.toString());
                    com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3 a18 = this.this$0;
                    com.wakeup.wearfit2.util.SPUtils.putString((android.content.Context)a18, "isp_mac", com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$1000(a18));
                }
            }
        } else if ("com.wakeup.smartband.ACTION_GATT_CHARACTER_NOTIFY".equals((Object)s)) {
            a0.getStringExtra("com.wakeup.wearfit2.le.EXTRA_DATA");
        } else if ("com.wakeup.smartband.OTA_RX_DAT_ACTION".equals((Object)s)) {
            byte[] a19 = a0.getByteArrayExtra("com.wakeup.smartband.ARRAY_BYTE_DATA");
            if (a19 != null) {
                com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$1100(this.this$0).setBluetoothNotifyData(a19, 2);
            }
        } else if ("com.wakeup.smartband.OTA_RX_CMD_ACTION".equals((Object)s)) {
            byte[] a20 = a0.getByteArrayExtra("com.wakeup.smartband.ARRAY_BYTE_DATA");
            if (a20 != null) {
                com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$1100(this.this$0).setBluetoothNotifyData(a20, 1);
            }
        } else if ("com.wakeup.smartband.OTA_RX_ISP_CMD_ACTION".equals((Object)s)) {
            a0.getByteArrayExtra("com.wakeup.smartband.ARRAY_BYTE_DATA");
            com.wakeup.wearfit2.kotlin.activity.SwitchBgActivity3.access$1100(this.this$0).entryIspModel(1007);
        }
    }
}
