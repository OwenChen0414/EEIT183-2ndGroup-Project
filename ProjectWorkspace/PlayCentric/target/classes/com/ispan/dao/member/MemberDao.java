package com.ispan.dao.member;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ispan.bean.member.MemView;
import com.ispan.bean.member.Member;

public class MemberDao implements IMemberDao {
	private Session session;
	private static final String[] columes = { "memId", "account", "password", "email", "nickname", "memName",
			"birthday", "phone", "addres", "sso", "accomAcnt", "consumption", "registDate", "lastLoginTime",
			"roles", "levels" };

	public MemberDao(Session session) {
		this.session = session;
	}

	// 新增方法
	@Override
	public int saveMember(Member member) {
		int count = 0;
		session.persist(member);
		session.flush();
		count++;
		return count;
	}

	// 刪除特定條件下的資料
	@Override
	public int deleteMember(int id) {
		int count = 0;
		Member originMember = session.get(Member.class, id);
		if (originMember != null) {
			session.remove(originMember);
			session.flush();
			count++;
		}
		return count;
	}

	// 更新單筆資料
	@Override
	public int updateMember(Member member) {
		int count = 0;
		Member originMember = session.get(Member.class, member.getMemId());
		if (originMember != null) {
			session.merge(member);
			session.flush();
			count++;
		}
		return count;
	}

	// 查詢單筆資料
	@Override
	public MemView selectMember(int Id) {
		Query<MemView> query = session.createQuery("from MemView Where memId = ?1", MemView.class);
		query.setParameter(1, Id);
		return query.uniqueResult();
	}
	// 多條件查詢
	@SuppressWarnings("unchecked")
	@Override
	public List<MemView> selectMembers(Map<String, String> searchList) {
		String hqlCommon = "FROM memview WHERE";
		List<String> list = Arrays.asList("account","email","nickname","mem_name","phone","addres");
		int n = 1;
		for(Object entry : searchList.entrySet()) {
			Entry<String, String> e = (Entry<String, String>) entry;
			if (e.getValue() == null || e.getValue().equals("")) {
				continue;
			} else if (list.contains(e.getKey())) {
				hqlCommon += String.format(" %s LIKE(?%d) AND", e.getKey(),n++);
				continue;
			}
			hqlCommon += String.format(" %s = ?%d AND", e.getKey(), n++);
		}
		hqlCommon = hqlCommon.substring(0,hqlCommon.lastIndexOf(n>0?"AND":"WHERE"));
		hqlCommon += " ORDER BY memId";
		System.out.println(hqlCommon);
		
		Query<MemView> query = session.createQuery(hqlCommon, MemView.class);
		
		n = 1;
		for (Object entry : searchList.entrySet()) {
			Entry<String, String> e = (Entry<String, String>) entry;
			if (e.getValue() == null || e.getValue().equals("")) {
				continue;
			} else if (list.contains(e.getKey())) {
				query.setParameter(n++, "%" + (String) e.getValue() + "%");
				continue;
			}
			query.setParameter(n++, (String) e.getValue());
		}

		return query.list();
	}

	// 查詢整張表
	@Override
	public List<MemView> findAllMembers() {
		Query<MemView> query = session.createQuery("from MemView ORDER BY memId", MemView.class);
		return query.list();
	}
	
	//登入確認
	@Override
	public Member checkLogin(Member member) {
		String hqlCommon = "From Member Where %s = ?1 AND %s = ?2";
		hqlCommon = String.format(hqlCommon, columes[1], columes[2]);
		Query<Member> query = session.createQuery(hqlCommon, Member.class);
		query.setParameter(1, member.getAccount());
		query.setParameter(2, member.getPassword());
		member = query.uniqueResult();
		if (member == null) {
			return member;
		}
		
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
		member.setLastLoginTime(now.format(formatDateTime));
		return member;
	}
}
