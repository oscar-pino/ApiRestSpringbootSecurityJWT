package cl.oscar_pino.apiSecurityJWT.entities.dto;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Size;

@Validated
public record AuthCreateRoleRequest(
        @Size(max = 3, message = "El usuario no puede tener más de 3 roles") 
		List<String> roleListName) {
}
