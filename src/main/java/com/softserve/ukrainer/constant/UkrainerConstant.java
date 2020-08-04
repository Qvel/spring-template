package com.softserve.ukrainer.constant;


public enum UkrainerConstant {

    AUTHORIZE_EXCEPTION("Sorry, You're not authorized to access this resource."),
    USER_NOT_FOUND("user not found"),
    AUTHENTICATION_EXCEPTION("User authentication was failed"),
    REGISTERED_SUCCESSFULLY("Registered Successfully %s"),
    JWT_EXCEPTION("Unable to get JWT Token or JWT Token has expired"),
    PASSWORD_RESTORE_MESSAGE("Hello %s, here your new password : %s"),
    PASSWORD_RESTORE_SUBJECT("Password restoration for Ukrainer"),
    EMAIL_SENDING_EXCEPTION("Exception while performing email sending")
    ;

    private String message;

    UkrainerConstant(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "UkrainerConstant{" +
            "message='" + message + '\'' +
            '}';
    }
}
