package com.example.app;

import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;

@AnalyzeClasses(packages = "com.example")
class ArchitectureTest {

	@ArchTest
	static final ArchRule core_must_not_depend_on_another_domain_core = noClasses().that()
			.resideInAnyPackage("com.example.catalog.course..").or()
			.resideInAnyPackage("com.example.enrollments.enrollment..").or()
			.resideInAnyPackage("com.example.notifications.notification..").should().dependOnClassesThat()
			.resideInAnyPackage("com.example.catalog.course..", "com.example.enrollments.enrollment..",
					"com.example.notifications.notification..");
}
