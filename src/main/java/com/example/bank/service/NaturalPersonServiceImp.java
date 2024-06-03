package com.example.bank.service;

import com.example.bank.model.entities.ClientStatus;
import com.example.bank.model.entities.NaturalPerson;
import com.example.bank.model.repos.NaturalPersonRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NaturalPersonServiceImp implements NaturalPersonService{

    @Autowired
    NaturalPersonRepository naturalPersonRepository;

    @Override
    public void create(NaturalPerson naturalPerson) {
        naturalPersonRepository.save(naturalPerson);
    }

    @Override
    public List<NaturalPerson> readAll() {
        return naturalPersonRepository.findAll();
    }

    @Override
    public NaturalPerson readById(long id) {
        return naturalPersonRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean update(NaturalPerson naturalPerson, long id) {
        if (naturalPersonRepository.existsById(id)) {
            naturalPerson.setId(id);
            naturalPersonRepository.save(naturalPerson);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public NaturalPerson patch(Long id, JsonPatch patch) {
        NaturalPerson naturalPerson = naturalPersonRepository.
                findById(id).
                    orElseThrow(EntityNotFoundException::new);
        NaturalPerson naturalPersonPatched = applyPatchToNaturalPerson(patch, naturalPerson);
        naturalPersonRepository.save(naturalPersonPatched);
        return naturalPersonPatched;
    }

    @SneakyThrows
    private NaturalPerson applyPatchToNaturalPerson(
                JsonPatch patch,
                NaturalPerson targetNaturalPerson) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch
                .apply(objectMapper.
                        convertValue(targetNaturalPerson, JsonNode.class));
        return objectMapper.treeToValue(patched, NaturalPerson.class);
    }

    @Override
    public void delete(long id) {
        NaturalPerson naturalPerson = naturalPersonRepository
                .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        naturalPersonRepository.deleteById(id);
    }
}
