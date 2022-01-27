package gulp.rest.medicine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gulp.rest.medicine.model.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository <Medicine, Long> {
	List<Medicine> findByBrandId(Long brandId);
	List<Medicine> findByCategoryId(Long categoryId);
	List<Medicine> findByNameContainsAndBrandIdAndCategoryId(String name, Long brandId, Long categoryId);
	
	@Query(nativeQuery = true
			, value = "UPDATE"
					+ "    medicine m "
					+ "    , ("
					+ "			SELECT "
					+ "				medicine_id "
					+ "            , COUNT(alarm_hist_medicine_id) as daily_dose_count "
					+ "         FROM "
					+ "				alarm_hist_medicine "
					+ "         WHERE "
					+ "         	DATE_FORMAT(created_at, \"%Y%m%d\") = :yesterday "
					+ "         GROUP BY medicine_id "
					+ "      ) ahm_count "
					+ "SET "
					+ "	   m.dose_count = m.dose_count + ahm_count.daily_dose_count "
					+ "WHERE "
					+ "	   m.medicine_id = ahm_count.medicine_id; ")
	@Modifying
	void updateMedicineDoseCount(@Param("yesterday") String yesterday);
}
