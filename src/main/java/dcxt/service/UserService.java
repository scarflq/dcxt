package dcxt.service;

import dcxt.dao.User_infoMapper;
import dcxt.dao.User_loginMapper;
import dcxt.pojo.User_info;
import dcxt.pojo.User_login;
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

    public User_login login(String uname) {
        return user_loginMapper.selectByPrimaryKey(uname);
    }

    public User_info getStates(String username) {
        return user_infoMapper.selectByPrimaryKey(username);
    }

    public int saveul(User_login ul) {
        return user_loginMapper.insertSelective(ul);
    }

    public int saveui(User_info ui) {
        return user_infoMapper.insertSelective(ui);
    }

    public void update2(User_login ul) {
        user_loginMapper.updateByPrimaryKeySelective(ul);
    }
}
