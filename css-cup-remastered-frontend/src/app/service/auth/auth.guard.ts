import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {from, Observable} from "rxjs";
import {map} from "rxjs/operators";
import {JwtContentDto} from "../dto/jwt-content.dto";
import {AuthService} from "./auth.service";
import {PlayerCredentialsDto} from "../dto/player-credentials.dto";
import {Role} from "./role.enum";
import {environment} from "../../../environments/environment";

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    console.log('service', this.authService.user)
    console.log('hasAuthority', this.authService.user.hasAuthority(route.data.roles))
    console.log('authorities', route.data.roles)
    if (this.authService.user && this.authService.user.hasAuthority(route.data.roles)) {
      return true
    }

    return from(this.authService.verifyAndReturnJwtContent()).pipe(
      map((res: JwtContentDto) => {

        if (res && res.verified && route.data.roles.indexOf(res.role) != -1) {
          return true;
        }
        try {
          window.localStorage.removeItem(environment.bearer_token);
        } catch (e) {
          console.log(e)
        }

        this.authService.user = new PlayerCredentialsDto(Role.ROLE_UNDECIDED);

        this.router.navigate(['/auth/login']);
        return false;
      })
    );
  }

}
