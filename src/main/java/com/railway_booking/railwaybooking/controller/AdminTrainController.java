package com.railway_booking.railwaybooking.controller;

import com.railway_booking.railwaybooking.dto.TrainRequest;
import com.railway_booking.railwaybooking.dto.TrainResponse;
import com.railway_booking.railwaybooking.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/trains")
public class AdminTrainController {
    private final TrainService trainService;

    public  AdminTrainController(TrainService trainService){
        this.trainService = trainService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainResponse createTrain(@Valid @RequestBody TrainRequest request){
        return  trainService.createTrain(request);
    }

    @GetMapping
    public List<TrainResponse> getALLTrains(){
        return  trainService.getAllTrains();
    }

    @GetMapping("/{id}")
    public TrainResponse getTrainById(@PathVariable Long  id){
        return  trainService.getTrainById(id);
    }

    @PutMapping("/{id}")
    public TrainResponse updateTrain(@PathVariable Long id, @Valid @RequestBody TrainRequest request){
        return  trainService.updateTrain(id, request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void deleteTrain(@PathVariable Long id){
        trainService.deleteTrain(id);
    }

}
