package com.devwdougherty.restlolapi.controller;

import com.devwdougherty.restlolapi.model.Summoner;
import com.devwdougherty.restlolapi.service.SummonerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.devwdougherty.restlolapi.util.Constants.BASE_API_URL_V1;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = BASE_API_URL_V1 + "/summoners")
public class SummonerController {

    @Autowired
    SummonerService summonerService;

    Logger logger = LoggerFactory.getLogger(SummonerController.class);

    @GetMapping(value = "{summonerId}")
    @Operation(summary = "Get summoner by ID")
    @ApiResponse(responseCode = "200", description = "Summoner found", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Summoner.class))})
    @ApiResponse(responseCode = "404", description = "Summoner not found", content = {@Content})
    @ApiResponse(responseCode = "500", description = "Internal Server Error on the operation", content = {@Content})
    public ResponseEntity<Summoner> getSummonerById(@PathVariable String summonerId) {

        logger.info("SUMMONERS GET /summonerId");

        Summoner summonerMono;

        summonerMono = summonerService.findById(summonerId);

        if (summonerMono.getId() == null) {

            logger.error("Summoner not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(summonerMono);
    }

    @GetMapping
    @Operation(summary = "Get a list of Summoners")
    @ApiResponse(responseCode = "200", description = "Summoners list returned", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Summoner.class))})
    @ApiResponse(responseCode = "500", description = "Internal Server Error on the operation", content = {@Content})
    public ResponseEntity<List<Summoner>> getSummoners() {

        logger.info("SUMMONERS GET ALL");

        List<Summoner> summonerList;

        summonerList = summonerService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(summonerList);
    }

    @PostMapping
    @Operation(summary = "Crate a new summoner")
    @ApiResponse(responseCode = "201", description = "Summoner is created", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Summoner.class))})
    @ApiResponse(responseCode = "500", description = "Internal Server Error on the operation", content = {@Content})
    public ResponseEntity<Summoner> saveSummoner(@Valid @RequestBody Summoner summoner) {

        logger.info("SUMMONERS POST /summoner");

        Summoner newSummoner;

        newSummoner = summonerService.save(summoner);

        return ResponseEntity.status(HttpStatus.OK).body(newSummoner);
    }
}
