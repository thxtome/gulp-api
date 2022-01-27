package gulp.batch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gulp.rest.medicine.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MedicineScheduler {

    private final MedicineRepository medicineRepository;
    
    @Transactional
    @Scheduled(cron = "*/30 * * * * *")
    public void countDailyDose() {
    	String yesterday = LocalDate.now().minus(1, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    	medicineRepository.updateMedicineDoseCount(yesterday);

    }

}