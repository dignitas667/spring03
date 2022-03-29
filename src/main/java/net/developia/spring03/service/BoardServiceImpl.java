package net.developia.spring03.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import net.developia.spring03.dao.BoardDAO;
import net.developia.spring03.dto.BoardDTO;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {

	private BoardDAO boardDAO;

	public BoardServiceImpl(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public void insertBoard(BoardDTO boardDTO) throws Exception {
		try {
			boardDAO.insertBoard(boardDTO);
		} catch (Exception e) {
			log.info(e.toString());
			throw e;
		}		
	}
	
	@Override
	public List<BoardDTO> getBoardList() throws Exception {
		try {
			return boardDAO.getBoardList();
		} catch (Exception e) {
			log.info(e.toString());
			throw e;
		}
	}
}
