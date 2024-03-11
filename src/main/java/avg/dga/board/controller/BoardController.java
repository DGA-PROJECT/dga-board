package avg.dga.board.controller;

import avg.dga.board.dto.ApiResponseMessage;
import avg.dga.board.dto.BoardRequest;
import avg.dga.board.service.AwsS3Service;
import avg.dga.board.service.BoardService;
import avg.dga.board.service.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
  private BoardService boardService;
  private LikeService likeService;

  @Autowired
  public BoardController(AwsS3Service awsS3Service, BoardService boardService, LikeService likeService){
    this.awsS3Service = awsS3Service;
    this.boardService = boardService;
    this.likeService = likeService;
  }

  // 페이징 처리로 받기
  @GetMapping("/")
  public String paging(@PageableDefault(page = 1)Pageable pageable, Model model) {
    System.out.println("page = " + pageable.getPageNumber());
    Page<BoardRequest> boardRequests = boardService.paging(pageable);
    for (BoardRequest boardRequest : boardRequests) {
      System.out.println("boardRequest = " + boardRequest);
    }

    model.addAttribute("boardList", boardRequests);
    // 시작페이지(startPage), 마지막페이지(endPage) 값 계산
    // 하단에 보여줄 페이지 갯수 3개
    int blockLimit = 3;
    int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
    int endPage = ((startPage + blockLimit - 1) < boardRequests.getTotalPages()) ? startPage + blockLimit - 1 : boardRequests.getTotalPages();

    model.addAttribute("startPage",startPage);
    model.addAttribute("endPage",endPage);

    return "boards/boardPaging";
  }

  @GetMapping("/page")
  public String page(@PageableDefault(page = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model){
    System.out.println("page = " + pageable.getPageNumber());
    Page<BoardRequest> boardRequests = boardService.paging(pageable);

    // 시작페이지(startPage), 마지막페이지(endPage) 값 계산
    // 하단에 보여줄 페이지 갯수 3개
    int blockLimit = 3;
    int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
    int endPage = Math.min((startPage + blockLimit - 1), boardRequests.getTotalPages());

    model.addAttribute("boardList", boardRequests);
    model.addAttribute("startPage",startPage);
    model.addAttribute("endPage",endPage);

    return "boards/paging";

  }

  // 게시물  전체 리스트 조회
  @GetMapping("/list")
  public String list(Model model) {
    List<BoardRequest> boardRequestList = boardService.getBoardList();
    model.addAttribute("boardList", boardRequestList);
    return "boards/list";
  }



  // 게시물 상세정보 조회
  @GetMapping("/{id}")
  public String detail(@PathVariable("id") Long id, Model model) {
    //조회 하기 위해 id 값 가져오기
    BoardRequest boardRequest = boardService.getBoard(id);
    int like = likeService.findLike(boardRequest.getId(), boardRequest.getUserId());

    //변경 된 값을 가져오기 
    boardRequest = boardService.getBoard(id);

    System.out.println("boardRequest.getLikeCount() = " + boardRequest.getLikeCount());
    
    model.addAttribute("board", boardRequest);
    model.addAttribute("likeCount", boardRequest.getLikeCount());
    model.addAttribute("like", like);
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
      return "redirect:/boards/paging";
  }

  @PostMapping("/like")
  public @ResponseBody int like(Long boardId, Long userId){
    return likeService.saveLike(boardId, userId);
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



