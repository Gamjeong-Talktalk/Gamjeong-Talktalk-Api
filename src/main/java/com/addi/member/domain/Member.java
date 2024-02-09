package com.addi.member.domain;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import com.addi.global.auditing.BaseEntity;

import com.addi.guardian.domain.Guardian;
import com.addi.member.domain.constants.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "MEMBER")
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {

	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="guardian_id")
	private Guardian guardian;

	@Column(nullable = false, length=6, unique = true)
	private String identificationCode;

	@Column(nullable=false, length=30)
	private String name;

	@Column(name="phone_number", nullable=false, length=20, unique=true)
	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Gender gender;

	@Column(name="birth_day",nullable=false)
	private LocalDate birthDay;

	@Column(nullable=false)
	private String address;

	@Column(name="depression_index", precision = 4, scale = 2)
	@Setter
	private BigDecimal depressionIndex;



	@Builder
	public Member(Guardian guardian, String name, String phoneNumber, Gender gender, LocalDate birthDay, String address) {
		this.identificationCode = generateRandomCode();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.birthDay = birthDay;
		this.address = address;
	}
	public String generateRandomCode() {
		return RandomStringUtils.randomAlphanumeric(6).toUpperCase();
	}
}
