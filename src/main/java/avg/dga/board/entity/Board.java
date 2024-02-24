package avg.dga.board.entity;

import jakarta.persistence.*;

import lombok.*;

import org.springframework.util.Assert;

@NoArgsConstructor(access = AccessLevel.PUBLIC)  // 외부에서의 생성을 열어 둘 필요가 없을 때 PROTECT 가  / 보안적으로 권장된다.
@Getter
@Setter
@Entity
@Table(name = "board")
public class Board extends Time{

  @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
  @Column(name = "board_id")
  private Long id;

  @JoinColumn(name = "user_id", nullable = false)
  @ManyToOne
  private User user ;

  @Column(name="title", length = 100, nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String content;

  @Column(name = "area")
  private Area area;

  @Column(name = "desti_name")
  private String destiName;

  @Column(name = "travel_type")
  @Enumerated(value = EnumType.STRING)
  private TravelType travelType;

  @Column(name = "desti_type")
  @Enumerated(value = EnumType.STRING)
  private DestiType destiType;

  @Column(name = "like_count")
  private Integer likeCount;

  @Column(name = "revisit_count")
  private Integer revisitCount;

  @Column(name = "latitude")
  private String latitude;

  @Column(name = "longitude")
  private String longitude;

  @Column(name = "is_banned")
  private Boolean isBanned;

  @Builder
  public Board(Long id, String title, String content, User user, Area area, String destiName, TravelType travelType, DestiType destiType,
                Integer revisitCount, String latitude, String longitude, Integer likeCount, Boolean isBanned) {
    Assert.hasText(title, "title must not be empty");
    Assert.hasText(content, "content must not be empty");

    this.id = id;
    this.title = title;
    this.content = content;
    this.user = user;
    this.area = area;
    this.destiName = destiName;
    this.travelType = travelType;
    this.destiType = destiType;
    this.revisitCount = revisitCount;
    this.latitude = latitude;
    this.longitude = longitude;
    this.likeCount = likeCount;
    this.isBanned = isBanned;
  }

}
