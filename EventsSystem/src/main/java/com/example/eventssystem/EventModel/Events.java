package com.example.eventssystem.EventModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Events {
    @NotEmpty
    @Size(min = 2,message = "Length must be more than 2 ")
    private String id;
    @NotEmpty
    @Size(min = 15,message = "description must be more than 15 ")
    private String description;
    @NotNull(message = "capacity cannot be null")
    @Min(value = 26, message = "capacity must be more than 25")
    private Integer capacity;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime startDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;
}
