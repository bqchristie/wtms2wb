package com.agilea.integration.wb.jobs.model;

import org.easybatch.core.record.GenericRecord;
import org.easybatch.core.record.Record;
import org.easybatch.core.mapper.RecordMapper;
import org.easybatch.jdbc.JdbcRecord;

import java.sql.ResultSet;

public class BalanceRecordMapper implements RecordMapper<JdbcRecord, Record<BalanceRecord>> {

    private String balanceType;

    public Record<BalanceRecord> processRecord(JdbcRecord jdbcRecord) throws Exception {
        ResultSet resultSet = jdbcRecord.getPayload();
        BalanceRecord balanceRecord = new BalanceRecord(balanceType);
        balanceRecord.setWin(resultSet.getString(1));
        balanceRecord.setAdp(resultSet.getDouble(2));
        return new GenericRecord<BalanceRecord>(jdbcRecord.getHeader(), balanceRecord);
    }

    public String getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }
}
