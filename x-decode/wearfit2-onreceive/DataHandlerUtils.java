package com.wakeup.wearfit2.util;

public class DataHandlerUtils {
    public DataHandlerUtils() {
    }
    
    public static byte[] addBytes(byte[] a, byte[] a0) {
        if (a0 != null && a0.length != 0) {
            byte[] a1 = new byte[a.length + a0.length];
            System.arraycopy((Object)a, 0, (Object)a1, 0, a.length);
            System.arraycopy((Object)a0, 0, (Object)a1, a.length, a0.length);
            a = a1;
        }
        return a;
    }
    
    public static java.util.List bytesToArrayList(byte[] a) {
        java.util.ArrayList a0 = new java.util.ArrayList();
        int i = 0;
        for(; i < a.length; i = i + 1) {
            ((java.util.List)(Object)a0).add((Object)Integer.valueOf((int)a[i] & 255));
        }
        return (java.util.List)(Object)a0;
    }
    
    public static String bytesToHexStr(byte[] a) {
        StringBuilder a0 = new StringBuilder("");
        if (a != null && a.length > 0) {
            int i = 0;
            for(; i < a.length; i = i + 1) {
                String s = Integer.toHexString((int)a[i] & 255);
                if (s.length() >= 2) {
                    StringBuilder a1 = new StringBuilder();
                    a1.append(" ");
                    a1.append(s);
                    a0.append(a1.toString());
                } else {
                    StringBuilder a2 = new StringBuilder();
                    a2.append(" 0");
                    a2.append(s);
                    a0.append(a2.toString());
                }
            }
            return a0.toString();
        }
        String s0 = null;
        return s0;
    }
    
    public static byte[] handleMessageBytes(byte[] a) {
        if (((Integer)com.wakeup.wearfit2.util.DataHandlerUtils.bytesToArrayList(a).get(0)).intValue() == 171 && a.length > 20) {
            int i = 0;
            byte[] a0 = new byte[20];
            System.arraycopy((Object)a, 0, (Object)a0, 0, 20);
            int i0 = a.length - 20;
            byte[] a1 = new byte[i0];
            System.arraycopy((Object)a, 20, (Object)a1, 0, a.length - 20);
            java.util.ArrayList a2 = new java.util.ArrayList();
            int i1 = 0;
            while(true) {
                i = i0 / 19;
                if (i1 >= i + 1) {
                    break;
                }
                byte[] a3 = new byte[19];
                int i2 = i0 % 19;
                byte[] a4 = new byte[i2];
                if (i1 >= i) {
                    System.arraycopy((Object)a1, i1 * 19, (Object)a4, 0, i2);
                    a2.add((Object)a4);
                } else {
                    System.arraycopy((Object)a1, i1 * 19, (Object)a3, 0, 19);
                    a2.add((Object)a3);
                }
                i1 = i1 + 1;
            }
            java.util.ArrayList a5 = new java.util.ArrayList();
            int i3 = 0;
            for(; i3 < a2.size(); i3 = i3 + 1) {
                byte[] a6 = new byte[1];
                a6[0] = (byte)(int)(byte)i3;
                a5.add((Object)com.wakeup.wearfit2.util.DataHandlerUtils.addBytes(a6, (byte[])a2.get(i3)));
            }
            int i4 = i * 20 + 20;
            int i5 = i0 % 19;
            byte[] a7 = new byte[i4 + i5 + 1];
            System.arraycopy((Object)a0, 0, (Object)a7, 0, 20);
            int i6 = 0;
            for(; i6 < a5.size(); i6 = i6 + 1) {
                if (i6 >= a5.size() - 1) {
                    System.arraycopy(a5.get(i6), 0, (Object)a7, (i6 + 1) * 20, i5 + 1);
                } else {
                    System.arraycopy(a5.get(i6), 0, (Object)a7, (i6 + 1) * 20, 20);
                }
            }
            a = a7;
        }
        return a;
    }
}
