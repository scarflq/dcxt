package dcxt.service;

import dcxt.dao.Day_infoMapper;
import dcxt.dao.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtherService {

    @Autowired
    ShopMapper shopMapper;
    @Autowired
    Day_infoMapper day_infoMapper;


}
