package ru.jug.jps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jug.jps.dto.Participant;
import ru.jug.jps.repository.ParticipantRepository;

import java.util.List;

@Service
public class ParticipantService {
    private final ParticipantRepository repository;

    @Autowired
    public ParticipantService(ParticipantRepository repository) {
        this.repository = repository;
    }

    public List<Participant> findByEitherField(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return repository.findByEitherField(value);
    }

    public List<Participant> findAll() {
        return repository.findAll();
    }
}
