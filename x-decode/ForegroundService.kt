    private fun updateEsp32(context: Context){
        val dbHandler = MyDBHandler(context, null, null, 1)

        val user = dbHandler.getUser()
        updateUser(user, unit)
        val setPref =  PreferenceManager.getDefaultSharedPreferences(this)
        update12hr(setPref.getBoolean(ST.PREF_12H, false))
        updateHourly(hourly)
        val disp = byteArrayOfInts(0xAB, 0x00, 0x04, 0xFF, 0x23, 0x80, if (setPref.getBoolean(ST.PREF_DISPLAY_OFF, false)) 1 else 0)
        sendData(disp)
        val timeout = byteArrayOfInts(0xAB, 0x00, 0x04, 0xFF, 0x7B, 0x80, setPref.getInt(ST.PREF_TIMEOUT, 10))
        sendData(timeout)

        val level = readBatteryLevel(context)
        updateBat(level)


    }

    private fun sendImage(pos: Int, context: Context){
        val checkG = byteArrayOfInts(0xAD, 0x04, 0x00, 0x00, 0x00, 0x37, 0x9D, 0x00)
        val checkR = byteArrayOfInts(0xAD, 0x04, 0x00, 0x00, 0x00, 0xB5, 0x8C, 0x00)
        val datB = byteArrayOfInts(0xAE, 0x12, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,0x00, 0x00, 0x00, 0x00, 0x00, 0x00)
        val datG = byteArrayOfInts(0xAE, 0x12, 0x00, 0x00, 0x07, 0xE0, 0x07, 0xE0, 0x07, 0xE0, 0x07, 0xE0, 0x07, 0xE0, 0x07, 0xE0, 0x07, 0xE0, 0x07, 0xE0)
        val datR = byteArrayOfInts(0xAE, 0x12, 0x00, 0x00, 0xF8, 0x00, 0xF8, 0x00, 0xF8, 0x00, 0xF8, 0x00, 0xF8, 0x00, 0xF8, 0x00, 0xF8, 0x00, 0xF8, 0x00)
        when {
            pos == 112 -> {
                checkG[1] = 0x02.toByte()
                checkG[3] = (pos/256).toByte()
                checkG[4] = (pos%256).toByte()
                checkG[5] = 0x37.toByte()
                checkG[6] = 0xDF.toByte()
                checkG[7] = 1
                transmitData(checkG, 100, context)
                for (x in 0x0E00 .. 0x0E1F){
                    datR[2] = (x/256).toByte()
                    datR[3] = (x%256).toByte()
                    sendData(datR)
                }
            }
            pos % 28 == 0 -> {
                for (x in pos * 64 until (pos + 1) * 64) { //0x1BFF
                    checkR[3] = (pos / 256).toByte()
                    checkR[4] = (pos % 256).toByte()
                    if (x % 64 == 0) {
                        transmitData(checkR, ((x.toFloat() / 0x1BFF) * 100).toInt(), context)

                    }
                    datR[2] = (x / 256).toByte()
                    datR[3] = (x % 256).toByte()
                    sendData(datR)
                }
            }
            else -> {
                for (x in pos * 64 until (pos + 1) * 64) { //0x1BFF
                    checkG[3] = (pos / 256).toByte()
                    checkG[4] = (pos % 256).toByte()
                    if (x % 64 == 0) {
                        transmitData(checkG, ((x.toFloat() / 0x1BFF) * 100).toInt(), context)

                    }
                    datG[2] = (x / 256).toByte()
                    datG[3] = (x % 256).toByte()
                    sendData(datG)
                }
            }
        }

        //cancelNotification(SERVICE_ID2, context)

    }


    fun uploadFile(pos: Int, context: Context){ val directory = context.cacheDir
        val upload = File(directory, "upload")

        val file = File(upload, "out$pos.hex")
        val check = File(upload, "check$pos.hex")
        if (file.exists() && check.exists()){
            val sum = check.readBytes()
            transmitData(sum, ((pos.toFloat() / 0x70) * 100).toInt(), context)
            val pxls = file.readBytes()
            if (pos == 112){
                val array = ByteArray(20)
                for (b in 0 until  32) {
                    for (y in 0 until 20){
                        array[y] = pxls[(b*20)+y]
                    }
                    sendData(array)
                }
            } else {
                for (b in 0 until  64) {
                    val array = ByteArray(20)
                    for (y in 0 until 20){
                        array[y] = pxls[(b*20)+y]
                    }
                    sendData(array)
                }
            }

        } else {
            sendImage(pos, context)
        }
    }

    fun sendDat(byteArray: ByteArray, pos: Int, context: Context){
        val checkSum = byteArrayOfInts(0xAD, 0x04, 0x00, 0x00, 0x00, 0x37, 0x9D, 0x00)
        val data = byteArrayOfInts(0xAE, 0x12, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,0x00, 0x00, 0x00, 0x00, 0x00, 0x00)

        val crc = CRC16Modbus()
        for (b in byteArray){
            crc.update(b.toInt())
        }
        val sum = crc.crcBytes
        checkSum[3] = (pos / 256).toByte()
        checkSum[4] = (pos % 256).toByte()
        checkSum[5] = sum[1]
        checkSum[6] = sum[0]
        if (pos == 112){
            checkSum[1] = 0x02
            checkSum[7] = 0x01
        }
        transmitData(checkSum, ((pos.toFloat() / 0x70) * 100).toInt(), context)
        val pxls = byteArray
        if (pos == 112){
            for (b in 0 until  32) {
                for (x in 0 until 16) {
                    data[x + 4] = pxls[x + (b * 16)]
                }
                data[2] = ((b + 0x0E00)/ 256).toByte()
                data[3] = ((b + 0x0E00) % 256).toByte()
                sendData(data)
            }
        } else {
            for (b in 0 until  64) {
                for (x in 0 until 16) {
                    data[x + 4] = pxls[x + (b * 16)]
                }
                data[2] = ((b + pos) / 256).toByte()
                data[3] = ((b + pos) % 256).toByte()
                sendData(data)
            }
        }
    }
