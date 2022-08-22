package com.jb.TaaS.dto;

import com.jb.TaaS.beans.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResDto {

    private UUID token;
    private String email;
    private ClientType type;
}
