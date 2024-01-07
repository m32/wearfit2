class Commands:
    def cut(self, a, i):
        i = min(i, len(a))
        i0 = 0
        i1 = 0
        while i0 < i:
            i2 = 0
            if (a[i0] & 128) != 0:
                if (a[i0] & 224) != 192:
                    if (a[i0] & 240) != 224:
                        i0 = i0 + 4
                        i2 = 2
                    else:
                        i0 = i0 + 3
                        i2 = 1
                else:
                    i0 = i0 + 2
                    i2 = 1
            else:
                i0 = i0 + 1
                i2 = 1
            if i0 <= i:
                i1 = i1 + i2
        return a[:i1]

    '''
    def string2Int(String s):
        int i = s.length();
        int i0 = 0;
        int i1 = 0;
        while(i0 < i) {
            int i2 = i0 + 1;
            String s0 = s.substring(i0, i2);
            int i3 = i0 * 4;
            i1 = i1 + (((s0.equals((Object)"+")) ? 10 : Integer.parseInt(s0)) << i3);
            i0 = i2;
        }
        return i1;
    '''

    '''
        camera pause
        a = bytes([0xAB, 0x00, 0x04, 0xFF, 0x79, 0x80, 0x00])
        camera resume
        a = bytes(([0xAB, 0x00, 0x04, 0xFF, 0x79, 0x80, 0x01])
        FG().sendData(byteArrayOfInts(0xAB, 0x00, 0x04, 0xFF, 0x01, 0x01, p1))
        shake camera
        val cam = byteArrayOfInts(0xAB, 0x00, 0x04, 0xFF, 0x79, 0x80, if (!camera) 0x01 else 0x00)
        update bat
        val bat = byteArrayOfInts(0xAB, 0x00, 0x05, 0xFF, 0x91, 0x80, 0x00, percentage)
    '''

    def bodyTempMeasure(self, i):
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x86, 0x80, i])
        return a

    def bootloader(self):
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x25, 0x80, 0x01])
        return a

    def bootloaderMac(self):
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x28, 0x80, 0x01])
        return a

    def bootloaderWatch(self):
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x9a, 0x80, 0x01])
        return a

    def drinkWater(self, i0, i1, i2, i3, i4, i5):
        a = bytes([ 0xab, 0x00, 0x09, 0xff, 0x53, 0x80, i0, i1, i2, i3, i4, i5]);
        return a

    def getBattery(self):
        a = bytes([0xab, 0x00, 0x03, 0xff, 0xb2, 0x80])
        return a

    def getBatteryInfo(self):
        a = bytes([0xab, 0x00, 0x03, 0xff, 0x91, 0x80])
        # dt78
        #a = bytes([0xab, 0x00, 0x04, 0xff, 0x91, 0x80, 0x01])
        return a

    def getBiaoPan(self):
        a = bytes([0xab, 0x00, 0x03, 0xff, 0x95, 0x80, 0x00])
        return a

    def getConfig(self):
        a = bytes([0xab, 0x00, 0x03, 0xff, 0x9b, 0x80])
        return a

    def getClassicMac(self):
        a = bytes([0xab, 0x00, 0x03, 0xff, 0xa6, 0x80])
        return a

    def getEcgHistoryData(self, i, y, m, d, h, mi):
        a = bytes([0xab, 0x00, 0x09, 0xff, 0x26, 0x80, i, y-2000, m, d, h, mi])
        return a

    def getExerciseRecord(self, y, m, d, h, mi):
        a = bytes([0xab, 0x00, 0x09, 0xff, 0x29, 0x80, 0, y-2000, m, d, h, mi])
        return a

    def getTrueTimeRate(self, i):
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x84, 0x80, i])
        return a

    def getVersionInfo(self):
        a = bytes([0xab, 0x00, 0x03, 0xff, 0x92, 0x80])
        return a

    def jingQi(self, i, j):
        a = bytes([0xab, 0x00, 0x05, 0xff, 0x24, 0x80, i, j])
        return a

    def peidui(self):
        a = bytes([0xab, 0x00, 0x04, 0xff, 0xa0, 0x80, 1])
        return a

    def sendContactName(self, i, s):
        if not s:
            return
        s = self.cut(s, 21)
        sdiv, smod = divmod(len(s)+3, 256)
        a = bytes([0xab, sdiv, smod, 0xff, 0xa2, i]) + s
        return a

    '''
    public void sendContactPhone(int i, String s) {
        byte[] a = null;
        if (s == null) {
            return;
        }
        String s0 = s.replace((CharSequence)(Object)" ", (CharSequence)(Object)"");
        if (s0.contains((CharSequence)(Object)"+")) {
            s0.replace((CharSequence)(Object)"+", (CharSequence)(Object)"A");
            int i0 = s0.length();
            if (i0 % 2 != 0) {
                int i1 = i0 / 2 + 1;
                a = new byte[i1];
                int i2 = 0;
                for(; i2 < i1; i2 = i2 + 1) {
                    String s1 = null;
                    if (i2 >= i1 + -1) {
                        int i3 = i2 * 2;
                        s1 = s0.substring(i3, i3 + 1);
                    } else {
                        int i4 = i2 * 2;
                        s1 = s0.substring(i4, i4 + 2);
                    }
                    a[i2] = (byte)(int)(byte)this.string2Int(s1);
                }
            } else {
                int i5 = i0 / 2;
                a = new byte[i5];
                int i6 = 0;
                for(; i6 < i5; i6 = i6 + 1) {
                    int i7 = i6 * 2;
                    a[i6] = (byte)(int)(byte)this.string2Int(s0.substring(i7, i7 + 2));
                }
            }
        } else {
            int i8 = s0.length();
            if (i8 % 2 != 0) {
                int i9 = i8 / 2 + 1;
                a = new byte[i9];
                int i10 = 0;
                for(; i10 < i9; i10 = i10 + 1) {
                    String s2 = null;
                    if (i10 >= i9 + -1) {
                        int i11 = i10 * 2;
                        s2 = s0.substring(i11, i11 + 1);
                    } else {
                        int i12 = i10 * 2;
                        s2 = s0.substring(i12, i12 + 2);
                    }
                    a[i10] = (byte)(int)(byte)this.string2Int(s2);
                }
            } else {
                int i13 = i8 / 2;
                a = new byte[i13];
                int i14 = 0;
                for(; i14 < i13; i14 = i14 + 1) {
                    int i15 = i14 * 2;
                    a[i14] = (byte)(int)(byte)this.string2Int(s0.substring(i15, i15 + 2));
                }
            }
        }
        byte[] a0 = new byte[7];
        a0[0] = (byte)(-85);
        a0[1] = (byte)(int)(byte)((a.length + 4) / 256);
        a0[2] = (byte)(int)(byte)((a.length + 4) % 256);
        a0[3] = (byte)(-1);
        a0[4] = (byte)(-93);
        a0[5] = (byte)(int)(byte)i;
        a0[6] = (byte)(int)(byte)s0.length();
        this.broadcastData("\u8bbe\u7f6e\u8054\u7cfb\u4eba\u7535\u8bdd\u53f7\u7801", com.wakeup.wearfit2.util.DataHandlerUtils.addBytes(a0, a));
    }
    '''
    '''
    public void sendImageContent(byte[] a) {
        this.broadcastData("\u5c4f\u4fdd\u63a8\u9001\u56fe\u7247", a);
    }
    '''

    '''
    def nameConvert(self, name, pos):
        val start = byteArrayOfInts(0xAB, 0x00)
        val type = byteArrayOfInts(0xFF, 0xA2)
        val msg = name.toByteArray()
        val msgByte = if (msg.size > 33){
            msg.slice(0 until 33)
        } else {
            msg.slice(msg.indices)
        }
        val lenD = (msgByte.size + 3).toByte()
        //val lenM = (msgByte.size).toByte()

        return if (msgByte.size <= 14){
            start + lenD + type + pos.toByte() +  msgByte
        } else {
            val msg0 = msgByte.slice(0 until 14)
            val msg1 = msgByte.slice(14 until msgByte.size)
            start + lenD + type + pos.toByte() + msg0 + 0x00 + msg1
        }

    }
    '''
    '''
    private fun numberConvert(number: String, pos: Int): ByteArray{
        val start = byteArrayOfInts(0xAB, 0x00)
        val type = byteArrayOfInts(0xFF, 0xA3)

        val lenN = (number.length).toByte()
        var no = if (number.length % 2 != 0 ){
            number + "0"
        } else {
            number
        }
        no = no.replace("+", "A")
        var phone : ByteArray = byteArrayOf()
        for (x in 0 until no.length/2) {
            val digit = no.substring(x * 2, (x + 1) * 2)
            phone += (digit.reversed().toInt(16)).toByte()

        }
        val lenD = (4 + phone.size).toByte()
        return start + lenD + type + pos.toByte() + lenN + phone

    }
    '''

    def sendWeatherInfo(self, data):
        i = 6
        a = [0xab, 0x00, 0x11, 0xff, 0x7e, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00]
        for code, temperature in data:
            # 0x00 - sun + cloud
            # 0x10 - sun
            # 0x20 - snow
            # 0x30 - rain
            # 0x40 - clouds
            # 0x50 - tornado
            # 0x60 - wind
            # 0x70 - sun + haze
            a[i+0] = (code * 16) | (1 if temperature < 0 else 0)
            a[i+1] = abs(temperature)
            i += 2
            if i > len(a):
                break
        return bytes(a)

    def set12HourSystem(self, i):
        # i=on/off (1/0)
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x7c, 0x80, i])
        return a

    def setAlertClock(self, id, state, h, m, repeat):
        a = bytes([0xab, 0x00, 0x08, 0xff, 0x73, 0x80, id, state, h, m, repeat])
        return a

    def setAntiLostAlert(self, i):
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x7a, 0x80, i])
        return a

    def setBandLanguage(self, i):
        # i = DEVICE_NAME == "KOSPET MAGIC 2" AND i == 18 else i
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x7b, 0x80, i])
        return a

    def setBiaoPan(self, i0, i1):
        # 0x0f, 0x0c
        # 0x0f, 0x0d
        # 0x0f, 0x0e
        a = bytes([0xab, 0x00, 0x06, 0xff, 0x95, 0x80, 0x01, i0, i1])
        return a

    def setFace(self, no):
        faces = {
            0: (0x0f, 0x0c),
            1: (0x0f, 0x0d),
            2: (0x0f, 0x0e),
        }
        face = faces.get(no, None)
        if face is None:
            face = (0x0f, 0x00)
        return self.setBiaoPan(face[0], face[1])

    def setBloodPressureReference(self, isbpref, sp, bp):
        a = bytes([0xab, 0x00, 0x06, 0xff, 0x95, 0x80, isbpref, sp, bp])
        return a

    def setCalibrationTime(self, i0, i1):
        a = bytes([0xab, 0x00, 0x05, 0xff, 0x7d, 0x80, i0, i1])
        return a

    def setClearData(self, i=0):
        # i=on/off (1/0)
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x23, 0x80, 0x00])
        return a

    def setContactNum(self, i0, i1):
        # if contact is SOS -> i0=contactNo of i1 contacts
        a = bytes([0xab, 0x00, 0x05, 0xff, 0xa5, 0x80, i0, i1])
        return a

    def setDisturbanceModel(self, on, startH, startM, stopH, stopM):
        a = bytes([0xab, 0x00, 0x08, 0xff, 0x76, 0x80, on, startH, startM, stopH, stopM])
        return a

    def setFindBand(self):
        a = bytes([0xab, 0x00, 0x03, 0xff, 0x71, 0x80])
        return a

    def setHangUpPhone(self):
        a = bytes([0xab, 0x00, 0x03, 0xff, 0x81, 0x00])
        return a
    
    def setHrWarn(self, i):
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x85, 0x80, i])
        return a

    def setOnTimeMeasure(self, i):
        # i=on/off (1/0) measure hourly
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x78, 0x80, i])
        return a

    def setOnceKeyMeasure(self, i):
        # i=start/stop (1/0)
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x32, 0x80, i])
        return a

    def setOnceOrRealTimeMeasure(self, i0, i1):
        # i0: once/rt, timeout
        #   65 = miany, 40000
        #   33/34 = blood pressure, 3600
        #   9/10 = heart rate, 2000
        #   17/18 = oxygen, 2000
        #   129 = tiwent, 40000
        # i1 = start/stop (1/0)
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x31, i0, i1])
        return a

    def setPowerSaving(self, i):
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x96, 0x80, i])
        return a

    def setResetBand(self):
        a = bytes([0xab, 0x00, 0x03, 0xff, 0xff, 0x80])
        return a

    def setSedentarinessWarn(self, i, on, startH, startM, endH, endM):
        a = bytes([0xab, 0x00, 0x09, 0xff, 0x75, 0x80, on, startH, startM, endH, endM, i])
        return a

    def setSharkTakePhoto(self, i):
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x79, 0x80, i])
        return a

    def setShowPingBao(self, i):
        a = bytes([0xab, 0x00, 0x04, 0xff, 0xe5, 0x80, i])
        return a

    def setSleepTimeRange(self, i, startH, startM, endH, endM):
        a = bytes([0xab, 0x00, 0x08, 0xff, 0x7f, 0x80, i, startH, startM, endH, endM])
        return a

    #setSmartWarn
    def message(self, app, msg):
        #requestMtu(MTU).enqueue()
        if len(msg) > 125:
            msg[124] = 95
            msg = msg[:125]
        start = bytes([0xAB, 0x00, len(msg)+5])
        mtypes = {
            1 : bytes([0xFF, 0x72, 0x80, 0x0A, 0x02]), #whatsapp
            2 : bytes([0xFF, 0x72, 0x80, 0x0F, 0x02]), #twitter
            3 : bytes([0xFF, 0x72, 0x80, 0x01, 0x02]), #caller
            4 : bytes([0xFF, 0x72, 0x80, 0x12, 0x02]), #instagram
            5 : bytes([0xFF, 0x72, 0x80, 0x11, 0x02]), #facebook
            6 : bytes([0xFF, 0x72, 0x80, 0x10, 0x02]), #messenger
            7 : bytes([0xFF, 0x72, 0x80, 0x08, 0x02]), #skype
            8 : bytes([0xFF, 0x72, 0x80, 0x07, 0x02]), #QQ
            9 : bytes([0xFF, 0x72, 0x80, 0x09, 0x02]), #wechat
            10: bytes([0xFF, 0x72, 0x80, 0x0E, 0x02]), #line
            11: bytes([0xFF, 0x72, 0x80, 0x13, 0x02]), #weibo
            12: bytes([0xFF, 0x72, 0x80, 0x14, 0x02]), #kakaotalk
            13: bytes([0xFF, 0x72, 0x80, 0x18, 0x02]), #telegram
            14: bytes([0xFF, 0x72, 0x80, 0x16, 0x02]), #viber
        }
        mtype = mtypes.get(app, None)
        if mtype is None:
            mtype = bytes([0xFF, 0x72, 0x80, 0x03, 0x02]) #messages

        if len(msg) <= 12:
            a = start + mtype + msg
            result = [a]
        else:
            result = []
            result.append(start + mtype + msg[:12])

            no = 0
            for i in range(12, len(msg), 19):
                result.append(bytes([no])+msg[i:i+19])
                no += 1
        return result

    #stepsRequest
    def setSyncData(self, y, m, d, h, mi):
        a = bytes([0xab, 0x00, 0x09, 0xff, 0x51, 0x80, 0x00, y-2000, m, d, h, 0x00])
        return a

    def setSyncDataHr(self, startY, startM, startH, startMI, endY, endM, endD, endH, endMI):
        a = bytes([0xab, 0x00, 0x0e, 0xff, 0x51, 0x80, 0x00, startY-2000, startM, startD, startH, startMI, endY-2000, endM, endD, endH, endMI])
        return a

    def setSyncSleepData(self, y, m, d, h, mi):
        a = bytes([0xab, 0x00, 0x09, 0xff, 0x52, 0x80, 0x00, y-2000, m, d, h, 0x00])
        return a

    def setTime(self, y, m, d, h, mi, s):
        a = bytes([0xab, 0x00, 0x0b, 0xff, 0x93, 0x80, 0x00, y//256, y%256, m, d, h, mi, s])
        return a

    def setTimeSync(self, y, m, d, h, mi, s):
        a = bytes([0xab, 0x00, 0x11, 0xff, 0x93, 0x80, 0x00, (y&65280)>>8, y&255, m, d, h, mi, s])
        return a

    def endCall(self):
        # dt78
        a = bytes([0xab, 0x00, 0x05, 0xff, 0x72, 0x80, 0x02, 0x02])
        return a

    def setUpHandLightScreen(self, i):
        # i=on/off (1/0)
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x77, 0x80, i])
        return a

    def setUserInfo(self, band, step, age, height, weight, target, si=1, sheshidu=1):
        # t1s != 115
        if band != 115:
            a = bytes([0xab, 0x00, 0x0a, 0xff, 0x74, 0x80, step, age, height, weight, si, target//1000, sheshidu])
        else:
            a = bytes([0xab, 0x00, 0x0b, 0xff, 0x74, 0x80, step, age, height, weight, 0, 0, si, taget//1000])
        return a

    def sethongwai(self, i):
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x98, 0x80, i])
        return a

    def startMeasureEcg(self):
        a = bytes([0xac, 0x01])
        return a

    def startSendPic(self, i0, i1, i2, i3):
        a = bytes([0xad, i0//256, i0%256, i1//256, i1%256, i2//256, i2%256, i3//256, i3%256])
        return a

    def stoptMeasureEcg(self):
        a = bytes([0xac, 0x00])
        return a

    def switchSpeed(self, i):
        # i=on/off (1/0)
        a = bytes([0xab, 0x00, 0x04, 0xff, 0x87, 0x80, i])
        return a

    def unbind(self):
        a = bytes([0xab, 0x00, 0x03, 0xff, 0xa1, 0x80])
        return a

    '''
    def setup(self):
        this.powerSavingOn = com.wakeup.wearfit2.util.SPUtils.getBoolean(this.getApplicationContext(), "powerSavingOn", false)
        this.setBandLanguage()
        this.mManager.setTimeSync()
        this.mManager.setUpHandLightScreen(isUpLightScreenWarnOn ? 1 : 0)
        this.mManager.setOnTimeMeasure(isOnTimeMeasureWarnOn ? 1 : 0)
        this.mManager.setHrWarn(isHrWarnOn ? 1 : 0)
        this.mManager.setPowerSaving((this.powerSavingOn) ? 1 : 0)
        if (com.wakeup.wearfit2.util.SPUtils.getInt(this.getApplicationContext(), "set_watch_bg", 0) != 0) {
            boolean b = com.wakeup.wearfit2.util.SPUtils.getBoolean((android.content.Context)this, "PING_BAO_SWITCH_STATUS", true);
            this.mManager.setShowPingBao(b ? 1 : 0);
        }
        int i = com.wakeup.wearfit2.util.SPUtils.getInt(this.getApplicationContext(), "jiuzuo_time", 45);
        this.mManager.setSedentarinessWarn(i);
        this.mManager.setDisturbanceModel();
        this.mManager.setAntiLostAlert(isLostWarnOn ? 1 : 0);
        this.mManager.set12HourSystem(is12HourSystemWarnOn ? 1 : 0);
        android.database.sqlite.SQLiteDatabase a = writableDatabase;
        if (a != null) {
            String[] a0 = new String[1];
            a0[0] = "*";
            android.database.Cursor a1 = a.query("clock2", a0, (String)null, (String[])null, (String)null, (String)null, (String)null);
            if (a1 != null) {
                int i0 = a1.getCount();
                if (i0 == 0) {
                    this.mManager.setAlertClock(0, 0, 0, 0, 0);
                }
                String s = TAG;
                StringBuilder a2 = new StringBuilder();
                a2.append("count: ");
                a2.append(String.valueOf(i0));
                android.util.Log.i(s, a2.toString());
                Object a3 = a1;
                while(((android.database.Cursor)a3).moveToNext()) {
                    if (((android.database.Cursor)a3).getColumnCount() >= 5) {
                        this.mManager.setAlertClock(((android.database.Cursor)a3).getInt(0) + -1, ((android.database.Cursor)a3).getInt(1), ((android.database.Cursor)a3).getInt(2), ((android.database.Cursor)a3).getInt(3), ((android.database.Cursor)a3).getInt(4));
                    }
                }
            }
        }
        com.wakeup.wearfit2.model.UserModel a4 = (com.wakeup.wearfit2.model.UserModel)org.litepal.LitePal.findFirst(com.wakeup.wearfit2.model.UserModel.class);
        if (a4 == null) {
            com.wakeup.wearfit2.model.UserModel a5 = new com.wakeup.wearfit2.model.UserModel();
            this.mManager.setUserInfo(a5);
        } else {
            this.mManager.setUserInfo(a4);
            this.mManager.setSleepTimeRange(1, a4);
        }
        this.mManager.setSmartWarnNoContent(9, (this.isWeiXinChecked) ? 1 : 0);
        this.mManager.setSmartWarnNoContent(7, (this.isQQChecked) ? 1 : 0);
        this.mManager.setSmartWarnNoContent(19, (this.isWeiBoChecked) ? 1 : 0);
        this.mManager.setSmartWarnNoContent(15, (this.isTwitterChecked) ? 1 : 0);
        this.mManager.setSmartWarnNoContent(10, (this.isWhatsAppChecked) ? 1 : 0);
        this.mManager.setSmartWarnNoContent(14, (this.isLineChecked) ? 1 : 0);
        this.mManager.setSmartWarnNoContent(20, (this.isKakaoTalkChecked) ? 1 : 0);
        this.mManager.setSmartWarnNoContent(21, (this.isFacebookManagerChecked) ? 1 : 0);
        this.mManager.setSmartWarnNoContent(17, (this.isMessengerChecked) ? 1 : 0);
        this.mManager.setSmartWarnNoContent(18, (this.isInstagramChecked) ? 1 : 0);
        this.mManager.setSmartWarnNoContent(8, (this.isSkypeChecked) ? 1 : 0);
        this.mManager.getBiaoPan();
        java.util.List a6 = org.litepal.LitePal.order("order asc").find(com.wakeup.wearfit2.bean.Contacts.class);
        label2: {
            label0: {
                label1: {
                    if (a6 == null) {
                        break label1;
                    }
                    if (a6.size() != 0) {
                        break label0;
                    }
                }
                this.mManager.setContactNum(0, 0);
                break label2;
            }
            try {
                Thread.sleep(1000L);
            } catch(InterruptedException a7) {
                a7.printStackTrace();
            }
            java.util.Iterator a8 = a6.iterator();
            Object a9 = a6;
            Object a10 = a8;
            while(((java.util.Iterator)a10).hasNext()) {
                com.wakeup.wearfit2.bean.Contacts a11 = (com.wakeup.wearfit2.bean.Contacts)((java.util.Iterator)a10).next();
                if (a11.isSos()) {
                    this.mManager.setContactNum(a11.getOrder(), ((java.util.List)a9).size());
                }
                this.mManager.sendContactName(a11.getOrder(), a11.getName());
                this.mManager.sendContactPhone(a11.getOrder(), a11.getPhoneNumber());
            }
        }
        try {
            Thread.sleep(2000L);
        } catch(InterruptedException a12) {
            a12.printStackTrace();
        }
        this.mManager.getClassicMac();

        langs = {
            'zh_TW': 7,
            'zh': 0,
            'vi': 24,
            'tr': 12,
            'th': 10,
            'ru': 5,
            'ro': 22,
            'pt': 4,
            'pl': 14,
            'ms': 16,
            'ko': 9,
            'ja': 6,
            'iw': 19,
            'it': 2,
            'in': 15,
            'fr': 13,
            'fa': 17,
            'es': 3,
            'en': 1,
            'el': 18,
            'de': 8,
            'cs': 20,
            'ar': 11,
        }
        lang = langs.get(lang, 255)
    '''
