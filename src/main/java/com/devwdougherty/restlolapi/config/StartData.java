package com.devwdougherty.restlolapi.config;

import com.devwdougherty.restlolapi.model.Summoner;
import com.devwdougherty.restlolapi.repository.SummonerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StartData implements CommandLineRunner {

    @Autowired
    private SummonerRepository summonerRepository;

    Logger logger = LoggerFactory.getLogger(StartData.class);

    @Override
    public void run(String... args) throws Exception {

        List<Summoner> summonerList = new ArrayList<>();

        summonerRepository.deleteById("O4--yezkMbmweikVsGc8apSid3rrVeUhhLowA8ApgjfqTw");
        summonerRepository.deleteById("Nx_q9b5Yf2THp3XvGXushsmGWiBuH1KVkJSpFtP294NlXw");

        Summoner raveenNight = new Summoner(
                "O4--yezkMbmweikVsGc8apSid3rrVeUhhLowA8ApgjfqTw",
                "cEEyB_0fmTBlkYw_XFEh-j3hdYxSqSP8BCmafnZAtSjJ",
                "HelsncDiql5jNlcxiOE5vW50srVMMaEXxDA0NbiSBip94TvI6n-8QNZAa9MOAcsBmMmBa1zwjcnF8A",
                "RaveenNight",
                4271,
                Long.valueOf("1614070460000"),
                227);

        Summoner bodDougherty = new Summoner(
                "Nx_q9b5Yf2THp3XvGXushsmGWiBuH1KVkJSpFtP294NlXw",
                "tJKVuQeE68ak1DnvzK2p74roE3MwU_fawDPNZorOgLvr",
                "bKP9ANuRIe95PgdHIQwJ_-xJArgbWzAPxOaD_AZHW-tLuv2R3bJ-7AZ4foI8nsESS8QqWT7xw435uA",
                "BOD Dougherty",
                9,
                Long.valueOf("1614698928000"),
                193);

        summonerList.add(raveenNight);
        summonerList.add(bodDougherty);

        summonerList.stream().map(summonerRepository::save).collect(Collectors.toList());

        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
    }
}
