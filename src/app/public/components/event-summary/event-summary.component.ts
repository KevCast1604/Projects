import {Component, Input} from '@angular/core';
import {Event} from '../../../eventify/model/event.entity';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import {TranslateModule} from '@ngx-translate/core';

@Component({
  selector: 'app-event-summary',
  imports: [CommonModule, MatCardModule, TranslateModule],
  templateUrl: './event-summary.component.html',
  styleUrl: './event-summary.component.css'
})
export class EventSummaryComponent {
  @Input() event!: Event;
  @Input() checkedInCount: number = 0;
  @Input() averageRating: string = 'No ratings';
}
