import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListcategorieComponent } from './pages/categories/listcategorie/listcategorie.component';
import { ListmoviebycategComponent } from './pages/categories/listmoviebycateg/listmoviebycateg.component';
import { NewcategorieComponent } from './pages/categories/newcategorie/newcategorie.component';
import { DetailsmovieComponent } from './pages/detailsmovie/detailsmovie.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { NotfoundComponent } from './pages/login/notfound/notfound.component';
import { NewmovieComponent } from './pages/newmovie/newmovie.component';
import { SearchedmovieComponent } from './pages/searchedmovie/searchedmovie.component';
import { AuthGuard } from './services/_helpers/auth.guard';

const routes: Routes = [
  {path:'',redirectTo:"home",pathMatch:'full'},
  {path: 'home', component: HomeComponent},
  {path: 'newmovie',component:NewmovieComponent},
  { path: 'search/:keyword', component:SearchedmovieComponent },
  {path: 'signin',component:LoginComponent},
  {path: 'unhautorized',component:NotfoundComponent},
  { path: 'details/:moviename', component: DetailsmovieComponent },
  {path:'admin/listcategories',component:ListcategorieComponent,canActivate: [AuthGuard],data: { roles: ["ADMIN"]}},
  {path:'admin/newcategorie',component:NewcategorieComponent,canActivate: [AuthGuard],data: { roles: ["ADMIN"]}},
  {path:'admin/listmovie/:idcategorie',component:ListmoviebycategComponent,canActivate: [AuthGuard],data: { roles: ["ADMIN"]}},
  {path:'admin/listmovie',component:ListmoviebycategComponent,canActivate: [AuthGuard],data: { roles: ["ADMIN"]}},
  { path: 'admin/details/:moviename', component: DetailsmovieComponent ,canActivate: [AuthGuard],data: { roles: ["ADMIN"]}},
  { path: 'admin/newmovie/:idcategorie', component:NewmovieComponent ,canActivate: [AuthGuard],data: { roles: ["ADMIN"]}},
  { path: 'admin/newmovie', component:NewmovieComponent }




];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
