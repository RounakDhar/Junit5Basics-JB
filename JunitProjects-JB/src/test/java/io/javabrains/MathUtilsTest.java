package io.javabrains;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("when running MathUtils")
class MathUtilsTest {

	MathUtils mathutils;
	TestInfo testInfo;
	TestReporter testReporter;

	@BeforeAll
	// static void beforeAll() {
	void beforeAll() {
		System.out.println("This Needs to be Run Before All");
	}

	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		mathutils = new MathUtils();
		testReporter.publishEntry("running" + testInfo.getDisplayName() + "with tags" + testInfo.getTags());
	}

	@AfterEach
	void cleanup() {
		System.out.println("Cleaning Up...");
	}

	@Test
	void testRun() {
		System.out.println("This is a Test Ran");
	}

	@Test
	@DisplayName("Testing Add Method")
	void testAdd1() {

		// MathUtils mathutils = new MathUtils();

		int expected = 2;
		int actual = mathutils.add(1, 1);
		System.out.println(actual);
		// assertEquals(expected,actual);
		assertEquals(expected, actual, "The Add Method Should Add Two Numbers");
	}
	
	@Nested
	@DisplayName("add method")
	class AddTest {
		
		@Test
		@DisplayName("Testing Add Method for +")
		@Tag("Math")
		void testAddPositive() {

			assertEquals(2,mathutils.add(1, 1),"should return the right sum");
		}
		
		@Test
		@DisplayName("Testing Add Method for -")
		void testAddNegative() {
			int expected = -2;
			int actual = mathutils.add(-1, -1);
			//assertEquals(expected,actual,"should return the sum " + expected + " but returned " + actual);
			assertEquals(expected,actual,() -> "should return the sum " + expected + " but returned " + actual);
			//assertEquals(-2,mathutils.add(-1, -1),"should return the right sum");
		}

	}
	
	@Test
	//@RepeatedTest(3)
	@Tag("Circle")
	//void testComputeCircleRadius(RepetitionInfo repetitionInfo) {
	void testComputeCircleRadius() {
		// MathUtils mathutils = new MathUtils();
		//repetitionInfo.getTotalRepetitions();
		assertEquals(314.16, mathutils.computeCircleArea(10), "Should Return Right Circle Area");
	}

	@Test
	@EnabledOnOs(OS.LINUX)
	void testDivide() {
		// MathUtils mathutils = new MathUtils();
		
		boolean isServerUp = false;
		assumeTrue(isServerUp);

		assertThrows(ArithmeticException.class, () -> mathutils.divide(1, 0), "divide by zero should throw");
		// assertThrows(NullPointerException.class, () -> mathutils.divide(1,0),"divide
		// by zero should throw");
		// mathutils.divide(1,0);
	}
	
	@Test
	@Tag("Math")
	void testMultiply1() {
		assumeTrue(true);
//		int expected = 1;
//		int actual = mathutils.multiply(1, 1);
//		assertEquals(expected, actual, "The Multiply Method Should Multiply Two Numbers");
		
		//assertEquals(4, mathutils.multiply(2, 2),"should return the right product");
		assertAll(
				() -> assertEquals(4, mathutils.multiply(2, 2)),
				() -> assertEquals(0, mathutils.multiply(2, 0)),
				() -> assertEquals(-2, mathutils.multiply(2, -1))
				);
				
		
	}
	
	@Test
	void testMultiply2() {
		
		//System.out.println("running" + testInfo.getDisplayName() + "with tags" + testInfo.getTags());
		testReporter.publishEntry("running" + testInfo.getDisplayName() + "with tags" + testInfo.getTags());
		
		assertAll(
				() -> assertEquals(4, mathutils.multiply(2, 2)),
				() -> assertEquals(0, mathutils.multiply(2, 0)),
				() -> assertEquals(-2, mathutils.multiply(2, -1))
				);
				
		
	}

	
	@Test
	@Disabled
	@DisplayName("TDD Method should not Run")
	void testDisabled() {
		fail("this test should be disabled");
	}
	
	
}
