package com.jb.TaaS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReqDto {
    @Email
    private String email;
    @Length(min = 3, max = 8)
    private String password;
}
