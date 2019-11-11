package com.smt.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

/**
 * Body DTO @author Turuu
 */

public class BodyDTO {

    @ApiModelProperty()
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object response;

    @ApiModelProperty(position = 1)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object error;

    @ApiModelProperty(position = 2)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object success;

    /**
     * Constructor
     **/

    public BodyDTO() {
        super();
    }

    public BodyDTO(Object response) {
        this.response = response;
    }

    public BodyDTO(Object response, Object error) {
        this.response = response;
        this.error = error;
    }

    /**
     * Getter.Setter
     **/

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public Object getSuccess() {
        return success;
    }

    public void setSuccess(Object success) {
        this.success = success;
    }
}