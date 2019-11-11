package com.smt.example.controller.rest;

import com.smt.example.constant.Constants;
import com.smt.example.dto.UploadDTO;
import com.smt.example.exception.RestrictionException;
import com.smt.example.security.JwtTokenProvider;
import com.smt.example.service.UploadService;
import com.smt.example.utilities.LogUtilities;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Upload Controller @author Turuu
 */

@RestController
@RequestMapping("/v1api/upload")
@Api(tags = "UPLOAD")
public class UploadController implements Constants {

    private UploadService service;

    private JwtTokenProvider jwtTokenProvider;

    public UploadController(UploadService service, JwtTokenProvider jwtTokenProvider) {
        this.service = service;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public UploadDTO uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest req) throws IOException, RestrictionException {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][upload][uploadFile][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            String fileName = service.storeFile(file, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][upload][uploadMultipleFiles][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return new UploadDTO(fileName, file.getContentType(), file.getSize());
        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][upload][uploadFile][unknown][ " + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "uploadMultipleFiles", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public List<UploadDTO> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, HttpServletRequest req) {
        LogUtilities.info(this.getClass().getName(), "[ctrl][upload][uploadMultipleFiles][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
        return Arrays.asList(files)
                .stream()
                .map(file -> {
                    try {
                        LogUtilities.info(this.getClass().getName(), "[ctrl][upload][uploadMultipleFiles][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
                        return uploadFile(file, req);
                    } catch (IOException ex) {
                        LogUtilities.warn(this.getClass().getName(), "[ctrl][upload][uploadMultipleFiles][io.warn][ " + ex.getMessage() + "]");
                    } catch (RestrictionException ex) {
                        LogUtilities.fatal(this.getClass().getName(), "[ctrl][upload][uploadMultipleFiles][unknown][ " + ex.getMessage() + "]", ex);
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }
}
