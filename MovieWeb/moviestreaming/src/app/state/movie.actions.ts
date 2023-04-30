import { Action } from "@ngrx/store";
import { categoryDTO, MovieDTO, Pagecategory, PageMovie } from "../models/MovieDTO";

export enum MoviesActionsTypes{
    /* Get All movies*/
    GET_ALL_MOVIES="[Movies] Get All Movies",
    GET_ALL_MOVIES_SUCCESS="[Movies] Get All Movies Success",
    GET_ALL_MOVIES_ERROR="[Movies] Get All Movies Error",
    /* Get ALL Categorie */
    GET_ALL_CATEGORIES="[CATEGORIES] Get All  CATEGORIES",
    GET_ALL_CATEGORIES_SUCCESS="[CATEGORIES] Get All CATEGORIES Success",
    GET_ALL_CATEGORIES_ERROR="[CATEGORIES] Get All CATEGORIES Error",
    /* Get Categorie By Page*/
    GET_ALL_PAGED_CATEGORIES="[CATEGORIES] Get All  By Page CATEGORIES",
    GET_ALL_PAGED_CATEGORIES_SUCCESS="[CATEGORIES] Get All By Page CATEGORIES Success",
    GET_ALL_PAGED_CATEGORIES_ERROR="[CATEGORIES] Get All By Page CATEGORIES Error",
     /* Search Categorie By Page*/
     SEARCH_ALL_PAGED_CATEGORIES="[CATEGORIES] SEARCH All  CATEGORIES",
     SEARCH_ALL_PAGED_CATEGORIES_SUCCESS="[CATEGORIES] SEARCH All CATEGORIES Success",
     SEARCH_ALL_PAGED_CATEGORIES_ERROR="[CATEGORIES] SEARCH All CATEGORIES Error",
    /* Get ALL Movie Of Categorie By Page*/
    GET_ALL_PAGED_MOVIE_OFCATEGORIES="[MOVIES] Get All MOVIE OF CATEGORIES",
    GET_ALL_PAGED_MOVIE_OFCATEGORIES_SUCCESS="[MOVIES] Get All MOVIE OF CATEGORIES Success",
    GET_ALL_PAGED_MOVIE_OFCATEGORIES_ERROR="[MOVIES] Get All MOVIE OF CATEGORIES Error",
    /* Search ALL Movie  By Page*/
    SEARCH_ALL_PAGED_MOVIE="[MOVIES] Search All MOVIE ",
    SEARCH_ALL_PAGED_MOVIE_SUCCESS="[MOVIES] Search All MOVIE  Success",
    SEARCH_ALL_PAGED_MOVIE_ERROR="[MOVIES] Search All MOVIE  Error",
    /* Get ALL Movie Of Categorie And Year By Page*/
    GET_ALL_PAGED_MOVIE_OFCATEGORIESANDYEARS="[MOVIES] Get All MOVIE OF CATEGORIES AND YEARS",
    GET_ALL_PAGED_MOVIE_OFCATEGORIESANDYEARS_SUCCESS="[MOVIES] Get All MOVIE OF CATEGORIES AND YEARS Success",
    GET_ALL_PAGED_MOVIE_OFCATEGORIESANDYEARS_ERROR="[MOVIES] Get All MOVIE OF CATEGORIES AND YEARS Error",
     /* Save Categorie */
     SAVE_CATEGORIES="[CATEGORIES]  Save  CATEGORIES",
     SAVE_CATEGORIES_SUCCESS="[CATEGORIES] Save CATEGORIES Success",
     SAVE_CATEGORIES_ERROR="[CATEGORIES] Save CATEGORIES Error",
      /* Save Movie */
      SAVE_MOVIE="[Movie]  Save  Movie",
      SAVE_MOVIE_SUCCESS="[Movie] Save Movie Success",
      SAVE_MOVIE_ERROR="[Movie] Save Movie Error",
      /* Get  movies By Name*/
    GET_MOVIE="[Movie] Get  Movie By Name",
    GET_MOVIE_SUCCESS="[Movie] Get  Movie By Name Success",
    GET_MOVIE_ERROR="[Movie] Get  Movie By Name Error",
}

/* Get ALL Movies Actions*/

export class GetAllMoviesAction implements Action{
    type: MoviesActionsTypes=MoviesActionsTypes.GET_ALL_MOVIES;
    constructor(public payload:any) {
    }
  }

  export class GetAllMoviesActionSuccess implements Action{
    type: MoviesActionsTypes=MoviesActionsTypes.GET_ALL_MOVIES_SUCCESS;
    constructor(public payload:MovieDTO[]) {
    }
  }

  export class GetAllMoviesActionError implements Action{
    type: MoviesActionsTypes=MoviesActionsTypes.GET_ALL_MOVIES_ERROR;
    constructor(public payload:string) {
    }
  }
  /* Searc ALL paged Movies  Actions*/

export class SearchAllPagedMoviesAction implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.SEARCH_ALL_PAGED_MOVIE;
  constructor(public page:number,public keyword:any) {
  }
}

export class SearchAllPagedMoviesActionSuccess implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.SEARCH_ALL_PAGED_MOVIE_SUCCESS;
  constructor(public payload:PageMovie) {
  }
}

export class SearchAllPagedMoviesActionError implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.SEARCH_ALL_PAGED_MOVIE_ERROR;
  constructor(public payload:string) {
  }
}

  /* Get ALL Categories Actions*/

export class GetAllCategoriesAction implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.GET_ALL_CATEGORIES;
  constructor() {
  }
}

export class GetAllCategoriesActionSuccess implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.GET_ALL_CATEGORIES_SUCCESS;
  constructor(public payload:categoryDTO[]) {
  }
}

export class GetAllCategoriesActionError implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.GET_ALL_CATEGORIES_ERROR;
  constructor(public payload:string) {
  }
}

  /* Get ALL Paged Categories Actions*/

export class GetAllPagedCategoriesAction implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.GET_ALL_PAGED_CATEGORIES;
  constructor(public payload:number) {
  }
}

export class GetAllPagedCategoriesActionSuccess implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.GET_ALL_PAGED_CATEGORIES_SUCCESS;
  constructor(public payload:Pagecategory) {
  }
}

export class GetAllPagedCategoriesActionError implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.GET_ALL_PAGED_CATEGORIES_ERROR;
  constructor(public payload:string) {
  }
}
  /* Search ALL Paged Categories Actions*/

  export class SearchAllPagedCategoriesAction implements Action{
    type: MoviesActionsTypes=MoviesActionsTypes.SEARCH_ALL_PAGED_CATEGORIES;
    constructor(public payload:number,public keyword:string) {
    }
  }

  export class SearchAllPagedCategoriesActionSuccess implements Action{
    type: MoviesActionsTypes=MoviesActionsTypes.SEARCH_ALL_PAGED_CATEGORIES_SUCCESS;
    constructor(public payload:Pagecategory) {
    }
  }

  export class SearchAllPagedCategoriesActionError implements Action{
    type: MoviesActionsTypes=MoviesActionsTypes.SEARCH_ALL_PAGED_CATEGORIES_ERROR;
    constructor(public payload:string) {
    }
  }
 /* Get ALL Movie Of Categorie By Page Actions*/

 export class GetAllPagedMoviesOfCategoriesAction implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.GET_ALL_PAGED_MOVIE_OFCATEGORIES;
  constructor(public payload:number,public categorieid:string,public keyword:string) {
  }
}

export class GetAllPagedMoviesOfCategoriesActionSuccess implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.GET_ALL_PAGED_MOVIE_OFCATEGORIES_SUCCESS;
  constructor(public payload:PageMovie) {
  }
}

export class GetAllPagedMoviesOfCategoriesActionError implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.GET_ALL_PAGED_MOVIE_OFCATEGORIES_ERROR;
  constructor(public payload:string) {
  }
}

 /* Get ALL Movie Of Categorie And Years By Page Actions*/

 export class GetAllPagedMoviesOfCategoriesAndYearsAction implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.GET_ALL_PAGED_MOVIE_OFCATEGORIESANDYEARS;
  constructor(public payload:number,public categorieid:string,public minyear:string,public maxyear:string) {
  }
}

export class GetAllPagedMoviesOfCategoriesAndYearsActionSuccess implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.GET_ALL_PAGED_MOVIE_OFCATEGORIESANDYEARS_SUCCESS;
  constructor(public payload:PageMovie) {
  }
}

export class GetAllPagedMoviesOfCategoriesAndYearsActionError implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.GET_ALL_PAGED_MOVIE_OFCATEGORIESANDYEARS_ERROR;
  constructor(public payload:string) {
  }
}

 /* Save Categories Actions*/

 export class SaveCategoriesAction implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.SAVE_CATEGORIES;
  constructor(public payload:categoryDTO) {
  }
}

export class SaveCategoriesActionSuccess implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.SAVE_CATEGORIES_SUCCESS;
  constructor(public payload:categoryDTO) {
  }
}

export class SaveCategoriesActionError implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.SAVE_CATEGORIES_ERROR;
  constructor(public payload:string) {
  }
}
 /* Save Categories Actions*/

 export class SaveMovieAction implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.SAVE_MOVIE;
  constructor(public payload:FormData) {
  }
}

export class SaveMovieActionSuccess implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.SAVE_MOVIE_SUCCESS;
  constructor(public payload:categoryDTO) {
  }
}

export class SaveMovieActionError implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.SAVE_MOVIE_ERROR;
  constructor(public payload:string) {
  }
}

 /* Get Movie  By Page Name*/

 export class GetMoviesByNameAction implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.GET_MOVIE;
  constructor(public payload:string) {
  }
}

export class GetMoviesByNameActionSuccess implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.GET_MOVIE_SUCCESS;
  constructor(public payload:MovieDTO) {
  }
}

export class GetMoviesByNameActionError implements Action{
  type: MoviesActionsTypes=MoviesActionsTypes.GET_MOVIE_ERROR;
  constructor(public payload:string) {
  }
}


  export type MoviesActions=
  GetAllMoviesAction | GetAllMoviesActionSuccess | GetAllMoviesActionError
|GetAllPagedCategoriesAction | GetAllPagedCategoriesActionSuccess | GetAllPagedCategoriesActionError
|SearchAllPagedCategoriesAction | SearchAllPagedCategoriesActionSuccess | SearchAllPagedCategoriesActionError
|GetAllPagedMoviesOfCategoriesAction | GetAllPagedMoviesOfCategoriesActionSuccess | GetAllPagedMoviesOfCategoriesActionError|
SaveCategoriesAction | SaveCategoriesActionSuccess | SaveCategoriesActionError;

