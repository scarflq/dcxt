package dcxt.dao;

import dcxt.pojo.Lala;
import dcxt.pojo.LalaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LalaMapper {
    long countByExample(LalaExample example);

    int deleteByExample(LalaExample example);

    int deleteByPrimaryKey(Integer acb);

    int insert(Lala record);

    int insertSelective(Lala record);

    List<Lala> selectByExample(LalaExample example);

    Lala selectByPrimaryKey(Integer acb);

    int updateByExampleSelective(@Param("record") Lala record, @Param("example") LalaExample example);

    int updateByExample(@Param("record") Lala record, @Param("example") LalaExample example);

    int updateByPrimaryKeySelective(Lala record);

    int updateByPrimaryKey(Lala record);
}