package com.addi.guardian.dto.response;


import com.addi.guardian.domain.Guardian;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class LoginToGuardianResponse {

    Long id;
    String name;
    String phoneNumber;
    String organization;
    String registrationCode;

    public LoginToGuardianResponse toResponse(Guardian guardian){
        return LoginToGuardianResponse.builder()
                .id(guardian.getId())
                .name(guardian.getName())
                .phoneNumber(guardian.getPhoneNumber())
                .organization(guardian.getOrganization())
                .registrationCode(guardian.getRegistrationCode())
                .build();
    }

}
