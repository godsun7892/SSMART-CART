package com.a203.smartcart.service;

import com.a203.smartcart.model.dto.CartListDto;
import com.a203.smartcart.model.dto.SaveCartDto;
import com.a203.smartcart.model.dto.WishCartProductDto;
import com.a203.smartcart.model.entity.*;
import com.a203.smartcart.repository.CartProductRepository;
import com.a203.smartcart.repository.CartRepository;
import com.a203.smartcart.repository.MemberRepository;
import com.a203.smartcart.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;

    // cart 상품 전체 가져오기
    public CartListDto getCart(int seq) {

        Member member = memberRepository.findBySeq(seq);

        Cart cart = cartRepository.findByMemberSeq(member.getSeq());
        System.out.println(cart);
        if(cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
            return null;
        } else {
            CartListDto cartListDto = new CartListDto();
            cartListDto.setCartSeq(cart.getSeq());
            cartListDto.setWishCartProductDtoList(cartProductRepository.findWishCartProductDtoList(cart.getSeq()));
            return cartListDto;
        }

    }

    // 카트 상품 저장
    public SaveCartDto saveCartProduct(String barcode, int userSeq) {
        SaveCartDto saveCartDto = new SaveCartDto();
        Product product = productRepository.findByBarcode(barcode);
        if(product == null) {
            throw new NullPointerException();
        }
        Cart cart = cartRepository.findByMemberSeq(userSeq);
        saveCartDto.setProduct(product);

        Cartproduct cartproduct = new Cartproduct(cart, product, 1);
        saveCartDto.setCartProductSeq(cartProductRepository.save(cartproduct).getSeq());
        return saveCartDto;
    }

    // 카트 상품 플러스
    public void plusCartProduct(int cartProductseq) {
        cartProductRepository.plusCartproduct(cartProductseq);
    }

    // 카트 상품 마이너스
    public void minusCartProduct(int cartProductseq) {
        cartProductRepository.minusCartproduct(cartProductseq);
    }

    // 카트 상품 하나 삭제
    public void deleteCartProduct(int seq) {
        cartProductRepository.deleteById(seq);
    }

    // 카트 목록 전체 삭제
    public void deleteAllCartProduct(int seq) {

        Member member = memberRepository.findBySeq(seq);

        Cart cart = cartRepository.findByMemberSeq(member.getSeq());
        cartProductRepository.deleteByCartSeq(cart.getSeq());
    }

}
