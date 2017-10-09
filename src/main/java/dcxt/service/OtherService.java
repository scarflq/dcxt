package dcxt.service;

import dcxt.dao.Day_infoMapper;
import dcxt.dao.ShopMapper;
import dcxt.pojo.Day_info;
import dcxt.pojo.Shop;
import dcxt.pojo.ShopExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class OtherService {

    @Autowired
    ShopMapper shopMapper;
    @Autowired
    Day_infoMapper day_infoMapper;


    public List<Shop> getShop() {
        return shopMapper.selectByExample(null);
    }

    public int updateShop(Shop shop) {
        ShopExample example=new ShopExample();
        return shopMapper.updateByExampleSelective(shop,example);
    }

    public int getTotal() {
        return day_infoMapper.getTotal();
    }

    public Day_info getDay(String dateString) {
        return day_infoMapper.selectByPrimaryKey(dateString);
    }

    public List<Day_info> getWeek() {
        return day_infoMapper.getweek();
    }
}
