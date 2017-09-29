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

    @RequestMapping("/adord")
    @ResponseBody
    public Msg getAdord(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<Order_> o = order_Service.getAll();
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

    @ResponseBody
    @RequestMapping(value = "/jieshou/{id}", method = RequestMethod.PUT)
    public Msg changeStatus1(Order_ o, HttpServletRequest request) {
        o.setStatus(2);
        order_Service.changeStatus(o);
        return Msg.success("接收成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/jushou/{id}", method = RequestMethod.PUT)
    public Msg changeStatus2(Order_ o, HttpServletRequest request) {
        o.setStatus(5);
        order_Service.changeStatus(o);
        return Msg.success("拒收成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/songhuo/{id}", method = RequestMethod.PUT)
    public Msg changeStatus3(Order_ o, HttpServletRequest request) {
        o.setStatus(3);
        order_Service.changeStatus(o);
        return Msg.success("送货！");
    }

    @ResponseBody
    @RequestMapping(value = "/quxiao/{id}", method = RequestMethod.PUT)
    public Msg changeStatus4(Order_ o, HttpServletRequest request) {
        o.setStatus(5);
        order_Service.changeStatus(o);
        return Msg.success("取消订单！");
    }

    @ResponseBody
    @RequestMapping(value = "/songda/{id}", method = RequestMethod.PUT)
    public Msg changeStatus5(Order_ o, HttpServletRequest request) {
        o.setStatus(4);
        order_Service.changeStatus(o);
        return Msg.success("货物已送达！");
    }

    @ResponseBody
    @RequestMapping(value="/xiadan",method = RequestMethod.POST)
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

    @ResponseBody
    @RequestMapping(value="/comment",method = RequestMethod.POST)
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
