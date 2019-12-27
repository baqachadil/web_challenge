import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponentComponent } from './Components/login-component/login-component.component';
import { RegisterComponentComponent } from './Components/register-component/register-component.component';
import { HttpClientModule } from '@angular/common/http';
import { AuthenticationServiceService } from './Services/authentication-service.service';
import { NavBarComponent } from './Components/nav-bar/nav-bar.component';
import { NearbyShopsComponentComponent } from './Components/nearby-shops-component/nearby-shops-component.component';
import { MatCardModule, MatButtonModule, MatIconModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FooterComponent } from './Components/footer/footer.component';
import { PrefferedShopsComponent } from './Components/preffered-shops/preffered-shops.component';
import { PanelComponent } from './Components/panel/panel.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponentComponent,
    RegisterComponentComponent,
    NavBarComponent,
    NearbyShopsComponentComponent,
    FooterComponent,
    PrefferedShopsComponent,
    PanelComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    MatCardModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,    
  ],
  providers: [AuthenticationServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
