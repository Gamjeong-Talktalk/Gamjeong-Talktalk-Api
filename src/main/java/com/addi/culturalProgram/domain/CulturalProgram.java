package com.addi.culturalProgram.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "CULTURAL_PROGRAM")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class CulturalProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="facility_name", nullable=false)
    private String facilityName;

    @Column(nullable=false)
    private String address;

    @Column(name="phone_number", nullable=false, length=20)
    private String phoneNumber;

    @Column(name="program_name", nullable=false)
    private String programName;

    @Column(name="start_date", nullable=false)
    private LocalDate startDate;

    @Column(name="end_date", nullable=false)
    private LocalDate endDate;


    @Builder
    public CulturalProgram (String facilityName, String address, String phoneNumber, String programName, LocalDate startDate, LocalDate endDate){
        this.facilityName = facilityName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.programName = programName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
