package com.example.validationspringboot.ProjectsController;
import com.example.validationspringboot.ApiRespons.ApiResponse;
import com.example.validationspringboot.ProjectsModel.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectsController {

    ArrayList<Project> projects = new ArrayList<>();

    @PostMapping("/add")
    public ResponseEntity<?> createProject(@RequestBody @Valid Project project, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        this.projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("project added successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity<?>  getProjects(){
        return ResponseEntity.status(200).body(this.projects);
    }

    @PutMapping("/update/{index}")
    public ResponseEntity<?>  updateProject(@PathVariable int index, @RequestBody @Valid Project project, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        this.projects.set(index,project);
        return ResponseEntity.status(400).body( new ApiResponse("updated successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<?>  deleteProject(@PathVariable int index){
        this.projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("project deleted successfully"));
    }

    @PutMapping("/update-status/{index}/{status}")
    public ResponseEntity<?>  updateStatus(@PathVariable int index,@PathVariable String status){
        if(!status.matches("^(Not Started|In Progress|Completed)$")){
            return ResponseEntity.status(400).body(new ApiResponse("Status must be ('Not Started', 'In Progress' or 'Completed') only"));
        }else {
            this.projects.get(index).setStatus(status);
            return ResponseEntity.status(200).body(new ApiResponse("status updated successfully"));
        }
    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity<?>  getByTitle(@PathVariable String title){
        for(Project p:this.projects){
            if(p.getTitle().equalsIgnoreCase(title)){
                return ResponseEntity.status(200).body(p);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("project with this title: '"+title+"' was no found"));
    }

    @GetMapping("/get-by-company-name/{compname}")
    public ResponseEntity<?>  getByCompanyName(@PathVariable String compname){
        ArrayList<Project> companyProjects = new ArrayList<>();
        for(Project p:this.projects){
            if(p.getCompanyName().equalsIgnoreCase(compname)){
                companyProjects.add(p);
            }
        }
        if(companyProjects.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("projects with this company name: '"+compname+"' was not found"));
        }else {
            return ResponseEntity.status(200).body(companyProjects);
        }
    }

}
