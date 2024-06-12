package com.ispan.dao.member;

import java.util.List;
import java.util.Map;

import com.ispan.bean.member.MemView;
import com.ispan.bean.member.Member;

public interface IMemberDao {

	public int saveMember(Member member);
	
	public int deleteMember(int id);
	
	public int updateMember(Member member);
	
	public MemView selectMember(int id);
	
	public List<MemView> selectMembers(Map<String, String> searchList);
	
	public List<MemView> findAllMembers();
	
	public Member checkLogin(Member member);
}
