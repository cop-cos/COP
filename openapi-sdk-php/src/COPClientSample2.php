<?php
require_once 'vendor/autoload.php';
use COP\Client\COPClient;
use GuzzleHttp\Exception\RequestException;
use GuzzleHttp\HandlerStack;
/****************************************************************
 * Setting up HttpClient ...
 ***************************************************************/
$stack = HandlerStack::create();

$copClient = COPClient::builder('YOUR Api Key', 'YOUR Secret Key')->build();

$copClient->register($stack);

$httpClient = new \GuzzleHttp\Client(['handler' => $stack]);
/****************************************************************/

try {
    $resp = $httpClient->request('GET', 'https://api-pp.lines.coscoshipping.com/service/info/tracking/6103622780?numberType=bl', [ // 注意替换为实际URL
        'headers' => [
            'Accept' => 'application/json'
        ]
    ]);

    echo $resp->getStatusCode() . ' ' . $resp->getReasonPhrase() . "\n";
    echo $resp->getBody() . "\n";
} catch (RequestException $e) {
    // 进行错误处理
    echo $e->getMessage() . "\n";
    if ($e->hasResponse()) {
        echo $e->getResponse()->getStatusCode() . ' ' . $e->getResponse()->getReasonPhrase() . "\n";
        echo $e->getResponse()->getBody();
    }
    return;
}