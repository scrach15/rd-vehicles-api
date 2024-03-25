package hu.rd.vehicle.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.UUID;


public abstract class UuidIdentifiedEntity {

    @Id
    @JsonProperty("uuid")
    protected UUID id;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
