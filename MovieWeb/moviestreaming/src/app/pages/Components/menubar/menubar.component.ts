import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DataStateEnum, NotificationEvent } from 'src/app/models/state';
import { EventDriverService } from 'src/app/state/EventDriverService';

@Component({
  selector: 'app-menubar',
  templateUrl: './menubar.component.html',
  styleUrls: ['./menubar.component.css']
})
export class MenubarComponent implements OnInit {
  activeheader=false;
  message="";
  constructor(private router: Router, private route: ActivatedRoute,
    private eventDrivenService:EventDriverService) { }
go()
{
  this.router.navigate([`../home`], { relativeTo: this.route });
}
searchmovies(form: NgForm)
{
  this.router.navigate(['search', form.value.keyword]);
 // this.route.navigate(['admin/listmovie', { idcategorie: 'categid' }]);
  
}
connect()
{
  //this.activeheader=!this.activeheader;
  //alert('processing notification');
}
  ngOnInit(): void {
 /*   this.eventDrivenService.sourceEventSubjectObservable.subscribe((notificationEvent:NotificationEvent)=>{
      if(notificationEvent.type==DataStateEnum.LOADED) { 
        this.activeheader=false;
      console.log('Loaded event ');
      }
      else{
        this.message=<string>notificationEvent.payload;
        this.activeheader=true;
        console.log('Loading event ');
      }
     
     
    })*/

  }

}
