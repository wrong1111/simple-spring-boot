package com.example.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.User;


public interface UserRepository extends JpaRepository<User, Integer>{

}
