package com.test.demo.spring;

import com.test.demo.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created on 2017/8/21.
 */
public class BeanTest {
	@Test
	public void getBean() throws Exception {
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("config/application.xml"));

		User user = (User) factory.getBean("user");
		System.out.println(user.getId());
	}
}
