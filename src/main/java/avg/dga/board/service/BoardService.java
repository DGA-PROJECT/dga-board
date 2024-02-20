package avg.dga.board.service;

import avg.dga.board.dto.BoardRequest;
import avg.dga.board.entity.Board;
import avg.dga.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardService {
  private BoardRepository boardRepository;

  //Entity -> Dto로 변환
  private BoardRequest convertEntityToDto(Board board){
    return BoardRequest.builder()
        .id(board.getId())
        .title(board.getTitle())
        .content(board.getContent())
        .createdDate(board.getCreatedDate())
        .modifiedDate(board.getModifiedDate())
        .build();
  }

  @Transactional
  public Long saveBoard(BoardRequest boardRequest) {
    return boardRepository.save(boardRequest.toEntity()).getId();
  }
}
