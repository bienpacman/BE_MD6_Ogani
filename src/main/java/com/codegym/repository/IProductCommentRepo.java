package com.codegym.repository;

import com.codegym.model.ProductComment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductCommentRepo extends CrudRepository<ProductComment, Long> {
    ProductComment save(ProductComment productComment);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.comments where product_id =:idProduct\n" +
            "order by id desc\n" +
            "limit 2 ;")
    List<ProductComment> findProductCommentListByProductId(Long idProduct);
}
