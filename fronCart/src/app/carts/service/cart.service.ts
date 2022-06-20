import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { Cart } from '../interfaces/cart.interface';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private url: string = environment.url;
  constructor(private http: HttpClient) { }

  addProduct(idCart: number, idProduct: number): Observable<Cart> {
    return this.http.put<Cart>(`${this.url}/cart/addProduct/${idCart}/idProduct/${idProduct}`, {});
  }

  takeProduct(idCart: number, idProduct: number): Observable<Cart> {
    return this.http.put<Cart>(`${this.url}/cart/takeProduct/${idCart}/idProduct/${idProduct}`, {});
  }
}
