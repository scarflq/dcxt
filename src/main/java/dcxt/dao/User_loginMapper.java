package dcxt.dao;

import dcxt.pojo.User_login;
import dcxt.pojo.User_loginExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface User_loginMapper {
    long countByExample(User_loginExample example);

    int deleteByExample(User_loginExample example);

    int deleteByPrimaryKey(String username);

    int insert(User_login record);

    int insertSelective(User_login record);

    List<User_login> selectByExample(User_loginExample example);

    User_login selectByPrimaryKey(String username);

    int updateByExampleSelective(@Param("record") User_login record, @Param("example") User_loginExample example);

    int updateByExample(@Param("record") User_login record, @Param("example") User_loginExample example);

    int updateByPrimaryKeySelective(User_login record);

    int updateByPrimaryKey(User_login record);
}