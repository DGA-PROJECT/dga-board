package avg.dga.board.service;

import avg.dga.board.entity.Board;
import avg.dga.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardService {
  private final BoardRepository boardRepository;

}
