package com.ahmadibrahim.springboot.routeros.connection.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@NotBlank
@Getter
@Setter
public class MikrotikCredentialRequest {
    private String name;
    private String area;
    private String ipAddress;
    private String username;
    private String password;
    private String port;
}
