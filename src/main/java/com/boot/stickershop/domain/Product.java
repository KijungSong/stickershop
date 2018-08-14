package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
public class Product {
    public Product() { regtime = LocalDateTime.now(); }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String size;
    private Long sales;
    @Column(name = "image_path") // @Column이 없으면 이름 그대로 필드명 그대로 Column이 된다.
    private String imagePath;

    @ManyToOne(targetEntity = ProductCategory.class, fetch=FetchType.EAGER) //FetchType.EAGER : Product조회시 productCategory도 함께 가져온다.
    @JoinColumn(name="category_id")
    private ProductCategory productCategory;
    private LocalDateTime regtime;
    private LocalDateTime edittime;  // 수정날짜

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // mappedBy = product 속성을 통해 조인된다.
    private List<ProductFile> productFiles = new ArrayList<>();

    public void addProductFile(ProductFile productFile){
        this.productFiles.add(productFile);
        if(productFile.getProduct()!=this){
            productFile.setProduct(this);
        }
    }

}
