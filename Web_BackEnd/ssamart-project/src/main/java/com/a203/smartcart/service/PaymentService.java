package com.a203.smartcart.service;

import com.a203.smartcart.model.dto.*;
import com.a203.smartcart.model.entity.*;
import com.a203.smartcart.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final MemberRepository memberRepository;
    private final PaymethodRepository paymethodRepository;
    private final ModelMapper modelMapper;
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final RecepitproductRepository recepitproductRepository;
    private final RecepitRepository recepitRepository;

    @Value("${my.sKey}")
    private String secretKey;

    public boolean enrollCard(PayMethodFormDto payMethodFormDto) throws Exception {

        String phoneNumber = payMethodFormDto.getPhoneNumber();
        String authKey = payMethodFormDto.getAuthKey();
        String customerKey = payMethodFormDto.getCustomerKey();

        // 1. phone-> DB에서 사용자 seq 번호 가져오기
        Member member = memberRepository.findByUserId(phoneNumber);

        // 2. 밑에 코드 실행
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/billing/authorizations/issue"))
                .header("Authorization", "Basic " + secretKey)
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"authKey\":\"" + authKey + "\",\"customerKey\":\"" + customerKey + "\"}"))
                .build();


            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() != 200){
                throw new Exception("Failed to get billing key");
            }

            // TBD: Dealing with billing key response
            /*
            {
                "mId": "tosspayments",
                    "customerKey": "8uzCPbgmIVWi11ltEiv5C",
                    "authenticatedAt": "2021-01-01T10:00:00+09:00",
                    "method": "카드",
                    "billingKey": "Z_t5vOvQxrj4499PeiJcjen28-V2RyqgYTwN44Rdzk0=",
                    "card": {
                "issuerCode": "61",
                        "acquirerCode": "31",
                        "number": "43301234****123*",
                        "cardType": "신용",
                        "ownerType": "개인"
            }
            }*/

            // 3. DB에 Paymethod 저장
            ObjectMapper objectMapper = new ObjectMapper();
            PayMethodData payMethodData = objectMapper.readValue(response.body(), PayMethodData.class);
            Paymethod paymethod = new Paymethod();
            paymethod.setMember(member);
            paymethod.setBillingKey(payMethodData.getBillingKey());
            paymethod.setCustomerKey(payMethodData.getCustomerKey());
            paymethod.setCardType(paymethod.getCardType());
            paymethod.setOwnerType(paymethod.getOwnerType());

            paymethodRepository.save(paymethod);
            return true;

        // 4. controller에 결과 리턴

    }

    public boolean payWithBillingkey(PayProcessDto payProcessDto) throws Exception {

        int memSeq = payProcessDto.getMemSeq();
        int amount = payProcessDto.getAmount();
        Cart cart = cartRepository.findByMemberSeq(memSeq);
        int cartSeq = cart.getSeq();

        Member member = memberRepository.findBySeq(memSeq);

        // 1. 결제수단일련번호 + 회원 일련 번호로 DB에서 결제키랑 회원키(결제수단 테이블 내에 있음) 가져오기
        List<Paymethod> paymethodList = paymethodRepository.findByMember_Seq(memSeq);
        if(paymethodList.get(0) == null) {
            return false;
        }

        int taxFreeAmount = 0;
        String customerEmail = member.getEmail();
        String customerName = member.getName();
        String billingKey = paymethodList.get(0).getBillingKey();
        String customerKey = paymethodList.get(0).getCustomerKey();
        String orderId = generateRandomString(10);
        String orderName = "SSAMART 결제";

        // 2. 밑에 코드 호출
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/billing/" + billingKey))
                .header("Authorization", "Basic " + secretKey)
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"customerKey\":\"" + customerKey + "\",\"amount\":" + amount + ",\"orderId\":\""
                        + orderId + "\",\"orderName\":\"" + orderName +"\",\"customerEmail\":\"" + customerEmail + "\",\"customerName\":\"" + customerName
                        + "\",\"taxFreeAmount\":" + taxFreeAmount + "}"))
                .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() != 200){
                throw new Exception("Failed to make a payment with billing key");
            }

            // TBD: Dealing with payment response
            /*
            {
                "mId": "tosspayments",
                    "version": "2022-11-16",
                    "paymentKey": "TeKdbKfsdMgo5WsoGodNi",
                    "status": "DONE",
                    "lastTransactionKey": "YRs_GtuYs7T7zqzjcKWKb",
                    "orderId": "LI__dmzb6kc6ERDjLKYwO",
                    "orderName": "토스 프라임 구독",
                    "requestedAt": "2022-06-08T15:40:09+09:00",
                    "approvedAt": "2022-06-08T15:40:49+09:00",
                    "useEscrow": false,
                    "cultureExpense": false,
                    "card": {
                "issuerCode": "61",
                        "acquirerCode": "31",
                        "number": "43301234****123*",
                        "installmentPlanMonths": 0,
                        "isInterestFree": false,
                        "interestPayer": null,
                        "approveNo": "00000000",
                        "useCardPoint": false,
                        "cardType": "신용",
                        "ownerType": "개인",
                        "acquireStatus": "READY",
                        "amount": 4900
            },
                "virtualAccount": null,
                    "transfer": null,
                    "mobilePhone": null,
                    "giftCertificate": null,
                    "cashReceipt": null,
                    "cashReceipts": null,
                    "discount": null,
                    "cancels": null,
                    "secret": null,
                    "type": "BILLING",
                    "easyPay": null,
                    "country": "KR",
                    "failure": null,
                    "isPartialCancelable": true,
                    "receipt": {
                "url": "https://dashboard.tosspayments.com/sales-slip?transactionId=KAgfjGxIqVVXDxOiSW1wUnRWBS1dszn3DKcuhpm7mQlKP0iOdgPCKmwEdYglIHX&ref=PX"
            },
                "checkout": {
                "url": "https://api.tosspayments.com/v1/payments/TeKdbKfsdMgo5WsoGodNi/checkout"
            },
                "currency": "KRW",
                    "totalAmount": 4900,
                    "balanceAmount": 4900,
                    "suppliedAmount": 4455,
                    "vat": 455,
                    "taxFreeAmount": 0,
                    "taxExemptionAmount": 0,
                    "method": "카드"
            }*/

            ObjectMapper objectMapper = new ObjectMapper();
            ReceiptData receiptData = objectMapper.readValue(response.body(), ReceiptData.class);

            Receipt receipt = modelMapper.map(receiptData, Receipt.class);
            receipt.setMember(member);
            receipt.setCart(cart);

            recepitRepository.save(receipt);

            List<WishCartProductDto> wishCartProductDtoList = cartProductRepository.findWishCartProductDtoList(cart.getSeq());
            for(WishCartProductDto wishCartProductDto : wishCartProductDtoList) {
                Receiptproduct receiptproduct = new Receiptproduct();
                receiptproduct.setName(wishCartProductDto.getName());
                receiptproduct.setPrice(wishCartProductDto.getPrice());
                receiptproduct.setQuantity(wishCartProductDto.getQuantity());
                receiptproduct.setReceipt(receipt);
                receiptproduct.setProductSeq(wishCartProductDto.getSeq());
                receiptproduct.setDiscountPrice((int)(wishCartProductDto.getDiscountRate() * wishCartProductDto.getPrice()));
                recepitproductRepository.save(receiptproduct);
            }

        // 3. 결제 내역 DB에 저장
        // 4. 컨트롤러에 리턴

        return true;
    }

    public List<PayHistoryDto> getPayHistory(int memSeq) {
        List<Receipt> receiptList = recepitRepository.findByMember_Seq(memSeq);
        if(receiptList.isEmpty()) return null;
        List<PayHistoryDto> payHistoryDtoList = new ArrayList<>();
        for (int i = 0; i < receiptList.size(); i++) {
            payHistoryDtoList.add(new PayHistoryDto());
            payHistoryDtoList.get(i).setReceiptSeq(receiptList.get(i).getSeq());
            payHistoryDtoList.get(i).setDate(receiptList.get(i).getDate().toString());

            List<PayHistoryProductDto> payHistoryProductDtoList = new ArrayList<>();
            List<Receiptproduct> receiptproductList = recepitproductRepository.findByReceipt_Seq(receiptList.get(i).getSeq());
            for (int j = 0; j < receiptproductList.size(); j++) {
                payHistoryProductDtoList.add(modelMapper.map(receiptproductList.get(j), PayHistoryProductDto.class));
            }

            payHistoryDtoList.get(i).setPayHistoryProductDtoList(payHistoryProductDtoList);

        }
        return payHistoryDtoList;
    }

    private String generateRandomString(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }

}
