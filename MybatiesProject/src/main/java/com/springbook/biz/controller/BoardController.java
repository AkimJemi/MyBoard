package com.springbook.biz.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
@SessionAttributes("board")
public class BoardController {

	@Autowired
	BoardService boardDAO;

	int total;
	int offset;
	String board1;

	@RequestMapping("*board.do")
	public String FirstMethod(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String path = request.getServletPath();
		HttpSession session = request.getSession();
		StringBuffer url = request.getRequestURL();
		System.out.println("path : " + path);
		System.out.println("session : " + session);
		System.out.println("url : " + url);
		// 1
		if (path.contains("Aboard")) board1 = "a";
		else if (path.contains("Bboard")) board1 = "b";
		else if (path.contains("Cboard")) board1 = "c";
		else board1 = null;

		return "getBoardList.do";
	}

	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		System.out.println("BoardController ===>  searchConditionMap 실행 ");
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}

	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO vo) {
		System.out.println("BoardController ===>  insertBoard 실행 ");
		boardDAO.insertBoard(vo);
		return "getBoardList.do";
	}

	@RequestMapping("/autoInsert.do")
	public String autoInsert(int auto, BoardVO vo, HttpSession session) {
		System.out.println("자동삭제 갯수 : " + auto);
		if (auto >= 1000) {
			session.invalidate();
			return "login.jsp";
		} else {
			vo.setAuto(auto);
			System.out.println("BoardController ===>  autoInsert 실행 ");
			boardDAO.autoInsert(vo);
			return "getBoardList.do";
		}

	}

	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println("BoardController ===>  updateBoard 실행 ");
		System.out.println("번호 : " + vo.getSeq() + ", 제목 : " + vo.getTitle() + ", 작성자 : " + vo.getWriter() + ", 내용 : "
				+ vo.getContent() + ", 등록일 : " + vo.getRegdate() + ", 조회수 : " + vo.getCnt());
		boardDAO.updateBoard(vo);
		return "getBoardList.do";
	}

	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		System.out.println("BoardController ===>  deleteBoard 실행 ");
		boardDAO.deleteBoard(vo);
		return "getBoardList.do";
	}

	@RequestMapping("/autoDelete.do")
	public String autodelele(BoardVO vo, int auto) {
		System.out.println("BoardController ===>  autodelete 실행 ");
		if (this.total != 0) {
			vo.setAuto(auto);
			int highestSeq = boardDAO.getHighestSeq();
			vo.setHighestSeq(highestSeq);
			boardDAO.autodelete(vo, highestSeq);
		}
		return "getBoardList.do";
	}

	@RequestMapping("/deleteAll.do")
	public String deleteAll(BoardVO vo) {
		System.out.println("BoardController ===>  deleteAll 실행 ");
		boardDAO.deleteAll(vo);
		return "getBoardList.do";
	}

	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		System.out.println("BoardController ===>  getBoard 실행 ");
		model.addAttribute("board", boardDAO.getBoard(vo)); // -> BoardServiceImpl -> BoardDAOMybatis
		boardDAO.viewCount(vo);
		return "getBoard.jsp";
	}
//	@RequestMapping("/getBoard.do")
//	public String getBoard(@RequestParam(value = "error", required = false) String error, BoardVO vo, Model model) {
//		BoardVO mboard = boardDAO.getBoard(vo);
//		if (!(error == null || error.equals(""))) {
//			cntChk = 0;
//		} else if (cntChk <= 0) {
//			boardDAO.updateCnt(mboard);
//		} else {
//			cntChk = 0;
//		}
//		model.addAttribute("board", boardDAO.getBoard(vo));
//		return "getBoard.jsp";
//	}

//   @RequestMapping("/getBoardList.do")
//   public class PagingDAOMybatis(PagingVO vo, Model model
//   		, @RequestParam(value="nowPage", required=false)String nowPage
//   		, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
//   		
//   		int total = boardService.countBoard();
//   		if (nowPage == null && cntPerPage == null) {
//   			nowPage = "1";
//   			cntPerPage = "5";
//   		} else if (nowPage == null) {
//   			nowPage = "1";
//   		} else if (cntPerPage == null) { 
//   			cntPerPage = "5";
//   		}
//   		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
//   		model.addAttribute("paging", vo);
//   		model.addAttribute("viewAll", boardService.selectBoard(vo));
//   		return "board/boardPaging";
//   	}

	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo,
			@RequestParam(value = "searchCondition", defaultValue = "TITLE", required = false) String condition,
			@RequestParam(value = "searchKeyword", defaultValue = "", required = false) String keyword, Model model,
			String nowPage, String cntPerPage) {

		if (board1 == null) board1 = "previous";
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "5";
		}
		vo.setBoard1(board1);
		int total = boardDAO.countBoard(vo);
		int offset = ((Integer.parseInt(nowPage) - 1) * Integer.parseInt(cntPerPage));
		this.total = total;
		this.offset = offset;
		vo = new BoardVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage), offset);
		if (keyword.equals("")) {
			System.out.println("검색 기능 미실행");
		} else {
//			System.out.println("검색 조건 : " + condition);
//			System.out.println("검색 단어 : " + keyword);
			vo.setSearchCondition(condition);
			vo.setSearchKeyword(keyword);
		}
		System.out.println("test6");
		vo.setBoard1(board1);
		model.addAttribute("paging", vo);
		model.addAttribute("viewAll", boardDAO.getBoardList(vo));
//		model.addAttribute("boardList", boardDAO.getBoardList(vo));
		// 어떻게 Mybatis까지 가는가
//		System.out.println(boardDAO.getBoardList(vo));
		return "getBoardList.jsp";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String autoLogin(@RequestParam(value = "userRole", required = false) String userRole, UserVO vo,
			UserDAO userDAO, HttpSession session) {
		System.out.println("userRole : " + userRole);
		if (userRole.equals("관리자")) {
			System.out.println("관리자 자동 로그인");
			vo.setId("1");
			vo.setPassword("1");
			System.out.println("vo : " + vo);
		} else {
			System.out.println("이용자 자동 로그인");
			vo.setId("2");
			vo.setPassword("2");
			System.out.println("vo : " + vo);
		}
		UserVO user = userDAO.getUser(vo);
		session.setAttribute("userName", user.getName());
		session.setAttribute("userId", user.getId());
		session.setAttribute("userRole", user.getRole());
		session.setAttribute("userPassword", user.getPassword());
		System.out.println(vo.getId() + "," + vo.getPassword());
		return "getBoardList.do";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDAO, HttpSession session) {
		System.out.println("BoardController ===>  login 실행 ");
		System.out.println("vo : " + vo);
		UserVO user = userDAO.getUser(vo);
		if (user != null) {
			session.setAttribute("userName", user.getName());
			session.setAttribute("userId", user.getId());
			session.setAttribute("userRole", user.getRole());
			session.setAttribute("userPassword", user.getPassword());
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println("BoardController ===>  logout 실행 ");
		session.invalidate();
		return "login.jsp";
	}

}