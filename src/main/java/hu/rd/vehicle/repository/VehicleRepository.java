package hu.rd.vehicle.repository;

import hu.rd.vehicle.model.Vehicle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface VehicleRepository  extends MongoRepository<Vehicle, UUID> {

    @Query("{ $or: [ { 'rendszam': { $regex: ?0, $options: 'i' } }, { 'tulajdonos': { $regex: ?0, $options: 'i' } }, { 'adatok': { $regex: ?0, $options: 'i' } } ] }")
    List<Vehicle> search(String query, Pageable page);




}
