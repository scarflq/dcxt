package dcxt.controller;

import dcxt.bean.Msg;
import dcxt.pojo.Shop;
import dcxt.service.OtherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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


}
