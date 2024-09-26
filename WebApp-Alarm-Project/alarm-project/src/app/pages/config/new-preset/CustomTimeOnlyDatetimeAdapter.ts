import { Injectable, Optional, Inject } from '@angular/core';
import { DateAdapter, MAT_DATE_LOCALE } from '@angular/material/core';
import { DatetimeAdapter } from '@mat-datetimepicker/core';

@Injectable()
export class CustomTimeOnlyDatetimeAdapter extends DatetimeAdapter<Date> {

  constructor(
    @Optional() @Inject(MAT_DATE_LOCALE) private matDateLocale: string,
    private delegate: DateAdapter<Date>
  ) {
    super(delegate);
  }

  getDisplayFormat(): string {
    return 'HH:mm'; // Format hours and minutes only
  }

  override getHour(date: Date): number {
    return date.getHours();
  }

  override getMinute(date: Date): number {
    return date.getMinutes();
  }

  override getFirstDateOfMonth(date: Date): Date {
    const newDate = new Date(date);
    newDate.setDate(1);
    return newDate;
  }

  override isInNextMonth(startDate: Date, endDate: Date): boolean {
    const startMonth = startDate.getMonth();
    const endMonth = endDate.getMonth();
    return startMonth < endMonth || (startDate.getFullYear() < endDate.getFullYear());
  }

  override getHourNames(): string[] {
    return Array.from({length: 24}, (_, i) => i.toString().padStart(2, '0'));
  }

  override getMinuteNames(): string[] {
    return Array.from({length: 60}, (_, i) => i.toString().padStart(2, '0'));
  }

  override addCalendarHours(date: Date, hours: number): Date {
    const newDate = new Date(date);
    newDate.setHours(newDate.getHours() + hours);
    return newDate;
  }

  override addCalendarMinutes(date: Date, minutes: number): Date {
    const newDate = new Date(date);
    newDate.setMinutes(newDate.getMinutes() + minutes);
    return newDate;
  }

  override createDatetime(year: number, month: number, day: number, hour: number, minute: number): Date {
    const newDate = new Date();
    newDate.setHours(hour, minute, 0, 0);
    return newDate;
  }

  override compareDatetime(first: Date, second: Date, respectMinutePart: boolean = true): number {
    const firstTime = first.getTime();
    const secondTime = second.getTime();
    if (respectMinutePart) {
      return firstTime - secondTime;
    } else {
      return this.getHour(first) - this.getHour(second);
    }
  }

  override sameDatetime(first: Date | null, second: Date | null): boolean {
    return first?.getTime() === second?.getTime();
  }

  override sameYear(first: Date, second: Date): boolean {
    return first.getFullYear() === second.getFullYear();
  }

  override sameDay(first: Date, second: Date): boolean {
    return first.toDateString() === second.toDateString();
  }

  override sameHour(first: Date, second: Date): boolean {
    return this.getHour(first) === this.getHour(second);
  }

  override sameMinute(first: Date, second: Date): boolean {
    return this.getMinute(first) === this.getMinute(second);
  }

  override sameMonthAndYear(first: Date | null, second: Date | null): boolean {
    return first?.getFullYear() === second?.getFullYear() &&
      first?.getMonth() === second?.getMonth();
  }

  override deserialize(value: any): Date | null {
    return value ? new Date(value) : null;
  }

  override clone(date: Date): Date {
    return new Date(date.getTime());
  }

  override addCalendarYears(date: Date, years: number): Date {
    const newDate = new Date(date);
    newDate.setFullYear(newDate.getFullYear() + years);
    return newDate;
  }

  override addCalendarMonths(date: Date, months: number): Date {
    const newDate = new Date(date);
    newDate.setMonth(newDate.getMonth() + months);
    return newDate;
  }

  override addCalendarDays(date: Date, days: number): Date {
    const newDate = new Date(date);
    newDate.setDate(newDate.getDate() + days);
    return newDate;
  }

  override getYear(date: Date): number {
    return date.getFullYear();
  }

  override getMonth(date: Date): number {
    return date.getMonth();
  }

  override getDate(date: Date): number {
    return date.getDate();
  }

  override getDayOfWeek(date: Date): number {
    return date.getDay();
  }

  override getMonthNames(style: any): string[] {
    return Array.from({length: 12}, (_, i) => new Date(2000, i).toLocaleString(this.matDateLocale, {month: style}));
  }

  override getDateNames(): string[] {
    return Array.from({length: 31}, (_, i) => (i + 1).toString());
  }

  override getDayOfWeekNames(style: any): string[] {
    return Array.from({length: 7}, (_, i) => new Date(2000, 0, i).toLocaleString(this.matDateLocale, {weekday: style}));
  }

  override getYearName(date: Date): string {
    return date.getFullYear().toString();
  }

  override getFirstDayOfWeek(): number {
    return 0; // Sunday
  }

  override getNumDaysInMonth(date: Date): number {
    return new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();
  }

  override createDate(year: number, month: number, date: number): Date {
    return new Date(year, month, date);
  }

  override today(): Date {
    return new Date();
  }

  override parse(value: any, parseFormat: any): Date | null {
    return value ? new Date(value) : null;
  }

  override format(date: Date, displayFormat: any): string {
    return `${this._toTwoDigit(date.getHours())}:${this._toTwoDigit(date.getMinutes())}`;
  }

  override toIso8601(date: Date): string {
    return date.toISOString();
  }

  override isDateInstance(obj: any): boolean {
    return obj instanceof Date;
  }

  override isValid(date: Date): boolean {
    return !isNaN(date.getTime());
  }

  override invalid(): Date {
    return new Date(NaN);
  }

  override clampDate(date: Date, min?: Date | null, max?: Date | null): Date {
    if (min && date < min) {
      return min;
    }
    if (max && date > max) {
      return max;
    }
    return date;
  }

  // Utility to pad the hours and minutes to two digits
  private _toTwoDigit(value: number): string {
    return value < 10 ? `0${value}` : `${value}`;
  }
}
