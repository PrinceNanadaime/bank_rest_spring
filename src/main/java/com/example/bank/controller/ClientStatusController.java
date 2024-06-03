package com.example.bank.controller;

import com.example.bank.model.entities.ClientStatus;
import com.example.bank.model.repos.ClientStatusRepository;
import com.example.bank.service.ClientStatusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController // This means that this class is a Controller
public class ClientStatusController {
    @Autowired
    private ClientStatusService clientStatusService;


    @PostMapping(value = "/status")
    public ResponseEntity<?> create(@RequestBody ClientStatus clientStatus) {
        clientStatusService.create(clientStatus);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/status")
    public ResponseEntity<List<ClientStatus>> read() {

        List<ClientStatus> clientStatuses = clientStatusService.readAll();
        return clientStatuses != null &&  !clientStatuses.isEmpty()
                ? new ResponseEntity<>(clientStatuses, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/status/{id}")
    public ResponseEntity<ClientStatus> read(@PathVariable(name = "id") long id) {
        try {
            ClientStatus clientStatus = clientStatusService.readById(id);
            return new ResponseEntity<>(clientStatus, HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    @PatchMapping(value = "/status/{id}")
    public ResponseEntity<ClientStatus> patch(@PathVariable(name = "id") long id, @RequestBody String value) {
        try {
            clientStatusService.patch(value, id);
            ClientStatus updatedClientStatus = clientStatusService.readById(id);
            return new ResponseEntity<>(updatedClientStatus, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

     */
    @PatchMapping(path = "/status/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<ClientStatus> patch(@PathVariable Long id, @RequestBody JsonPatch patch) {
        try {
            ClientStatus clientStatusPatched = clientStatusService.patch(id, patch);
            return ResponseEntity.ok(clientStatusPatched);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value = "/status")
    public ResponseEntity<?> put(@RequestParam Optional<Long> id, @RequestBody ClientStatus clientStatus) {
        boolean updated = id.filter(aLong -> clientStatusService.update(clientStatus, aLong)).isPresent();
        //id.ifPresent(aLong -> updated = clientStatusService.update(clientStatus, aLong));
        if (updated) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        else {
            return create(clientStatus);
        }
        //return updated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }



    @DeleteMapping(value = "/status/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        try {
            clientStatusService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    /*
    @GetMapping(path="/status")
    public ClientStatus getClientStatus(@RequestParam(value = "id", defaultValue = "1") long id){
        return clientStatusRepository.getReferenceById(id);
    }
     */
}
