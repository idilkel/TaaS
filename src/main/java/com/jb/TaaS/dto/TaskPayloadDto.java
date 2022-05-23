package com.jb.TaaS.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jb.TaaS.beans.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskPayloadDto {
    @NotBlank
    private String caption;

    @NotBlank
    private String info;

    @NotBlank
    private String classification;

    @JsonFormat(pattern = "dd/MM/yyy HH:mm:ss")
    private LocalDateTime dueDate;

    //I added since user disappears on update
    @ToString.Exclude
    private User user;
}

