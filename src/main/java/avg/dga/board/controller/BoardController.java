package avg.dga.board.controller;

import avg.dga.board.dto.BoardRequest;
import avg.dga.board.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
  private  BoardService boardService;

  @Autowired
  public BoardController(BoardService boardService){
    this.boardService = boardService;
  }

  @GetMapping ("/write")
  public String write() {
    return "/boards/write";
  }

  @PostMapping ("/write")
  public String write(BoardRequest boardRequest){

    System.out.println("boardRequest = " + boardRequest);
    System.out.println("Id = " + boardRequest.getId());
    System.out.println("area = " + boardRequest.getArea());

    boardService.saveBoard(boardRequest);

      return "redirect:/";
  }

}



