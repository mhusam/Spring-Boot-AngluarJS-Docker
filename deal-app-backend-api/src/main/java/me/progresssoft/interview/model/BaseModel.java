package me.progresssoft.interview.model;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * @author Husam Aljamal
 * @email m-husam@hotmail.com
 * @since 20 Nov, 2021
 * @details https://github.com/mhusam/Spring-Boot-AngluarJS-Docker
 */
@MappedSuperclass
@Data
public abstract class BaseModel implements Serializable {

    @Id
    @Column(name = "uuid", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private String uuid;

    public BaseModel() {}

    @PrePersist
    public void prePersist() {
        this.uuid = UUID.randomUUID().toString();
    }
}
