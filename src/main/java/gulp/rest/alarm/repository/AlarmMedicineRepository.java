package gulp.rest.alarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gulp.rest.alarm.model.AlarmMedicine;

@Repository
public interface AlarmMedicineRepository extends JpaRepository <AlarmMedicine, Long> {
	
}
