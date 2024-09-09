package ru.skillbox.task.manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skillbox.task.manager.dto.UserCreateDto;
import ru.skillbox.task.manager.dto.UserDto;
import ru.skillbox.task.manager.entities.RoleEnum;
import ru.skillbox.task.manager.entities.User;
import ru.skillbox.task.manager.exceptions.ContentNotFoundException;
import ru.skillbox.task.manager.exceptions.UserAlredyExists;
import ru.skillbox.task.manager.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    public UserDto findbyEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new ContentNotFoundException(String.format("User %s not found", email)));
        return convertUserToDto(user);
    }

    public  User findByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() ->
                new ContentNotFoundException(String.format("User %s not found", username)));
    }
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new ContentNotFoundException("User not found"));
    }
    public List<UserDto> findAll(){
        return  userRepository.findAll().stream().map(this::convertUserToDto).toList();
    }

    public UserDto create(UserCreateDto userCreateDto){
        Optional<User> user = userRepository.findByEmail(userCreateDto.email());
        if (user.isPresent()){
            throw new UserAlredyExists(String.format("User with emal: %s alredy exists", userCreateDto.email()));
        }
        user = userRepository.findByUsername(userCreateDto.username());
        if (user.isPresent()){
            throw new UserAlredyExists(String.format("Username: %s alredy exists", userCreateDto.username()));
        }
        User newUser = userRepository.save(User.builder()
                .username(userCreateDto.username())
                .email(userCreateDto.email())
                .password(encoder.encode(userCreateDto.password()))
                .role(RoleEnum.USER)
                .build()
        );
        return convertUserToDto(newUser);

    }

    private UserDto convertUserToDto(User user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
        );
    }
}
