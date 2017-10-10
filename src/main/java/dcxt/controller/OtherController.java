package dcxt.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dcxt.bean.Detail;
import dcxt.bean.Msg;
import dcxt.pojo.Day_info;
import dcxt.pojo.Shop;
import dcxt.service.OtherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.Resource;

@Controller
@RequestMapping("")
public class OtherController {
    @Resource
    OtherService otherService;

    /*对本店的操作*/


    /*改*/
    @RequestMapping(value = "/shop", method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateShop(Float money, String name, String tel, String address) {
        Shop shop=new Shop();
        shop.setName(name);
        shop.setAddress(address);
        shop.setMoney(money);
        shop.setTel(tel);
        int i=otherService.updateShop(shop);
        if (i==1) {
            return Msg.success("修改成功！");
        }else{
            return Msg.fail("修改失败！");
        }
    }

    /*查*/
    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    @ResponseBody
    public Msg getShop() {
        List<Shop> shop=otherService.getShop();
        return Msg.success("").add("sorts", shop);
    }

    /*获得每日信息*/
    @RequestMapping(value = "/day_info", method = RequestMethod.GET)
    @ResponseBody
    public Msg getDay_info(){
        int total_money=otherService.getTotal();
        Date date=new Date();//取时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        String last_day = formatter.format(date);
        Day_info lastDay=otherService.getDay(last_day);
        List<Day_info> lastWeekDetail=otherService.getWeek();
        Day_info today_=otherService.getDay(today);
        if(today_==null){
            lastWeekDetail.remove(7);
        }else {
            lastWeekDetail.remove(0);
        }
        Day_info lastWeek=new Day_info();
        int product_num=0;
        int order_num=0;
        int total=0;
        for(int i=0;i<lastWeekDetail.size();i++){
            product_num+=lastWeekDetail.get(i).getpTotal();
            order_num+=lastWeekDetail.get(i).getoTotal();
            total+=lastWeekDetail.get(i).getPriceTotal();
        }
        lastWeek.setpTotal(product_num);
        lastWeek.setoTotal(order_num);
        lastWeek.setPriceTotal(total);
        return Msg.success("查询成功").add("total_money",total_money).add("last_day_detail",lastDay).add("last_week",lastWeek).add("last_week_detail",lastWeekDetail);
    }


}
