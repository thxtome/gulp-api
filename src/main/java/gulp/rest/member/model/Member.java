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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Member {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@ApiModelProperty
	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;
	
	@Column(unique=true)
	private String email;

	private String nickname;
	
	private String password;
	
	private int straightDay;
		
	@ColumnDefault("'https://firebasestorage.googleapis.com/v0/b/medicine-cc1f6.appspot.com/o/face.png?alt=media'")
	private String imgPath;
	
	@ManyToOne(targetEntity=Grade.class, fetch=FetchType.LAZY) // (1)
	@JoinColumn(name="grade_id") // (2)
	private Long gradeId;
	
	public void encodePassword () {
		this.password =  new BCryptPasswordEncoder().encode(this.password);
	}
	
	public void addStraightDay() {
		this.straightDay += 1; 
	}
	
	public void update(String nickname, String password, String imgPath, Long gradeId) {
		this.nickname = nickname;
		this.password = password;
		this.imgPath = imgPath;
		this.gradeId = gradeId;
	}
}
