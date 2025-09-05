package com.example.demo.member;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@ToString // TODO 수정
@Entity
@Table(name = "role")
public class RoleVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleNum;
	private String roleName;
	
	@OneToMany(mappedBy = "roleVO")
	private List<MemberRoleVO> list;
	
}
