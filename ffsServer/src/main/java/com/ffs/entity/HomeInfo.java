package com.ffs.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HomeInfo {
    private int id;
    private BigDecimal payMoney;
    private BigDecimal incomMoney;
    private String date;


}
