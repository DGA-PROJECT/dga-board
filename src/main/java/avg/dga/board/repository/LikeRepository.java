package avg.dga.board.repository;

import avg.dga.board.entity.Board;
import avg.dga.board.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
  Optional<Like> findByBoard_IdAndUser_Id(Long boardId, Long memberId);

  void  deleteByBoard_IdAndUser_Id(Long boardId, Long memberId);

}
