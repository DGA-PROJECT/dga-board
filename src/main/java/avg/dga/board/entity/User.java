package avg.dga.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
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
  private Boolean isBanned;

  @Column(name = "is_admin")
  private Boolean isAdmin;

  @Column(name = "profile_image_link")
  private String profileImageLink;

  private String etc;

  @Column(name = "created_time")
  private Date createDate;

  @Builder
  public User(Long id, List<Board> board, String nickName, String email, String birthDate, String grade, Boolean isBanned, Boolean isAdmin, String profileImageLink, String etc, Date createDate ){
    this.id = id;
    this.board = board;
    this.nickName = nickName;
    this.email = email;
    this.birthDate = birthDate;
    this.grade = grade;
    this.isBanned = isBanned;
    this.isAdmin = isAdmin;
    this.profileImageLink = profileImageLink;
    this.etc = etc;
    this.createDate = createDate;
  }

}
