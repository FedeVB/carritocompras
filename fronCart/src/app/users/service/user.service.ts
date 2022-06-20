import { Injectable } from '@angular/core';
import { User } from '../interfaces/user.interface';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url: string = environment.url;
  private user: User = {
    id: 0,
    firstName: '',
    lastName: '',
    email: ''
  };

  constructor(private http: HttpClient) { }

  getByEmail(email: string): Observable<User> {
    return this.http.get<User>(`${this.url}/user/email/${email}`).pipe(
      tap(data => {
        this.user = data;
      })
    );
  }
}
