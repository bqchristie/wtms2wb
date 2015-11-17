package com.agilea.integration.wb.jobs.util;

import com.opencsv.CSVWriter;
import org.easybatch.core.job.JobParameters;
import org.easybatch.core.job.JobReport;
import org.easybatch.core.listener.JobListener;

import java.io.IOException;

/**
 * Created by brucechristie on 15-07-24.
 */
public class CSVJobEventListener implements JobListener {


    private CSVWriter writer = null;

    public CSVJobEventListener(CSVWriter writer) {
        this.writer = writer;
    }

    public void beforeJobStart(JobParameters jobParameters) {
    }


    public void afterJobEnd(JobReport report) {
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void onJobException(Throwable throwable) {
        //TODO: Delete file or send to deleted on completion
    }
}
