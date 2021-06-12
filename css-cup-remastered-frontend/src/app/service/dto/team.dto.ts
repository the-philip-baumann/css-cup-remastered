import {PlayerInfoDto} from "./player-info.dto";

export interface TeamDto {
  id: number
  name: string
  discipline: string
  players: PlayerInfoDto[]
  isUserPartOfTeam: boolean
}
