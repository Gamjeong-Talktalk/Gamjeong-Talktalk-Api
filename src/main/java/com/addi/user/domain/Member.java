package com.addi.user.domain;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import com.addi.global.auditing.BaseEntity;

import com.addi.user.domain.constants.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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

	@Column(nullable=false, length=30)
	private String name;

	@Column(name="phone_number", nullable=false, length=20, unique=true)
	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Gender gender;

	@Column(nullable=false)
	private int age;

	@Column(nullable=false)
	private String address;

	@Column(name="depression_index", precision = 4, scale = 2)
	@Setter
	private BigDecimal depressionIndex;



	@Builder
	public Member(Guardian guardian, String name, String phoneNumber, Gender gender, int age, String address) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.age = age;
		this.address = address;
	}

}
