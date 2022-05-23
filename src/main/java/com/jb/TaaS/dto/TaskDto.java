package com.jb.TaaS.dto;

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
