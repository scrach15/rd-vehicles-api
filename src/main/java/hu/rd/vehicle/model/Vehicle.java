package hu.rd.vehicle.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@Document
@ToString
//@CompoundIndexes({
//        @CompoundIndex(name = "search", def = "{'rendszam': 1, 'tulajdonos': 1, 'adatok': 1}")
//})
public class Vehicle extends UuidIdentifiedEntity {

//    @Indexed
    @NotNull(message = "Nem lehet null")
    @Size(min = 1, message = "Nem lehet ures")
    @JsonProperty("rendszam")
    public String rendszam;

//    @Indexed
    @NotNull(message = "Nem lehet null")
    @Size(min = 1, message = "Nem lehet ures")
    @JsonProperty("tulajdonos")
    public String tulajdonos;

    @NotNull(message = "Nem lehet null")
    @Size(min = 1, message = "Nem lehet ures")
    @JsonProperty("forgalmi_ervenyes")
    public String forgalmiErvenyes;

//    @Indexed
    @NotNull(message = "Nem lehet null")
    @JsonProperty("adatok")
    public Set<String> adatok;


}
