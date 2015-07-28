package com.agilea.integration.wb.jobs.util;

import com.opencsv.CSVWriter;
import org.easybatch.core.api.event.job.JobEventListener;

import java.io.IOException;

/**
 * Created by brucechristie on 15-07-24.
 */
public class CSVJobEventListener implements JobEventListener {


    private CSVWriter writer = null;

    public CSVJobEventListener(CSVWriter writer) {
        this.writer = writer;
    }

    public void beforeJobStart() {
    }


    public void afterJobEnd() {
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
