package avg.dga.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@Entity(name = "users")
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
  @Column(name = "user_id")
  private Long id;

  @Column(name = "nick_name")
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
