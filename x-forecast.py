#!/usr/bin/env vpython3
url = 'http://www.iwhop.com/weatherapi/weather/weekforecasting'
lat = '53.4372274'
lon = '14.5097729'

import urllib.parse
import urllib.request

values = {
    'lat': lat,
    'lon': lon,
}

data = urllib.parse.urlencode(values)
url = 'http://www.iwhop.com/weatherapi/weather/weekforecasting?'+data
if 1:
    req = urllib.request.Request(url)
    with urllib.request.urlopen(req) as response:
        the_page = response.read()
        print(the_page)
else:
    the_page=b'''\
{
    "msg":"ok",
    "code":200,
    "forecasts":[
        {"code":0,"temperature":23,"date":"2023-05-31"},
        {"code":1,"temperature":13,"date":"2023-06-01"},
        {"code":1,"temperature":10,"date":"2023-06-02"},
        {"code":0,"temperature":12,"date":"2023-06-03"},
        {"code":1,"temperature":16,"date":"2023-06-04"},
        {"code":1,"temperature":15,"date":"2023-06-05"},
        {"code":0,"temperature":15,"date":"2023-06-06"}
    ]
}
'''

import json
msg = json.loads(the_page)
if msg['code'] == 200:
    for e in msg['forecasts']:
        print(e)
