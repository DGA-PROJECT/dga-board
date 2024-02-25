package avg.dga.board.controller;

import avg.dga.board.dto.BoardRequest;
import avg.dga.board.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
  private  BoardService boardService;

  @Autowired
  public BoardController(BoardService boardService){
    this.boardService = boardService;
  }

  // 게시물 리스트 조회
  @GetMapping("/list")
  public String list(Model model) {
    List<BoardRequest> boardRequestList = boardService.getBoardList();
    model.addAttribute("boardList", boardRequestList);
    System.out.println("생성시간" + boardRequestList.getFirst().getCreateDate());
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
    return "/boards/write";
  }

  //게시물 작성
  @PostMapping ("/write")
  public String write(BoardRequest boardRequest){

    boardService.saveBoard(boardRequest);

      return "redirect:/boards/list";
  }



}



