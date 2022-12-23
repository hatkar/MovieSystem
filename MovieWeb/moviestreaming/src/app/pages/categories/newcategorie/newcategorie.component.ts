import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { categoryDTO } from 'src/app/models/MovieDTO';
import { SaveCategoriesAction } from 'src/app/state/movie.actions';

@Component({
  selector: 'app-newcategorie',
  templateUrl: './newcategorie.component.html',
  styleUrls: ['./newcategorie.component.css']
})
export class NewcategorieComponent implements OnInit {
  categForm: any;

  constructor(private formBuilder :FormBuilder,private store:Store<any>) { }

  ngOnInit(): void {
    
    this.categForm = this.formBuilder.group({
      name:['', [Validators.required]],
      description:['', [Validators.required]]
      
    });
  }
  savecateg()
  {
    console.log('*****SAVE CATEGORIE BUTTON')
    const Categ =new categoryDTO();
    Categ.name="Horor"
    Categ.description="Best Horro Movie from Holywood and other country";
    Categ.movies=null;
this.store.dispatch(new SaveCategoriesAction(this.categForm?.value));
  }




}
