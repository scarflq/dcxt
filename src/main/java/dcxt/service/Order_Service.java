package dcxt.service;

import java.util.List;

import dcxt.dao.Day_infoMapper;
import dcxt.dao.Order_Mapper;
import dcxt.pojo.Day_info;
import dcxt.pojo.Order_;
import dcxt.pojo.Order_Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Order_Service {

    @Autowired
    Order_Mapper order_mapper;
    @Autowired
    Day_infoMapper day_infoMapper;

    public List<Order_> getAll() {
        // TODO Auto-generated method stub
        return order_mapper.selectByExample(null);
    }

    public void changeStatus(Order_ o) {
        // TODO Auto-generated method stub
        order_mapper.updateByPrimaryKeySelective(o);
    }

    public int xiadan(Order_ o) {
        return order_mapper.insertSelective(o);
    }

    public int comment(Order_ o) {
        return order_mapper.updateByPrimaryKeySelective(o);
    }

    public List<Order_> getAllf(String u_id) {
        return order_mapper.selectByUid(u_id);
    }

    public List<Order_> getByStatus(Integer status) {
        return order_mapper.selectByStatus(status);
    }

    public List<Order_> getByStatusf(Order_ o1) {
        return order_mapper.selectByUAS(o1);
    }

    public int add_day_info(String a, int order_num, int product_num, int total) {
        Day_info day_info=day_infoMapper.selectByPrimaryKey(a);
        if(day_info!=null){
            int o=day_info.getoTotal()+order_num;
            int p=day_info.getpTotal()+product_num;
            int t=day_info.getPriceTotal()+total;
            day_info.setoTotal(o);
            day_info.setpTotal(p);
            day_info.setPriceTotal(t);
            day_infoMapper.updateByPrimaryKeySelective(day_info);
        }
        else {
            Day_info today=new Day_info();
            today.setDate(a);
            today.setoTotal(order_num);
            today.setpTotal(product_num);
            today.setPriceTotal(total);
            day_infoMapper.insertSelective(today);
        }
        return 0;
    }
}
