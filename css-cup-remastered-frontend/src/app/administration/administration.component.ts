import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {PlayerDto} from "../service/dto/player.dto";
import {Router} from "@angular/router";

@Component({
  selector: 'app-administration',
  templateUrl: './administration.component.html',
  styleUrls: ['./administration.component.scss']
})
export class AdministrationComponent implements OnInit {


  players: PlayerDto[]

  constructor(private http: HttpClient, private router: Router) {}

  async ngOnInit(): Promise<void> {
    await this.fetchAllPlayers()
  }

  async fetchAllPlayers(): Promise<void> {
    this.players = await this.http.get<PlayerDto[]>(environment.remote + "player/all").toPromise()
  }

  async edit(): Promise<void> {
    await this.router.navigate(["edit-player"]);
  }

  async delete(player: PlayerDto): Promise<void> {
    console.log(player.id)
    await this.http.delete(environment.remote + "player/" + player.id).toPromise()
    await this.fetchAllPlayers()
  }
}
