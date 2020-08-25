package com.apress.springrecipes.repositories;

import org.reactivestreams.Publisher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.apress.springrecipes.nosql.Vehicle;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MongoDBVehicleRepository implements VehicleRepository {
	
	private final ReactiveMongoTemplate mongo;
	
	public MongoDBVehicleRepository(ReactiveMongoTemplate mongo) {
		this.mongo = mongo;
	}

	@Override
	public <S extends Vehicle> Mono<S> insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Vehicle> Flux<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Vehicle> Flux<S> insert(Publisher<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Vehicle> Flux<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Vehicle> Flux<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Vehicle> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Vehicle> Mono<S> save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Vehicle> Flux<S> saveAll(Iterable<S> entities) {
		entities.forEach(e->mongo.save(e));
		return Flux.fromIterable(entities);
	}

	@Override
	public <S extends Vehicle> Flux<S> saveAll(Publisher<S> entityStream) {
//		entityStream.forEach(e->mongo.save(e));
		
		return Flux.from(entityStream);
	}

	@Override
	public Mono<Vehicle> findById(String id) {
		return mongo.findById(id, Vehicle.class);
	}

	@Override
	public Mono<Vehicle> findById(Publisher<String> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Boolean> existsById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Boolean> existsById(Publisher<String> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Vehicle> findAll() {
		return mongo.findAll(Vehicle.class);
	}

	@Override
	public Flux<Vehicle> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Vehicle> findAllById(Publisher<String> idStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Long> count() {
		return mongo.count(new Query(), Vehicle.class);
	}

	@Override
	public Mono<Void> deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteById(Publisher<String> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> delete(Vehicle entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll(Iterable<? extends Vehicle> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll(Publisher<? extends Vehicle> entityStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll() {
//		mongo.findAndRemove(new Query(), Vehicle.class);
		return Mono.empty();
	}

	@Override
	public <S extends Vehicle> Mono<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Vehicle> Mono<Long> count(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Vehicle> Mono<Boolean> exists(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Vehicle> findByVehicleNo(String vehicleNo) {
		return mongo.findById(vehicleNo, Vehicle.class);
	}

}
