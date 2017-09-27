package dcxt.service_impl;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import dcxt.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by 759517209@qq.com on 2017/9/11.
 */
@Service("UploadService")
public class UploadServiceImpl implements UploadService {

    public String uploadToLocal(MultipartFile file, String realPath, String fileName) {
        File f = new File(realPath, fileName);
        if (!f.exists()) {
            f.mkdirs();
        }

        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f.getAbsolutePath();
    }

    public String uploadToQINiu(String localFilePath,String fileName) {

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone1());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "HT5y_GhXDYZXNGA6AXbWJFdrRH0N8VIqXyfQ7BZE";
        String secretKey = "yzd3vnNkWa1qQ-cZnJKL3J45wq6DClh5VO2ueBlD";
        String bucket = "lcxmb";

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }
}
