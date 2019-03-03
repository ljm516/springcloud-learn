package com.virgoconsumer.domain.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ljming.common.baseResult.Result;
import top.ljming.common.conviction.Conviction;
import top.ljming.common.conviction.ConvictionClient;
import top.ljming.common.conviction.ConvictionClientWrap;
import top.ljming.common.virgoConsumer.VirgoConsumer;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserBO {

    @Autowired
    private ConvictionClientWrap convictionClientWrap;

    public VirgoConsumer.UserDTO createUser(VirgoConsumer.UserOption userOption) {
        Conviction.UserOption convictionUserOption = createConvictionUserOption(userOption);
        Conviction.UserDTO convictionUserDTO = convictionClientWrap.createUser(convictionUserOption);

        return buildVirgoConsumerUserDTO(convictionUserDTO);
    }

    private VirgoConsumer.UserDTO buildVirgoConsumerUserDTO(Conviction.UserDTO convictionUserDTO) {
        VirgoConsumer.UserDTO virgoConsumerUserDTO = new VirgoConsumer.UserDTO();
        VirgoConsumer.UserInfo virgoConsumerUserInfo = new VirgoConsumer.UserInfo();
        Result result = new Result();

        virgoConsumerUserInfo.setAge(convictionUserDTO.getUserInfo().getAge());
        virgoConsumerUserInfo.setCellPhone(convictionUserDTO.getUserInfo().getCellPhone());
        virgoConsumerUserInfo.setGender(convictionUserDTO.getUserInfo().getGender());
        virgoConsumerUserInfo.setUserId(convictionUserDTO.getUserInfo().getUserId());
        virgoConsumerUserInfo.setUsername(convictionUserDTO.getUserInfo().getUsername());

        result.setMsg("");
        result.setCode(200);

        virgoConsumerUserDTO.setResult(result);
        virgoConsumerUserDTO.setUserInfo(virgoConsumerUserInfo);
        return virgoConsumerUserDTO;

    }

    private Conviction.UserOption createConvictionUserOption(VirgoConsumer.UserOption userOption) {
        Conviction.UserOption convictionUserOpt = new Conviction.UserOption();

        convictionUserOpt.setAge(userOption.getAge());
        convictionUserOpt.setCellPhone(userOption.getCellPhone());
        convictionUserOpt.setGender(userOption.getGender());
        convictionUserOpt.setUsername(userOption.getUsername());

        return convictionUserOpt;
    }


    public VirgoConsumer.UserListDTO getUserList() {
        Conviction.UserListDTO convictionUserListDTO = convictionClientWrap.getUserList();
        return buildVirgoConsumerUserListDTO(convictionUserListDTO);
    }

    private VirgoConsumer.UserInfo buildVirgoConsumerUserInfo(Conviction.UserInfo convictionUserInfo) {
        VirgoConsumer.UserInfo virgoConsumerUserInfo = new VirgoConsumer.UserInfo();
        virgoConsumerUserInfo.setAge(convictionUserInfo.getAge());
        virgoConsumerUserInfo.setCellPhone(convictionUserInfo.getCellPhone());
        virgoConsumerUserInfo.setGender(convictionUserInfo.getGender());
        virgoConsumerUserInfo.setUserId(convictionUserInfo.getUserId());
        virgoConsumerUserInfo.setUsername(convictionUserInfo.getUsername());

        return virgoConsumerUserInfo;
    }

    private VirgoConsumer.UserListDTO buildVirgoConsumerUserListDTO(Conviction.UserListDTO convictionUserListDTO) {
        VirgoConsumer.UserListDTO virgoConsumerUserListDTO = new VirgoConsumer.UserListDTO();
        List<VirgoConsumer.UserInfo> virgoConsumerUserInfoList = new ArrayList<>();
        convictionUserListDTO.getUserInfoList().stream().forEach(userInfo -> virgoConsumerUserInfoList.add(buildVirgoConsumerUserInfo(userInfo)));
        virgoConsumerUserListDTO.setUserInfoList(virgoConsumerUserInfoList);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("");

        virgoConsumerUserListDTO.setResult(result);
        return virgoConsumerUserListDTO;
    }

    public VirgoConsumer.UserDTO getUserById(Long userId) {
        Conviction.UserDTO convictionUserDTO = convictionClientWrap.getUserById(userId);

        if (convictionUserDTO.getUserInfo() == null) {
            return new VirgoConsumer.UserDTO();
        }
        VirgoConsumer.UserInfo virgoConsumerUserInfo = buildVirgoConsumerUserInfo(convictionUserDTO.getUserInfo());

        VirgoConsumer.UserDTO virgoConsumerUserDTO = new VirgoConsumer.UserDTO();
        virgoConsumerUserDTO.setUserInfo(virgoConsumerUserInfo);

        Result result = new Result();
        result.setMsg("");
        result.setCode(200);

        virgoConsumerUserDTO.setResult(result);

        return virgoConsumerUserDTO;
    }
}
