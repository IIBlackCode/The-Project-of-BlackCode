package freeboard.action;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.db.FreeBoardDAO;
import member.action.Action;
import member.action.ActionForward;

public class FreeBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("FreeBoardAction 시작");
		FreeBoardDAO boarddao=new FreeBoardDAO();		
		ActionForward forward=new ActionForward();
		List boardlist=new ArrayList();
		int list_boardid;
		if(request.getParameter("main_list_end_boardid")!=null)
		{
			list_boardid=Integer.valueOf(request.getParameter("main_list_end_boardid"));
			System.out.println(list_boardid+"FreeBoardAction의 시작 id값");
		}else{
			System.out.println("main_list_end_boardid의 값이 없는 초기화면 입니다.");
			list_boardid=boarddao.getlastwrite();
			System.out.println(list_boardid+"FreeBoardAction의 시작 id값(게시판 초기화면)");
		}
		
		boardlist=boarddao.getBoardList(list_boardid);
		request.setAttribute("boardlist", boardlist);
		forward.setPath("freeboard/boardlist.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
