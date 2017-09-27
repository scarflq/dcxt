package dcxt.controller;

import dcxt.bean.Msg;
import dcxt.pojo.Product;
import dcxt.pojo.User_info;
import dcxt.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("")
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("/getuser")
    @ResponseBody
    public Msg getUser(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<User_info> us =userService.getAll();
        PageInfo page = new PageInfo(us, 5);
        return Msg.success("").add("pageInfo", page);
    }

    @ResponseBody
    @RequestMapping(value="/zhuxiao/{id}",method=RequestMethod.PUT)
    public Msg zhuxiaoUs(User_info u, HttpServletRequest request){
        u.setStatus(1);
        userService.delete(u);
        return Msg.success("注销成功！");
    }

    @ResponseBody
    @RequestMapping(value="/qiyong/{id}",method=RequestMethod.PUT)
    public Msg qiyongUs(User_info u, HttpServletRequest request){
        u.setStatus(0);
        userService.delete(u);
        return Msg.success("");
    }

    /*模糊查询*/
    /*查找用户名*/
    @ResponseBody
    @RequestMapping(value="/chauser/{title}",method=RequestMethod.GET)
    public Msg searchuser(@RequestParam(value = "pn", defaultValue = "1") Integer pn,@PathVariable("title")String title){
        PageHelper.startPage(pn, 10);
        String title1 = null;
        try {
            title1 = new String(title.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<User_info> men=userService.search(title1);
        PageInfo page = new PageInfo(men, 5);
        return Msg.success("").add("pageInfo", page);
    }

    /*修改用户信息*/
    @RequestMapping(value="/gu/{id}",method=RequestMethod.GET)
    @ResponseBody
    public Msg getU(@PathVariable("id")String username){
        User_info men = userService.getU(username);
        return Msg.success("").add("user_info", men);
    }

    @ResponseBody
    @RequestMapping(value="/gaiu/{id}",method=RequestMethod.PUT)
    public Msg saveU(User_info u, HttpServletRequest request){
        userService.update(u);
        return Msg.success("");
    }

    /*按照余额排序*/
    @RequestMapping("/money")
    @ResponseBody
    public Msg money(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<User_info> us =userService.money();
        PageInfo page = new PageInfo(us, 5);
        return Msg.success("").add("pageInfo", page);
    }

}
