import { Component, inject } from '@angular/core';
import { AsyncPipe, DatePipe, NgFor, NgIf } from '@angular/common';
import { combineLatest, map, startWith } from 'rxjs';
import { FlashMessageService } from '../../../../core/services/flash-message.service';
import { DashboardService } from '../../services/dashboard.service';

@Component({
  standalone: true,
  imports: [AsyncPipe, NgFor, NgIf, DatePipe],
  templateUrl: './dashboard-page.component.html',
  styleUrl: './dashboard-page.component.css'
})
export class DashboardPageComponent {
  private readonly dashboardService = inject(DashboardService);
  readonly flashMessage = inject(FlashMessageService).consume();
  readonly fallbackImage = 'https://images.unsplash.com/photo-1517836357463-d25dfeac3438';

  readonly vm$ = combineLatest([
    this.dashboardService.getSummary(),
    this.dashboardService.getSponsorReport(),
    this.dashboardService.getAttendanceAlerts(),
    this.dashboardService.getInventoryAlerts(),
    this.dashboardService.getAchievements()
  ]).pipe(
    map(([summary, report, attendanceAlerts, inventoryAlerts, achievements]) => ({
      metrics: [
        { label: 'Alunos ativos', value: summary.activeStudents },
        { label: 'Alertas de frequencia', value: summary.totalAttendanceAlerts },
        { label: 'Alertas de estoque', value: summary.inventoryAlerts },
        { label: 'Conquistas registradas', value: summary.totalAchievements }
      ],
      report,
      attendanceAlerts,
      inventoryAlerts,
      achievements
    })),
    startWith({
      metrics: [] as Array<{ label: string; value: number }>,
      report: {
        semesterLabel: '',
        studentsServed: 0,
        attendanceRate: 0,
        championshipWinners: 0,
        activeStudents: 0,
        zeroDropoutCount: 0,
        impactMessage: 'Carregando...'
      },
      attendanceAlerts: [],
      inventoryAlerts: [],
      achievements: []
    })
  );
}
