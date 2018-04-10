package com.test.demo.sql;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSqlGenerator implements SqlGenerator {

	private final static Log log = LogFactory.getLog(AbstractSqlGenerator.class);

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
		return doGenerate(res, tbName);
	}

	/**
	 * 根据getSql的结果生成指定sql
	 * @param res
	 * @param tbName
	 * @return
	 */
	protected abstract String doGenerate(ResultSet res, String tbName);

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

		StringBuilder sql = new StringBuilder();
		sql.append("SET NAMES utf8mb4;\r\nSET FOREIGN_KEY_CHECKS = 0;\r\n\r\n\r\n");
		for (String tbName : tbNames) {
			sql.append(generate(tbName)).append("\r\n\r\n\r\n");
		}
		sql.append("SET FOREIGN_KEY_CHECKS = 1;");
		return sql.toString();
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
			log.error(ex);
		}
		return tbNames;
	}
}
