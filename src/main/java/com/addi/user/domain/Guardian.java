package com.addi.user.domain;



import com.addi.global.auditing.BaseEntity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

@Entity
@Table(name = "GUARDIAN")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Guardian extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "guardian_id")
	private Long id;

	@Column(nullable=false, length=20, unique = true)
	private String ID;

	@Column(nullable=false, length=30)
	private String password;

	@Column(nullable = false, length = 20)
	private String name;

	@Column(name="phone_number", nullable = false, length = 20, unique = true)
	private String phoneNumber;

	@Column(nullable = false, length = 20)
	private String organization;

	@Column(name="registration_code", nullable = true, length = 6, unique = true)
	private String registrationCode;

	@OneToMany(mappedBy = "guardian", fetch = FetchType.LAZY)
	private List<Member> member;

	// 생성자에서 registration_code를 랜덤한 값으로 초기화
	@Builder
	public Guardian(String ID, String password, String name, String phoneNumber, String organization) {
		this.ID = ID;
		this.password = password; // 암호화 x
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.organization = organization;
		this.registrationCode = generateRandomCode();
	}

	public String generateRandomCode() {
		return RandomStringUtils.randomAlphanumeric(6).toUpperCase();
	}
}



