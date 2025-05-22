package userservice.service;


import userservice.dto.AuthResponseDTO;
import userservice.dto.LoginRequestDTO;
import userservice.dto.UserRequestDTO;

public interface UserService {
    void registerUser(UserRequestDTO userRequest);
    AuthResponseDTO loginUser(LoginRequestDTO loginRequest);
}
