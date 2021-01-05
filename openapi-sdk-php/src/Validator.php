<?php
/**
 * Validator
 * PHP version 5
 *
 * @category Class
 * @package  COP\Client
 * @author   Chen Jipeng
 * @link     https://github.com/cop-cos/COP
 */

namespace COP\Client;

use Psr\Http\Message\ResponseInterface;

/**
 * Interface abstracting Validator.
 *
 * @package  COP\Client
 * @author   Chen Jipeng
 * @link     https://github.com/cop-cos/COP
 */
interface Validator
{
    /**
     * Validate Response
     *
     * @param ResponseInterface $response Api response to validate
     *
     * @return bool
     */
    public function validate(ResponseInterface $response);
}
