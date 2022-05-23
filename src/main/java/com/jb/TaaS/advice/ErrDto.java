package com.jb.TaaS.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrDto {
    private final String key = "Taas";
    private String value;
}
