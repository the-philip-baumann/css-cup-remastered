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
  displayResponseError: boolean;
  tempDisciple: string = 'FOOTBALL'

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) {
    this.displayError = false;
  }


  async ngOnInit(): Promise<void> {
    this.teamView.playerSolo = await this.http.get<PlayerInfoDto[]>(environment.remote + "player/solo").toPromise()
    let player = this.teamView.playerSolo.find(player => player.id == this.authService.getUser().id)
    let index = this.teamView.playerSolo.indexOf(player)
    this.teamView.playerSolo.splice(index, 1)
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
    this.team.teamName = this.teamView.teamName
    this.team.discipline = this.tempDisciple
    this.team.players = []
    this.teamView.playerTeam.forEach(p => {
      this.team.players.push(p.id)
    })
    this.team.players.push(this.authService.user.id)

    console.log(this.team.discipline)

    if (this.team.teamName.length > 3 && this.team.teamName.length < 16 && this.team.players.length <= 10) {
      try {
        const response = await this.http.post(environment.remote + "team/add", this.team).toPromise()
        await this.router.navigate(['/uebersicht'])
        console.log(response)
      } catch (e) {
        this.displayError = false
        this.displayResponseError = true;
      }
    } else {
      this.displayError = true;
    }


  }

  toggleDropDown() {
    this.dropdown = !this.dropdown
  }

  switchDiscipline($event: boolean) {
    if ($event) {
      this.tempDisciple = 'FOOTBALL'
    } else {
      this.tempDisciple = 'VOLLEYBALL'
    }

    console.log(this.tempDisciple)
  }
}
