package com.learnreactiveprogramming.service;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxAndMonoGeneratorService {
	
	
	public Flux<String> namesFlux() {
		return Flux.fromIterable(List.of("b1", "b2", "b3?!?"));
	}
	
	public Flux<String> namesFlux_map() {
		return Flux.fromIterable(List.of("b1", "b2", "b3?!?"))
				.map(String::toUpperCase);
	}
	
	public Flux<String> namesFlux_flatMap(int strLength) {
		return Flux.fromIterable(List.of("b1", "b2", "b3?!?"))
				.map(String::toUpperCase)
				.filter(s -> s.length() > strLength)
				.flatMap(this::splitString);
	}
	
	public Flux<String> namesFlux_flatMap_asynch(int strLength) {
		return Flux.fromIterable(List.of("b1", "b2", "b3?!?"))
				.map(String::toUpperCase)
				.filter(s -> s.length() > strLength)
				.flatMap(this::splitString_withDelay);
	}
	
	public Flux<String> splitString(String name) {
		return Flux.fromArray(name.split(""));
	}
	
	public Flux<String> splitString_withDelay(String name) {
		var delay = new Random().nextInt(1000);
		return Flux.fromArray(name.split(""))
				.delayElements(Duration.ofMillis(delay));
	}
	
	public Mono<String> namesMono() {
		return Mono.just("name");
	}
	
	public static void main(String[] args) {
		FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
		
		fluxAndMonoGeneratorService
			.namesFlux()
			.subscribe(System.err::println);
	
		fluxAndMonoGeneratorService.namesMono().subscribe(System.err::println);
	}
}
