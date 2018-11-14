package com.example.reservationclient.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.example.reservationclient.domain.Reservation;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/reservations")
public class ReservationApiGatewayRestController 
{
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Source source;
	
	public Collection<String>getReservationNamesFallback()
	{
		return new ArrayList<>();
	}
	
	@HystrixCommand(fallbackMethod="getReservationNamesFallback")
	@RequestMapping(method=RequestMethod.GET, value="/names")
	public Collection<String>getReservationNames()
	{
		ParameterizedTypeReference <Resources<Reservation>> ptr = new ParameterizedTypeReference <Resources<Reservation>>() {};
		ResponseEntity <Resources<Reservation>>entity = this.restTemplate.exchange("http://reservation-service/reservations", HttpMethod.GET,null,ptr);
		return entity
				.getBody()
				.getContent()
				.stream()
				.map(Reservation::getReservationName)
				.collect(Collectors.toList());
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void writeReservation(@RequestBody Reservation r)
	{
		Message<String>msg = MessageBuilder.withPayload(r.getReservationName()).build();
		this.source.output().send(msg);
	}
	
}
