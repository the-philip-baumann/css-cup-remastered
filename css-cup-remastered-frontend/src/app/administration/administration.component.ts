import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {PlayerDto} from "../service/dto/player.dto";

@Component({
  selector: 'app-administration',
  templateUrl: './administration.component.html',
  styleUrls: ['./administration.component.scss']
})
export class AdministrationComponent implements OnInit {

  test = [1,2,3,4,5,6,7,8,9,10];

  players: PlayerDto[];

  constructor(private http: HttpClient) {}

  async ngOnInit(): Promise<void> {
    this.players = await this.http.get<PlayerDto[]>(environment.remote + "player/all").toPromise();
  }

  edit(): void {
    throw new Error('Not implemented yet');
  }

  delete(): void {
    throw new Error('Not implemented yet');
  }
}
