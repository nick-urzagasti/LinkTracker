package com.bootcamp.ejercicio_link_tracker.controller;

import com.bootcamp.ejercicio_link_tracker.service.ILinkService;
import com.bootcamp.ejercicio_link_tracker.service.LinkServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServlet;

@RestController
@RequestMapping("/link")
public class LinkController  extends HttpServlet {
    private ILinkService linkService;
    public LinkController(LinkServiceImpl linkService){
        this.linkService = linkService;
    }
    //Crear un link:
    // Endpoint POST para crear link a partir de una URL válida
    // y tiene que devolver un JSON con el linkId para utilizar en la redirección.
    @PostMapping("/new")
    public ResponseEntity<?> createLink(@RequestParam String redirectionLink, @RequestParam(required = false) String password){
        return new ResponseEntity<>(this.linkService.createLink(redirectionLink, password), HttpStatus.OK);
    }

    //Redirección:  Dado un link (ej: http://localhost:8080/link/{linkId} )
    // tiene que realizar un redirect a la URL enmascarada.
    // Siempre y cuando el link sea válido. En el caso de que el link sea invalido devolver
    // 404(INVESTIGAR REDIRECT).
    @GetMapping("/{linkId}")
    public ResponseEntity<?> redirection( @PathVariable int linkId, @RequestParam(required = false) String password)  {
        return new ResponseEntity<>(this.linkService.redirectLink(linkId, password), HttpStatus.FOUND);
    }

    //Estadísticas por link: Endpoint GET que dado un link
    // (ej: http://localhost:8080/metrics/{linkID} )
    // tiene que devolver la estadística de cantidad de veces que se redireccionó.
    @GetMapping("/metrics/{linkId}")
    public ResponseEntity<?> getMetric(@PathVariable int linkId){
        return new ResponseEntity<>(this.linkService.getMetric(linkId), HttpStatus.OK);
    }
    //Invalidate link:
    // Endpoint POST para invalidar un link (ej: http://localhost:8080/invalidate/{linkID} ).
    //Al crear los links se tiene que poder agregar un password que
    // va a ser un query param al llamar a la redirección.
    @PatchMapping("/invalidate/{linkId}")
    public ResponseEntity<?> invalidateLink(@PathVariable int linkId){
        return new ResponseEntity<>(this.linkService.invalidateLink(linkId), HttpStatus.OK);
    }


}
