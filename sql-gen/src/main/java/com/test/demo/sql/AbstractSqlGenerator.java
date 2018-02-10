package com.test.demo.sql;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSqlGenerator implements SqlGenerator {

	private DBHelper dbHelper;

	public AbstractSqlGenerator() {
		this.dbHelper = new DBHelper();
	}

	@Override
	public String generate(String tbName) {
		if (tbName == null || tbName == "") {
			return "";
		}

		//查询
		ResultSet res = dbHelper.query(getSql(tbName));
		return doGenerate(res);
	}

	/**
	 * 根据getSql的结果生成指定sql
	 * @param res
	 * @return
	 */
	protected abstract String doGenerate(ResultSet res);

	/**
	 * 查询sql
	 * @return
	 * @param tbName
	 */
	protected abstract String getSql(String tbName);

	@Override
	public String generateAll() {
		List<String> tbNames = queryAllTbNames();
		if (tbNames == null || tbNames.size() <= 0) {
			return "";
		}

		StringBuilder builder = new StringBuilder();
		for (String tbName : tbNames) {
			builder.append(generate(tbName)).append("\r\n\r\n\r\n");
		}
		return builder.toString();
	}

	@Override
	public void close() {
		dbHelper.close();
	}

	//查询数据库所有表名
	private List<String> queryAllTbNames() {
		List<String> tbNames = new ArrayList<>();
		ResultSet res = dbHelper.query("show tables;");
		try {
			while (res.next()) {
				tbNames.add(res.getString(1));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tbNames;
	}
}
