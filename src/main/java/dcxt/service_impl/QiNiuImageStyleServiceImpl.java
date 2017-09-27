package dcxt.service_impl;


import dcxt.service.QiNiuImageStyleService;
import org.springframework.stereotype.Service;

/**
 * Created by 759517209@qq.com on 2017/9/11.
 */
@Service("QiNiuImageStyleService")
public class QiNiuImageStyleServiceImpl implements QiNiuImageStyleService {
    private String url = "http://ow408mv5t.bkt.clouddn.com";
    private String style;

    public String getStyle(String width){
        style = "imageView2/1/w/" + width + "/h/" + width + "/q/75|imageslim";
        return style;
    }

    public String getImageUrl(String width, String QiNiuKey){
        return url + "/" + QiNiuKey + "?" + getStyle("400");
    }
}
