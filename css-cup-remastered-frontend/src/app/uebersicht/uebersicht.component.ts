import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {TeamDto} from "../service/dto/team.dto";

@Component({
  selector: 'app-uebersicht',
  templateUrl: './uebersicht.component.html',
  styleUrls: ['./uebersicht.component.scss']
})
export class UebersichtComponent implements OnInit {

  teams: TeamDto[];

  constructor(private http: HttpClient) { }

  async ngOnInit(): Promise<void> {
    console.log('Übersicht aufgerufen')
    this.teams = await this.http.get<TeamDto[]>(environment.remote + 'team/all').toPromise()
    console.log(this.teams);
  }

}
