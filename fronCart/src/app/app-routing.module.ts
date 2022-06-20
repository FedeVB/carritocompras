import { NgModule } from '@angular/core';
import { RouterModule, Routes, CanLoad } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { ListProductsComponent } from './products/list-products/list-products.component';
import { LoginGuard } from './auth/guards/login.guard';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path:'login',
    component:LoginComponent
  },
  {
    path: 'product',
    component: ListProductsComponent,
    canActivate: [LoginGuard],
    canLoad: [LoginGuard]
  },
  {
    path: '**',
    redirectTo: 'login'
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
