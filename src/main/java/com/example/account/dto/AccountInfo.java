package com.example.account.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountInfo { // Account의 몇 가지 특정 정보를 특정해서 주는 Class
    private String accountNumber;
    private Long balance;


}
