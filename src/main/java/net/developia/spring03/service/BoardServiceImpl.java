package net.developia.spring03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import net.developia.spring03.dao.BoardDAO;
import net.developia.spring03.dto.BoardDTO;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {

	private BoardDAO boardDAO;

	@Value("${pageSize}")
	private int pageSize;


	public BoardServiceImpl(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public void insertBoard(BoardDTO boardDTO) throws Exception {
		boardDAO.insertBoard(boardDTO);
	}
	
	@Override
	public List<BoardDTO> getBoardList() throws Exception {
		return boardDAO.getBoardList();
	}
	
	@Override
	public BoardDTO getDetail(long no) throws Exception {
		if (no == -1) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		boardDAO.updateReadcount(no);
		BoardDTO boardDTO = boardDAO.getDetail(no);
		if (boardDTO == null) {
			throw new RuntimeException(no + "번 글이 존재하지 않습니다.");
		}
		return boardDTO;
	}
	
	@Override
	public void deleteBoard(BoardDTO boardDTO) throws Exception {
		if(boardDAO.deleteBoard(boardDTO) == 0) {
			throw new RuntimeException(
				"해당하는 게시물이 없거나 비밀번호가 틀립니다.");
		}
	}
	
	@Override
	public void updateBoard(BoardDTO boardDTO) throws Exception {
		if(boardDAO.updateBoard(boardDTO) == 0) {
			throw new RuntimeException(
				"해당하는 게시물이 없거나 비밀번호가 틀립니다.");
		}
	}

	@Override
	public List<BoardDTO> getBoardListPage(long pg) throws Exception {
		long startNum = (pg - 1) * pageSize + 1;
		long endNum   = pg * pageSize;
	
		return boardDAO.getBoardListPage(startNum, endNum);
	}
	
	@Override
	public long getBoardCount() throws Exception {
		return boardDAO.getBoardCount();
	}
}
