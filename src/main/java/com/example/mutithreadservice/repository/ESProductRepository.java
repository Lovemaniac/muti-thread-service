package com.example.mutithreadservice.repository;


import com.example.mutithreadservice.es.ProductDocument;
import org.springframework.data.domain.Page; // 导入 Spring Data 的 Page
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public interface ESProductRepository extends ElasticsearchRepository<ProductDocument, String> {
    Page<ProductDocument> findByNameOrBrandOrDescription(String name, String brand, String description, Pageable pageable);
}
