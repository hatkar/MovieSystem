import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AuthentificationService } from 'src/app/services/authentification.service';
@Injectable({
    providedIn: 'root'
  })
  export class JwtInterceptor implements HttpInterceptor {
    BASEURL : string="http://localhost:9292";
    constructor(private authenticationService: AuthentificationService) { }
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // add auth header with jwt if user is logged in and request is to api url
        const currentUser = this.authenticationService.currentUserValue;
        const isLoggedIn = currentUser && currentUser.token;
        const isApiUrl = request.url.startsWith(this.BASEURL);
        if (isLoggedIn ) {
            request = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${currentUser.token}`
                }
            });
        }

        return next.handle(request);
    }
}