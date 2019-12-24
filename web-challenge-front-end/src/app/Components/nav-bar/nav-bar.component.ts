import { Component, OnInit, DoCheck, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { AuthenticationServiceService } from 'src/app/Services/authentication-service.service';
import { MatIconRegistry } from '@angular/material';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})

@Injectable({
  providedIn: 'root'
})
export class NavBarComponent implements OnInit,DoCheck {

  isLogin = false;
  isAdmin

  coll=false;

  constructor(private router:Router,private authenticationService: AuthenticationServiceService, private iconRegistry: MatIconRegistry,private sanitizer: DomSanitizer ) {
    iconRegistry.addSvgIcon(
      'shop-logo',
      sanitizer.bypassSecurityTrustResourceUrl('assets/shop.svg'));
   }

  ngOnInit() {  
  }
  
  ngDoCheck(){
    if(this.authenticationService.getToken() != null) this.isLogin = true
    if(localStorage.getItem('isAdmin') == 'true') this.isAdmin=true
  }

  logOut(){
    this.authenticationService.logOut()
    this.isLogin = false
    this.router.navigate(['/'])
  }

}
