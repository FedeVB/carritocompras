import { Component, OnInit } from '@angular/core';
import { Cart } from 'src/app/carts/interfaces/cart.interface';
import { CartService } from 'src/app/carts/service/cart.service';
import { Product } from '../interfaces/product.interface';
import { ProductService } from '../service/product.service';

@Component({
  selector: 'app-list-products',
  templateUrl: './list-products.component.html',
  styleUrls: ['./list-products.component.css']
})
export class ListProductsComponent implements OnInit {

  private _products: Product[] = [];

  private _cart: Cart = {
    id: 16,
    products: [] = []
  };

  constructor(private productService: ProductService, private cartService: CartService) { }

  ngOnInit(): void {
    this.productService.getProducts().subscribe(data => {
      this._products = data;
    })
  }

  get products(): Product[] {
    return [...this._products];
  }

  addProduct(idProduct: number) {
    if (!this._cart.products?.find(prod => prod.id === idProduct)) {
      this.cartService.addProduct(this._cart.id!, idProduct).subscribe(cart => {
        console.log(cart)
        this._cart = cart;
      })
    }
  }

  takeProduct(idProduct: number) {
    this.cartService.takeProduct(this._cart.id!, idProduct).subscribe(cart => {
      console.log(cart);
      this._cart = cart;
    })
  }

}
