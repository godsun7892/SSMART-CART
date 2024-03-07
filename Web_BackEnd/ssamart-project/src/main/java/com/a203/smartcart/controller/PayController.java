package com.a203.smartcart.controller;

import com.a203.smartcart.model.dto.*;
import com.a203.smartcart.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
public class PayController {

    private final PaymentService paymentService;

    @PostMapping("/regist")
    public ResponseEntity<?> savePayMethod(@RequestBody PayMethodFormDto payMethodFormDto) {

        try {
            paymentService.enrollCard(payMethodFormDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED); // 201 CREATED
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/process")
    public ResponseEntity<?> pay(@RequestBody PayProcessDto payProcessDto) {

        try {
            return new ResponseEntity<Boolean>(paymentService.payWithBillingkey(payProcessDto), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/confirm/{memSeq}")
    public ResponseEntity<?> savePayMethod(@PathVariable int memSeq) {

        List<PayHistoryDto> payHistoryDtoList = paymentService.getPayHistory(memSeq);
        return new ResponseEntity<List<PayHistoryDto>>(payHistoryDtoList, HttpStatus.OK);

//        try {
//            List<PayHistoryDto> payHistoryDtoList = paymentService.getPayHistory(memSeq);
//            return new ResponseEntity<List<PayHistoryDto>>(payHistoryDtoList, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
//        }

    }

}
