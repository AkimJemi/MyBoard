package com.springbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//로그인처리를 담당하는 인터셉터
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	// preHandle() : 컨트롤러보다 먼저 수행되는 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// session 객체를 가져옴
		HttpSession session = request.getSession();
		System.out.println("interceptor");
		// login처리를 담당하는 사용자 정보를 담고 있는 객체를 가져옴
		Object obj = session.getAttribute("login");
//		session.setAttribute("stsb", "disabled");
//		System.out.println("stsb : " + session.getAttribute("stsb"));
		
		if (obj == null) {
			// 로그인이 안되어 있는 상태임으로 로그인 폼으로 다시 돌려보냄(redirect)
			response.sendRedirect("login.jsp");
			// 더이상 컨트롤러 요청으로 가지 않도록false로 반환함
			return false;
		}

		// path가져오기 로직
		String path = request.getServletPath();
		System.out.println("path : "  + path);
		System.out.println("session : "  + session);
		// if문 작성하기 path가 updateBoard.do 또는 path가 deleteBoard.do인 경우만 처리되도록 조건문(if) 처리
		// 세션이름과 작성자 이름이 다른 경우는 컨트롤러 타지 못하도록 처리
		System.out.println("path writer Parameter : " + request.getParameter("writer"));
		if (path.equals("/deleteBoard.do") || path.equals("/updateBoard.do")) {
			if (!session.getAttribute("userName").equals(request.getParameter("writer"))) {
				session.setAttribute("stsb", "해당 글은 작성자 만이 수정 삭제 할 수 있습니다.");
				System.out.println("stsb : " + session.getAttribute("stsb"));
				response.sendRedirect("getBoard.do?seq=" + request.getParameter("seq")); //
				return false; 
			}
		}
	
		if(path.contains("jpit")) {
			
		}

		// preHandle의 return은 컨트롤러 요청 uri로 가도 되냐 안되냐를 허가하는 의미임
		// 따라서true로하면 컨트롤러 uri로 가게 됨.
		return true;
	}

	// 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메서드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle 실행");
		super.postHandle(request, response, handler, modelAndView);
		
	}
}
