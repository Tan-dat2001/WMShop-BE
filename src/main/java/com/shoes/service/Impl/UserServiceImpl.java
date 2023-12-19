package com.shoes.service.Impl;

import com.shoes.dto.manager.UserDto;
import com.shoes.entity.User;
import com.shoes.repository.UserRepository;
import com.shoes.response.ApiResponse;
import com.shoes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.shoes.common.Message.MSG_USER_FAIL;
import static com.shoes.common.Message.MSG_USER_SUCCESS;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public ApiResponse<?> getUser(String id) {
        try{
            User user = userRepository.findById(Long.parseLong(id)).get();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            UserDto userDto = new UserDto(user);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_USER_SUCCESS, userDto);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_USER_FAIL, null);
        }
    }
}
