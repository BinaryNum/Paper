package com.javalec.paper.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalec.paper.command.Command;
import com.javalec.paper.command.Download;
import com.javalec.paper.command.IndexCommand;
import com.javalec.paper.command.MemberIdCheck;
import com.javalec.paper.command.MemberInsertCommand;
import com.javalec.paper.command.MemberLoginCommand;
import com.javalec.paper.command.MemberLogoutCommand;
import com.javalec.paper.command.MemberMypageCommand;
import com.javalec.paper.command.NaverTranslation;
import com.javalec.paper.command.PBasketCommand;
import com.javalec.paper.command.PContentCommand;
import com.javalec.paper.command.PListCommand;
import com.javalec.paper.command.PSearchCommand;
import com.javalec.paper.command.PWriteCommand;
import com.javalec.paper.command.PaperRandomData;
import com.javalec.paper.command.SandMail;
import com.javalec.paper.command.AutoComplete;
import com.javalec.paper.command.BContentCommand;
import com.javalec.paper.command.BDeleteCommand;
import com.javalec.paper.command.BListCommand;
import com.javalec.paper.command.BModifyCommand;
import com.javalec.paper.command.BReplyCommand;
import com.javalec.paper.command.BReplyViewCommand;
import com.javalec.paper.command.BWriteCommand;
import com.javalec.paper.command.BasketDeleteCommand;
import com.javalec.paper.command.CCommentDeleteCommand;
import com.javalec.paper.command.CCommentWriteCommand;
import com.javalec.paper.util.Constant;

@Controller
public class PController {
	Command command;
	
	public JdbcTemplate template;
	
	@Autowired
	public void setTemPlate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}
	
	@RequestMapping("/")
	public String root(Model model) {
		command = new IndexCommand();
		command.execute(model);
		return "index";
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		command = new IndexCommand();
		command.execute(model);
		return "index";
	}
	
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	
	//커뮤니티 관련
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("write_view()");
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("write()");
		model.addAttribute("request",request);
		command = new BWriteCommand();
		command.execute(model);
		return "redirect:list";
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		System.out.println("list()");
		model.addAttribute("request",request);
		command = new BListCommand();
		command.execute(model);
		return "list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view()");
		model.addAttribute("request",request);
		command = new BContentCommand();
		command.execute(model);
		return "content_view";
	}
	
	@RequestMapping("/content_modify")
	public String content_modify(HttpServletRequest request, Model model) {
		System.out.println("content_modify()");
		model.addAttribute("request",request);
		command = new BContentCommand();
		command.execute(model);
		return "content_modify";
	}
	
	@RequestMapping(method= RequestMethod.POST, value = "/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modify()");
		model.addAttribute("request",request);
		command = new BModifyCommand();
		command.execute(model);
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");
		model.addAttribute("request",request);
		command = new BDeleteCommand();
		command.execute(model);
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_view()");
		model.addAttribute("request",request);
		command = new BReplyViewCommand();
		command.execute(model);
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply()");
		model.addAttribute("request",request);
		command = new BReplyCommand();
		command.execute(model);
		return "redirect:list";
	}
	
	//커뮤니티 댓글관련
	@RequestMapping("/community_comment_write")
	public String community_comment_write(HttpServletRequest request, Model model) {
		System.out.println("community_comment_write()");
		model.addAttribute("request",request);
		command = new CCommentWriteCommand();
		command.execute(model);
		return "redirect:content_view?bId="+request.getParameter("bId");
	}
	
	@RequestMapping("/community_comment_delete")
	public String community_comment_delete(HttpServletRequest request, Model model) {
		System.out.println("community_comment_delete()");
		model.addAttribute("request",request);
		command = new CCommentDeleteCommand();
		command.execute(model);
		return "redirect:content_view?bId="+request.getParameter("bId");
	}
	
	//논문관련
	@RequestMapping("/paper_list")
	public String paper_list(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		command = new PListCommand();
		command.execute(model);
		return "paper_list";
	}
	
	@RequestMapping("/paper_write_view")
	public String paper_write_view() {
		return "paper_write_view";
	}
	
	@RequestMapping("/paper_content_view")
	public String paper_content_view(HttpServletRequest request, Model model) {
		System.out.println("paper_content_view()");
		model.addAttribute("request",request);
		command = new PContentCommand();
		command.execute(model);
		return "paper_content_view";
	}
	
	@RequestMapping("/paper_write")
	public String paper_write(HttpServletRequest request, Model model) {
		System.out.println("paper_write()");
		model.addAttribute("request",request);
		command = new PWriteCommand();
		command.execute(model);
		return "redirect:paper_list";
	}
	
	@RequestMapping("/Download")
	public String download(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("download()");
		model.addAttribute("request",request);
		model.addAttribute("response",response);
		command = new Download();
		command.execute(model);
		return null;
	}
	
	
	//회원관련
	@RequestMapping("/checkUser")
	public String checkUser() {
		System.out.println("checkUser()");
		return "checkUser";
	}
	
	@RequestMapping("/signup")
	public String signup(HttpServletRequest request, Model model) {
		System.out.println("signup()");
		model.addAttribute("request",request);
		command = new MemberInsertCommand();
		command.execute(model);
		return "redirect:index";
	}
	
	@RequestMapping("/doublecheck")
	public String doublecheck(HttpServletRequest request,HttpServletResponse response, Model model) {
		System.out.println("doublecheck()");
		model.addAttribute("request",request);
		model.addAttribute("response",response);
		command = new MemberIdCheck();
		command.execute(model);
		return null;
	}
	
	@RequestMapping("/Logincheck")
	public String Logincheck(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("Logincheck()");
		model.addAttribute("request",request);
		model.addAttribute("response",response);
		command = new MemberLoginCommand();
		command.execute(model);
		return null;
	}
	@RequestMapping("/RandomPaper")
	public String RandomPaper(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("RandomPaper()");
		model.addAttribute("request",request);
		model.addAttribute("response",response);
		command = new PaperRandomData();
		command.execute(model);
		return null;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {
		System.out.println("logout()");
		model.addAttribute("request",request);
		command = new MemberLogoutCommand();
		command.execute(model);
		return "redirect:index";
	}
	
	@RequestMapping("/basket")
	public String basket(HttpServletRequest request, Model model) {
		System.out.println("basket()");
		model.addAttribute("request",request);
		command = new PBasketCommand();
		command.execute(model);
		return "redirect:mypage";
	}
	
	@RequestMapping("/mypage")
	public String mypage(HttpServletRequest request, Model model) {
		System.out.println("mypage()");
		model.addAttribute("request",request);
		command = new MemberMypageCommand();
		command.execute(model);
		return "mypage";
	}
	
	@RequestMapping("/basket_delete")
	public String basket_delete(HttpServletRequest request, Model model) {
		System.out.println("basket_delete()");
		model.addAttribute("request",request);
		command = new BasketDeleteCommand();
		command.execute(model);
		return "redirect:mypage";
	}
	
	//기타기능
	@RequestMapping("/Auto")
	public String auto(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("AutoComplete()");
		model.addAttribute("request",request);
		model.addAttribute("response",response);
		command = new AutoComplete();
		command.execute(model);
		return null;
	}
	
	@RequestMapping("/translate")
	public String translate(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("translate()");
		model.addAttribute("request",request);
		model.addAttribute("response",response);
		command = new NaverTranslation();
		command.execute(model);
		return null;
	}
	
	@RequestMapping("/search")
	public String search(HttpServletRequest request, Model model) {
		System.out.println("search()");
		model.addAttribute("request",request);
		command = new PSearchCommand();
		command.execute(model);
		return "paper_list";
	}
	
	//메일관련
	@RequestMapping("/SendMail")
	public String sandMail(HttpServletRequest request, Model model) {
		System.out.println("sandMail()");
		model.addAttribute("request",request);
		command = new SandMail();
		command.execute(model);
		return "codeCheck";
	}
	@RequestMapping("/Verify")
	public String verify(HttpServletRequest request, Model model) {
		System.out.println("verify()");
		String email = request.getParameter("email");
		int inputCode = Integer.parseInt(request.getParameter("code"));
		HttpSession session = request.getSession();
		int currentUserVerifyCode = (Integer)session.getAttribute("code");
		request.setAttribute("email", email);
		
		if(inputCode == currentUserVerifyCode) {
			return "signup";
		}else {
			return "codeCheck";
		}
	}
	
}
