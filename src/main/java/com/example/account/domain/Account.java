package com.example.account.domain;

import com.example.account.exception.AccountException;
import com.example.account.type.ErrorCode;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Account extends BaseEntity {

    @ManyToOne
    private AccountUser accountUser; // DB의 User라고 이미 존재하고 있는 게 있기 때문에 여기와 충돌하지 않기 위해 AccountUser로 지칭한다.
    private String accountNumber;


    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    private Long balance;

    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;


    public void useBalance(Long amount) {
        if(amount > balance){
            throw new AccountException(ErrorCode.AMOUNT_EXCEED_BALANCE);
        }
        balance -= amount;
    }

    public void cancelBalance(Long amount) {
        if(amount < 0){
            throw new AccountException(ErrorCode.INVALID_REQUEST);
        }
        balance += amount;
    }
}
