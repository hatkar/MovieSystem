import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { map } from 'rxjs';
import { categoryDTO, PageMovie } from 'src/app/models/MovieDTO';
import { GetAllCategoriesAction, GetAllPagedMoviesOfCategoriesAndYearsAction } from 'src/app/state/movie.actions';
import { MoviesStateEnum } from 'src/app/state/Movies.reducer';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  readonly DataStateEnum= MoviesStateEnum;
startyear:number=1950;
endyear:number=2023;
categories: categoryDTO[] = [];
selectedcategorie:categoryDTO=new categoryDTO();
tmpcategorie:categoryDTO=new categoryDTO();
pagemovies?:PageMovie;
pagArray: number[] = [];
BASEURL : string="http://localhost:9292/api";
  constructor(private store:Store<any>,private router: Router) { }

  ngOnInit(): void {
    this.tmpcategorie.name="All Categories"
    this.tmpcategorie.id=""
    this.selectedcategorie.name="All Categories"
    this.selectedcategorie.id=""
    this.getCategories()
    this.getmovies();
  }
  getmovies() {
    this.store.subscribe( (state)=> {
      if(state.catalogState.dataState==this.DataStateEnum.LOADED){
        console.log('loaded movie of categ')

        this.pagemovies=state.catalogState.MoviePage;
        console.log(this.pagemovies)
        var totalp=this.pagemovies?.totalPages;
        var curr=this.pagemovies?.pageNumber;
        this.createpagine(totalp!,curr!)
      }

        //return state.catalogState
      }
      
        );
        this.store.dispatch(new GetAllPagedMoviesOfCategoriesAndYearsAction(0,this.selectedcategorie.id!,this.startyear.toString(),this.endyear.toString()))
       // this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(0,this.categid,this.keyword))

  }
  getCategories()
  {
    this.store.subscribe((state)=>{
      if(state.catalogState.dataState==this.DataStateEnum.LOADED){
        this.categories=state.catalogState.Categogories;
    /*  if(this.clickedcategorie.id)
      {
     
     this.categories.map(cat=>{if(cat.id==this.clickedcategorie.id) this.clickedcategorie=cat})
        console.log("==>ID CATEGORIE"+this.clickedcategorie.id);}
      else{
        
        this.clickedcategorie=state.catalogState.Categogories[0]
        
      }*/
      
      console.log("=======>**<======") 
    console.log(this.categories)
      }
    });
  
    this.store.dispatch(new GetAllCategoriesAction());
  }
  applyfilter()
  {
    console.log("FILTER")
    console.log("Categorie Filter :"+this.selectedcategorie.id);
    console.log("Date Release Between : "+this.startyear +" and : "+this.endyear);
   this.getmovies()
  }

  selectedcategoriechange(categ :categoryDTO)
  {
    this.selectedcategorie=categ;
    console.log('=====><======')
    console.log(this.selectedcategorie);
  }
  selectAllcategorie()
  {
  //  categ :categoryDTO = new categoryDTO();
    this.selectedcategorie=this.tmpcategorie;
    //.name="All Categories"
    //this.selectedcategorie.id=""
  }
  startyearchange(event:any)
  {
    console.log(event.target)
    
    let type=event.target.attributes['class'].value;
    if (type=='noUi-handle noUi-handle-lower')this.startyear=event.target.attributes['aria-valuetext'].value
    if(type=='noUi-handle noUi-handle-upper')this.endyear=event.target.attributes['aria-valuetext'].value
console.log("start year : "+this.startyear+" end Year : "+this.endyear )


  }


  prec()
  {
    console.log('PREC BUTTON')
    if(!this.pagemovies!.first)
    this.store.dispatch(new GetAllPagedMoviesOfCategoriesAndYearsAction(this.pagemovies!.pageNumber! -1,this.selectedcategorie.id!,this.startyear.toString(),this.endyear.toString()))
   // this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(this.pagemovies!.pageNumber! -1,this.categid))
//this.loadcategories(this.pagecategories!.pageNumber! -1);

  }
  suiv()
  {
    console.log('SUIV BUTTON')
    if(!this.pagemovies!.last)
       this.store.dispatch(new GetAllPagedMoviesOfCategoriesAndYearsAction(this.pagemovies!.pageNumber! +1,this.selectedcategorie.id!,this.startyear.toString(),this.endyear.toString()))
    // this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(this.pagemovies!.pageNumber! +1,this.categid))
   // this.loadcategories(this.pagecategories!.pageNumber! +1);
  }
  goto(i:any)
  {
    console.log('GOTO BUTTON')
    this.store.dispatch(new GetAllPagedMoviesOfCategoriesAndYearsAction(i,this.selectedcategorie.id!,this.startyear.toString(),this.endyear.toString()))

    //this.store.dispatch(new GetAllPagedMoviesOfCategoriesAction(i,this.categid,this.keyword))
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
  findCatefName(id:string):string
  {
    return this.categories.find(c=>c.id==id)!.name!
   // return"Romance";
  }

  gotomovies(name:string)
  {
    this.router.navigate(['details', name]);
   // this.route.navigate(['admin/listmovie', { idcategorie: 'categid' }]);
    
  }
}
