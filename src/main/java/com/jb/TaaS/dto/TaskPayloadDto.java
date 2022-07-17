package com.jb.TaaS.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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

    //    @JsonFormat(pattern = "dd/MM/yyy HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dueDate;

    //I added since user disappears on update
    @ToString.Exclude
    private User user;
}

