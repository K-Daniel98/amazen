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
  NbPopoverModule,
  NbToastrService,
  NbUserModule,
} from '@nebular/theme';
import { DeleteDialogComponent } from './dialog/delete-dialog/delete-dialog.component';
import { ItemService } from './item.service';
import { FormsModule } from '@angular/forms';
import { UpdateDialogComponent } from './dialog/update-dialog/update-dialog.component';
import { CreateDialogComponent } from './dialog/create-dialog/create-dialog.component';

@NgModule({
  declarations: [
    ShopComponent,
    DeleteDialogComponent,
    UpdateDialogComponent,
    CreateDialogComponent,
  ],
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
    NbPopoverModule,
  ],
  providers: [NbDialogService, ItemService, NbToastrService],
})
export class ShopModule {}
