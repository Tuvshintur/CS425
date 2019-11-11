package com.smt.example.service;

import com.smt.example.constant.Constants;
import com.smt.example.entity.Project;
import com.smt.example.entity.User;
import com.smt.example.repository.UserRepository;
import com.smt.example.security.JwtTokenProvider;
import com.smt.example.utilities.LogUtilities;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserService implements Constants {

    private UserRepository userRepository;

    private JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public User getUserByUserName(String userName, HttpServletRequest req) {
        LogUtilities.info(this.getClass().getName(), "[srvc][user.get.username][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
        try {
            User user = userRepository.findByUsername(userName);
            LogUtilities.info(this.getClass().getName(), "[srvc][user.get.username][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            return user;
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][user.get.username][unknown][ " + ex.getMessage() + "]["
                    + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
    }
}
