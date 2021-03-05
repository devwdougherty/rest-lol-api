package com.devwdougherty.restlolapi.service;

import com.devwdougherty.restlolapi.model.Summoner;

import java.util.List;

public interface SummonerService {

    List<Summoner> findAll();

    Summoner findById(String id);

    Summoner save(Summoner summoner);
}
