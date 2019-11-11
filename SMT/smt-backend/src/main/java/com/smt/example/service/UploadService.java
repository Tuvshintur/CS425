package com.smt.example.service;

import com.smt.example.config.PropertiesConfig;
import com.smt.example.constant.Constants;
import com.smt.example.exception.RestrictionException;
import com.smt.example.security.JwtTokenProvider;
import com.smt.example.utilities.LogUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UploadService implements Constants {

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    PropertiesConfig propertiesConfig;

    public String storeFile(MultipartFile file, HttpServletRequest req) throws IOException, RestrictionException {
        LogUtilities.info(this.getClass().getName(), "[srvc][store.file][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new RestrictionException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = getUploadPath().resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][store.file][io.error][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        } catch (RestrictionException ex) {
            LogUtilities.warn(this.getClass().getName(), "[srvc][store.file][validation][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][store.file][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
    }

    public Path getUploadPath() throws RestrictionException {
        Path path = Paths.get(propertiesConfig.getConfigValue("uploadDir")).toAbsolutePath().normalize();
        try {
            if(!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (Exception ex) {
            throw new RestrictionException("Could not create the directory where the uploaded files will be stored.");
        }
        return path;
    }
}
