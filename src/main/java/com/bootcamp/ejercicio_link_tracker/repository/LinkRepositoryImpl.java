package com.bootcamp.ejercicio_link_tracker.repository;

import com.bootcamp.ejercicio_link_tracker.entity.Link;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LinkRepositoryImpl implements ILinkRepository{
    List<Link> linkList = new ArrayList<>();
    @Override
    public int saveLink(Link link) {
        link.setId(this.linkList.size());
        linkList.add(link);
        return link.getId();
    }

    @Override
    public Optional<Link> getLinkById(int id) {
        return this.linkList.stream().filter(link -> link.getId() == id).findFirst();
    }
}
