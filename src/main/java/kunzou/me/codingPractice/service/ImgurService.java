package kunzou.me.codingPractice.service;

import kunzou.me.codingPractice.dto.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImgurService {
  Image uploadImage(MultipartFile file) throws Exception;
}
