<?php
/**
 * Signer
 * PHP version 5
 *
 * @category Class
 * @package  COP\Client
 * @author   Chen Jipeng
 * @link     https://github.com/cop-cos/COP
 */

namespace COP\Client\Auth;

use Psr\Http\Message\RequestInterface;

/**
 * Interface abstracting Signer.
 *
 * @package  COP\Client
 * @author   Chen Jipeng
 * @link     https://github.com/cop-cos/COP
 */
interface Signer
{

    /**
     * Sign Message
     *
     * @param string $message Message to sign
     * @return request
     */
    public function sign(RequestInterface $request);
}
