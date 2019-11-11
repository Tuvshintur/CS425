package com.smt.example.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Response DTO @author Turuu
 */

public class ResponseDTO implements IGeneralDTO {

    @ApiModelProperty()
    private HeaderDTO header;

    @ApiModelProperty(position = 1)
    private BodyDTO body;

    /**    @ApiModelProperty()
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object user;
     * Constructor
     **/

    public ResponseDTO() {
        super();
    }

    public ResponseDTO(HeaderDTO header) {
        this.header = header;
    }

    public ResponseDTO(HeaderDTO header, BodyDTO body) {
        this.header = header;
        this.body = body;
    }

    /**
     * Getter.Setter
     **/

    public HeaderDTO getHeader() {
        return header != null ? this.header : (this.header = new HeaderDTO());
    }

    public void setHeader(HeaderDTO header) {
        this.header = header;
    }

    public BodyDTO getBody() {
        return body != null ? this.body : (this.body = new BodyDTO());
    }

    public void setBody(BodyDTO body) {
        this.body = body;
    }
}