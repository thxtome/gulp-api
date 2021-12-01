package gulp.rest.medicine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Brand {
	
	@Id @GeneratedValue
	@Column(name="brand_id")
	private Long id;
	
	@Column(unique=true)
	private String name;
}


