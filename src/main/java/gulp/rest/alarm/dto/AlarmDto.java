package gulp.rest.alarm.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import gulp.rest.medicine.dto.MedicineDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
public class AlarmDto {
	
	@QueryProjection
	public AlarmDto(Long id, LocalTime time, String day, boolean isEaten, List<MedicineDto> medicineList) {
		this.id = id;
		this.time = time;
		this.day = day;
		this.isEaten = isEaten;
		this.medicineList = medicineList;
	}

	private Long id;
	
	private LocalTime time;

	private String day;

	private boolean isEaten;
	
	private List<MedicineDto> medicineList = new ArrayList<>();
}
