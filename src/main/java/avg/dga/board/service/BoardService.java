package avg.dga.board.service;

import avg.dga.board.dto.BoardRequest;
import avg.dga.board.entity.Board;
import avg.dga.board.entity.User;
import avg.dga.board.repository.BoardRepository;
import avg.dga.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

      // Thumnail 이미지 가져오기
      String content = board.getContent();
      String thumnailUrl = getImageUrl(content);

      log.info("thumnailUrl = " + thumnailUrl);

      board.setThumbnailUrl(thumnailUrl);

      board.setUser(user);
      board.setNickname(boardRequest.getNickname());
      // 똑같은 유저 아이디와 여행지명으로 갔다면 또간집임으로 두건 이상의 리스트가 나옴
      List<Board> boards = findBoardSameUserWithDestiName(board.getUser(), board.getDestiName());

      // then 또간 횟 수가 1 이상이면
      if(!boards.isEmpty()){
          List<Integer> RevisitCountList = new ArrayList<>();
          Integer maxRevisitCount;
          // 첫번째 조회한 revisit 조회수

        for (Board value : boards) {
          int current = value.getRevisitCount();
          RevisitCountList.add(current);
        }
          // 재방문수 가장 큰 숫자를
          maxRevisitCount = Collections.max(RevisitCountList);
          // 가져와서 1 더해줌
          board.setRevisitCount( maxRevisitCount + 1);
      } else { // 첫방문이면 1 더해줌
        board.setRevisitCount(1);
      }
      board.setIsBanned(false);
      boardRepository.save(board);

    }catch(Exception e){
      log.error( e.getMessage());
    }
  }

  // 게시글 페이징 처리된 화면 서비스
  public Page<BoardRequest> paging(Pageable pageable){
    int page = pageable.getPageNumber() - 1 ;
    int pageLimit= 7;
    Page<Board> boards =
        boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC,"id")));
    Page<BoardRequest> boardRequests = boards.map(board -> BoardRequest.builder()
        .id(board.getId())
        .user(board.getUser())
        .userId(board.getUser().getId()) // 유저 아이디 가져오기
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
        .build());
    return boardRequests;
  }


  //리스트 페이지 조회시 서비스
  public List<BoardRequest> getBoardList() {
    List<Board> boardList = boardRepository.findAll();
    List<BoardRequest> boardRequestList = new ArrayList<>();

    for(Board board : boardList) {
      BoardRequest boardRequest = BoardRequest.builder()
          .id(board.getId())
          .user(board.getUser())
          .userId(board.getUser().getId()) // 유저 아이디 가져오기
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

    return BoardRequest.builder()
        .id(board.getId())
        .user(board.getUser())
        .userId(board.getUser().getId())
        .title(board.getTitle())
        .content(board.getContent())
        .nickname(board.getNickname())
        .destiName(board.getDestiName())
        .destiType(board.getDestiType())
        .travelType(board.getTravelType())
        .likeCount(board.getLikeCount())
        .revisitCount(board.getRevisitCount())
        .latitude(board.getLatitude())
        .longitude(board.getLongitude())
        .createdDate(board.getCreatedTime())
        .build();
  }

  // 게시글 삭제
//  public void deleteBoard (Long boardId){
//    boardRepository.deleteById(boardId);
//  }

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

  // 게시글 content 에서 첫 img 태그의 url를 썸네일 url로 추출
  public static String getImageUrl(String content) {
    // 정규 표현식을 사용하여 img 태그 추출
    Matcher matcher = Pattern.compile("<img\\s+[^>]*?src\\s*=\s*['\"]([^'\"]+)['\"][^>]*?>").matcher(content);

    // img 태그가 없는 경우
    if (!matcher.find()) {
      return "https://dgaui.s3.ap-northeast-2.amazonaws.com/noThumbImg.webp";
    }

    // img 태그의 src 속성 값 추출
    String url = matcher.group(1);

    // URL 앞뒤 공백 제거
    return url.trim();
  }

}
