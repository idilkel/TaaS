package com.jb.TaaS.dto;

import com.jb.TaaS.beans.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginReqDto {
    @Email
    private String email;
    @Length(min = 3, max = 8)
    private String password;

    private ClientType type;
}
