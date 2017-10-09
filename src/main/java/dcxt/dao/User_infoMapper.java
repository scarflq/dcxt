package dcxt.dao;

import dcxt.pojo.User_info;
import dcxt.pojo.User_infoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface User_infoMapper {
    long countByExample(User_infoExample example);

    int deleteByExample(User_infoExample example);

    int deleteByPrimaryKey(String username);

    int insert(User_info record);

    int insertSelective(User_info record);

    List<User_info> selectByExample(String string);

    User_info selectByPrimaryKey(String username);

    int updateByExampleSelective(@Param("record") User_info record, @Param("example") User_infoExample example);

    int updateByExample(@Param("record") User_info record, @Param("example") User_infoExample example);

    int updateByPrimaryKeySelective(User_info record);

    int updateByPrimaryKey(User_info record);

    List<User_info> selectAll(User_infoExample example);

    List<User_info> selectMoney(User_infoExample example);

    List<User_info> selectByExampleM(String test);

    List<User_info> multiSearch(
            @Param("keyword") String keyword,
            @Param("money_sort") String money_sort
    );
}