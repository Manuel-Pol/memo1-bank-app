package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Double amount;
    public Long associatedCbu;
    public String type;

    public Transaction(){
    }

    public Transaction(Long cbu, Double amount){
        this.amount = amount;
        this.associatedCbu = cbu;
    }

    public Long getAssociatedCbu() {
        return associatedCbu;
    }

    public Double getAmount() {
        return amount;
    }

    public Long getId(){
        return id;
    }
}








// @Entity
// public class Deposit extends Transaction{
    
//     public Deposit(){
//     }

//     public Deposit(Double amount, Long cbu){
//         this.amount = amount;
//         this.cbu = cbu;
//     }
// }


// @Entity
// public class Withdraw extends Transaction{
    
//     public Withdraw(){
//     }

//     public Withdraw(Double amount, Long cbu){
//         this.amount = amount;
//         this.cbu = cbu;
//     }
// }