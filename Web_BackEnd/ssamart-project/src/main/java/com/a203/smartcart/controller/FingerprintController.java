package com.a203.smartcart.controller;

import com.a203.smartcart.model.constant.FingerprintMessage;
import com.a203.smartcart.model.dto.LoginDto;
import com.a203.smartcart.service.FingerprintService;
import com.a203.smartcart.service.MemberService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FingerprintController {

    @Autowired
    private FingerprintService fingerprintService;

    @Autowired
    private MemberService memberService;

    @PostMapping("/fingerprint/register")
    public ResponseEntity register(@RequestBody String data) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(data);

        String phoneNum = jsonObject.get("phoneNum").toString();

        JSONArray dataArray = (JSONArray) jsonObject.get("data");

        byte[][][] result = new byte[dataArray.size()][288][128];
        int resultRow = 288;
        int resultCol = 128;

        for(int i = 0; i < dataArray.size(); i++){
            JSONArray imageArray = (JSONArray) dataArray.get(i);
            int row = 0;
            int col = 0;
            for(int j = 0; j < imageArray.size(); j++){
                result[i][row][col] = (byte) Integer.parseUnsignedInt(imageArray.get(j).toString());
                col += 1;
                if (col >= resultCol){
                    row += 1;
                    col = 0;
                }
            }

        }

        int serviceResult = fingerprintService.register(result, phoneNum);


        if(serviceResult == -2){
            return new ResponseEntity<>(new FingerprintMessage("BAD_REQUEST","로그인 정보가 등록되어 있지 않습니다."),null, HttpStatus.BAD_REQUEST);
        }
        if(serviceResult == -1){
            return new ResponseEntity<>(new FingerprintMessage("BAD_REQUEST","등록되지 않은 유저입니다."),null, HttpStatus.BAD_REQUEST);
        }
        else if (serviceResult == 0){
            return new ResponseEntity<>(new FingerprintMessage("INTERNAL_SERVER_ERROR", "정보 등록에 실패하였습니다."),null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else if(serviceResult == 1){
            return new ResponseEntity<>(new FingerprintMessage("OK", "성공적으로 정보를 등록하였습니다."),null,HttpStatus.OK);
        }

        return new ResponseEntity<>(new FingerprintMessage("UNKNOWN_ERROR","알 수 없는 오류가 발생했습니다."),null,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping("/fingerprint/match")
    public ResponseEntity match(@RequestBody String data) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(data);

        String phoneNum = jsonObject.get("phoneNum").toString();

        JSONArray dataArray = (JSONArray) jsonObject.get("data");

        byte[][] result = new byte[288][128];
        int resultRow = 288;
        int resultCol = 128;
        int row = 0;
        int col = 0;
        for(int i = 0; i < dataArray.size(); i++){
            result[row][col] = (byte) Integer.parseUnsignedInt(dataArray.get(i).toString());
            col += 1;
            if(col >= resultCol){
                row += 1;
                col = 0;
            }
        }

        int serviceResult = fingerprintService.match(result, phoneNum);

        if(serviceResult == -1){
            return new ResponseEntity<>(new FingerprintMessage("BAD_REQUEST","등록되지 않은 유저입니다."),null, HttpStatus.BAD_REQUEST);
        }
        else if (serviceResult == 0){
            return new ResponseEntity<>(new FingerprintMessage("UNAUTHORIZED", "로그인 정보가 올바르지 않습니다."),null, HttpStatus.UNAUTHORIZED);
        }
        else if(serviceResult == 1){
            LoginDto loginDto = this.memberService.fingerPrintLogin(phoneNum);
            return ResponseEntity.status(HttpStatus.OK).body(loginDto);
        }

        return new ResponseEntity<>(new FingerprintMessage("UNKNOWN_ERROR","알 수 없는 오류가 발생했습니다."),null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}