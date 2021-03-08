package com.devwdougherty.restlolapi.service;

import com.devwdougherty.restlolapi.dto.SummonerDTO;
import com.devwdougherty.restlolapi.exception.ResourceNotFoundException;
import com.devwdougherty.restlolapi.model.Summoner;
import com.devwdougherty.restlolapi.repository.SummonerRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SummonerServiceImpl implements SummonerService {

    Logger logger = LoggerFactory.getLogger(SummonerServiceImpl.class);

    @Autowired
    private SummonerRepository summonerRepository;

    /**
     * Model Mapper to handle with DTOs and Models.
     */
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<SummonerDTO> findAll() {

        List<Summoner> summonerList;
        List<SummonerDTO> summonerDTOList = new ArrayList<>();

        summonerList = summonerRepository.findAll();

        summonerDTOList = summonerList.stream().map(s -> modelMapper.map(s, SummonerDTO.class)).collect(Collectors.toList());

        logger.info("Summoners returned: " + summonerList.toString());

        return summonerDTOList;
    }

    @Override
    public SummonerDTO findById(String summonerId) {

        Summoner summoner;
        SummonerDTO summonerDTO;

        summoner = summonerRepository.findById(summonerId).orElseThrow(() -> new ResourceNotFoundException("Summoner ID: + " + summonerId + "not found."));

        summonerDTO = modelMapper.map(summoner, SummonerDTO.class);

        /* Model Mapper conversion validation. */
        modelMapper.createTypeMap(Summoner.class, SummonerDTO.class);
        modelMapper.validate();

        logger.info("Summoner returned: " + summoner.toString());

        return summonerDTO;
    }

    @Override
    public Summoner save(Summoner newSummoner) {

        Summoner summoner = new Summoner();

        summoner = summonerRepository.save(newSummoner);

        logger.info("Summoner saved: " + summoner.toString());

        return summoner;
    }
}
