package com.example.mutithreadservice.controller;


import com.example.mutithreadservice.es.ProductDocument;
import com.example.mutithreadservice.model.Product;
import com.example.mutithreadservice.model.ProductDetails;
import com.example.mutithreadservice.repository.ESProductRepository;
import com.example.mutithreadservice.service.ProductService;
import org.springframework.data.domain.Page; // 导入 Spring Data 的 Page
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ESProductRepository esRepository;

    //并行执行
    @GetMapping("/details/{productId}")
    public ProductDetails getProductDetails(@PathVariable Long productId){
        return productService.getProductDetails(productId);
    }
    //串行执行
    @GetMapping("/details-serial/{productId}")
    public ProductDetails getProductDetailsSerial(@PathVariable("productId") Long productId) {
        return productService.getProductDetailsBySerial(productId);
    }
    @GetMapping("/search")
    public Page<ProductDocument> search(
            @RequestParam(required = false) String keyword,  // 搜索关键字,允许为null
            @RequestParam(defaultValue = "0") int page,// 第几页（默认第0页）
            @RequestParam(defaultValue = "10") int size,// 每页条数（默认10条）
            @RequestParam(defaultValue = "price") String sortField,// 排序字段（默认按价格）
            @RequestParam(defaultValue = "asc") String sortOrder) {// 排序顺序（默认升序）

        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Direction.fromString(sortOrder), sortField));
        if(keyword==null|| keyword.trim().isEmpty()){
            return  esRepository.findAll(pageable);
        }else{
            return esRepository.findByNameOrBrandOrDescription(keyword, keyword, keyword, pageable);
        }


    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

}
