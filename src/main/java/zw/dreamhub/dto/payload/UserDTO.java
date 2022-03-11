package zw.dreamhub.dto.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 11/3/2022
 */

@Data
@AllArgsConstructor
public class UserDTO {

    @NotNull
    @NotBlank
    private String fullName;
    @NotNull
    @NotBlank
    @Size(min = 12, max = 12)
    private String phoneNumber;
    @NotNull
    @NotBlank
    private String stage;
    @NotNull
    @NotBlank
    private String position;

    private LocalDateTime lastInteractionTime;
}
