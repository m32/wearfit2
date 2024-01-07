package com.wakeup.wearfit2.util;

public class BitmapUtils {
    public BitmapUtils() {
    }
    
    public static int crcTable(byte[] a) {
        int[] a0 = new int[256];
        a0[0] = 0;
        a0[1] = 49345;
        a0[2] = 49537;
        a0[3] = 320;
        a0[4] = 49921;
        a0[5] = 960;
        a0[6] = 640;
        a0[7] = 49729;
        a0[8] = 50689;
        a0[9] = 1728;
        a0[10] = 1920;
        a0[11] = 51009;
        a0[12] = 1280;
        a0[13] = 50625;
        a0[14] = 50305;
        a0[15] = 1088;
        a0[16] = 52225;
        a0[17] = 3264;
        a0[18] = 3456;
        a0[19] = 52545;
        a0[20] = 3840;
        a0[21] = 53185;
        a0[22] = 52865;
        a0[23] = 3648;
        a0[24] = 2560;
        a0[25] = 51905;
        a0[26] = 52097;
        a0[27] = 2880;
        a0[28] = 51457;
        a0[29] = 2496;
        a0[30] = 2176;
        a0[31] = 51265;
        a0[32] = 55297;
        a0[33] = 6336;
        a0[34] = 6528;
        a0[35] = 55617;
        a0[36] = 6912;
        a0[37] = 56257;
        a0[38] = 55937;
        a0[39] = 6720;
        a0[40] = 7680;
        a0[41] = 57025;
        a0[42] = 57217;
        a0[43] = 8000;
        a0[44] = 56577;
        a0[45] = 7616;
        a0[46] = 7296;
        a0[47] = 56385;
        a0[48] = 5120;
        a0[49] = 54465;
        a0[50] = 54657;
        a0[51] = 5440;
        a0[52] = 55041;
        a0[53] = 6080;
        a0[54] = 5760;
        a0[55] = 54849;
        a0[56] = 53761;
        a0[57] = 4800;
        a0[58] = 4992;
        a0[59] = 54081;
        a0[60] = 4352;
        a0[61] = 53697;
        a0[62] = 53377;
        a0[63] = 4160;
        a0[64] = 61441;
        a0[65] = 12480;
        a0[66] = 12672;
        a0[67] = 61761;
        a0[68] = 13056;
        a0[69] = 62401;
        a0[70] = 62081;
        a0[71] = 12864;
        a0[72] = 13824;
        a0[73] = 63169;
        a0[74] = 63361;
        a0[75] = 14144;
        a0[76] = 62721;
        a0[77] = 13760;
        a0[78] = 13440;
        a0[79] = 62529;
        a0[80] = 15360;
        a0[81] = 64705;
        a0[82] = 64897;
        a0[83] = 15680;
        a0[84] = 65281;
        a0[85] = 16320;
        a0[86] = 16000;
        a0[87] = 65089;
        a0[88] = 64001;
        a0[89] = 15040;
        a0[90] = 15232;
        a0[91] = 64321;
        a0[92] = 14592;
        a0[93] = 63937;
        a0[94] = 63617;
        a0[95] = 14400;
        a0[96] = 10240;
        a0[97] = 59585;
        a0[98] = 59777;
        a0[99] = 10560;
        a0[100] = 60161;
        a0[101] = 11200;
        a0[102] = 10880;
        a0[103] = 59969;
        a0[104] = 60929;
        a0[105] = 11968;
        a0[106] = 12160;
        a0[107] = 61249;
        a0[108] = 11520;
        a0[109] = 60865;
        a0[110] = 60545;
        a0[111] = 11328;
        a0[112] = 58369;
        a0[113] = 9408;
        a0[114] = 9600;
        a0[115] = 58689;
        a0[116] = 9984;
        a0[117] = 59329;
        a0[118] = 59009;
        a0[119] = 9792;
        a0[120] = 8704;
        a0[121] = 58049;
        a0[122] = 58241;
        a0[123] = 9024;
        a0[124] = 57601;
        a0[125] = 8640;
        a0[126] = 8320;
        a0[127] = 57409;
        a0[128] = 40961;
        a0[129] = 24768;
        a0[130] = 24960;
        a0[131] = 41281;
        a0[132] = 25344;
        a0[133] = 41921;
        a0[134] = 41601;
        a0[135] = 25152;
        a0[136] = 26112;
        a0[137] = 42689;
        a0[138] = 42881;
        a0[139] = 26432;
        a0[140] = 42241;
        a0[141] = 26048;
        a0[142] = 25728;
        a0[143] = 42049;
        a0[144] = 27648;
        a0[145] = 44225;
        a0[146] = 44417;
        a0[147] = 27968;
        a0[148] = 44801;
        a0[149] = 28608;
        a0[150] = 28288;
        a0[151] = 44609;
        a0[152] = 43521;
        a0[153] = 27328;
        a0[154] = 27520;
        a0[155] = 43841;
        a0[156] = 26880;
        a0[157] = 43457;
        a0[158] = 43137;
        a0[159] = 26688;
        a0[160] = 30720;
        a0[161] = 47297;
        a0[162] = 47489;
        a0[163] = 31040;
        a0[164] = 47873;
        a0[165] = 31680;
        a0[166] = 31360;
        a0[167] = 47681;
        a0[168] = 48641;
        a0[169] = 32448;
        a0[170] = 32640;
        a0[171] = 48961;
        a0[172] = 32000;
        a0[173] = 48577;
        a0[174] = 48257;
        a0[175] = 31808;
        a0[176] = 46081;
        a0[177] = 29888;
        a0[178] = 30080;
        a0[179] = 46401;
        a0[180] = 30464;
        a0[181] = 47041;
        a0[182] = 46721;
        a0[183] = 30272;
        a0[184] = 29184;
        a0[185] = 45761;
        a0[186] = 45953;
        a0[187] = 29504;
        a0[188] = 45313;
        a0[189] = 29120;
        a0[190] = 28800;
        a0[191] = 45121;
        a0[192] = 20480;
        a0[193] = 37057;
        a0[194] = 37249;
        a0[195] = 20800;
        a0[196] = 37633;
        a0[197] = 21440;
        a0[198] = 21120;
        a0[199] = 37441;
        a0[200] = 38401;
        a0[201] = 22208;
        a0[202] = 22400;
        a0[203] = 38721;
        a0[204] = 21760;
        a0[205] = 38337;
        a0[206] = 38017;
        a0[207] = 21568;
        a0[208] = 39937;
        a0[209] = 23744;
        a0[210] = 23936;
        a0[211] = 40257;
        a0[212] = 24320;
        a0[213] = 40897;
        a0[214] = 40577;
        a0[215] = 24128;
        a0[216] = 23040;
        a0[217] = 39617;
        a0[218] = 39809;
        a0[219] = 23360;
        a0[220] = 39169;
        a0[221] = 22976;
        a0[222] = 22656;
        a0[223] = 38977;
        a0[224] = 34817;
        a0[225] = 18624;
        a0[226] = 18816;
        a0[227] = 35137;
        a0[228] = 19200;
        a0[229] = 35777;
        a0[230] = 35457;
        a0[231] = 19008;
        a0[232] = 19968;
        a0[233] = 36545;
        a0[234] = 36737;
        a0[235] = 20288;
        a0[236] = 36097;
        a0[237] = 19904;
        a0[238] = 19584;
        a0[239] = 35905;
        a0[240] = 17408;
        a0[241] = 33985;
        a0[242] = 34177;
        a0[243] = 17728;
        a0[244] = 34561;
        a0[245] = 18368;
        a0[246] = 18048;
        a0[247] = 34369;
        a0[248] = 33281;
        a0[249] = 17088;
        a0[250] = 17280;
        a0[251] = 33601;
        a0[252] = 16640;
        a0[253] = 33217;
        a0[254] = 32897;
        a0[255] = 16448;
        int i = a.length;
        int i0 = 65535;
        int i1 = 0;
        for(; i1 < i; i1 = i1 + 1) {
            int i2 = a[i1];
            int i3 = i0 >>> 8;
            i0 = a0[(i0 ^ i2) & 255] ^ i3;
        }
        return i0;
    }
    
    public static android.graphics.Bitmap getBitmap(String s) {
        android.graphics.BitmapFactory$Options a = new android.graphics.BitmapFactory$Options();
        a.inPreferredConfig = android.graphics.Bitmap$Config.ARGB_8888;
        a.inPurgeable = true;
        a.inInputShareable = true;
        a.inScaled = false;
        try {
            return android.graphics.BitmapFactory.decodeStream((java.io.InputStream)new java.io.FileInputStream(new java.io.File(s)), (android.graphics.Rect)null, a);
        } catch(java.io.FileNotFoundException a0) {
            a0.printStackTrace();
            android.graphics.Bitmap a1 = null;
            return a1;
        }
    }
    
    public static int getBitmapSize(android.graphics.Bitmap a) {
        if (android.os.Build$VERSION.SDK_INT >= 19) {
            return a.getAllocationByteCount();
        }
        if (android.os.Build$VERSION.SDK_INT >= 12) {
            return a.getByteCount();
        }
        return a.getRowBytes() * a.getHeight();
    }
    
    public static java.util.ArrayList getHexPixels(int[] a, int i) {
        java.util.ArrayList a0 = new java.util.ArrayList(i);
        int i0 = 0;
        for(; i0 < a.length; i0 = i0 + 1) {
            int i1 = a[i0];
            String s = Integer.toHexString(((16711680 & i1) >> 16 >> 3 << 11) + ((65280 & i1) >> 8 >> 2 << 5) + ((i1 & 255) >> 3));
            if (s.length() < 4) {
                while(s.length() < 4) {
                    StringBuilder a1 = new StringBuilder();
                    a1.append("0");
                    a1.append(s);
                    s = a1.toString();
                }
            }
            a0.add((Object)s);
        }
        return a0;
    }
    
    public static java.util.List hexString_16_to_8(java.util.ArrayList a) {
        java.util.ArrayList a0 = new java.util.ArrayList();
        Object a1 = a.iterator();
        while(((java.util.Iterator)a1).hasNext()) {
            int i = Integer.parseInt((String)((java.util.Iterator)a1).next(), 16);
            int i0 = i / 256;
            int i1 = i % 256;
            ((java.util.List)(Object)a0).add((Object)Integer.toHexString(i0));
            ((java.util.List)(Object)a0).add((Object)Integer.toHexString(i1));
        }
        return (java.util.List)(Object)a0;
    }
    
    private static boolean isEmptyBitmap(android.graphics.Bitmap a) {
        boolean b = false;
        label2: {
            label0: {
                label1: {
                    if (a == null) {
                        break label1;
                    }
                    if (a.getWidth() == 0) {
                        break label1;
                    }
                    if (a.getHeight() != 0) {
                        break label0;
                    }
                }
                b = true;
                break label2;
            }
            b = false;
        }
        return b;
    }
    
    public static android.graphics.Bitmap scale(android.graphics.Bitmap a, int i, int i0) {
        return com.wakeup.wearfit2.util.BitmapUtils.scale(a, i, i0, false);
    }
    
    public static android.graphics.Bitmap scale(android.graphics.Bitmap a, int i, int i0, boolean b) {
        if (com.wakeup.wearfit2.util.BitmapUtils.isEmptyBitmap(a)) {
            android.graphics.Bitmap a0 = null;
            return a0;
        }
        android.graphics.Bitmap a1 = android.graphics.Bitmap.createScaledBitmap(a, i, i0, true);
        if (b && !a.isRecycled() && a1 != a) {
            a.recycle();
        }
        return a1;
    }
    
    public static java.util.List split(java.util.List a, int i) {
        if (a != null && i >= 1) {
            java.util.ArrayList a0 = new java.util.ArrayList();
            int i0 = a.size();
            if (i0 > i) {
                int i1 = i0 / i;
                int i2 = i0 % i;
                Object a1 = a;
                int i3 = 0;
                for(; i3 < i1; i3 = i3 + 1) {
                    java.util.ArrayList a2 = new java.util.ArrayList();
                    int i4 = 0;
                    for(; i4 < i; i4 = i4 + 1) {
                        ((java.util.List)(Object)a2).add(((java.util.List)a1).get(i3 * i + i4));
                    }
                    ((java.util.List)(Object)a0).add((Object)a2);
                }
                if (i2 > 0) {
                    java.util.ArrayList a3 = new java.util.ArrayList();
                    int i5 = 0;
                    for(; i5 < i2; i5 = i5 + 1) {
                        ((java.util.List)(Object)a3).add(((java.util.List)a1).get(i1 * i + i5));
                    }
                    ((java.util.List)(Object)a0).add((Object)a3);
                }
            } else {
                ((java.util.List)(Object)a0).add((Object)a);
            }
            return (java.util.List)(Object)a0;
        }
        java.util.ArrayList a4 = null;
        return (java.util.List)(Object)a4;
    }
}
