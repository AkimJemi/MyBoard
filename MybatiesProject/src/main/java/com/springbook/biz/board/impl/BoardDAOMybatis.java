package com.springbook.biz.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

@Repository
public class BoardDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;


	public void insertBoard(BoardVO vo) {
		System.out.println("BoardDAOMybatis ===> insertBoard() 기능처리");
		mybatis.insert("BoardDAO.insertBoard", vo);
	}

	// 글수정
	public void updateBoard(BoardVO vo) {
		System.out.println("BoardDAOMybatis ===> updateBoard() 기능처리");
		System.out.println("BoardVO : " + vo);
		mybatis.update("BoardDAO.updateBoard", vo);

	}

	// 글삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("BoardDAOMybatis ===> updateBoard() 기능처리");
		mybatis.delete("BoardDAO.deleteBoard", vo);

	}

	public void viewCount(BoardVO vo) {
		System.out.println("BoardDAOMybatis ===> viewCount() 기능처리");
		mybatis.update("BoardDAO.viewCount", vo);
	}

	// 글상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("BoardDAOMybatis ===> getBoard() 기능처리");
		System.out.println("DAOMybatis : " + vo.getSeq());
		return (BoardVO) mybatis.selectOne("BoardDAO.getBoard", vo);
	}

	// 글목록 조회
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("BoardDAOMybatis ===> getBoardList() 기능처리");
		System.out.println("board : " + "'" + vo.getBoard1() + "'");
		if (vo.getSearchKeyword() == null) {
			return mybatis.selectList("BoardDAO.getBoardList", vo);
		} else {
			return mybatis.selectList("BoardDAO.SearchBoard", vo);
		}
	}

	public void deleteAll(BoardVO vo) {
		System.out.println("BoardDAOMybatis ===> deleteAll() 기능처리");
		mybatis.delete("BoardDAO.deleteAll1", vo);
		System.out.println("BoardDAOMybatis.java deleteAll delete 쿼리 실행");
		mybatis.delete("BoardDAO.deleteAll2", vo);
		System.out.println("BoardDAOMybatis.java deleteAll seq 초기화");

	}

	public void autoInsert(BoardVO vo) {
		System.out.println("BoardDAOMybatis ===> autoInsert() 기능처리");
		System.out.println("자동 생성된 갯수 : " + vo.getAuto());
		for (int i = 1; i <= vo.getAuto(); i++) {
			mybatis.insert("BoardDAO.autoInsert", vo);
		}
	}

	public int countBoard(BoardVO vo) {
		System.out.println("board1 : " + vo.getBoard1());
		return mybatis.selectOne("BoardDAO.countBoard");
	}

	public void autodelete(BoardVO vo, int highestSeq) {
		System.out.println("BoardDAOMybatis ===> autoInsert() 기능처리");
		System.out.println("자동 생성된 갯수 : " + vo.getAuto());
		for (int i = 1; i <= vo.getAuto(); i++) {
			vo.setSeq(highestSeq);
			highestSeq--;
			if (highestSeq >= 0) {
				mybatis.insert("BoardDAO.deleteBoard", vo);
			}
		}
	}

	public int getHighestSeq() {
		return mybatis.selectOne("BoardDAO.getHighestSeq");
	}

//	public List<BoardVO> selectBoard(PagingVO vo) {
//		mybatis.selectList("BoardDAO.getBoardList", vo);
//		return null;
//	}

}
