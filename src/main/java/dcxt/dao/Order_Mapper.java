package dcxt.dao;

import dcxt.pojo.Order_;
import dcxt.pojo.Order_Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Order_Mapper {
    long countByExample(Order_Example example);

    int deleteByExample(Order_Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order_ record);

    int insertSelective(Order_ record);

    List<Order_> selectByExample(Order_Example example);

    Order_ selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order_ record, @Param("example") Order_Example example);

    int updateByExample(@Param("record") Order_ record, @Param("example") Order_Example example);

    int updateByPrimaryKeySelective(Order_ record);

    int updateByPrimaryKey(Order_ record);
}