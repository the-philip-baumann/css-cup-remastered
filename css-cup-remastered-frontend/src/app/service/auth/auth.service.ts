import {Injectable} from "@angular/core";
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {JwtContentDto} from "../dto/jwt-content.dto";
import {LoginRequestDto} from "../dto/login-request.dto";
import {RegisterDto} from "../dto/register.dto";
import {LoginResponseDto} from "../dto/login-response.dto";
import {AuthState} from "./auth.state";
import {PlayerCredentialsDto} from "../dto/player-credentials.dto";
import {Role} from "./role.enum";

@Injectable({providedIn: "root"})
export class AuthService {

  localStorage;
  user: PlayerCredentialsDto;

  constructor(private http: HttpClient) {
    this.localStorage = window.localStorage;
  }

  public async login(loginDto: LoginRequestDto): Promise<AuthState> {
    let authState;
    let response;

    try {
      response = await this.http.post<LoginResponseDto>(environment.remote + "auth/login", loginDto).toPromise()
      authState = AuthState.success(response)
    } catch (e) {
      authState = AuthState.failure(e);
    }

    if (authState.ok) {
      this.localStorage.setItem(environment.bearer_token, response.jwt)
      const tokenContent: JwtContentDto = await this.verifyAndReturnJwtContent()
      this.user = this.mapTokenContentToUser(tokenContent)
      console.log('setToken', this.user)
    }

    return authState;
  }


  public async register(registerDto: RegisterDto): Promise<AuthState> {
    let authState;
    let response;

    try {
      response = await this.http.post<LoginResponseDto>(environment.remote + "auth/register", registerDto).toPromise();
      authState = AuthState.success(response.jwt)
    } catch (e) {
      authState = AuthState.failure(response.jwt)
    }

    if (authState.ok) {
      this.localStorage.setItem(environment.bearer_token, response.jwt)
    }
    return authState
  }

  verifyAndReturnJwtContent(): JwtContentDto {
    let bearerToken

    try {
      bearerToken = this.localStorage.getItem(environment.bearer_token)
    } catch (e) {
      console.error(e)
    }


    if (bearerToken) {
      const tokenContent = this.resolveJwtPayload(bearerToken)

      return {
        token: bearerToken,
        verified: tokenContent.expiryDate * 1000 >= Date.now() + 5 * 1000,
        exp: tokenContent.expiryDate,
        id: tokenContent.id,
        firstname: tokenContent.firstname,
        lastname: tokenContent.lastname,
        email: tokenContent.email,
        role: tokenContent.role,
        discipline: tokenContent.discipline,
        function: tokenContent.function
      } as JwtContentDto
    }
    return null;
  }

  mapTokenContentToUser(tokenContent: JwtContentDto) {
    return new PlayerCredentialsDto(tokenContent.role, tokenContent.id, tokenContent.firstname, tokenContent.lastname, tokenContent.email, tokenContent.function, tokenContent.discipline);
  }

  public resolveJwtPayload(jwt: string): any {
    const base64Url = jwt.split('.')[1];
    const base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.parse(atob(base64));
  }

  hasAuthority(...authorities: string[]) {
    let jwt: JwtContentDto
    if (!this.user) {
      jwt = this.verifyAndReturnJwtContent()
      if (jwt && jwt.verified) {
        this.user = new PlayerCredentialsDto(jwt.role, jwt.id, jwt.firstname, jwt.lastname, jwt.email, jwt.function, jwt.discipline)
      } else {
        this.user = null
      }
    }

    if (!this.user) {
      return authorities.indexOf(Role.ROLE_UNDECIDED) != -1
    }

    return authorities.indexOf(this.user.role) != -1
  }

  logout() {
    window.localStorage.removeItem(environment.bearer_token)
    this.user = null
    location.reload()
  }

  getUser(): PlayerCredentialsDto {
    if (!this.user) {
      return this.verifyAndReturnJwtContent()
    }

    return this.user
  }
}
