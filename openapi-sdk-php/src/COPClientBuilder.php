<?php
namespace COP\Client;;

use COP\Client\Auth\COPCredentials;
use COP\Client\Auth\COPNoopValidator;
use COP\Client\Auth\COPCredentialSigner;

/**
 * COPClientBuilder
 *
 * @category Class
 * @package  COP\Client
 * @author   Chen Jipeng
 * @link     https://github.com/cop-cos/COP
 */
class COPClientBuilder
{
    /**
     * Client application credentials
     *
     * @var Credentials
     */
    protected $credentials;

    /**
     * Response Validator
     *
     *  @var Validator
     */
    protected $validator;

    /**
     * Constructor
     */
    public function __construct()
    {
        $this->withValidator(new COPNoopValidator());
    }
    /**
     * Set Merchant Infomation
     *
     * @param string            $apiKey   apiKey
     * @param string            $secretKey secretKey
     * @return $this
     */
    public function withAuthentication($apiKey,$secretKey)
    {
        $this->credentials = new COPCredentials(new COPCredentialSigner($apiKey, $secretKey));
        return $this;
    }

    /**
     * Set Merchant Credentials
     *
     * @param Credentials $credentials Merchant Certificate Credentials
     *
     * @return $this
     */
    public function withCredentials(Credentials $credentials)
    {
        $this->credentials = $credentials;
        return $this;
    }

    /**
     * Set COP Certificates Infomation
     *
     * @param array of string|resource   $certifcates   COP Certificates (string - PEM formatted \
     * certificate, or resource - X.509 certificate resource returned by openssl_x509_read)
     *
     * @return $this
     */
    public function withCertification(array $certificates)
    {
        $this->validator = new COPNoopValidator();
        return $this;
    }

    /**
     * Set COP Validator
     *
     * @param Validator $Validator  COP Validator
     *
     * @return $this
     */
    public function withValidator(Validator $validator)
    {
        $this->validator = $validator;
        return $this;
    }

    /**
     * Build COPClient
     *
     * @return COPClient
     */
    public function build($copBaseUri='')
    {
        if (!isset($this->credentials)) {
            throw new \InvalidArgumentException('COP API credential is unset.');
        }
        if (!isset($this->validator)) {
            $this->validator = new COPNoopValidator();
        }
        $client = (new COPClient($this->credentials, $this->validator));
        if($copBaseUri!=='') {
            $client = $client->withCopBaseUri($copBaseUri);
        }
        return $client;
    }
}
