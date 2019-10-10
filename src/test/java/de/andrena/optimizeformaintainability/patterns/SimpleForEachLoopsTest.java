package de.andrena.optimizeformaintainability.patterns;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleForEachLoopsTest {

	private SimpleForEachLoops loops;
	
	@BeforeEach
	void before() {
		loops = new SimpleForEachLoops();
	}
	
	@Test
	void testPadWithSpaces() throws Exception {
		assertThat(loops.join(asList("a", "b"))).isEqualTo("ab");
	}

}
