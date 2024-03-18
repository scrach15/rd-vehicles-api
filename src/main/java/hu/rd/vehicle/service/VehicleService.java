package hu.rd.vehicle.service;

import hu.rd.vehicle.api.exception.VehicleNotFoundException;
import hu.rd.vehicle.model.Vehicle;
import hu.rd.vehicle.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
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
        return vehicleRepository.save(vehicle);
    }

    /**
     * Finds a vehicle by its ID.
     *
     * @param  id   the ID of the vehicle to find
     * @return      the found vehicle or null if not found
     */
    public Vehicle findById(String id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(String.format("Vehicle not found: %s", id)));
        return vehicle;
    }

    public Long count() {
        return vehicleRepository.count();
    }

    public List<Vehicle> search(String query) {
        List<Vehicle> result = vehicleRepository.search(query);
        return result;
    }
}
