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
public class ProductController{
    @Resource
    ProductService productService;

    @Resource
    UploadService uploadService;

    @Resource
    QiNiuImageStyleService qiNiuImageStyleService;
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Msg Upload(@RequestParam("file") MultipartFile file,@RequestParam String width,HttpServletRequest request) throws IOException {

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
    @RequestMapping("/addCategorys")
    @ResponseBody
    public Msg addCategorys(String title) {
        Category c=new Category();
        c.setcTitle(title);
        int i=productService.addCategorys(c);
        if (i==1) {
            return Msg.success("新增成功！");
        }else{
            return Msg.fail("新增失败！");
        }
    }

    /*删*/
    @RequestMapping("/deleteCategorys")
    @ResponseBody
    public Msg deleteCategorys(int id) {
        int i=productService.deleteCategorys(id);
        if (i==1) {
            return Msg.success("删除成功！");
        }else{
            return Msg.fail("删除失败！");
        }
    }

    /*改*/
    @RequestMapping("/updateCategorys")
    @ResponseBody
    public Msg updateCategorys(int id, String title) {
        Category c=new Category();
        c.setcId(id);
        c.setcTitle(title);
        int i=productService.updateCategorys(c);
        if (i==1) {
            return Msg.success("修改成功！");
        }else{
            return Msg.fail("修改失败！");
        }
    }

    /*查*/
    @RequestMapping("/getCategorys")
    @ResponseBody
    public Msg getCategorys() {
        List<Category> list = productService.getSorts();
        return Msg.success("").add("sorts", list);
    }

    /*显示菜单*/
    @RequestMapping("/menu")
    @ResponseBody
    public Msg getMenus(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<Product> cai = productService.getAll();
        PageInfo page = new PageInfo(cai, 5);
        return Msg.success("").add("pageInfo", page);
    }

    /*新增菜品*/
    @RequestMapping(value="/xinzeng",method=RequestMethod.POST)
    @ResponseBody
    public Msg xinzeng(String title,String description, String img,float price ,int category){
        Product product=new Product();
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
        productService.insertProduct(product);
        return Msg.success("新增成功");
    }

    /*下架*/
    @ResponseBody
    @RequestMapping(value = "/xiajia/{id}", method = RequestMethod.PUT)
    public Msg changeStatus1(Product p, HttpServletRequest request) {
        p.setStatus(1);
        productService.changeStatus(p);
        return Msg.success("下架成功");
    }

    /*上架*/
    @ResponseBody
    @RequestMapping(value = "/shangjia/{id}", method = RequestMethod.PUT)
    public Msg changeStatus2(Product p, HttpServletRequest request) {
        p.setStatus(0);
        productService.changeStatus(p);
        return Msg.success("上架成功");
    }

    /*通过名字模糊查询*/
    @ResponseBody
    @RequestMapping(value="/search",method=RequestMethod.POST)
    public Msg searchCai(@RequestParam(value = "pn", defaultValue = "1") Integer pn,String title){
        PageHelper.startPage(pn, 10);
        List<Product> men=productService.searchName(title);
        PageInfo page = new PageInfo(men, 5);
        return Msg.success("").add("pageInfo", page);
    }

    /*修改菜品属性*/
    @RequestMapping(value="/gai1",method=RequestMethod.POST)
    @ResponseBody
    public Msg getMe(Integer id){
        Product men = productService.getP(id);
        return Msg.success("").add("product", men);
    }

    @ResponseBody
    @RequestMapping(value="/gai2",method=RequestMethod.POST)
    public Msg saveMe(int id,String title,String description, String img,float price ,int category){
        Product p=new Product();
        p.setId(id);
        p.setCategory(category);
        p.setDescription(description);
        p.setTitle(title);
        p.setPrice(price);
        p.setImg(img);
        productService.updateP(p);
        return Msg.success("修改成功");
    }

    /*分类查询*/
    @ResponseBody
    @RequestMapping(value="/category",method=RequestMethod.POST)
    public Msg categoryP(int category, @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<Product> cai = productService.getCategory(category);
        PageInfo page = new PageInfo(cai, 5);
        return Msg.success("").add("pageInfo", page);
    }

    /*前台显示：只显示已上架商品，直接显示、搜索显示、分类显示3种*/
    /*前台直接显示*/
    @RequestMapping("/menuf")
    @ResponseBody
    public Msg getMenusf(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<Product> cai = productService.getAllf();
        PageInfo page = new PageInfo(cai, 5);
        return Msg.success("").add("pageInfo", page);
    }

    /*前台搜索显示*/
    @ResponseBody
    @RequestMapping(value="/searchf",method=RequestMethod.POST)
    public Msg searchCaif(@RequestParam(value = "pn", defaultValue = "1") Integer pn,String title){
        PageHelper.startPage(pn, 10);
        List<Product> men=productService.searchNamef(title);
        PageInfo page = new PageInfo(men, 5);
        return Msg.success("").add("pageInfo", page);
    }

    /*前台分类显示*/
    @ResponseBody
    @RequestMapping(value="/categoryf",method=RequestMethod.POST)
    public Msg categoryPf(int category, @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<Product> cai = productService.getCategoryf(category);
        PageInfo page = new PageInfo(cai, 5);
        return Msg.success("").add("pageInfo", page);
    }

}
