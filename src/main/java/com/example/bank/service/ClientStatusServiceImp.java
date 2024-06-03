package com.example.bank.service;

import com.example.bank.model.entities.ClientStatus;
import com.example.bank.model.repos.ClientStatusRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientStatusServiceImp implements ClientStatusService {

    @Autowired
    ClientStatusRepository clientStatusRepository;

    @Override
    public void create(ClientStatus clientStatus) {
        clientStatusRepository.save(clientStatus);
    }

    @Override
    public List<ClientStatus> readAll() {
        //return new ArrayList<>(CLIENT_REPOSITORY_MAP.values());
        return clientStatusRepository.findAll();
    }

    @Override
    public ClientStatus readById(long id) {
        return clientStatusRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean update(ClientStatus clientStatus, long id) {
        if (clientStatusRepository.existsById(id)) {
            clientStatus.setId(id);
            clientStatusRepository.save(clientStatus);
            return true;
        }
        return false;
    }

    /*
    @Override
    public void patch(String value, long id) throws EntityNotFoundException {
        ClientStatus clientStatus = clientStatusRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        clientStatus.setValue(value);
        clientStatusRepository.save(clientStatus);
    } */


    @Override
    @SneakyThrows
    public ClientStatus patch(Long id, JsonPatch patch) {
        ClientStatus clientStatus = clientStatusRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ClientStatus clientStatusPatched = applyPatchToClientStatus(patch, clientStatus);
        clientStatusRepository.save(clientStatusPatched);
        return clientStatusPatched;
    }

    @SneakyThrows
    private ClientStatus applyPatchToClientStatus(
            JsonPatch patch,
            ClientStatus targetClientStatus) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetClientStatus, JsonNode.class));
        return objectMapper.treeToValue(patched, ClientStatus.class);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        ClientStatus clientStatus = clientStatusRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        clientStatusRepository.deleteById(id);
    }
}
