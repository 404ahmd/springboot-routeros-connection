package com.ahmadibrahim.springboot.routeros.connection.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MikrotikResponse<T> {
    private Boolean success;
    private String messages;
    private T data;

    public static <T> MikrotikResponse<T> success(String messages, T data){
        return MikrotikResponse.<T>builder()
                .success(true)
                .messages(messages)
                .data(data)
                .build();
    }

    public static <T> MikrotikResponse<T> error(String error){
        return MikrotikResponse.<T>builder()
                .success(false)
                .messages(error)
                .data(null)
                .build();
    }
}
