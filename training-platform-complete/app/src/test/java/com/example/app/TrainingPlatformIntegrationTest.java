package com.example.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import com.example.catalog.api.CourseDto;
import com.example.enrollments.api.EnrollmentDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TrainingPlatformIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void publishEnrollAndNotificationFlowWorks() {
		ResponseEntity<CourseDto> createResponse = restTemplate.postForEntity("/api/v1/courses",
				new CourseRequest("Java Microservices", 2, "Gurugram"), CourseDto.class);

		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		Long courseId = createResponse.getBody().id();

		ResponseEntity<CourseDto> publishResponse = restTemplate
				.postForEntity("/api/v1/courses/" + courseId + "/publish", null, CourseDto.class);

		assertThat(publishResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(publishResponse.getBody().published()).isTrue();

		ResponseEntity<EnrollmentDto> enrollmentResponse = restTemplate.postForEntity("/api/v1/enrollments",
				new EnrollmentRequest("Ravi", "ravi@example.com", courseId), EnrollmentDto.class);

		assertThat(enrollmentResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		ResponseEntity<EnrollmentDto[]> listResponse = restTemplate
				.getForEntity("/api/v1/enrollments?courseId=" + courseId, EnrollmentDto[].class);

		assertThat(listResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(listResponse.getBody()).hasSize(1);
	}

	record CourseRequest(String title, int capacity, String city) {
	}

	record EnrollmentRequest(String name, String email, Long courseId) {
	}
}
