package com.cydeo.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder //chaining setters... flexibility
public class Transaction {

    private UUID sender;  //it could be Account... but later
    private UUID receiver;
    private BigDecimal amount;
    private String message;
    private Date createDate;
}
