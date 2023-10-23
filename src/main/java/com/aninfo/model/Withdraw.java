package com.aninfo.model;

import javax.persistence.*;
import com.aninfo.model.Transaction;

@Entity
public class Withdraw extends Transaction{
    
    public Withdraw(){
    }

    public Withdraw(Long cbu, Double amount){
        this.type = "Withdraw";
        this.amount = amount;
        this.associatedCbu = cbu;
    }
}