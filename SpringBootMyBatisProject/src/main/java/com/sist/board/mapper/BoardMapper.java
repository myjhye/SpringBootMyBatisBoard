package com.sist.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.board.vo.*;

@Repository
@Mapper
public interface BoardMapper {

	public List<BoardVO> boardListData(Map map);
	public int boardTotalPage();
	public void boardInsert(BoardVO vo);
	public void hitIncrement(int no);
	public BoardVO boardDetailData(int no);
	public String boardGetPassword(int no);
	public void boardUpdate(BoardVO vo);
	public void boardDelete(int no);

}
