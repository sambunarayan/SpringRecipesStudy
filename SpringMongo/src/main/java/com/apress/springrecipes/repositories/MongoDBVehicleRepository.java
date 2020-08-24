package com.apress.springrecipes.repositories;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;
import java.util.Optional;

import javax.annotation.PreDestroy;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.apress.springrecipes.nosql.Vehicle;

public class MongoDBVehicleRepository implements VehicleRepository {
	
	private final MongoTemplate mongo;
	
	public MongoDBVehicleRepository(MongoTemplate mongo) {
		this.mongo = mongo;
	}

	@Override
	public long count() {
		return mongo.count(new Query(), Vehicle.class);
	}

	@Override
	public Vehicle save(Vehicle vehicle) {
		mongo.save(vehicle);
		return vehicle;
	}

	@Override
	public void delete(Vehicle vehicle) {
		mongo.remove(vehicle);
	}

	@Override
	public List<Vehicle> findAll() {
		return mongo.findAll(Vehicle.class);
	}

	@Override
	public Vehicle findByVehicleNo(String vehicleNo) {
		return mongo.findOne(new Query(where("vehicleNo").is(vehicleNo)), Vehicle.class);
	}
	
	@PreDestroy
	public void cleanUp() {
		mongo.execute(db->{
			db.drop();
			return null;
		});
	}

	@Override
	public <S extends Vehicle> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicle> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Vehicle> S insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Vehicle> List<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Vehicle> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Vehicle> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Vehicle> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Vehicle> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Vehicle> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Vehicle> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Vehicle> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Vehicle> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Vehicle> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Vehicle> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

}
