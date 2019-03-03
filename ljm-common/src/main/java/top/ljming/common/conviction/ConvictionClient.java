package top.ljming.common.conviction;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "conviction", configuration = ConvictionClientConfiguration.class, fallback = ConvictionClientWrap.class)
@FeignClient(name = "conviction", configuration = ConvictionClientConfiguration.class)
public interface ConvictionClient {

    @RequestLine("GET /api/conviction/v1/users")
    Conviction.UserListDTO getUserList();

    @RequestLine("POST /api/conviction/v1/users/new")
    Conviction.UserDTO createUser(@RequestBody Conviction.UserOption userOption);

    @RequestLine("GET /api/conviction/v1/users/{userId}")
    Conviction.UserDTO getUserById(@Param("userId") Long userId);

    @RequestLine("PATCH /api/conviction/v1/users/{userId}")
    Conviction.UserDTO updateUser(@Param("userId") Long userId, @RequestBody Conviction.UserUpdateOption userUpdateOption);

}
