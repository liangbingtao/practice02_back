package practice02_back.mapper;

import org.apache.ibatis.annotations.*;
import practice02_back.model.User;

@Mapper
public interface UserMapper {

    User findByToken(@Param("token") String token);

    User findByUsername(@Param("username") String username);

    void save(User user);

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    void updateUser(User user);

    User selectByPhone(String phone);
}
