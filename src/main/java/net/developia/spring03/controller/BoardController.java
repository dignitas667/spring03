package net.developia.spring03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.log4j.Log4j;
import net.developia.spring03.dto.BoardDTO;
import net.developia.spring03.service.BoardService;

@Log4j
@Controller
public class BoardController {

	private BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("insert")
	public String insertBoard() {
		return "insert";
	}	
	
	@PostMapping("insert")
	public String insertBoard(@ModelAttribute BoardDTO boardDTO,
		Model model) {
		
		log.info(boardDTO.toString());
		try {
			boardService.insertBoard(boardDTO);
			return "redirect:list";
		} catch (Exception e) {
			model.addAttribute("msg", "입력 에러");
			model.addAttribute("url", "javascript:history.back();");
			return "result";
		}
	}
}
