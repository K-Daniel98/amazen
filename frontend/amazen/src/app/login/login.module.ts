import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import {RouterModule} from "@angular/router";
import {
  NbButtonModule,
  NbCardModule,
  NbInputModule,
  NbLayoutModule,
  NbSidebarModule,
  NbThemeModule
} from "@nebular/theme";
import {LoginRoutingModule} from "./login-routing.module";

@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    NbThemeModule,
    NbCardModule,
    NbInputModule,
    NbLayoutModule,
    NbSidebarModule,
    NbButtonModule,
    LoginRoutingModule,
  ],
  exports: [LoginComponent]
})
export class LoginModule { }
