import {Component, OnInit} from '@angular/core';
import {ClassSession} from '../../models/class-session';
import {PilatesService} from '../../services/pilates.service';

@Component({
  selector: 'app-class-list',
  standalone: true,
  imports: [],
  templateUrl: './class-list.html',
  styleUrl: './class-list.css',
})
export class ClassList implements OnInit{

  classes: ClassSession[] = [];

  constructor(private pilatesService: PilatesService) {}
  ngOnInit(): void {
    this.pilatesService.getClasses().subscribe({
      next: (data) => {
        this.classes = data; // Itt kapjuk meg az adatokat a Javától!
      },
      error: (err) => {
        console.error('Hiba történt:', err);
      }
    });
  }

}
