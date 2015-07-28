package com.agilea.integration.wb.jobs.model;

import org.easybatch.core.api.Record;
import org.easybatch.core.api.RecordMapper;
import org.easybatch.core.api.RecordMappingException;
import org.easybatch.jdbc.JdbcRecord;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceRecordMapper<T> implements RecordMapper<BalanceRecord> {

    private String balanceType;

    public BalanceRecord mapRecord(Record record) throws RecordMappingException {
        JdbcRecord jdbcRecord = (JdbcRecord)record;
        ResultSet resultSet = (ResultSet)jdbcRecord.getPayload();
        BalanceRecord balanceRecord = new BalanceRecord(balanceType);
        try {
            balanceRecord.setWin(resultSet.getString(1));
            balanceRecord.setAdp(resultSet.getDouble(2));
        } catch (SQLException e) {
            throw new RecordMappingException("Trouble mapping" + e.getMessage());
        }
        return balanceRecord;
    }

    public String getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }
}
