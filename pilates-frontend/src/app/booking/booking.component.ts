import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule, NgIf, NgFor } from '@angular/common';

export interface Booking {
  foglalasId: number;
  oraId: number;
  resztvevoNeve: string;
}

@Component({
  selector: 'app-booking',
  standalone: true,
  imports: [CommonModule, NgIf, NgFor],
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  foglalasok: Booking[] = [];
  loading = false;
  error = '';
  infoMessage = '';

  private readonly API_BASE = "http://localhost:8080/api";

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadBookings();
  }

  loadBookings(): void {
    this.loading = true;
    this.error = '';
    this.infoMessage = '';

    this.http.get<Booking[]>(`${this.API_BASE}/foglalas`)
      .subscribe({
        next: data => {
          this.foglalasok = data;
          this.loading = false;
        },
        error: () => {
          this.error = 'Nem sikerült betölteni a foglalásokat.';
          this.loading = false;
        }
      });
  }

  cancelBooking(f: Booking): void {
    this.error = '';
    this.infoMessage = '';

    this.http.delete(`${this.API_BASE}/foglalas/${f.foglalasId}`)
      .subscribe({
        next: () => {
          this.infoMessage = 'Foglalás sikeresen törölve.';
          this.loadBookings();
        },
        error: () => {
          this.error = 'Nem sikerült törölni a foglalást.';
        }
      });
  }
}
