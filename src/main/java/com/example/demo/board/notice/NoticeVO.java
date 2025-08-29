package com.example.demo.board.notice;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;

import com.example.demo.board.BoardVO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity	// 해당 객체가 JPA에서 관리하고 있다라는것을 정의, 필수
@Table(name="notice")	// DB에 존재하는 테이블 이름을 매핑, 생략하면 클래스명이 테이블명이 됨
@DynamicInsert // insert 쿼리문을 날릴 때 null 인 값은 제외하고 쿼리문을 작성
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class NoticeVO extends BoardVO {
	
	@OneToMany(mappedBy = "noticeVO", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // mappedBy = "변수명"
	private List<NoticeFileVO> list = new ArrayList<>();
	
	// 양방향 관계 설정
    public void addNoticeFileVO(NoticeFileVO noticeFileVO) {
    	noticeFileVO.setNoticeVO(this);
    	list.add(noticeFileVO);
    }
	
}