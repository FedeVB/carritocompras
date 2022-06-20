import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpHeaders } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";


@Injectable()
export class TokenInterceptor implements HttpInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (localStorage.getItem('token_acces') !== null) {
            req = req.clone({
                setHeaders: { "Authorization": localStorage.getItem('token_acces')! }
            })
        }

        return next.handle(req);
    }
}