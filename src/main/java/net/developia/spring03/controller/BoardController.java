package net.developia.spring03.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping("list")
	public String list(Model model) throws Exception {
		try {
			List<BoardDTO> list = boardService.getBoardList();
			model.addAttribute("list", list);
			return "list";
		} catch (Exception e) {
			model.addAttribute("msg", "list 출력 에러");
			model.addAttribute("url", "index");
			return "result";
		}
	}
	
	@GetMapping(value = "detail")
	public String detail(
			@RequestParam(defaultValue = "-1") long no,
			Model model) {
		
		try {
			BoardDTO boardDTO = boardService.getDetail(no);
			model.addAttribute("boardDTO", boardDTO);
			return "detail";
		} catch(RuntimeException e) { 
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "list");
			return "result";
		} catch (Exception e) {
			model.addAttribute("msg", "상세보기 에러");
			model.addAttribute("url", "list");
			return "result";
		}
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam long no, Model model) {
		model.addAttribute("no", no);
		return "delete";
	}
	
	@PostMapping("delete")
	public ModelAndView delete(@ModelAttribute BoardDTO boardDTO) {
		ModelAndView mav = new ModelAndView("result");
		try {
			boardService.deleteBoard(boardDTO);
			mav.addObject("msg", boardDTO.getNo() + "번 게시물이 삭제되었습니다.");
			mav.addObject("url", "list");
		} catch (Exception e) {
			mav.addObject("msg", e.getMessage());
			mav.addObject("url", "javascript:history.back();");
		}
		return mav;
	}
	
	
}
