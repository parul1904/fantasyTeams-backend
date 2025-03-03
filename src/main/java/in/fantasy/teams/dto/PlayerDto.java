package in.fantasy.teams.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private Integer playerId;
    private String playerName;
    private String nickName;
    private String playerImgUrl;
    private String role;
    private String country;
}