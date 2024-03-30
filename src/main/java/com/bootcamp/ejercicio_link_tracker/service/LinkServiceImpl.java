package com.bootcamp.ejercicio_link_tracker.service;

import com.bootcamp.ejercicio_link_tracker.dto.response.GenericResponseDTO;
import com.bootcamp.ejercicio_link_tracker.dto.response.LinkIdDTO;
import com.bootcamp.ejercicio_link_tracker.dto.response.MetricDTO;
import com.bootcamp.ejercicio_link_tracker.entity.Link;
import com.bootcamp.ejercicio_link_tracker.exception.AuthException;
import com.bootcamp.ejercicio_link_tracker.exception.NotFoundException;
import com.bootcamp.ejercicio_link_tracker.repository.ILinkRepository;
import com.bootcamp.ejercicio_link_tracker.repository.LinkRepositoryImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service

public class LinkServiceImpl implements ILinkService{
    private ILinkRepository linkRepository;

    public  LinkServiceImpl(LinkRepositoryImpl linkRepository){
        this.linkRepository = linkRepository;
    }
    @Override
    public LinkIdDTO createLink(String redirectionLink, String password) {
        Link linkCreated = new Link(0, password, redirectionLink , 0, true);
        int linkCreatedId = linkRepository.saveLink(linkCreated);
        return new LinkIdDTO(linkCreatedId);
    }

    @Override
    public HttpHeaders redirectLink(int linkId, String password) {
        Optional<Link> link = this.linkRepository.getLinkById(linkId);
        if (link.isEmpty()){
            throw new NotFoundException("Link no valido");
        }
        if (link.get().getPassword() != null && !link.get().getPassword().equals(password)){
            throw new AuthException("La contraseña indicada no es correcta");
        }
        if (!link.get().isValid()){
            throw new AuthException("El link ha sido invalidado y no puede ser accedido");
        }
        link.get().incrementRedirectionQuantity();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(link.get().getRedirection());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.build().toUri());
        return headers;
    }

    @Override
    public MetricDTO getMetric(int linkId) {
        Optional<Link> link = this.linkRepository.getLinkById(linkId);
        if (link.isEmpty()){
            throw new NotFoundException("Link no valido");
        }
        return new MetricDTO(link.get().getRedirectionsQuantity());
    }

    @Override
    public GenericResponseDTO invalidateLink(int linkId) {
        Optional<Link> link = this.linkRepository.getLinkById(linkId);
        if (link.isEmpty()){
            throw new NotFoundException("Link no valido");
        }
        if (!link.get().isValid()){
            return new GenericResponseDTO("El link ya estaba invalidado");
        }

        link.get().setValid(false);
        return new GenericResponseDTO("El link ha sido invalidado con exito");
    }

}
