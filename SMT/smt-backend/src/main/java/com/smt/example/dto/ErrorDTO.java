package com.smt.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

/**
 * Error DTO @author Turuu
 */

public class ErrorDTO implements IGeneralDTO {

    @ApiModelProperty()
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorCode;

    @ApiModelProperty(position = 1)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorDesc;

    @ApiModelProperty(position = 2)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorType;

    /**
     * Constructor
     **/

    public ErrorDTO() {
        super();
    }

    public ErrorDTO(String errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorDTO(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public ErrorDTO(String errorCode, String errorDesc, String errorType) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.errorType = errorType;
    }

    /**
     * Getter.Setter
     **/

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }
}
