package com.shoes.dto.manager;

import com.shoes.common.Function;
import com.shoes.entity.User;
import com.shoes.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;
//    private String avatar;
    private String email;
    private String firstName;
    private String lastName;
//    private long birthday;
//    private String gender;
    private String address;
    private String phone;
    private boolean status;
    private String roleId;
    private String roleName;
//    private List<String> roleList;
    private long createAt;
    private long updateAt;

    public UserDto(User user){
        this.id = user.getId().toString();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.status = user.getStatus();

        List<UserRole> userRoleList = user.getUserRoleList();
        if(!CollectionUtils.isEmpty(userRoleList)){
            this.roleId = userRoleList.get(0).getRole().getId().toString();
            this.roleName = userRoleList.get(0).getRole().getName();
            //todo: error to handle show role list
//            for(UserRole role:userRoleList){
//                this.roleList.add(role.getRole().getName());
//            }
        }
        this.createAt = null != user.getCreatedAt() ? Function.toLongFromTimeStamp(user.getCreatedAt()) : 0;
        this.updateAt = null != user.getUpdatedAt() ? Function.toLongFromTimeStamp(user.getUpdatedAt()) : 0;
    }
}
