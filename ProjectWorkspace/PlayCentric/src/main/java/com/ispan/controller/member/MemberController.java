package com.ispan.controller.member;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ispan.bean.member.MemView;
import com.ispan.bean.member.Member;
import com.ispan.dao.member.MemberService;

@Controller
@SessionAttributes(names = "descript")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/MemberManage")
	public String listMembers(Model model, SessionStatus status) {
		List<MemView> members = memberService.findAllMembers();
		model.addAttribute("members", members);
		status.setComplete();
		return "member/memberSheet";
	}
	
	@GetMapping("/MemberManage/search")
	public String searchMembers(@RequestParam Map<String, String> searchMap, Model model, SessionStatus status) {
		for(Object entry : searchMap.entrySet()) {
			@SuppressWarnings("unchecked")
			Entry<String,String> e = (Entry<String,String>) entry;
			System.out.println(e.getKey()+":"+e.getValue());
		}
		
		List<MemView> members = memberService.selectMembers(searchMap);
		if (members==null) {
			members = memberService.findAllMembers();
			model.addAttribute("descript", "查無資料");
			status.setComplete();
		}
		model.addAttribute("members", members);
		return "member/memberSheet";
	}
	
	@RequestMapping(path = "/DelectMember", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteMemberById(@RequestParam("id") String id, Model model) {
		int count = memberService.deleteMember(Integer.parseInt(id));
		model.addAttribute("descript", count>0? "刪除"+count+"筆資料!":"刪除失敗!");
		return "redirect:/MemberManage";
	}
	
	@PostMapping("/InsertMember")
	public String insertMember(@ModelAttribute Member member, Model model) {
		int count = 0;
		try {
			setUpMember(member);
			count += memberService.saveMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("descript", count>0? "新增"+count+"筆資料!":"新增失敗!");
		return "redirect:/MemberManage";
	}
	
	@PostMapping("/UpdateMember")
	public String updateMember(@ModelAttribute Member member, Model model) {
		int count = 0;
		try {
			setUpMember(member);
			count += memberService.updateMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("descript", count>0? "更新"+count+"筆資料!":"更新失敗!");
		return "redirect:/MemberManage";
	}
	
	private void setUpMember(Member member) {
		if (member.getRole().isEmpty()) {
			member.setRole("member");
		}
		if (member.getAccount().isEmpty()) {
			member.setAccount("defAcc");
		}
		if (member.getPassword().isEmpty()) {
			member.setPassword("123");
		}
		if (member.getBirthday().isEmpty()) {
			member.setBirthday(null);
		}
		if (member.getPhone().isEmpty()) {
			member.setPhone(null);
		}
		if (member.getAccomAcnt().isEmpty()) {
			member.setAccomAcnt(null);
		}
	}
}
