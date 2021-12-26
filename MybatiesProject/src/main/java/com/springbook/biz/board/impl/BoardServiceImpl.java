package com.springbook.biz.board.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {

//	@Autowired
//	private BoardDAO boardDAO;
//	p220-p223
	@Autowired
	private BoardDAOMybatis boardDAO;

	@Override
	public void insertBoard(BoardVO vo) {
		System.out.println("BoardServiceImpl ===>  insertBoard 실행 ");
		boardDAO.insertBoard(vo);
	}

	@Override
	public void updateBoard(BoardVO vo) {
		System.out.println("BoardServiceImpl ===>  updateBoard 실행 ");
		System.out.println("BoardVO : " + vo);
		boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		System.out.println("BoardServiceImpl ===>  deleteBoard 실행 ");
		boardDAO.deleteBoard(vo);
	}

	@Override
	public void viewCount(BoardVO vo) {
		System.out.println("BoardServiceImpl ===>  viewCount 실행 ");
		boardDAO.viewCount(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("BoardServiceImpl ===>  getBoard 실행 ");
		return boardDAO.getBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("BoardServiceImpl ===>  getBoardList 실행 ");
		return boardDAO.getBoardList(vo);
	}

	@Override
	public void deleteAll(BoardVO vo) {
		System.out.println("BoardServiceImpl ===>  deleteAll 실행 ");
		boardDAO.deleteAll(vo);
	}

	@Override
	public void autoInsert(BoardVO vo) {
		System.out.println("BoardServiceImpl ===>  autoInsert 실행 ");
		boardDAO.autoInsert(vo);
	}

	@Override
	public void autodelete(BoardVO vo, int highestSeq) {
		
		System.out.println("BoardServiceImpl ===>  deleteBoard 실행 ");
		boardDAO.autodelete(vo, highestSeq);
	}

	@Override
	public int countBoard(BoardVO vo){
		System.out.println("test4");
		return boardDAO.countBoard(vo);
	}

	@Override
	public int getHighestSeq() {
		System.out.println("BoardServiceImpl ===>  getHighestSeq 실행getHighestSeq : " + boardDAO.getHighestSeq());
		return boardDAO.getHighestSeq();
	}

//	@Override
//	public List<BoardVO> selectBoard(PagingVO vo) {
//		return boardDAO.selectBoard(vo);
//	}

}
