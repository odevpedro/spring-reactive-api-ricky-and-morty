package com.odevpedro.demo.client;

import com.odevpedro.demo.reponse.CharacterResponse;
import com.odevpedro.demo.reponse.EpisodeResponse;
import com.odevpedro.demo.reponse.ListOfEpisodesResponse;
import com.odevpedro.demo.reponse.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
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

    public Mono<LocationResponse> findLocationById(String id){
        log.info("Searching the local with the id [{}]", id);
        return webClient
                .get()
                .uri( "/location/" +id)
                .accept(APPLICATION_JSON)
                .retrieve().
                onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verify the params")))
                .bodyToMono(LocationResponse.class);

    }

    public Mono<EpisodeResponse> findEpisodeById(String id){
        log.info("Searching the episode with the id [{}]", id);
        return webClient
                .get()
                .uri( "/episode/" +id)
                .accept(APPLICATION_JSON)
                .retrieve().
                onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verify the params")))
                .bodyToMono(EpisodeResponse.class);

    }

    public Flux<ListOfEpisodesResponse> getAllEpisodes(){
        log.info("Listing all episodes");
        return webClient
                .get()
                .uri( "/episode/")
                .accept(APPLICATION_JSON)
                .retrieve().
                onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verify the params")))
                .bodyToFlux(ListOfEpisodesResponse.class);

    }

}
