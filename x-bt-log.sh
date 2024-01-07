#!/bin/bash
xwhen=`date +%Y%m%d-%H%M%S`
adb shell logcat >x-bt-log-$xwhen.log
