package com.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by akrantan on 5.4.2017.
 */

@MappedSuperclass
public abstract class DomainObject {
    @Id
    @GeneratedValue
    private long id;
}
