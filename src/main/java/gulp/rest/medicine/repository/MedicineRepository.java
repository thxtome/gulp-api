package gulp.rest.medicine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gulp.rest.medicine.model.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository <Medicine, Long> {
	List<Medicine> findByBrandId(Long brandId);
	List<Medicine> findByCategoryId(Long categoryId);
	List<Medicine> findByNameContainsAndBrandIdAndCategoryId(String name, Long brandId, Long categoryId);
}
