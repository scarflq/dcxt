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
import javax.validation.Valid;
import org.springframework.validation.BindingResult;


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


    @RequestMapping("/categorys")
    @ResponseBody
    public Msg getSorts() {
        List<Category> list = productService.getSorts();
        return Msg.success("").add("sorts", list);
    }

    @RequestMapping("/menu")
    @ResponseBody
    public Msg getMenus(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<Product> cai = productService.getAll();
        PageInfo page = new PageInfo(cai, 5);
        return Msg.success("").add("pageInfo", page);
    }

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

    @ResponseBody
    @RequestMapping(value = "/xiajia/{id}", method = RequestMethod.PUT)
    public Msg changeStatus1(Product p, HttpServletRequest request) {
        p.setStatus(1);
        productService.changeStatus(p);
        return Msg.success("下架成功");
    }
    @ResponseBody
    @RequestMapping(value = "/shangjia/{id}", method = RequestMethod.PUT)
    public Msg changeStatus2(Product p, HttpServletRequest request) {
        p.setStatus(0);
        productService.changeStatus(p);
        return Msg.success("上架成功");
    }

    @ResponseBody
    @RequestMapping(value="/search/{title}",method=RequestMethod.GET)
    public Msg searchCai(@RequestParam(value = "pn", defaultValue = "1") Integer pn,@PathVariable("title")String title){
        PageHelper.startPage(pn, 10);
        String title1 = null;
        try {
            title1 = new String(title.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Product> men=productService.searchName(title1);
        PageInfo page = new PageInfo(men, 5);
        return Msg.success("").add("pageInfo", page);
    }

    @RequestMapping(value="/gai1",method=RequestMethod.GET)
    @ResponseBody
    public Msg getMe(@PathVariable("id")Integer id){
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

}
