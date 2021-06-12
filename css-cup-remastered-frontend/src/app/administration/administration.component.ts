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


  players: PlayerDto[] = []
  backup: PlayerDto[]
  needle: string;

  constructor(private http: HttpClient) {}

  async ngOnInit(): Promise<void> {
    await this.fetchAllPlayers()
  }

  async fetchAllPlayers(): Promise<void> {
    this.backup = await this.http.get<PlayerDto[]>(environment.remote + "player/all").toPromise()
    this.backup.forEach(player => {
      console.log(player)
      this.players.push(player);
    })

    console.log(this.players)
  }

  async delete(player: PlayerDto): Promise<void> {
    console.log(player.id)
    await this.http.delete(environment.remote + "player/" + player.id).toPromise()
    await this.fetchAllPlayers()
  }

  search() {

    this.players = [];

    if (this.needle.charAt(0) == '*') {
      this.backup.filter(player => {
        let needleClone = this.needle.slice(1, this.needle.length).toLowerCase()
        console.log(needleClone)
        if (player.role.toLowerCase().includes('role_' + needleClone) || player.function.toLowerCase().includes(needleClone) || (player.team && player.team.name.toLowerCase().includes(needleClone))) {
          this.players.push(player)
        }
      })
    } else {
      this.backup.filter(player => {
        if (player.firstname.toLowerCase().includes(this.needle.toLowerCase()) || player.lastname.toLowerCase().includes(this.needle.toLowerCase())) {
          this.players.push(player);
        }
      })
    }

  }
}
