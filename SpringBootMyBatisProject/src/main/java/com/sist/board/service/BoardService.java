package com.sist.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.board.vo.BoardVO;

@Service
public interface BoardService {

	public List<BoardVO> boardListData(Map map);
	public int boardTotalPage();
	public int boardRowCount();
	public void boardInsert(BoardVO vo);
	public BoardVO boardDetailData(int no);
	public BoardVO boardUpdateData(int no); // update => mapper 없이 service부터 작성
	public String boardGetPassword(int no);
	public void boardUpdate(BoardVO vo);
	public void boardDelete(int no);
}
