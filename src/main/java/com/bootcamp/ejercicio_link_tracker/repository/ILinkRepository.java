package com.bootcamp.ejercicio_link_tracker.repository;

import com.bootcamp.ejercicio_link_tracker.entity.Link;

import java.util.Optional;

public interface ILinkRepository {
    int saveLink(Link link);
    Optional<Link> getLinkById(int id);
}
