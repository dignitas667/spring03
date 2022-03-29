package net.developia.spring03.dao;

import java.sql.SQLException;
import java.util.List;

import net.developia.spring03.dto.BoardDTO;

public interface BoardDAO {

	void insertBoard(BoardDTO boardDTO) throws Exception;

	List<BoardDTO> getBoardList() throws Exception;

}
