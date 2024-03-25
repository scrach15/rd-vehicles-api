package hu.rd.vehicle.api;

import hu.rd.vehicle.model.Vehicle;
import hu.rd.vehicle.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/jarmuvek")
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/{uuid}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable String uuid) {
        return ResponseEntity.ok().cacheControl(CacheControl.noStore()).body(vehicleService.findById(uuid));
    }

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public  @ResponseBody ResponseEntity<String> getVehicleCount() {
        return ResponseEntity.ok().cacheControl(CacheControl.noStore()).body(String.valueOf(vehicleService.count()));
    }

    @PostMapping
    public ResponseEntity<Object> createVehicle(@RequestBody Vehicle vehicle) throws URISyntaxException {
        Vehicle saved = vehicleService.save(vehicle);
        return ResponseEntity.created(new URI(String.format("/jarmuvek/%s", saved.getId()))).build();
    }
}
