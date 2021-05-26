import {TeamDto} from "./team.dto";

export interface PlayerDto {
  id: string;
  firstname: string;
  lastname: string;
  function: string;
  role: string;
  team: TeamDto;
}
