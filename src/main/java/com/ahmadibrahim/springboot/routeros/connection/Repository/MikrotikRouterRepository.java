package com.ahmadibrahim.springboot.routeros.connection.Repository;

import com.ahmadibrahim.springboot.routeros.connection.Model.MikrotikRouter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MikrotikRouterRepository extends JpaRepository<MikrotikRouter, Long> {
    Optional<MikrotikRouter> findByIpAddress(String ipAddress);
}
