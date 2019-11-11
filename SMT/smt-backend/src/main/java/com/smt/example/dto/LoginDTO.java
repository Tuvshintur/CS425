package com.smt.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

/**
 * Login DTO @author Turuu
 */

public class LoginDTO implements IGeneralDTO {

    @ApiModelProperty()
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    @ApiModelProperty()
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object user;

    /**
     * Constructor
     **/

    public LoginDTO() {
        super();
    }

    public LoginDTO(String token) {
        this.token = token;
    }

    public LoginDTO(String token, Object user) {
        this.token = token;
        this.user = user;
    }

    /**
     * Getter.Setter
     **/

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }
}