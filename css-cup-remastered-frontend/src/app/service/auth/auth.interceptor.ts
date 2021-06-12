import {Injectable} from "@angular/core";
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {from, Observable} from "rxjs";
import {flatMap, map, tap} from "rxjs/operators";
import {AuthService} from "./auth.service";
import {JwtContentDto} from "../dto/jwt-content.dto";
import {Router} from "@angular/router";

@Injectable()
export class AuthInterceptor {

  constructor(private authService: AuthService, private router: Router) {

  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    console.log('interceptor')
    return from(this.authService.verifyAndReturnJwtContent()).pipe(
      map((res: JwtContentDto) => {
        if (res) {
          return req.clone({
            headers: req.headers.set('Authorization', 'Bearer ' + res.token)
          });
        }
        return req;

      }), flatMap((clonedRequest) => {
        return next.handle(clonedRequest).pipe(tap(() => {
        }, (e) => {
          if (e instanceof HttpErrorResponse && (e.status === 401 || e.status === 403)) {
            this.router.navigate(['/auth/login']);
          }
          return;
        }));
      })
    );
  }



}
