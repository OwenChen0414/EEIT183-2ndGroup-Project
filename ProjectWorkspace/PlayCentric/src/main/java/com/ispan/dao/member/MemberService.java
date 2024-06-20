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
		return 0;
	}

	
	public int deleteMember(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public MemView selectMember(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<MemView> selectMembers(Map<String, String> searchList) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<MemView> findAllMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Member checkLogin(Member member) {
		return memberDao.checkLogin(member);
	}
	
	
}
