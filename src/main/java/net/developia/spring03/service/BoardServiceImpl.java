package net.developia.spring03.service;

import org.springframework.stereotype.Service;

import net.developia.spring03.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardDAO boardDAO;

	public BoardServiceImpl(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	
}
