package com.mailgun.exception;

public class MailGunException extends RuntimeException {

    public MailGunException() {
        super();
    }

    public MailGunException(String message) {
        super(message);
    }

    public MailGunException(String message, Throwable cause) {
        super(message, cause);
    }

}
