package dcxt.service;

import java.util.List;

import dcxt.dao.Order_Mapper;
import dcxt.pojo.Order_;
import dcxt.pojo.Order_Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Order_Service {

    @Autowired
    Order_Mapper order_mapper;

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
}
