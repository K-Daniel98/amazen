import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './register.component';
import {RegisterRoutingModule} from "./register-routing.module";
import {NbButtonModule, NbCardModule, NbInputModule, NbLayoutModule} from "@nebular/theme";

@NgModule({
  declarations: [
    RegisterComponent
  ],
  imports: [
    CommonModule,
    RegisterRoutingModule,
    NbLayoutModule,
    NbCardModule,
    NbInputModule,
    NbButtonModule
  ]
})
export class RegisterModule { }
