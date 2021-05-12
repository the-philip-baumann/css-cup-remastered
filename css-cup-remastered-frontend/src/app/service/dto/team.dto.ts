import {PlayerInfoDto} from "./player-info.dto";

export class TeamDto {
  name: string;
  discipline: string;
  players: PlayerInfoDto[];
}
