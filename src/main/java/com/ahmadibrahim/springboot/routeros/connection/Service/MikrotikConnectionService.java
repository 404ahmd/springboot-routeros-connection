package com.ahmadibrahim.springboot.routeros.connection.Service;

import com.ahmadibrahim.springboot.routeros.connection.DTO.MikrotikCredentialRequest;
import com.ahmadibrahim.springboot.routeros.connection.Exception.DuplicateDataException;
import com.ahmadibrahim.springboot.routeros.connection.Model.MikrotikRouter;
import com.ahmadibrahim.springboot.routeros.connection.Repository.MikrotikRouterRepository;
import lombok.RequiredArgsConstructor;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.ConnectException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MikrotikConnectionService {
    private final MikrotikRouterRepository mikrotikRouterRepository;

   public List<?> getSystemResource(MikrotikCredentialRequest request) throws MikrotikApiException {
       ApiConnection connection = ApiConnection.connect(request.getIpAddress());
       connection.login(request.getUsername(), request.getPassword());
       return connection.execute("/system/resource/print");
   }

   public void testConnection(MikrotikCredentialRequest request) throws MikrotikApiException{
       try(ApiConnection connection = ApiConnection.connect(request.getIpAddress())){
            connection.login(request.getUsername(), request.getPassword());
       }
   }

   public List<?> saveRouterToDatabase(MikrotikCredentialRequest request){
        try{
            List<?> response = getSystemResource(request);
            if (mikrotikRouterRepository.findByIpAddress(request.getIpAddress()).isPresent()){
                throw new RuntimeException("Router sudah terdaftar");
            }
            MikrotikRouter router = new MikrotikRouter();
            router.setName(request.getName());
            router.setArea(request.getArea());
            router.setIpAddress(request.getIpAddress());
            router.setUsername(request.getUsername());
            router.setPassword(request.getPassword());
            router.setPort(request.getPort());
            mikrotikRouterRepository.save(router);
            return response;
        }catch (MikrotikApiException exception){
            throw new RuntimeException(exception.getMessage());
        }
   }
}
