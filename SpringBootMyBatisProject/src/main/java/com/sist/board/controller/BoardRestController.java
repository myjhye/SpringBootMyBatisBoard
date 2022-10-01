package com.sist.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.board.service.*;
import com.sist.board.vo.*;

/*
 * @Controller => 화면 변경
 * @RestController => JSP 안에 필요한 데이터만 전송이 가능
 */

@RestController
public class BoardRestController {

	@Autowired
	private BoardService service; // 같은 객체를 쓴다 (메모리 주소가 동일) => 싱글톤(실무 70% 차지) => 메모리 누수 방지
	//==
	
	
	// 수정 처리 => 자바스크립트 전송, 일반 문자열, JSON
	@PostMapping("/update_ok")
	public String board_update_ok(BoardVO vo)
	{
		String result = "";
		
		String db_pwd = service.boardGetPassword(vo.getNo()); // db에서 가져오는 비번
		if(db_pwd.equals(vo.getPwd()))
		{
			service.boardUpdate(vo); // 비번 맞으면 수정
			result = "<script>"
					+ "location.href=\"/detail?no=" + vo.getNo() + "\";" // 해당 상세보기 페이지로 return
					+ "</script>";
		}
		else
		{
			result = "<script>"
					+ "alert(\"비밀번호가 틀립니다!\");"
					+ "history.back();"
					+ "</script>";
		}
		
		return result;
	}
	
	
	
	// 삭제 처리
	@PostMapping("/delete_ok")
	public String board_delete_ok(int no, String pwd)
	{
		String result = "";
		
		String db_pwd = service.boardGetPassword(no); // 입력된 비번
		if(db_pwd.equals(pwd))
		{
			service.boardDelete(no); // 비번 맞으면 수정
			result = "<script>"
					+ "location.href=\"/\";" // 해당 상세보기 페이지로 return
					+ "</script>";
		}
		else
		{
			result = "<script>"
					+ "alert(\"비밀번호가 틀립니다!\");"
					+ "history.back();"
					+ "</script>";
		}
		
		return result;
	}
	
	
	
	
}
