package gulp.rest.medicine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineForm {
	private String name;

	private Long categoryId;
	
	private Long brandId;
}
