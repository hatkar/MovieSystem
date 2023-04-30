import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-notfound',
  templateUrl: './notfound.component.html',
  styleUrls: ['./notfound.component.css']
})
export class NotfoundComponent implements OnInit {
  returnurl:string="/"
  constructor(  private route :ActivatedRoute,
    private router : Router) { }

  ngOnInit(): void {
    this.returnurl=this.route.snapshot.queryParams['returnUrl']|| '/';
  }
  login(){
    this.router.navigate(['/signin'],{queryParams: {returnUrl :this.returnurl}});
  }
tohome()
{
  this.router.navigate(['/home']);
}
}
