package com.agilea.integration.wb.jobs.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BalanceRecord  {


    private double adp;

    private String win;
    private String overrideEnd ="01/01/3000";
    private SimpleDateFormat outputDateFormat = new SimpleDateFormat("MM/dd/YYYY");
    private String overrideStart = outputDateFormat.format(new Date());
    private String balanceType;

    private BalanceRecord() {
    }

    public BalanceRecord(String balanceType) {
        this.balanceType = balanceType;
    }

    public double getAdp() {
        return adp;
    }

    public void setAdp(double adp) {
        this.adp = adp;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getOverrideEnd() {
        return overrideEnd;
    }

    public void setOverrideEnd(String overrideEnd) {
        this.overrideEnd = overrideEnd;
    }

    public SimpleDateFormat getOutputDateFormat() {
        return outputDateFormat;
    }

    public void setOutputDateFormat(SimpleDateFormat outputDateFormat) {
        this.outputDateFormat = outputDateFormat;
    }

    public String getOverrideStart() {
        return overrideStart;
    }


    public String[] getCSVValues() {
        return new String[]{
                this.getOverrideStart(),
                this.getOverrideEnd(),
                this.getWin(),
                balanceType + "~" + this.getAdp() +"~SET"
        };
    }
}
