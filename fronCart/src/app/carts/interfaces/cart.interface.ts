import { Product } from "src/app/products/interfaces/product.interface";

export interface Cart{
    id?:number;
    totalPrice?:number;
    date?:Date;
    status?:string;
    products?:Product[];
}