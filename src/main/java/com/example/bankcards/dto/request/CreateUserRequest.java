package com.example.bankcards.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotBlank
    @Size(min = 3, max=50)
    private String username;

    @NotBlank
    @Size(min = 6,message = "Пароль должен быть не менее 6 символов")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\\S{6,}$",message = "Пароль должен содержать латинские буквы верхнего и нижнего регистров, спецсимвол и цифру")
    private String password;

    @NotBlank
    private String role;
}
