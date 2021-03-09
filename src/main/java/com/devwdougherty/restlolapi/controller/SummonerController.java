package com.devwdougherty.restlolapi.controller;

import com.devwdougherty.restlolapi.dto.SummonerDTO;
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
    @ApiResponse(responseCode = "200", description = "Summoner found", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = SummonerDTO.class))})
    @ApiResponse(responseCode = "404", description = "Summoner not found", content = {@Content})
    @ApiResponse(responseCode = "500", description = "Internal Server Error on the operation", content = {@Content})
    public ResponseEntity<SummonerDTO> getSummonerById(@PathVariable String summonerId) {

        logger.info("SUMMONERS GET /summonerId");

        SummonerDTO summonerDTO;

        summonerDTO = summonerService.findById(summonerId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(summonerDTO);
    }

    @GetMapping
    @Operation(summary = "Get a list of Summoners")
    @ApiResponse(responseCode = "200", description = "Summoners list returned", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = SummonerDTO.class))})
    @ApiResponse(responseCode = "500", description = "Internal Server Error on the operation", content = {@Content})
    public ResponseEntity<List<SummonerDTO>> getAllSummoners() {

        logger.info("SUMMONERS GET ALL");

        List<SummonerDTO> summonerList;

        summonerList = summonerService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(summonerList);
    }

    @PostMapping
    @Operation(summary = "Crate a new summoner")
    @ApiResponse(responseCode = "201", description = "Summoner is created", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = SummonerDTO.class))})
    @ApiResponse(responseCode = "500", description = "Internal Server Error on the operation", content = {@Content})
    public ResponseEntity<SummonerDTO> saveSummoner(@Valid @RequestBody SummonerDTO summonerDTO) {

        logger.info("SUMMONERS POST /summoner");

        SummonerDTO newSummoner;

        newSummoner = summonerService.save(summonerDTO);

        return ResponseEntity.status(HttpStatus.OK).body(newSummoner);
    }

    @PutMapping(value = "{summonerId}")
    @Operation(summary = "Update a summoner by ID")
    @ApiResponse(responseCode = "200", description = "Summoner updated", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = SummonerDTO.class))})
    @ApiResponse(responseCode = "404", description = "Summoner not found", content = {@Content})
    @ApiResponse(responseCode = "500", description = "Internal Server Error on the operation", content = {@Content})
    public ResponseEntity<SummonerDTO> updateSummoner(@PathVariable String summonerId, @Valid @RequestBody SummonerDTO summonerDTO) {

        logger.info("SUMMONERS PUT /summonerId");

        SummonerDTO newSummoner;

        newSummoner = summonerService.updateWholeSummoner(summonerId, summonerDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(newSummoner);
    }

    @DeleteMapping(value = "{summonerId}")
    @Operation(summary = "Delete summoner by ID")
    @ApiResponse(responseCode = "200", description = "Summoner deleted", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = SummonerDTO.class))})
    @ApiResponse(responseCode = "404", description = "Summoner not found", content = {@Content})
    @ApiResponse(responseCode = "500", description = "Internal Server Error on the operation", content = {@Content})
    public ResponseEntity deleteSummonerById(@PathVariable String summonerId) {

        logger.info("SUMMONERS DELETE /summonerId");

        summonerService.deleteSummonerById(summonerId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
