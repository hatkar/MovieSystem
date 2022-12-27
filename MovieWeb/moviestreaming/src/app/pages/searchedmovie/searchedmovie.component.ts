import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { categoryDTO, PageMovie } from 'src/app/models/MovieDTO';
import { GetAllCategoriesAction, SearchAllPagedMoviesAction } from 'src/app/state/movie.actions';
import { MoviesStateEnum } from 'src/app/state/Movies.reducer';

@Component({
  selector: 'app-searchedmovie',
  templateUrl: './searchedmovie.component.html',
  styleUrls: ['./searchedmovie.component.css']
})
export class SearchedmovieComponent implements OnInit {
  BASEURL : string="http://localhost:9292";
keyword:string="";
readonly DataStateEnum= MoviesStateEnum;
pagemovies?:PageMovie;
pagArray: number[] = [];
categories: categoryDTO[] = [];
loadded:boolean=false;
  constructor(private store:Store<any>,private route: ActivatedRoute,private router: Router) {
    this.route.params.subscribe(params => {this.keyword=params['keyword']
    console.log(params)
    console.log(this.keyword)
    if(this.keyword)console.log("==>ID KEYWORD"+this.keyword);
    else{
      this.keyword=""
      console.log("===>ID KEYWORD"+this.keyword);
    }
  this.getmovies(this.keyword);
  this.getCategories()

  });
  }
  getCategories() {
    this.store.subscribe((state)=>{
      if(state.catalogState.dataState==this.DataStateEnum.LOADED){
        this.categories=state.catalogState.Categogories;


      console.log("=======>**<======")
    console.log(this.categories)
      }
    });

    this.store.dispatch(new GetAllCategoriesAction());
  }
  findCatefName(id:string):string
  {
    return this.categories.find(c=>c.id==id)!.name!
   // return"Romance";
  }
  getmovies(keyword: string) {
    console.log("keyword"+keyword);
    this.store.subscribe(
      (state)=> {
        //if loaded
        if(state.catalogState.dataState==this.DataStateEnum.LOADED)
        {
          console.log('loaded movie of categ')

          this.pagemovies=state.catalogState.MoviePage;
          console.log(this.pagemovies)
          var totalp=this.pagemovies?.totalPages;
          var curr=this.pagemovies?.pageNumber;
          this.createpagine(totalp!,curr!)
          this.loadded=true;
        }

        }
        );
        this.store.dispatch(new SearchAllPagedMoviesAction(0,keyword))

  }

  ngOnInit(): void {
  }


  prec()
  {
    console.log('PREC BUTTON')
    if(!this.pagemovies!.first)
    this.store.dispatch(new SearchAllPagedMoviesAction(this.pagemovies!.pageNumber! -1,this.keyword))
   // this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(this.pagemovies!.pageNumber! -1,this.categid))
//this.loadcategories(this.pagecategories!.pageNumber! -1);

  }
  suiv()
  {
    console.log('SUIV BUTTON')
    if(!this.pagemovies!.last)
    this.store.dispatch(new SearchAllPagedMoviesAction(this.pagemovies!.pageNumber! +1,this.keyword))
   // this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(this.pagemovies!.pageNumber! +1,this.categid))
   // this.loadcategories(this.pagecategories!.pageNumber! +1);
  }
  goto(i:any)
  {
    console.log('GOTO BUTTON')
    this.store.dispatch(new SearchAllPagedMoviesAction(i,this.keyword))
   // this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(i,this.categid));

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

  gotomovies(name:string)
  {
    this.router.navigate(['details', name]);
   // this.route.navigate(['admin/listmovie', { idcategorie: 'categid' }]);

  }

}
