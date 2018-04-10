package com.test.demo.sql;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.ResultSet;

public class CreateTbSqlGenerator extends AbstractSqlGenerator {
	private final static Log log = LogFactory.getLog(CreateTbSqlGenerator.class);
	@Override
	protected String doGenerate(ResultSet res, String tbName) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DROP TABLE IF EXISTS `").append(tbName).append("`;\r\n");
			if (res.next()) {
				sql.append(res.getString(2)).append(";");
			}
			return sql.toString();
		} catch (Exception ex) {
			log.error(ex);
		}
		return null;
	}

	@Override
	protected String getSql(String tbName) {
		return "show create table " + tbName + ";";
	}
}
