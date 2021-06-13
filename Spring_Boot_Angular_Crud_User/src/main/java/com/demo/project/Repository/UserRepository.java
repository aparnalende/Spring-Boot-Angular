package com.demo.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.demo.project.entity.User;

@Service
public interface UserRepository extends JpaRepository<User, Long> {

}
