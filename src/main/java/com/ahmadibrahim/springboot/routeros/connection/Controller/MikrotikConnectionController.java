package com.ahmadibrahim.springboot.routeros.connection.Controller;

import com.ahmadibrahim.springboot.routeros.connection.DTO.MikrotikCredentialRequest;
import com.ahmadibrahim.springboot.routeros.connection.DTO.MikrotikResponse;
import com.ahmadibrahim.springboot.routeros.connection.Service.MikrotikConnectionService;
import lombok.RequiredArgsConstructor;
import me.legrange.mikrotik.MikrotikApiException;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.net.ConnectException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(
        "/api/mikrotik"
)
public class MikrotikConnectionController {
    private final MikrotikConnectionService mikrotikConnectionService;

    @GetMapping
    public ResponseEntity<?>getSystemResource(){
        MikrotikCredentialRequest newRequest = new MikrotikCredentialRequest(null, null,"31.58.158.246","perwiracloud", "B6204_biw", "8728");
        try{
            List<?> response = mikrotikConnectionService.getSystemResource(newRequest);
            return ResponseEntity.ok().body(MikrotikResponse.success("Berhasil terkoneksi",response));
        }catch (MikrotikApiException exception){
            System.out.println("Error connection : " + exception.getMessage());
            return ResponseEntity.badRequest().body(MikrotikResponse.error(exception.getMessage()));
        }

    }

    @PostMapping(
            "/test"
    )
    public ResponseEntity<?>testConnection(@RequestBody MikrotikCredentialRequest request){
        try{
            List<?> response = mikrotikConnectionService.getSystemResource(request);
            return ResponseEntity.ok().body(response);
        }catch (MikrotikApiException exception){
            return ResponseEntity.badRequest().body("Gagal koneksi ke Mikrotik : " + exception.getMessage());
        }
    }

    @PostMapping(
            "/save"
    )
    public ResponseEntity<?> saveRouterToDatabase(@RequestBody MikrotikCredentialRequest request){
        try {
            List<?> response = mikrotikConnectionService.saveRouterToDatabase(request);
            return ResponseEntity.ok().body(MikrotikResponse.success("Berhasil tekroneksi", response));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(MikrotikResponse.error(exception.getMessage()));
        }
    }
}
