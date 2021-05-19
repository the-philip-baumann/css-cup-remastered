import {TeamDto} from "./team.dto";

export interface PlayerDto {
  firstname: string;
  lastname: string;
  function: string;
  role: string;
  team: TeamDto[];
}
