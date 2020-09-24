package com.lck.blog.dao;

import com.lck.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by limi on 2017/10/15.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username, String password);
}
