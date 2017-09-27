package dcxt.service;

import java.util.List;

import dcxt.dao.Order_Mapper;
import dcxt.pojo.Order_;
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
}
