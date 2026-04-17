import { Injectable } from '@angular/core';

const FLASH_MESSAGE_KEY = 'tatamedobem.flash-message';

@Injectable({ providedIn: 'root' })
export class FlashMessageService {
  set(message: string): void {
    sessionStorage.setItem(FLASH_MESSAGE_KEY, message);
  }

  consume(): string {
    const message = sessionStorage.getItem(FLASH_MESSAGE_KEY) ?? '';
    sessionStorage.removeItem(FLASH_MESSAGE_KEY);
    return message;
  }
}
