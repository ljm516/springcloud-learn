package com.virgoconsumer.domain.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.ljming.common.virgoConsumer.VirgoConsumer;

@RestController
@RequestMapping(value = "/api/virgoConsumer/v1/users")
public class UserController {

    @Autowired
    UserBO userBO;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public VirgoConsumer.UserDTO createUser(@RequestBody VirgoConsumer.UserOption userOption) {
        return userBO.createUser(userOption);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public VirgoConsumer.UserListDTO getUserList() {
        return userBO.getUserList();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public VirgoConsumer.UserDTO getUserById(@PathVariable("userId") Long userId) {
        return userBO.getUserById(userId);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PATCH)
    public VirgoConsumer.UserDTO updateUserById(@PathVariable("userId") Long userId,
                                                @RequestBody VirgoConsumer.UserUpdateOption userUpdateOption) {
        return null;
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
    public VirgoConsumer.UserDTO deleteUserById(@PathVariable("userId") Long userId) {
        return null;
    }
}
