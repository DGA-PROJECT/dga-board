package avg.dga.board.service;

import avg.dga.board.entity.Board;
import avg.dga.board.entity.Like;
import avg.dga.board.entity.User;
import avg.dga.board.repository.BoardRepository;
import avg.dga.board.repository.LikeRepository;
import avg.dga.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class LikeService {
  private final LikeRepository likeRepository;
  private final UserRepository userRepository;
  private final BoardRepository boardRepository;


  public int findLike(Long boardId, Long userId){

    Optional<Like> findLike = likeRepository.findByBoard_IdAndUser_Id(boardId, userId);
    if(findLike.isEmpty()){
      return 0;
    } else {
      return 1;
    }
  }
  @Transactional
  public int saveLike(Long boardId, Long userId){
    Optional<Like> findLike = likeRepository.findByBoard_IdAndUser_Id(boardId, userId);

    if (findLike.isEmpty()){

      User user = userRepository.findById(userId).get();
      Board board = boardRepository.findById(boardId);

      Like like = Like.toLikeEntity(user, board);

      likeRepository.save(like);
      boardRepository.plusLike(boardId);
      return 1;
    } else {

      likeRepository.deleteByBoard_IdAndUser_Id(boardId, userId);
      boardRepository.minusLike(boardId);
      return 0;
    }
  }

}
