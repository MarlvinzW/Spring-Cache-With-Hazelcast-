package zw.dreamhub.services;

import zw.dreamhub.dto.payload.UserDTO;
import zw.dreamhub.dto.response.ApiResponse;

import java.util.List;
import java.util.Optional;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 11/3/2022
 */


public interface UserService {
    ApiResponse addUser(UserDTO dto);
    ApiResponse modifyUser(UserDTO dto);
    ApiResponse deleteUser(String phoneNumber);
    Optional<UserDTO> getUserByPhoneNumber(String phoneNumber);
    List<UserDTO> getUsers();
}
