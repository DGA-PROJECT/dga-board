package avg.dga.board.controller;



import avg.dga.board.dto.BoardRequest;
import avg.dga.board.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class BoardController {

  private BoardService boardService;

  @GetMapping ("/write")
  public String write() {
    return "/post/write";
  }

  @PostMapping ("/write")
  public String write(BoardRequest boardRequest) {
    System.out.println("boardRequest = " + boardRequest);
    boardRequest.setId(1L);
    System.out.println("Id = " + boardRequest.getId());

    boardService.saveBoard(boardRequest);
    return "redirect:/post/list";
  }

}



