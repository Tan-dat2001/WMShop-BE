package com.shoes.service.Impl;

import com.shoes.dto.manager.UserDto;
import com.shoes.entity.User;
import com.shoes.repository.UserRepository;
import com.shoes.request.ChangePasswordRequest;
import com.shoes.response.ApiResponse;
import com.shoes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.shoes.common.Message.*;

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

    @Override
    public ApiResponse<?> changePassword(ChangePasswordRequest changePasswordRequest) {
        User user = userRepository.findById(Long.parseLong(changePasswordRequest.getId().toString())).get();
        if(!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_OLD_PASSWORD_NOT_TRUE, null); // mật khẩu cũ không khớp
        }
        if(!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getNewPasswordConfirm())){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_NEW_PASSWORD_NOT_MATCH, null); // Mật khẩu mới xác nhận lại không khớp
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        try{
            userRepository.save(user);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_CHANGE_PASSWORD_SUCCESS, null); // thành công
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_CHANGE_PASSWORD_FAIL, null); // thất bại
        }
    }

}
