package avg.dga.board.repository;

import avg.dga.board.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.transaction.annotation.Transactional;


@Repository
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BoardJpaRepositoryTest {

  @Autowired BoardRepository boardRepository;

  @PersistenceContext
  private EntityManager em;

  @Test
  @Transactional
  @Rollback(value = false)
  public void testBoard() throws Exception {



    //given
    //User user = new User();
    //Board board = new Board(1L, "강원도 갔다왓어요","강원도 갔다온 내용" ,"넥넨겐게", user, Area.kangwon, 0, "안흥찐빵", TravelType.KID, DestiType.MARKET, 1, "34.123451","123.51231", false);
    //when
    //boardRepository.save(board);
    //then
  }
}