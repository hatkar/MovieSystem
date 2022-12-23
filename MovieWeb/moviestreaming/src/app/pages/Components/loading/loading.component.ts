import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { map, Observable } from 'rxjs';
import { DataStateEnum } from 'src/app/models/state';
import { MoviesState, MoviesStateEnum } from 'src/app/state/Movies.reducer';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.css']
})
export class LoadingComponent implements OnInit {
  movieState$:Observable<MoviesState>|null=null;
  readonly DataStateEnum= MoviesStateEnum;
message:String =""
show:boolean =false
  constructor(private store:Store<any>) {
    this.movieState$=this.store.pipe(
    map((state)=> {
      this.message=state.catalogState.dataState
      
      return state.catalogState
    })); }

  ngOnInit(): void {
    this.movieState$?.subscribe(st=>{
if(st.dataState==this.DataStateEnum.LOADING||st.dataState==this.DataStateEnum.SAVING||st.dataState==this.DataStateEnum.SAVED)
{
  this.message=st.dataState;
  this.show=true
console.log("showing state");
}
else this.show=false
    });
    
  }
  Dismiss()
  {
    this.show=false
  }

}
