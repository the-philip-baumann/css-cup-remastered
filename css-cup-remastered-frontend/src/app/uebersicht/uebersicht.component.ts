import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {TeamDto} from "../service/dto/team.dto";
import {Router} from "@angular/router";

@Component({
  selector: 'app-uebersicht',
  templateUrl: './uebersicht.component.html',
  styleUrls: ['./uebersicht.component.scss']
})
export class UebersichtComponent implements OnInit {

  teams: TeamDto[];

  constructor(private http: HttpClient, private router: Router) { }

  async ngOnInit(): Promise<void> {
    this.teams = await this.http.get<TeamDto[]>(environment.remote + 'team/all').toPromise()
  }

  public async redirectToCreateTeamView(): Promise<void> {
    await this.router.navigate(['/team-create'])
  }

}
