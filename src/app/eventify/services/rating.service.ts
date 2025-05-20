import { Injectable } from '@angular/core';
import { BaseService } from "../../shared/services/base.service";
import { HttpClient } from "@angular/common/http";
import { Rating } from "../model/rating.entity";


@Injectable({
  providedIn: 'root'
})
export class RatingService extends BaseService<Rating> {

  constructor(http: HttpClient) {
    super(http);
    this.resourceEndpoint = '/ratings';
  }
}
