package hu.rd.vehicle.api;

import hu.rd.vehicle.model.Vehicle;
import hu.rd.vehicle.service.VehicleService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kereses")
public class SearchController {

    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> search(@RequestParam(required = false) String q) {
        if (StringUtils.isEmpty(q)) {
            throw new ConstraintViolationException("q must not be empty", null);
        }
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES)).body(vehicleService.search(q));
    }
}
