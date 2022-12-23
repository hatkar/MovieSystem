import { Component, OnInit } from '@angular/core';
import { categoryDTO, Pagecategory } from 'src/app/models/MovieDTO';
import { MovieService } from 'src/app/services/movie.service';
import {Store} from '@ngrx/store';
import { GetAllMoviesAction, GetAllPagedCategoriesAction, GetAllPagedMoviesOfCategoriesAction, SearchAllPagedCategoriesAction } from 'src/app/state/movie.actions';
import { MoviesState, MoviesStateEnum } from 'src/app/state/Movies.reducer';
import { map, Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-listcategorie',
  templateUrl: './listcategorie.component.html',
  styleUrls: ['./listcategorie.component.css']
})
export class ListcategorieComponent implements OnInit {

pagecategories?:Pagecategory;
  pagArray: number[] = [];
  keyword="";
//isLoaded=false;



  constructor(private httpservice:MovieService,private store:Store<any>,private route: Router) { }

  categState$:Observable<MoviesState>|null=null;
  readonly DataStateEnum= MoviesStateEnum;
  ngOnInit(): void {
    console.log('LISTCATEGORIES ONINIT ')
    this.loadcategories(0);
  
   // this.store.dispatch(new GetAllPagedCategoriesAction(0))
   //this.store.dispatch(new GetAllMoviesAction({}))
  /*  this.productsState$=this.store.pipe(
      map((state)=>  state.catalogState)
    );*/
  
  }
 
  prec()
  {
    console.log('PREC BUTTON')
    if(!this.pagecategories!.first)
    this.store.dispatch(new SearchAllPagedCategoriesAction(this.pagecategories!.pageNumber! -1,this.keyword))
   // this.store.dispatch(new GetAllPagedCategoriesAction(this.pagecategories!.pageNumber! -1))
//this.loadcategories(this.pagecategories!.pageNumber! -1);

  }
  suiv()
  {
    console.log('SUIV BUTTON')
    if(!this.pagecategories!.last)
    this.store.dispatch(new SearchAllPagedCategoriesAction(this.pagecategories!.pageNumber! +1,this.keyword))
    //this.store.dispatch(new GetAllPagedCategoriesAction(this.pagecategories!.pageNumber! +1))
   // this.loadcategories(this.pagecategories!.pageNumber! +1);
  }
  goto(i:any)
  {
    console.log('GOTO BUTTON')
    //this.store.dispatch(new GetAllPagedCategoriesAction(i));
    this.store.dispatch(new SearchAllPagedCategoriesAction(i,this.keyword))

  }
  loadcategories(page:number)
  {
    
this.categState$=this.store.pipe(
map((state)=> {
  
  this.pagecategories=state.catalogState.CategoriePages;
  var totalp=this.pagecategories?.totalPages;
  var curr=this.pagecategories?.pageNumber;
  this.createpagine(totalp!,curr!)
  return state.catalogState})
);
//this.store.dispatch(new GetAllPagedCategoriesAction(0))
this.store.dispatch(new SearchAllPagedCategoriesAction(0,this.keyword))
//this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(0,'635e710d82b17e52cf65f7af'))
   
  }
  createpagine(totalpag:number,current:number)
  { 
totalpag=totalpag-1;
    console.log("RECEIVED TOTAL PAGE ==>"+totalpag)
    console.log("RECEIVED CURRENT PAGE ==>"+current)
    var deb=0;
    var fin =0;
if(totalpag<4||current-2<0)
{ deb=0;
  fin=totalpag-4>0?4:totalpag;
}else{
  if(totalpag-current<2)
  { fin=totalpag;
    deb = totalpag-4;
  }else
  { deb= current-2;
    fin= current+2
  }
}
console.log("DEBUT ARRAY ==>"+deb)
    console.log("FIN ARRAY ==>"+fin)
//just en cas ou ilya une autre cas qui peut produire un erreur
if(deb<0) deb=0
if (fin>totalpag) fin=totalpag
this.pagArray=[];
    for (var i = deb; i <= fin; i++) {
      this.pagArray.push(i);
    }
return this.pagArray;
  }

  gotomovies(id:string)
  {
    this.route.navigate(['admin/listmovie', id]);
   // this.route.navigate(['admin/listmovie', { idcategorie: 'categid' }]);
    
  }
  search(form: NgForm)
  {
    this.keyword=form.value.keyword;

 
      this.store.dispatch(new SearchAllPagedCategoriesAction(0,this.keyword))
   
   
   // alert('searching'+form.value.keyword);
  }
}
