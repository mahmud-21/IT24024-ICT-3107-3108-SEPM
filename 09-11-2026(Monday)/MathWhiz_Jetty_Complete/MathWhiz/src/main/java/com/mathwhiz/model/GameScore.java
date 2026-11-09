package com.mathwhiz.model;
import java.time.LocalDateTime;
public record GameScore(long id,String playerName,int score,String difficulty,LocalDateTime playedAt) {}
