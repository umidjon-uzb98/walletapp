package com.example.walletapp.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name cann't be blank")
    @Size(min = 2, max = 30)
    private String name;
    @Size(min = 2, max = 30)
    private String accountNumber;
    @Size(max = 100)
    private String description;
    @Min(1)
    @Max(3)
    private Integer priority; // 1=High; 2=Medium; 3=Low
    private Double currentBalance;

    @PrePersist
    public void setBalance(){
//        this.currentBalance = new Double(0); // 'Double(double)' is deprecated
        this.currentBalance = Double.valueOf(0);
    }
}
