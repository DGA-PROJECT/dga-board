package avg.dga.board.entity;

import jakarta.persistence.*;

import lombok.*;

import org.springframework.util.Assert;

@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 외부에서의 생성을 열어 둘 필요가 없을 때 / 보안적으로 권장된다.
@Getter
@Entity(name = "board")
public class Board extends Time{

  @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
  @Column(name = "board_id")
  private Long id;

  @JoinColumn(name = "user_id", nullable = false)
  @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
  private User user ;

  @Column(name="title",length = 100, nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String content;

  @Column(name = "area", nullable = false)
  private String area;

  @Column(name = "travel_type", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private TravelType travelType;

  @Column(name = "desti_type", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private DestiType destiType;

  @Column(name = "revisit_count")
  private Integer revisitCount;

  @Column(name = "latitude")
  private String latitude;

  @Column(name = "longitude")
  private String longitude;

  @Column(name = "like_count")
  private Integer likeCount;

  @Column(name = "is_banned")
  private Boolean isBanned;

  @Builder
  public Board(Long id, String title, String content, User user, String area, TravelType travelType, DestiType destiType,
                Integer revisitCount, String latitude, String longitude, Integer likeCount, Boolean isBanned) {
    Assert.hasText(title, "title must not be empty");
    Assert.hasText(content, "content must not be empty");

    this.id = id;
    this.title = title;
    this.content = content;
    this.user = user;
    this.area = area;
    this.travelType = travelType;
    this.destiType = destiType;
    this.revisitCount = revisitCount;
    this.latitude = latitude;
    this.longitude = longitude;
    this.likeCount = likeCount;
    this.isBanned = isBanned;
  }

  public Board(long ld, User user, String title, String area, TravelType travelType, DestiType destiType, String travelTime, int i2, int i3, int i4, boolean b) {
  }

  public Board(long id, String title, String content){
    
  }
}
