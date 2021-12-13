package gulp.rest.medicine;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gulp.rest.medicine.dto.MedicineForm;
import gulp.rest.medicine.model.Brand;
import gulp.rest.medicine.model.Category;
import gulp.rest.medicine.model.Medicine;
import gulp.rest.medicine.service.MedicineService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MedicineController {

	private final MedicineService medicineService;

	@PostMapping("/medicine")
	@ApiOperation(value = "약 생성", notes = "사용자에게 약의 정보를 받아서 약을 생성한다.")
	@ApiImplicitParam(name = "medicine", value = "sample\n\n "
			+ "{\"name\":\"오메가 3\", \"brandId\":1, \"categoryId\": 1}", required = true, dataTypeClass = MedicineForm.class, paramType = "body")
	public ResponseEntity<Object> createMember(@RequestBody MedicineForm medicineform) {
		return medicineService.createMedicine(medicineform);
	}

	@GetMapping("/medicines")
	@ApiOperation(value = "약 조회", notes = "사용자에게 키워드를 받아서 조회한다.")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "프", required = false, dataTypeClass = String.class),
			@ApiImplicitParam(name = "brandId", value = "1", required = true, dataTypeClass = Long.class),
			@ApiImplicitParam(name = "categoryId", value = "1", required = true, dataTypeClass = Long.class) })

	public ResponseEntity<Object> getMedicines(String name, Long brandId, Long categoryId) {
		return medicineService.getMedicines(name, brandId, categoryId);
	}

	@GetMapping("/brand")
	@ApiOperation(value = "브랜드 조회", notes = "사용자에게 브랜드의 이름을 받아서 브랜드를 조회한다.")
	@ApiImplicitParam(name = "name", value = "sample\n\n " + "트", required = true, dataTypeClass = String.class)
	public ResponseEntity<Object> getBrands(String name) {
		return medicineService.getBrands(name);
	}

	@PostMapping("/brand")
	@ApiOperation(value = "브랜드 생성", notes = "사용자에게 브랜드의 정보를 받아서 브랜드를 생성한다.")
	@ApiImplicitParam(name = "brand", value = "sample\n\n "
			+ "{\"name\":\"기타\"}", required = true, dataTypeClass = Medicine.class, paramType = "body")
	public ResponseEntity<Object> createBrand(@RequestBody Brand brand) {
		return medicineService.createBrand(brand);
	}

	@GetMapping("/category")
	@ApiOperation(value = "카테고리 조회", notes = "모든 카테고리를 조회한다.")
	public ResponseEntity<Object> getCategories() {
		return medicineService.getCategories();
	}

	@PostMapping("/category")
	@ApiOperation(value = "카테고리 생성", notes = "사용자에게 카테고리의 정보를 받아서 카테고리를 생성한다.")
	@ApiImplicitParam(name = "category", value = "sample\n\n "
			+ "{\"name\":\"기타\"}", required = true, dataTypeClass = Category.class, paramType = "body")
	public ResponseEntity<Object> createCategory(@RequestBody Category category) {
		return medicineService.createCategory(category);
	}

}
