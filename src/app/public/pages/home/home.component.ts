import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatGridListModule } from '@angular/material/grid-list';
import { EventService } from '../../../eventify/services/event.service';
import { Event } from '../../../eventify/model/event.entity';
import { EventSummaryComponent } from '../../components/event-summary/event-summary.component';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { TranslateModule} from '@ngx-translate/core';
import { Attendeed } from '../../../eventify/model/attendeed.entity';
import { Rating } from '../../../eventify/model/rating.entity';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, MatGridListModule, EventSummaryComponent, TranslateModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  events: Event[] = [];
  private attendees: Attendeed[] = [];
  private ratings: Rating[] = [];

  constructor(
    private eventService: EventService,
    private http: HttpClient
  ) {}

  ngOnInit() {
    this.loadData();
  }

  private loadData() {
    this.eventService.getAll().subscribe({
      next: (response: any) => {
        this.events = Array.isArray(response) ? response : [response];
      },
      error: (error) => {
        console.error('Error loading events:', error);
      }
    });

    // Cargar attendees y ratings
    this.http.get<Attendeed[]>(`${environment.serverBasePath}${environment.attendeesEndpointPath}`).subscribe({
      next: (attendees) => this.attendees = attendees,
      error: (error) => console.error('Error loading attendees:', error)
    });

    this.http.get<Rating[]>(`${environment.serverBasePath}${environment.ratingsEndpointPath}`).subscribe({
      next: (ratings) => this.ratings = ratings,
      error: (error) => console.error('Error loading ratings:', error)
    });
  }

  getCheckedInCount(eventId: number): number {
    return this.attendees.filter(a =>
      a.eventId === eventId && a.checkedInAt != null
    ).length;
  }

  getAverageRating(eventId: number): string {
    const eventRatings = this.ratings.filter(r => r.eventId === eventId);
    if (eventRatings.length === 0) return 'No ratings';

    const average = eventRatings.reduce((acc, curr) => acc + curr.rating, 0) / eventRatings.length;
    return average.toFixed(1);
  }
}

