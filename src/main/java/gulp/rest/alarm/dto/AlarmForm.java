package gulp.rest.alarm.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlarmForm {
	private Long id;
	
	private LocalTime time;

	private String day;

	private List<Long> medicineIdList = new ArrayList<Long>();
}
