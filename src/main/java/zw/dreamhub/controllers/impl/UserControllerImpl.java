package zw.dreamhub.controllers.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import zw.dreamhub.controllers.UserController;
import zw.dreamhub.dto.payload.UserDTO;
import zw.dreamhub.dto.response.ApiResponse;
import zw.dreamhub.services.Logger;
import zw.dreamhub.services.UserService;
import zw.dreamhub.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 11/3/2022
 */

@RestController
public class UserControllerImpl implements UserController {

    final Logger logger;
    final UserService userService;
    final HttpServletRequest request;

    public UserControllerImpl(Logger logger, UserService userService, HttpServletRequest request) {
        this.logger = logger;
        this.userService = userService;
        this.request = request;
    }

    @Override
    public ResponseEntity<Object> getUsers() {
        /*
            Get available users in cache
        */
        logger.request(request.getRequestURI(), request.getMethod());
        return ResponseEntity.ok(new ApiResponse(true, userService.getUsers()));
    }

    @Override
    public ResponseEntity<Object> getUserByPhoneNumber(String phoneNumber) {
        /*
            Get user by phone number available in cache
        */
        logger.request(request.getRequestURI(), request.getMethod());
        Optional<UserDTO> user = userService.getUserByPhoneNumber(phoneNumber);
        return user.<ResponseEntity<Object>>map(userDTO -> ResponseEntity.ok(new ApiResponse(true, userDTO))).orElseGet(() -> ResponseEntity.status(404).body(new ApiResponse(false, "User not found")));
    }

    @Override
    public ResponseEntity<Object> addUser(UserDTO dto) {
        /*
            Add user to cache
        */
        logger.request(request.getRequestURI(), dto);
        try {
            dto.setLastInteractionTime(LocalDateTime.now());
            ApiResponse response = userService.addUser(dto);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            logger.request("User creation exception", e.getMessage());
            return ResponseEntity.badRequest().body(new ApiResponse(false, Constants.badRequestMsg));
        }
    }

    @Override
    public ResponseEntity<Object> modifyUser(UserDTO dto) {
        /*
           Modify user in cache
        */
        logger.request(request.getRequestURI(), dto);
        try {
            dto.setLastInteractionTime(LocalDateTime.now());
            ApiResponse response = userService.modifyUser(dto);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            logger.request("Modify user exception", e.getMessage());
            return ResponseEntity.badRequest().body(new ApiResponse(false, Constants.badRequestMsg));
        }
    }

    @Override
    public ResponseEntity<Object> deleteUser(String phoneNumber) {
        /*
           Delete user in cache
        */
        logger.request(request.getRequestURI(), request.getMethod());
        try {
            ApiResponse response = userService.deleteUser(phoneNumber);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            logger.request("Delete user exception", e.getMessage());
            return ResponseEntity.badRequest().body(new ApiResponse(false, Constants.badRequestMsg));
        }
    }
}
