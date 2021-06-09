import {Injectable} from "@angular/core";
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {from, Observable} from "rxjs";
import {flatMap, map, tap} from "rxjs/operators";
import {AuthService} from "./auth.service";
import {JwtContentDto} from "../dto/jwt-content.dto";
import {Router} from "@angular/router";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService, private router: Router) {

  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<unknown>> {
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
        }, (err) => {
          if (err instanceof HttpErrorResponse && err.status === 401) {
            this.router.navigate(['/auth/login']);
          }
          return;
        }));
      })
    );
  }



}
