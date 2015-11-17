package com.agilea.integration.wb.jobs.util;

import com.agilea.integration.wb.jobs.model.BalanceRecord;
import com.opencsv.CSVWriter;
import org.apache.log4j.Logger;
import org.easybatch.core.processor.RecordProcessingException;
import org.easybatch.core.processor.RecordProcessor;
import org.easybatch.core.record.Record;

import java.util.ArrayList;
import java.util.List;

public class CSVProcessor implements RecordProcessor<Record<BalanceRecord>,Record<BalanceRecord>> {

    private CSVWriter writer = null;

    Logger log = Logger.getLogger(CSVProcessor.class);

    static List records = new ArrayList();

    private CSVProcessor() {
    }

    public CSVProcessor(CSVWriter writer) {
        this.writer = writer;
    }

    public Record<BalanceRecord> processRecord(Record<BalanceRecord> record) throws RecordProcessingException {
        writer.writeNext(record.getPayload().getCSVValues());
        return record;
    }
}
