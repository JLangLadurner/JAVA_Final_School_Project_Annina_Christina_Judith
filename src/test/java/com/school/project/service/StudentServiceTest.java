package com.school.project.service;

import com.school.project.model.Student;
import com.school.project.repository.RoleRepository;
import com.school.project.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;


public class StudentServiceTest {

	@Mock
	private StudentRepository mockStudentRepository;
	@Mock
	private RoleRepository mockRoleRepository;
	@Mock
	private BCryptPasswordEncoder mockBCryptPasswordEncoder;

	private StudentService studentServiceUnderTest;
	private Student student;

	@Before
	public void setUp() {
		initMocks(this);
		studentServiceUnderTest = new StudentService(mockStudentRepository,
				mockRoleRepository,
				mockBCryptPasswordEncoder);
		student = Student.builder()
				.id(1)
				.firstName("Max")
				.lastName("Musterman")
				.email("test@test.com")
				.password("12345")
				.age("11")
				.oldClass("5a")
				.fkNewClassId(1)
				.build();

		Mockito.when(mockStudentRepository.save(any()))
				.thenReturn(student);
		Mockito.when(mockStudentRepository.findByEmail(anyString()))
				.thenReturn(student);
	}

	@Test
	public void testFindStudentByEmail() {
		// Setup
		final String email = "test@test.com";

		// Run the test
		final Student result = studentServiceUnderTest.findByEmail(email);

		// Verify the results
		assertEquals(email, result.getEmail());
	}

	@Test
	public void testSaveUser() {
		// Setup
		final String email = "test@test.com";

		// Run the test
		Student result = studentServiceUnderTest.saveStudent(Student.builder().build());

		// Verify the results
		assertEquals(email, result.getEmail());
	}


}
