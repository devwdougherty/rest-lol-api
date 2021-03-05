package com.devwdougherty.restlolapi.service;

import com.devwdougherty.restlolapi.model.Summoner;
import com.devwdougherty.restlolapi.repository.SummonerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SummonerServiceImpl implements SummonerService {

    Logger logger = LoggerFactory.getLogger(SummonerServiceImpl.class);

    @Autowired
    private SummonerRepository summonerRepository;

    @Override
    public List<Summoner> findAll() {

        List<Summoner> summonerList = new ArrayList<>();

        try {

            summonerList = summonerRepository.findAll();
        } catch (Exception e) {

            logger.error("Exception: " + e.toString());
        }

        logger.info("Summoners returned: " + summonerList.toString());

        return summonerList;
    }

    @Override
    public Summoner findById(String id) {

        Summoner summoner = new Summoner();

        try {

            summoner = summonerRepository.findById(id).get();
        } catch (Exception e) {

            logger.error("Exception: " + e.toString());
        }

        logger.info("Summoner returned: " + summoner.toString());

        return summoner;
    }

    @Override
    public Summoner save(Summoner newSummoner) {

        Summoner summoner = new Summoner();

        try {

            summoner = summonerRepository.save(newSummoner);
        } catch (Exception e) {

            logger.error("Exception: " + e.toString());
        }

        logger.info("Summoner saved!");

        return summoner;
    }
}
