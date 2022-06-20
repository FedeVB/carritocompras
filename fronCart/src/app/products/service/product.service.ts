import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { Product } from '../interfaces/product.interface';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private url: string = environment.url;

  constructor(private http: HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.url}/product/all`)
  }

  getProductById(idProduct: number): Observable<Product> {
    return this.http.get<Product>(`${this.url}/product/id/${idProduct}`);
  }
}
