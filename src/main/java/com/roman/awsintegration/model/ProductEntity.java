package com.roman.awsintegration.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class ProductEntity extends BaseEntity {

    private static final long serialVersionUID = 3285573456314741883L;

    @Builder
    public ProductEntity(Timestamp dateInserted, Timestamp dateUpdated, Long productId, String name, BigDecimal price, Set<CategoryEntity> categories) {
        super(dateInserted, dateUpdated);
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.categories = categories;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_ID_SEQ")
    @SequenceGenerator(name = "PRODUCT_ID_SEQ", allocationSize = 1)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name="PRICE", nullable = false)
    private BigDecimal price;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "PRODUCT_CATEGORY_RELATION",
            joinColumns = { @JoinColumn(name = "PRODUCT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "CATEGORY_ID") }
    )
    private Set<CategoryEntity> categories = new HashSet<>();



}
