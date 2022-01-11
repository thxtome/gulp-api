package gulp.rest.alarm.repository;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static gulp.rest.alarm.model.QAlarm.alarm;
import static gulp.rest.alarm.model.QAlarmMedicine.alarmMedicine;
import static gulp.rest.medicine.model.QMedicine.medicine;
import static gulp.rest.member.model.QMember.member;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gulp.rest.alarm.dto.AlarmDto;
import gulp.rest.alarm.dto.QAlarmDto;
import gulp.rest.alarm.model.Alarm;
import gulp.rest.medicine.dto.QMedicineDto;

@Repository
public class AlarmRepositoryImpl implements CustomAlarmRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public AlarmRepositoryImpl(EntityManager entityManager) {
		this.jpaQueryFactory = new JPAQueryFactory(entityManager);
	}

	public List<AlarmDto> findAllDtoByMemberIdAndDayContains(Long memberId, String day) {
		JPQLQuery<Alarm> query = jpaQueryFactory.selectFrom(alarm);
		
		query
			.innerJoin(member)
			.on(alarm.member.id.eq(memberId))
			.leftJoin(alarmMedicine)
			.on(alarmMedicine.in(alarm.alarmMedicines))
			.leftJoin(medicine)
			.on(alarmMedicine.medicine.eq(medicine))
			.where(alarm.isRemoved.eq(false));
			
			if (StringUtils.hasText(day)) {
				query.where(alarm.day.contains(day));
			}
			
			List<AlarmDto> list = query.transform(
				groupBy(alarm.id)
				.list(
					new QAlarmDto(alarm.id, alarm.time, alarm.day, alarm.isEaten
						, list(new QMedicineDto(medicine.id, medicine.name))
					)
				)
			);
		

		
		return list;
	}

	@Override
	public AlarmDto findDtoByIdAndMemberId(Long memberId, Long alarmId) {
		JPQLQuery<Alarm> query = jpaQueryFactory.selectFrom(alarm);
		List<AlarmDto> list = query
		.innerJoin(member)
		.on(alarm.member.id.eq(memberId))
		.leftJoin(alarmMedicine)
		.on(alarmMedicine.in(alarm.alarmMedicines))
		.leftJoin(medicine)
		.on(alarmMedicine.medicine.eq(medicine))
		.where(alarm.id.eq(alarmId))
		.transform(
			groupBy(alarm.id)
			.list(
				new QAlarmDto(alarm.id, alarm.time, alarm.day, alarm.isEaten
					, list(new QMedicineDto(medicine.id, medicine.name))
				)
			)
		);

		return list.size() == 1 ? list.get(0) : null;
	}
}
