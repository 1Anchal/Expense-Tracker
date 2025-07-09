package com.expense.expense.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Expense {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long id;

    BigDecimal amount;
    String description;

    public Expense(BigDecimal amount,String description){
        this.amount=amount;
        this.description=description;
    }
    public Expense(){

    }
    
}
