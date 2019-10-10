package de.andrena.optimizeformaintainability.ssa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ComplexConditionalsTest {

	private ComplexConditionals conditionals;

	@BeforeEach
	void before() {
		conditionals = new ComplexConditionals();
	}
	
	@Test
	public void testWrap() throws Exception {
		assertThat(conditionals.area("square", 5)).isEqualTo(25);
		assertThat(conditionals.area("rectangle", 5,10)).isEqualTo(50);
	}

}
