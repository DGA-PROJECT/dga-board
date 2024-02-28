package avg.dga.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "likes")
public class Like {

  @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
  @Column(name = "like_id")
  private Long id;

  @JoinColumn(name = "user_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @JoinColumn(name = "board_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private Board board;

  public static Like toLikeEntity(User user, Board board){
    Like like = new Like();
    like.setBoard(board);
    like.setUser(user);

    return like;
  }
}
