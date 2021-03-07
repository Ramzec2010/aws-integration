package com.roman.awsintegration.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -6612614990450269845L;

    public BaseEntity(Timestamp dateInserted, Timestamp dateUpdated) {
        this.dateInserted = dateInserted;
        this.dateUpdated = dateUpdated;
    }

    @Column(name = "DATE_INSERTED", nullable = false)
    private Timestamp dateInserted;

    @Column(name = "DATE_UPDATED", nullable = false)
    private Timestamp dateUpdated;

    /** Will be trigger (before) if you use JPARepository::save(), since it calls persist. */
    @PrePersist
    private void onCreate() {
        Timestamp dateTime = new Timestamp(System.currentTimeMillis());
        this.setDateInserted(dateTime);
        this.setDateUpdated(dateTime);
    }

    /** Will be trigger (before) if you use JPARepository::update(). */
    @PreUpdate
    private void onPersist() {
        this.setDateUpdated(new Timestamp(System.currentTimeMillis()));
    }

    public Timestamp getDateInserted() {
        return dateInserted;
    }

    public void setDateInserted(Timestamp dateInserted) {
        this.dateInserted = dateInserted;
    }

    public Timestamp getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Timestamp dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
