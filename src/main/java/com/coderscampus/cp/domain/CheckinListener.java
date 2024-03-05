package com.coderscampus.cp.domain;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class CheckinListener {
    @PrePersist
    @PreUpdate
    public void beforeSave(Checkin checkin) {
        checkin.calculateTimeInClass();
    }
}