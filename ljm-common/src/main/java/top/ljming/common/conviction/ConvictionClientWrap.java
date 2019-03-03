package top.ljming.common.conviction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvictionClientWrap implements ConvictionClient {

    @Autowired
    ConvictionClient convictionClient;

    @Override
    public Conviction.UserListDTO getUserList() {
        String where = "ConvictionClientWrap -> getUserList";
        Conviction.UserListDTO ret;
        try {
            ret = convictionClient.getUserList();
        } catch (Exception e) {
            throw new RuntimeException("哎哟，出错了;" + where);
        }
        return ret;
    }

    @Override
    public Conviction.UserDTO createUser(Conviction.UserOption userOption) {
        String where = "ConvictionClientWrap -> createUser";
        Conviction.UserDTO ret;
        try {
            ret = convictionClient.createUser(userOption);
        } catch (Exception e) {
            throw new RuntimeException("哎哟，出错了;" + where);
        }
        return ret;
    }

    @Override
    public Conviction.UserDTO getUserById(Long userId) {
        String where = "ConvictionClientWrap -> getUserById";
        Conviction.UserDTO ret;
        try {
            ret = convictionClient.getUserById(userId);
        } catch (Exception e) {
            throw new RuntimeException("哎哟，出错了;" + where);
        }
        return ret;
    }

    @Override
    public Conviction.UserDTO updateUser(Long userId, Conviction.UserUpdateOption userUpdateOption) {
        String where = "ConvictionClientWrap -> updateUser";
        Conviction.UserDTO ret;
        try {
            ret = convictionClient.updateUser(userId, userUpdateOption);
        } catch (Exception e) {
            throw new RuntimeException("哎哟，出错了;" + where);
        }
        return ret;
    }
}
