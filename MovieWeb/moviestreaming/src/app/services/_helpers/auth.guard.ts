import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { AuthentificationService } from "../authentification.service";

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate {
   
   /**
    *
    */
   constructor( private router :Router,
    private authenticationService: AuthentificationService) {
  
    
   }
   canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const currentUser =this.authenticationService.currentUserValue;
    console.log("Intercepted User :")
    console.log(currentUser);
    if(currentUser){
        console.log('NOT LOGGED intercepted by guard1')
        
        //check if route is restricetd by role
        if(route.data["roles"] && route.data["roles"][0]!=currentUser.role)
        {
            console.log('intercepted by guard1 UNHAUTORIZED')
           // this.router.navigate(['/']);
           this.router.navigate(['/unhautorized'],{queryParams: {returnUrl :state.url}});
            return false;
        }
        //authorised 
        console.log('Authorized intercepted by guard2 and passed ')
        return true;
    }
    //not logged alors redirect vers login page avec return url
    this.router.navigate(['/unhautorized'],{queryParams: {returnUrl :state.url}});
    return false;
    }
}