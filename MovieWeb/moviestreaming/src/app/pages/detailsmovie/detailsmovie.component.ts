import { DOCUMENT } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { map, Observable } from 'rxjs';
import { MovieDTO } from 'src/app/models/MovieDTO';
import { MovieService } from 'src/app/services/movie.service';
import { GetMoviesByNameAction } from 'src/app/state/movie.actions';
import { MoviesState, MoviesStateEnum } from 'src/app/state/Movies.reducer';

declare function  initializePlayer2():void;
@Component({
  selector: 'app-detailsmovie',
  templateUrl: './detailsmovie.component.html',
  styleUrls: ['./detailsmovie.component.css']
})
export class DetailsmovieComponent implements OnInit {
  BASEURL : string="http://localhost:9292";
                  // http://localhost:9292/api/video/movie
movieName:string="";
currentMovie?:MovieDTO= new MovieDTO;
photurl?:string;
movieurl?:string="";
downloadurl?:string;
movieStatekk$:Observable<MoviesState>|null=null;
loaded:boolean=false
readonly DataStateEnum= MoviesStateEnum;
  constructor(private movieService: MovieService,private route: ActivatedRoute,private store:Store<any>,@Inject(DOCUMENT) document: Document) { }

  ngOnInit(): void {
    console.log("Waiting param")
    this.route.params.subscribe(params => {this.movieName=params['moviename']
   // this.route.params.subscribe(params => {this.categid=params['idcategorie']
    console.log(params)
    if(!this.movieName) this.movieName="Joker";


    this.getMovie(this.movieName);

  });

  }
  getMovie(movieName: string | undefined) {
    //throw new Error('Method not implemented.');
  /*  this.movieState$=this.store.pipe(
      map((state)=> {
        console.log("received movie");
        //console.log(resp);
        this.currentMovie=state.catalogState.Movie;
        console.log(this.currentMovie);
        this.photurl=this.BASEURL+"/photo/"+this.currentMovie?.imageurl;
        this.movieurl=this.BASEURL+"/streamvideo/movie/"+this.currentMovie?.fileurl;
        this.downloadurl=this.BASEURL+"/video/movie/"+this.currentMovie?.fileurl;
        console.log("video url");
        console.log(this.movieurl)
        return state.catalogState})
        );*/



// a enlever
this.store.subscribe(resp=>{
  if(resp.catalogState.dataState==this.DataStateEnum.LOADED){
      console.log("received movie");
      this.currentMovie=resp.catalogState.Movie;
     // console.log(this.currentMovie);
      //this.currentMovie=resp;
      console.log(this.currentMovie);
      this.photurl=this.BASEURL+"/api/ressources/photo/"+this.currentMovie!.imageurl;
      this.movieurl=this.BASEURL+"/api/ressources/video/movie/"+this.currentMovie!.fileurl;
      this.downloadurl=this.BASEURL+"/api/ressources/video/movie/"+this.currentMovie!.fileurl;
      console.log("video url");
      console.log(this.movieurl)
      this.loaded=true
      if(this.movieurl!="")  initializePlayer2();
//const player = new Plyr('#player');
     // document.getElementById('categmov'));
     //$(window).on('change', initializePlayer());
     //document.addEventListener.createExpression("alert('hello hello');")
    // document.onchange("alert('boom)");
//document.currentScript

     // alert('loaded from details')
  }});
    this.store.dispatch(new GetMoviesByNameAction(this.movieName!))

  }


}


