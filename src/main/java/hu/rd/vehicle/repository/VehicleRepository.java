package hu.rd.vehicle.repository;

import hu.rd.vehicle.model.Vehicle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

@Transactional(Transactional.TxType.MANDATORY)
public interface VehicleRepository  extends JpaRepository<Vehicle, String> {

    @Query("select count(v) from Vehicle v")
    long count();

    @Query("select v from Vehicle v join fetch v.adatok a where v.rendszam ilike %?1% or v.tulajdonos ilike %?1% or exists (select 1 from v.adatok a where a ilike %?1%)")
    List<Vehicle> search(String query);

    @Query(value = "SELECT v.uuid, v.rendszam, v.tulajdonos, v.forgalmi_ervenyes, va.adatok FROM vehicle v inner join vehicle_adatok va ON v.uuid = va.vehicle_uuid WHERE va.adatok ILIKE :query OR v.rendszam ILIKE :query OR v.tulajdonos ILIKE :query LIMIT 25", nativeQuery = true)
    Set<Vehicle> search2(@Param("query") String query);



}
