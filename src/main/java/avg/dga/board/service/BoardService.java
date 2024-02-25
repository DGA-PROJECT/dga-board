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

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardService {
  private final BoardRepository boardRepository;
  private final UserRepository userRepository;

  public void saveBoard(BoardRequest boardRequest){

    try{
      // 테스트 강제로 user 1 조회 해서 엔티티 영속성 부여
      System.out.println("+++++++++++++++++++++++++++++++");
      User user =  new User();;
      //userRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: "));
      user.setId(1L);

      System.out.println("user = " + user);
      System.out.println("user = " + user.getId());


      Board board = Board.builder()
          .user(user)
          .title(boardRequest.getTitle())
          .content(boardRequest.getContent())
          .area(boardRequest.getArea())
          .destiName(boardRequest.getDestiName())
          .travelType(boardRequest.getTravelType())
          .revisitCount(boardRequest.getRevisitCount())
          .destiType(boardRequest.getDestiType())
          .revisitCount(boardRequest.getRevisitCount())
          .latitude(boardRequest.getLatitude())
          .longitude(boardRequest.getLongitude())
          .build();

      System.out.println("board.toString() = " + board.toString());

      boardRepository.save(board);

    }catch(Exception e){
      log.error( e.getMessage());
    }
  }
  //리스트 페이지 조회시 서비스
  public List<BoardRequest> getBoardList() {
    List<Board> boardList = boardRepository.findAll();
    List<BoardRequest> boardRequestList = new ArrayList<>();

    for(Board board : boardList) {
      BoardRequest boardRequest = BoardRequest.builder()
          .id(board.getId())
          .user(board.getUser())
          .title(board.getTitle())
          .content(board.getContent())
          .destiName(board.getDestiName())
          .destiType(board.getDestiType())
          .travelType(board.getTravelType())
//          .likeCount(board.getLikeCount())  // NullpointException 오류 뜰꺼임
          .revisitCount(board.getRevisitCount())
          .latitude(board.getLatitude())
          .longitude(board.getLongitude())
          .createdDate(board.getCreatedDate())
          .build();
      boardRequestList.add(boardRequest);
    }
    return boardRequestList;
  }

  public BoardRequest getBoard(Long id) {
    Board board = boardRepository.findById(id).get();

    BoardRequest boardRequest = BoardRequest.builder()
        .id(board.getId())
        .user(board.getUser())
        .title(board.getTitle())
        .content(board.getContent())
        .destiName(board.getDestiName())
        .destiType(board.getDestiType())
        .travelType(board.getTravelType())
//          .likeCount(board.getLikeCount())  // NullpointException 오류 뜰꺼임
        .revisitCount(board.getRevisitCount())
        .latitude(board.getLatitude())
        .longitude(board.getLongitude())
        .createdDate(board.getCreatedDate())
        .build();
    return boardRequest;
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
