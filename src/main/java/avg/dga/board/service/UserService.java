package avg.dga.board.service;

import avg.dga.board.dto.UserDto;
import avg.dga.board.entity.User;
import avg.dga.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
  private final UserRepository userRepository;

  public UserDto getUser(Long id){
    User user= userRepository.findById(id).get();

    UserDto userDto = UserDto.builder()
        .id(user.getId())
        .nickName(user.getNickName())
        .email(user.getEmail())
        .birthDate(user.getBirthDate())
        .grade(user.getGrade())
        .isAdmin(user.getIsAdmin())
        .isBanned(user.getIsBanned())
        .profileImageLink(user.getProfileImageLink())
        .etc(user.getEtc())
        .createDate(user.getCreateDate())
        .build();
    return userDto;
  }
}
