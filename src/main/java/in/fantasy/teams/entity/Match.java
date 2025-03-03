package in.fantasy.teams.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Integer matchId;

    @ManyToOne
    @JoinColumn(name = "season_id",  referencedColumnName = "season_id")
    private Season season;

    @Column(name = "match_no", nullable = false)
    private Integer matchNo;

    @ManyToOne
    @JoinColumn(name = "team1_id", referencedColumnName = "team_id")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id",  referencedColumnName = "team_id")
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "venue_id",  referencedColumnName = "venue_id")
    private Venue venue;

    @Column(name="match_date", nullable = false)
    private LocalDate matchDate;

    @Column(name="match_time", nullable = false)
    private String matchTime;

    @ManyToOne
    @JoinColumn(name = "winner_team_id", referencedColumnName = "team_id")
    private Team winnerTeam;

    @Column(name="winning_margin", nullable = false)
    private String winningMargin;

    @ManyToOne
    @JoinColumn(name = "player_of_the_match", referencedColumnName = "player_id")
    private Player playerOfTheMatch;

    @ManyToOne
    @JoinColumn(name = "mvp_id", referencedColumnName = "player_id")
    private Player mvpId;
} 