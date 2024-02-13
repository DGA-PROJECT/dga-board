package avg.dga.board.entity;

import jakarta.persistence.*;

import lombok.Getter;

import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "tb_board")
public class Board {

  @Id @GeneratedValue (strategy = GenerationType.AUTO)
  @Column(name = "board_id")
  private Long id;

  @JoinColumn(name = "user_id")
  @ManyToOne(cascade = CascadeType.MERGE)
  private DestiType.User user ;

  @Column(name="post_title")
  private String title;

  @Column(name = "meta_img_link")
  private String imgLink;

  @Column(name = "province", nullable = false)
  private String province;

  @Column(name = "travel_type", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private TravelType travelType;

  @Column(name = "desti_type", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private DestiType destiType;

  @Column(name = "travel_time")
  private String travelTime;

  @Column(name = "travel_duration")
  private Integer travelDuration;

  @Column(name = "revisit_count")
  private Integer revisitCount;

  @Column(name = "latitude")
  private Integer latitude;

  @Column(name = "longitude")
  private Integer longitude;

  @Column(name = "like_count")
  private Integer likeCount;

  @Column(name = "is_banned")
  private Boolean isBanned;

  @Column(name = "created_date", updatable = false)
  @CreatedDate
  private LocalDateTime createdDate;


  public Board(Long id, DestiType.User user, String imgLink, String province, TravelType travelType, DestiType destiType, String travelTime,
               Integer travelDuration, Integer revisitCount, Integer latitude, Integer longitude, Integer likeCount, Boolean isBanned, LocalDateTime createdDate) {
    this.id = id;
    this.user = user;
    this.imgLink = imgLink;
    this.province = province;
    this.travelType = travelType;
    this.destiType = destiType;
    this.travelTime = travelTime;
    this.travelDuration = travelDuration;
    this.revisitCount = revisitCount;
    this.latitude = latitude;
    this.longitude = longitude;
    this.likeCount = likeCount;
    this.isBanned = isBanned;
    this.createdDate = createdDate;
  }

  public Board() {

  }

  public Board(long l, DestiType.User user, String number, String 강원도, TravelType travelType, DestiType destiType, String 네시간, int i, int i1, int i2, int i3, int i4, boolean b) {
  }
}
