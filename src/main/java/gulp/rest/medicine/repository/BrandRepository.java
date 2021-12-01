package gulp.rest.medicine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gulp.rest.medicine.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository <Brand, Long> {
	List<Brand> findByNameContains(String name);
}
