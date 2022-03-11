package zw.dreamhub.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.dreamhub.dto.payload.UserDTO;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 11/3/2022
 */

@RequestMapping(path = "${base.url.public}")
@Produces("application/json")
@Consumes("application/json")
public interface UserController {

    @GetMapping("users")
    ResponseEntity<Object> getUsers();

    @GetMapping("users/{phoneNumber}")
    ResponseEntity<Object> getUserByPhoneNumber(@Valid @PathVariable String phoneNumber);

    @PostMapping("users")
    ResponseEntity<Object> addUser(@Valid @RequestBody UserDTO dto);

    @PatchMapping("users")
    ResponseEntity<Object> modifyUser(@Valid @RequestBody UserDTO dto);

    @DeleteMapping("users/{phoneNumber}")
    ResponseEntity<Object> deleteUser(@Valid @PathVariable String phoneNumber);
}
