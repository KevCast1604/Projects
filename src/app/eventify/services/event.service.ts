import { Injectable } from '@angular/core';
import { BaseService } from "../../shared/services/base.service";
import { HttpClient } from "@angular/common/http";
import { Event } from "../model/event.entity";

@Injectable({
  providedIn: 'root'
})
export class EventService extends BaseService<Event>
{

  constructor(http: HttpClient) {
    super(http);
    this.resourceEndpoint = '/events';
  }
}
