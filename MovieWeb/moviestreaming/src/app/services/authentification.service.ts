import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { LoginRequest, User } from '../models/MovieDTO';

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;
  //private currentUserSubject? = new BehaviorSubject<User|null>(null);
  //private currentUserSubject: BehaviorSubject<User>;
  //public currentUser:Observable<User>
  BASEURL : string="http://localhost:62584";
  tmpuser?:User ;
  constructor(private http:HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')!));
    this.currentUser=this.currentUserSubject.asObservable();

   }
   public get currentUserValue(): User|null {
     console.log('the user');
     console.log(this.currentUserSubject.value);
       return this.currentUserSubject.value;
   }
   login(username:string,password:string)
   {//username,login.password
    var userrequest = new LoginRequest();
    userrequest.username=username;
    userrequest.password=password
    ///auth/signin
return this.http.post<any>(this.BASEURL+"/auth/signin",userrequest)
.pipe(map(user=>{
if(user && user.token){
  
  this.tmpuser =user
  
  localStorage.setItem('currentUser',JSON.stringify(this.tmpuser));
  this.currentUserSubject.next(user);

}
return user
}));
   }
   logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
   
    this.currentUserSubject.next(new User());
}
}
