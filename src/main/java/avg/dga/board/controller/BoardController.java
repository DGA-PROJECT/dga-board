package avg.dga.board.controller;

import avg.dga.board.dto.ApiResponseMessage;
import avg.dga.board.dto.BoardRequest;
import avg.dga.board.service.AwsS3Service;
import avg.dga.board.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
  private final AwsS3Service awsS3Service;
  private  BoardService boardService;

  @Autowired
  public BoardController(AwsS3Service awsS3Service, BoardService boardService){
    this.awsS3Service = awsS3Service;
    this.boardService = boardService;
  }

  // 게시물 리스트 조회
  @GetMapping("/list")
  public String list(Model model) {
    List<BoardRequest> boardRequestList = boardService.getBoardList();
    model.addAttribute("boardList", boardRequestList);
    return "boards/list";
  }

  // 게시물 상세정보 조회
  @GetMapping("/{id}")
  public String detail(@PathVariable("id") Long id, Model model) {
    BoardRequest boardRequest = boardService.getBoard(id);
    model.addAttribute("board", boardRequest);
    return "boards/detail";
  }

  // 게시물 작성 페이지 호출
  @GetMapping ("/write")
  public String write() {
    return "boards/write";
  }

  //게시물 작성
  @PostMapping ("/write")
  public String write(BoardRequest boardRequest){


    boardService.saveBoard(boardRequest);

      return "redirect:list";
  }

  @RequestMapping(value = "/upload", method = RequestMethod.POST)
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



