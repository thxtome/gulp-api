package gulp.rest.medicine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import gulp.rest.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
	
	@Id @GeneratedValue
	@Column(name="brand_id")
	private Long id;
	
	@Column(unique=true)
	private String name;
}


