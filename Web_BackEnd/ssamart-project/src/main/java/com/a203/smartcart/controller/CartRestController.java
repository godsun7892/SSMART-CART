package com.a203.smartcart.controller;

import com.a203.smartcart.model.dto.BarcodeDto;
import com.a203.smartcart.model.dto.CartListDto;
import com.a203.smartcart.model.dto.SaveCartDto;
import com.a203.smartcart.model.dto.WishCartProductDto;
import com.a203.smartcart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartRestController {

    private final CartService cartService;

    // 장바구니 목록 불러오기
    @GetMapping("/list/{userSeq}")
    public ResponseEntity<?> detail(@PathVariable int userSeq) {
        CartListDto cartListDto = cartService.getCart(userSeq);
        return new ResponseEntity<CartListDto>(cartListDto, HttpStatus.OK);
    }

    // 징바구니 상품 저장하기
    @PostMapping("/save/{userSeq}")
    public ResponseEntity<?> saveWishProduct(@RequestBody BarcodeDto barcodeDto,
                                             @PathVariable int userSeq) {
        try {
            SaveCartDto saveCartDto = cartService.saveCartProduct(barcodeDto.getBarcode(), userSeq);
            return new ResponseEntity<SaveCartDto>(saveCartDto, HttpStatus.CREATED); // 201 CREATED
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

    }

    // 장바구니 상품 수량 플러스
    @PatchMapping("/plus/{cartProductseq}")
    public ResponseEntity<?> plusCartProduct(@PathVariable int cartProductseq) {
        try {
            cartService.plusCartProduct(cartProductseq);
            return new ResponseEntity<>(HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    // 장바구니 상품 수량 마이너스
    @PatchMapping("/minus/{cartProductseq}")
    public ResponseEntity<?> minusCartProduct(@PathVariable int cartProductseq) {
        try {
            cartService.minusCartProduct(cartProductseq);
            return new ResponseEntity<>(HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    // 장바구니 상품 삭제
    @DeleteMapping("/delete/{cartProductseq}") // seq에 해당하는 wishproduct 삭제
    public ResponseEntity<?> deleteCartProduct(@PathVariable int cartProductseq) {
        try {
            cartService.deleteCartProduct(cartProductseq);
            return new ResponseEntity<>(HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    // 장바구니 상품 전체 삭제
    @DeleteMapping("/deleteAll/{userSeq}")
    public ResponseEntity<?> deleteAllCartProduct(@PathVariable int userSeq) {
        try {
            cartService.deleteAllCartProduct(userSeq);
            return new ResponseEntity<>(HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

}
