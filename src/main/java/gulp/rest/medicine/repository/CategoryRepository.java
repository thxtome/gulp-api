package gulp.rest.medicine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gulp.rest.medicine.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Long> {
	List<Category> findByNameContains(String name);
}
