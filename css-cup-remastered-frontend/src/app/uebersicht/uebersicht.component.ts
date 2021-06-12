import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {TeamDto} from "../service/dto/team.dto";
import {Router} from "@angular/router";
import {AuthService} from "../service/auth/auth.service";

@Component({
  selector: 'app-uebersicht',
  templateUrl: './uebersicht.component.html',
  styleUrls: ['./uebersicht.component.scss']
})
export class UebersichtComponent implements OnInit {

  teams: TeamDto[]

  teamsVolleyball: TeamDto[]
  teamsFootball: TeamDto[]

  constructor(private http: HttpClient, private router: Router, public authService: AuthService) { }

  async ngOnInit(): Promise<void> {
    await this.fetchAllTeams(true)
  }

  async fetchAllTeams(isFootballCurrentDiscipline: boolean): Promise<void> {
    this.teamsVolleyball = []
    this.teamsFootball = []
    let teams = await this.http.get<TeamDto[]>(environment.remote + 'team/all').toPromise()
    teams.filter(team => {

      team.players.filter(player => {
        if (player.id == this.authService.user.id) {
          team.isUserPartOfTeam = true
        }
      })

      if (team.discipline == "FOOTBALL") {
        this.teamsFootball.push(team)
      } else {
        this.teamsVolleyball.push(team)
      }
    })

    if (isFootballCurrentDiscipline) {
      this.teams = this.teamsFootball;
    } else {
      this.teams = this.teamsVolleyball;
    }
  }

  async redirectToCreateTeamView(): Promise<void> {
    await this.router.navigate(['/team-create'])
  }

  switchDiscipline(event): void {
    if (event) {
      this.teams = this.teamsFootball
    } else {
      this.teams = this.teamsVolleyball
    }
  }

  async joinTeam(team: TeamDto): Promise<void> {
    console.log(team.id)
    await this.http.post(environment.remote + 'team/join/' + team.id, {}).toPromise()
    await this.fetchAllTeams(true);
  }

  async deleteTeam(team: TeamDto): Promise<void> {
    await this.http.delete(environment.remote + 'team/delete/' + team.id, {}).toPromise();
    await this.fetchAllTeams(true);
  }
}
