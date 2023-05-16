package com.ra.contentcalendar.controller;

import com.ra.contentcalendar.model.Content;
import com.ra.contentcalendar.model.Status;
import com.ra.contentcalendar.model.Type;
import com.ra.contentcalendar.repository.ContentCollectionRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController //IOC
@RequestMapping("/api/content")
public class ContentController {

    private final ContentCollectionRepository repository;

@Autowired //its implicit here and dont needed
    public ContentController(ContentCollectionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Content> findAll(){
    return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id){
    return  repository.findById(id).
            orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("") //here we add valid to verify
    public void create(@Valid  @RequestBody Content content){
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id){
if (!repository.existsById(id)){
    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found!");
}
repository.save(content);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
    repository.delete(id);
    }


}
