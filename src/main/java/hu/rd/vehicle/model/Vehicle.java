package hu.rd.vehicle.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@NamedNativeQuery(
        name = "Vehicle.search",
        query = "SELECT * FROM vehicle_adatok va JOIN vehicle v ON v.uuid = va.vehicle_uuid WHERE v.rendszam ILIKE '%R%' OR v.tulajdonos ILIKE '%R%' OR va.adatok ILIKE '%R%'",
        resultClass = Vehicle.class
)
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
    public Set<String> adatok;


}
