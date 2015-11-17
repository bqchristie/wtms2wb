package com.agilea.integration.wb.jobs.model;

import org.easybatch.core.record.GenericRecord;
import org.easybatch.core.record.Record;
import org.easybatch.core.mapper.RecordMapper;
import org.easybatch.core.mapper.RecordMappingException;
import org.easybatch.jdbc.JdbcRecord;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceRecordMapper implements RecordMapper<JdbcRecord, Record<BalanceRecord>> {

    private String balanceType;

    public Record<BalanceRecord> processRecord(JdbcRecord jdbcRecord) throws RecordMappingException {
        ResultSet resultSet = jdbcRecord.getPayload();
        BalanceRecord balanceRecord = new BalanceRecord(balanceType);
        try {
            balanceRecord.setWin(resultSet.getString(1));
            balanceRecord.setAdp(resultSet.getDouble(2));
        } catch (SQLException e) {
            throw new RecordMappingException("Trouble mapping" + e.getMessage());
        }
        return new GenericRecord<BalanceRecord>(jdbcRecord.getHeader(), balanceRecord);
    }

    public String getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }
}
