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


  players: PlayerDto[]

  constructor(private http: HttpClient) {}

  async ngOnInit(): Promise<void> {
    await this.fetchAllPlayers()
  }

  async fetchAllPlayers(): Promise<void> {
    this.players = await this.http.get<PlayerDto[]>(environment.remote + "player/all").toPromise()
  }

  edit(): void {
    throw new Error('Not implemented yet')
  }

  async delete(player: PlayerDto): Promise<void> {
    console.log(player.id)
    await this.http.delete(environment.remote + "player/" + player.id).toPromise()
    await this.fetchAllPlayers()
  }
}
