import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { finalize } from 'rxjs/operators';

import { AuthService } from '../auth/auth.service';
import { ClassService } from './class.service';
import { ClassSession } from './class.model';

export interface BookingDto {
  foglalasId: number;
  oraId: number;
  oratipus: string;
  oktato: string;
  idopont: string;
}

@Component({
  standalone: true,
  selector: 'app-class',
  imports: [CommonModule, RouterModule],
  templateUrl: './class.component.html',
  styleUrls: ['./class.component.css']
})
export class ClassComponent implements OnInit {

  sessions: ClassSession[] = [];
  loading = true;

  bookedIds = new Set<number>();
  inFlightIds = new Set<number>();

  message = '';
  error = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private auth: AuthService,
    private classes: ClassService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    const resolved = this.route.snapshot.data['data'];
    const sessions = resolved?.sessions ?? [];
    const bookings = resolved?.bookings ?? [];

    this.sessions = this.sortByDate(sessions);
    this.bookedIds = new Set(bookings.map((b: any) => b.oraId));
    this.loading = false;
  }

  private sortByDate(list: ClassSession[]): ClassSession[] {
    return [...list].sort(
      (a, b) => new Date(a.time).getTime() - new Date(b.time).getTime()
    );
  }

  private refreshBookedIds(): void {
    this.http.get<BookingDto[]>('http://localhost:8080/api/foglalas/sajat').subscribe({
      next: (bookings) => {
        this.bookedIds = new Set(bookings.map(b => b.oraId));
      },
      error: () => {
        this.bookedIds = new Set<number>();
      }
    });
  }

  isBooked(c: ClassSession): boolean {
    return this.bookedIds.has(c.id);
  }

  loadSessions(): void {
    this.loading = true;

    this.classes.getAll()
      .pipe(finalize(() => (this.loading = false)))
      .subscribe({
        next: (data) => {
          this.sessions = this.sortByDate(data);
          this.refreshBookedIds();
        },
        error: () => {
          this.error = 'Nem sikerült betölteni az órákat';
        }
      });
  }

  getFreeSpots(c: ClassSession): number {
    return c.capacity - c.bookedspots;
  }

  bookClass(c: ClassSession): void {
    this.message = '';
    this.error = '';

    if (this.inFlightIds.has(c.id) || this.isBooked(c) || this.getFreeSpots(c) <= 0) return;

    this.inFlightIds.add(c.id);

    this.bookedIds.add(c.id);
    c.bookedspots = (c.bookedspots ?? 0) + 1;
    this.sessions = [...this.sessions];

    this.classes.book(c.id)
      .pipe(finalize(() => this.inFlightIds.delete(c.id)))
      .subscribe({
        next: () => {
          this.message = 'Sikeres jelentkezés!';
          setTimeout(() => (this.message = ''), 1500);

          this.refreshBookedIds();
        },
        error: (err) => {
          this.bookedIds.delete(c.id);
          c.bookedspots = Math.max(0, (c.bookedspots ?? 0) - 1);
          this.sessions = [...this.sessions];

          this.error = (typeof err?.error === 'string' ? err.error : '') || 'Nem sikerült a jelentkezés.';
          setTimeout(() => (this.error = ''), 2500);

          this.refreshBookedIds();
        }
      });
  }


  logout(): void {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}
