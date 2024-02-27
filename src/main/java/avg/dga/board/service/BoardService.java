package avg.dga.board.service;

import avg.dga.board.dto.BoardRequest;
import avg.dga.board.entity.Board;
import avg.dga.board.entity.User;

import avg.dga.board.repository.BoardRepository;
import avg.dga.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardService {
  private final BoardRepository boardRepository;
  private final UserRepository userRepository;

  public void saveBoard(BoardRequest boardRequest){

    try{
      User user = User.builder()
          .id(boardRequest.getUserId())
          .build();
      //새로 생기는 글이므로 like 갯수 0
      boardRequest.setLikeCount(0);

      Board board = boardRequest.toEntity();
      board.setUser(user);
      board.setNickname(boardRequest.getNickname());
      // 똑같은 유저 아이디와 여행지명으로 갔다면 또간집임으로 두건 이상의 리스트가 나옴
      List<Board> boards = findBoardSameUserWithDestiName(board.getUser(), board.getDestiName());

      // then 또간 횟 수가 1 이상이면
      if(boards.size()>=1){
          List<Integer> RevisitCountList = new ArrayList<>();
          Integer maxRevisitCount;
          // 첫번째 조회한 revisit 조회수

          for (int i = 0; i < boards.size(); i++) {
            int current = boards.get(i).getRevisitCount();
            RevisitCountList.add(current);
          }
          // 재방문수 가장 큰 숫자를
          maxRevisitCount = Collections.max(RevisitCountList);
          // 가져와서 1 더해줌
          board.setRevisitCount( maxRevisitCount + 1);
      } else { // 첫방문이면 1 더해줌
        board.setRevisitCount(1);
      }
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
          .likeCount(board.getLikeCount())  // NullpointException 오류 뜰꺼임
          .nickname(board.getNickname())
          .revisitCount(board.getRevisitCount())
          .latitude(board.getLatitude())
          .longitude(board.getLongitude())
          .createdDate(board.getCreatedTime())
          .build();
      boardRequestList.add(boardRequest);
    }
    return boardRequestList;
  }

  //상세 페이지 조회시
  public BoardRequest getBoard(Long id) {
    Board board = boardRepository.findById(id);

    BoardRequest boardRequest = BoardRequest.builder()
        .id(board.getId())
        .user(board.getUser())
        .title(board.getTitle())
        .content(board.getContent())
        .destiName(board.getDestiName())
        .destiType(board.getDestiType())
        .travelType(board.getTravelType())
        .likeCount(board.getLikeCount())
        .revisitCount(board.getRevisitCount())
        .latitude(board.getLatitude())
        .longitude(board.getLongitude())
        .createdDate(board.getCreatedTime())
        .build();
    return boardRequest;
  }

  private  List<Board> findBoardSameUserWithDestiName(final User user, final String destiName){
    log.info("user =" + user + "destiName" + destiName);

    return boardRepository.findAllByUserAndDestiName(user, destiName);
  }

  //Entity -> Dto로 변환
  private BoardRequest convertEntityToDto(Board board){
    return BoardRequest.builder()
        .id(board.getId())
        .title(board.getTitle())
        .content(board.getContent())
        .createdDate(board.getCreatedTime())
        .modifiedDate(board.getModifiedTime())
        .build();
  }

}
