package avg.dga.board.controller;



import avg.dga.board.dto.BoardRequest;
import avg.dga.board.entity.User;
import avg.dga.board.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.core.Response;


@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
  private  BoardService boardService;

  @Autowired
  public BoardController(BoardService boardService){
    this.boardService = boardService;
  }

  @GetMapping ("/write")
  public String write() {
    return "/board/write";
  }

  @PostMapping ("/write")
  public String write(BoardRequest boardRequest) {

    User user = new User();
    boardRequest.setUser(user);
    boardRequest.setId(1L);
    System.out.println("boardRequest = " + boardRequest);
    System.out.println("Id = " + boardRequest.getId());

    boolean result = false;
    try {
      result = boardService.saveBoard(boardRequest);
      System.out.println("result = " + result);
    } catch (Exception e){
      System.out.println("result = " + result);
      e.printStackTrace();
    }

    if(result){
      System.out.println("성공");
      return "redirect:/";

    } else {
      System.out.println("실패");
      return "redirect:/";
    }


  }

}



