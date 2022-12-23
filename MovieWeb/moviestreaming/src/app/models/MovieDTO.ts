export class MovieDTO { 

    id?: any;
    moviename?: string;
    description?: string;
    categorieid?: string;
    duration?: string;
    releaseyear?:string;
    fileurl?:string;
    imageurl?:File;
    
    

    
  }
  export class MovieDTORequest { 
   
    moviename?: string;
    description?: string;
    categorieid?: string;
    
    duration?: string;
    releaseyear?:string;
    

    
  }
  export class categoryDTO{
    id?:string;
    name?:string;
    description?: string;
movies?:MovieDTO[]|null=null;
  }
  export class Pagecategory{
  
    content?:categoryDTO[];
    pageNumber?:number;
    pageSize?:number;
    totalElements?:number;
    totalPages?:number;
    first?:boolean;
    last?:boolean;
  }
  export class PageMovie{
    content?:MovieDTO[];
    pageNumber?:number;
    pageSize?:number;
    totalElements?:number;
    totalPages?:number;
    first?:boolean;
    last?:boolean;
  }