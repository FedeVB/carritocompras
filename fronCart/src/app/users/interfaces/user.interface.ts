import { Cart } from "src/app/carts/interfaces/cart.interface"

export interface User {
    id: number,
    firstName: string,
    lastName: string,
    email: string
    cart?: Cart;
}