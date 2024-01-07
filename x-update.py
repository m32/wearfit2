#!/usr/bin/env vpython3
url = 'http://huitent.iwhop.com/wearfitapi/sys/v2/version?'

import urllib.parse
import urllib.request
import json

values = {
    'versionCode': 10085,
    'locale': 'pl_PL',
    'type': 1,
}

data = urllib.parse.urlencode(values)
url = url+data
if 1:
    req = urllib.request.Request(url)
    with urllib.request.urlopen(req) as response:
        the_page = response.read()
        print(the_page)
        msg = json.loads(the_page)
        print(msg)
else:
    the_page = b'{"code":10000,"msg":"\xe5\xb7\xb2\xe7\xbb\x8f\xe6\x98\xaf\xe6\x9c\x80\xe6\x96\xb0\xe7\x89\x88\xe6\x9c\xac\xe4\xba\x86\xef\xbc\x81","errors":null,"data":null}'
    # Already the latest version!
    msg = json.loads(the_page)
    print(msg)
