package avg.dga.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "likes")
public class Like {

  @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
  @Column(name = "like_id")
  private Long id;

  @JoinColumn(name = "user_id")
  @ManyToOne
  private User user;

  @JoinColumn(name = "board_id")
  @ManyToOne
  private Board board;

  @Builder
  public Like (Long id, User user, Board board) {
    Like.builder()
        .id(id)
        .user(user)
        .board(board)
        .build();
  }
}
