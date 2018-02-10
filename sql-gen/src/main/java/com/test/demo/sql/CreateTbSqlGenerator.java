package com.test.demo.sql;

import java.sql.ResultSet;

public class CreateTbSqlGenerator extends AbstractSqlGenerator {

	@Override
	protected String doGenerate(ResultSet res) {
		try {
			while (res.next()) {
				return res.getString(2) + ";";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	protected String getSql(String tbName) {
		return "show create table " + tbName + ";";
	}
}
