package avg.dga.board.repository;

import avg.dga.board.entity.Board;
import avg.dga.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, User> {

  @Query(value = "select b from Board  b " +
      "where b.user = :user " +
      "and b.destiName like :destiName" )
  List<Board> findAllByUserAndDestiName(@Param("user") User user, @Param("destiName") String destiName);

  Board findById(Long id);

}
