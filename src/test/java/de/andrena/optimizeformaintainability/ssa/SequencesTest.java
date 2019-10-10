package de.andrena.optimizeformaintainability.ssa;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SequencesTest {

	
	private Sequences sequences;

	@BeforeEach
	void before() {
		sequences = new Sequences();
	}
	
	@Test
	public void testWrap() throws Exception {
		assertThat(sequences.wrap("<", asList("a","b"), ">")).isEqualTo("<[a, b]>");
	}

}
