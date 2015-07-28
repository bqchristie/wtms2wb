package com.agilea.integration.wb.jobs.util;

import com.agilea.integration.wb.jobs.model.BalanceRecord;
import com.opencsv.CSVWriter;
import org.apache.log4j.Logger;
import org.easybatch.core.api.RecordProcessingException;
import org.easybatch.core.api.RecordProcessor;

import java.util.ArrayList;
import java.util.List;

public class CSVProcessor implements RecordProcessor<BalanceRecord,BalanceRecord> {

    private CSVWriter writer = null;

    Logger log = Logger.getLogger(CSVProcessor.class);

    static List records = new ArrayList();

    private CSVProcessor() {
    }

    public CSVProcessor(CSVWriter writer) {
        this.writer = writer;
    }

    public BalanceRecord processRecord(BalanceRecord balanceRecord) throws RecordProcessingException {
        writer.writeNext(balanceRecord.getCSVValues());
        return balanceRecord;
    }
}
