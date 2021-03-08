package com.devwdougherty.restlolapi.service;

import com.devwdougherty.restlolapi.dto.SummonerDTO;
import com.devwdougherty.restlolapi.model.Summoner;

import java.util.List;

public interface SummonerService {

    List<SummonerDTO> findAll();

    SummonerDTO findById(String id);

    Summoner save(Summoner summoner);
}
