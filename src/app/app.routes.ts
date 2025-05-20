import { Routes } from '@angular/router';
import { HomeComponent } from "./public/pages/home/home.component";
import { PageNotFoundComponent } from "./public/pages/page-not-found/page-not-found.component";
import {RatingsComponent} from './public/pages/ratings/ratings.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'ratings', component: RatingsComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];
