package com.devsuperior.bds04.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	@Transactional
	public EventDTO insert(EventDTO eventDto) {
		Event entity = new Event();
		entity.setName(eventDto.getName());
		entity.setDate(eventDto.getDate());
		entity.setUrl(eventDto.getUrl());
		entity.setCity(new City(eventDto.getCityId(),null));
		entity = repository.save(entity);
		return new EventDTO(entity);
		

	}
	
	@Transactional(readOnly = true)
	public Page<EventDTO> findAll(Pageable pageable) {
		Page<Event> events = repository.findAll(pageable);
		return events.map(x -> new EventDTO(x));
		
	}
	
}
