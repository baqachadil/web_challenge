import { Component, OnInit } from '@angular/core';
import { AuthenticationServiceService } from 'src/app/Services/authentication-service.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { LocationService } from 'src/app/Services/location.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent implements OnInit {

  loading = false
  constructor(private authenticationService: AuthenticationServiceService, private router: Router, private locationService: LocationService, private title: Title) {
    this.title.setTitle("Login")
   }
  
  ngOnInit() {
    if(localStorage.getItem('JwtToken')) this.router.navigate(['/nearBy'])
  }  

  
  onSubmit(email,pass){    
    this.authenticationService.login(email.value, pass.value).subscribe(
      res => {
        this.loading = true
        this.locationService.getPosition().then(pos=>
          {
             console.log(`Positon: ${pos.lng} ${pos.lat}`);             
             Swal.fire({
              position: 'top-end',
              icon: 'success',
              title: 'Your are logged in',
              showConfirmButton: false,
              timer: 1500
            })
            let jwtToken = res.headers.get('Authorization')
            this.authenticationService.saveToken(jwtToken)
            this.locationService.setPosition(pos.lat, pos.lng).subscribe()
            this.router.navigate(['/nearBy'])
          });        
      },
      err => {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Bad Credentials !! verify your username or password',
        })
      })
  }

}
