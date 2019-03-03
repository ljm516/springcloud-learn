package com.conviction.domain.User;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ljming.common.baseResult.Result;
import top.ljming.common.conviction.Conviction;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserBO {

    @Autowired
    UserDAO userDAO;

    public Conviction.UserDTO createUser(Conviction.UserOption userOption) {
        User user = buildUser(userOption);
        User dbUser = userDAO.save(user);

        return buildConvictionUserDTO(dbUser);
    }

    private Conviction.UserDTO buildConvictionUserDTO(User dbUser) {
        Conviction.UserDTO userDTO = new Conviction.UserDTO();
        Conviction.UserInfo userInfo = new Conviction.UserInfo();
        Result result = new Result();

        userInfo.setAge(dbUser.getAge());
        userInfo.setCellPhone(dbUser.getCellPhone());
        userInfo.setGender(dbUser.getGender());
        userInfo.setUserId(dbUser.getId());
        userInfo.setUsername(dbUser.getUsername());

        result.setCode(200);
        result.setMsg("");

        userDTO.setResult(result);
        userDTO.setUserInfo(userInfo);
        return userDTO;
    }

    private User buildUser(Conviction.UserOption userOption) {
        User user = new User();

        user.setAge(userOption.getAge());
        user.setCellPhone(userOption.getCellPhone());
        user.setGender(userOption.getGender());
        user.setUsername(userOption.getUsername());
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return user;
    }

    public Conviction.UserListDTO getUserList() {
        List<User> userList = userDAO.findAll();
        List<Conviction.UserInfo> userInfoList = new ArrayList<>();
        Conviction.UserListDTO userListDTO = new Conviction.UserListDTO();
        Result result = new Result();

        userList.stream().forEach(user -> userInfoList.add(buildConvictionUserInfo(user)));
        userListDTO.setUserInfoList(userInfoList);

        result.setMsg("");
        result.setCode(200);

        userListDTO.setResult(result);
        return userListDTO;
    }

    private Conviction.UserInfo buildConvictionUserInfo(User user) {
        Conviction.UserInfo userInfo = new Conviction.UserInfo();
        userInfo.setAge(user.getAge());
        userInfo.setCellPhone(user.getCellPhone());
        userInfo.setGender(user.getGender());
        userInfo.setUserId(user.getId());
        userInfo.setUsername(user.getUsername());

        return userInfo;
    }

    public Conviction.UserDTO getUserById(Long userId) {
        User user = userDAO.findOne(userId);

        Conviction.UserDTO userDTO = new Conviction.UserDTO();
        Result result = new Result();
        result.setCode(200);
        if (user == null) {
            result.setMsg("用户不存在");
            userDTO.setUserInfo(null);
            userDTO.setResult(result);
        } else {
            result.setMsg("");
            userDTO.setResult(result);
            userDTO.setUserInfo(buildConvictionUserInfo(user));
        }

        return userDTO;
    }

    public Conviction.UserDTO updateUser(Long userId, Conviction.UserUpdateOption userUpdateOption) {
        User existUser = userDAO.findOne(userId);

        Conviction.UserDTO userDTO = new Conviction.UserDTO();
        Result result = new Result();
        result.setCode(200);
        if (existUser == null) {
            result.setMsg("用户不存在");
            userDTO.setUserInfo(null);
            userDTO.setResult(result);
            return userDTO;
        }

        if (userUpdateOption.getCellPhone() != null && StringUtils.isNotBlank(userUpdateOption.getCellPhone())) {
            existUser.setCellPhone(userUpdateOption.getCellPhone());
        }
        if (userUpdateOption.getUsername() != null && StringUtils.isNotBlank(userUpdateOption.getUsername())) {
            existUser.setUsername(userUpdateOption.getUsername());
        }

        User updatedUser = userDAO.save(existUser);

        return buildConvictionUserDTO(updatedUser);
    }

    public Conviction.UserDTO deleteUserById(Long userId) {
        User existUser = userDAO.findOne(userId);

        Conviction.UserDTO userDTO = new Conviction.UserDTO();
        Result result = new Result();
        result.setCode(200);
        if (existUser == null) {
            result.setMsg("用户不存在");
            userDTO.setResult(result);
            userDTO.setUserInfo(null);
            return userDTO;
        }

        userDAO.delete(userId);
        result.setMsg("删除成功");

        userDTO.setUserInfo(null);
        userDTO.setResult(result);

        return userDTO;
    }
}
