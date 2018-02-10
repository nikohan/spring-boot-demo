package com.test.demo.sql;

import java.util.List;

public interface SqlGenerator {

	/**
	 * 生成一个表sql
	 * @return
	 */
	String generate(String tbName);

	/**
	 * 生成多个表sql
	 * @return
	 */
	String generateAll();

	void close();
}
