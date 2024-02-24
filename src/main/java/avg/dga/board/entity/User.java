package avg.dga.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
  @Column(name = "user_id")
  private Long id;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Board> board = new ArrayList<>();;

  @Column(name = "nickname")
  private String nickName;

  private String email;

  @Column(name = "birth_date")
  private String birthDate;
  private String grade;

  @Column(name = "is_banned")
  private Integer isBanned;

  @Column(name = "is_admin")
  private Integer isAdmin;

  @Column(name = "profile_image_link")
  private String profileImageLink;

  private String etc;

  @Column(name = "created_time")
  private Date createDate;
}
