CREATE EXTENSION pg_trgm;

CREATE INDEX idx_vehicle_rendszam ON vehicle USING gin (rendszam gin_trgm_ops);
CREATE INDEX idx_vehicle_tulajdonos ON vehicle USING gin (tulajdonos gin_trgm_ops);
CREATE INDEX idx_vehicle_adatok_adatok ON vehicle_adatok USING gin (adatok gin_trgm_ops);