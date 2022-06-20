import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListProductsComponent } from './list-products/list-products.component';



@NgModule({
  declarations: [
    ListProductsComponent
  ],
  exports:[
    ListProductsComponent
  ],
  imports: [
    CommonModule
  ]
})
export class ProductModule { }
