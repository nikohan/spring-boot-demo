package com.test.demo.sql;


import org.junit.Test;

public class SqlGeneratorTest {
	@Test
	public void createTbSql() {
		SqlGenerator createSqlGenerator = new CreateTbSqlGenerator();
		System.out.println(createSqlGenerator.generate("city"));
		createSqlGenerator.close();
	}

	@Test
	public void createAllTbSql() {
		SqlGenerator createSqlGenerator = new CreateTbSqlGenerator();
		System.out.println(createSqlGenerator.generateAll());
		createSqlGenerator.close();
	}
}
