import {DOCUMENT} from '@angular/common';
import {HttpClient} from '@angular/common/http';
import {Component, ElementRef, Inject, Input, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {map, Observable} from 'rxjs';
import {categoryDTO, MovieDTO} from 'src/app/models/MovieDTO';
import {MovieService} from 'src/app/services/movie.service';
import {GetAllCategoriesAction, SaveMovieAction} from 'src/app/state/movie.actions';
import {MoviesState, MoviesStateEnum} from 'src/app/state/Movies.reducer';

@Component({
  selector: 'app-newmovie',
  templateUrl: './newmovie.component.html',
  styleUrls: ['./newmovie.component.css']
})
export class NewmovieComponent implements OnInit {
  movieForm: any;
  movieFile!: File;
  pictureFile!: File;
  movie: MovieDTO = new MovieDTO;
  categories: categoryDTO[] = [];
  categorieidtt: string = ""
  //movieState$:Observable<MoviesState>|null=null;
  readonly DataStateEnum = MoviesStateEnum;
  //@Input() selectedcateg:string | undefined;
  //@ViewChild("categmov") myNameElem: ElementRef;
  categname: string = ""
  categid: string = ""
  clickedcategorie: categoryDTO = new categoryDTO;

  constructor(private formBuilder: FormBuilder, @Inject(DOCUMENT) document: Document,
              private uploadService: MovieService, private store: Store<any>, private route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      this.clickedcategorie.id = params['idcategorie']
      if (this.clickedcategorie.id) console.log("==>ID CATEGORIE" + this.clickedcategorie.id);
      else {
        this.clickedcategorie.id = ""
        //console.log("===>ID CATEGORIE"+this.clickedcategorie.id);
      }
    })
    // @ViewChild("categmov") myNameElem: ElementRef;
    this.createForm();
  }

  createForm() {
    this.movieForm = this.formBuilder.group({
      moviename: ['', [Validators.required]],
      description: ['', [Validators.required]],

      duration: ['', [Validators.required]],
      releaseyear: ['', [Validators.required]],
      filemovie: '',
      picturefile: ''
    });


    this.store.subscribe((state) => {
      if (state.catalogState.dataState == this.DataStateEnum.LOADED) {
        this.categories = state.catalogState.Categogories;
        if (this.clickedcategorie.id) {

          this.categories.map(cat => {
            if (cat.id == this.clickedcategorie.id) this.clickedcategorie = cat
          })
          console.log("==>ID CATEGORIE" + this.clickedcategorie.id);
        } else {

          this.clickedcategorie = state.catalogState.Categogories[0]

        }

        console.log("=======>**<======")
        console.log(this.categories)
      }
    });


    this.store.dispatch(new GetAllCategoriesAction());

  }

  selectedcategorie(categ: categoryDTO) {

    this.clickedcategorie = categ


  }

  saveMovie() {
    const formData = new FormData();

    formData.append('id', '');


  }

  ngOnInit(): void {
    this.uploadService.getcategories().subscribe(resp => {

    });
  }

  onFilemoviechange(event: any) {

    this.movieFile = event.target.files[0]
  }

  onFilepicturechange(event: any) {
    //console.log(event.target.files[0])
    this.pictureFile = event.target.files[0]
  }

  onFormSubmit() {
    const movie: MovieDTO = this.movieForm.value;
    var inputValue = (<HTMLInputElement>document.getElementById('categmov')).value;
    movie.categorieid = inputValue;
    const formData = new FormData();
    formData.append('file', new Blob([this.pictureFile]), this.pictureFile.name);
    formData.append('moviefile', new Blob([this.movieFile]), this.movieFile.name);

    formData.append('movie', new Blob([JSON.stringify({
      "moviename": movie.moviename,
      "description": movie.description,
      "duration": movie.duration,
      "releaseyear": movie.releaseyear,
      "categorieid": this.clickedcategorie.id
    })], {
      type: "application/json"
    }));

    this.store.dispatch(new SaveMovieAction(formData));
    this.createForm();


  }

}
