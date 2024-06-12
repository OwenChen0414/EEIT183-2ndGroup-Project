package com.ispan.dao.member;

import java.sql.Connection;

import com.ispan.util.member.CreateConnection;

public class MemLevelDao {
	private Connection connection;
	private String sqlCommon = "";
	private static final String[] columes = {
			"level_id",
			"levels",
			"min_amount",
			"max_amount"};
	
	public MemLevelDao(CreateConnection createConnection) {
		connection =  createConnection.getConnection();
	}

}
