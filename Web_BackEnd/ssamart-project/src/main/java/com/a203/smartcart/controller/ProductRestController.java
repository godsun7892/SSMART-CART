package com.a203.smartcart.controller;

import com.a203.smartcart.model.dto.ProductFormDto;
import com.a203.smartcart.model.entity.Product;
import com.a203.smartcart.service.ProductService;
import com.a203.smartcart.service.S3ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;
    private final S3ImageService s3ImageService;

    // 상품 등록
    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(
            @RequestPart("productForm") ProductFormDto productFormDto,
            @RequestPart(value = "image", required = false) MultipartFile image) throws Exception {

        try {
            Product product = productService.saveProduct(productFormDto, image);
            return new ResponseEntity<Void>(HttpStatus.CREATED); // 201 CREATED
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    // 상품 전체 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAllProducts(Pageable pageable) {
        Page<Product> productsPage = productService.getAllProducts(pageable);
        List<Product> products = productsPage.getContent();
        if(products.isEmpty()) {
            return new ResponseEntity<>((HttpStatus.NO_CONTENT));
        }
        return new ResponseEntity<>(products, HttpStatus.OK); // 200 OK
    }

    // 상품 상세 조회
    @GetMapping("/detail/{productSeq}")
    public ResponseEntity<Product> getProduct(@PathVariable int productSeq) {
        Product product = productService.getProduct(productSeq);
        System.out.println(product.getCategory().toString());
        if(product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 NOT FOUND
        }
    }

    // 상품 수정
    @PatchMapping("/patch/{productSeq}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int productSeq,
            @RequestPart("productForm") ProductFormDto productFormDto,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        Product updatedProduct = productService.updateProduct(productSeq, productFormDto, image);
        if(updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 NOT FOUND
        }
    }

    // 할인 상품 불러오기
    @GetMapping("/discount")
    public ResponseEntity<?> getdiscountProduct(Pageable pageable) {
        Page<Product> productsPage = productService.getDiscountProducts(pageable);
        List<Product> products = productsPage.getContent();
        if(products.isEmpty()) {
            return new ResponseEntity<>((HttpStatus.NO_CONTENT));
        }
        return new ResponseEntity<>(products, HttpStatus.OK); // 200 OK
    }

    // 검색으로 상품 불러오기
    @GetMapping("/search/{name}")
    public ResponseEntity<?> getSearchProducts(@PathVariable String name, Pageable pageable) {
        Page<Product> productsPage = productService.searchProductsByName(name, pageable);
        List<Product> products = productsPage.getContent();
        if(products.isEmpty()) {
            return new ResponseEntity<>((HttpStatus.NO_CONTENT));
        }
        return new ResponseEntity<>(products, HttpStatus.OK); // 200 OK
    }

    @PostMapping("/img")
    public ResponseEntity<?> s3Upload(@RequestPart(value = "image", required = false) MultipartFile image){
        String profileImage = s3ImageService.upload(image);
        return ResponseEntity.ok(profileImage);
    }

}
