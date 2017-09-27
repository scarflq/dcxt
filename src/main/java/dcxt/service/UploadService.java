package dcxt.service;


import org.springframework.web.multipart.MultipartFile;


public interface UploadService {
    public String uploadToLocal(MultipartFile file, String path, String fileName);
    public String uploadToQINiu(String localFilePath, String fileName);
}