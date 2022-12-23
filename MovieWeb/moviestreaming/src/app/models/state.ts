export interface NotificationEvent {
    type:DataStateEnum,
    payload?:string
  }
  export enum DataStateEnum {
    LOADING,
    LOADED,
    ERROR
  }
  
  export interface AppDataState<T> {
    dataState:DataStateEnum,
    data?:T,
    errorMessage?:string
  }