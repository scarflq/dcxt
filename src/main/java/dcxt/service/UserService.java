package dcxt.service;

import dcxt.dao.User_infoMapper;
import dcxt.dao.User_loginMapper;
import dcxt.pojo.User_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    User_infoMapper user_infoMapper;
    @Autowired
    User_loginMapper user_loginMapper;


    public List<User_info> getAll() {
        // TODO Auto-generated method stub
        return user_infoMapper.selectAll(null);
    }

    public int delete(User_info u) {
        return user_infoMapper.updateByPrimaryKeySelective(u);
    }


    public List<User_info> search(String title1) {
        return user_infoMapper.selectByExample(title1);
    }

    public List<User_info> money() {
        return user_infoMapper.selectMoney(null);
    }

    public void update(User_info u) {
        user_infoMapper.updateByPrimaryKeySelective(u);
    }

    public User_info getU(String username) {
        return user_infoMapper.selectByPrimaryKey(username);
    }
}
