import { Component, OnInit } from '@angular/core';
import {TeamAddDto} from "../service/dto/team-add.dto";
import {PlayerInfoDto} from "../service/dto/player-info.dto";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-team',
  templateUrl: './create-team.component.html',
  styleUrls: ['./create-team.component.scss']
})
export class CreateTeamComponent implements OnInit {

  teamView = {
    teamName: 'asdf',
    discipline: 'FOOTBALL',
    playerSolo: [],
    playerTeam: []
  }

  team: TeamAddDto;

  constructor(private http: HttpClient, private router: Router) {
  }


  async ngOnInit(): Promise<void> {
    this.teamView.playerSolo = await this.http.get<PlayerInfoDto[]>(environment.remote + "player/solo").toPromise()
  }

  setDiscipline(): void {
    this.teamView.discipline = "FOOTBALL"
  }

  addPlayerToTeam(player: PlayerInfoDto): void {
    this.teamView.playerTeam.push(player)
  }

  removePlayerFromTeam(player: PlayerInfoDto): void {
    let index = this.teamView.playerTeam.findIndex(p => p.id == player.id)
    this.teamView.playerTeam.splice(index, 1)
    this.teamView.playerSolo.push(player)
  }

  async submit(): Promise<void> {
    this.team = new TeamAddDto()
    this.team.teanName = this.teamView.teamName
    this.team.discipline = this.teamView.discipline
    this.team.players = this.teamView.playerTeam.filter(p => p.id)

    await this.http.post<void>(environment.remote + "team/add", this.team).toPromise()
    await this.router.navigate(['/team-uebersicht'])
  }
}
