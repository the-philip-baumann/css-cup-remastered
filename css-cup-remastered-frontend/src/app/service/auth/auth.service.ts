import {Inject, Injectable} from "@angular/core";
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {JwtContentDto} from "../dto/jwt-content.dto";
import {LoginRequestDto} from "../dto/login-request.dto";
import {RegisterDto} from "../dto/register.dto";
import {LoginResonseDto} from "../dto/login-resonse.dto";
import {RegisterResponseDto} from "../dto/register-response.dto";

@Injectable({providedIn: "root"})
export class AuthService {

  localStorage;

  constructor(private http: HttpClient) {
    this.localStorage = window.localStorage;
  }

  public async login(loginDto: LoginRequestDto): Promise<void> {
    const response = await this.http.post<LoginResonseDto>(environment.remote + "/auth/login", loginDto).toPromise()
    this.localStorage.setItem(environment.remote, response.jwt)
  }

  public async register(registerDto: RegisterDto): Promise<void> {
    const response = await this.http.post<RegisterResponseDto>(environment.remote + "/auth/register", registerDto).toPromise();
    this.localStorage.setItem(environment.remote, response.jwt)
  }

  public async verifyAndReturnJwtContent(): Promise<JwtContentDto> {
    let bearerToken

    try {
      bearerToken = await this.localStorage.get(environment.bearer_token)
    } catch (e) {
      console.error(e)
    }
    if (bearerToken) {
      const tokenContent = this.resolveJwtPayload(bearerToken)
      return {
        token: bearerToken,
        verified: tokenContent.exp * 1000 >= Date.now() + 5 * 1000,
        exp: tokenContent.exp
      } as JwtContentDto
    }
    return null;
  }

  public resolveJwtPayload(jwt: string): any {
    const base64Url = jwt.split('.')[1];
    const base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.parse(atob(base64));
  }
}
