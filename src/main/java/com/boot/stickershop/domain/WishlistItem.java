package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wishlist_items")
@Getter
@Setter
public class WishlistItem {
    public WishlistItem() { regtime = LocalDateTime.now(); }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime regtime;
    @ManyToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;
}
