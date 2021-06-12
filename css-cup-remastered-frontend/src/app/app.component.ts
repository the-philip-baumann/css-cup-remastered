import {Component, OnInit} from '@angular/core';
import {AuthService} from "./service/auth/auth.service";
import {JwtContentDto} from "./service/dto/jwt-content.dto";
import {PlayerCredentialsDto} from "./service/dto/player-credentials.dto";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'css-cup-remastered-frontend';

  constructor(private authService: AuthService) {

  }

  async ngOnInit(): Promise<void> {
    const tokenContent: JwtContentDto = await this.authService.verifyAndReturnJwtContent();

    if (tokenContent) {
      this.authService.user = this.authService.mapTokenContentToUser(tokenContent)
    } else {
      this.authService.user = new PlayerCredentialsDto('ROLE_UNDECIDED')
    }
  }



}
