package avg.dga.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
@Service
public class AwsS3Service {
  private final S3Client s3Client;

  @Value("${cloud.aws.s3.bucket}")
  private String bucketName;

  public String uploadFile(MultipartFile multipartFile)  {

    String fileName =  multipartFile.getOriginalFilename();

    try {
      PutObjectRequest putObjectRequest = PutObjectRequest.builder()
          .bucket(bucketName)
          .contentType(multipartFile.getContentType())
          .contentLength(multipartFile.getSize())
          .key(fileName)
          .build();
      RequestBody requestBody = RequestBody.fromBytes(multipartFile.getBytes());
      System.out.println("===================================");
      System.out.println("requestBody = " + requestBody);
      s3Client.putObject(putObjectRequest, requestBody);
    } catch (IOException e) {
      log.error("cannot upload image",e);
      throw new RuntimeException(e);
    }
    GetUrlRequest getUrlRequest = GetUrlRequest.builder()
        .bucket(bucketName)
        .key(fileName)
        .build();

    return s3Client.utilities().getUrl(getUrlRequest).toString();
  }
}