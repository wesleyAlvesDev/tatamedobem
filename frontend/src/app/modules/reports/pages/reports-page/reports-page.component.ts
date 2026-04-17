import { Component, inject } from '@angular/core';
import { AsyncPipe, DatePipe, NgFor, NgIf } from '@angular/common';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { BehaviorSubject, combineLatest, map, startWith, switchMap } from 'rxjs';
import { AttendanceService } from '../../../attendance/services/attendance.service';
import { ReportsService } from '../../services/reports.service';

@Component({
  standalone: true,
  imports: [AsyncPipe, DatePipe, NgFor, NgIf, ReactiveFormsModule],
  templateUrl: './reports-page.component.html',
  styleUrl: './reports-page.component.css'
})
export class ReportsPageComponent {
  private readonly attendanceService = inject(AttendanceService);
  private readonly reportsService = inject(ReportsService);
  private readonly fb = inject(FormBuilder);
  private readonly refresh$ = new BehaviorSubject<void>(undefined);

  readonly form = this.fb.nonNullable.group({
    month: [new Date().toISOString().slice(0, 7)]
  });

  readonly vm$ = combineLatest([
    this.refresh$.pipe(
      startWith(undefined),
      switchMap(() => this.attendanceService.getMonthlyReport(this.form.getRawValue().month))
    ),
    this.reportsService.getSponsorReport(),
    this.reportsService.getAchievements()
  ]).pipe(
    map(([monthly, report, achievements]) => ({ monthly, report, achievements }))
  );

  reload(): void {
    this.refresh$.next();
  }
}
