/*
 * Copyright (c) Marviq 2014
 */

package com.marviq.service.jersey;

/**
 * 
 * @author navidallahverdi, navid.allahverdi@marviq.com
 */
public class MessageParsingException extends Exception {
    public MessageParsingException() {
    }

    public MessageParsingException(String message) {
        super(message);
    }

    public MessageParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageParsingException(Throwable cause) {
        super(cause);
    }
}
