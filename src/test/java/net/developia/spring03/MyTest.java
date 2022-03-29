package net.developia.spring03;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import net.developia.spring03.dto.BoardDTO;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/*-context.xml"})
public class MyTest {
	
	@Test
	public void testBoardDTO() {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNo(1);
		boardDTO.setTitle("제목");
		boardDTO.setName("이름");
		boardDTO.setPassword("1111");
		log.info(boardDTO.toString());
		assertNotNull(boardDTO);
	}
	
	@Test(timeout = 2000)
	public void testTimeout() {
		BoardDTO boardDTO = new BoardDTO();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boardDTO.setNo(1);
		boardDTO.setTitle("제목");
		boardDTO.setName("이름");
		boardDTO.setPassword("1111");
		log.info(boardDTO.toString());
		assertNotNull(boardDTO);
	}
	
	@Test
	@Ignore
	public void createBoardDTO2() {
		log.info("실행하지 않음");
	}
}
