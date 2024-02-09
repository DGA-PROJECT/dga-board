package avg.dga.user.entity;

import avg.dga.board.entity.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity(name = "tb_user")
public class User {

  @Id @GeneratedValue (strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private  Long id;

  @OneToMany(mappedBy = "user")
  private List<Board> boards = new ArrayList<>();

  @Column(name = "nick_name", nullable = false)
  private String nickName;

  @Column(updatable = false, nullable = false)
  private String email;

  @Column(name = "birthday", nullable = true)
  private LocalDate birthDate;

  @Column(name = "grade", nullable = false)
  private String grade;


  @Column(name = "created_date", unique = true)
  @CreatedDate
  private LocalDate createdDate;

  @Column(name = "is_banned",  nullable = false)
  private Boolean isBanned;

  @Column(name = "is_admin",  nullable = false)
  private Boolean isAdmin;

  @Column(name = "profile_img_link",  nullable = true)
  private String imageLink;

  @Column(columnDefinition="TEXT")
  private String etc;

  public User(Long id, String nickName, String email, LocalDate birthDate, String grade, LocalDate createdDate, Boolean isBanned, Boolean isAdmin, String imageLink, String etc){
    this.id = id;
    this.nickName = nickName;
    this.email = email;
    this.birthDate = birthDate;
    this.grade = grade;
    this.createdDate = createdDate;
    this.isBanned = isBanned;
    this.isAdmin = isAdmin;
    this.imageLink = imageLink;
    this.etc = etc;
  }


  public User() {

  }

  public User(String nickName) {
    this.nickName = nickName;
  }
}
