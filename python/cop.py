import urllib.request
import urllib.error
import hmac
import datetime
import uuid
import base64
import hashlib
import ssl
import json
import logging

logging.basicConfig(
    filename='log_vessel_delay.log',
    level=logging.DEBUG,
    datefmt='[%Y-%m-%d %H:%M:%S]',
    format='%(asctime)s %(levelname)s %(filename)s [%(lineno)d] %(threadName)s : %(message)s',
)



def hmac_client(domain, apiKey, secureKey, bodyStr, requestPath, method, additionalHeader={}):
    METHOD = method
    HTTP_VERSION = "HTTP/1.1"
    header_x_date = "X-Coscon-Date"
    header_content_MD5 = "X-Coscon-Content-Md5"
    header_digest = "X-Coscon-Digest"
    header_authorization = "X-Coscon-Authorization"
    GMT_FORMAT = "%a, %d %b %Y %H:%M:%S GMT"
    currentTime = datetime.datetime.utcnow().strftime(GMT_FORMAT)
    contentMD5 = hashlib.md5(str(uuid.uuid1()).replace('-', '').encode(encoding='UTF-8')).hexdigest()
    digest = 'SHA-256=' + str(base64.b64encode(hashlib.sha256(bodyStr.encode('utf-8')).digest()), encoding='utf-8')
    request_line = METHOD + ' ' + requestPath + ' ' + HTTP_VERSION
    signStr = (header_x_date + ": " + currentTime + "\n" +
               header_digest + ": " + digest + "\n" +
               header_content_MD5 + ": " + contentMD5 + "\n" +
               request_line)

    signature = str(base64.b64encode(
        hmac.new(key=bytes(secureKey, encoding="utf-8"), msg=bytes(signStr, encoding="utf-8"),
                 digestmod="SHA1").digest()), encoding="utf-8")

    authStr = 'hmac username="' + str(
        apiKey) + '",algorithm="hmac-sha1",headers="' + header_x_date + ' ' + header_digest + ' ' + header_content_MD5 + ' request-line",signature="' + signature + '"'

    # construct the header
    headers = {
        header_digest: digest,
        header_content_MD5: contentMD5,
        header_authorization: authStr,
        header_x_date: currentTime,
    }
    url = domain + requestPath
    try:
        req = urllib.request.Request(url=url, headers=dict(headers, **additionalHeader), data=bodyStr.encode("utf-8"),
                                     method=METHOD)
        response = urllib.request.urlopen(req, context=ssl._create_unverified_context())
        return str(response.read(), encoding="utf-8")
    except urllib.error.HTTPError as e:
        logging.error("Http Error:")
        logging.error(e.msg)
        logging.error(e)
        print("Http Error:")
        print(e.msg)
        print(e)
        return None
    except urllib.error.URLError as e:
        logging.error("URLError:")
        logging.error(e.msg)
        logging.error(e)
        print("URLError:")
        print(e.reason)
        print(e)
        return None



if __name__ == "__main__":

    logging.info("""
            /\_/\  
       ____/ o o \ 
     /~____  =-= / 
    (______)__m_m) 
       """)

    # get api data of oocl
    response = hmac_client(
        # the protocol and domain
        domain="https://api-pp.lines.coscoshipping.com",
        # your key and secret
        apiKey="",
        secureKey="",
        bodyStr="",
        # path begin /service
        requestPath="/service/xxx",
        method="GET",
    )
    print(response)

