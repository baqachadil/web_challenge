import { Component, OnInit } from '@angular/core';
import { AuthenticationServiceService } from 'src/app/Services/authentication-service.service';
import { Router } from '@angular/router';
import { MatIconRegistry } from '@angular/material';
import { DomSanitizer, Title } from '@angular/platform-browser';

@Component({
  selector: 'app-nearby-shops-component',
  templateUrl: './nearby-shops-component.component.html',
  styleUrls: ['./nearby-shops-component.component.css']
})
export class NearbyShopsComponentComponent implements OnInit {

  shops
  images = new Array("https://i.ibb.co/n33YdTt/Untitled-2.png","https://i.ibb.co/r2YdPr0/Untitled-1.png");
  constructor(private title: Title,private authenticationService: AuthenticationServiceService, private router: Router,iconRegistry: MatIconRegistry, private sanitizer : DomSanitizer) {
    title.setTitle("NearBy Shops")
    iconRegistry.addSvgIcon(
      'thumb-up',
      sanitizer.bypassSecurityTrustResourceUrl('assets/thumb-up.svg'));
      iconRegistry.addSvgIcon(
        'thumb-down',
        sanitizer.bypassSecurityTrustResourceUrl('assets/thumb-down.svg'));
   }    

  ngOnInit() {
    if(this.authenticationService.getToken() != null){
      this.authenticationService.getNearbyShops().subscribe(res=>{   
        this.shops=res
      },err=>{
        console.log(err)
      })
    }else{
      this.router.navigate(['/'])
    }
    
  }

  like(i){
    this.authenticationService.likeShop(this.shops[i].id).subscribe(res => {
      this.shops.splice(i, 1)
    },err => {
      console.log(err)
    })
  }

  dislike(i){
    this.authenticationService.dislikeShop(this.shops[i].id).subscribe(res => {
      this.shops.splice(i, 1)
    },err => {
      console.log(err)
    })
  }

}
