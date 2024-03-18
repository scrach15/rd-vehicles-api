package hu.rd.vehicle.repository;

import hu.rd.vehicle.model.Vehicle;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Transactional(Transactional.TxType.MANDATORY)
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    @Query("select max(v.id) from Vehicle v")
    long count2();


    @Query("select count(v) from Vehicle v")
    long count();

    @Query("select v from Vehicle v join fetch v.adatok a where v.rendszam ilike %?1% or v.tulajdonos ilike %?1% or exists (select 1 from v.adatok a where a ilike %?1%)")
    List<Vehicle> search(String query);


}
