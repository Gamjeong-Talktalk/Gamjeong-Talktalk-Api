package com.addi.user.infra.persistence;

import java.util.Optional;

import com.addi.user.domain.Guardian;
import com.addi.user.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByGuardianAndPhoneNumber(Guardian guardian, String phoneNumber);

}
