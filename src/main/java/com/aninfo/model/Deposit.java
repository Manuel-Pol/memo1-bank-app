package com.aninfo.model;

import javax.persistence.*;
import com.aninfo.model.Transaction;


@Entity
public class Deposit extends Transaction{
    
    public Deposit(){
    }

    public Deposit(Long cbu, Double amount){
        this.type = "Deposit";
        this.amount = amount;
        this.associatedCbu = cbu;
    }
}