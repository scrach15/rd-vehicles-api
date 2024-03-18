package hu.rd.vehicle.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", nullable = false)
    @JsonProperty("uuid")
    private String id;

    @NotNull(message = "Nem lehet null")
    @Size(min = 1, message = "Nem lehet ures")
    @Column(length = 20)
    @JsonProperty("rendszam")
    public String rendszam;

    @NotNull(message = "Nem lehet null")
    @Size(min = 1, message = "Nem lehet ures")
    @Column(length = 200)
    @JsonProperty("tulajdonos")
    public String tulajdonos;

    @NotNull(message = "Nem lehet null")
    @Size(min = 1, message = "Nem lehet ures")
    @JsonProperty("forgalmi_ervenyes")
    @Column(length = 10)
    public String forgalmiErvenyes;

    @NotNull(message = "Nem lehet null")
    @ElementCollection(fetch = FetchType.EAGER)
    @JsonProperty("adatok")
    public List<String> adatok;


}
