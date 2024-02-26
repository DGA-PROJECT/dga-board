package avg.dga.board.dto;

import avg.dga.board.entity.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
  private Long id;
  private String nickName;
  private String email;
  private String birthDate;
  private String grade;
  private boolean isBanned;
  private boolean isAdmin;
  private String profileImageLink;
  private String etc ;
  private Date createDate;


  public User toEntity(){
    return User.builder()
        .id(id)
        .nickName(nickName)
        .email(email)
        .birthDate(birthDate)
        .grade(grade)
        .isBanned(isBanned)
        .isAdmin(isAdmin)
        .profileImageLink(profileImageLink)
        .etc(etc)
        .createDate(createDate)
        .build();
  }

  @Builder
  public UserDto(Long id, String nickName, String email, String birthDate, String grade, Boolean isBanned, Boolean isAdmin, String profileImageLink, String etc, Date createDate) {
    this.id = id;
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
