package gulp.batch;

import java.time.LocalDate;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AlarmScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private AlarmJobConfig alarmJobConfig;

    @Scheduled(cron = "0 0 0 * * *")
    public void runJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("createDate", LocalDate.now().toString())
                .toJobParameters();

        try {

            jobLauncher.run(alarmJobConfig.alarmUpdateJob(), jobParameters);

        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException e) {

            log.error(e.getMessage());
        }
    }

}