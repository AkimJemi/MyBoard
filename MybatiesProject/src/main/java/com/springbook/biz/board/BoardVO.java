package com.springbook.biz.board;

import java.sql.Date;

public class BoardVO {
	// Board 객체
	private int seq, cnt;
	private String title, writer, content, searchCondition, searchKeyword, board1;
	private Date regdate;
	private int auto, highestSeq;

	
	public String getBoard1() {
		return board1;
	}

	public void setBoard1(String board1) {
		this.board1 = board1;
	}

	public int getHighestSeq() {
		return highestSeq;
	}

	public void setHighestSeq(int highestSeq) {
		this.highestSeq = highestSeq;
	}

	public int getAuto() {
		return auto;
	}

	public void setAuto(int auto) {
		this.auto = auto;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", cnt=" + cnt + ", " + searchKeyword + ", " + searchCondition + " , board1 : " + board1+ "]";
	}

//	 Page 객체
//	 현재페이지, 시작페이지, 끝페이지, 게시글 총 갯수, 페이지당 글 갯수, 마지막페이지, SQL쿼리에 쓸 start, end
	private int nowPage, startPage, endPage, total, cntPerPage, lastPage, start, end, offset;
	private int cntPage = 5;

	public BoardVO() {
	}

	public BoardVO(int total, int nowPage, int cntPerPage, int offset) {
		setNowPage(nowPage);
		setCntPerPage(cntPerPage);
		setTotal(total);
		setOffset(offset);
		calcLastPage(getTotal(), getCntPerPage());
		calcStartEndPage(getNowPage(), cntPage);
		calcStartEnd(getNowPage(), getCntPerPage());

		System.out.println("BoardVO ===> BoardVO(total, nowPage, cntPerPage, offset)실행 ");
		System.out.println(
				cntPerPage + ", total : " + total + "nowPage " + nowPage + ", cntPerPage : " + ", offset : " + offset);
	}

	// 제일 마지막 페이지 계산
	public void calcLastPage(int total, int cntPerPage) {
		setLastPage((int) Math.ceil((double) total / (double) cntPerPage));
	}

	// 시작, 끝 페이지 계산
	public void calcStartEndPage(int nowPage, int cntPerPage) {
		setEndPage(((int) Math.ceil((double) nowPage / (double) cntPerPage)) * cntPerPage);
		if (getLastPage() < getEndPage()) {
			setEndPage(getLastPage());
		}
//		setStartPage(getEndPage() - cntPerPage + 1);

		setStartPage(((int) Math.ceil((double) nowPage / (double) cntPerPage) - 1) * cntPerPage + 1);
		if (getStartPage() < 1) {
			setStartPage(1);
		}
		System.out.println("getStartPage() : " + getStartPage());
	}

	// DB 쿼리에서 사용할 start, end값 계산
	public void calcStartEnd(int nowPage, int cntPerPage) {
		setEnd(nowPage * cntPerPage);
		setStart(getEnd() - cntPerPage + 1);
//		(int total/cntPerPage +1 )*cntPerPage
//		cntPerPage : {5, 10, 15, 20}, total : 31, lastPage : 7
//		startPage : 1, 6, 11, 16, 21 ~ 24 
//		endPage : 5, 10, 15, 20, 25

//		Math.ceil(nowPage/cntPerPage)+1
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCntPerPage() {
		return cntPerPage;
	}

	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int setCntPage() {
		return cntPage;
	}

	public void getCntPage(int cntPage) {
		this.cntPage = cntPage;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String toString1() {
		return "PagingVO [nowPage=" + nowPage + ", startPage=" + startPage + ", endPage=" + endPage + ", total=" + total
				+ ", cntPerPage=" + cntPerPage + ", lastPage=" + lastPage + ", start=" + start + ", end=" + end
				+ ", cntPage=" + cntPage + "]";
	}

}
