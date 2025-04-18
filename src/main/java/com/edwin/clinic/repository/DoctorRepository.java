package com.edwin.clinic.repository;

import com.edwin.clinic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<User,Integer> {


}
