package gulp.rest.member.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DynamicInsert
public class Grade {
	
	@Id @GeneratedValue
	private Long gradeId;
	
	@NotNull
	private String title;
		
	@ColumnDefault("'https://firebasestorage.googleapis.com/v0/b/medicine-cc1f6.appspot.com/o/face.png?alt=media'")
	private String imgPath;
}
