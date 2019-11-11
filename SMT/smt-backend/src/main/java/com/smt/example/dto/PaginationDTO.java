package com.smt.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Pagination DTO @author Turuu
 */

public class PaginationDTO implements IGeneralDTO {

    @ApiModelProperty(position = 1)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Object> content;

    @ApiModelProperty(position = 2)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object pageable;

    @ApiModelProperty(position = 3)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int totalPages;

    @ApiModelProperty(position = 3)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long totalElements;

    /**
     * Push
     **/

    public void pushToContent(Object object) {
        this.content = this.content != null ? this.content : new ArrayList<>();
        this.content.add(object);
    }

    /**
     * Getter.Setter
     **/

    public List<Object> getContent() {
        return content;
    }

    public void setContent(List<Object> content) {
        this.content = content;
    }

    public Object getPageable() {
        return pageable;
    }

    public void setPageable(Object pageable) {
        this.pageable = pageable;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}