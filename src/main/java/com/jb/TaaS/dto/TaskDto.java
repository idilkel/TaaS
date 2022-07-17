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
public class TaskDto {

    private int id;
    @NotBlank
    private String caption;
    @NotBlank
    private String info;
    @NotBlank
    private String classification;
    @NotBlank
//    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dueDate;

    //I added since user disappears on update
    @ToString.Exclude
    private User user;

    public TaskDto(TaskPayloadDto taskPayloadDto) {
        this.caption = taskPayloadDto.getCaption();
        this.info = taskPayloadDto.getInfo();
        this.classification = taskPayloadDto.getClassification();
        this.dueDate = taskPayloadDto.getDueDate();
        //I added since user disappears on update
        this.user = taskPayloadDto.getUser();
    }
}
