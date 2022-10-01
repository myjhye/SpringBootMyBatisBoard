package com.sist.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.board.service.BoardService;
import com.sist.board.vo.BoardVO;

@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	//==
	
	// 컨트롤러 순서
	// 1. 요청 확인 @getmapping ("/detail")
	// 2. 요청 데이터 받기 board_detail(int no, Model model)
	// 3. 결과를 모아서 model.addattribute("vo", vo);
	// 4. 보여줄 화면으로 이동 return "detail";
	
	
	@GetMapping("/")
	public String board_list(String page, Model model)
	{
		if(page == null)
			page = "1";
	
		int curpage = Integer.parseInt(page);
		int rowSize = 10;
		int start = (rowSize*curpage)-rowSize;
		int totalpage = service.boardTotalPage();
		
		Map map = new HashMap();
		map.put("start", start);
		List<BoardVO> list = service.boardListData(map);
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		
		int count = service.boardRowCount();
		count = count-((curpage*rowSize)-rowSize);
		model.addAttribute("count", count);
		
		return "list";
	}
	
	
	// 작성 폼
	@GetMapping("/insert")
	public String board_insert()
	{
		return "insert";
	}
	
	
	// 작성 처리
	@PostMapping("/insert_ok")
	public String board_insert_ok(BoardVO vo)
	{
		service.boardInsert(vo);
		return "redirect:/"; // list로 return
	}
	
	
	// 상세보기 폼
	@GetMapping("/detail")
	public String board_detail(int no, Model model)
	{
		BoardVO vo = service.boardDetailData(no);
		model.addAttribute("vo", vo);
		
		return "detail";
	}
	

	// 수정 폼
	@GetMapping("/update")
	public String board_update(int no, Model model)
	{
		BoardVO vo = service.boardUpdateData(no);
		model.addAttribute("vo", vo);
		
		return "update";
	}
	
	
	
	// 삭제 폼
	@GetMapping("/delete")
	public String board_delete(int no, Model model)
	{
		model.addAttribute("no", no); // 게시물 번호 view에 전송
		
		return "delete";
	}
	
	
}
