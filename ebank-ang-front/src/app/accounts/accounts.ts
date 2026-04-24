import { Loading } from './../services/loading';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { catchError, map, Observable, of } from 'rxjs';
import { Account, AccountListState, RequestStatus } from '../model/account.model';

@Component({
  selector: 'app-accounts',
  imports: [CommonModule],
  templateUrl: './accounts.html',
  styleUrl: './accounts.css',
})
export class Accounts {
  private http = inject(HttpClient);
  public loadService = inject(Loading);
  
  accounts$: Observable<AccountListState> = this.http
    .get<Account[]>('http://localhost:9999/EBANK-SERVICE/account')
    .pipe(
      map((value) => {
        return { accounts: value, status: RequestStatus.SUCCESS };
      }),
      catchError((err, caught) => {
        return of({ status: RequestStatus.ERROR, errorMessage: err.statusText });
      }),
    );

  protected readonly RequestStatus = RequestStatus;
}
