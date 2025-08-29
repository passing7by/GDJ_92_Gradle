package com.example.demo.board.notice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.board.BoardVO;

//JUnit 테스트
//Repository 사용

@SpringBootTest
@Transactional
class NoticeRepositoryTest {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Test
	void test() throws Exception {
		
		NoticeVO boardVO = new NoticeVO();
		boardVO.setBoardTitle("title1");
		boardVO.setBoardContents("contents1");
		boardVO.setBoardWriter("writer1");
//		boardVO.setBoardNum(2L);
		
		NoticeFileVO noticeFileVO = new NoticeFileVO();
		boardVO.addNoticeFileVO(noticeFileVO);
		
//		boardVO = noticeRepository.save(boardVO);
		
		boardVO = noticeRepository.save(boardVO);
		
		
		
		assertNotNull(boardVO);
		
	}
	
	@Test
	void test2() {
		Optional<NoticeVO> result = noticeRepository.findById(3L);	// Repository에서 넣은 타입
		NoticeVO noticeVO = result.get();
		System.out.println("==============================");
		System.out.println(noticeVO);
		
		assertNotNull(noticeVO);
	}
	
	@Test
	void test3() {
		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoardNum(6L);
		noticeRepository.deleteById(7L);
//		noticeRepository.delete(noticeVO);
	}
	
	@Test
	void test4() throws Exception {
//		Pageable pageable = (Pageable) PageRequest.of(0, 2, Sort.by("board_num").descending());
		
		List<NoticeVO> list = noticeRepository.findByBoardTitleLike("%t%");
		System.err.println(list);
		
	}
}