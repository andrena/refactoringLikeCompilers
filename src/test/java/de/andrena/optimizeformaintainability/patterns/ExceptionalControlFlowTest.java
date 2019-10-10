package de.andrena.optimizeformaintainability.patterns;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExceptionalControlFlowTest {

	private ExceptionalControlFlow controlflow;
	
	@BeforeEach
	void before() {
		controlflow = new ExceptionalControlFlow();
	}
	
	@Test
	void testFind() throws Exception {
		assertThat(controlflow.find("a", asList("a","b","c"))).isEqualTo(0);
		assertThat(controlflow.find("d", asList("a","b","c"))).isEqualTo(3);
	}

}
