package com.example.inventrolabstask3.persistence.repository;

import com.example.inventrolabstask3.persistence.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
