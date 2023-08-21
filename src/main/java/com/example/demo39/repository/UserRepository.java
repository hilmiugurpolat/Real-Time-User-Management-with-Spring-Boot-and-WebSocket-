package com.example.demo39.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.example.demo39.model.User;
@Repository
@Component
public interface UserRepository extends JpaRepository<User,Integer> {

}
