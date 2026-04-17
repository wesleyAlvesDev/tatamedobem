import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  AttendanceRecord,
  CreateAttendanceRequest,
  MonthlyAttendanceReport
} from '../../../core/models/attendance.models';

@Injectable({ providedIn: 'root' })
export class AttendanceService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = '/api/attendance';

  getAttendanceAlerts(): Observable<AttendanceRecord[]> {
    return this.http.get<AttendanceRecord[]>(`${this.baseUrl}/alerts`);
  }

  createAttendance(payload: CreateAttendanceRequest): Observable<AttendanceRecord> {
    return this.http.post<AttendanceRecord>(this.baseUrl, payload);
  }

  getMonthlyReport(month: string): Observable<MonthlyAttendanceReport> {
    return this.http.get<MonthlyAttendanceReport>(`${this.baseUrl}/monthly-report`, { params: { month } });
  }
}
