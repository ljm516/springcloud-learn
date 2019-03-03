package com.conviction.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.ljming.common.conviction.Conviction;

/**
 * Created by jiangmingli on 2017/10/15.
 */

@RestController
@RequestMapping("/api/conviction/v1/users")
public class UserController {

    @Autowired
    UserBO userBO;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Conviction.UserDTO createUser(@RequestBody Conviction.UserOption userOption) {
        return userBO.createUser(userOption);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Conviction.UserListDTO getUserList() {
        return userBO.getUserList();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Conviction.UserDTO getUserById(@PathVariable("userId") Long userId) {
        return userBO.getUserById(userId);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PATCH)
    public Conviction.UserDTO updateUser(@PathVariable("userId") Long userId,
                                        @RequestBody Conviction.UserUpdateOption userUpdateOption) {
        return userBO.updateUser(userId, userUpdateOption);
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
    public Conviction.UserDTO deleteUserById(@PathVariable("userId") Long userId) {
        return userBO.deleteUserById(userId);
    }

}

