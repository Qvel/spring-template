package com.softserve.ukrainer.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UkrainerException extends RuntimeException {

    private int code;

    public UkrainerException(String errorMessage, int code) {
        super(errorMessage);
        this.code = code;
    }

}
