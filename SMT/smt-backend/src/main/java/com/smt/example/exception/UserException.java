package com.smt.example.exception;

public class UserException extends Exception {

    private String errCode, errDesc, errType;

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable exception) {
        super(message, exception);
    }

    public UserException(Throwable exception) {
        super(exception);
    }

    /** Utilities **/

    public UserException updateErr(String errCode, String errDesc, String errType) {
        this.setErrCode(errCode);
        this.setErrDesc(errDesc);
        this.setErrType(errType);

        return this;
    }

    /** Getter.Setter **/

    public String getErrCode() {
        return this.errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrDesc() {
        return this.errDesc;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }

    public String getErrType() {
        return this.errType;
    }

    public void setErrType(String errType) {
        this.errType = errType;
    }

}
