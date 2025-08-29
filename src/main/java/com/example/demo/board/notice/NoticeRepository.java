package com.example.demo.board.notice;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeVO, Long> {
	
//	public NoticeFileVO save(NoticeFileVO noticeFileVO) throws Exception;
	
	public List<NoticeVO> findByBoardTitleLike(String search) throws Exception;
//	public List<NoticeVO> findByBoardTitleLike(String search, Pageable pageable) throws Exception;
}
