package avg.dga.board.dto;

import avg.dga.board.entity.Board;
import avg.dga.board.entity.DestiType;
import avg.dga.board.entity.TravelType;
import avg.dga.board.entity.User;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor // 인자없이 객체 생성 가능
public class BoardRequest {
  private Long id;
  private String title;
  private String content;
  private User user;

  private Integer revisitCount;
  //일단 빼고 구성
  //private Integer likeCount; 
  private TravelType travelType;
  private DestiType destiType;
  private String destiName;
  private String latitude;
  private String longitude;
  private String thumbnailImg;
  private String s3ImgLnk;

  private LocalDateTime createDate;
  private LocalDateTime modifiedDate;

  public Board toEntity(){
    return Board.builder()
        .id(id)
        .title(title)
        .content(content)
        .destiName(destiName)
        .user(getUser())
        .longitude(longitude)
        .latitude(latitude)
        .destiType(destiType)
        .travelType(travelType)
        .build();
  }

  @Builder
  public BoardRequest(Long id, String title, String content, Integer revisitCount, TravelType travelType, DestiType destiType, String destiName, String longitude, String latitude, String thumbnailImg,
                     LocalDateTime createdDate, LocalDateTime modifiedDate){
    this.id = id;
    this.title = title;
    this.content = content;
    this.revisitCount = revisitCount;
    this.travelType = travelType;
    this.destiType = destiType;
    this.destiName = destiName;
    this.longitude = longitude;
    this.latitude = latitude;
    this.thumbnailImg = thumbnailImg;
    this.createDate = createdDate;
    this.modifiedDate = modifiedDate;
  }
}