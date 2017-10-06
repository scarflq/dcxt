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

    public List<Product> getCategory(int category) {
        return productMapper.selectByCategory(category);
    }

    public int addCategorys(Category c) {
        return categoryMapper.insertSelective(c);
    }

    public int deleteCategorys(Integer id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }

    public int updateCategorys(Category c) {
        return categoryMapper.updateByPrimaryKeySelective(c);
    }

    public List<Product> getAllf() {
        return productMapper.selectByExampleWithSortf(null);
    }

    public List<Product> searchNamef(String title) {
        return productMapper.selectByExamplef(title);
    }

    public List<Product> getCategoryf(int category) {
        return productMapper.selectByCategoryf(category);
    }

    public List<Product> searchNameAndCategory(String keyword, Integer category) {
        Product product=new Product();
        product.setCategory(category);
        product.setTitle(keyword);
        return productMapper.selectByNAC(product);
    }

    public List<Product> searchNameAndCategoryf(String test, Integer category) {
        Product product=new Product();
        product.setCategory(category);
        product.setTitle(test);
        return productMapper.selectByNACf(product);
    }
}
