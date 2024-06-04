package com.ispan.dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ispan.bean.member.Member;
import com.ispan.util.member.Check;
import com.ispan.util.member.CreateConnection;

public class MemberDao {
	private Connection connection;
	private static final String[] columes = { "mem_id", "account", "passwords", "email", "nickname", "mem_name",
			"birthday", "phone", "addres", "sso", "accom_acnt", "consumption", "regist_date", "last_login_time",
			"roles", "levels" };

	public MemberDao(CreateConnection createConnection) {
		connection = createConnection.getConnection();
	}

	// 完善SQL指令
	private void updateInfo(Member member, PreparedStatement statement) throws SQLException {
		statement.setString(1, member.getAccount());
		statement.setString(2, member.getPassword());
		statement.setString(3, member.getEmail());
		statement.setString(4, member.getNickName());
		statement.setString(5, member.getMemName());
		statement.setString(6, member.getBirthday());
		statement.setString(7, member.getPhone());
		statement.setString(8, member.getAddress());
		statement.setString(9, member.getSso());
		statement.setString(10, member.getAccomAccount());
		statement.setString(11, member.getConsumption());
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		statement.setString(12, now.format(formatDate));
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
		statement.setString(13, now.format(formatDateTime));
		statement.setString(14, member.getRole());
	}

	// 新增方法
	public int saveMembers(Member member) {
		int count = 0;
		if (selectMember(columes[0], member.getId()) != null) {
			return count;
		}
		String sqlCommon = String.format(
					  "INSERT INTO members(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)" 
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
					columes[1], columes[2], columes[3], columes[4],
					columes[5], columes[6], columes[7], columes[8],
					columes[9], columes[10], columes[11], columes[12],
					columes[13], columes[14]);
		try (PreparedStatement statement = connection.prepareStatement(sqlCommon)) {
			updateInfo(member, statement);
			statement.execute();
			count++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 刪除特定條件下的資料
	public int deleteMember(String colume, String value) {
		int count = 0;
		if (!Check.containsColume(columes, colume)) {
			return count;
		}
		String sqlCommon = String.format("DELETE FROM members WHERE %s = ?;", colume);
		try (PreparedStatement statement = connection.prepareStatement(sqlCommon)) {
			statement.setString(1, value);
			statement.execute();
			count++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 更新單筆資料
	public int updateMember(Member member) {
		int count = 0;
		String sqlCommon = String.format("UPDATE members SET %s=?,%s=?,%s=?,%s=?,%s=?"
				+ ",%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=? WHERE %s=?;",
				columes[1], columes[2], columes[3], columes[4],
				columes[5], columes[6], columes[7], columes[8],
				columes[9], columes[10], columes[11], columes[12],
				columes[13], columes[14], columes[0]);
		try (PreparedStatement statement = connection.prepareStatement(sqlCommon)) {
			statement.setString(15, member.getId());
			updateInfo(member, statement);
			statement.execute();
			count++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 產生Members物件
	private Member getMember(ResultSet result) throws SQLException {
		Member member = new Member();
		member.setId(result.getString(columes[0]));
		member.setAccount(result.getString(columes[1]));
		member.setPassword(result.getString(columes[2]));
		member.setEmail(result.getString(columes[3]));
		member.setNickName(result.getString(columes[4]));
		member.setMemName(result.getString(columes[5]));
		member.setBirthday(result.getString(columes[6]));
		member.setPhone(result.getString(columes[7]));
		member.setAddress(result.getString(columes[8]));
		member.setSso(result.getString(columes[9]));
		member.setAccomAccount(result.getString(columes[10]));
		member.setConsumption(result.getString(columes[11]));
		member.setRegistDate(result.getString(columes[12]));
		member.setLastLogin(result.getString(columes[13]));
		member.setRole(result.getString(columes[14]));
		member.setLevel(result.getString(columes[15]));
		return member;
	}

	// 查詢單筆資料
	public Member selectMember(String colume, String value) {
		if (!Check.containsColume(columes, colume)) {
			return null;
		}
		String sqlCommon = String.format("SELECT * FROM memview WHERE %s = ?;", colume);
		Member member = null;
		try (PreparedStatement statement = connection.prepareStatement(sqlCommon)) {
			statement.setString(1, value);
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					member = getMember(result);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	// 多條件查詢
	public List<Member> selectMembers(Map<String, String> searchList) {
		String sqlCommon = "SELECT * FROM memview WHERE";
		List<String> list = Arrays.asList("account","email","nickname","mem_name","phone","addres");
		boolean hasCond = false;
		for(Object entry : searchList.entrySet()) {
			Entry e = (Entry) entry;
			if (e.getValue() == null || e.getValue().equals("")) {
				continue;
			} else if (list.contains(e.getKey())) {
				sqlCommon += String.format(" %s LIKE(?) AND", e.getKey());
				hasCond = true;
				continue;
			}
			sqlCommon += String.format(" %s = ? AND", e.getKey());
			hasCond = true;
		}
		sqlCommon = sqlCommon.substring(0,sqlCommon.lastIndexOf(hasCond?"AND":"WHERE"));
		sqlCommon += " ORDER BY mem_id;";
		System.out.println(sqlCommon);
		
		List<Member> memberList = new ArrayList<Member>();
		try (PreparedStatement statement = connection.prepareStatement(sqlCommon)) {
			
			 int n=1;
			for(Object entry : searchList.entrySet()) {
				Entry e = (Entry) entry;
				if (e.getValue() == null || e.getValue().equals("")) {
					continue;
				} else if (list.contains(e.getKey())) {
					statement.setString(n++, "%"+(String)e.getValue()+"%");
					continue;
				}
				statement.setString(n++, (String)e.getValue());
			}
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				memberList.add(getMember(result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;
	}

	// 查詢整張表
	public List<Member> findAllMembers() {
		String sqlCommon = "SELECT * FROM memview ORDER BY mem_id;";
		List<Member> memberList = new ArrayList<Member>();
		try (PreparedStatement statement = connection.prepareStatement(sqlCommon)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				memberList.add(getMember(result));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;
	}
	
	//登入確認
	public Member checkLogin(Member member) {
		String sqlCommon = "SELECT * From memview Where %s = ? AND %s = ?;";
		sqlCommon = String.format(sqlCommon, columes[1], columes[2]);
		try (PreparedStatement statement = connection.prepareStatement(sqlCommon)) {
			statement.setString(1, member.getAccount());
			statement.setString(2, member.getPassword());
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				member = getMember(result);
			} else {
				member.setRole("guest");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
		member.setLastLogin(now.format(formatDateTime));
		updateMember(member);
		return member;
	}
}
