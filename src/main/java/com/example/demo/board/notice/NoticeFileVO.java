package com.example.demo.board.notice;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@ToString
@Getter
@Setter
@Entity
@Table(name="notice_files")
@DynamicInsert // insert 쿼리문을 날릴 때 null 인 값은 제외하고 쿼리문을 작성
public class NoticeFileVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fileNum;
	
    // 부모 엔티티와의 다대일 관계 설정
    @ManyToOne
    @JoinColumn(name = "board_num")
    @JsonIgnore // JSON 직렬화 할 때 제외
	private NoticeVO noticeVO;
    
	private String saveName;
	private String oriName;
	@Override
	public String toString() {
		return "NoticeFileVO [fileNum=" + fileNum + ", saveName=" + saveName + ", oriName=" + oriName + "]";
	}
	
}
