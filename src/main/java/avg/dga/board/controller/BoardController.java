package avg.dga.board.controller;

import avg.dga.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

  @Autowired
  BoardService boardService;

  @RequestMapping("/write")
  public String board(Model model) {
    model.addAttribute("data","sdfasdfsdaf");
    return "/board/write";
  }
}



