package com.mergetechng.jobs.commons.enums;

public enum MessageEnum {
    MAIL_SENDING_FAILED("Failed to send mail to the email address"),
    MAIL_SENDING_SUCCESSFUL("Mail sent successfully");
    String message ;
    MessageEnum(String message) { }

    public String getMessage() {
        return message;
    }
}
