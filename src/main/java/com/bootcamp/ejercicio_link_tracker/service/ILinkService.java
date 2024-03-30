package com.bootcamp.ejercicio_link_tracker.service;

import com.bootcamp.ejercicio_link_tracker.dto.response.GenericResponseDTO;
import com.bootcamp.ejercicio_link_tracker.dto.response.LinkIdDTO;
import com.bootcamp.ejercicio_link_tracker.dto.response.MetricDTO;
import org.springframework.http.HttpHeaders;

public interface ILinkService {
    LinkIdDTO createLink(String redirectionLink, String Password);
    HttpHeaders redirectLink(int linkId, String password);

    MetricDTO getMetric(int linkId);

    GenericResponseDTO invalidateLink(int linkId);
}
