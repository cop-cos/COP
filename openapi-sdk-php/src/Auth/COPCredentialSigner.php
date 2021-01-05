<?php
/**
 * PrivateKeySigner
 * PHP version 5
 *
 * @category Class
 * @package  COP\Client
 * @author   Chen Jipeng
 * @link     https://github.com/cop-cos/COP
 */

namespace COP\Client\Auth;

use COP\Client\Auth\Signer;
use Psr\Http\Message\RequestInterface;
use Psr\Http\Message\UriInterface;
/**
 * PrivateKeySigner
 *
 * @category Class
 * @package  COP\Client
 * @author   Chen Jipeng
 * @link     https://github.com/cop-cos/COP
 */
class COPCredentialSigner implements Signer
{
    protected static $X_DATE = "X-Coscon-Date";
    protected static $X_CONTENT_MD5 = "X-Coscon-Content-Md5";
    protected static $X_AUTHORIZATION = "X-Coscon-Authorization";
    protected static $X_DIGEST = "X-Coscon-Digest";
    protected static $X_REQUEST_LINE = "request-line";
    protected static $X_COSCON_HMAC = "X-Coscon-Hmac";
    /**
     * apiKey
     *
     * @var string
     */
    protected $apiKey;

    /**
     * secretKey
     *
     * @var string|resource
     */
    protected $secretKey;

    /**
     * Constructor
     *
     * @param string            $serialNo     Merchant Certificate Serial Number
     * @param string|resource   $privateKey   Merchant Certificate Private Key \
     * (string - PEM formatted key, or resource - key returned by openssl_get_privatekey)
     */
    public function __construct($apiKey, $secretKey)
    {
        $this->apiKey = $apiKey;
        $this->secretKey = $secretKey;
    }
    public function getApiKey() {
        return $this->apiKey;
    }
    public function sign(RequestInterface $request)
    {
        return $this->performSign($request);
    }

    
    /**
     * Get sign timestamp
     *
     * @return integer
     */
    protected function getTimestamp()
    {
        return \time();
    }
    
    /**
     * Get nonce
     *
     * @return string
     */
    protected function getNonce()
    {
        static $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        $charactersLength = strlen($characters);
        $randomString = '';
        for ($i = 0; $i < 32; $i++) {
            $randomString .= $characters[rand(0, $charactersLength - 1)];
        }
        return $randomString;
    }
    
    public function getHmacAlgorithm()
    {
        return "hmac-sha1";
    }
    protected function performSign(RequestInterface $request)
    {
    
        $requestPath = $request->getUri()->getPath().($request->getUri()->getQuery()===''?'':"?".$request->getUri()->getQuery());
        $requestProtocolAndVersion = $request->getProtocolVersion();
        if(strpos($requestProtocolAndVersion,"HTTP/") === false) {
            $requestProtocolAndVersion = "HTTP/".$requestProtocolAndVersion;
        }
        $requestLine = $request->getMethod().' '.$requestPath. ' '.$requestProtocolAndVersion;
        
        $body = '';
        $bodyStream = $request->getBody();
        // non-seekable stream need to be handled by the caller
        if ($bodyStream->isSeekable()) {
            $body = (string)$bodyStream;
            $bodyStream->rewind();
        } else {
            throw new \UnexpectedValueException('Unsupported request body - which is not seekable.');
        }
        
        return $this->performSign0($request, $requestLine,$body);
    }
    protected function performSign0(RequestInterface $request, $requestLine, $body) {
        $guid = $this->uuid($this->getNonce());
        $guidMd5 = md5($guid . uniqid(md5(microtime(true)), true));
        
        //$guidMd5="939c5596e01d452b0daef30c5d08bf0e";

        $date = date(DATE_RFC7231, $this->getTimestamp());
        //$date = "Tue, 05 Jan 2021 00:21:02 GMT";
        $digest = "SHA-256=" . base64_encode(hash('sha256', $body, true));
        
        $buf=self::$X_DATE.': '.$date."\n".self::$X_DIGEST.': '.$digest."\n".self::$X_CONTENT_MD5.': '.$guidMd5."\n".$requestLine;
        $encodedSignature = $this->signHmacAlgorithm($this->getHmacAlgorithm(), $buf, $this->secretKey);
        
        $hmacAuth = "hmac username=\"".$this->getApiKey()."\",algorithm=\"".$this->getHmacAlgorithm()."\",headers=\"".self::$X_DATE." "
            .self::$X_DIGEST." ".self::$X_CONTENT_MD5." ".self::$X_REQUEST_LINE."\",signature=\"".$encodedSignature."\"";
        
        
        $request = $request->withHeader(self::$X_DATE, $date) 
            ->withHeader(self::$X_DIGEST, $digest)
            ->withHeader(self::$X_CONTENT_MD5, $guidMd5)
            ->withHeader(self::$X_AUTHORIZATION, $hmacAuth)
            ->withHeader(self::$X_COSCON_HMAC, $guidMd5);
        return $request;
    }
    
    
    /**
     * @param string $salt
     *
     * @return string
     */
    public function uuid($salt)
    {
        if (!isset($salt)) {
            $salt = $this->getNonce();
        }
        return md5($salt . uniqid(md5(microtime(true)), true));
    }
    
    public function signHmacAlgorithm($hmacAlgorithm, $string, $key) {
        if('hmac-sha1' == $hmacAlgorithm) {
            return self::signHmacSha1($string, $key);
        } else if('hmac-sha256' == $hmacAlgorithm) {
            return self::signHmacSha256($string, $key);
        } else if('hmac-sha384' == $hmacAlgorithm) {
            return self::signHmacSha384($string, $key);
        } else if('hmac-sha512' == $hmacAlgorithm) {
            return self::signHmacSha512($string, $key);
        }
        
        return self::signHmacSha1($string, $key);
    }
    /**
     * @param string $string
     * @param string $accessKeySecret
     *
     * @return string
     */
    public function signHmacSha1($string, $key)
    {
        return base64_encode(hash_hmac('sha1', $string, $key, true));
    }
    /**
     * @param string $string
     * @param string $accessKeySecret
     *
     * @return string
     */
    public function signHmacSha256($string, $key)
    {
        return base64_encode(hash_hmac('sha256', $string, $key, true));
    }
    /**
     * @param string $string
     * @param string $accessKeySecret
     *
     * @return string
     */
    public function signHmacSha512($string, $key)
    {
        return base64_encode(hash_hmac('sha512', $string, $key, true));
    }
    /**
     * @param string $string
     * @param string $accessKeySecret
     *
     * @return string
     */
    public function signHmacSha384($string, $key)
    {
        return base64_encode(hash_hmac('sha384', $string, $key, true));
    }
}
