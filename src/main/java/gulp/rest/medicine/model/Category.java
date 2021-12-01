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
public class Category {
	
	@Id @GeneratedValue
	@Column(name="category_id")
	private Long id;
	
	@Column(unique=true)
	private String name;
}

