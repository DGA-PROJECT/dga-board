package avg.dga.board.entity;

import jakarta.persistence.*;

import lombok.*;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 외부에서의 생성을 열어 둘 필요가 없을 때 PROTECT가  / 보안적으로 권장된다.
@Getter
@Setter
@Entity
@Table(name = "posts")
public class Board extends Time{

  @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
  @Column(name = "post_id")
  private Long id;

  @JoinColumn(name = "user_id", nullable = false)
  @ManyToOne(cascade = CascadeType.PERSIST)
  private User user ;

  @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
  private List<Like> like = new ArrayList<>();

  @Column(name="title", length = 100, nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String content;

  @Column
  private String nickname;

  @Column(name = "area")
  @Enumerated(value = EnumType.STRING)
  private Area area;

  @Column(name = "desti_name")
  private String destiName;

  @Column(name = "thumbnail_url")
  private String thumbnailUrl;

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
  @ColumnDefault("false")
  private Boolean isBanned = false;

  @Builder
  public Board(Long id, String title, String content, String nickname, User user, Area area, Integer likeCount,
               String destiName, TravelType travelType, DestiType destiType, String thumbnailUrl,
                Integer revisitCount, String latitude, String longitude, Boolean isBanned) {
    Assert.hasText(title, "title must not be empty");
    Assert.hasText(content, "content must not be empty");

    this.id = id;
    this.title = title;
    this.content = content;
    this.nickname = nickname;
    this.user = user;
    this.area = area;
    this.likeCount = likeCount;
    this.destiName = destiName;
    this.thumbnailUrl = thumbnailUrl;
    this.travelType = travelType;
    this.destiType = destiType;
    this.revisitCount = revisitCount;
    this.latitude = latitude;
    this.longitude = longitude;
    this.isBanned = isBanned;
  }

}
