package dcxt.service;

import dcxt.dao.CategoryMapper;
import dcxt.dao.ProductMapper;
import dcxt.pojo.Category;
import dcxt.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryMapper categoryMapper;


    public List<Product> getAll() {
        return productMapper.selectByExampleWithSort(null);
    }

    public void changeStatus(Product p) {
        productMapper.updateByPrimaryKeySelective(p);
    }

    public void insertProduct(Product product) {
        productMapper.insertSelective(product);
    }

    public List<Product> searchName(String title) {
        return productMapper.selectByExample(title);
    }

    public Product getP(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    public void updateP(Product p) {
        productMapper.updateByPrimaryKeySelective(p);
    }

    public List<Category> getSorts() {
        return categoryMapper.selectByExample(null);
    }
}
