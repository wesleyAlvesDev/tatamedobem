import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  Achievement,
  DashboardSummary,
  InventoryAlert,
  SponsorReport
} from '../../../core/models/dashboard.models';
import { AttendanceRecord } from '../../../core/models/attendance.models';

@Injectable({ providedIn: 'root' })
export class DashboardService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = '/api';

  getSummary(): Observable<DashboardSummary> {
    return this.http.get<DashboardSummary>(`${this.baseUrl}/dashboard/summary`);
  }

  getSponsorReport(): Observable<SponsorReport> {
    return this.http.get<SponsorReport>(`${this.baseUrl}/dashboard/sponsor-report`);
  }

  getAttendanceAlerts(): Observable<AttendanceRecord[]> {
    return this.http.get<AttendanceRecord[]>(`${this.baseUrl}/attendance/alerts`);
  }

  getInventoryAlerts(): Observable<InventoryAlert[]> {
    return this.http.get<InventoryAlert[]>(`${this.baseUrl}/inventory/alerts`);
  }

  getAchievements(): Observable<Achievement[]> {
    return this.http.get<Achievement[]>(`${this.baseUrl}/achievements`);
  }
}
