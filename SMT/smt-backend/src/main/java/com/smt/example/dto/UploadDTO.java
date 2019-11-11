package com.smt.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

/**
 * Upload DTO @author Turuu
 */


public class UploadDTO implements IGeneralDTO {

    @ApiModelProperty()
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fileName;

    @ApiModelProperty()
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fileType;

    @ApiModelProperty()
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long size;

    public UploadDTO(String fileName, String fileType, long size) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
