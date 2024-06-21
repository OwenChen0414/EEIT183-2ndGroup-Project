package com.ispan.dao.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.bean.member.MemView;
import com.ispan.bean.member.Member;

import jakarta.transaction.Transactional;

@Service @Transactional
public class MemberService {

	@Autowired
	private MemberDao memberDao;
	
	public int saveMember(Member member) {
		return memberDao.saveMember(member);
	}

	
	public int deleteMember(int id) {
		return memberDao.deleteMember(id);
	}

	
	public int updateMember(Member member) {
		return memberDao.updateMember(member);
	}

	
	public MemView selectMember(int id) {
		return memberDao.selectMember(id);
	}

	
	public List<MemView> selectMembers(Map<String, String> searchList) {
		return memberDao.selectMembers(searchList);
	}

	
	public List<MemView> findAllMembers() {
		return memberDao.findAllMembers();
	}

	
	public Member checkLogin(Member member) {
		return memberDao.checkLogin(member);
	}
	
	
}
