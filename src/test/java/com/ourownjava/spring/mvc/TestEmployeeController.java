package com.ourownjava.spring.mvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author ourownjava.com
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-servlet-context.xml")
public class TestEmployeeController {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private final String EMPLOYEE_REQUEST = "{\"name\" : \"ename\"}";

	private MockMvc mockMvc;

	@Autowired
	private EmployeeController employeeController;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
				.build();
	}

	@Test
	public void shouldSaveEmployee() throws Exception {
		this.mockMvc
				.perform(
						post("/employee/save/").content(EMPLOYEE_REQUEST)
								.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$id", is("e1")))
				.andExpect(jsonPath("$name", is("ename")));
	}
	
	@Test
	public void shouldNotFindTheResource() throws Exception{
		this.mockMvc
		.perform(
				post("/employee/store/").content(EMPLOYEE_REQUEST)
						.contentType(APPLICATION_JSON_UTF8))
		.andExpect(status().is(404));
	}
}
