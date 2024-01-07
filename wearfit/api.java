/*
https://wearfit.coding.net/public/Howear/BleDemo/git/files/master/FastBleLib/src/main/java/com/clj/fastble/BleOrderBiz.java
*/
package com.clj.fastble;

import android.text.TextUtils;

import com.clj.fastble.bluetooth.SplitWriter;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.model.DateModel;
import com.clj.fastble.model.WeatherModel;
import com.clj.fastble.utils.BleUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;


/**
 * ---------------------------------------------------------------------------------------------
 * 功能描述: 手机发送手环蓝牙指令集
 * ---------------------------------------------------------------------------------------------
 * 时　　间: 2020/3/23 15:38
 * ---------------------------------------------------------------------------------------------
 * 代码创建: 刘桂安
 * ---------------------------------------------------------------------------------------------
 * 代码备注:
 * ---------------------------------------------------------------------------------------------
 **/
public class BleOrderBiz {
    /**
     * 连续体温
     *
     * @param control 0关  1开
     */
    /**
     * 单次、实时测量
     *
     * @param type    心率：0X09(单次) 0X0A(实时)
     *                血氧：0X11(单次) 0X12(实时)
     *                血压：0X21(单次) 0X22(实时)
     *                体温：0x81(单次)
     *                免疫值：0x41(单次)
     * @param control 0关  1开
     */
    public static void setOnceOrRealTimeMeasure(int type, int control, BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[7];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 4;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0x31;
        bytes[5] = (byte) type;
        bytes[6] = (byte) control;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 连续体温
     *
     * @param control 0关  1开
     */
    public static void bodyTempMeasure(int control, BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[7];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 4;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0x86;
        bytes[5] = (byte) 0x80;
        bytes[6] = (byte) control;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 一键测量
     *
     * @param control 0(关)  1(开)
     */
    public static void setOnceKeyMeasure(int control, BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[7];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 4;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0x32;
        bytes[5] = (byte) 0x80;
        bytes[6] = (byte) control;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 下拉同步数据
     *
     * @param timeInMillis 同步数据起始时间毫秒值
     */
    public static void setSyncDataHr(long timeInMillis, BleDevice bleDevice, BleWriteCallback callback) {
        DateModel dateModelA = new DateModel(timeInMillis);
        DateModel dateModelB = new DateModel(timeInMillis);
        byte[] bytes = new byte[17];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 14;
        bytes[3] = (byte) 0xff;
        bytes[4] = (byte) 0x51;
        bytes[5] = (byte) 0x80;
        bytes[7] = (byte) ((dateModelA.year - 2000));
        bytes[8] = (byte) (dateModelA.month);
        bytes[9] = (byte) (dateModelA.day);
        bytes[10] = (byte) (dateModelA.hour);
        bytes[11] = (byte) (dateModelA.minute);

        bytes[12] = (byte) ((dateModelB.year - 2000));
        bytes[13] = (byte) (dateModelB.month);
        bytes[14] = (byte) (dateModelB.day);
        bytes[15] = (byte) (dateModelB.hour);
        bytes[16] = (byte) (dateModelB.minute);
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 下拉同步睡眠数据
     */
    public static void syncSleepData(long timeInMillis, BleDevice bleDevice, BleWriteCallback callback) {
        DateModel dateModel = new DateModel(timeInMillis);
        byte[] bytes = new byte[12];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 9;
        bytes[3] = (byte) 0xff;
        bytes[4] = (byte) 0x52;
        bytes[5] = (byte) 0x80;
        //data[6] = (byte)0;//占位符，没意义
        bytes[7] = (byte) ((dateModel.year - 2000));
        bytes[8] = (byte) (dateModel.month);
        bytes[9] = (byte) (dateModel.day);
        bytes[10] = (byte) (dateModel.hour);
        bytes[11] = (byte) (dateModel.minute);
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 查找手环
     */
    public static void findBand(BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[6];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 3;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0x71;
        bytes[5] = (byte) 0x80;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 设置勿扰模式
     *
     * @param control     0关 1开
     * @param startHour   开始时间 - 小时
     * @param startMinute 开始时间 - 分钟
     * @param endHour     结束时间 - 小时
     * @param endMinute   结束时间 - 分钟
     */
    public static void setDisturbanceModel(int control, int startHour, int startMinute, int endHour, int endMinute, BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[11];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 8;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0x76;
        bytes[5] = (byte) 0x80;
        bytes[6] = (byte) control;
        bytes[7] = (byte) startHour;
        bytes[8] = (byte) startMinute;
        bytes[9] = (byte) endHour;
        bytes[10] = (byte) endMinute;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 设置用户信息
     *
     * @param unit            距离单位 1：公里 0：英里
     * @param temperatureUnit 温度单位 0 摄氏度；1华氏度
     * @param birthday        生日 毫秒级时间戳
     * @param height          身高
     * @param weight          体重
     * @param goalNum         目标步数
     */
    public static void sendUserInfo(int unit, int temperatureUnit, long birthday, int height, float weight, int goalNum, BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[13];
        //起始帧
        bytes[0] = (byte) 0xAB;
        //长度
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 10;
        //ID
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0x74;
        //默认值
        bytes[5] = (byte) 0x80;

        //步距(单位:cm)
        bytes[6] = (byte) 70;
        //年龄
        bytes[7] = (byte) BleUtil.getAge(new Date(birthday));
        //身高
        bytes[8] = (byte) height;
        //体重
        bytes[9] = (byte) weight;
        //距离单位 1：公里 0：英里
        bytes[10] = unit == 1 ? (byte) 1 : (byte) 0;
        //目标步数（缩小1000倍）
        bytes[11] = (byte) (goalNum / 1000);
        //温度单位 0 摄氏度；1华氏度
        bytes[12] = (byte) (temperatureUnit == 2 ? 1 : 0);
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 整点测量
     *
     * @param control 0关  1开
     */
    public static void setOnTimeMeasure(int control, BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[7];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 4;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0x78;
        bytes[5] = (byte) 0x80;
        bytes[6] = (byte) control;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 抬手亮屏
     *
     * @param control 0关  1开
     */
    public static void setUpHandLightScreen(int control, BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[7];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 4;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0x77;
        bytes[5] = (byte) 0x80;
        bytes[6] = (byte) control;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }


    /**
     * 摇摇拍照指令
     *
     * @param control 0关  1开
     */
    public static void setSharkTakePhoto(int control, BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[7];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 4;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0x79;
        bytes[5] = (byte) 0x80;
        bytes[6] = (byte) control;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 设置手环语言
     *
     * @param language 0中文  1英文
     */
    public static void setBandLanguage(int language, BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[7];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 4;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0x7B;
        bytes[5] = (byte) 0x80;
        bytes[6] = (byte) language;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 时间制切换
     *
     * @param type 0（24小时制）  1(12小时制)
     */
    public static void set12HourSystem(int type, BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[7];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 4;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0x7C;
        bytes[5] = (byte) 0x80;
        bytes[6] = (byte) type;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 同步时间
     */
    public static void setTimeSync(BleDevice bleDevice, BleWriteCallback callback) {
        //当前时间
        DateModel dateModel = new DateModel(System.currentTimeMillis());
        byte[] bytes = new byte[14];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 11;
        bytes[3] = (byte) 0xff;
        bytes[4] = (byte) 0x93;
        bytes[5] = (byte) 0x80;
        //TODO 拆开   如2018=0x07E2,则byte7=0x07，byte8=0xE2
        bytes[7] = (byte) ((dateModel.year & 0xff00) >> 8);
        bytes[8] = (byte) (dateModel.year & 0xff);
        bytes[9] = (byte) (dateModel.month & 0xff);
        bytes[10] = (byte) (dateModel.day & 0xff);
        bytes[11] = (byte) (dateModel.hour & 0xff);
        bytes[12] = (byte) (dateModel.minute & 0xff);
        bytes[13] = (byte) (dateModel.second & 0xff);
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 清除数据
     */
    public static void setClearData(BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[7];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 4;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0x23;
        bytes[5] = (byte) 0x80;
        bytes[6] = (byte) 0x00;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 手环复位
     */
    public static void setResetBand(BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[6];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 3;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0xFF;
        bytes[5] = (byte) 0x80;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 久坐提醒
     *
     * @param control     开关 0关 1开
     * @param startHour   开始时间 - 小时
     * @param startMinute 开始时间 - 分钟
     * @param endHour     结束时间 - 小时
     * @param endMinute   结束时间 - 分钟
     * @param interval    间隔（默认45分钟，其他设置可以为30,90,120分钟）
     */
    public static void setSedentarinessWarn(int control, int startHour, int startMinute, int endHour, int endMinute, int interval, BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[12];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 9;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0x75;
        bytes[5] = (byte) 0x80;
        bytes[6] = (byte) control;
        bytes[7] = (byte) startHour;
        bytes[8] = (byte) startMinute;
        bytes[9] = (byte) endHour;
        bytes[10] = (byte) endMinute;
        bytes[11] = (byte) interval;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 设置睡眠记录时间
     *
     * @param control     0关  1开
     * @param startHour   开始时间 - 小时
     * @param startMinute 开始时间 - 分钟
     * @param endHour     结束时间 - 小时
     * @param endMinute   结束时间 - 分钟
     */
    public static void setSleepTimeRange(int control, int startHour, int startMinute, int endHour, int endMinute, BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[11];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 8;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0x7F;
        bytes[5] = (byte) 0x80;
        bytes[6] = (byte) control;
        bytes[7] = (byte) startHour;
        bytes[8] = (byte) startMinute;
        bytes[9] = (byte) endHour;
        bytes[10] = (byte) endMinute;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 消息提醒
     *
     * @param messageId 消息来源于哪个APP
     * @param type      0：关消息提醒功能 1：开消息提醒功能 2：来消息通知(带内 容)(安卓手机)
     * @param content   消息内容
     */
    public static void setSmartWarnCarryContent(int messageId, int type, String content, BleDevice bleDevice, BleWriteCallback callback) {
        if (content == null || TextUtils.isEmpty(content)) {
            return;
        }
        //裁剪文字
        content = BleUtil.subString(content);
        byte[] bytesContent = content.getBytes();
        int length = bytesContent.length;
        /**
         * 头
         */
        List<Byte> list = new ArrayList<>();
        //头
        list.add((byte) 0xAB);
        //长度
        list.add((byte) ((length + 5) / 256));
        list.add((byte) ((length + 5) % 256));
        //ID
        list.add((byte) 0xFF);
        list.add((byte) 0x72);
        //默认
        list.add((byte) 0x80);
        //消息ID
        list.add((byte) messageId);
        //类型
        list.add((byte) type);

        if (bytesContent.length <= (20 - list.size())) {
            for (byte b : bytesContent) {
                list.add(b);
            }
        } else {
            //14
            int size = (20 - list.size());
            //填满14
            for (int i = 0; i < size; i++) {
                list.add(bytesContent[i]);
            }

            //剩余长度
            byte[] data = new byte[bytesContent.length - size];
            for (int i = 0; i < data.length; i++) {
                data[i] = bytesContent[size + i];
            }
            //切歌
            Queue<byte[]> mDataQueue = SplitWriter.splitByte(data, 19);
            int index = 0;
            //循环添加
            while (mDataQueue.peek() != null) {
                list.add((byte) index);
                byte[] pollByte = mDataQueue.poll();
                for (int i = 0; i < pollByte.length; i++) {
                    list.add(pollByte[i]);
                }
                index++;
            }
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 发送天气
     */
    public static void sendWeatherInfo(List<WeatherModel> weatherModelList, BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[20];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 17;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0x7E;
        bytes[5] = (byte) 0X80;

        for (int i = 0; i < weatherModelList.size(); i++) {
            WeatherModel weatherModel = weatherModelList.get(i);
            int temperature = weatherModel.getTemperature();
            int code = weatherModel.getCode();
            String weatherType;
            if (temperature > 0) {
                weatherType = code + "0";
            } else {
                weatherType = code + "1";
            }

            bytes[6 + 2 * i] = (byte) (Integer.parseInt(weatherType, 16));
            bytes[7 + 2 * i] = (byte) Math.abs(temperature);
        }
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    //获取经典蓝牙地址
    public static void getClassicMac(BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[6];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 3;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0xA6;
        bytes[5] = (byte) 0x80;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    //设置联系人个数和谁是sos
    public static void setContactNum(int id, int num, BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[8];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 5;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0xA5;
        bytes[5] = (byte) 0x80;
        bytes[6] = (byte) id;//sos
        bytes[7] = (byte) num;//联系人个数
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    //设置联系人姓名
    public static void sendContactName(int id, String name, BleDevice bleDevice, BleWriteCallback callback) {
        if (name == null) {
            return;
        }
        name = cut(name, 21);
        byte[] bytesContent = null;
        int length = 0;
        if (!TextUtils.isEmpty(name)) {
            bytesContent = name.getBytes();
            length = bytesContent.length;
        }
        byte[] bytes = new byte[6];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) ((length + 3) / 256);
        bytes[2] = (byte) ((length + 3) % 256);
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0xA2;
        bytes[5] = (byte) id;
        byte[] bytesAdded = addBytes(bytes, bytesContent);
        byte[] bytesHandled = handleMessageBytes(bytesAdded);
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    //设置联系人电话号码
    public static void sendContactPhone(int id, String phone, BleDevice bleDevice, BleWriteCallback callback) {
        if (phone == null) {
            return;
        }
        phone = phone.replace(" ", "");
        byte[] bytes2;
        if (phone.contains("+")) {
            phone.replace("+", "A");
            int i = phone.length();
            if (i % 2 == 0) {
                //偶数个
                //一共可以切割成j个
                int j = i / 2;
                bytes2 = new byte[j];
                String substring;
                for (int i1 = 0; i1 < j; i1++) {
                    substring = phone.substring(2 * i1, 2 * i1 + 2);
                    bytes2[i1] = (byte) string2Int(substring);
                }
            } else {
                //奇数个
                int j = i / 2 + 1;
                bytes2 = new byte[j];
                String substring;
                for (int i1 = 0; i1 < j; i1++) {
                    if (i1 < j - 1) {
                        substring = phone.substring(2 * i1, 2 * i1 + 2);
                    } else {
                        //最后一位
                        substring = phone.substring(2 * i1, 2 * i1 + 1);
                    }
                    bytes2[i1] = (byte) string2Int(substring);
                }
            }
        } else {
            int i = phone.length();
            if (i % 2 == 0) {
                //偶数个
                //一共可以切割成j个
                int j = i / 2;
                bytes2 = new byte[j];
                String substring;

                for (int i1 = 0; i1 < j; i1++) {
                    substring = phone.substring(2 * i1, 2 * i1 + 2);
                    bytes2[i1] = (byte) string2Int(substring);
                }
            } else {
                //奇数个
                int j = i / 2 + 1;
                bytes2 = new byte[j];
                String substring;
                for (int i1 = 0; i1 < j; i1++) {
                    if (i1 < j - 1) {
                        substring = phone.substring(2 * i1, 2 * i1 + 2);
                    } else {
                        //最后一位
                        substring = phone.substring(2 * i1, 2 * i1 + 1);
                    }
                    bytes2[i1] = (byte) string2Int(substring);
                }
            }
        }


        byte[] bytes = new byte[7];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) ((bytes2.length + 4) / 256);
        bytes[2] = (byte) ((bytes2.length + 4) % 256);
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0xA3;
        bytes[5] = (byte) id;
        bytes[6] = (byte) phone.length();
        byte[] bytesAdded = addBytes(bytes, bytes2);
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }

    /**
     * 喝水提醒
     *
     * @param control   0关  1开
     * @param startHour 开始小时
     * @param startMin  开始分钟
     * @param endHour   结束小时
     * @param endMin    结束分钟
     * @param interval  间隔
     */
    public static void setDrinkWater(int control, int startHour, int startMin, int endHour, int endMin, int interval, BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[12];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0x00;
        bytes[2] = (byte) 0x09;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0x53;
        bytes[5] = (byte) 0x80;
        bytes[6] = (byte) control;
        bytes[7] = (byte) startHour;
        bytes[8] = (byte) startMin;
        bytes[9] = (byte) endHour;
        bytes[10] = (byte) endMin;
        bytes[11] = (byte) interval;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }


    /**
     * 设置闹钟
     *
     * @param id     索引 0-7
     * @param status 0：关闭闹钟提醒功能 1：开启闹钟提醒功能
     * @param hour   小时
     * @param minute 分钟
     * @param repeat 提醒规则
     */
    public static void setAlertClock(int id, int status, int hour, int minute, int repeat, BleDevice bleDevice, BleWriteCallback callback) {
        byte[] bytes = new byte[11];
        bytes[0] = (byte) 0xAB;
        bytes[1] = (byte) 0;
        bytes[2] = (byte) 8;
        //数据id + status 共 3 bytes
        bytes[3] = (byte) 0xff;
        bytes[4] = (byte) 0x73;
        bytes[5] = (byte) 0x80;
        //数据值
        bytes[6] = (byte) id;
        bytes[7] = (byte) status;
        bytes[8] = (byte) hour;
        bytes[9] = (byte) minute;
        bytes[10] = (byte) repeat;
        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
    }
    /**
     * OK
     */


//    /**
//     * 防丢
//     *
//     * @param control 0关  1开
//     */
//    public static void setAntiLostAlert(int control, BleDevice bleDevice, BleWriteCallback callback) {
//        byte[] bytes = new byte[7];
//        bytes[0] = (byte) 0xAB;
//        bytes[1] = (byte) 0;
//        bytes[2] = (byte) 4;
//        bytes[3] = (byte) 0xFF;
//        bytes[4] = (byte) 0x7A;
//        bytes[5] = (byte) 0x80;
//        bytes[6] = (byte) control;
//        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
//    }
//
//
//    /**
//     * 获取OTA MAC地址
//     * 先发0x28获取OTA地址 再发0x25进入OTA模式
//     */
//    public static void bootloaderMac(BleDevice bleDevice, BleWriteCallback callback) {
//        byte[] bytes = new byte[7];
//        bytes[0] = (byte) 0xAB;
//        bytes[1] = (byte) 0;
//        bytes[2] = (byte) 4;
//        bytes[3] = (byte) 0xFF;
//        bytes[4] = (byte) 0x28;
//        bytes[5] = (byte) 0x80;
//        bytes[6] = (byte) 1;
//        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
//    }
//
//    /**
//     * 进入bootloader hs6620专用，发0x25进入
//     */
//    public static void bootloader(BleDevice bleDevice, BleWriteCallback callback) {
//        byte[] bytes = new byte[7];
//        bytes[0] = (byte) 0xAB;
//        bytes[1] = (byte) 0;
//        bytes[2] = (byte) 4;
//        bytes[3] = (byte) 0xFF;
//        bytes[4] = (byte) 0x25;
//        bytes[5] = (byte) 0x80;
//        bytes[6] = (byte) 1;
//        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
//    }
//
//
//    /**
//     * 解绑3003通话手环
//     */
//    public static void unbind(BleDevice bleDevice, BleWriteCallback callback) {
//        byte[] bytes = new byte[6];
//        bytes[0] = (byte) 0xAB;
//        bytes[1] = (byte) 0;
//        bytes[2] = (byte) 3;
//        bytes[3] = (byte) 0xFF;
//        bytes[4] = (byte) 0xA1;
//        bytes[5] = (byte) 0x80;
//        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
//    }
//
//    /**
//     * BLE传输高低速切换
//     *
//     * @param control 1 高速 0 低速
//     */
//    public static void switchSpeed(int control, BleDevice bleDevice, BleWriteCallback callback) {
//        byte[] bytes = new byte[7];
//        bytes[0] = (byte) 0xAB;
//        bytes[1] = (byte) 0;
//        bytes[2] = (byte) 4;
//        bytes[3] = (byte) 0xFF;
//        bytes[4] = (byte) 0x87;
//        bytes[5] = (byte) 0x80;
//        bytes[6] = (byte) control;
//        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
//    }
//
//    /**
//     * 开始发送图片
//     *
//     * @param dataLength 数据长度
//     * @param req        序号
//     * @param crc        crc值
//     * @param end        0 ----> 继续 1 ----> 结束
//     */
//    public static void startSendPic(int dataLength, int req, int crc, int end, BleDevice bleDevice, BleWriteCallback callback) {
//        byte[] bytes = new byte[8];
//        //头
//        bytes[0] = (byte) 0xAD;
//        //数据长度
//        bytes[1] = (byte) (dataLength / 256);
//        bytes[2] = (byte) (dataLength % 256);
//        //序号
//        bytes[3] = (byte) (req / 256);
//        bytes[4] = (byte) (req % 256);
//        //CRC
//        bytes[5] = (byte) (crc / 256);
//        bytes[6] = (byte) (crc % 256);
//        //继续还是结束
//        bytes[7] = (byte) (end);
//        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
//    }
//
//    /**
//     * 发送样式
//     *
//     * @param R
//     * @param G
//     * @param B
//     * @param style
//     * @param location
//     */
//    public static void sendStyle(int R, int G, int B, int style, int location, BleDevice bleDevice, BleWriteCallback callback) {
//        byte[] bytes = new byte[10];
//        //头
//        bytes[0] = (byte) 0xAB;
//        //数据长度
//        bytes[1] = (byte) 0;
//        bytes[2] = (byte) 7;
//        //ID
//        bytes[3] = (byte) 0xFF;
//        bytes[4] = (byte) 0x9C;
//        //R
//        bytes[5] = (byte) R;
//        //G
//        bytes[6] = (byte) G;
//        //B
//        bytes[7] = (byte) B;
//        //样式
//        bytes[8] = (byte) style;
//        //位置
//        bytes[9] = (byte) location;
//        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
//    }
//
//    /**
//     * 发送图片信息
//     *
//     * @param bytes
//     */
//    public static void sendImageContent(byte[] bytes, BleDevice bleDevice, BleWriteCallback callback) {
//        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
//    }
//
//
//    public static void reply(int id, BleDevice bleDevice, BleWriteCallback callback) {
//        byte[] bytes = new byte[3];
//        bytes[0] = (byte) 0xE0;
//        bytes[1] = (byte) id;
//        bytes[2] = (byte) 0x01;
//        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
//    }
//
//
//    /**
//     * 获取手机MTU值
//     *
//     * @param type 0x00 获取MTU  0x01 响应
//     */
//    public static void getMtu(int type, BleDevice bleDevice, BleWriteCallback callback) {
//        byte[] bytes = new byte[7];
//        bytes[0] = (byte) 0xAB;
//        bytes[1] = (byte) 0;
//        bytes[2] = (byte) 4;
//        bytes[3] = (byte) 0xFF;
//        bytes[4] = (byte) 0xA7;
//        bytes[5] = (byte) 0x80;
//        bytes[6] = (byte) type;
//        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
//    }
//
//    /**
//     * 新表盘 ---- 序号
//     *
//     * @param dataLength 数据长度
//     * @param req        序号
//     * @param crc        crc验证
//     * @param status     status
//     */
//    public static void sendDialReq(int dataLength, int req, int crc, int status, BleDevice bleDevice, BleWriteCallback callback) {
//
//        byte[] bytes = new byte[8];
//        //头
//        bytes[0] = (byte) 0xB0;
//        //数据长度
//        bytes[1] = (byte) (dataLength / 256);
//        bytes[2] = (byte) (dataLength % 256);
//        //序号
//        bytes[3] = (byte) (req / 256);
//        bytes[4] = (byte) (req % 256);
//        //CRC
//        bytes[5] = (byte) (crc / 256);
//        bytes[6] = (byte) (crc % 256);
//        //继续还是结束
//        bytes[7] = (byte) (status);
//        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
//    }
//
//    /**
//     * 运动、吃药、看书、出行提醒设置
//     *
//     * @param type    0:运动提醒 1:吃药提醒 2.看书提醒 3.出行提醒
//     * @param index   提醒索引（最多开8个）
//     * @param control 0：关闭提醒功能 1：开启提醒功能
//     * @param hour    提醒时间之小时
//     * @param min     提醒时间之分钟
//     * @param other
//     */
//    public static void setRemind(int type, int index, int control, int hour, int min, int other, BleDevice bleDevice, BleWriteCallback callback) {
//        byte[] bytes = new byte[11];
//        //头
//        bytes[0] = (byte) 0xAB;
//        //数据长度
//        bytes[1] = (byte) 0x00;
//        bytes[2] = (byte) 0x08;
//        //ID
//        bytes[3] = (byte) 0xFF;
//        bytes[4] = (byte) 0x9E;
//        //提醒类型
//        bytes[5] = (byte) type;
//        //提醒索引
//        bytes[6] = (byte) index;
//        //开关
//        bytes[7] = (byte) control;
//        //小时
//        bytes[8] = (byte) hour;
//        //分钟
//        bytes[9] = (byte) min;
//        //其他
//        bytes[10] = (byte) other;
//        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
//    }
//
//
//    /**
//     * 心率提醒
//     *
//     * @param control 0：关闭提醒功能 1：开启提醒功能
//     * @param hr      警戒值预设参数：100（默认）、110、120、130、140、150
//     */
//    public static void heartReminder(int control, int hr, BleDevice bleDevice, BleWriteCallback callback) {
//        byte[] bytes = new byte[8];
//        //头
//        bytes[0] = (byte) 0xAB;
//        //数据长度
//        bytes[1] = (byte) 0x00;
//        bytes[2] = (byte) 0x05;
//        //ID
//        bytes[3] = (byte) 0xFF;
//        bytes[4] = (byte) 0x9F;
//        //默认值
//        bytes[5] = (byte) 0x80;
//        //开关
//        bytes[6] = (byte) control;
//        //预警值
//        bytes[7] = (byte) hr;
//        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
//    }
//
//    /**
//     * 远眺提醒
//     *
//     * @param control 0：关闭提醒功能 1：开启提醒功能
//     * @param value   警戒值预设参数：30分钟、45分钟、60分钟、90分钟、120分钟（默认）、150分钟、200分钟、250分钟
//     */
//    public static void distantReminder(int control, int value, BleDevice bleDevice, BleWriteCallback callback) {
//        byte[] bytes = new byte[8];
//        //头
//        bytes[0] = (byte) 0xAB;
//        //数据长度
//        bytes[1] = (byte) 0x00;
//        bytes[2] = (byte) 0x05;
//        //ID
//        bytes[3] = (byte) 0xFF;
//        bytes[4] = (byte) 0xA0;
//        //默认值
//        bytes[5] = (byte) 0x80;
//        //开关
//        bytes[6] = (byte) control;
//        //预警值
//        bytes[7] = (byte) value;
//        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
//    }
//
//    /**
//     * 新表盘市场 ---- 数据
//     *
//     * @param bytes
//     */
//    public static void sendDialData(byte[] bytes, BleDevice bleDevice, BleWriteCallback callback) {
//        BleManager.getInstance().write(bleDevice, FastBle.SERVICE_UUID, FastBle.WRITE_CHARACTERISTIC_UUID, bytes, callback);
//    }


    /**
     * **************************************************************
     * 以下为工具                                                   *
     * **************************************************************
     */
    private static String cut(String s, int n) {
        byte[] utf8 = s.getBytes();
        if (utf8.length < n) n = utf8.length;
        int n16 = 0;
        int advance;
        int i = 0;
        while (i < n) {
            advance = 1;
            if ((utf8[i] & 0x80) == 0) i += 1;
            else if ((utf8[i] & 0xE0) == 0xC0) i += 2;
            else if ((utf8[i] & 0xF0) == 0xE0) i += 3;
            else {
                i += 4;
                advance = 2;
            }
            if (i <= n) n16 += advance;
        }
        return s.substring(0, n16);
    }

    /**
     * 拼接字节数组
     *
     * @param data1
     * @param data2
     * @return
     */
    private static byte[] addBytes(byte[] data1, byte[] data2) {
        byte[] data3 = data1;
        if (data2 != null && data2.length != 0) {
            data3 = new byte[data1.length + data2.length];
            System.arraycopy(data1, 0, data3, 0, data1.length);
            System.arraycopy(data2, 0, data3, data1.length, data2.length);
        }
        return data3;
    }

    /**
     * 字节分割
     *
     * @param bytes
     * @return
     */
    private static byte[] handleMessageBytes(byte[] bytes) {
        List<Integer> datas = bytesToArrayList(bytes);
        if (datas.get(0) == 0xAB) {
            //消息通知的数据需要处理成以上格式
            if (bytes.length > 20) {
                byte[] firstPacket = new byte[20];
                System.arraycopy(bytes, 0, firstPacket, 0, 20);

                byte[] otherPackets = new byte[bytes.length - 20];
                System.arraycopy(bytes, 20, otherPackets, 0, bytes.length - 20);
                //后面的所有数据
                ArrayList<byte[]> bytes1 = new ArrayList<>();
                for (int i = 0; i < otherPackets.length / 19 + 1; i++) {
                    byte[] temp = new byte[19];
                    byte[] temp1 = new byte[otherPackets.length % 19];
                    if (i < otherPackets.length / 19) {
                        System.arraycopy(otherPackets, i * 19, temp, 0, 19);
                        bytes1.add(temp);
                    } else {
                        System.arraycopy(otherPackets, i * 19, temp1, 0, otherPackets.length % 19);
                        bytes1.add(temp1);
                    }
                }

                //加上序号
                ArrayList<byte[]> newBytes = new ArrayList<>();
                for (int i = 0; i < bytes1.size(); i++) {
                    byte[] bytes2 = addBytes(new byte[]{(byte) i}, bytes1.get(i));
                    newBytes.add(bytes2);
                }

                //全部拼接起来
                bytes = new byte[20 + 20 * (otherPackets.length / 19) + otherPackets.length % 19 + 1];
                System.arraycopy(firstPacket, 0, bytes, 0, 20);
                for (int i = 0; i < newBytes.size(); i++) {
                    if (i < newBytes.size() - 1) {
                        System.arraycopy(newBytes.get(i), 0, bytes, 20 * (i + 1), 20);
                    } else {
                        System.arraycopy(newBytes.get(i), 0, bytes, 20 * (i + 1), otherPackets.length % 19 + 1);
                    }
                }

            }
        }


        return bytes;
    }

    /**
     * 字节数组转化成集合
     */
    private static List<Integer> bytesToArrayList(byte[] bytes) {
        List<Integer> datas = new ArrayList<>();
        for (int i = 0; i < bytes.length; i++) {
            datas.add(bytes[i] & 0xff);
        }
        return datas;
    }

    //字符串转int
    private static int string2Int(String string) {
        int length = string.length();
        int number = 0;
        for (int i = 0; i < length; i++) {
            String str = string.substring(i, i + 1);
            //左移位
            int yiwei = i * 4;
            int anInt;
            if (str.equals("+")) {
                anInt = 10;
            } else {
                anInt = Integer.parseInt(str);
            }
            int finalInt = anInt << yiwei;
            number += finalInt;
        }


        return number;
    }

    public static byte bit2Byte(String bitStr) {
        int re, len;
        if (null == bitStr) {
            return 0;
        }
        len = bitStr.length();
        if (len != 4 && len != 8) {
            return 0;
        }
        if (len == 8) {// 8 bit处理
            if (bitStr.charAt(0) == '0') {// 正数
                re = Integer.parseInt(bitStr, 2);
            } else {// 负数
                re = Integer.parseInt(bitStr, 2) - 256;
            }
        } else {//4 bit处理
            re = Integer.parseInt(bitStr, 2);
        }
        return (byte) re;
    }
}
