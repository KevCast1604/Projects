
import { Component, ChangeDetectionStrategy, signal } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { CommonModule } from '@angular/common';
import { RatingService } from '../../../eventify/services/rating.service';
import { Rating } from '../../../eventify/model/rating.entity';

@Component({
  selector: 'app-ratings',
  standalone: true,
  imports: [
    CommonModule,
    TranslateModule,
    MatButtonModule,
    MatDividerModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule
  ],
  templateUrl: './ratings.component.html',
  styleUrl: './ratings.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class RatingsComponent {
  ratingForm: FormGroup;
  successMessage = signal<string>('');
  errorMessage = signal<string>('');

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private ratingService: RatingService
  ) {
    this.ratingForm = this.fb.group({
      ticketIdentifier: ['', [Validators.required]],
      rating: ['', [Validators.required, Validators.min(1), Validators.max(5)]]
    });
  }

  async onSubmit() {
    if (this.ratingForm.valid) {
      const ticketId = this.ratingForm.get('ticketIdentifier')?.value;
      const ratingValue = this.ratingForm.get('rating')?.value;

      try {
        // Buscar el attendee por ticketIdentifier
        const attendees = await this.http.get<any[]>(
          `${environment.serverBasePath}${environment.attendeesEndpointPath}?ticketIdentifier=${ticketId}`
        ).toPromise();

        if (!attendees || attendees.length === 0) {
          this.errorMessage.set('Invalid Ticket Identifier');
          return;
        }

        const attendee = attendees[0];

        // Verificar si el checkedInAt está vacío o nulo
        if (!attendee.checkedInAt || attendee.checkedInAt === '') {
          this.errorMessage.set('You can rating only to the events that you attend.');
          return;
        }

        // Verificar si ya existe un rating para este attendee
        const existingRatings = await this.http.get<any[]>(
          `${environment.serverBasePath}${environment.ratingsEndpointPath}?attendeeId=${attendee.id}`
        ).toPromise();

        if (existingRatings && existingRatings.length > 0) {
          this.errorMessage.set('You rating this event before');
          return;
        }

        // Crear nuevo rating
        const newRating: Rating = {
          id: 0, // El servidor asignará el ID
          attendeeId: attendee.id,
          eventId: attendee.eventId,
          rating: ratingValue,
          ratedAt: new Date().toISOString()
        };

        await this.ratingService.create(newRating).toPromise();
        this.successMessage.set('Event successfully rated');
        this.errorMessage.set('');
        this.ratingForm.reset();

      } catch (error) {
        console.error('Error:', error);
        this.errorMessage.set('An error occurred while processing your request');
      }
    } else {
      if (this.ratingForm.get('rating')?.errors) {
        this.errorMessage.set('Rating must be between 1 and 5');
      }
    }
  }
}
