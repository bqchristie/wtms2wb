package com.agilea.integration.wb.tasklet;

import com.opencsv.CSVWriter;
import com.agilea.integration.wb.jobs.util.CSVJobEventListener;
import com.agilea.integration.wb.jobs.util.CSVProcessor;
import org.apache.log4j.Logger;
import org.easybatch.core.job.Job;
import org.easybatch.core.job.JobExecutor;
import org.easybatch.core.mapper.RecordMapper;
import org.easybatch.core.job.JobReport;
import org.easybatch.core.job.JobBuilder;
import org.easybatch.jdbc.JdbcRecordReader;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.SQLException;


public class JDBCtoCSVTasklet implements Tasklet {

    private String fileName;
    private DriverManagerDataSource dataDource;
    private String sql;
    private String taskName;
    private RecordMapper recordMapper;

    Logger log = Logger.getLogger(JDBCtoCSVTasklet.class);

    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        final CSVWriter writer = new CSVWriter(new FileWriter(this.getFileName()), ',',CSVWriter.NO_QUOTE_CHARACTER);

        Job job = JobBuilder.aNewJob().named(taskName + " Export Processor")
                .reader(new JdbcRecordReader(this.getConnection(), this.getSql()))
                .mapper(this.getRecordMapper())
                .processor(new CSVProcessor(writer))
                .jobListener(new CSVJobEventListener(writer))
                .build();

        JobReport report = JobExecutor.execute(job);

        //TODO write this somehwere useful
        log.info(report.toString());
        return RepeatStatus.FINISHED;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setDataDource(DriverManagerDataSource dataDource) {
        this.dataDource = dataDource;
    }

    public DriverManagerDataSource getDataDource() {
        return dataDource;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

    public RecordMapper getRecordMapper() {
        return recordMapper;
    }

    public void setRecordMapper(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Connection getConnection() throws SQLException {
        return this.getDataDource().getConnection();
    }
}
