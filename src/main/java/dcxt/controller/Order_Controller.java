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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @ResponseBody
    @RequestMapping(value = "/jieshou/{id}", method = RequestMethod.PUT)
    public Msg changeStatus1(Order_ o, HttpServletRequest request) {
        o.setStatus(2);
        order_Service.changeStatus(o);
        return Msg.success("");
    }

    @ResponseBody
    @RequestMapping(value = "/jushou/{id}", method = RequestMethod.PUT)
    public Msg changeStatus2(Order_ o, HttpServletRequest request) {
        o.setStatus(5);
        order_Service.changeStatus(o);
        return Msg.success("");
    }

    @ResponseBody
    @RequestMapping(value = "/songhuo/{id}", method = RequestMethod.PUT)
    public Msg changeStatus3(Order_ o, HttpServletRequest request) {
        o.setStatus(3);
        order_Service.changeStatus(o);
        return Msg.success("");
    }

    @ResponseBody
    @RequestMapping(value = "/quxiao/{id}", method = RequestMethod.PUT)
    public Msg changeStatus4(Order_ o, HttpServletRequest request) {
        o.setStatus(5);
        order_Service.changeStatus(o);
        return Msg.success("");
    }

    @ResponseBody
    @RequestMapping(value = "/songda/{id}", method = RequestMethod.PUT)
    public Msg changeStatus5(Order_ o, HttpServletRequest request) {
        o.setStatus(4);
        order_Service.changeStatus(o);
        return Msg.success("");
    }
}
