package com.railway_booking.railwaybooking.service;

import com.railway_booking.railwaybooking.dto.TrainRequest;
import com.railway_booking.railwaybooking.dto.TrainResponse;
import com.railway_booking.railwaybooking.entity.Train;
import com.railway_booking.railwaybooking.exception.TrainNotFoundException;
import com.railway_booking.railwaybooking.repository.TrainRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
/*
No. Mockito and JUnit are different tools, but they are commonly used together in Java testing.
JUnit → Framework for writing and running tests.
Example: @Test, assertions like assertEquals()
Mockito → Framework for creating mock objects and simulating dependencies.
Example: @Mock, when(), verify()

For Private Method testing we used Reflection;
*/
public class TrainServiceTest {

    @Mock
    private TrainRepository trainRepository;

    @InjectMocks
    private TrainService trainService;


    @BeforeAll  //Class Level Setup
    public static void TestInitialize() {
        System.out.println("TestInitialize");

    }

    @BeforeEach
    public void BeforeEachReq(){
        System.out.println("BeforeEach");
    }

    @Test
    public void createTrainTest() {

        TrainRequest req = new TrainRequest();

        req.setTrainNumber("12345");
        req.setTrainName("Shikhar Express");

        Train savedTrain = new Train();
        savedTrain.setTrainId(1L);
        savedTrain.setTrainNumber("12345");
        savedTrain.setTrainName("Shikhar Express");




        when(trainRepository.save(any(Train.class))).thenReturn(savedTrain);

        TrainResponse result = trainService.createTrain(req);

        assertEquals("12345", result.getTrainNumber());
        assertEquals("Shikhar Express", result.getTrainName());
    }

    @AfterEach
    public void AfterAllRequest() {
        System.out.println("AfterAllRequest");
    }

    @Test
    public void deleteTrain() {


       Mockito.doNothing().when(trainRepository).deleteById(1L);

        trainService.deleteTrain(1L);

        Mockito.verify(trainRepository)
                .deleteById(1L);

        log.info("Delete Train Success");

    }

    @Test
    void shouldMapTrainToTrainResponse() {
        Train train = new Train();
        train.setTrainId(1L);
        train.setTrainNumber("12345");
        train.setTrainName("Rajdhani Express");
        train.setStatus("ACTIVE");

        TrainResponse response =
                ReflectionTestUtils.invokeMethod(
                        trainService,
                        "mapToResponse",
                        train
                );

        assertEquals(1L, response.getTrainId());
        assertEquals("12345", response.getTrainNumber());
        assertEquals("Rajdhani Express", response.getTrainName());
        assertEquals("ACTIVE", response.getStatus());
    }


    @Test
    public void getTrainById() {
        TrainRequest req1 = new TrainRequest();

        req1.setTrainNumber("12345");
        req1.setTrainName("Shikhar Express");

        Train savedTrain = new Train();
        savedTrain.setTrainId(1L);
        savedTrain.setTrainNumber("12345");
        savedTrain.setTrainName("Shikhar Express");

        Mockito.when(trainRepository.findById(1L)).thenReturn(Optional.of(savedTrain));



        TrainResponse id=   trainService.getTrainById(1L);
        TrainNotFoundException exception = assertThrows(
                TrainNotFoundException.class,
                () -> trainService.getTrainById(2L)
        );

        assertNotNull(exception);

        Mockito.verify(trainRepository, Mockito.times(1))
                .findById(1L);


    }
}
