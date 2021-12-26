package com.springbook.biz.board;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface BoardService {

	// CRUD 기능의 메소드 구현
	// 글등록
	void insertBoard(BoardVO vo);

	// 글수정
	void updateBoard(BoardVO vo);

	// 글삭제
	void deleteBoard(BoardVO vo);

	void deleteAll(BoardVO vo);

	void viewCount(BoardVO vo);

	// 글상세 조회
	BoardVO getBoard(BoardVO vo);

	// 글목록 조회
	List<BoardVO> getBoardList(BoardVO vo);

	void autoInsert(BoardVO vo);

	// 게시물 총 갯수
	public int countBoard(BoardVO vo);

	void autodelete(BoardVO vo, int highestSeq);

	int getHighestSeq();

	

	// 페이징 처리 게시글 조회

}