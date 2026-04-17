import { Component, inject } from '@angular/core';
import { NgIf } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterRequest } from '../../../../core/models/auth.models';
import { FlashMessageService } from '../../../../core/services/flash-message.service';
import { AuthService } from '../../services/auth.service';

@Component({
  standalone: true,
  imports: [NgIf, ReactiveFormsModule],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.css'
})
export class RegisterPageComponent {
  private readonly fb = inject(FormBuilder);
  private readonly authService = inject(AuthService);
  private readonly flashMessageService = inject(FlashMessageService);
  private readonly router = inject(Router);

  errorMessage = '';

  readonly form = this.fb.nonNullable.group({
    fullName: ['', Validators.required],
    cpf: ['', Validators.required],
    password: ['', [Validators.required, Validators.minLength(6)]],
    role: ['STAFF' as RegisterRequest['role'], Validators.required]
  });

  submit(): void {
    if (this.form.invalid) {
      return;
    }

    this.errorMessage = '';
    this.authService.createUser(this.form.getRawValue() as RegisterRequest).subscribe({
      next: () => {
        this.flashMessageService.set('Usuario cadastrado com sucesso.');
        void this.router.navigate(['/']);
      },
      error: (error) => {
        this.errorMessage = error?.error?.detail ?? 'Nao foi possivel cadastrar o usuario.';
      }
    });
  }
}
