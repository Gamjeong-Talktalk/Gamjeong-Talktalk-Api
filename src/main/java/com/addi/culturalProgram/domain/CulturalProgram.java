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

    @Column(nullable=false)
    private String facilityName;

    @Column(nullable=false)
    private String address;

    @Column(nullable=false, length=20)
    private String phoneNumber;

    @Column(nullable=false)
    private String programName;

    @Column(nullable=false)
    private LocalDate startDate;

    @Column(nullable=false)
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
