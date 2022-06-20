import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Login } from '../interfaces/login.interface';
import { Observable, tap } from 'rxjs';
import { environment } from '../../../environments/environment';
import { UserService } from '../../users/service/user.service';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url: string = environment.url;
  constructor(private http: HttpClient, private userService: UserService) { }

  login(login: Login): Observable<any> {
    return this.http.post<any>(`${this.url}/auth/login`, login).pipe(
      tap(data => {
        localStorage.setItem('token_acces', data.token);
        let token: string[] = data.token.split('.');
        const email: string = JSON.parse(atob(token[1])).sub;
        this.userService.getByEmail(email).subscribe(data=>{});
      })
    );
  }

}
