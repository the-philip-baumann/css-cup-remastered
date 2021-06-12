import { Component, OnInit } from '@angular/core';
import {TeamAddDto} from "../service/dto/team-add.dto";
import {PlayerInfoDto} from "../service/dto/player-info.dto";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../service/auth/auth.service";

@Component({
  selector: 'app-create-team',
  templateUrl: './create-team.component.html',
  styleUrls: ['./create-team.component.scss']
})
export class CreateTeamComponent implements OnInit {

  dropdown = false

  teamView = {
    teamName: 'asdf',
    discipline: 'FOOTBALL',
    playerSolo: [],
    playerTeam: []
  }

  team: TeamAddDto;
  displayError: boolean

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) {
    this.displayError = false;
  }


  async ngOnInit(): Promise<void> {
    this.teamView.playerSolo = await this.http.get<PlayerInfoDto[]>(environment.remote + "player/solo").toPromise()
  }

  addPlayerToTeam(player: PlayerInfoDto): void {
    let index = this.teamView.playerSolo.indexOf(player, 0)
    this.teamView.playerSolo.splice(index, 1)
    this.teamView.playerTeam.push(player)
  }

  removePlayerFromTeam(player: PlayerInfoDto): void {
    let index = this.teamView.playerTeam.findIndex(p => p.id == player.id)
    this.teamView.playerTeam.splice(index, 1)
    this.teamView.playerSolo.push(player)
  }

  async submit(): Promise<void> {
    this.team = new TeamAddDto()
    this.team.id = this.authService.user.id
    this.team.teamName = this.teamView.teamName
    this.team.discipline = this.teamView.discipline
    this.team.players = this.teamView.playerTeam.filter(p => p.id)

    if (this.team.teamName.length > 3 && this.team.teamName.length < 30) {

      console.log(this.team)
      console.log(this.authService.user)

      await this.http.post<void>(environment.remote + "team/add", this.team).toPromise()
      console.log('test')
      await this.router.navigate(['/team-uebersicht'])
    } else {
      this.displayError = true;
    }


  }

  toggleDropDown() {
    this.dropdown = !this.dropdown
  }
}
