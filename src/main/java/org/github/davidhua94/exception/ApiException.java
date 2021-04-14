package org.github.davidhua94.exception;

/**
 * @author David Hua
 * @date 2021/3/26
 * @desc
 */
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
