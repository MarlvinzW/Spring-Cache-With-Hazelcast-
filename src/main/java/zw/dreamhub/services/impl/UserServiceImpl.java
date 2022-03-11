package zw.dreamhub.services.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.stereotype.Service;
import zw.dreamhub.dto.payload.UserDTO;
import zw.dreamhub.dto.response.UsersResponse;
import zw.dreamhub.dto.response.ApiResponse;
import zw.dreamhub.services.Logger;
import zw.dreamhub.services.UserService;
import zw.dreamhub.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 11/3/2022
 */

@Service
public class UserServiceImpl implements UserService {

    final HazelcastInstance hazelcastInstance;
    final Logger logger;

    public UserServiceImpl(HazelcastInstance hazelcastInstance, Logger logger) {
        this.hazelcastInstance = hazelcastInstance;
        this.logger = logger;
    }

    private ConcurrentMap<String, String> instance() {
        return hazelcastInstance.getMap("map");
    }

    Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    @Override
    public ApiResponse addUser(UserDTO dto) {
        Optional<UserDTO> user = this.getUserByPhoneNumber(dto.getPhoneNumber());
        if (user.isPresent()) {
            return new ApiResponse(false, "User already exists");
        } else {
            List<UserDTO> users = this.getUsers();
            users.add(dto);
            save(users);
            return new ApiResponse(true, Constants.successMsg);
        }

    }

    @Override
    public ApiResponse modifyUser(UserDTO dto) {
        Optional<UserDTO> user = this.getUserByPhoneNumber(dto.getPhoneNumber());
        if (user.isPresent()) {
            List<UserDTO> users = this.getUsers();
            users.remove(user.get());
            users.add(dto);
            this.save(users);
            return new ApiResponse(true, Constants.successMsg);
        } else {
            return new ApiResponse(false, "User not found");
        }
    }

    @Override
    public ApiResponse deleteUser(String phoneNumber) {
        Optional<UserDTO> user = this.getUserByPhoneNumber(phoneNumber);
        if (user.isPresent()) {
            List<UserDTO> users = this.getUsers();
            users.remove(user.get());
            this.save(users);
            return new ApiResponse(true, Constants.successMsg);
        } else {
            return new ApiResponse(false, "User not found");
        }
    }

    @Override
    public Optional<UserDTO> getUserByPhoneNumber(String phoneNumber) {
        List<UserDTO> users = this.getUsers();
        return users.stream().filter(item -> item.getPhoneNumber().equalsIgnoreCase(phoneNumber)).findFirst();
    }

    @Override
    public List<UserDTO> getUsers() {
        String users = instance().get(Constants.usersCacheName);
        if ((users == null) || users.isBlank() || users.isEmpty()) {
            return new ArrayList();
        } else {
            JsonObject data = new Gson().fromJson(users, JsonObject.class);
            UsersResponse dto = gson.fromJson(data, UsersResponse.class);
            return dto.getUsers();
        }
    }

    private void save(List<UserDTO> users) {
        instance().put(Constants.usersCacheName, gson.toJson(new UsersResponse(users)));
    }
}
