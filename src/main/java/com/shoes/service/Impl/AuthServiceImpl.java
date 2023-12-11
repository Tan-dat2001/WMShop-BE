package com.shoes.service.Impl;

import com.shoes.entity.Role;
import com.shoes.entity.User;
import com.shoes.entity.UserRole;
import com.shoes.repository.RoleRepository;
import com.shoes.repository.UserRepository;
import com.shoes.repository.UserRoleRepository;
import com.shoes.request.LoginRequest;
import com.shoes.request.SignUpRequest;
import com.shoes.response.ApiResponse;
import com.shoes.response.JwtResponse;
import com.shoes.security.jwt.JwtUtils;
import com.shoes.security.service.UserDetailsImpl;
import com.shoes.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.shoes.common.Message.*;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public ApiResponse<?> logIn(LoginRequest loginRequest) {
        if(loginRequest.getEmail().isEmpty() || loginRequest.getPassword().isEmpty()){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),MSG_BAD_REQUEST, null);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return new ApiResponse(HttpStatus.OK.value(), MSG_LOGIN_SUCCESS, new JwtResponse(jwt, userDetails.getUsername(),roles));
    }

    @Override
    public ApiResponse<?> signUp(SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),MSG_EMAIL_EXIST, null);
        }
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setAddress(signUpRequest.getAddress());
        user.setPhone(signUpRequest.getPhone());
        user.setStatus(true);
        try{
            userRepository.save(user);
            User userRegistry = userRepository.findByEmail(signUpRequest.getEmail()).orElseThrow(
                    () -> new UsernameNotFoundException("User not found with email"));
            Role roleRegistry = roleRepository.findById(Long.parseLong(signUpRequest.getRoleId())).orElseThrow(
                    () -> new IllegalStateException("Role not found by Id"));
            UserRole userRole = new UserRole();
            userRole.setUser(userRegistry);
            userRole.setRole(roleRegistry);
            userRoleRepository.save(userRole);
            return new ApiResponse<>(HttpStatus.CREATED.value(), MSG_REGISTRY_SUCCESS, null);
        }catch (Exception e){
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_REGISTRY_FAIL, null);
        }
    }
}
