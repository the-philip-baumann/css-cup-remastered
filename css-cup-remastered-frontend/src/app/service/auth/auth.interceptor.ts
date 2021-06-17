import {Injectable} from "@angular/core";
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";
import {AuthService} from "./auth.service";
import {JwtContentDto} from "../dto/jwt-content.dto";
import {Router} from "@angular/router";

@Injectable()
export class AuthInterceptor {

  constructor(private authService: AuthService, private router: Router) {

  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let res: JwtContentDto = this.authService.verifyAndReturnJwtContent()
    let clonedRequest = req;

    if (res && res.verified) {
      clonedRequest = req.clone({
        headers: req.headers.set('Authorization', 'Bearer ' + res.token)
      });
    }

    return next.handle(clonedRequest).pipe(tap(() => {
    }, (e) => {
      if (e instanceof HttpErrorResponse && (e.status === 401 || e.status === 403)) {
        // this.router.navigate(['/auth/login']);
        console.log('Unauthorized')
      }
      return;
    }))
  }
}
