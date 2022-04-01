package net.developia.spring03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import net.developia.spring03.dto.BoardDTO;
import net.developia.spring03.service.BoardService;

@Log4j
@Controller
@RequestMapping("board/{pg}")
public class BoardController {

	private BoardService boardService;
	
	@Value("${pageSize}")
	private long pageSize;
	
	@Value("${blockSize}")
	private long blockSize;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("insert")
	public String insertBoard() {
		return "board.insert";
	}	
	
	@PostMapping("insert")
	public String insertBoard(@ModelAttribute BoardDTO boardDTO,
		Model model) {
		
		log.info(boardDTO.toString());
		try {
			boardService.insertBoard(boardDTO);
			return "redirect:../1/";
		} catch (Exception e) {
			model.addAttribute("msg", "입력 에러");
			model.addAttribute("url", "javascript:history.back();");
			return "result";
		}
	}
	
	@GetMapping("/")
	public String list(@PathVariable long pg, 
		Model model) throws Exception {
		
		try {
			long recordCount = boardService.getBoardCount();
			long pageCount = recordCount / pageSize;
			if (recordCount % pageSize != 0) pageCount++;
			
			List<BoardDTO> list = boardService.getBoardListPage(pg);

			long startPage = (pg - 1)/blockSize*blockSize+1;
			long endPage   = startPage + blockSize - 1;
			if (endPage > pageCount) endPage = pageCount;
			
			model.addAttribute("list", list);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("pg", pg);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("recordCount", recordCount);
			model.addAttribute("pageSize", pageSize);
			return "board.list";
		} catch (Exception e) {
			model.addAttribute("msg", "list 출력 에러");
			model.addAttribute("url", "index");
			return "result";
		}
	}
}
