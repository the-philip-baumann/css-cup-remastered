import {PlayerInfoDto} from "./player-info.dto";

export interface TeamDto {
  name: string;
  discipline: string;
  players: PlayerInfoDto[];
}
