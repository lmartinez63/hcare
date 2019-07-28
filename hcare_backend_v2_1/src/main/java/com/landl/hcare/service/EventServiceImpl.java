package com.landl.hcare.service;
import com.landl.hcare.entity.Event;
import com.landl.hcare.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    public Event save(Event event) throws Exception{
        return eventRepository.save(event);
    }

    public List<Event> findAll() throws Exception{
        return eventRepository.findAll();
    }

    public List<Event> findBySurgeryAreaId(Long surgeryAreaId) throws Exception{
        return eventRepository.findBySurgeryAreaId(surgeryAreaId);
    }

    public Event findById(Long eventId) throws Exception{
        return eventRepository.findById(eventId).get();
    }

    public Event createEventFromSurgeryArea(Long surgeryAreaId) throws  Exception{
        Event event = new Event();
        event.setSurgeryAreaId(surgeryAreaId);
        event.setOverlap(false);
        event.setGroupId(surgeryAreaId);
        return event;
    }
}
