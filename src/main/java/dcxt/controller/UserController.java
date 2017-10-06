package dcxt.controller;

import dcxt.bean.Msg;
import dcxt.pojo.User_info;
import dcxt.pojo.User_login;
import dcxt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("")
public class UserController {
    @Resource
    UserService userService;

    /*登录*/
    @ResponseBody
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public Msg login(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password, HttpSession session){
        User_login ul = userService.login(username);
        if(ul!=null){
            if (password.equals(ul.getPassword())){
                User_info ui=userService.getStates(ul.getUsername());
                if(ui.getStatus()!=1){
                    session.setAttribute("username",ui.getUsername());
                    return Msg.success("登录成功！");
                }
                else{
                    return Msg.fail("登录失败，用户名失效！");
                }
            }
            else{
                return Msg.fail("登录失败，密码错误");
            }
        }
        else{
            return Msg.fail("登录失败，没有该用户");
        }

    }

    /*注册*/
    @ResponseBody
    @RequestMapping(value="/register",method=RequestMethod.POST)
    public Msg save(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password,@RequestParam(value = "password1") String password1){
        if (password.equals(password1)) {
            User_login user_login=userService.login(username);
            if (user_login!=null) {
                return Msg.fail("用户名已存在！");
            } else if (!isStandardUsername(username)){
                return Msg.fail("用户名格式错误！");
            }else if (!isStandardPassword(password)){
                return Msg.fail("密码格式错误！");
            } else{
                User_login ul=new User_login();
                ul.setUsername(username);
                ul.setPassword(password);
                userService.saveul(ul);
                User_info ui=new User_info();
                ui.setUsername(username);
                ui.setStatus(0);
                Date date = new Date();
                Timestamp timeStamp = new Timestamp(date.getTime());
                ui.setCreateTime(timeStamp);
                float f=0;
                ui.setMoney(f);
                userService.saveui(ui);
                return Msg.success("注册成功！");
            }
        }
        else{
            return Msg.fail("两次输入的密码不同，请重新输入！");
        }
    }
    

    public boolean isStandardUsername(String username){
        Pattern p = Pattern.compile("[A-Za-z0-9]{6,16}");
        Matcher m = p.matcher(username);
        return m.matches();
    }

    public boolean isStandardPassword(String password){
        Pattern p = Pattern.compile("\\w{6,16}");
        Matcher m = p.matcher(password);
        return m.matches();
    }





    /*用户注销*/
    @ResponseBody
    @RequestMapping(value="/signout",method=RequestMethod.POST)
    public Msg clear( HttpSession session){
        session.invalidate();
        return Msg.success("注销成功");
    }

    /*修改个人信息*/
    /*1、获取当前用户信息*/
    @ResponseBody
    @RequestMapping(value="/user",method=RequestMethod.GET)
    public Msg changeU(HttpSession session){
        if(session.getAttribute("username")!=null){
            User_info men = userService.getU(session.getAttribute("username").toString());
            return Msg.success("").add("user_info", men);
        }
        else{
            return Msg.fail("请先登录！");
        }
    }

    /*2、输入新的个人信息*/
    @ResponseBody
    @RequestMapping(value="/user",method=RequestMethod.PUT)
    public Msg changeU2(String username, String password, String address, String tel){
        User_info ui=new User_info();
        ui.setUsername(username);
        ui.setAddress(address);
        ui.setTel(tel);
        userService.update(ui);
        User_login ul=new User_login();
        ul.setUsername(username);
        ul.setPassword(password);
        userService.update2(ul);
        return Msg.success("修改成功！");
    }

    /*注销和启用*/
    @ResponseBody
    @RequestMapping(value="/user_remove",method=RequestMethod.POST)
    public Msg zhuxiaoUs(String username){
        User_info u=new User_info();
        u.setStatus(1);
        u.setUsername(username);
        userService.update(u);
        return Msg.success("注销成功！");
    }

    @ResponseBody
    @RequestMapping(value="/user_recover",method=RequestMethod.POST)
    public Msg qiyongUs(String username){
        User_info u=new User_info();
        u.setUsername(username);
        u.setStatus(0);
        userService.update(u);
        return Msg.success("启用成功！");
    }

    /*修改用户信息*/
    @ResponseBody
    @RequestMapping(value="/ad_user",method=RequestMethod.POST)
    public Msg adChangeU2(String username, Float money, String address, String tel) throws UnsupportedEncodingException {
        User_info ui=new User_info();
        ui.setUsername(username);
        String add=new String(address.getBytes("ISO-8859-1"), "UTF-8");
        ui.setAddress(add);
        ui.setTel(tel);
        ui.setMoney(money);
        userService.update(ui);
        return Msg.success("修改成功！");
    }

    /*显示所有用户*/
    /*如果money无值则按用户名排序，否则输入money=1（或者任意不为0的数字）则按余额排序*/
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public Msg getUsers(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn,
            @RequestParam(value = "search", defaultValue = "null") String keyword,
            @RequestParam(value = "money", defaultValue = "0") Integer money
    ) throws UnsupportedEncodingException {
        PageHelper.startPage(pn, 5);
        List<User_info> users;
        String test= new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
        if(keyword.equals("null")) {
            if(money.equals(0)){
                users = userService.getAll();
            }
            else {
                users = userService.money();
            }
        } else {
            if(money.equals(0)){
                users = userService.search(test);
            }
            else{
                users = userService.searchByMoney(test);
            }
        }
        PageInfo page = new PageInfo(users, 5);
        return Msg.success("").add("pageInfo", page);
    }



    public Msg getUser(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<User_info> us =userService.getAll();
        PageInfo page = new PageInfo(us, 5);
        return Msg.success("").add("pageInfo", page);
    }

    /*模糊查询*/
    /*查找用户名*/
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
    /*按照余额排序*/
    public Msg money(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<User_info> us =userService.money();
        PageInfo page = new PageInfo(us, 5);
        return Msg.success("").add("pageInfo", page);
    }

}
