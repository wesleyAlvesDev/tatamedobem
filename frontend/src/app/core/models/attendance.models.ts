export interface AttendanceRecord {
  id: number;
  studentId: number;
  studentName: string;
  attendanceDate: string;
  status: 'PRESENT' | 'ABSENT';
  notes: string;
  guardianAlertTriggered: boolean;
}

export interface CreateAttendanceRequest {
  studentId: number;
  attendanceDate: string;
  status: string;
  notes?: string;
}

export interface MonthlyAttendanceReport {
  month: string;
  totalClasses: number;
  totalPresents: number;
  totalAbsences: number;
  attendancePercentage: number;
  meetsMinimumAttendance: boolean;
}
