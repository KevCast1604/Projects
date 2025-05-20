export class Attendeed {

  id: number;
  firstName: string;
  lastName: string;
  eventId: number;
  ticketIdentifier: number;
  checkedInAt: string;

  constructor() {
    this.id = 0;
    this.firstName = '';
    this.lastName = '';
    this.eventId = 0;
    this.ticketIdentifier = 0;
    this.checkedInAt = '';
  }
}
