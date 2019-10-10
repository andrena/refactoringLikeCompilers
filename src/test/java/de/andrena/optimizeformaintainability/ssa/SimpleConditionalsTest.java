package de.andrena.optimizeformaintainability.ssa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.andrena.optimizeformaintainability.ssa.SimpleConditionals.Sign;


public class SimpleConditionalsTest {

	private SimpleConditionals conditionals;

	@BeforeEach
	void before() {
		conditionals = new SimpleConditionals();
	}
	
	@Test
	public void testWrap() throws Exception {
		assertThat(conditionals.format(Sign.PLUS, 10)).isEqualTo("10");
		assertThat(conditionals.format(Sign.MINUS, 5)).isEqualTo("-5");
	}

}
