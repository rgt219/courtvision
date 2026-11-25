package com.courtvision.statsapi.player;

import com.courtvision.statsapi.team.Team;
import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer externalId;
    private String firstName;
    private String lastName;
    private String position;
    private String height;
    private Short weightLbs;
    private String jerseyNumber;
    private String college;
    private String country;
    private Short draftYear;
    private Short draftRound;
    private Short draftNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team; // current team; null for free agents

    protected Player() {
    }

    public Player(Integer externalId) {
        this.externalId = externalId;
    }

    public Integer getExternalId() {
        return externalId;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Short getWeightLbs() {
        return weightLbs;
    }

    public void setWeightLbs(Short weightLbs) {
        this.weightLbs = weightLbs;
    }

    public String getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(String jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Short getDraftYear() {
        return draftYear;
    }

    public void setDraftYear(Short draftYear) {
        this.draftYear = draftYear;
    }

    public Short getDraftRound() {
        return draftRound;
    }

    public void setDraftRound(Short draftRound) {
        this.draftRound = draftRound;
    }

    public Short getDraftNumber() {
        return draftNumber;
    }

    public void setDraftNumber(Short draftNumber) {
        this.draftNumber = draftNumber;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}