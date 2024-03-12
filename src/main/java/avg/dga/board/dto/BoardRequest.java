package avg.dga.board.dto;

import avg.dga.board.entity.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor // 인자없이 객체 생성 가능
public class BoardRequest extends Time{
  private Long id;
  private String title;
  private String content;
  private String nickname;
  private User user;
  private Long userId;
  private Area area;

  private Integer revisitCount;
  //일단 빼고 구성
  private Integer likeCount;
  private TravelType travelType;
  private DestiType destiType;
  private String destiName;
  private String latitude;
  private String longitude;
  private String thumbnailUrl;

  private LocalDateTime createDate;
  private LocalDateTime modifiedDate;

  public Board toEntity(){
    return Board.builder()
        .id(id)
        .title(title)
        .content(content)
        .user(user)
        .destiName(destiName)
        .area(area)
        .revisitCount(revisitCount)
        .longitude(longitude)
        .latitude(latitude)
        .thumbnailUrl(thumbnailUrl)
        .destiType(destiType)
        .travelType(travelType)
        .likeCount(likeCount)
        .build();
  }

  @Builder
  public BoardRequest(Long id, String title, String content, String nickname, User user, Long userId, Area area, Integer revisitCount, TravelType travelType, DestiType destiType, String destiName, Integer likeCount, String longitude, String latitude, String thumbnailUrl,
                      LocalDateTime createdDate, LocalDateTime modifiedDate){
    this.id = id;
    this.title = title;
    this.content = content;
    this.nickname = nickname;
    this.user = user;
    this.userId = userId;
    this.area = area;
    this.revisitCount = revisitCount;
    this.travelType = travelType;
    this.destiType = destiType;
    this.destiName = destiName;
    this.likeCount = likeCount;
    this.longitude = longitude;
    this.latitude = latitude;
    this.thumbnailUrl = thumbnailUrl;
    this.createDate = createdDate;
    this.modifiedDate = modifiedDate;
  }

}