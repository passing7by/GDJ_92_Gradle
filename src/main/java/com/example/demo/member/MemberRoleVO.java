package com.example.demo.member;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
//@ToString // TODO 수정
@Table(name = "member_role")
@IdClass(MemberRolePK.class)
public class MemberRoleVO {
	
	// 단일키
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long memberRoleNum;
	
	// 복합키
//	@EmbeddedId
//	private MemberRolePK memberRolePK;
	
	@Id
	private String username;
	@Id
	private Long roleNum;
	
	@ManyToOne
	@JoinColumn(name = "username")
	private MemberVO memberVO;
	
	@ManyToOne
	@JoinColumn(name = "roleNum")
	private RoleVO roleVO;
	
}
