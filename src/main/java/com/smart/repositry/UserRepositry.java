package com.smart.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.entity.User;

public interface UserRepositry extends JpaRepository<User, Integer> {

}
