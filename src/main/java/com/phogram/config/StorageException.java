package com.phogram.config;

/**
 * Created by gavin on 2017. 5. 6..
 */
public class StorageException extends RuntimeException {

        public StorageException(String message) {
            super(message);
        }

        public StorageException(String message, Throwable cause) {
            super(message, cause);
        }
}
