package com.devwdougherty.restlolapi.repository;

import com.devwdougherty.restlolapi.model.Summoner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SummonerRepository extends MongoRepository<Summoner, String> {

}
