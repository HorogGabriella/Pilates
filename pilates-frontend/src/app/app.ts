import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ClassList } from './components/class-list/class-list';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,ClassList],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('pilates-frontend');
}
