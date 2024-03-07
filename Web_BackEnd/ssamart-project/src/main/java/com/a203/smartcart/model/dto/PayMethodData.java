package com.a203.smartcart.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayMethodData {

    private String mId;
    private String customerKey;
    private String authenticatedAt;
    private String method;
    private String billingKey;
    private Card card;

    @Getter @Setter
    public static class Card {
        private String issuerCode;
        private String acquirerCode;
        private String number;
        // cardType 필드는 정의하지만, 사용할 때는 무시
        private String cardType;
        private String ownerType;

    }


}
