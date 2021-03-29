package com.roman.awsintegration.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true, exclude = "products")
@ToString(callSuper = true, exclude = "products")
@Table(name = "CATEGORY", schema = "public")
public class CategoryEntity extends BaseEntity {

    private static final long serialVersionUID = -5289699882513381166L;

    @Builder
    public CategoryEntity(Timestamp dateInserted, Timestamp dateUpdated, Long categoryId, String name, String description) {
        super(dateInserted, dateUpdated);
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_ID_SEQ")
    @SequenceGenerator(name = "CATEGORY_ID_SEQ", allocationSize = 1)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<ProductEntity> products = new HashSet<>();

    @Formula("(SELECT COUNT(p.product_id) FROM product p " +
            "join PRODUCT_CATEGORY_RELATION pc on pc.product_id = p.product_id " +
            "join CATEGORY c on c.CATEGORY_ID = pc.CATEGORY_ID " +
            "WHERE c.CATEGORY_ID = category_id)")
    private Integer numberOfProducts;
}
