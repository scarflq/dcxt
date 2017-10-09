package dcxt.dao;

import dcxt.pojo.Day_info;
import dcxt.pojo.Day_infoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Day_infoMapper {
    long countByExample(Day_infoExample example);

    int deleteByExample(Day_infoExample example);

    int deleteByPrimaryKey(String date);

    int insert(Day_info record);

    int insertSelective(Day_info record);

    List<Day_info> selectByExample(Day_infoExample example);

    Day_info selectByPrimaryKey(String date);

    int updateByExampleSelective(@Param("record") Day_info record, @Param("example") Day_infoExample example);

    int updateByExample(@Param("record") Day_info record, @Param("example") Day_infoExample example);

    int updateByPrimaryKeySelective(Day_info record);

    int updateByPrimaryKey(Day_info record);

    int getTotal();

    List<Day_info> getweek();
}