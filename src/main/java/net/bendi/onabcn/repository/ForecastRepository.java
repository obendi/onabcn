package net.bendi.onabcn.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.bendi.onabcn.domain.Forecast;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Date> {
	
	List<Forecast> findAllByDateBetween(Date dateStart, Date dateEnd);

}
