package com.example.validationspringboot.ProjectsModel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty(message ="ID Can't be Empty ")
    @Size(min =2,message ="ID length should be more than 2")
    private String id;
    @NotEmpty(message ="title Can't be Empty ")
    @Size(min =8,message ="title length should be more than 8")
    private String title;
    @NotEmpty(message ="description Can't be Empty ")
    @Size(min =15,message ="description length should be more than 15")
    private String description;
    @NotEmpty(message ="status Can't be Empty ")
    @Pattern(regexp ="^(Not Started|In Progress|Completed)$",message="Status must be ('Not Started', 'In Progress' or 'Completed') only")
    private String status;
    @NotEmpty(message ="company Name Can't be Empty ")
    @Size(min =8,message ="company Name length should be more than 8")
    private String companyName;
}
