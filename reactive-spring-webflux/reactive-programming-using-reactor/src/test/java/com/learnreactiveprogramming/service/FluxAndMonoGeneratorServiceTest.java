package com.learnreactiveprogramming.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;

class FluxAndMonoGeneratorServiceTest {
	FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
	
	@Test
	public void namesFlux() {
		//given
		
		//when
		var namesFlux = fluxAndMonoGeneratorService.namesFlux();
		
		//then
		StepVerifier.create(namesFlux)
			.expectNext("b1", "b2", "b3?!?")
			.verifyComplete();
	}
	
	@Test
	public void namesFlux_map() {
		//given
		
		//when
		var namesFlux = fluxAndMonoGeneratorService.namesFlux_map();
		
		//then
		StepVerifier.create(namesFlux)
			.expectNext("B1", "B2", "B3?!?")
			.verifyComplete();
	}
	
	@Test
	public void namesFlux_flatMap() {
		//given
		
		//when
		var namesFlux = fluxAndMonoGeneratorService.namesFlux_flatMap(2);
		
		//then
		StepVerifier.create(namesFlux)
			.expectNext("B", "3", "?", "!","?")
			.verifyComplete();
	}
	
	@Test
	public void namesFlux_flatMap_asynch() {
		//given
		
		//when
		var namesFlux = fluxAndMonoGeneratorService.namesFlux_flatMap_asynch(2);
		
		//then
		StepVerifier.create(namesFlux)
			.expectNextCount(5)
			.verifyComplete();
	}
}