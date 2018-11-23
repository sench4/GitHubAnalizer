package com.webbfontaine.githubanalizer.data.response.error;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by Arsen Aleksanyan on 11/23/18.
 */
@JsonRootName("error")
public class ErrorResponse {

    private ErrorCode errorCode;
    private String errorMessage;

    public ErrorResponse(final ErrorCode errorCode, final String errorMessage) {
        if (errorCode == null) throw new IllegalArgumentException("Null errorCode not allowed.");
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorCode getErrorCode() { return errorCode; }

    public String getErrorMessage() { return errorMessage; }
}
