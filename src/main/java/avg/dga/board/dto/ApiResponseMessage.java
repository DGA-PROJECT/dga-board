package avg.dga.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseMessage {
  private String Status;        // HttpStatus
  private String message;       // Http Default Message
  private String errorMessage;  // Error Message to USER
  private String errorCode;     // Error Code
}
