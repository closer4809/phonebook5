package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller

public class PhoneController {

	// 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("[PhoneController.list]");

		// dao사용
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList);

		// model담기
		model.addAttribute("personList", personList);

		// view
		return "/WEB-INF/views/list.jsp";
	}

	// 쓰기폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("[PhoneController.writeForm]");

		return "/WEB-INF/views/writeForm.jsp";

	}

	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personvo) {
		System.out.println("[PhoneController.write]");

		// @ModelAttribute --> 기본생성자 + setter
		System.out.println(personvo);

		// dao사용할것
		PhoneDao phonedao = new PhoneDao();

		// dao의 personinsert() 이용해서 데이터 저장
		phonedao.personInsert(personvo);

		// view --> 리다이렉트 (화면이 필요없다, 일만 해주면댐)
		return "redirect:/list";

	}

	// 삭제
	@RequestMapping(value = "delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("id") int pId) {
		System.out.println("[PhoneController.Delete]");

		// Dao 생성(메소드이용)
		PhoneDao pDao = new PhoneDao();
		pDao.personDelete(pId);

		// view -->리다이렉트

		return "redirect:/list";
	}

	// 수정폼

	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(Model model, @RequestParam("id") int personId) {
		System.out.println("[PhoneController.updateForm]");

		PhoneDao phoneDao = new PhoneDao();
		PersonVo personvo = phoneDao.getPerson(personId);

		model.addAttribute("personvo", personvo);
		
		return "/WEB-INF/views/updateForm.jsp";

	}
	
	
	// 수정

	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@ModelAttribute PersonVo personvo) {
		System.out.println("[PhoneController.update]");

		// @ModelAttribute --> 기본생성자 + setter
		System.out.println(personvo);

		// dao사용할것
		PhoneDao phonedao = new PhoneDao();

		// dao의 personinsert() 이용해서 데이터 저장
		phonedao.personUpdate(personvo);

		// view --> 리다이렉트 (화면이 필요없다, 일만 해주면댐)
		
		return "redirect:/list";

	}

	
	/*
	 * // 쓰기 //파라미터가 있을때도 있고 없을때도 있을때
	 * 
	 * @RequestMapping(value = "/write", method = { RequestMethod.GET,
	 * RequestMethod.POST }) public String write(@RequestParam("name") String name,
	 * 
	 * @RequestParam("hp") String hp,
	 * 
	 * @RequestParam(value = "company", required =false, defaultValue="-1") String
	 * company) { System.out.println("[PhoneController.write]");
	 * 
	 * 
	 * PersonVo personVo = new PersonVo(name, hp, company);
	 * System.out.println(personVo);
	 * 
	 * 
	 * return "";
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @RequestMapping(value = "/write", method = { RequestMethod.GET,
	 * RequestMethod.POST }) public String write(@RequestParam("name") String name,
	 * 
	 * @RequestParam("hp") String hp,
	 * 
	 * @RequestParam("company") String company) {
	 * System.out.println("[PhoneController.write]"); System.out.println(name);
	 * System.out.println(hp); System.out.println(company);
	 * 
	 * PersonVo personVo = new PersonVo(name, hp, company);
	 * System.out.println(personVo);
	 * 
	 * 
	 * return "";
	 * 
	 * 
	 * }
	 * 
	 * //쓰기
	 * 
	 * @RequestMapping(value="/board/read/{no}", method= {RequestMethod.GET,
	 * RequestMethod.POST}) public String read(@PathVariable("no") int boardNo) {
	 * System.out.println("PathVariable [read]");
	 * 
	 * 
	 * 
	 * 
	 * System.out.println(boardNo); return""; }
	 * 
	 */

	@RequestMapping(value = "/test")
	public String test() {
		System.out.println("test");

		return "/WEB-INF/views/test.jsp"; // DispatcherServlet 야 /WEB-INF/views/test.jsp 에 포워드해

	}

}
