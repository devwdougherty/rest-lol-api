package com.devwdougherty.restlolapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Summoners")
public class Summoner {

    @Id
    private String id;

    private String accountId;

    private String puuId;

    private String name;

    private Integer profileIconId;

    private Long revisionDate;

    private Integer summonerLevel;

    public Summoner() {

    }

    public Summoner(Summoner summoner) {
        this.id = summoner.getId();
        this.accountId = summoner.getAccountId();
        this.puuId = summoner.getPuuId();
        this.name = summoner.getName();;
        this.profileIconId = summoner.getProfileIconId();;
        this.revisionDate = summoner.getRevisionDate();;
        this.summonerLevel = summoner.getSummonerLevel();;
    }

    public Summoner(String id, String accountId, String puuId, String name, Integer profileIconId, Long revisionDate, Integer summonerLevel) {
        this.id = id;
        this.accountId = accountId;
        this.puuId = puuId;
        this.name = name;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPuuId() {
        return puuId;
    }

    public void setPuuId(String puuId) {
        this.puuId = puuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(Integer profileIconId) {
        this.profileIconId = profileIconId;
    }

    public Long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public Integer getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(Integer summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    @Override
    public String toString() {
        return "Summoner{" +
                "id='" + id + '\'' +
                ", accountId='" + accountId + '\'' +
                ", puuId='" + puuId + '\'' +
                ", name='" + name + '\'' +
                ", profileIconId=" + profileIconId +
                ", revisionDate=" + revisionDate +
                ", summonerLevel=" + summonerLevel +
                '}';
    }
}
