export interface AuthRequest {
  cpf: string;
  password: string;
}

export interface RegisterRequest {
  fullName: string;
  cpf: string;
  password: string;
  role: 'ADMIN' | 'TEACHER' | 'STAFF';
}

export interface AuthResponse {
  token: string;
  tokenType: string;
  userId: number;
  fullName: string;
  cpf: string;
  role: 'ADMIN' | 'TEACHER' | 'STAFF';
}
