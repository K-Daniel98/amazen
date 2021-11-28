import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ShopComponent } from './shop.component';
import { ShopRoutingModule } from './shop-routing.module';
import {
  NbButtonGroupModule,
  NbButtonModule,
  NbCardModule,
  NbDialogModule,
  NbDialogService,
  NbFormFieldModule,
  NbIconModule,
  NbInputModule,
  NbLayoutModule,
  NbUserModule,
} from '@nebular/theme';
import { DeleteDialogComponent } from './dialog/delete-dialog.component';
import { ItemService } from './item.service';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [ShopComponent, DeleteDialogComponent],
  imports: [
    CommonModule,
    ShopRoutingModule,
    NbLayoutModule,
    NbCardModule,
    NbInputModule,
    NbButtonModule,
    NbUserModule,
    NbButtonGroupModule,
    NbIconModule,
    NbDialogModule,
    NbFormFieldModule,
    FormsModule,
  ],
  providers: [NbDialogService, ItemService],
})
export class ShopModule {}
