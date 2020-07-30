package com.du11.coreapi.config;

import com.du11.coreapi.common.ErrorMessageConstants;
import com.du11.coreapi.dto.error.ApiError;
import com.du11.coreapi.dto.error.ApiErrorDetail;
import com.du11.coreapi.exception.EntityNotFoundException;
import com.du11.coreapi.exception.InsertExistedEntityException;
import com.du11.coreapi.exception.SRException;
import com.du11.coreapi.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.WebUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class SRExceptionHandler {

    @Autowired
    MessageUtils messageUtils;

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ApiError> handleEntityNotFoundException(EntityNotFoundException e){
        log.error(e.getMessage());
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .statusMessage("Data not found")
                .timestamp(Instant.now().toString())
                .build();
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InsertExistedEntityException.class})
    public ResponseEntity<ApiError> handleInsertExistedEntityException(InsertExistedEntityException e){
        log.error(e.getMessage());

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .statusMessage("The entity is existed.")
                .timestamp(Instant.now().toString())
                .errors(e.getApiError().getErrors())
                .build();
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleSRException(SRException ex, WebRequest request) {
        ApiError apiError = ex.getApiError();
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.valueOf(ex.getApiError().getStatus()));
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return handleHttpRequestMethodNotSupported((HttpRequestMethodNotSupportedException) ex,
                    headers, HttpStatus.METHOD_NOT_ALLOWED, request);
        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
            return handleHttpMediaTypeNotSupported((HttpMediaTypeNotSupportedException) ex,
                    headers, HttpStatus.UNSUPPORTED_MEDIA_TYPE, request);
        } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
            status = HttpStatus.NOT_ACCEPTABLE;
        } else if (ex instanceof MaxUploadSizeExceededException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof MissingServletRequestParameterException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof ServletRequestBindingException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof TypeMismatchException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof HttpMessageNotReadableException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof MethodArgumentNotValidException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof MissingServletRequestPartException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof BindException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof NoHandlerFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof AsyncRequestTimeoutException) {
            status = HttpStatus.SERVICE_UNAVAILABLE;
        }

        return handleInternalException(ex, headers, status, request);
    }

    private ResponseEntity<ApiError> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("HttpRequestMethodNotSupported", ex);
        Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
        if (CollectionUtils.isEmpty(supportedMethods)) {
            headers.setAllow(supportedMethods);
        }

        return handleInternalException(ex, headers, status, request);
    }

    private ResponseEntity<ApiError> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("HttpMediaTypeNotSupported", ex);
        List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
        if (!CollectionUtils.isEmpty(mediaTypes)) {
            headers.setAccept(mediaTypes);
        }
        return handleInternalException(ex, headers, status, request);
    }

    private ResponseEntity<ApiError> handleInternalException(
            Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Exception", ex);
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, RequestAttributes.SCOPE_REQUEST);
        }

        List<ApiErrorDetail> errorDetails = null;
        if (ex instanceof MethodArgumentNotValidException) {
            errorDetails = new ArrayList<>();
            for (FieldError error: ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors()) {
                if ("NotEmpty".equalsIgnoreCase(error.getCode())
                    || "NotNull".equalsIgnoreCase(error.getCode())
                    || "NotBlank".equalsIgnoreCase(error.getCode())) {
                    errorDetails.add(new ApiErrorDetail(ErrorMessageConstants.ERROR_FIELD_IS_REQUIRED,
                            error.getField(), messageUtils.getMessage(ErrorMessageConstants.ERROR_FIELD_IS_REQUIRED)));
                } else if ("Size".equalsIgnoreCase(error.getCode())) {
                    if (error.getArguments() != null && error.getArguments().length > 2
                        && error.getArguments()[2].equals(0)) {
                        errorDetails.add(new ApiErrorDetail(ErrorMessageConstants.ERROR_FIELD_EXCEED_MAX_LENGTH,
                                error.getField(),
                                messageUtils.getMessage(ErrorMessageConstants.ERROR_FIELD_EXCEED_MAX_LENGTH, error.getArguments()[1])));
                    } else if (error.getArguments() != null && error.getArguments().length > 2
                        && !error.getArguments()[2].equals(0)) {
                        if (error.getArguments()[1].equals(error.getArguments()[2])) {
                            errorDetails.add(new ApiErrorDetail(ErrorMessageConstants.ERROR_FIELD_REQUIRED_LENGTH,
                                    error.getField(),
                                    messageUtils.getMessage(ErrorMessageConstants.ERROR_FIELD_REQUIRED_LENGTH, error.getArguments()[1])));
                        }
                    } else {
                        errorDetails.add(new ApiErrorDetail(ErrorMessageConstants.ERROR_FIELD_IS_INVALID,
                                error.getField(), error.getDefaultMessage()));
                    }
                }
            }
        }

        ApiError apiError = ApiError.builder()
                .status(status.value())
                .statusMessage(status.getReasonPhrase())
                .timestamp(Instant.now().toString())
                .errors(errorDetails == null ? Collections.singletonList(new ApiErrorDetail(
                        ErrorMessageConstants.ERROR_CODE_COMMON_PREFIX+status.value(),
                        "",
                        status.getReasonPhrase()
                )) : errorDetails)
                .build();

        return new ResponseEntity<>(apiError, headers, status);
    }
}
