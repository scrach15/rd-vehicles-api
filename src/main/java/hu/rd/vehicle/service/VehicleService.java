package hu.rd.vehicle.service;

import hu.rd.vehicle.api.exception.VehicleNotFoundException;
import hu.rd.vehicle.model.Vehicle;
import hu.rd.vehicle.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    /**
     * Saves the given vehicle.
     *
     * @param vehicle the vehicle to be saved
     * @return
     */
    public Vehicle save(Vehicle vehicle) {
        vehicle.setId(null);
        return vehicleRepository.save(vehicle);
    }

    /**
     * Finds a vehicle by its ID.
     *
     * @param  id   the ID of the vehicle to find
     * @return      the found vehicle or null if not found
     */
    public Vehicle findById(String id) {
        return vehicleRepository.findById(UUID.fromString(id)).orElseThrow(() -> new VehicleNotFoundException(String.format("Vehicle not found: %s", id)));
    }

    public Long count() {
        return vehicleRepository.count();
    }

    public List<Vehicle> search(String query) {
        return vehicleRepository.search(query, Pageable.ofSize(10));
    }
}
