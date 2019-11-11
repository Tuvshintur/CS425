package com.smt.example.service.utilities;

import com.smt.example.dto.PaginationDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class HelperDTOService<T> {

    /**
     * do.Fill.PaginationDTO
     **/

    public PaginationDTO getPaginationDTO(Page<T> pagination) {
        PaginationDTO paginationDTO = new PaginationDTO();

        for (T item : pagination.getContent()) {
            paginationDTO.pushToContent(item);
        }

        paginationDTO.setPageable(pagination.getPageable());
        paginationDTO.setTotalPages(pagination.getTotalPages());
        paginationDTO.setTotalElements(pagination.getTotalElements());
        return paginationDTO;
    }

}