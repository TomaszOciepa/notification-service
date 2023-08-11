package com.mango.notifications.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@ToString
public class NotificationInfoDto {
    private List<String> emails;
    private String courseId;
    private String courseName;
    private String courseDescription;
    private LocalDateTime courseStartDate;
    private LocalDateTime courseEndDate;


}
