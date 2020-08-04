package com.softserve.ukrainer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softserve.ukrainer.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

}
