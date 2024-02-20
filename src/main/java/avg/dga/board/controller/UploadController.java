package avg.dga.board.controller;

import avg.dga.board.dto.ApiResponseMessage;
import avg.dga.board.service.AwsS3Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor

public class UploadController {

  private final AwsS3Service awsS3Service;


  @RequestMapping(value = "post/uploadSummernoteImageFile", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<ApiResponseMessage> upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    // JSON 객체 생성
    String imgUrl = awsS3Service.uploadFile(multipartFile);

    if(imgUrl.isEmpty()){
      ApiResponseMessage message = new ApiResponseMessage("Fail", "Upload Fail", "", "");
      return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.BAD_REQUEST);
    }
    ApiResponseMessage message = new ApiResponseMessage("Success", imgUrl, "", "");
    System.out.println("message = " + message)
    ;
    return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
  }


}
