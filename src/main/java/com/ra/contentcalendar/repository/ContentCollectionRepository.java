package com.ra.contentcalendar.repository;

import com.ra.contentcalendar.model.Content;
import com.ra.contentcalendar.model.Status;
import com.ra.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository(){

    }

    public List<Content> findAll(){
        return contentList;
    }

    public Optional<Content> findById(Integer id){
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }


    //Metodo para agregar un content
    public void save(Content content){
        contentList.removeIf(c ->c.id().equals(content.id()));
        contentList.add(content);
    }
    @PostConstruct
    private void   init(){
        Content c = new Content(1,"POST1","DESCRIPTION", Status.COMPLETED,
                Type.ARTICLE, LocalDateTime.now(),null,"");
        contentList.add(c);
    }

    public boolean existsById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).count() ==1;
    }

    public void delete(Integer id){
        contentList.removeIf(c -> c.id().equals(id));

    }
}
