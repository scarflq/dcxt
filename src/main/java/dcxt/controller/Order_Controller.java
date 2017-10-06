package dcxt.controller;

import dcxt.bean.Msg;
import javax.servlet.http.HttpServletRequest;
import dcxt.pojo.Order_;
import dcxt.service.Order_Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("")
public class Order_Controller{
    @Resource
    Order_Service order_Service;

    /*后台显示所有订单*/
    @RequestMapping(value = "/ad_order", method = RequestMethod.POST)
    @ResponseBody
    public Msg getAd_order(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn,
            @RequestParam(value = "status", defaultValue = "0") Integer status
    ){
        PageHelper.startPage(pn, 10);
        List<Order_> o ;
        if(status.equals(0)){
            o = order_Service.getAll();
        }
        else {
            o = order_Service.getByStatus(status);
        }
        PageInfo order = new PageInfo(o, 5);
        return Msg.success("").add("order", order);
    }

    /*前台显示所有当前session储存的用户名的订单*/
    @RequestMapping(value="/user_order",method = RequestMethod.POST)
    @ResponseBody
    public Msg getOrder(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                        @RequestParam(value = "status", defaultValue = "0") Integer status,
                        HttpSession session) {
        PageHelper.startPage(pn, 10);
        String u_id="lq";
        u_id=session.getAttribute("username").toString();
        List<Order_> o ;
        if(status.equals(0)){
            o = order_Service.getAllf(u_id);
        }
        else {
            Order_ o1=new Order_();
            o1.setStatus(status);
            o1.setuId(u_id);
            o = order_Service.getByStatusf(o1);
        }
        PageInfo order = new PageInfo(o, 5);
        return Msg.success("").add("order", order);
    }
/*    @ResponseBody
    @RequestMapping(value="/jieshou",method=RequestMethod.POST)
    public Msg changeStatus1(int id){
        Order_ o=new Order_();
        o.setId(id);
        o.setStatus(2);
        order_Service.changeStatus(o);
        return Msg.success("接收成功！");
    }*/

    /*接收*/
    @ResponseBody
    @RequestMapping(value = "/order/receive/{id}", method = RequestMethod.PUT)
    public Msg changeStatus1(Order_ o, HttpServletRequest request) {
        o.setStatus(2);
        order_Service.changeStatus(o);
        return Msg.success("接收成功！");
    }

    /*拒收*/
    @ResponseBody
    @RequestMapping(value = "/order/reject/{id}", method = RequestMethod.PUT)
    public Msg changeStatus2(Order_ o, HttpServletRequest request) {
        o.setStatus(5);
        order_Service.changeStatus(o);
        return Msg.success("拒收成功！");
    }

    /*送货*/
    @ResponseBody
    @RequestMapping(value = "/order/send_success/{id}", method = RequestMethod.PUT)
    public Msg changeStatus3(Order_ o, HttpServletRequest request) {
        o.setStatus(3);
        order_Service.changeStatus(o);
        return Msg.success("送货！");
    }

    /*取消*/
    @ResponseBody
    @RequestMapping(value = "/order/cancel/{id}", method = RequestMethod.PUT)
    public Msg changeStatus4(Order_ o, HttpServletRequest request) {
        o.setStatus(5);
        order_Service.changeStatus(o);
        return Msg.success("取消订单！");
    }

    /*送达*/
    @ResponseBody
    @RequestMapping(value = "/order/confirm/{id}", method = RequestMethod.PUT)
    public Msg changeStatus5(Order_ o, HttpServletRequest request) {
        o.setStatus(4);
        order_Service.changeStatus(o);
        return Msg.success("货物已送达！");
    }

    /*下单*/
    @ResponseBody
    @RequestMapping(value="/order",method = RequestMethod.POST)
    public Msg xiadan(String detail, HttpSession session){
        if(session.getAttribute("username")!=null){
            Order_ o=new Order_();
            o.setStatus(1);
            String uid="lq";
            uid=session.getAttribute("username").toString();
            o.setuId(uid);
            Date date = new Date();
            Timestamp timeStamp = new Timestamp(date.getTime());
            o.setCreateTime(timeStamp);
            o.setDetail(detail);
            int i=order_Service.xiadan(o);
            if (i==1) {
                return Msg.success("下单成功！");
            }else{
                return Msg.fail("下单失败！");
            }
        }
        else{
            return Msg.fail("请先登录！");
        }
    }

    /*评论*/
    @ResponseBody
    @RequestMapping(value="/order/comment",method = RequestMethod.PUT)
    public Msg comment(String comment, int id){
        Order_ o=new Order_();
        o.setId(id);
        o.setComment(comment);
        int i=order_Service.comment(o);
        if (i==1) {
            return Msg.success("评论成功！");
        }else{
            return Msg.fail("评论失败！");
        }
    }

}
