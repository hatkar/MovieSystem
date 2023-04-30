import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { MenubarComponent } from './pages/Components/menubar/menubar.component';
import { FooterComponent } from './pages/Components/footer/footer.component';
import { NewmovieComponent } from './pages/newmovie/newmovie.component';
import { DetailsmovieComponent } from './pages/detailsmovie/detailsmovie.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NewcategorieComponent } from './pages/categories/newcategorie/newcategorie.component';
import { ListcategorieComponent } from './pages/categories/listcategorie/listcategorie.component';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import {moviesReducer} from './state/Movies.reducer'
import { MoviesEffects } from './state/Movies.effects';
import { ListmoviebycategComponent } from './pages/categories/listmoviebycateg/listmoviebycateg.component';
import { LoadingComponent } from './pages/components/loading/loading.component';
import { SearchedmovieComponent } from './pages/searchedmovie/searchedmovie.component';
import { LoginComponent } from './pages/login/login.component';
import { NotfoundComponent } from './pages/login/notfound/notfound.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MenubarComponent,
    FooterComponent,
    NewmovieComponent,
    DetailsmovieComponent,
    NewcategorieComponent,
    ListcategorieComponent,
    ListmoviebycategComponent,
    LoadingComponent,
    SearchedmovieComponent,
    LoginComponent,
    NotfoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    StoreModule.forRoot({catalogState:moviesReducer}),
    EffectsModule.forRoot([MoviesEffects]),
    StoreDevtoolsModule.instrument()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
