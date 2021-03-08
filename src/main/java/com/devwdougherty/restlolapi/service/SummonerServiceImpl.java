package com.devwdougherty.restlolapi.service;

import com.devwdougherty.restlolapi.exception.ResourceNotFoundException;
import com.devwdougherty.restlolapi.model.Summoner;
import com.devwdougherty.restlolapi.repository.SummonerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SummonerServiceImpl implements SummonerService {

    Logger logger = LoggerFactory.getLogger(SummonerServiceImpl.class);

    @Autowired
    private SummonerRepository summonerRepository;

    @Override
    public List<Summoner> findAll() {

        List<Summoner> summonerList = new ArrayList<>();

        summonerList = summonerRepository.findAll();

        logger.info("Summoners returned: " + summonerList.toString());

        return summonerList;
    }

    @Override
    public Summoner findById(String summonerId) {

        Summoner summoner = new Summoner();

        summoner = summonerRepository.findById(summonerId).orElseThrow(() -> new ResourceNotFoundException("Summoner ID: + " + summonerId + "not found."));

        logger.info("Summoner returned: " + summoner.toString());

        return summoner;
    }

    @Override
    public Summoner save(Summoner newSummoner) {

        Summoner summoner = new Summoner();

        summoner = summonerRepository.save(newSummoner);

        logger.info("Summoner saved: " + summoner.toString());

        return summoner;
    }
}
