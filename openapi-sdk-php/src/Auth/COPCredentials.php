<?php
/**
 * COPCredentials
 * PHP version 5
 *
 * @category Class
 * @package  COP\Client
 * @author   Chen Jipeng
 * @link     https://github.com/cop-cos/COP
 */

namespace COP\Client\Auth;

use Psr\Http\Message\RequestInterface;
use COP\Client\Credentials;

/**
 * COPCredentials
 *
 * @category Class
 * @package  COP\Client
 * @author   Chen Jipeng
 * @link     https://github.com/cop-cos/COP
 */
class COPCredentials implements Credentials
{

    /**
     * signer
     *
     * @var Signer
     */
    protected $signer;

    /**
     * Constructor
     */
    public function __construct(Signer $signer)
    {
        $this->signer = $signer;
    }
    
    public function sign(RequestInterface $request)
    {
        
        return $this->signer->sign($request);
    }


    


}
