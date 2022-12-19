package com.odevpedro.demo.client;

import com.odevpedro.demo.reponse.CharacterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
public class RickAndMorryClient {

    private final WebClient webClient;

    public RickAndMorryClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Mono<CharacterResponse> findAndCharacterById(String id){
        log.info("Searching character with the id [{}]", id);
        return webClient
                .get()
                .uri( "/character/" +id)
                .accept(APPLICATION_JSON)
                .retrieve().
                onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verify the params")))
                .bodyToMono(CharacterResponse.class);

    }
}
