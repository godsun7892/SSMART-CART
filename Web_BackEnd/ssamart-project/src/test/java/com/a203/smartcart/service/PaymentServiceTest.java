package com.a203.smartcart.service;

import com.a203.smartcart.model.dto.PayMethodFormDto;
import com.a203.smartcart.model.dto.PayProcessDto;
import com.a203.smartcart.model.entity.Member;
import com.a203.smartcart.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void enrollTest() throws Exception {
        String phoneNumber = "01011111111";
        String ck = "Y291c3Rlc3QxMjM0NTYwMQ";
        String authkey = "bln_m4a2R2mRLKQ";

        PayMethodFormDto payMethodFormDto = new PayMethodFormDto();
        payMethodFormDto.setAuthKey(authkey);
        payMethodFormDto.setPhoneNumber(phoneNumber);
        payMethodFormDto.setCustomerKey(ck);

        Member member = memberRepository.findByUserId(phoneNumber);

        boolean result = paymentService.enrollCard(payMethodFormDto);

        Assertions.assertEquals(result, true);
    }

    @Test
    public void payWithBillingkeyTest() {


    }

}
