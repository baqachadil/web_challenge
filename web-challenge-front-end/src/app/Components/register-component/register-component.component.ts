import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/Interfaces/User';
import { AuthenticationServiceService } from 'src/app/Services/authentication-service.service';
import Swal from 'sweetalert2'
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
@Component({
  selector: 'app-register-component',
  templateUrl: './register-component.component.html',
  styleUrls: ['./register-component.component.css']
})
export class RegisterComponentComponent implements OnInit {

  constructor(private authenticationService: AuthenticationServiceService, private router: Router,private title: Title) {
    this.title.setTitle("Register")
   }

  ngOnInit() {
  }

  onSubmit(username, password){
    this.authenticationService.register(username, password).subscribe(res=>{
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Your are signed up',
        showConfirmButton: false,
        timer: 1500
      })
      this.router.navigate(['/login'])
    },err=>{
      console.log(err)
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'This user already exists',
        })
    })
  }

}
