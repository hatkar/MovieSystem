import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs';
import { AuthentificationService } from 'src/app/services/authentification.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
loginForm:FormGroup | null=null;
loading=false
submitted=false
returnurl:string="/"
error=''
  constructor(
    private formBuilder :FormBuilder,
    private route :ActivatedRoute,
    private router : Router,
    private authservice:AuthentificationService
  ) { 
    /*if(this.authservice.currentUserValue)
    {
      this
    }*/
  }

  ngOnInit(): void {
    this.loginForm=this.formBuilder.group({
      username:['',Validators.required],
      password:['',Validators.required]
    });
    this.returnurl=this.route.snapshot.queryParams['returnUrl']|| '/';

  }
  get f(){
    return this.loginForm!.controls;
  }
onSubmit(){
  console.log("init Login")
  this.submitted=true;
  if(this.loginForm?.invalid) return;
  this.loading=true;
  this.authservice.login(this.f['username'].value, this.f['password'].value)
  .pipe(first())
  .subscribe(
    data => {
      console.log('login received')
        this.router.navigate([this.returnurl]);
    },
    error => {
      console.log('login error')
        this.error = error;
        this.loading = false;
    });
}
}
