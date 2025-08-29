package com.example.demo.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
// 각 메서드에 @ResponseBody를 붙이는 대신 컨트롤러에 @RestController를 붙이기
@CrossOrigin
@RestController // 이 컨트롤러 안의 모든 메서드는 json으로 응답하도록 함
@RequestMapping("/notice/**")
public class NoticeController {
	
	/* restful test
	// list
	@GetMapping("/notice/{pageNum}/{kind}/{serch}")
//	@ResponseBody // 응답을 json 형태로 보냄
	public String notice() throws Exception {
		return "list";
	}
	
	// detail
	@GetMapping("/notice/{boardNum}") // 뒤의 값은 계속 바뀌기 때문에 변수 취급
//	@ResponseBody // 응답을 json 형태로 보냄
//	public String detail(@PathVariable Long num) throws Exception { // @PathVariable: 
		public String notice(@PathVariable("num") Long num, @PathVariable("kind") String kind) throws Exception { // 변수명과 pathVariable명이 다를 경우 매핑 설정 가능
		
		System.err.println(num);
		
		return "detail";
	}
	
	// insert
	@PostMapping("/notice")
	public void notice(VO vo) {
		
	}
	
	// update
	@PutMapping("/notice/{boardNum}")
	public void notice(VO vo) {
		
	}
	
	// delete
	@DeleteMapping("/notice/{}")
	public void notice() {
		
	}
	*/
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("{boardNum}")
	public NoticeVO detail(@PathVariable("boardNum") Long boardNum) throws Exception {
		System.out.println(boardNum);
		return noticeService.detail(boardNum);
	}
	
//	@GetMapping("list")
//	public List<NoticeVO> list() throws Exception {
//		return noticeService.list();
//	}
	
	@GetMapping("list")
	public Page<NoticeVO> list(@PageableDefault(size = 2, sort = "boardNum", direction = Direction.DESC) Pageable pageable) throws Exception {
		
		return noticeService.list(pageable);
	}
	
}
