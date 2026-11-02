package com.ahmadibrahim.springboot.routeros.connection.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(
        name = "mikrotik_routers"
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MikrotikRouter {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private String area;
    private String ipAddress;
    private String username;
    private String password;
    private String port;
}
