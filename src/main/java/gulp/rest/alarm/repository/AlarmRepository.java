package gulp.rest.alarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gulp.rest.member.model.Member;

@Repository
public interface AlarmRepository extends JpaRepository <Member, Long> {
}
