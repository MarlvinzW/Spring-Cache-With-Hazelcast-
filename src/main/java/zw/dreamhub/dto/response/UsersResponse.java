package zw.dreamhub.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import zw.dreamhub.dto.payload.UserDTO;

import java.util.List;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 11/3/2022
 */

@Data
@AllArgsConstructor
public class UsersResponse {
    List<UserDTO> users;
}
