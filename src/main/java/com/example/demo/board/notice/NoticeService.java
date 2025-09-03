package com.example.demo.board.notice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.board.BoardVO;

@Service
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	
	public NoticeVO detail(Long id) throws Exception {
		Optional<NoticeVO> result = noticeRepository.findById(id);
		
		return result.orElseThrow();
	}

	public List<NoticeVO> list() {
		return noticeRepository.findAll();
	}
	
	public Page<NoticeVO> list(Pageable pageable) {
		Page<NoticeVO> page = noticeRepository.findAll(pageable);
		
		return page;
	}
	
	public NoticeVO add(NoticeVO noticeVO) {
		return noticeRepository.save(noticeVO);
	}
}
