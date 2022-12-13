package com.example.Inst_Sait.services;

import com.example.Inst_Sait.entity.User;
import com.example.Inst_Sait.entity.enums.ERole;
import com.example.Inst_Sait.exception.UserExistException;
import com.example.Inst_Sait.payload.request.SignupRequest;
import com.example.Inst_Sait.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(SignupRequest userIn){
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getFirstname());
        user.setLastname(userIn.getLastname());
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRole().add(ERole.ROLE_USER);

        try{
            LOG.info("Saving User{}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception e){
            LOG.error("Error during registration, {}", e.getMessage());
            throw new UserExistException("the user " + user.getUsername() + "already exist. Please check credentials");
        }
    }
}
