package dcxt.controller;

import dcxt.bean.Msg;
import dcxt.pojo.Category;
import dcxt.pojo.Product;
import dcxt.service.ProductService;

import javax.servlet.http.HttpServletRequest;

import dcxt.service.QiNiuImageStyleService;
import dcxt.service.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;


@Controller
@RequestMapping("")
public class ProductController {
    @Resource
    ProductService productService;

    @Resource
    UploadService uploadService;

    @Resource
    QiNiuImageStyleService qiNiuImageStyleService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Msg Upload(@RequestParam("file") MultipartFile file, @RequestParam String width, HttpServletRequest request) throws IOException {

        // 获得原始文件名
        String fileOriginalName = file.getOriginalFilename();
        String fileExt = fileOriginalName.substring(fileOriginalName.lastIndexOf(".") + 1);
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;

        // 获得项目的路径
        ServletContext sc = request.getSession().getServletContext();

        // 设定文件保存的目录
        String realPath = sc.getRealPath("/static/imgUpload") + '/';

        String localFilePath = uploadService.uploadToLocal(file, realPath, fileName);

        //上传至七牛
        String QiNiuKey = uploadService.uploadToQINiu(localFilePath, fileName);

        // 完成上传至七牛云后删除本地文件
        File uploadImg = new File(localFilePath);
        if (uploadImg.exists() && uploadImg.isFile()) {
            uploadImg.delete();
        }

        width = width == null ? "200" : width;

        String imageUrl = qiNiuImageStyleService.getImageUrl(width, QiNiuKey);

        return Msg.success("上传成功").add("imageUrl", imageUrl);
    }

    /*对分类的操作*/
    /*增*/
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    @ResponseBody
    public Msg addCategorys(String title) {
        Category c = new Category();
        c.setcTitle(title);
        int ret = productService.addCategorys(c);
        if (ret == 1) {
            return Msg.success("新增成功！").add("status", ret);
        } else {
            return Msg.fail("新增失败！").add("status", ret);
        }
    }

    /*删*/
    @RequestMapping(value = "/delete_category", method = RequestMethod.POST)
    @ResponseBody
    public Msg deleteCategorys(Integer id) {
        int ret = productService.deleteCategorys(id);
        if (ret == 1) {
            return Msg.success("删除成功！").add("status", ret);
        } else {
            return Msg.fail("删除失败！").add("status", ret);
        }
    }

    /*改*/
    @RequestMapping(value = "/update_category", method = RequestMethod.POST)
    @ResponseBody
    public Msg updateCategorys(Integer id, String title) {
        Category c = new Category();
        c.setcId(id);
        c.setcTitle(title);
        int ret = productService.updateCategorys(c);
        if (ret == 1) {
            return Msg.success("修改成功！").add("status", ret);
        } else {
            return Msg.fail("修改失败！").add("status", ret);
        }
    }

    /*查*/
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    @ResponseBody
    public Msg getCategorys() {
        List<Category> list = productService.getSorts();
        return Msg.success("").add("sorts", list);
    }


    /*新增菜品*/
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseBody
    public Msg xinzeng(String title, String description, String img, float price, int category) {
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(description);
        product.setTitle(title);
        product.setPrice(price);
        product.setImg(img);
        product.setStatus(0);
        product.setGood(0);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        product.setCreateTime(timeStamp);
        int status = productService.insertProduct(product);
        if (status == 0) {
            return Msg.fail("新增菜品失败");
        } else {
            return Msg.success("新增菜品成功");
        }
    }

    /*下架*/
    @ResponseBody
    @RequestMapping(value = "/product_down/{id}", method = RequestMethod.PUT)
    public Msg changeStatus1(Product p, HttpServletRequest request) {
        p.setStatus(1);
        productService.changeStatus(p);
        return Msg.success("下架成功");
    }

    /*上架*/
    @ResponseBody
    @RequestMapping(value = "/product_up/{id}", method = RequestMethod.PUT)
    public Msg changeStatus2(Product p, HttpServletRequest request) {
        p.setStatus(0);
        productService.changeStatus(p);
        return Msg.success("上架成功");
    }

    /*修改菜品属性*/
    @ResponseBody
    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    public Msg saveMe(Integer id, String title, String description, String img, Float price, Integer category) {
        Product p = new Product();
        p.setId(id);
        p.setCategory(category);
        p.setDescription(description);
        p.setTitle(title);
        p.setPrice(price);
        p.setImg(img);
        productService.updateP(p);
        return Msg.success("修改成功");
    }

    /*后台显示菜单*/
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public Msg getMenus(@RequestParam(value = "pn", defaultValue = "1") Integer pn, String keyword, Integer category) throws UnsupportedEncodingException {
        PageHelper.startPage(pn, 5);
        List<Product> products;
        String test;
        if (keyword == null) {
            if (category == null) {
                products = productService.getAll();
            } else {
                products = productService.getCategory(category);
            }
        } else {
            test= new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
            if (category == null) {
                products = productService.searchName(test);
            } else {
                products = productService.searchNameAndCategory(test, category);
            }
        }
        PageInfo page = new PageInfo(products, 10);
        return Msg.success("").add("pageInfo", page);
    }


    /*前台显示：只显示已上架商品，直接显示、搜索显示、分类显示3种*/
    @RequestMapping(value = "/user_products", method = RequestMethod.POST)
    @ResponseBody
    public Msg getMenusForUser(@RequestParam(value = "pn", defaultValue = "1") Integer pn, String keyword, Integer category) throws UnsupportedEncodingException {
        PageHelper.startPage(pn, 100);
        List<Product> products;
        String test;
        if (keyword == null) {
            if (category == null) {
                products = productService.getAllf();
            } else {
                products = productService.getCategoryf(category);
            }
        } else {
            test= new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
            if (category == null) {
                products = productService.searchNamef(test);
            } else {
                products = productService.searchNameAndCategoryf(test, category);
            }
        }
        PageInfo page = new PageInfo(products, 5);
        return Msg.success("").add("pageInfo", page);
    }
}
