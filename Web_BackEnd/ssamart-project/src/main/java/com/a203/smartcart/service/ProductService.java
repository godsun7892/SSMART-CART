package com.a203.smartcart.service;

import com.a203.smartcart.model.dto.ProductFormDto;
import com.a203.smartcart.model.entity.Category;
import com.a203.smartcart.model.entity.Product;
import com.a203.smartcart.repository.CategoryRepository;
import com.a203.smartcart.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final S3ImageService s3ImageService;

    // 상품 등록
    public Product saveProduct(ProductFormDto productFormDto, MultipartFile image) {
        Category category = categoryRepository.findById(productFormDto.getCategorySeq())
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
        productFormDto.setImage(s3ImageService.upload(image));
        Product product = productFormDto.createProduct(category);
        return productRepository.save(product);
    }

    // 상품 조회
    public Product getProduct(int seq) {
        System.out.println(seq);
        return productRepository.findById(seq)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다."));
    }

    // 상품명으로 상품 검색, 카테고리 정보 포함
    public Page<Product> searchProductsByName(String name, Pageable pageable) {
        return productRepository.findByNameContaining(name, pageable);
    }

    // 상품 전체 리스트 조회
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    // 상품 수정
    public Product updateProduct(int seq, ProductFormDto productFormDto, MultipartFile image) {
        // DB에서 기존 상품을 가져옴
        Product existingProduct = productRepository.findById(seq)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다."));
        Category category = categoryRepository.findById(productFormDto.getCategorySeq())
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
        System.out.println(existingProduct.toString());
        // DTO의 속성들로 상품 속성 업데이트
        String imageURL = s3ImageService.upload(image);
        productFormDto.setImage(imageURL);
        existingProduct.updateFromDto(productFormDto, category);

        // 변경된 상품을 DB에 저장
        return productRepository.save(existingProduct);
    }

    // 상품 삭제
    public void deleteProduct(int seq) {
        Product product = productRepository.findById(seq)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다."));
        productRepository.delete(product);
    }

    // 할인 상품 불러오기
    public Page<Product> getDiscountProducts(Pageable pageable) {
        Float rate = 0.3F;
        return productRepository.findByDiscountRateGreaterThanEqualOrderByDiscountRateDesc(rate, pageable);
    }



}
