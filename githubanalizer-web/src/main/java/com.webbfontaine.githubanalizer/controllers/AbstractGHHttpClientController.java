package com.webbfontaine.githubanalizer.controllers;

import com.webbfontaine.githubanalizer.data.response.error.ErrorCode;
import com.webbfontaine.githubanalizer.data.response.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by Arsen Aleksanyan on 11/23/18.
 */
public abstract class AbstractGHHttpClientController {

    private static final String UNAUTHORIZED_ERROR =
            "Failed to authorize to GitHub. Probably you have provided wrong username and password.\n" +
            "Please provide valid GitHub username and password at runtime or leave those blank at all.\n" +
            "But in that case you will have limitations to access the application";

    private static final String FORBIDDEN_ERROR =
            "GitHub API access limit exceeded.\n" +
            "If you didn't provided GitHub username and password during runtime, please provide those to extend GitHub API access limitation.\n" +
            "If you have already provided those, try to use another GitHub account.";

    private static final String UNKNOWN_ERROR =
            "Unknown error occurred. Please reload or re-run the application.";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    protected final ErrorResponse returnGHHttpClientError(final HttpClientErrorException e) {
        switch (e.getStatusCode()){
            case UNAUTHORIZED:
                return new ErrorResponse(ErrorCode.UNAUTHORIZED, UNAUTHORIZED_ERROR);
            case FORBIDDEN:
                return new ErrorResponse(ErrorCode.FORBIDDEN, FORBIDDEN_ERROR);
            default:
                return new ErrorResponse(ErrorCode.UNKNOWN, e.getMessage());
        }
    }
}
