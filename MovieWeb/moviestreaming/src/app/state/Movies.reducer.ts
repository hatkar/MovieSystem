import { Action } from "@ngrx/store";
import { categoryDTO, MovieDTO, Pagecategory, PageMovie } from "../models/MovieDTO";
import { MoviesActions, MoviesActionsTypes } from "./movie.actions";

export enum MoviesStateEnum{
    LOADING="Loading DATA",
    LOADED="Loaded",
    ERROR="Error",
    INITIAL="Initial",
    SAVING ="SAVING DATA ...",
    SAVED ="ITEM SUCCSUFUL SAVED"
  }
  export interface MoviesState{
    Movies:MovieDTO[],
    Movie:MovieDTO,
    MoviePage:PageMovie,
    CategoriePages:Pagecategory,
    Categogories:categoryDTO[]
    errorMessage:string,
    dataState:MoviesStateEnum
}
const initState:MoviesState={
  Movies: [],
  errorMessage: "",
  dataState: MoviesStateEnum.INITIAL,
  CategoriePages: new Pagecategory(),
  MoviePage: new PageMovie(),
  Movie: new MovieDTO,
  Categogories: []
}

  export function moviesReducer(state=initState, action:Action):MoviesState {
    switch (action.type) {
      case MoviesActionsTypes.GET_ALL_MOVIES||MoviesActionsTypes.GET_ALL_PAGED_CATEGORIES:
        console.log('REDUCER : GET_ALL_MOVIES');
        return {...state, dataState:MoviesStateEnum.LOADING }
      case MoviesActionsTypes.GET_ALL_MOVIES_SUCCESS:
        console.log('REDUCER : GET_ALL_MOVIES_SUCCESS');
        return {...state, dataState:MoviesStateEnum.LOADED, Movies:(<MoviesActions>action).payload}
      case MoviesActionsTypes.GET_ALL_MOVIES_ERROR:
        console.log('REDUCER : GET_ALL_MOVIES_ERROR');
        return {...state, dataState:MoviesStateEnum.ERROR, errorMessage:(<MoviesActions>action).payload}

        /*GET All Categories*/
      case MoviesActionsTypes.GET_ALL_CATEGORIES:
        console.log('REDUCER : GET_ALL_PAGED_CATEGORIES');
        return {...state, dataState:MoviesStateEnum.LOADING }
      case MoviesActionsTypes.GET_ALL_CATEGORIES_SUCCESS:
        console.log('REDUCER : GET_ALL_PAGED_CATEGORIES_SUCCESS');
        return {...state, dataState:MoviesStateEnum.LOADED, Categogories:(<MoviesActions>action).payload}
      case MoviesActionsTypes.GET_ALL_CATEGORIES_ERROR:
        console.log('REDUCER : GET_ALL_PAGED_CATEGORIES_ERROR');
        return {...state, dataState:MoviesStateEnum.ERROR, errorMessage:(<MoviesActions>action).payload}
  

        /*paged Categories*/
      case MoviesActionsTypes.GET_ALL_PAGED_CATEGORIES:
        console.log('REDUCER : GET_ALL_PAGED_CATEGORIES');
        return {...state, dataState:MoviesStateEnum.LOADING }
      case MoviesActionsTypes.GET_ALL_PAGED_CATEGORIES_SUCCESS:
        console.log('REDUCER : GET_ALL_PAGED_CATEGORIES_SUCCESS');
        return {...state, dataState:MoviesStateEnum.LOADED, CategoriePages:(<MoviesActions>action).payload}
      case MoviesActionsTypes.GET_ALL_PAGED_CATEGORIES_ERROR:
        console.log('REDUCER : GET_ALL_PAGED_CATEGORIES_ERROR');
        return {...state, dataState:MoviesStateEnum.ERROR, errorMessage:(<MoviesActions>action).payload}
    
/*search paged Categories*/
case MoviesActionsTypes.SEARCH_ALL_PAGED_CATEGORIES:
  console.log('REDUCER : SEARCH_ALL_PAGED_CATEGORIES');
  return {...state, dataState:MoviesStateEnum.LOADING }
case MoviesActionsTypes.SEARCH_ALL_PAGED_CATEGORIES_SUCCESS:
  console.log('REDUCER : SEARCH_ALL_PAGED_CATEGORIES_SUCCESS');
  return {...state, dataState:MoviesStateEnum.LOADED, CategoriePages:(<MoviesActions>action).payload}
case MoviesActionsTypes.SEARCH_ALL_PAGED_CATEGORIES_ERROR:
  console.log('REDUCER : SEARCH_ALL_PAGED_CATEGORIES_ERROR');
  return {...state, dataState:MoviesStateEnum.ERROR, errorMessage:(<MoviesActions>action).payload}
  
        /*paged Movie of Categorie*/
      case MoviesActionsTypes.GET_ALL_PAGED_MOVIE_OFCATEGORIES:
        console.log('REDUCER : GET_ALL_PAGED_MOVIE');
        return {...state, dataState:MoviesStateEnum.LOADING }
      case MoviesActionsTypes.GET_ALL_PAGED_MOVIE_OFCATEGORIES_SUCCESS:
        console.log('REDUCER : GET_ALL_PAGED_CATEGORIES_SUCCESS');
        return {...state, dataState:MoviesStateEnum.LOADED, MoviePage:(<MoviesActions>action).payload}
      case MoviesActionsTypes.GET_ALL_PAGED_MOVIE_OFCATEGORIES_ERROR:
        console.log('REDUCER : GET_ALL_PAGED_CATEGORIES_ERROR');
        return {...state, dataState:MoviesStateEnum.ERROR, errorMessage:(<MoviesActions>action).payload}
        /*paged Movie of Categorie And Year*/
        case MoviesActionsTypes.GET_ALL_PAGED_MOVIE_OFCATEGORIESANDYEARS:
          console.log('REDUCER : GET_ALL_PAGED_MOVIE');
          return {...state, dataState:MoviesStateEnum.LOADING }
        case MoviesActionsTypes.GET_ALL_PAGED_MOVIE_OFCATEGORIESANDYEARS_SUCCESS:
          console.log('REDUCER : GET_ALL_PAGED_CATEGORIES_SUCCESS');
          return {...state, dataState:MoviesStateEnum.LOADED, MoviePage:(<MoviesActions>action).payload}
        case MoviesActionsTypes.GET_ALL_PAGED_MOVIE_OFCATEGORIESANDYEARS_ERROR:
          console.log('REDUCER : GET_ALL_PAGED_CATEGORIES_ERROR');
          return {...state, dataState:MoviesStateEnum.ERROR, errorMessage:(<MoviesActions>action).payload}
        /*paged searched Movies r*/
        case MoviesActionsTypes.SEARCH_ALL_PAGED_CATEGORIES:
          console.log('REDUCER : GET_ALL_PAGED_MOVIE');
          return {...state, dataState:MoviesStateEnum.LOADING }
        case MoviesActionsTypes.SEARCH_ALL_PAGED_MOVIE_SUCCESS:
          console.log('REDUCER : GET_ALL_PAGED_CATEGORIES_SUCCESS');
          return {...state, dataState:MoviesStateEnum.LOADED, MoviePage:(<MoviesActions>action).payload}
        case MoviesActionsTypes.SEARCH_ALL_PAGED_MOVIE_ERROR:
          console.log('REDUCER : GET_ALL_PAGED_CATEGORIES_ERROR');
          return {...state, dataState:MoviesStateEnum.ERROR, errorMessage:(<MoviesActions>action).payload}
        
      /*save Categories*/
      case MoviesActionsTypes.SAVE_CATEGORIES:
      console.log('SAVE CATEGORIE ACTION');
        return {...state, dataState:MoviesStateEnum.SAVING }
      case MoviesActionsTypes.SAVE_CATEGORIES_SUCCESS:
        console.log('SAVE CATEGORIE ACTION SUCCESS');
        return {...state, dataState:MoviesStateEnum.SAVED}
      case MoviesActionsTypes.SAVE_CATEGORIES_ERROR:
        console.log('SAVE CATEGORIE ACTION ERROR');
        return {...state, dataState:MoviesStateEnum.ERROR, errorMessage:(<MoviesActions>action).payload}
          /*save Movie*/
      case MoviesActionsTypes.SAVE_MOVIE:
        console.log('SAVE CATEGORIE ACTION');
          return {...state, dataState:MoviesStateEnum.SAVING }
        case MoviesActionsTypes.SAVE_MOVIE_SUCCESS:
          console.log('SAVE CATEGORIE ACTION SUCCESS');
          return {...state, dataState:MoviesStateEnum.SAVED}
        case MoviesActionsTypes.SAVE_MOVIE_ERROR:
          console.log('SAVE CATEGORIE ACTION ERROR');
          return {...state, dataState:MoviesStateEnum.ERROR, errorMessage:(<MoviesActions>action).payload}
     /* GET MOVIE BY NAME */
     case MoviesActionsTypes.GET_MOVIE:
      console.log('REDUCER : GET_MOVIES');
      return {...state, dataState:MoviesStateEnum.LOADING }
    case MoviesActionsTypes.GET_MOVIE_SUCCESS:
      console.log('REDUCER : GET_MOVIES_SUCCESS');
      return {...state, dataState:MoviesStateEnum.LOADED, Movie:(<MoviesActions>action).payload}
    case MoviesActionsTypes.GET_MOVIE_ERROR:
      console.log('REDUCER : GET_ALL_MOVIES_ERROR');
      return {...state, dataState:MoviesStateEnum.ERROR, errorMessage:(<MoviesActions>action).payload}
        default : return {...state}
    }
  }