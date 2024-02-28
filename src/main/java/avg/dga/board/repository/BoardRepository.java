package avg.dga.board.repository;

import avg.dga.board.entity.Board;
import avg.dga.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, User> {

  @Query(value = "select b from Board  b " +
      "where b.user = :user " +
      "and b.destiName like :destiName" )
  List<Board> findAllByUserAndDestiName(@Param("user") User user, @Param("destiName") String destiName);

  @Modifying
  @Query(value = "update Board b set b.likeCount = b.likeCount + 1 where b.id = :boardId")
  void plusLike(@Param("boardId")Long boardId);

  @Modifying
  @Query(value = "update Board b set b.likeCount = b.likeCount - 1 where b.id = :boardId")
  void minusLike(@Param("boardId")Long boardId);

  Board findById(Long id);
}
