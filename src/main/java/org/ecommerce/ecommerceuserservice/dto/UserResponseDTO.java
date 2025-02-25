package org.ecommerce.ecommerceuserservice.dto;

import lombok.*;
import org.ecommerce.ecommerceuserservice.entity.UserRole;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private UserRole role;
}
