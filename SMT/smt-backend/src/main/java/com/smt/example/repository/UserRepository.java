package com.smt.example.repository;

import com.smt.example.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * User Repository @author Turuu
 */

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();

    /**
     * Check User
     * * @return boolean
     **/

    boolean existsByUsername(String username);

    /**
     * Check User email
     * * @return boolean
     **/

    boolean existsByEmail(String email);

    /**
     * Find User by username
     * * @return user /Object/
     **/

    User findByUsername(String username);

}
