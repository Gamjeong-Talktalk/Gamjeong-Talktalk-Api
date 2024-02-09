package com.addi.guardian.dto.request;


import com.addi.guardian.domain.Guardian;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpToGuardianRequest {

    String loginId;
    String password;
    String name;
    String phoneNumber;
    String organization;

    public Guardian toEntity(){
        return Guardian.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .organization(organization)
                .build();
    }

}
