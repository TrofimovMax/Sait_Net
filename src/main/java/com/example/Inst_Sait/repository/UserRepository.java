package com.example.Inst_Sait.repository;

import com.example.Inst_Sait.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>
{
     Optional <User> findUserByUsername (String username);
     Optional <User> findUserByEmail(String email);
     Optional <User> findUserById (Long id);

}
