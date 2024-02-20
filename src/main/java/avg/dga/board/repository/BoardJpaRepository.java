package avg.dga.board.repository;

import avg.dga.board.entity.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class BoardJpaRepository {

  @PersistenceContext
  private EntityManager em;

  public Long save (Board board){
    em.persist(board);
    return board.getId();
  }


  public Board find (Long id){
    return em.find(Board.class, id);
  }
}
