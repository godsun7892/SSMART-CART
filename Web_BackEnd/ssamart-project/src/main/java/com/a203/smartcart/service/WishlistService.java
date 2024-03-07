package com.a203.smartcart.service;

import com.a203.smartcart.model.dto.WishCartProductDto;
import com.a203.smartcart.model.dto.WishListDto;
import com.a203.smartcart.model.dto.WishListFormDto;
import com.a203.smartcart.model.dto.WishProductFormDto;
import com.a203.smartcart.model.entity.Member;
import com.a203.smartcart.model.entity.Product;
import com.a203.smartcart.model.entity.Wishlist;
import com.a203.smartcart.model.entity.Wishproduct;
import com.a203.smartcart.repository.MemberRepository;
import com.a203.smartcart.repository.ProductRepository;
import com.a203.smartcart.repository.WishProductRepository;
import com.a203.smartcart.repository.WishlistRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WishlistService {

    private final MemberRepository memberRepository;
    private final WishlistRepository wishlistRepository;
    private final WishProductRepository wishProductRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    // 찜목록 리스트 가져오기
    public List<WishListDto> getWishList(int seq) {

        Member member = memberRepository.findBySeq(seq);

        List<Wishlist> wishLists = wishlistRepository.findByMemberSeq(member.getSeq());
        List<WishListDto> wishListDtoList = new ArrayList<>();

        if(wishLists.isEmpty()) {
            Wishlist wishlist = new Wishlist("찜 목록1", member);
            wishlistRepository.save(wishlist);
            wishLists.add(wishlist);
        } else {
            for(int i=0; i<wishLists.size(); i++) {
                wishListDtoList.add(modelMapper.map(wishLists.get(i), WishListDto.class));
                wishListDtoList.get(i).setNum(wishProductRepository.countByWishlistSeq(wishLists.get(i).getSeq()));
                List<Wishproduct> wishproducts = wishProductRepository.findWishproductList(wishLists.get(i).getSeq());
                if(!wishproducts.isEmpty()) {
                    wishListDtoList.get(i).setImg(wishproducts.get(0).getProduct().getImage());
                    wishListDtoList.get(i).setProductName(wishproducts.get(0).getProduct().getName());
                }
            }
        }

        return wishListDtoList;
    }

    // 찜 목록 리스트 저장하기
    public WishListDto saveWishList(WishListFormDto wishListFormDto, int userSeq) {

        Member member = memberRepository.findBySeq(userSeq);

        Wishlist wishlist = new Wishlist(wishListFormDto.getName(), member);
        WishListDto wishListDto = modelMapper.map(wishlistRepository.save(wishlist), WishListDto.class);
        return wishListDto;
    }

    // 찜 목록 리스트 수정하기
    public WishListDto updateWishList(WishListFormDto wishListFormDto, int wishListSeq) {

        Wishlist existingwishlist = wishlistRepository.findById(wishListSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 찜 목록을 찾을 수 없습니다."));

        existingwishlist.setName(wishListFormDto.getName());
        WishListDto wishListDto = modelMapper.map(wishlistRepository.save(existingwishlist), WishListDto.class);
        return wishListDto;
    }


    // 찜 목록 리스트 삭제하기
    public void deleteWishList(int seq) {
        List<Wishproduct> wishproductList = wishProductRepository.findWishproductList(seq);
        for (Wishproduct wishproduct : wishproductList) {
            wishProductRepository.deleteById(wishproduct.getSeq());
        }
        wishlistRepository.deleteById(seq);

    }

    // 찜 상품들 가져오기
    public List<WishCartProductDto> getWishListProduct(int wishListseq) {

        List<Wishproduct> wishproductList = wishProductRepository.findWishproductList(wishListseq);
        List<WishCartProductDto> wishCartProductDtoList = new ArrayList<>();

        for(int i=0; i<wishproductList.size(); i++) {
            wishCartProductDtoList.add(modelMapper.map(wishproductList.get(i).getProduct(), WishCartProductDto.class));
            wishCartProductDtoList.get(i).setQuantity(wishproductList.get(i).getQuantity());
            wishCartProductDtoList.get(i).setWishProductSeq(wishproductList.get(i).getSeq());
            wishCartProductDtoList.get(i).setLocate(wishproductList.get(i).getProduct().getCategory().getLocation());
        }

        return wishCartProductDtoList;
    }

    // 찜 상품 저장하기
    // 1. product_seq로 해당하는 상품 찾음
    // 2. 가져온 wishlistSeq로 wishlist 찾음
    // 3. 그 wishlist 의 seq를 가진 상품 일련번호를 wishproduct에 저장x
    public WishCartProductDto saveWishProduct(WishProductFormDto wishProductFormDto, int seq) {

        // 가져온 상품seq로 상품 찾음
        Product product = productRepository.findBySeq(wishProductFormDto.getProductSeq());
        WishCartProductDto wishCartProductDto = modelMapper.map(product, WishCartProductDto.class);
        wishCartProductDto.setQuantity(wishProductFormDto.getQuantity());

        // 가져온 wishlistSeq로 wishlist 찾음
        Wishlist wishlist = wishlistRepository.findById(seq)
                .orElseThrow(() -> new IllegalArgumentException("해당 찜 목록을 찾을 수 없습니다."));

        Wishproduct wishproduct = wishProductRepository.save(wishProductFormDto.createWishproduct(wishlist, product));
        wishCartProductDto.setWishProductSeq(wishproduct.getSeq());
        return wishCartProductDto;
    }

    // 찜 상품 플러스
    public void plusWishProduct(int seq) {
        wishProductRepository.plusWishproduct(seq);
    }

    // 찜 상품 마이너스
    public void minusWishProduct(int seq) {
        wishProductRepository.minusWishproduct(seq);
    }

    // 찜 상품 삭제
    public void deleteWishProduct(int seq) {
        wishProductRepository.deleteById(seq);
    }

    // 찜 목록 전체 삭제
    public void deleteAllWishProduct(int seq) {

        wishProductRepository.deleteByWishlistSeq(seq);
    }

}
