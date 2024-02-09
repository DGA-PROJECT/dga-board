package avg.dga;

import avg.dga.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

  @Autowired
  BoardService boardService;

  @GetMapping("hello")
  public String hello(Model model) {
    model.addAttribute("data","sdfasdfsdaf");
    return "hello";
  }
}
