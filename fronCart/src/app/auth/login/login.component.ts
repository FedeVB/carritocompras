import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from '../interfaces/login.interface';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService,private router:Router) {
  }

  ngOnInit(): void {
  }

  public myLoginForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.email, Validators.required]),
    password: new FormControl('', [Validators.minLength(7)])
  });

  login: Login = {
    email: "",
    password: ""
  }

  get emailAbstractControl(): AbstractControl | null | undefined {
    return this.myLoginForm.get('email');
  }

  get passwordAbstractControl(): AbstractControl | null | undefined {
    return this.myLoginForm.get('password');
  }

  getError(controlName: string): string {
    const control = this.myLoginForm.get(controlName);
    if (control?.invalid && control.hasError('required')) {
      return 'El campo es requerido';
    }
    if (control?.invalid && control.hasError('email')) {
      return 'El formato de email no es correcto';
    }
    if (control?.invalid && control.hasError('minlength')) {
      return 'La cantidad minima de caracteres es 7';
    }
    return '';
  }

  loginUser(): void {
    this.login = this.myLoginForm?.value;
    this.authService.login(this.login).subscribe(data => {
      this.router.navigate(['/product']);
    });
  }
}
