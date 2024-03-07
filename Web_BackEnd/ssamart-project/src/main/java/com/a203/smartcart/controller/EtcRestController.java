package com.a203.smartcart.controller;

import com.a203.smartcart.model.dto.RecommendedDishDto;
import com.a203.smartcart.service.EctService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/etc")
@RequiredArgsConstructor
public class EtcRestController {

    private final EctService ectService;

    @GetMapping("/randomCuisine")
    public ResponseEntity<?> getRandomCuisine() {

        try {
            RecommendedDishDto recommendedDishDto = ectService.getRandomCuisine();
            return new ResponseEntity<RecommendedDishDto>(recommendedDishDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/location")
    public ResponseEntity<?> getLocation() {

        try {
            return new ResponseEntity<List<String>>(ectService.getAllLocations(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

    }

}
