import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListcategorieComponent } from './pages/categories/listcategorie/listcategorie.component';
import { ListmoviebycategComponent } from './pages/categories/listmoviebycateg/listmoviebycateg.component';
import { NewcategorieComponent } from './pages/categories/newcategorie/newcategorie.component';
import { DetailsmovieComponent } from './pages/detailsmovie/detailsmovie.component';
import { HomeComponent } from './pages/home/home.component';
import { NewmovieComponent } from './pages/newmovie/newmovie.component';
import { SearchedmovieComponent } from './pages/searchedmovie/searchedmovie.component';

const routes: Routes = [
  {path:'',redirectTo:"home",pathMatch:'full'},
  {path: 'home', component: HomeComponent},
  {path: 'newmovie',component:NewmovieComponent},
  { path: 'details/:moviename', component: DetailsmovieComponent },
  {path:'admin/listcategories',component:ListcategorieComponent},
  {path:'admin/newcategorie',component:NewcategorieComponent},
  {path:'admin/listmovie/:idcategorie',component:ListmoviebycategComponent},
  {path:'admin/listmovie',component:ListmoviebycategComponent},
  { path: 'admin/details/:moviename', component: DetailsmovieComponent },
  { path: 'admin/newmovie/:idcategorie', component:NewmovieComponent },
  { path: 'admin/newmovie', component:NewmovieComponent },
  { path: 'search/:keyword', component:SearchedmovieComponent }
  
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
