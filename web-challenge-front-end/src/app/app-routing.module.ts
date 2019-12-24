import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponentComponent } from './Components/login-component/login-component.component';
import { RegisterComponentComponent } from './Components/register-component/register-component.component';
import { NearbyShopsComponentComponent } from './Components/nearby-shops-component/nearby-shops-component.component';
import { PrefferedShopsComponent } from './Components/preffered-shops/preffered-shops.component';


const routes: Routes = [
  {path:"login", component: LoginComponentComponent},
  {path:"register", component: RegisterComponentComponent},
  {path:"nearBy", component: NearbyShopsComponentComponent},
  {path:"", redirectTo:"login", pathMatch:"full"},
  {path:"preferred", component:PrefferedShopsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
