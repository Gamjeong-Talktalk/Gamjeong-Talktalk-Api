package com.addi.culturalProgram.infra.persistence;

import com.addi.culturalProgram.domain.CulturalProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CulturalProgramRepository extends JpaRepository<CulturalProgram, Long> {

    List<CulturalProgram> findByEndDateAfter(LocalDate currentDate);
}
