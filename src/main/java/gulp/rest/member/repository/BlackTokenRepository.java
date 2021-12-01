package gulp.rest.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gulp.rest.member.model.BlackToken;

@Repository
public interface BlackTokenRepository extends JpaRepository <BlackToken, String> {

}
