package com.odevpedro.demo.controller;

import com.odevpedro.demo.client.RickAndMorryClient;
import com.odevpedro.demo.reponse.CharacterResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class RickAndMorryController {

    RickAndMorryClient rickAndMorryClient;

    @GetMapping("/character/{id}")
    public Mono<CharacterResponse> getCharacterById(@PathVariable String id){
         return rickAndMorryClient.findAndCharacterById(id);
    }

}
