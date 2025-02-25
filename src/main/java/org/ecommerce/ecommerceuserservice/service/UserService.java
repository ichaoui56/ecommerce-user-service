package org.ecommerce.ecommerceuserservice.service;

import lombok.RequiredArgsConstructor;
import org.ecommerce.ecommerceuserservice.dto.UserRequestDTO;
import org.ecommerce.ecommerceuserservice.dto.UserResponseDTO;
import org.ecommerce.ecommerceuserservice.entity.User;
import org.ecommerce.ecommerceuserservice.entity.UserRole;
import org.ecommerce.ecommerceuserservice.exception.EmailAlreadyExists;
import org.ecommerce.ecommerceuserservice.exception.UserNotFoundException;
import org.ecommerce.ecommerceuserservice.mapper.UserMapper;
import org.ecommerce.ecommerceuserservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDTO createUser(UserRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExists("L'utilisateur avec l'email " + request.getEmail() + " existe déjà");
        }

        User user = userMapper.toEntity(request);
        user.setRole(UserRole.USER);

        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }


    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé avec l'ID : " + id));
        return userMapper.toDTO(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé avec l'ID : " + id));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setDateOfBirth(request.getDateOfBirth());

        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé avec l'ID : " + id));
        userRepository.delete(user);
    }
}
