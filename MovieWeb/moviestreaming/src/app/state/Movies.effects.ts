import { Injectable } from "@angular/core";
import { act, Actions, createEffect,ofType } from "@ngrx/effects";
import { Action } from "@ngrx/store";
import { Observable , of} from "rxjs";
import { MovieService } from "../services/movie.service";
import {catchError, map, mergeMap} from 'rxjs/operators';
import { GetAllCategoriesAction, GetAllCategoriesActionError, GetAllCategoriesActionSuccess, GetAllMoviesActionError, GetAllMoviesActionSuccess, GetAllPagedCategoriesAction, GetAllPagedCategoriesActionError, GetAllPagedCategoriesActionSuccess, GetAllPagedMoviesOfCategoriesAction, GetAllPagedMoviesOfCategoriesActionError, GetAllPagedMoviesOfCategoriesActionSuccess, GetAllPagedMoviesOfCategoriesAndYearsAction, GetAllPagedMoviesOfCategoriesAndYearsActionError, GetAllPagedMoviesOfCategoriesAndYearsActionSuccess, GetMoviesByNameAction, GetMoviesByNameActionError, GetMoviesByNameActionSuccess, MoviesActionsTypes, SaveCategoriesAction, SaveCategoriesActionError, SaveCategoriesActionSuccess, SaveMovieAction, SaveMovieActionError, SaveMovieActionSuccess, SearchAllPagedCategoriesAction, SearchAllPagedCategoriesActionError, SearchAllPagedCategoriesActionSuccess, SearchAllPagedMoviesAction, SearchAllPagedMoviesActionError, SearchAllPagedMoviesActionSuccess } from "./movie.actions";

@Injectable()
export class MoviesEffects {
    constructor(private movieservice:MovieService, private effectActions:Actions) {  }

  /*Get Movie Of Categorie And year Release Paged */
  getAllPagedMovieOfCategoriesAndYearsEffect:Observable<Action>=createEffect(
    ()=>this.effectActions.pipe(
      ofType(MoviesActionsTypes.GET_ALL_PAGED_MOVIE_OFCATEGORIESANDYEARS),
      mergeMap((action:GetAllPagedMoviesOfCategoriesAndYearsAction)=>{
        return this.movieservice.GetAllMoviesPagedAndFiltred(action.payload,action.categorieid,action.minyear,action.maxyear)
          .pipe(
            map((mov)=> new GetAllPagedMoviesOfCategoriesAndYearsActionSuccess(mov)),
            catchError((err)=>of(new GetAllPagedMoviesOfCategoriesAndYearsActionError(err.message)))
          )
      })
    )
  );

    getAllMoviesEffect:Observable<Action>=createEffect(
        ()=>this.effectActions.pipe(
          ofType(MoviesActionsTypes.GET_ALL_MOVIES),
          mergeMap((action)=>{
                return this.movieservice.GetAllMovies()
                  .pipe(
                    map((movies)=> new GetAllMoviesActionSuccess(movies)),
                    catchError((err)=>of(new GetAllMoviesActionError(err.message)))
                  )
          })
        )
      );
      getMoviesEffect:Observable<Action>=createEffect(
        ()=>this.effectActions.pipe(
          ofType(MoviesActionsTypes.GET_MOVIE),
          mergeMap((action:GetMoviesByNameAction)=>{
                return this.movieservice.getMovie(action.payload)
                  .pipe(
                    map((movies)=> new GetMoviesByNameActionSuccess(movies)),
                    catchError((err)=>of(new GetMoviesByNameActionError(err.message)))
                  )
          })
        )
      );
      getAllPagedCategoriesEffect:Observable<Action>=createEffect(
        ()=>this.effectActions.pipe(
          ofType(MoviesActionsTypes.GET_ALL_PAGED_CATEGORIES),
          mergeMap((action:GetAllPagedCategoriesAction)=>{
                return this.movieservice.getpagedcategories(action.payload)
                  .pipe(
                    map((categ)=> new GetAllPagedCategoriesActionSuccess(categ)),
                    catchError((err)=>of(new GetAllPagedCategoriesActionError(err.message)))
                  )
          })
        )
      );
      searchAllPagedCategoriesEffect:Observable<Action>=createEffect(
        ()=>this.effectActions.pipe(
          ofType(MoviesActionsTypes.SEARCH_ALL_PAGED_CATEGORIES),
          mergeMap((action:SearchAllPagedCategoriesAction)=>{
                return this.movieservice.searchpagedcategories(action.payload,action.keyword)
                  .pipe(
                    map((categ)=> new SearchAllPagedCategoriesActionSuccess(categ)),
                    catchError((err)=>of(new SearchAllPagedCategoriesActionError(err.message)))
                  )
          })
        )
      );
      /*Get ALL Categories*/
      getAllCategoriesEffect:Observable<Action>=createEffect(
        ()=>this.effectActions.pipe(
          ofType(MoviesActionsTypes.GET_ALL_CATEGORIES),
          mergeMap((action:GetAllCategoriesAction)=>{
                return this.movieservice.getcategories()
                  .pipe(
                    map((categ)=> new GetAllCategoriesActionSuccess(categ)),
                    catchError((err)=>of(new GetAllCategoriesActionError(err.message)))
                  )
          })
        )
      );
      getAllPagedMovieOfCategoriesEffect:Observable<Action>=createEffect(
        ()=>this.effectActions.pipe(
          ofType(MoviesActionsTypes.GET_ALL_PAGED_MOVIE_OFCATEGORIES),
          mergeMap((action:GetAllPagedMoviesOfCategoriesAction)=>{
                return this.movieservice.getpagedmovie(action.payload,action.categorieid,action.keyword)
                  .pipe(
                    map((mov)=> new GetAllPagedMoviesOfCategoriesActionSuccess(mov)),
                    catchError((err)=>of(new GetAllPagedMoviesOfCategoriesActionError(err.message)))
                  )
          })
        )
      );

       /*Get Movie Of Categorie And year Release Paged */
       SearchAllPagedMovie:Observable<Action>=createEffect(
        ()=>this.effectActions.pipe(
          ofType(MoviesActionsTypes.SEARCH_ALL_PAGED_MOVIE),
          mergeMap((action:SearchAllPagedMoviesAction)=>{
                return this.movieservice.SearchMoviesPaged(action.page,action.keyword)
                  .pipe(
                    map((mov)=> new SearchAllPagedMoviesActionSuccess(mov)),
                    catchError((err)=>of(new SearchAllPagedMoviesActionError(err.message)))
                  )
          })
        )
      );
      //save Categ effect
      //first launching SaveCategoriesActionSuccess
      //then fire getallpagedcateg
      saveCategoriesEffect:Observable<Action>=createEffect(
        ()=>this.effectActions.pipe(
          ofType(MoviesActionsTypes.SAVE_CATEGORIES),
          mergeMap((action:SaveCategoriesAction)=>{
            console.log("SAVING EFFECTS")
                return this.movieservice.saveCategorie(action.payload)
                  .pipe(
                    map((categ)=> {
                      return new SaveCategoriesActionSuccess(categ);
                     // return new GetAllPagedCategoriesAction(0);
                    }),

                    catchError((err)=>of(new SaveCategoriesActionError(err.message)))
                  )
          })
        )
      );
       //save Movie

      saveMovieEffect:Observable<Action>=createEffect(
        ()=>this.effectActions.pipe(
          ofType(MoviesActionsTypes.SAVE_MOVIE),
          mergeMap((action:SaveMovieAction)=>{
            console.log("SAVING EFFECTS")
                return this.movieservice.savefile(action.payload)
                  .pipe(
                    map((categ)=> {
                      return new SaveMovieActionSuccess(categ);
                     // return new GetAllPagedCategoriesAction(0);
                    }),

                    catchError((err)=>of(new SaveMovieActionError(err.message)))
                  )
          })
        )
      );


}
