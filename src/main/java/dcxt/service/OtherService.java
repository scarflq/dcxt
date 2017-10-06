package dcxt.service;

import dcxt.dao.Day_infoMapper;
import dcxt.dao.ShopMapper;
import dcxt.pojo.Shop;
import dcxt.pojo.ShopExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
