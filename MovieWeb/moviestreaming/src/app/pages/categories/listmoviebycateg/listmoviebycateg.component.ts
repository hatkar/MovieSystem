import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { map, Observable } from 'rxjs';
import { PageMovie } from 'src/app/models/MovieDTO';
import { GetAllPagedMoviesOfCategoriesAction } from 'src/app/state/movie.actions';
import { MoviesState, MoviesStateEnum } from 'src/app/state/Movies.reducer';

@Component({
  selector: 'app-listmoviebycateg',
  templateUrl: './listmoviebycateg.component.html',
  styleUrls: ['./listmoviebycateg.component.css']
})
export class ListmoviebycategComponent implements OnInit {
  pagArray: number[] = [];
  categid:string="";
  keyword="";
  movieState$:Observable<MoviesState>|null=null;
  readonly DataStateEnum= MoviesStateEnum;
  pagemovies?:PageMovie;
  constructor(private store:Store<any>,private route: ActivatedRoute,private router: Router) {
    this.route.params.subscribe(params => {this.categid=params['idcategorie']
    console.log(params)
  console.log(this.categid)
  if(this.categid)console.log("==>ID CATEGORIE"+this.categid);
  else{
    this.categid=""
    console.log("===>ID CATEGORIE"+this.categid);
  }
this.getmovies(this.categid);

});
   }

   prec()
   {
     console.log('PREC BUTTON')
     if(!this.pagemovies!.first)
     this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(this.pagemovies!.pageNumber! -1,this.categid,this.keyword))
    // this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(this.pagemovies!.pageNumber! -1,this.categid))
 //this.loadcategories(this.pagecategories!.pageNumber! -1);
 
   }
   suiv()
   {
     console.log('SUIV BUTTON')
     if(!this.pagemovies!.last)
     this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(this.pagemovies!.pageNumber! +1,this.categid,this.keyword))
    // this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(this.pagemovies!.pageNumber! +1,this.categid))
    // this.loadcategories(this.pagecategories!.pageNumber! +1);
   }
   goto(i:any)
   {
     console.log('GOTO BUTTON')
     this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(i,this.categid,this.keyword))
    // this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(i,this.categid));
 
   }


  getmovies(categid: string) {
    console.log("ID CATEGORIE"+categid);
    this.movieState$=this.store.pipe(
      map((state)=> {
console.log('loaded movie of categ')

        this.pagemovies=state.catalogState.MoviePage;
        console.log(this.pagemovies)
        var totalp=this.pagemovies?.totalPages;
        var curr=this.pagemovies?.pageNumber;
        this.createpagine(totalp!,curr!)
        return state.catalogState})
        );
        this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(0,this.categid,this.keyword))
    //this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(0,categid,keyw))
   // throw new Error('Method not implemented.');
  }

  ngOnInit(): void {
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

  search(form: NgForm)
  {
    this.keyword=form.value.keyword;

 
    this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(0,this.categid,this.keyword))
   
   
   // alert('searching'+form.value.keyword);
  }
  gotomovies(id:string)
  {
    this.router.navigate(['admin/details', id]);
   // this.route.navigate(['admin/listmovie', { idcategorie: 'categid' }]);
    
  }

}
