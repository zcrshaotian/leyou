package ly.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import ly.Exception.LyException;
import ly.config.UploadProperties;
import ly.enums.ExceptionEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadService {

    //定义可上传文件类型
//    private static final List<String> ALLOW_TYPE = Arrays.asList("image/png", "image/jpeg", "image/jpg");

    //注入FastFileStorageClient对象
    @Autowired
    private FastFileStorageClient storageClient;

    //注入UploadProperties对象,用来获取配置文件中配置好的文件类型，和域名
    @Autowired
    private UploadProperties prop;



    public String uploadImage(MultipartFile file) {
        try {
            //校验文件后缀
            if (!prop.getAllowTypes().contains(file.getContentType())){
                throw new LyException(ExceptionEnum.Invalid_File_Type);
            }

            //校验文件内容  如果内容不是为图片则会返回空
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null){
                throw new LyException(ExceptionEnum.Invalid_File_Type);
            }


//            //准备目标路径
//            File dest = new File("F:\\pictures", file.getOriginalFilename());
//            //将文件存储到本地
//            file.transferTo(dest);
//            return "http://image.leyou.com/" + file.getOriginalFilename();


            //存储到FastFDS
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            System.out.println("http://image.leyou.com/" + storePath.getFullPath());
            return prop.getBaseUrl() + storePath.getFullPath();
        } catch (IOException e) {
            //打印错误信息
            log.error("上传文件失败", e);
            //失败返回结果信息
            throw new LyException(ExceptionEnum.Upload_File_Error);
        }

    }
}
