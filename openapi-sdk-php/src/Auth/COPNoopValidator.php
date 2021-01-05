<?php
/**
 * COPValidator
 * PHP version 5
 *
 * @category Class
 * @package  COP\Client
 * @author   Chen Jipeng
 * @link     https://github.com/cop-cos/COP
 */

namespace COP\Client\Auth;

use Psr\Http\Message\ResponseInterface;
use COP\Client\Validator;

/**
 * COPNoopValidator
 *
 * @category Class
 * @package  COP\Client
 * @author   Chen Jipeng
 * @link     https://github.com/cop-cos/COP
 */
class COPNoopValidator implements Validator
{
    public function validate(ResponseInterface $response)
    {
        return true;
    }
    
}