package com.roman.awsintegration.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CATEGORY")
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
    @Column(name = "PRODUCT_ID")
    private Long categoryId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<ProductEntity> products = new HashSet<>();

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
