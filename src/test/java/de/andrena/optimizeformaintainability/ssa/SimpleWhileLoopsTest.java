package de.andrena.optimizeformaintainability.ssa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleWhileLoopsTest {

	private SimpleWhileLoops loops;
	
	@BeforeEach
	void before() {
		loops = new SimpleWhileLoops();
	}
	
	@Test
	void testPadWithSpaces() throws Exception {
		assertThat(loops.padWithSpaces(10, "text")).isEqualTo("      text");
	}

}
