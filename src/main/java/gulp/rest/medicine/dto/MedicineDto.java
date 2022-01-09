package gulp.rest.medicine.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class MedicineDto {
	@QueryProjection
	public MedicineDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	private Long id;
	private String name;
}
