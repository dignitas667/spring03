package net.developia.spring03.service;

import java.util.List;

import net.developia.spring03.dto.BoardDTO;

public interface BoardService {

	void insertBoard(BoardDTO boardDTO) throws Exception;

	List<BoardDTO> getBoardList() throws Exception;

	BoardDTO getDetail(long no) throws Exception;

	void deleteBoard(BoardDTO boardDTO) throws Exception;

}
