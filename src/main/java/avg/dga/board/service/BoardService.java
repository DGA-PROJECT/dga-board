package avg.dga.board.service;

import avg.dga.board.dto.BoardRequest;
import avg.dga.board.entity.Board;
import avg.dga.board.entity.User;

import avg.dga.board.repository.BoardRepository;
import avg.dga.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardService {
  private final BoardRepository boardRepository;
  private final UserRepository userRepository;

  public void saveBoard(BoardRequest boardRequest){

    try{
      // 테스트 강제로 user 1 조회 해서 엔티티 영속성 부여
      User user =  new User();;
      //userRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: "));
      user.setId(1L);
      Board board = Board.builder()
          .user(user)
          .title(boardRequest.getTitle())
          .content(boardRequest.getContent())
          .area(boardRequest.getArea())
          .destiName(boardRequest.getDestiName())
          .travelType(boardRequest.getTravelType())
          .revisitCount(1)
          .destiType(boardRequest.getDestiType())
          .revisitCount(boardRequest.getRevisitCount())
          .latitude(boardRequest.getLatitude())
          .longitude(boardRequest.getLongitude())
          .build();

      boardRepository.save(board);

    }catch(Exception e){
      log.error( e.getMessage());
    }
  }

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

}
