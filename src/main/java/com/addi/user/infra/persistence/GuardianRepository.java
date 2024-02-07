package com.addi.user.infra.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.addi.user.domain.Guardian;

@Transactional
public interface GuardianRepository extends JpaRepository<Guardian, Long> {
	Optional<Guardian> findByPhoneNumber(String phoneNumber);
	Optional<Guardian> findByRegistrationCode(String registrationCode);
	Optional<Guardian> findByIDAndPassword(String ID, String password);
}
