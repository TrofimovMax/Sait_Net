package com.example.Inst_Sait.web;

import com.example.Inst_Sait.payload.request.LoginRequest;
import com.example.Inst_Sait.payload.request.SignupRequest;
import com.example.Inst_Sait.payload.response.JWTTokenSuccessResponce;
import com.example.Inst_Sait.payload.response.MessageResponse;
import com.example.Inst_Sait.security.JWTTokenProvider;
import com.example.Inst_Sait.security.SecurityConstants;
import com.example.Inst_Sait.services.UserService;
import com.example.Inst_Sait.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("premitAll")
public class AuthController {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @Autowired
    private UserService userService;

    //api/auth/signin
    @PostMapping("signin")
    public ResponseEntity<Object> authenticationUser(@Valid @RequestBody LoginRequest loginRequest
            , BindingResult bindingResult ){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);

        if(!ObjectUtils.isEmpty(errors)){return errors;}

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generationToken(authentication);
        return ResponseEntity.ok(new JWTTokenSuccessResponce(true, jwt));
    }

    //api/auth/signup
    @PostMapping("signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest,
                                               BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);

        if(!ObjectUtils.isEmpty(errors)){return errors;}

        userService.createUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
}
