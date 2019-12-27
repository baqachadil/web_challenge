import { Component, OnInit, DoCheck } from '@angular/core';
import { AuthenticationServiceService } from 'src/app/Services/authentication-service.service';
import { Router } from '@angular/router';
import { MatIconRegistry } from '@angular/material';
import { DomSanitizer, Title } from '@angular/platform-browser';

@Component({
  selector: 'app-preffered-shops',
  templateUrl: './preffered-shops.component.html',
  styleUrls: ['./preffered-shops.component.css']
})
export class PrefferedShopsComponent implements OnInit, DoCheck {


  preferredShops
  constructor(private authenticationService: AuthenticationServiceService, private router: Router, private iconRegistry: MatIconRegistry, private sanitizer: DomSanitizer, private title: Title) {
    this.title.setTitle("Preferred Shops")
    iconRegistry.addSvgIcon(
      'thumb-up',
      sanitizer.bypassSecurityTrustResourceUrl('assets/smiling.svg'));
      iconRegistry.addSvgIcon(
        'delete',
        sanitizer.bypassSecurityTrustResourceUrl('assets/delete.svg'));
   }

  ngOnInit() {
    if(this.authenticationService.getToken() != null){
      this.authenticationService.getPreferredShops().subscribe(res=>{    
        console.log(res)    
        this.preferredShops=res
      },err=>{
        console.log(err)
      })
    }else{
      this.router.navigate(['/'])
    }
  }

  ngDoCheck(){
    
  }

  delete(i){
    this.authenticationService.deletePreferredShop(this.preferredShops[i].id).subscribe(res=>{
      this.preferredShops.splice(i, 1)
    },err=>{
      console.log(err)
    })
  }

}
