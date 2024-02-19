package com.addi.member.infra.persistence;

import java.util.Optional;

import com.addi.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByIdentificationCode(String identificationCode);

}
