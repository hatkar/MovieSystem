import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { categoryDTO, MovieDTO, Pagecategory, PageMovie } from '../models/MovieDTO';
import { delay, Observable, Subscriber } from 'rxjs';
import { EventDriverService } from '../state/EventDriverService';
import { DataStateEnum } from '../models/state';
@Injectable({
  providedIn: 'root'
})
export class MovieService {
 constructor(private httpClient: HttpClient,private eventDrivenService:EventDriverService) { }

  getMovie(movieName: string | undefined):Observable<MovieDTO> {
    return this.httpClient.get('http://localhost:9292/movies/api/movie/'+movieName);
  }



  public uploadfile(movie:any,filepicture:File,movieFile:File) {
    let formParams = new FormData();
    formParams.append('movie',movie);
    formParams.append('filepicture', filepicture);
    formParams.append('filemovie', movieFile);

    return this.httpClient.post('http://localhost:9292/admin/api/addmovie', movie)
  }
  public getcategories():Observable<categoryDTO[]> {

    this.eventDrivenService.publishEvent({type:DataStateEnum.LOADING,payload:"Loading data from server"});
    //delay(10000);
     const result = this.httpClient.get<categoryDTO[]>('http://localhost:9292/movies/api/categories')
     this.eventDrivenService.publishEvent({type:DataStateEnum.LOADED,payload:"Loading data from server"});

    return result;
  }
  public GetAllMovies():Observable<MovieDTO[]> {
    //by default size =3
    console.log('Service HTTP : GetAllMovies');
     return this.httpClient.get<MovieDTO[]>('http://localhost:9292/movies/api/movies');
   }
   public GetAllMoviesPagedAndFiltred(page:number,idcategory:string,minyear:string,maxyear:string):Observable<PageMovie> {
    //by default size =3
    console.log('Service HTTP : GetAllMovies');
     return this.httpClient.get<PageMovie>('http://localhost:9292/movies/api/searchmovieofcategorieandyear'+'?page='+page+'&size=6&minyear='+minyear+'&maxyear='+maxyear+'&idcategory='+idcategory);
   }
   public SearchMoviesPaged(page:number,keyword:string):Observable<PageMovie> {
    //by default size =3
    console.log('Service HTTP : Search Movie');
     return this.httpClient.get<PageMovie>('http://localhost:9292/movies/api/searchmoviecontainbypage'+'?page='+page+'&size=6&keyword='+keyword);
   }
  public getpagedcategories(page:number):Observable<Pagecategory> {
   //by default size =3

    return this.httpClient.get<Pagecategory>('http://localhost:9292/movies/api/pageablecategories'+'?page='+page+'&size=10');
  }
  public searchpagedcategories(page:number,keyword:string):Observable<Pagecategory> {
    //by default size =3

     return this.httpClient.get<Pagecategory>('http://localhost:9292/movies/api/pageablecategoriescontain'+'?page='+page+'&size=10&keyword='+keyword);
   }
  public getpagedmovie(page:number,categorieid:string,keyword:string):Observable<PageMovie>
  {
    return this.httpClient.get<PageMovie>('http://localhost:9292/movies/api/searchmovieofcategorie?page='+page+'&size=5&idcategory='+categorieid+'&keyword='+keyword);
  }
  public savefile(data:FormData):Observable<MovieDTO> {
    console.log("sending Movie ...");
    ///admin/api
     return this.httpClient.post<MovieDTO>('http://localhost:9292/admin/api/uploadmovie', data );
  }
  //save Categorie
  public saveCategorie(categ:categoryDTO):Observable<categoryDTO>
  {
    return this.httpClient.post<categoryDTO>('http://localhost:9292/admin/api/addCategorie',categ);
  }


 }
