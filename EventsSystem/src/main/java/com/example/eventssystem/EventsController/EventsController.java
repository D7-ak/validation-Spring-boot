package com.example.eventssystem.EventsController;

import com.example.eventssystem.ApiResponse.ApiResponse;
import com.example.eventssystem.EventModel.Events;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/events")
public class EventsController {
    ArrayList<Events> events = new ArrayList<>();

    @PostMapping("/add")
    public ResponseEntity<?> addEvent(@RequestBody @Valid Events event, Errors error){
        if (error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        event.setStartDate(LocalDateTime.now());
        this.events.add(event);
        return ResponseEntity.status(200).body( new ApiResponse("event added successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getEvents(){
        return ResponseEntity.status(200).body(this.events);
    }

    @PutMapping("update/{index}")
    public ResponseEntity<?> updateEvent(@PathVariable int index,@RequestBody @Valid Events event,Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        event.setStartDate(LocalDateTime.now());
        this.events.set(index,event);
        return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));
    }

    @DeleteMapping("delete/{index}")
    public ResponseEntity<?> deleteEvent(@PathVariable int index){
        this.events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("event deleted successfully"));
    }

    @PutMapping("/change-capacity/{index}/{newcap}")
    public ResponseEntity<?> updateCap(@PathVariable int index, @PathVariable int newcap){
        if(newcap<25){
            return ResponseEntity.status(400).body(new ApiResponse("capacity must be more than 25"));
        }
        this.events.get(index).setCapacity(newcap);
        return ResponseEntity.status(200).body(new ApiResponse("capacity updated successfully"));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        for (Events e :this.events){
            if(e.getId().equalsIgnoreCase(id)){
                return ResponseEntity.status(200).body(e);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

}
