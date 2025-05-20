import { Injectable } from '@angular/core';
import { BaseService } from "../../shared/services/base.service";
import { HttpClient } from "@angular/common/http";
import { Attendeed } from "../model/attendeed.entity";

@Injectable({
  providedIn: 'root'
})
export class AttendeedService extends BaseService<Attendeed>{
  constructor(http: HttpClient) {
    super(http);
    this.resourceEndpoint = '/attendees';
  }
}
