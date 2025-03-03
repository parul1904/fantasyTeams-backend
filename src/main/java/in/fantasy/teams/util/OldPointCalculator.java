package in.fantasy.teams.util;

import in.fantasy.teams.dto.StatsDto;

public class OldPointCalculator {

    public static int calculatePoints(StatsDto statsDto, String role) {
        int points = 4;

        points += calculateAnnouncedPlayerPoint(statsDto);

        // Batting Points
        points += calculateBattingPoints(statsDto, role);

        // Bowling Points
        points += calculateBowlingPoints(statsDto);

        // Fielding Points
        points += calculateFieldingPoints(statsDto);

        return points;
    }

    private static int calculateAnnouncedPlayerPoint(StatsDto statsDto) {
        int points = 0;
        if (statsDto.getIsImpactPlayer()) {
            points = -4;
        }
        return points;
    }

    private static int calculateBattingPoints(StatsDto statsDto, String role) {
        int points = 0;
        points += null != statsDto.getRunsScored() ? statsDto.getRunsScored() : 0;
        points += null != statsDto.getFours() ? statsDto.getFours() : 0;
        points += null != statsDto.getSixes() ? statsDto.getSixes() * 2 : 0;

        if(null!= statsDto.getRunsScored() && statsDto.getRunsScored() >= 30) {
            if (statsDto.getRunsScored() < 50) points += 4;
            if (statsDto.getRunsScored() < 99) points += 8;
            if (statsDto.getRunsScored() >= 100) points += 16;
        }

        if (!"Bowler".equalsIgnoreCase(role)) {
            if (null != statsDto.getRunsScored() && statsDto.getRunsScored() == 0) {
                points -= 2;
            } else if (null != statsDto.getRunsScored() && statsDto.getRunsScored() > 0 && statsDto.getBallFaced() >= 10) {
                if (statsDto.getStrikeRate() > 170.01) points += 6;
                else if (statsDto.getStrikeRate() >= 150.01 && statsDto.getStrikeRate() <= 170) points += 4;
                else if (statsDto.getStrikeRate() >= 130.01 && statsDto.getStrikeRate() <= 150) points += 2;
                else if (statsDto.getStrikeRate() >= 60 && statsDto.getStrikeRate() <= 70) points -= 2;
                else if (statsDto.getStrikeRate() >= 50 && statsDto.getStrikeRate() <= 59.99) points -= 4;
                else if (statsDto.getStrikeRate() < 50) points -= 6;
            }
        }
        return points;
    }

    private static int calculateBowlingPoints(StatsDto statsDto) {
        int points = 0;
        points += null!=statsDto.getTotalWickets() ? statsDto.getTotalWickets() * 25 : 0;
        if (null != statsDto.getBowledLbw() && statsDto.getBowledLbw() > 0) {
            points += statsDto.getBowledLbw() * 8;
        }
        if (null!= statsDto.getTotalWickets() && statsDto.getTotalWickets() >= 2) {
            points += 2;
        }
        if (null!= statsDto.getTotalWickets() && statsDto.getTotalWickets() >= 3) {
            points += 4;
        }
        if (null != statsDto.getTotalWickets() && statsDto.getTotalWickets() >= 4) {
            points += 8;
        }
        if (null != statsDto.getTotalWickets() && statsDto.getTotalWickets() >= 5) {
            points += 16;
        }

        points += null != statsDto.getMaiden() ? statsDto.getMaiden() * 8 : 0;

        if (null != statsDto.getOvers() && statsDto.getOvers() >= 2) {
            if (statsDto.getEconomyRate() <= 4) points += 6;
            else if (statsDto.getEconomyRate() >= 4.01 && statsDto.getEconomyRate() < 5) points += 4;
            else if (statsDto.getEconomyRate() >= 5.01 && statsDto.getEconomyRate() < 6) points += 2;
            else if (statsDto.getEconomyRate() >= 10.01 && statsDto.getEconomyRate() < 11) points -= 4;
            else if (statsDto.getEconomyRate() >= 11.01) points -= 6;
        }

        return points;
    }

    private static int calculateFieldingPoints(StatsDto statsDto) {
            int points = 0;
        points += null != statsDto.getCatchTaken() ? statsDto.getCatchTaken() * 8 : 0;
        points += null != statsDto.getStumping() ? statsDto.getStumping() * 12 : 0;
        points += null != statsDto.getDirectRunout() ? statsDto.getDirectRunout() * 12 : 0;
        points += null != statsDto.getInDirectRunout() ? statsDto.getInDirectRunout() * 6 : 0;

        return points;
    }
}
