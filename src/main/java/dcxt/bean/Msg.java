package dcxt.bean;

import java.util.HashMap;
import java.util.Map;


public class Msg {

    //状态码
    private int code;
    //提示信息
    private String msg;

    private Map<String, Object> data = new HashMap<String, Object>();

    public static Msg success(String msg){
        Msg result = new Msg();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }

    public static Msg fail(String msg){
        Msg result = new Msg();
        result.setCode(1);
        result.setMsg(msg);
        return result;
    }

    public Msg add(String key,Object value){
        this.getdata().put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getdata() {
        return data;
    }

    public void setdata(Map<String, Object> data) {
        this.data = data;
    }
}
