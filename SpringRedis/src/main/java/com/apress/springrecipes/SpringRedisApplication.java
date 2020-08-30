package com.apress.springrecipes;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.SerializationUtils;

import com.apress.springrecipes.config.RedisConfig;
import com.apress.springrecipes.domain.Vehicle;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

public class SpringRedisApplication {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		Jedis jedis = new Jedis("localhost");
		jedis.rpush("authors", "Marten Deinum", "Josh Long", "Daniel Rubio", "Gary Mak");
		jedis.lpush("authors", "Jaewoo Kim");
		System.out.println("Authors: " + jedis.lrange("authors", 0, -1));
//		System.out.println("Authors: " + jedis.rr("authors", 0, -1));
		
		jedis.hset("sr_3", "authors", "Gary Mak, Daniel Rubio, Josh Long, Marten Deinum");
		jedis.hset("sr_3", "published", "2014");
		
		jedis.hset("sr_4", "authors", "Josh Long, Marten Deinum");
		jedis.hset("sr_4", "published", "2017");
		
		System.out.println("Spring Recipes 3rd " + jedis.hgetAll("sr_3"));
		System.out.println("Spring Recipes 4rd " + jedis.hgetAll("sr_4"));
		
		System.out.println("to bytes ======================");
		final String vehicleNo = "TEM0001";
		Vehicle vehicle = new Vehicle(vehicleNo, "RED", 4, 4);
		jedis.set(vehicleNo.getBytes(), SerializationUtils.serialize(vehicle));
		byte[] vehicleArray = jedis.get(vehicleNo.getBytes());
		System.out.println("vehicle: " + SerializationUtils.deserialize(vehicleArray));
		
		System.out.println("to JSON ======================");
		ObjectMapper mapper = new ObjectMapper();
		final String vehicleNo2 = "TEM0002";
		Vehicle vehicle2 = new Vehicle(vehicleNo2, "RED", 4, 4);
		System.out.println("to json : " + mapper.writeValueAsString(vehicle2));
		jedis.set(vehicleNo2, mapper.writeValueAsString(vehicle2));
		String vehicleString = jedis.get(vehicleNo2);
		System.out.println("get Json from Redis : " + vehicleString);
		System.out.println("Vehicle: " + mapper.readValue(vehicleString, Vehicle.class));
		
		System.out.println("JedisTemplate ======================");
		ApplicationContext context = new AnnotationConfigApplicationContext(RedisConfig.class);
		RedisTemplate<String ,Vehicle> template = context.getBean(RedisTemplate.class);
		
		final String vehicleNo3 = "TEM003";
		Vehicle vehicle3 = new Vehicle(vehicleNo3, "RED", 4, 4);
		template.opsForValue().set(vehicleNo3, vehicle3);
		System.out.println("Vehicle: " + template.opsForValue().get(vehicleNo3));
	}

}
