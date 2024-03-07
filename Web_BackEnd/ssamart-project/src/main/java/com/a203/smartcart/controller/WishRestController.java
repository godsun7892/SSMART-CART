package com.a203.smartcart.controller;

import com.a203.smartcart.model.dto.*;
import com.a203.smartcart.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wish")
@RequiredArgsConstructor // 이거 박으면 빈을 주입할 때 오토와이얼드 안박아도 final 같은거 박혀 있으면 자동으로 해줌
public class WishRestController {

    private final WishlistService wishlistService;

    // 찜 목록 리스트 불러오기
    @GetMapping("/list/{userSeq}")
    public ResponseEntity<?> getWishList(@PathVariable int userSeq) {

        List<WishListDto> list = wishlistService.getWishList(userSeq);
        return new ResponseEntity<List<WishListDto>>(list, HttpStatus.OK);

//        try {
//            List<WishListDto> list = wishlistService.getWishList(userSeq);
//            return new ResponseEntity<List<WishListDto>>(list, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
//        }
    }

    // 찜 목록 저장하기
    @PostMapping("/listSave/{userSeq}")
    public ResponseEntity<?> saveWishList(@RequestBody WishListFormDto wishListFormDto,
                                             @PathVariable int userSeq) {
        try {
            WishListDto wishListDto = wishlistService.saveWishList(wishListFormDto, userSeq);
            return new ResponseEntity<WishListDto>(wishListDto, HttpStatus.CREATED); // 201 CREATED
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    // 찜 목록 수정하기
    @PatchMapping("listUpdate/{wishListSeq}")
    public ResponseEntity<?> updateWishList(@RequestBody WishListFormDto wishListFormDto,
                                          @PathVariable int wishListSeq) {
        try {
            WishListDto wishListDto = wishlistService.updateWishList(wishListFormDto, wishListSeq);
            return new ResponseEntity<WishListDto>(wishListDto, HttpStatus.CREATED); // 201 CREATED
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

    }

    // 찜 목록 삭제하기
    @DeleteMapping("/listDelete/{wishListSeq}")
    public ResponseEntity<?> deleteWishList(@PathVariable int wishListSeq) {
        try {
            wishlistService.deleteWishList(wishListSeq);
            return new ResponseEntity<>(HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    // 찜 목록에 해당하는 상품들 불러오기
    @GetMapping("/products/{wishListSeq}")
    public ResponseEntity<?> getWishListProduct(@PathVariable int wishListSeq) {

        try {
            List<WishCartProductDto> list = wishlistService.getWishListProduct(wishListSeq);
            return new ResponseEntity<List<WishCartProductDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    // 찜 상품 저장하기
    @PostMapping("/productSave/{wishListSeq}")
    public ResponseEntity<?> saveWishProduct(@RequestBody WishProductFormDto wishProductFormDto,
                                                @PathVariable int wishListSeq) {

       try {
           WishCartProductDto WishCartProductDto = wishlistService.saveWishProduct(wishProductFormDto, wishListSeq);
           return new ResponseEntity<WishCartProductDto>(WishCartProductDto, HttpStatus.CREATED); // 201 CREATED
       } catch (Exception e) {
           return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
       }

    }

    // 찜 상품 수량 플러스
    @PatchMapping("/productPlus/{wishProductSeq}")
    public ResponseEntity<?> plusWishProduct(@PathVariable int wishProductSeq) {
        try {
            wishlistService.plusWishProduct(wishProductSeq);
            return new ResponseEntity<>(HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    // 찜 상품 수량 마이너스
    @PatchMapping("/productMinus/{wishProductSeq}")
    public ResponseEntity<?> minusWishProduct(@PathVariable int wishProductSeq) {
        try {
            wishlistService.minusWishProduct(wishProductSeq);
            return new ResponseEntity<>(HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    // 찜 상품 삭제
    @DeleteMapping("/productDelete/{wishProductSeq}") // seq에 해당하는 wishproduct 삭제
    public ResponseEntity<?> deleteWishProduct(@PathVariable int wishProductSeq) {
        try {
            wishlistService.deleteWishProduct(wishProductSeq);
            return new ResponseEntity<>(HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    // 찜 상품 전체 삭제
    @DeleteMapping("/productDeleteAll/{wishListSeq}")
    public ResponseEntity<?> deleteAllWishProduct(@PathVariable int wishListSeq) {
        try {
            wishlistService.deleteAllWishProduct(wishListSeq);
            return new ResponseEntity<>(HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

}
