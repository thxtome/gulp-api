package gulp.rest.member.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Member {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@ApiModelProperty
	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long memberId;
	
	@Column(unique=true)
	private String email;

	private String nickname;
	
	private String password;
		
	@ColumnDefault("'https://firebasestorage.googleapis.com/v0/b/medicine-cc1f6.appspot.com/o/face.png?alt=media'")
	private String imgPath;
	
	@ManyToOne(targetEntity=Grade.class, fetch=FetchType.LAZY) // (1)
	@JoinColumn(name="grade_id") // (2)
	private Long gradeId;
}
