package org.bankapp.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class BankAccount implements Comparable<BankAccount>{
    @Id
    private int actNumber;
    private String customerName;
    private double actBalance;
    private String city;
    private int branchCode;

    @Override
    public String toString() {
        return "Account Number: " + actNumber +
                ", Customer Name: " + customerName+
                ", Account Balance: " + actBalance +
                ", City: " + city +
                ", Branch Code: " + branchCode;
    }

    @Override
    public int compareTo(BankAccount o1) {
        return this.actNumber - o1.actNumber;
    }
}
