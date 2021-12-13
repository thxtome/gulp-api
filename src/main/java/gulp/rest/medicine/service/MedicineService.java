package gulp.rest.medicine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gulp.rest.medicine.dto.MedicineForm;
import gulp.rest.medicine.model.Brand;
import gulp.rest.medicine.model.Category;
import gulp.rest.medicine.model.Medicine;
import gulp.rest.medicine.repository.BrandRepository;
import gulp.rest.medicine.repository.CategoryRepository;
import gulp.rest.medicine.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicineService {

	@Autowired
	private final MedicineRepository medicineRepository;

	@Autowired
	private final BrandRepository brandRepository;

	@Autowired
	private final CategoryRepository categoryRepository;

	public ResponseEntity<Object> createMedicine(MedicineForm medicineForm) {
		Long medicineId = medicineRepository.saveAndFlush(new Medicine().create(
				medicineForm.getName()
				, medicineForm.getCategoryId()
				, medicineForm.getBrandId())
			).getId();
		return new ResponseEntity<>(medicineId, HttpStatus.OK);
	}
	
	public ResponseEntity<Object> getMedicines(String name, Long brandId) {
		List<Medicine> medicines = medicineRepository.findByNameContainsAndBrandId(name, brandId);
		return new ResponseEntity<>(medicines, HttpStatus.OK);
	}

	public ResponseEntity<Object> createBrand(Brand brand) {
		Long brandId = brandRepository.saveAndFlush(brand).getId();
		return new ResponseEntity<>(brandId, HttpStatus.OK);
	}

	public ResponseEntity<Object> getBrands(String name) {
		List<Brand> brands = brandRepository.findByNameContains(name);
		return new ResponseEntity<>(brands, HttpStatus.OK);
	}
	
	public ResponseEntity<Object> getCategories() {
		List<Category> categories = categoryRepository.findAll();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	public ResponseEntity<Object> createCategory(Category category) {
		Long categoryId = categoryRepository.saveAndFlush(category).getId();
		return new ResponseEntity<>(categoryId, HttpStatus.OK);
	}
	
}
