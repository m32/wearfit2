package com.wakeup.wearfit2.util;

public class EcgDataUtils {
    public EcgDataUtils() {
    }
    
    public static int getAverageEcg(String s) {
        if (s != null && s.length() != 0) {
            if (s.length() == 2) {
                return Integer.parseInt(s);
            }
            Object a = com.wakeup.wearfit2.util.EcgDataUtils.getBpmList(s).iterator();
            int i = 0;
            while(((java.util.Iterator)a).hasNext()) {
                i = i + ((Integer)((java.util.Iterator)a).next()).intValue();
            }
            return i / (s.length() / 2);
        }
        return 75;
    }
    
    public static int getAverageEcg(java.util.List a) {
        if (a != null && a.size() != 0) {
            java.util.Iterator a0 = a.iterator();
            Object a1 = a;
            int i = 0;
            Object a2 = a0;
            while(((java.util.Iterator)a2).hasNext()) {
                i = i + ((Integer)((java.util.Iterator)a2).next()).intValue();
            }
            return i / ((java.util.List)a1).size();
        }
        return 75;
    }
    
    public static java.util.List getBpmList(String s) {
        String s0 = s.replace((CharSequence)(Object)" ", (CharSequence)(Object)"");
        java.util.ArrayList a = new java.util.ArrayList();
        if (s0.length() > 10) {
            int i = 0;
            while(i < s0.length() / 2) {
                int i0 = i * 2;
                i = i + 1;
                String s1 = s0.substring(i0, i * 2);
                Integer a0 = Integer.valueOf(s1, 16);
                StringBuilder a1 = new StringBuilder();
                a1.append(s1);
                a1.append("--s");
                android.util.Log.i("lq789", a1.toString());
                StringBuilder a2 = new StringBuilder();
                a2.append((Object)a0);
                a2.append("");
                android.util.Log.i("lq789", a2.toString());
                ((java.util.List)(Object)a).add((Object)a0);
            }
        }
        return (java.util.List)(Object)a;
    }
    
    public static int getEcgPercent(String s) {
        if (s != null && s.length() != 0) {
            Object a = com.wakeup.wearfit2.util.EcgDataUtils.getBpmList(s).iterator();
            int i = 0;
            while(((java.util.Iterator)a).hasNext()) {
                Integer a0 = (Integer)((java.util.Iterator)a).next();
                if (a0.intValue() >= 60 && a0.intValue() <= 100) {
                    i = i + 1;
                }
            }
            return i * 100 * 2 / s.length();
        }
        return 100;
    }
    
    public static int getEcgPercent(java.util.List a) {
        if (a != null && a.size() != 0) {
            java.util.Iterator a0 = a.iterator();
            Object a1 = a;
            int i = 0;
            Object a2 = a0;
            while(((java.util.Iterator)a2).hasNext()) {
                Integer a3 = (Integer)((java.util.Iterator)a2).next();
                if (a3.intValue() >= 60 && a3.intValue() <= 100) {
                    i = i + 1;
                }
            }
            return i * 100 / ((java.util.List)a1).size();
        }
        return 100;
    }
    
    public static java.util.List getIllList(java.util.List a, int i) {
        label35: if (i >= 60) {
            label36: {
                if (i < 60) {
                    break label36;
                }
                if (i >= 65) {
                    break label36;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(0)).setPercent("8%");
                break label35;
            }
            label34: {
                if (i < 65) {
                    break label34;
                }
                if (i >= 70) {
                    break label34;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(0)).setPercent("3%");
                break label35;
            }
            if (i >= 70) {
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(0)).setPercent("0%");
            }
        } else {
            ((com.wakeup.wearfit2.bean.IllnessBean)a.get(0)).setPercent("15%");
        }
        label31: if (i >= 60) {
            label33: {
                if (i < 60) {
                    break label33;
                }
                if (i >= 65) {
                    break label33;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(1)).setPercent("3%");
                break label31;
            }
            label32: {
                if (i < 65) {
                    break label32;
                }
                if (i >= 70) {
                    break label32;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(1)).setPercent("2%");
                break label31;
            }
            label29: {
                label30: {
                    if (i < 70) {
                        break label30;
                    }
                    if (i < 100) {
                        break label29;
                    }
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(1)).setPercent("10%");
                break label31;
            }
            ((com.wakeup.wearfit2.bean.IllnessBean)a.get(1)).setPercent("0%");
        } else {
            ((com.wakeup.wearfit2.bean.IllnessBean)a.get(1)).setPercent("5%");
        }
        label26: if (i >= 80) {
            label28: {
                if (i < 80) {
                    break label28;
                }
                if (i >= 85) {
                    break label28;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(2)).setPercent("4%");
                break label26;
            }
            label27: {
                if (i < 85) {
                    break label27;
                }
                if (i >= 90) {
                    break label27;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(2)).setPercent("5%");
                break label26;
            }
            label25: {
                if (i < 90) {
                    break label25;
                }
                if (i >= 100) {
                    break label25;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(2)).setPercent("6%");
                break label26;
            }
            if (i >= 100) {
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(2)).setPercent("10%");
            }
        } else {
            ((com.wakeup.wearfit2.bean.IllnessBean)a.get(2)).setPercent("0%");
        }
        label22: if (i >= 60) {
            label24: {
                if (i < 60) {
                    break label24;
                }
                if (i >= 65) {
                    break label24;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(3)).setPercent("10%");
                break label22;
            }
            label23: {
                if (i < 65) {
                    break label23;
                }
                if (i >= 75) {
                    break label23;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(3)).setPercent("8%");
                break label22;
            }
            label20: {
                label21: {
                    if (i < 65) {
                        break label21;
                    }
                    if (i < 100) {
                        break label20;
                    }
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(3)).setPercent("0%");
                break label22;
            }
            ((com.wakeup.wearfit2.bean.IllnessBean)a.get(3)).setPercent("3%");
        } else {
            ((com.wakeup.wearfit2.bean.IllnessBean)a.get(3)).setPercent("15%");
        }
        label18: if (i >= 60) {
            label19: {
                if (i < 60) {
                    break label19;
                }
                if (i >= 65) {
                    break label19;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(4)).setPercent("15%");
                break label18;
            }
            label16: {
                label17: {
                    if (i < 65) {
                        break label17;
                    }
                    if (i < 75) {
                        break label16;
                    }
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(4)).setPercent("0%");
                break label18;
            }
            ((com.wakeup.wearfit2.bean.IllnessBean)a.get(4)).setPercent("5%");
        } else {
            ((com.wakeup.wearfit2.bean.IllnessBean)a.get(4)).setPercent("20%");
        }
        label13: if (i >= 60) {
            label15: {
                if (i < 60) {
                    break label15;
                }
                if (i >= 65) {
                    break label15;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(5)).setPercent("5%");
                break label13;
            }
            label14: {
                if (i < 65) {
                    break label14;
                }
                if (i >= 75) {
                    break label14;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(5)).setPercent("8%");
                break label13;
            }
            label11: {
                label12: {
                    if (i < 75) {
                        break label12;
                    }
                    if (i < 85) {
                        break label11;
                    }
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(5)).setPercent("10%");
                break label13;
            }
            ((com.wakeup.wearfit2.bean.IllnessBean)a.get(5)).setPercent("6%");
        } else {
            ((com.wakeup.wearfit2.bean.IllnessBean)a.get(5)).setPercent("4%");
        }
        label8: if (i >= 60) {
            label10: {
                if (i < 60) {
                    break label10;
                }
                if (i >= 65) {
                    break label10;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(6)).setPercent("5%");
                break label8;
            }
            label9: {
                if (i < 65) {
                    break label9;
                }
                if (i >= 75) {
                    break label9;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(6)).setPercent("0%");
                break label8;
            }
            label6: {
                label7: {
                    if (i < 75) {
                        break label7;
                    }
                    if (i < 85) {
                        break label6;
                    }
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(6)).setPercent("15%");
                break label8;
            }
            ((com.wakeup.wearfit2.bean.IllnessBean)a.get(6)).setPercent("10%");
        } else {
            ((com.wakeup.wearfit2.bean.IllnessBean)a.get(6)).setPercent("10%");
        }
        label2: if (i >= 60) {
            label5: {
                if (i < 60) {
                    break label5;
                }
                if (i >= 65) {
                    break label5;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(7)).setPercent("0%");
                break label2;
            }
            label4: {
                if (i < 65) {
                    break label4;
                }
                if (i >= 75) {
                    break label4;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(7)).setPercent("2%");
                break label2;
            }
            label3: {
                if (i < 75) {
                    break label3;
                }
                if (i >= 85) {
                    break label3;
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(7)).setPercent("5%");
                break label2;
            }
            label0: {
                label1: {
                    if (i < 85) {
                        break label1;
                    }
                    if (i < 95) {
                        break label0;
                    }
                }
                ((com.wakeup.wearfit2.bean.IllnessBean)a.get(7)).setPercent("20%");
                break label2;
            }
            ((com.wakeup.wearfit2.bean.IllnessBean)a.get(7)).setPercent("10%");
        } else {
            ((com.wakeup.wearfit2.bean.IllnessBean)a.get(7)).setPercent("0%");
        }
        return a;
    }
    
    public static int getlew(String s) {
        int i = 0;
        if (s == null) {
            i = 0;
        } else if (s.length() != 0) {
            if (s == null) {
                i = 0;
            } else if (s.length() != 0) {
                java.util.Iterator a = com.wakeup.wearfit2.util.EcgDataUtils.getBpmList(s).iterator();
                int i0 = 0;
                Object a0 = a;
                while(((java.util.Iterator)a0).hasNext()) {
                    Integer a1 = (Integer)((java.util.Iterator)a0).next();
                    int i1 = a1.intValue();
                    {
                        label0: {
                            label1: {
                                if (i1 >= 60) {
                                    break label1;
                                }
                                if (a1.intValue() > 50) {
                                    break label0;
                                }
                            }
                            if (a1.intValue() <= 100) {
                                continue;
                            }
                            if (a1.intValue() >= 130) {
                                continue;
                            }
                        }
                        i0 = i0 + 1;
                    }
                }
                i = i0 * 2 * 100 / s.length();
            } else {
                i = 0;
            }
        } else {
            i = 0;
        }
        return i;
    }
    
    public static int getlew(java.util.List a) {
        int i = 0;
        if (a == null) {
            i = 0;
        } else if (a.size() != 0) {
            java.util.Iterator a0 = a.iterator();
            Object a1 = a;
            int i0 = 0;
            Object a2 = a0;
            while(((java.util.Iterator)a2).hasNext()) {
                Integer a3 = (Integer)((java.util.Iterator)a2).next();
                int i1 = a3.intValue();
                {
                    label0: {
                        label1: {
                            if (i1 >= 60) {
                                break label1;
                            }
                            if (a3.intValue() > 50) {
                                break label0;
                            }
                        }
                        if (a3.intValue() <= 100) {
                            continue;
                        }
                        if (a3.intValue() >= 130) {
                            continue;
                        }
                    }
                    i0 = i0 + 1;
                }
            }
            i = i0 * 100 / ((java.util.List)a1).size();
        } else {
            i = 0;
        }
        return i;
    }
    
    public static int getlewRhy(String s) {
        int i = 0;
        if (s == null) {
            i = 0;
        } else if (s.length() != 0) {
            java.util.Iterator a = com.wakeup.wearfit2.util.EcgDataUtils.getBpmList(s).iterator();
            int i0 = 0;
            Object a0 = a;
            while(((java.util.Iterator)a0).hasNext()) {
                if ((double)((float)((Integer)((java.util.Iterator)a0).next()).intValue() / 60f) < 0.6) {
                    i0 = i0 + 1;
                }
            }
            i = i0 * 2 * 100 / s.length();
        } else {
            i = 0;
        }
        return i;
    }
    
    public static int getlewRhy(java.util.List a) {
        int i = 0;
        if (a == null) {
            i = 0;
        } else if (a.size() != 0) {
            java.util.Iterator a0 = a.iterator();
            Object a1 = a;
            int i0 = 0;
            Object a2 = a0;
            while(((java.util.Iterator)a2).hasNext()) {
                if ((double)((float)((Integer)((java.util.Iterator)a2).next()).intValue() / 60f) < 0.6) {
                    i0 = i0 + 1;
                }
            }
            i = i0 * 100 / ((java.util.List)a1).size();
        } else {
            i = 0;
        }
        return i;
    }
}
