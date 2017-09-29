package dcxt.dao;

import dcxt.pojo.Product;
import dcxt.pojo.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper {
    long countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(String string);

    Product selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectByExampleWithSort(ProductExample example);

    List<Product> selectByCategory(int category);

    List<Product> selectByExampleWithSortf(ProductExample example);

    List<Product> selectByExamplef(String title);

    List<Product> selectByCategoryf(int category);
}