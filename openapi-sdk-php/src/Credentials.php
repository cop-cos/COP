<?php
/**
 * Credentials
 * PHP version 5
 *
 * @category Class
 * @package  COP\Client
 * @author   Chen Jipeng
 * @link     https://github.com/cop-cos/COP
 */

namespace COP\Client;

use Psr\Http\Message\RequestInterface;

/**
 * Interface abstracting Credentials.
 *
 * @package  COP\Client
 * @author   Chen Jipeng
 * @link     https://github.com/cop-cos/COP
 */
interface Credentials
{

    /**
     * Perform signature.
     *
     * @param RequestInterface $request Api request
     *
     * Returns request.
     */
    public function sign(RequestInterface $request);
}
